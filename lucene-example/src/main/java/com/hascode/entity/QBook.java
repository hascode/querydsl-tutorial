package com.hascode.entity;

import org.apache.lucene.document.Document;
import org.joda.time.LocalDate;

import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

public class QBook extends EntityPathBase<Document> {
	private static final long serialVersionUID = 1L;

	public QBook(final String var) {
		super(Document.class, PathMetadataFactory.forVariable(var));
	}

	public final StringPath title = createString("title");
	public final StringPath author = createString("author");
	public final DatePath<LocalDate> published = createDate("published",
			LocalDate.class);
	public final NumberPath<Float> price = createNumber("price", Float.class);

}
