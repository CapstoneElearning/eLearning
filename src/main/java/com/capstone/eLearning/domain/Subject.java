package com.capstone.eLearning.domain;

import javax.xml.bind.annotation.XmlRootElement;

import jersey.repackaged.com.google.common.base.Objects;

@XmlRootElement(name = "subject")
public class Subject {
	private int subjectId;
	private String subjectName;
	private String subjectDesc;
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		         .add("id", subjectId)
		         .add("name", subjectName)
		         .add("desc", subjectDesc)
		         .toString();
	}
}
