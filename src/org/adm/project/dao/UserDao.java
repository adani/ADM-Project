package org.adm.project.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.adm.project.SessionData;
import org.adm.project.model.Book;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;


public class UserDao {
	
	public enum Relationship implements RelationshipType { BUYS }
	
	private static final String USERNAME_KEY = "username";
	private static final String ISBN_KEY = "isbn";
	
	private GraphDatabaseService graphDb;
	private Index<Node> userIndex;
	private Index<Node> bookIndex;
	private BookDao bookDao;
	
	public UserDao() {
		graphDb = SessionData.GRAPH_DB;
		userIndex = graphDb.index().forNodes("userNode");
		bookIndex = graphDb.index().forNodes("bookNode");
		bookDao = new BookDao(SessionData.MONGO_DB);
	}
	
	public boolean addNewUser(String userName) {
		boolean success = false;
		if (getUserNode(userName) == null) {
			Transaction trans = graphDb.beginTx();
			try {
				Node node = graphDb.createNode();
				node.setProperty(USERNAME_KEY, userName);
				userIndex.add(node, USERNAME_KEY, userName);
				trans.success();
				success = true;
			} finally {
				trans.finish();
			}
		}
		return success;
	}
	
	public boolean isUserExist(String username) {
		Node foundUser = getUserNode(username);
		return (foundUser != null);
	}
	
	public Node getUserNode (String username) {
		Node foundUser = userIndex.get(USERNAME_KEY, username).getSingle();
		return foundUser;
	}
	
	public void assertUserBuyBook(String userName, String isbn) {
		Transaction trans = graphDb.beginTx();
		try {
			Node user = getUserNode(userName);
			if (user != null) {
				Node book = getBookNode(isbn);
				if (book == null) {
					addNewBook(isbn);
					book = getBookNode(isbn);
				}
				user.createRelationshipTo(book, Relationship.BUYS);
			} else {
				throw new IllegalArgumentException("user does not exists");
			}
			trans.success();
		} finally {
			trans.finish();
		}
	}
	
	private boolean addNewBook(String isbn) {
		boolean success = false;
		if (getBookNode(isbn) == null) {
			Transaction trans = graphDb.beginTx();
			try {
				Node node = graphDb.createNode();
				node.setProperty(ISBN_KEY, isbn);
				bookIndex.add(node, ISBN_KEY, isbn);
				trans.success();
				success = true;
			} finally {
				trans.finish();
			}	
		}
		return success;
	}
	
	private Node getBookNode(String isbn) {
		return bookIndex.get(ISBN_KEY, isbn).getSingle();
	}
	
	public String[] getBoughtBooks(String userName) {
		Node user = getUserNode(userName);
		if (user == null) {
			throw new IllegalArgumentException("user does not exists");
		}
		Iterable<org.neo4j.graphdb.Relationship> relationships = user.getRelationships();
		ArrayList<String> isbns = new ArrayList<String>();
		for (org.neo4j.graphdb.Relationship relationship : relationships) {
			String isbn = relationship.getEndNode().getProperty(ISBN_KEY).toString();
			isbns.add(isbn);
		}
		return isbns.toArray(new String[isbns.size()]);
	}
	
	public String[] recommendBooks(String userName) {
		ArrayList<String> recomBooks = new ArrayList<String>();
		
		//look at books that has the same authors to the one he buys
		String[] isbns = getBoughtBooks(userName);
		Set<String> isbnSet = new HashSet<String>(Arrays.asList(isbns));
		for (String isbn : isbns) {
			Book book = bookDao.getBookByIsbn(isbn);
			String[] authors = book.getAuthors();
			for (String author : authors) {
				Book[] authoredBooks = bookDao.getBooksAuthoredBy(author);
				for (Book book2 : authoredBooks) {
					if (!isbnSet.contains(book2.getIsbn())) {
						recomBooks.add(book2.getIsbn());
					}
				}
			}
		}
		
		//look at books other's buy (best seller)
		String[] bestSellers = bookDao.getBestSellerIsbn(10);
		recomBooks.addAll(Arrays.asList(bestSellers));
		Collections.shuffle(recomBooks);
		return recomBooks.subList(0, 9).toArray(new String[10]);
	}
}
