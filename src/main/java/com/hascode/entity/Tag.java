package com.hascode.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
	private String name;

	public Tag() {
	}

	public Tag(final String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tag [name=").append(name).append("]");
		return builder.toString();
	}
}
