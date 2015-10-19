package com.capstone.eLearning.domain;

import javax.xml.bind.annotation.XmlRootElement;

import jersey.repackaged.com.google.common.base.Objects;

@XmlRootElement(name = "course")
public class Course {
	private int id;
	private String description;
	private Subject subject;
	private Department department;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		         .add("id", id)
		         .add("description", description)
		         .add("subject", subject)
		         .add("department", department)
		         .toString();
	}

}
