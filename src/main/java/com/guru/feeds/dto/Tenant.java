package com.guru.feeds.dto;

public class Tenant {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + "]";
	}
}
