package com.capstone.eLearning.dao;

import java.util.List;

import javax.persistence.Entity;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.exception.DaoException;

@Entity
public interface CourseDao {
	/* Search Operations */
	List<Course> findCoursesByProgramName(String programName) throws DaoException;
	List<Course> findCoursesByDeptName(String deptName) throws DaoException;
	List<Course> findCoursesBySubjectName(String subjectName) throws DaoException;
	List<Course> findCourse(String someNameText) throws DaoException;
	List<Course> findAllCourses() throws DaoException;
	
	/* Create, retrieve, update, delete operations */
	void create(Course course) throws DaoException;
	Course retrieve(Long courseId) throws DaoException;
	void delete(Long courseId) throws DaoException;
	void update(Long courseId, String startd_date, String end_date, double credits, int instructor, int active) throws DaoException;
	public List<Course> getAll();

}
