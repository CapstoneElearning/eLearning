package com.capstone.eLearning.services;

import com.capstone.eLearning.domain.Assignment;
import com.capstone.eLearning.exception.DaoException;
import com.capstone.eLearning.exception.ServiceException;

public interface AssignmentService {
	void create(Assignment assignment) throws ServiceException;
	Assignment retrieve(Long id) throws ServiceException;
	void delete(Long id) throws ServiceException;
	void update(Long id, String due_date, int allow_late_submit, int assignment_type, double total_marks, double secured_marks) throws DaoException;
}
