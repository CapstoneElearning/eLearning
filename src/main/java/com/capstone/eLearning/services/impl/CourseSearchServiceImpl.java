package com.capstone.eLearning.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.eLearning.dao.CourseDao;
import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.services.CourseSearchService;

@Service
@Transactional
public class CourseSearchServiceImpl implements CourseSearchService {
	@Autowired
	@Qualifier("courseDaoJdbcImpl")
	private CourseDao courseDao;
	
	@Override
	public List<Course> findCoursesByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findCoursesByDeptName(String deptName) {
		// TODO Auto-generated method stub
		return null;
	}

}
