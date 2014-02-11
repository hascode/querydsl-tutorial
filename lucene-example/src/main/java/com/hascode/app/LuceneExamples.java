package com.hascode.app;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.joda.time.LocalDate;

import com.hascode.entity.QBook;
import com.mysema.query.lucene.LuceneQuery;
import com.mysema.query.lucene.LuceneSerializer;

public class LuceneExamples {
	public void run() throws IOException {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2010, 2, 1); // 2010-03-01
		LocalDate date1 = new LocalDate(2010, 1, 1);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2011, 1, 13); // 2011-02-13
		LocalDate date2 = new LocalDate(2011, 2, 13);

		Directory index = new RAMDirectory();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,
				analyzer).setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(index, iwc);

		createBook(writer, "The big book of something", "Some Guy", date1,
				20.50F);
		createBook(writer, "Another book of something", "Arthur Someone",
				date2, 13.5F);
		createBook(writer, "Anything, onething, something", "Some Guy", date1,
				29.99F);
		createBook(writer, "Something, somewhere", "Some Guy", date1, 9.50F);
		writer.commit();
		writer.close();

		QBook book = new QBook("book");

		IndexReader reader = DirectoryReader.open(index);
		System.out.println(reader.numDocs() + " books stored in index");
		IndexSearcher searcher = new IndexSearcher(reader);

		LuceneQuery query = new LuceneQuery(new LuceneSerializer(true, true),
				searcher);
		System.out.println("Searching a Lucene Index with Querydsl");
		LuceneQuery bookQuery = query.where(book.author.eq("Some Guy"),
				book.published.before(date2), book.price.between(10.F, 30.F),
				book.title.endsWith("something")).orderBy(book.title.asc());
		System.out.println("generated lucene query: " + bookQuery.toString());
		List<Document> documents = bookQuery.list();

		System.out.println(documents.size() + " books found");
		for (Document doc : documents) {
			System.out.println(doc);
		}
	}

	private void createBook(final IndexWriter writer, final String title,
			final String author, final LocalDate published, final float price)
			throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Store.YES));
		doc.add(new TextField("author", author, Store.YES));
		doc.add(new StringField("published", published.toString(), Store.YES));
		doc.add(new FloatField("price", price, Store.YES));
		writer.addDocument(doc);
	}

	public static void main(final String[] args) throws IOException {
		new LuceneExamples().run();
	}
}
