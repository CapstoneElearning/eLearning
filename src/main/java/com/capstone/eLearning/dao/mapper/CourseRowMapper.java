package com.capstone.eLearning.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.capstone.eLearning.dao.UserDao;
import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.domain.Department;
import com.capstone.eLearning.domain.Program;
import com.capstone.eLearning.domain.Subject;

public class CourseRowMapper implements RowMapper<Course> {

	@Autowired
	private UserDao userDao;
	
	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		course.setId(rs.getInt("id_pk"));
		course.setActive(rs.getInt("active") == 1);
		course.setCredits(rs.getDouble("credits"));
		course.setDescription(rs.getString("description"));
		course.setEnd_date(rs.getDate("end_date"));
		course.setRoom(rs.getInt("room"));
		course.setSchedule_day(rs.getString("schedule_day"));
		java.util.Date date = null;
		Timestamp timestamp = rs.getTimestamp("schedule_time");
		if (timestamp != null) {
		    date = new java.util.Date(timestamp.getTime());
		}
		course.setSchedule_time(date);
		course.setStart_date(rs.getDate("start_date"));
		course.setInstructor(userDao.findById(rs.getInt("instructor")));
		int pid = rs.getInt("program");
		int sid = rs.getInt("subject");
		int did = rs.getInt("department");
		Program program = new Program();
		program.setId(pid);
		Subject subject = new Subject();
		program.setId(sid);
		Department department = new Department();
		department.setDeptId(did);
		course.setProgram(program);
		course.setSubject(subject);
		course.setDepartment(department);
		return course;
	}

}
