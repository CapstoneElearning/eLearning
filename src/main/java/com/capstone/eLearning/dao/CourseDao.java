package com.capstone.eLearning.dao;

import java.util.List;

import com.capstone.eLearning.domain.Course;

public interface CourseDao {
	List<Course> findCoursesBySubjectName(String subjectName);
}
