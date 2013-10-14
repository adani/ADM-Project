package org.adm.project.dao;

import java.util.regex.Pattern;

import org.adm.project.model.Book;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class BookDao {
	private DBCollection booksCollection;
	private DBCollection stockCollection;
	
	public BookDao(DB database) {
		booksCollection = database.getCollection("books");
		stockCollection = database.getCollection("stock");
	}
	
	public String[] getAllIsbn() {
		BasicDBObject isbnKeys = new BasicDBObject();
		isbnKeys.put("isbn", 1);
		
		DBCursor cursor = booksCollection.find(new BasicDBObject(), isbnKeys);
		String isbn[] = new String[cursor.count()];
		int index = 0;
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			isbn[index] = (String) dbObject.get("isbn");
			index++;
		}
		
		return isbn;
	}
	
	public Book getBookByIsbn(String isbn) {
		BasicDBObject isbnCondition = new BasicDBObject("isbn", isbn);
		DBCursor cursor = booksCollection.find(isbnCondition);
		
		if (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			return createBookFromDBObject(dbObject);
		}
		
		return null;
	}
	
	private Book createBookFromDBObject(DBObject dbObject) {
		Book book = new Book();
		book.setCoverUrl((String)dbObject.get("cover_url"));
		book.setIsbn((String)dbObject.get("isbn"));
		book.setTitle((String)dbObject.get("title"));
		book.setPrice((double)dbObject.get("price"));
		book.setDescription((String)dbObject.get("desc"));
		BasicDBList list = (BasicDBList) dbObject.get("authors");
		String[] authors = new String[list.size()];
		int index = 0;
		for (Object author : list) {
			authors[index] = (String) author;
			index++;
		}
		book.setAuthors(authors);
		return book;
	}
	
	public void insertBook(Book book, int stock) {
		BasicDBList authorList = new BasicDBList();
		String[] authors = book.getAuthors();
		if (authors == null) {
			//System.out.println(book.getIsbn());
		} else {
			for (String author : authors) {
				authorList.add(author);
			}
		}
		BasicDBObject object = new BasicDBObject("isbn", book.getIsbn())
				.append("title", book.getTitle())
				.append("cover_url", book.getCoverUrl())
				.append("authors", authorList)
				.append("desc", book.getDescription())
				.append("price", book.getPrice());
		booksCollection.insert(object);
		
		BasicDBObject stockDoc = new BasicDBObject("isbn", book.getIsbn())
				.append("num_of_stock", stock);
		stockCollection.insert(stockDoc);
	}
	
	public int getBookStock(String isbn) {
		BasicDBObject isbnCondition = new BasicDBObject("isbn", isbn);
		DBCursor cursor = stockCollection.find(isbnCondition);
		
		if (cursor.count() == 0) {
			throw new IllegalArgumentException("No such book. isbn: " + isbn);
		} else {
			DBObject stock = cursor.next();
			return (Integer) stock.get("num_of_stock");
		}
	}
	
	public void setBookStock(String isbn, int value) {
		BasicDBObject isbnCondition = new BasicDBObject("isbn", isbn);
		DBCursor cursor = stockCollection.find(isbnCondition);
		
		if (cursor.count() == 0) {
			throw new IllegalArgumentException("No such book. isbn: " + isbn);
		} else {
			DBObject stock = cursor.next();
			stock.put("num_of_stock", value);
			
			stockCollection.save(stock);
		}
	}
	
	public Book[] getBooksTitleContains(String text) {
		BasicDBObject titleContains = new BasicDBObject("title", Pattern.compile(text, Pattern.CASE_INSENSITIVE));
		DBCursor booksCursor = booksCollection.find(titleContains);
		
		Book[] books = new Book[booksCursor.count()];
		int i = 0;
		for (DBObject dbObject : booksCursor) {
			books[i++] = createBookFromDBObject(dbObject);
		}
		
		return books;
	}
	
	public Book[] getBooksAuthoredBy(String author) {
		BasicDBList authors = new BasicDBList();
		authors.add(author);
		BasicDBObject bookWithAuthor = new BasicDBObject("authors",
				new BasicDBObject("$all", authors));
		DBCursor booksCursor = booksCollection.find(bookWithAuthor);
		
		Book[] books = new Book[booksCursor.count()];
		int i = 0;
		for (DBObject dbObject : booksCursor) {
			books[i++] = createBookFromDBObject(dbObject);
		}
		
		return books;
	}
	
	public String[] getBestSellerIsbn(int limit) {
		DBCursor stockCursor = stockCollection.find().sort(new BasicDBObject("num_of_stock", 1)).limit(limit);
		
		String[] isbns = new String[limit];
		int i =0;
		for (DBObject dbObject : stockCursor) {
			isbns[i] = (String) dbObject.get("isbn");
			i++;
		}
		return isbns;
	}
}
