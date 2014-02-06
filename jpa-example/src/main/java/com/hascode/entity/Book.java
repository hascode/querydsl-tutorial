package com.hascode.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private Author author;

	@Temporal(TemporalType.DATE)
	private Date published;

	@OneToMany(cascade = CascadeType.ALL)
	private final List<Tag> tags = new ArrayList<>();

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
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
		this.tags.add(tag);
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
