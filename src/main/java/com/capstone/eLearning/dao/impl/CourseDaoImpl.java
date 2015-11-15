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
import com.capstone.eLearning.dao.mapper.CourseRowMapper;
import com.capstone.eLearning.dao.mapper.DepartmentRowMapper;
import com.capstone.eLearning.dao.mapper.SubjectRowMapper;
import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.domain.Department;
import com.capstone.eLearning.domain.Subject;
import com.capstone.eLearning.exception.DaoException;

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
	public List<Course> findCoursesBySubjectName(String subjectName) throws DaoException {
		List<Course> courseList = new ArrayList<Course>();

		String sql = "SELECT * FROM course WHERE subject = "
				+ "(select subj_id from subject where subject_name = \"" + subjectName + "\");";

		logger.info(":::: SQL Constructed ::::\n{}", sql);

		try {
			List<Map<String, Object>> rows = dbTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) {
				Course course = new Course();
				course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
				course.setDescription(String.valueOf(row.get("description")));
				course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
				course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
				course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
				course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
				course.setInstructor(Integer.parseInt(String.valueOf(row.get("instructor"))));
				course.setRoom(Integer.parseInt(String.valueOf(row.get("room"))));
				course.setSchedule_day(String.valueOf(row.get("schedule_day")));
				course.setStartd_date(new java.util.Date(String.valueOf(row.get("startd_date"))));
				course.setEnd_date(new java.util.Date(String.valueOf(row.get("end_date"))));
				courseList.add(course);
			}
		}
		catch (Exception e) {
			throw new DaoException(e);
		}

		return courseList;
	}

	@Override
	public List<Course> findCoursesByProgramName(String programName) throws DaoException {
		List<Course> courseList = new ArrayList<Course>();

		String sql = "SELECT * FROM course WHERE program = "
				+ "(select id_pk from program where name = \"" + programName + "\");";

		logger.info(":::: SQL Constructed ::::\n{}", sql);

		try {
			List<Map<String, Object>> rows = dbTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) {
				Course course = new Course();
				course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
				course.setDescription(String.valueOf(row.get("description")));
				course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
				course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
				course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
				course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
				course.setInstructor(Integer.parseInt(String.valueOf(row.get("instructor"))));
				course.setRoom(Integer.parseInt(String.valueOf(row.get("room"))));
				course.setSchedule_day(String.valueOf(row.get("schedule_day")));
				course.setStartd_date(new java.util.Date(String.valueOf(row.get("startd_date"))));
				course.setEnd_date(new java.util.Date(String.valueOf(row.get("end_date"))));
				courseList.add(course);
			}
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		return courseList;
	}

	@Override
	public List<Course> findCoursesByDeptName(String deptName) throws DaoException {
		List<Course> courseList = new ArrayList<Course>();

		String sql = "SELECT * FROM course WHERE dept = "
				+ "(select dept_id from department where name = \"" + deptName + "\");";

		logger.info(":::: SQL Constructed ::::\n{}", sql);

		try {
			List<Map<String, Object>> rows = dbTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) {
				Course course = new Course();
				course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
				course.setDescription(String.valueOf(row.get("description")));
				course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
				course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
				course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
				course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
				course.setInstructor(Integer.parseInt(String.valueOf(row.get("instructor"))));
				course.setRoom(Integer.parseInt(String.valueOf(row.get("room"))));
				course.setSchedule_day(String.valueOf(row.get("schedule_day")));
				course.setStartd_date(new java.util.Date(String.valueOf(row.get("startd_date"))));
				course.setEnd_date(new java.util.Date(String.valueOf(row.get("end_date"))));
				courseList.add(course);
			}
		}
		catch (Exception e) {
			throw new DaoException(e);
		}

		return courseList;
	}

	public Subject findSubjectById(String subjectId) throws DaoException {
		String sql = "SELECT * FROM subject WHERE subj_id = ?";
		try {
			return dbTemplate.queryForObject(sql, new Object[] { subjectId } , new SubjectRowMapper());
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Department findDeptById(String deptId) throws DaoException {
		String sql = "SELECT * FROM department WHERE dept_id = ?";
		try {
			return dbTemplate.queryForObject(sql, new Object[] { deptId } , new DepartmentRowMapper());
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void create(Course course) throws DaoException {
		String sql = "INSERT INTO course " +
				"(description, schedule_day, schedule_time,"
				+ "startd_date, end_date, credits,"
				+ "subject, dept, program, "
				+ "instructor, room, active) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			dbTemplate.update(sql, new Object[] { 
					course.getDescription(), course.getSchedule_day(), course.getSchedule_time(), 
					course.getStartd_date(), course.getEnd_date(), course.getCredits(), 
					course.getSubject().getSubjectId(), course.getDepartment().getDeptId(), course.getProgram().getId(), 
					course.getInstructor(), course.getRoom(), course.isActive() });  
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Course retrieve(Long courseId) throws DaoException {
		String sql = "SELECT * FROM course WHERE id_pk = ?";
		try {
			return dbTemplate.queryForObject(sql, new Object[] { courseId } , new CourseRowMapper());
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Long courseId) throws DaoException {
		String sql = "DELETE FROM course WHERE id_pk = ?";
		try {
			dbTemplate.update(sql, new Object[] { courseId });
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Long courseId, String startd_date, String end_date, double credits, int instructor, int active) throws DaoException {
		String sql = "UPDATE course SET startd_date=?, end_date=?, credits=?, instructor=?, active=? WHERE id_pk = ?";
		try {
			dbTemplate.update(sql, new Object[] { startd_date, end_date, credits, instructor, active, courseId});
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Course> findCourse(String someNameText) throws DaoException {
		/* name could be deptName (or) progName (or) SubjName */
		List<Course> results = new ArrayList<Course>();
		results.addAll(findCoursesByDeptName(someNameText));
		results.addAll(findCoursesByProgramName(someNameText));
		results.addAll(findCoursesBySubjectName(someNameText));
		return results;
	}

	@Override
	public List<Course> findAllCourses() throws DaoException {
		String sql = "select * from course";
		 
		List<Course> courseList = new ArrayList<Course>();
		
		try {
			List<Map<String, Object>> rows = dbTemplate.queryForList(sql);

			for (Map<String, Object> row : rows) {
				Course course = new Course();
				course.setId(Integer.parseInt(String.valueOf(row.get("id_pk"))));
				course.setDescription(String.valueOf(row.get("description")));
				course.setSubject(findSubjectById(String.valueOf(row.get("subject"))));
				course.setDepartment(findDeptById(String.valueOf(row.get("dept"))));
				course.setCredits(Double.parseDouble(String.valueOf(row.get("credits"))));
				course.setInstructor(Integer.parseInt(String.valueOf(row.get("instructor"))));
				course.setRoom(Integer.parseInt(String.valueOf(row.get("room"))));
				course.setSchedule_day(String.valueOf(row.get("schedule_day")));
				course.setActive(Boolean.valueOf(String.valueOf(row.get("active"))));
				course.setStartd_date(new java.util.Date(String.valueOf(row.get("startd_date"))));
				course.setEnd_date(new java.util.Date(String.valueOf(row.get("end_date"))));
				courseList.add(course);
			}
			
			return courseList;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
