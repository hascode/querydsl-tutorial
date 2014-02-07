package com.hascode.app;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.hascode.entity.Author;
import com.hascode.entity.Book;
import com.hascode.entity.QBook;
import com.hascode.entity.Tag;
import com.mysema.query.jpa.impl.JPAQuery;

public class JPAExamples {
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

		Tag horrorTag = new Tag("Horror");
		Tag dramaTag = new Tag("Drama");
		Tag scienceTag = new Tag("Science");

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

		em.persist(b1);
		em.persist(b2);
		em.persist(b3);

		// Using Querydsl
		JPAQuery query = new JPAQuery(em);
		QBook book = QBook.book;
		Book bookFound1 = query
				.from(book)
				.where(book.title.startsWith("The"),
						book.published.after(cal1.getTime()),
						book.author.name.eq("Some Guy"),
						book.tags.contains(horrorTag))
				.orderBy(book.title.asc()).uniqueResult(book);
		System.out.println("Using Querydsl:\t\t\t" + bookFound1.toString());

		// Using JPA Criteria API
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> bk = cq.from(Book.class);
		cq.select(bk);
		Expression<List<Tag>> tags = bk.get("tags");
		cq.where(cb.like(bk.get("title").as(String.class), "The%"),
				cb.greaterThan(bk.get("published").as(Date.class),
						cal1.getTime()), cb.equal(bk.get("author").get("name")
						.as(String.class), "Some Guy"), cb.isMember(horrorTag,
						tags));

		cq.orderBy(cb.asc(bk.get("title")));
		Book bookFound2 = em.createQuery(cq).getSingleResult();
		System.out.println("Using JPA Criteria API:\t\t"
				+ bookFound2.toString());

		// Using JPA Query Language
		Book bookFound3 = em
				.createQuery(
						"SELECT book FROM Book book WHERE book.title LIKE 'The%' AND book.published>:published AND book.author.name=:author AND :tag MEMBER OF book.tags ORDER BY book.title ASC",
						Book.class).setParameter("published", cal1.getTime())
				.setParameter("author", "Some Guy")
				.setParameter("tag", new Tag("Horror")).getSingleResult();
		System.out.println("Using JPA Query Language:\t"
				+ bookFound3.toString());
		tx.rollback();

	}

	public static void main(final String[] args) {
		new JPAExamples().run();
	}
}
