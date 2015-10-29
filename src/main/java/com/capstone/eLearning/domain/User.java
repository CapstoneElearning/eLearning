package com.capstone.eLearning.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	
	public User(){
		
	}
	
	private Integer id;
	private String username;
	private String password;
	private String fname;
	private String mname;
	private String lname;
	private Date dob;
	private String email;
	private String phone_home;
	private String phone_cell;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private Integer zip;
	private String country;
	private String pwd_hint;
	private String pwd_hint_ans;
	private int active;
	private Date registered_on;
	private Integer role_id;
	
	
	public Date getRegistered_on() {
		return registered_on;
	}
	public void setRegistered_on(Date registered_on) {
		this.registered_on = registered_on;
	}


	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_home() {
		return phone_home;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setPhone_home(String phone_home) {
		this.phone_home = phone_home;
	}
	public String getPhone_cell() {
		return phone_cell;
	}
	public void setPhone_cell(String phone_cell) {
		this.phone_cell = phone_cell;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPwd_hint() {
		return pwd_hint;
	}
	public void setPwd_hint(String pwd_hint) {
		this.pwd_hint = pwd_hint;
	}
	public String getPwd_hint_ans() {
		return pwd_hint_ans;
	}
	public void setPwd_hint_ans(String pwd_ans) {
		this.pwd_hint_ans = pwd_ans;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}

	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	
	
}
