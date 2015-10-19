package com.capstone.eLearning.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.capstone.eLearning.domain.Subject;


public class SubjectRowMapper implements RowMapper<Subject> {
	public Subject mapRow(ResultSet resultSet, int row) throws SQLException {
		Subject subject = new Subject();
		subject.setSubjectId(resultSet.getInt("subj_id"));
		subject.setSubjectName(resultSet.getString("subject_name"));
		subject.setSubjectDesc(resultSet.getString("subject_desc"));	
		return subject;
	}
}

