package com.capstone.eLearning.dao;

import java.util.List;

import javax.persistence.Entity;

import com.capstone.eLearning.domain.Course;

@Entity
public interface CourseDao {
	/* Search Operations */
	List<Course> findCoursesByProgramName(String programName);
	List<Course> findCoursesByDeptName(String deptName);
	List<Course> findCoursesBySubjectName(String subjectName);
	
	/* Create, retrieve, delete operations */
	void create(Course course);
	Course retrieve(Long courseId);
	void delete(Long courseId);
}
