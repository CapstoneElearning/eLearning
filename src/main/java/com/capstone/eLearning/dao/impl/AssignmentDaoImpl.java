package com.capstone.eLearning.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capstone.eLearning.dao.AssignmentDao;
import com.capstone.eLearning.dao.mapper.AssignmentRowMapper;
import com.capstone.eLearning.domain.Assignment;
import com.capstone.eLearning.exception.DaoException;

@Repository("assignmentDaoJdbcImpl")
public class AssignmentDaoImpl implements AssignmentDao {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate dbTemplate;	
	
	@PostConstruct
	public void setup() {
		dbTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Assignment assignment) throws DaoException {
		String sql = "INSERT INTO course_assignments " +
				"(desc, due_date, allow_late_submit, course_id, student_id, assignment_type, total_marks, secured-marks, document_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			dbTemplate.update(sql, new Object[] {
					assignment.getDesc(), assignment.getDue_date(), assignment.getAllow_late_submit(),
					assignment.getCourse_id(), assignment.getStudent_id(), assignment.getAssignment_type(),
					assignment.getTotal_marks(), assignment.getSecured_marks(), assignment.getDocument_id() });
		}
		catch (Exception e) {
			throw new DaoException(e);
		}		
	}

	@Override
	public Assignment retrieve(Long id) throws DaoException {
		String sql = "SELECT * FROM course_assignments WHERE id_pk = ?";
		try {
			return dbTemplate.queryForObject(sql, new Object[] { id } , new AssignmentRowMapper());
		}
		catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM course_assignments WHERE id_pk = ?";
		try {
			dbTemplate.update(sql, new Object[] { id });
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Long id, String due_date, int allow_late_submit,
			int assignment_type, double total_marks, double secured_marks)
			throws DaoException {
		String sql = "UPDATE course_assignments SET due_date=?, allow_late_submit=?, assignment_type=?, total_marks=?, secured_marks=? WHERE id_pk = ?";
		try {
			dbTemplate.update(sql, new Object[] { due_date, allow_late_submit, assignment_type, total_marks, secured_marks, id});
		}
		catch (Exception e) {
			throw new DaoException(e);
		}		
	}

}
