package com.capstone.eLearning.services;

import com.capstone.eLearning.domain.Course;

public interface CourseService {
	void create(Course course);
	Course retrieve(Long courseId);
	void delete(Long courseId);
}
