package com.capstone.eLearning.services;

import java.util.List;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.exception.ServiceException;

public interface CourseSearchService {
	List<Course> findCoursesByProgramName(String programName) throws ServiceException;
	List<Course> findCoursesByDeptName(String deptName) throws ServiceException;
	List<Course> findCoursesBySubjectName(String subjectName) throws ServiceException;
	List<Course> findCoursesByName(String someName) throws ServiceException;
}
