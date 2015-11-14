package com.capstone.eLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.eLearning.dao.CourseDao;
import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.exception.DaoException;
import com.capstone.eLearning.exception.ServiceException;
import com.capstone.eLearning.services.CourseSearchService;

@Service
@Transactional
public class CourseSearchServiceImpl implements CourseSearchService {
	@Autowired
	@Qualifier("courseDaoJdbcImpl")
	private CourseDao courseDao;
	
	@Override
	public List<Course> findCoursesByProgramName(String programName) throws ServiceException {
		try {
			return courseDao.findCoursesByProgramName(programName);
		}
		catch (DaoException e) {
			throw new ServiceException("Error finding courses by program name", e);
		}
	}

	@Override
	public List<Course> findCoursesByDeptName(String deptName) throws ServiceException {
		try {
			return courseDao.findCoursesByDeptName(deptName);
		}
		catch (DaoException e) {
			throw new ServiceException("Error finding courses by dept name", e);
		}
	}

	@Override
	public List<Course> findCoursesBySubjectName(String subjectName) throws ServiceException {
		try {
			return courseDao.findCoursesBySubjectName(subjectName);
		}
		catch (DaoException e) {
			throw new ServiceException("Error finding courses by subject name", e);
		}
	}

	@Override
	public List<Course> findCoursesByName(String someName) throws ServiceException {
		try {
			return courseDao.findCourse(someName);
		}
		catch (DaoException e) {
			throw new ServiceException("Error finding courses by name", e);
		}
	}
}
