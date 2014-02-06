package com.hascode.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany
	private final List<Book> books = new ArrayList<>();

	public Author() {
	}

	public Author(final String name) {
		this.name = name;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final List<Book> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Author [id=").append(id).append(", name=").append(name)
				.append("]");
		return builder.toString();
	}

}
