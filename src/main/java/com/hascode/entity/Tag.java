package com.hascode.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Tag {
	private String name;

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}
}
