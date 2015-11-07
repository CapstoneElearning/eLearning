package com.capstone.eLearning.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.eLearning.dao.CourseDao;
import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.services.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	@Autowired
	@Qualifier("courseDaoJdbcImpl")
	private CourseDao courseDao;

	@Override
	public void create(Course course) {
		courseDao.create(course);
	}

	@Override
	public Course retrieve(Long courseId) {
		return courseDao.retrieve(courseId);
	}

	@Override
	public void delete(Long courseId) {
		courseDao.delete(courseId);
	}
}