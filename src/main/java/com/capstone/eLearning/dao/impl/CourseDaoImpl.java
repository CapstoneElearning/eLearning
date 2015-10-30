package com.capstone.eLearning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capstone.eLearning.dao.CourseDao;
import com.capstone.eLearning.dao.mapper.DepartmentRowMapper;
import com.capstone.eLearning.dao.mapper.SubjectRowMapper;
import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.domain.Department;
import com.capstone.eLearning.domain.Subject;

@Repository("courseDaoJdbcImpl")
public class CourseDaoImpl implements CourseDao {
	private Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate dbTemplate;	
	
	@PostConstruct
	public void setup() {
		dbTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Course> findCoursesBySubjectName(String subjectName) {
		String sql = "SELECT * FROM course WHERE subject = "
				+ "(select subj_id from subject where subject_name = \"" + subjectName + "\");";
		
		logger.info(":::: SQL Constructed ::::\n{}", sql);

		List<Map<String, Object>> rows = dbTemplate.queryForList(sql);
		List<Course> courseList = new ArrayList<Course>();
		
        for (Map<String, Object> row : rows) {
        	Course course = new Course();
        	course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
        	course.setDescription(String.valueOf(row.get("description")));
        	course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
        	course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
        	course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
        	course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
        	courseList.add(course);
        }
		return courseList;
	}

	@Override
	public List<Course> findCoursesByProgramName(String programName) {
		String sql = "SELECT * FROM course WHERE dept = "
				+ "(select id_pk from program where name = \"" + programName + "\");";
		
		logger.info(":::: SQL Constructed ::::\n{}", sql);
		
		List<Map<String, Object>> rows = dbTemplate.queryForList(sql);
		List<Course> courseList = new ArrayList<Course>();
		
        for (Map<String, Object> row : rows) {
        	Course course = new Course();
        	course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
        	course.setDescription(String.valueOf(row.get("description")));
        	course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
        	course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
        	course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
        	course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
        	courseList.add(course);
        }
		return courseList;
	}

	@Override
	public List<Course> findCoursesByDeptName(String deptName) {
		String sql = "SELECT * FROM course WHERE dept = "
				+ "(select dept_id from department where name = \"" + deptName + "\");";
		
		logger.info(":::: SQL Constructed ::::\n{}", sql);

		List<Map<String, Object>> rows = dbTemplate.queryForList(sql);
		List<Course> courseList = new ArrayList<Course>();
		
        for (Map<String, Object> row : rows) {
        	Course course = new Course();
        	course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
        	course.setDescription(String.valueOf(row.get("description")));
        	course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
        	course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
        	course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
        	course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
        	courseList.add(course);
        }
		return courseList;
	}

	public Subject findSubjectById(String subjectId) {
		String sql = "SELECT * FROM subject WHERE subj_id = ?";
		return dbTemplate.queryForObject(sql, new Object[] { subjectId } , new SubjectRowMapper());
	}

	public Department findDeptById(String deptId) {
		String sql = "SELECT * FROM department WHERE dept_id = ?";
		return dbTemplate.queryForObject(sql, new Object[] { deptId } , new DepartmentRowMapper());
	}
}
