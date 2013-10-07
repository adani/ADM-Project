package org.adm.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.adm.project.dao.BookDao;
import org.adm.project.model.Book;
import org.adm.project.rdf.BookProperty;
import org.adm.project.rdf.BookSelector;
import org.apache.jena.riot.RiotException;

import com.hp.hpl.jena.graph.Node_URI;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MekongBookStore {

	public static final String DC_NS = "http://purl.org/dc/terms/";
	public static final String OM_NS = "http://purl.oreilly.com/ns/meta/";

	public static void main(String args[]) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("bookstore");

		if (!db.collectionExists("books")) {
			populateBooksColl(db);
		}
		
	}

	public static ArrayList<Book> readBooks() {
		File dataFolder = new File("data/");
		ArrayList<Book> books = new ArrayList<Book>();

		for (File entryFile : dataFolder.listFiles()) {
			FileInputStream fileInputStream;
			Model dataModel = ModelFactory.createDefaultModel();
			try {
				fileInputStream = new FileInputStream(entryFile);
				dataModel.read(fileInputStream, null);

				StmtIterator iterator = dataModel
						.listStatements(new BookSelector(null, null, null));
				BookProperty bookProperty = new BookProperty(dataModel);
				Book book = new Book();
				String fileName = entryFile.getName();
				book.setIsbn(fileName.substring(0, fileName.length() - 4));
				while (iterator.hasNext()) {
					Statement statement = iterator.next();
					Property pred = statement.getPredicate();
					if (pred.equals(bookProperty.getTitle())) {
						String rawTitle = statement.getObject().toString();
						int idx = rawTitle.indexOf('@');
						book.setTitle(rawTitle.substring(0, idx));
					} else if (pred.equals(bookProperty.getCover())) {
						book.setCoverUrl(statement.getObject().toString());
					} else if (pred.equals(bookProperty.getDescription())) {
						String rawDesc = statement.getObject().toString();
						int idx = rawDesc.indexOf("^^");
						if (idx > 7) {
							book.setDescription(rawDesc.substring(5, idx - 7));
						}
					} else if (pred.equals(bookProperty.getPrice())) {
						try {
							Resource perunitRes = statement.getObject()
									.asResource();
							Property spatial = dataModel.getProperty(DC_NS
									+ "spatial");
							if (perunitRes.getProperty(spatial) != null
									&& perunitRes.getProperty(spatial)
											.getObject().toString()
											.equals("USA@en")) {
								String price = perunitRes
										.getProperty(RDF.value).getObject()
										.toString();
								int idx = price.indexOf('@');
								book.setPrice(Float.parseFloat(price.substring(
										0, idx)));
							}
						} catch (Exception e) {
							// ignores
						}
					} else if (pred.equals(bookProperty.getCreator())) {
						Resource rdfSeq = statement.getObject().asResource();
						StmtIterator authorIterator = rdfSeq.listProperties();
						ArrayList<String> authors = new ArrayList<String>();
						while (authorIterator.hasNext()) {
							Statement authorStatement = authorIterator.next();
							Resource creator = authorStatement.getObject()
									.asResource();
							Statement personStatement = creator
									.getProperty(bookProperty.getFoafName());
							if (personStatement != null) {
								String authorName = personStatement.getObject()
										.toString();
								int idx = authorName.indexOf('@');
								authors.add(authorName.substring(0, idx));
							}
						}
						book.setAuthors(authors.toArray(new String[authors
								.size()]));
					}
				}
				books.add(book);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (RiotException e) {
				System.err.print("Skipped file: " + entryFile.getName() + " ");
				System.err.println(e.getMessage());
			}
		}
		return books;
	}

	public static void populateBooksColl(DB db) {
		ArrayList<Book> books = readBooks();
		BookDao bookController = new BookDao(db);
		
		for (Book book : books) {
			bookController.insertBook(book, 100);
		}
	}
}
