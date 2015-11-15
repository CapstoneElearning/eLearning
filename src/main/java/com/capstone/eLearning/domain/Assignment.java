package com.capstone.eLearning.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import jersey.repackaged.com.google.common.base.Objects;

@XmlRootElement(name = "course_assignments")
public class Assignment {
	private int id;
	private String desc;
	private Date due_date;
	private int allow_late_submit;
	private int course_id;
	private int student_id;
	private int assignment_type;
	private double total_marks;
	private double secured_marks;
	private int document_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public int getAllow_late_submit() {
		return allow_late_submit;
	}
	public void setAllow_late_submit(int allow_late_submit) {
		this.allow_late_submit = allow_late_submit;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getAssignment_type() {
		return assignment_type;
	}
	public void setAssignment_type(int assignment_type) {
		this.assignment_type = assignment_type;
	}
	public double getTotal_marks() {
		return total_marks;
	}
	public void setTotal_marks(double total_marks) {
		this.total_marks = total_marks;
	}
	public double getSecured_marks() {
		return secured_marks;
	}
	public void setSecured_marks(double secured_marks) {
		this.secured_marks = secured_marks;
	}
	public int getDocument_id() {
		return document_id;
	}
	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		         .add("id", id)
		         .add("desc", desc)
		         .add("due_date", due_date)
		         .add("allow_late_submit", allow_late_submit)
		         .add("course_id", course_id)
		         .add("student_id", student_id)
		         .add("assignment_type", assignment_type)
		         .add("total_marks", total_marks)
		         .add("secured_marks", secured_marks)
		         .add("document_id", document_id)
		         .toString();
	}
}
