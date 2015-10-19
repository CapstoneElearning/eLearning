package com.capstone.eLearning.domain;

import javax.xml.bind.annotation.XmlRootElement;

import jersey.repackaged.com.google.common.base.Objects;

@XmlRootElement(name = "department")
public class Department {
	private int deptId;
	private String name;
	private String location;
	private String schoolCode;
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		         .add("id", deptId)
		         .add("name", name)
		         .add("location", location)
		         .add("schoolCode", schoolCode)
		         .toString();
	}
}
