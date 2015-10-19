package com.capstone.eLearning.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capstone.eLearning.domain.User;
@Repository("userPOJO")
public class UserPOJO implements RowMapper {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id_pk"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFname(rs.getString("fname"));
        user.setMname(rs.getString("mname"));
        user.setAddress1(rs.getString("address1"));
        user.setAddress2(rs.getString("address2"));
        user.setCity(rs.getString("city"));
        user.setCountry(rs.getString("country"));
        user.setDob(rs.getDate("dob"));
        user.setEmail(rs.getString("email"));
        user.setActive(rs.getBoolean("active"));
        user.setPhone_cell(rs.getString("phone_cell"));
        user.setPhone_home(rs.getString("phone_home"));
        user.setPwd_hint_ans(rs.getString("pwd_hint_ans"));
        user.setPwd_hint(rs.getString("pwd_hint"));
        user.setRegistered_on(rs.getDate("registered_on"));
        user.setRole_id(rs.getInt("role_id"));
        user.setZip(rs.getInt("zip"));
        user.setState(rs.getString("state"));
        
		return user;
		
		
	}

}
