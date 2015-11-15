package com.capstone.eLearning.dao;

import javax.persistence.Entity;

import com.capstone.eLearning.domain.Assignment;
import com.capstone.eLearning.exception.DaoException;

@Entity
public interface AssignmentDao {
	/* Create, retrieve, update, delete operations */
	void create(Assignment assignment) throws DaoException;
	Assignment retrieve(Long id) throws DaoException;
	void delete(Long id) throws DaoException;
	void update(Long id, String due_date, int allow_late_submit, int assignment_type, double total_marks, double secured_marks) throws DaoException;
}
