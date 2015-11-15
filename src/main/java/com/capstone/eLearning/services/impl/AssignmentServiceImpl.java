package com.capstone.eLearning.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.eLearning.dao.AssignmentDao;
import com.capstone.eLearning.domain.Assignment;
import com.capstone.eLearning.exception.DaoException;
import com.capstone.eLearning.exception.ServiceException;
import com.capstone.eLearning.services.AssignmentService;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
	@Autowired
	@Qualifier("assignmentDaoJdbcImpl")
	private AssignmentDao assignmentDao;

	@Override
	public void create(Assignment assignment) throws ServiceException {
		try {
			assignmentDao.create(assignment);
		}
		catch (DaoException e) {
			throw new ServiceException("Create assignment failed", e);
		}
	}

	@Override
	public Assignment retrieve(Long id) throws ServiceException {
		try {
			return assignmentDao.retrieve(id);
		}
		catch (DaoException e) {
			throw new ServiceException("Retrieve assignment failed", e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			assignmentDao.delete(id);
		}
		catch (DaoException e) {
			throw new ServiceException("Delete assignment failed", e);
		}
	}

	@Override
	public void update(Long id, String due_date, int allow_late_submit,
			int assignment_type, double total_marks, double secured_marks)
			throws DaoException {
		try {
			assignmentDao.update(id, due_date, allow_late_submit, assignment_type, total_marks, secured_marks);
		}
		catch (DaoException e) {
			throw new ServiceException("Update assignment failed", e);
		}
	}

}
