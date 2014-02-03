package com.hascode.app;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hascode.entity.Author;
import com.hascode.entity.Book;
import com.hascode.entity.QBook;
import com.hascode.entity.Tag;
import com.mysema.query.jpa.impl.JPAQuery;

public class Main {
	public void run() {
		EntityManager em = Persistence.createEntityManagerFactory("default")
				.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2010, 0, 0);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2011, 0, 0);

		Author author = new Author("Some Guy");
		em.persist(author);

		Book b1 = new Book();
		b1.setTitle("The big book of something");
		b1.setAuthor(author);
		b1.setPublished(cal2.getTime());
		Tag horrorTag = new Tag("Horror");
		Tag dramaTag = new Tag("Drama");
		Tag scienceTag = new Tag("Science");
		b1.addTag(horrorTag);
		b1.addTag(dramaTag);
		b1.addTag(scienceTag);

		Book b2 = new Book();
		b2.setTitle("Another book");
		b2.setAuthor(author);

		em.persist(b1);
		em.persist(b2);

		JPAQuery query = new JPAQuery(em);
		QBook book = QBook.book;
		Book b = query
				.from(book)
				.where(book.title.startsWith("The"),
						book.published.after(cal1.getTime()),
						book.author.name.eq("Some Guy"),
						book.tags.contains(horrorTag)).uniqueResult(book);
		System.out.println(b.toString());

	}

	public static void main(final String[] args) {
		new Main().run();
	}
}
