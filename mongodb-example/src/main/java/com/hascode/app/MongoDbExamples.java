package com.hascode.app;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.hascode.entity.Author;
import com.hascode.entity.Book;
import com.hascode.entity.QBook;
import com.hascode.entity.Tag;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mysema.query.mongodb.morphia.MorphiaQuery;

import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;

public class MongoDbExamples {
	public static void main(final String[] args) throws UnknownHostException,
			MongoException, IOException {
		// test database
		String dbName = "testdb";
		new MongodForTestsFactory();
		MongoClient client = MongodForTestsFactory
				.with(Version.Main.PRODUCTION).newMongo();

		Morphia morphia = new Morphia()
				.map(Book.class, Author.class, Tag.class);

		Datastore ds = morphia.createDatastore(client, dbName);
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2010, 0, 0);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2011, 0, 0);

		Author author = new Author("Some guy");
		ds.save(author);

		Tag horrorTag = new Tag("Horror");
		Tag dramaTag = new Tag("Drama");
		Tag scienceTag = new Tag("Science");
		ds.save(horrorTag);
		ds.save(dramaTag);
		ds.save(scienceTag);

		Book b1 = new Book();
		b1.setTitle("The big book of something");
		b1.setAuthor(author);
		b1.setPublished(cal2.getTime());
		b1.addTag(horrorTag);
		b1.addTag(dramaTag);
		b1.addTag(scienceTag);

		Book b2 = new Book();
		b2.setTitle("Another book");
		b2.setAuthor(author);

		Book b3 = new Book();
		b3.setTitle("The other book of something");
		b3.setAuthor(author);
		b3.setPublished(cal2.getTime());
		b3.addTag(dramaTag);

		ds.save(b1);
		ds.save(b2);

		QBook book = QBook.book;
		MorphiaQuery<Book> query = new MorphiaQuery<Book>(morphia, ds, book);
		List<Book> books = query.where(book.title.startsWith("The"),
				book.published.after(cal1.getTime()),
				book.author.name.eq("Some guy"), book.tags.contains(horrorTag))
				.list();
		for (Book bookFound : books) {
			System.out.println(bookFound.toString());
		}
	}
}
