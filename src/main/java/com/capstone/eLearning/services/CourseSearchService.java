package com.capstone.eLearning.services;

import java.util.List;

import com.capstone.eLearning.domain.Course;

public interface CourseSearchService {
	List<Course> findCoursesByCourseName(String courseName);
	List<Course> findCoursesByDeptName(String deptName);
}
