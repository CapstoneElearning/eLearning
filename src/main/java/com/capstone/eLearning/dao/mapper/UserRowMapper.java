package com.capstone.eLearning.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capstone.eLearning.domain.User;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		final User user = new User();
		user.setId(rs.getInt("id_pk"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFname(rs.getString("fname"));
		user.setLname(rs.getString("lname"));
		user.setMname(rs.getString("mname"));
		user.setDob(rs.getDate("dob"));
		user.setEmail(rs.getString("email"));
		user.setPhone_home(rs.getString("phone_home"));
		user.setPhone_cell(rs.getString("phone_cell"));
		user.setAddress1(rs.getString("address1"));
		user.setAddress2(rs.getString("address2"));
		user.setCity(rs.getString("city"));
		user.setState(rs.getString("state"));
		user.setCountry(rs.getString("country"));
		user.setPwd_hint(rs.getString("pwd_hint"));
		user.setPwd_hint_ans(rs.getString("pwd_hint_ans"));
		user.setActive(rs.getInt("active"));
		user.setRegistered_on(rs.getDate("registered_on"));
		user.setRole_id(rs.getInt("role_id"));
		user.setZip(rs.getInt("zip"));
		return user;
	}

}
