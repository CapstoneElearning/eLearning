package com.capstone.eLearning.services;

import java.util.List;

import com.capstone.eLearning.domain.Course;

public interface CourseSearchService {
	List<Course> findCoursesByProgramName(String programName);
	List<Course> findCoursesByDeptName(String deptName);
	List<Course> findCoursesBySubjectName(String subjectName);
}
