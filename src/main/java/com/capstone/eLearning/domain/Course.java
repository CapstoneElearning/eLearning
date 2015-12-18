package com.capstone.eLearning.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import jersey.repackaged.com.google.common.base.Objects;

@XmlRootElement(name = "course")
public class Course {
	private int id;
	private String description;
	private String schedule_day;
	private Date schedule_time;
    private Date start_date;
	private Date end_date;
	private double credits;
	private Subject subject;
	private Department department;
	private Program program;
	private User instructor;
	private int room;
	private boolean active;

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


	public String getSchedule_day() {
		return schedule_day;
	}


	public void setSchedule_day(String schedule_day) {
		this.schedule_day = schedule_day;
	}


	public Date getSchedule_time() {
		return schedule_time;
	}


	public void setSchedule_time(Date schedule_time) {
		this.schedule_time = schedule_time;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public double getCredits() {
		return credits;
	}


	public void setCredits(double credits) {
		this.credits = credits;
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


	public Program getProgram() {
		return program;
	}


	public void setProgram(Program program) {
		this.program = program;
	}


	public User getInstructor() {
		return instructor;
	}


	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}


	public int getRoom() {
		return room;
	}


	public void setRoom(int room) {
		this.room = room;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		         .add("id", id)
		         .add("description", description)
		         .add("schedule_day", schedule_day)
		         .add("schedule_time", schedule_time)
		         .add("start_date", start_date)
		         .add("end_date", end_date)
		         .add("credits", credits)
		         .add("subject", subject)
		         .add("department", department)
		         .add("program", program)
		         .add("instructor", instructor)
		         .add("room", room)
		         .add("active", active)
		         .toString();
	}

}
