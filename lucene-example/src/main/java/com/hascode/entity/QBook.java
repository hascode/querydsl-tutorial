package com.hascode.entity;

import org.apache.lucene.document.Document;

import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.StringPath;

public class QBook extends EntityPathBase<Document> {
	private static final long serialVersionUID = 1L;

	public QBook(final String var) {
		super(Document.class, PathMetadataFactory.forVariable(var));
	}

	public final StringPath title = createString("title");
	public final StringPath author = createString("author");
	// public final DatePath<Date> published = createDate("published",
	// Date.class);
	// public final ArrayPath<String> tags = createArray ("tags", String.class);

}
