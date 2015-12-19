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
import com.capstone.eLearning.services.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	@Autowired
	@Qualifier("courseDaoJdbcImpl")
	private CourseDao courseDao;

	@Override
	public void create(Course course) throws ServiceException {
		try {
			courseDao.create(course);
		}
		catch (DaoException e) {
			throw new ServiceException("Create course failed", e);
		}
	}

	@Override
	public Course retrieve(Long courseId) throws ServiceException {
		try {
			return courseDao.retrieve(courseId);
		}
		catch (DaoException e) {
			throw new ServiceException("Retrieve course failed", e);
		}
	}

	@Override
	public void delete(Long courseId) throws ServiceException {
		try {
			courseDao.delete(courseId);
		}
		catch (DaoException e) {
			throw new ServiceException("Delete course failed", e);
		}
	}

	@Override
	public void update(Long courseId, String startd_date, String end_date,
			double credits, int instructor, int active) throws ServiceException {
		try {
			courseDao.update(courseId, startd_date, end_date, credits, instructor, active);
		}
		catch (DaoException e) {
			throw new ServiceException("Update course failed", e);
		}
	}
	
	
	@Override
	public List<Course> getAll() {
		List<Course> courses=courseDao.findAllCourses();
		return courses;
	}

	@Override
	public void enroll(String enroll_date, String completion_date, int active,
			int student_id, int course_id, String course_enrollmentcol)
			throws Exception {
		try {
			courseDao.enroll(enroll_date, completion_date, active, student_id, course_id, course_enrollmentcol);
		}
		catch (DaoException e) {
			throw new ServiceException("Update course failed", e);
		}
	}
}
