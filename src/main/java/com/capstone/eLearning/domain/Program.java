package com.capstone.eLearning.domain;

import jersey.repackaged.com.google.common.base.Objects;

public class Program {
	private int id;
	private String name;
	private String degree;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		         .add("id", id)
		         .add("name", name)
		         .add("degree", degree)
		         .toString();
	}
}
