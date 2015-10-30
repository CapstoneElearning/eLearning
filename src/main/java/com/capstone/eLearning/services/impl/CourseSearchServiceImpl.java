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
	public List<Course> findCoursesByProgramName(String programName) {
		return courseDao.findCoursesByProgramName(programName);
	}

	@Override
	public List<Course> findCoursesByDeptName(String deptName) {
		return courseDao.findCoursesByDeptName(deptName);
	}

	@Override
	public List<Course> findCoursesBySubjectName(String subjectName) {
		return courseDao.findCoursesBySubjectName(subjectName);
	}
}
