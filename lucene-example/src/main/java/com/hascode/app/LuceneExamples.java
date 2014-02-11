package com.hascode.app;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
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

import com.hascode.entity.QBook;
import com.mysema.query.lucene.LuceneQuery;
import com.mysema.query.lucene.LuceneSerializer;

public class LuceneExamples {
	public void run() throws IOException {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2010, 0, 0);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2011, 0, 0);

		Directory index = new RAMDirectory();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,
				analyzer).setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(index, iwc);

		createBook(writer, "The big book of something", "Some Guy",
				cal2.getTime(), "comedy", "horror", "action");
		writer.commit();
		writer.close();

		QBook book = new QBook("book");

		IndexReader reader = DirectoryReader.open(index);
		System.out.println(reader.numDocs() + " books stored in index");
		IndexSearcher searcher = new IndexSearcher(reader);

		LuceneQuery query = new LuceneQuery(new LuceneSerializer(true, true),
				searcher);
		System.out.println("Searching a Lucene Index with Querydsl");
		LuceneQuery bookQuery = query.where(book.author.eq("Some Guy"))
				.orderBy(book.title.asc());
		System.out.println("generated lucene query: " + bookQuery.toString());
		List<Document> documents = bookQuery.list();

		System.out.println(documents.size() + " books found");
	}

	private void createBook(final IndexWriter writer, final String title,
			final String author, final Date published, final String... tags)
			throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Store.YES));
		doc.add(new TextField("author", author, Store.YES));
		doc.add(new StringField("published", DateTools.dateToString(published,
				Resolution.YEAR), Store.YES));
		for (String tag : tags)
			doc.add(new StringField("tag", tag, Store.YES));
		writer.addDocument(doc);
	}

	public static void main(final String[] args) throws IOException {
		new LuceneExamples().run();
	}
}
