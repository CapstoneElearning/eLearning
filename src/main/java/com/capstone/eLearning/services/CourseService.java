package com.capstone.eLearning.services;

import java.util.List;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.exception.ServiceException;

public interface CourseService {
	void create(Course course) throws ServiceException;
	Course retrieve(Long courseId) throws ServiceException;
	void delete(Long courseId) throws ServiceException;
	void update(Long courseId, String startd_date, String end_date, double credits, int instructor, int active) throws ServiceException;
	public List<Course> getAll();
	public void enroll(String enroll_date, 
			           String completion_date, 
					   int active, int student_id, 
					   int course_id, 
					   String course_enrollmentcol) throws Exception;
}
