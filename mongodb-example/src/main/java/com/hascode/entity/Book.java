package com.hascode.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Book {
	@Id
	private ObjectId id;

	private String title;

	@Reference
	private Author author;

	private Date published;

	@Reference
	private final List<Tag> tags = new ArrayList<>();

	public final ObjectId getId() {
		return id;
	}

	public final void setId(final ObjectId id) {
		this.id = id;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(final String title) {
		this.title = title;
	}

	public final Author getAuthor() {
		return author;
	}

	public final void setAuthor(final Author author) {
		this.author = author;
	}

	public final Date getPublished() {
		return published;
	}

	public final void setPublished(final Date published) {
		this.published = published;
	}

	public final List<Tag> getTags() {
		return tags;
	}

	public void addTag(final Tag tag) {
		tags.add(tag);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=").append(id).append(", title=").append(title)
				.append(", author=").append(author).append(", published=")
				.append(published).append(", tags=").append(tags).append("]");
		return builder.toString();
	}

}
