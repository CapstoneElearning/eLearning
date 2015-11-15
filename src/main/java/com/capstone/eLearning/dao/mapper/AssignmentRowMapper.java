package com.capstone.eLearning.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.capstone.eLearning.domain.Assignment;

public class AssignmentRowMapper implements RowMapper<Assignment> {

	@Override
	public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Assignment assignment = new Assignment();
		assignment.setId(rs.getInt("id_pk"));
		assignment.setDesc(rs.getString("desc"));
		assignment.setDue_date(rs.getDate("due_date"));
		assignment.setAllow_late_submit(rs.getInt("allow_late_submit"));
		assignment.setCourse_id(rs.getInt("course_id"));
		assignment.setStudent_id(rs.getInt("student_id"));
		assignment.setAssignment_type(rs.getInt("assignment_type"));
		assignment.setTotal_marks(rs.getDouble("total_marks"));
		assignment.setSecured_marks(rs.getDouble("secured-marks"));
		assignment.setDocument_id(rs.getInt("document_id"));
	    return assignment;
	}

}
