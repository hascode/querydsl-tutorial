package com.hascode.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Tag {
	@Id
	private ObjectId id;

	private String name;

	public Tag() {
	}

	public Tag(final String name) {
		this.name = name;
	}

	public final ObjectId getId() {
		return id;
	}

	public final void setId(final ObjectId id) {
		this.id = id;
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
		builder.append("Tag [id=").append(id).append(", name=").append(name)
				.append("]");
		return builder.toString();
	}

}
