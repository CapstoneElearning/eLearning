package com.capstone.eLearning.controllers;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.services.CourseService;
import com.google.gson.Gson;

/*
 * Controller that can handle Create/Retrieve/Delete operations for Course
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	private Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	@Qualifier("courseServiceImpl")
	private CourseService courseService;

	private Gson gson = new Gson();

	
	/*
	 * Create course from input Json String (POST json representation of course
	 * obj to be created)
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody void create(@RequestBody String courseJsonString) throws Exception {
		if (courseJsonString == null) {
			logger.error("Course JSON input string cannot be null!");
			return;
		}

		try {
			Course course = gson.fromJson(courseJsonString, Course.class);
			courseService.create(course);
		} catch (Exception e) {
			logger.error("Error creating course: ", e);
			throw e;
		}
	}

	/*
	 * Delete course by Id
	 */
	@RequestMapping(value="/delete/{course_id}", method=RequestMethod.DELETE)
	public @ResponseBody void delete(@PathParam(value = "course_id") Long course_id) throws Exception {
		if (StringUtils.isEmpty(course_id)) {
			logger.error("course_id is empty!");
			return;
		}

		try {
			courseService.delete(course_id);
		} catch (Exception e) {
			logger.error("Error deleting course: ", e);
			throw e;
		}
	}

	/*
	 * Retrieve course by Id
	 * 
	 * Returns Json representation of the course obj
	 */
	@RequestMapping(value="/retrieve/{course_id}", method=RequestMethod.GET)
	public Course retrieve(@PathParam("course_id") Long course_id) {
		Course course = null;
		if (!StringUtils.isEmpty(course_id)) {
			course = courseService.retrieve(course_id);
		}	
		return course;
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Course> getAll() {
		List<Course> courses = null;
		try {
			courses = courseService.getAll();
		} catch (Exception ex) {
			logger.info("Exception::::::" + ex.getMessage());
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);

		}

		return courses;
	}
	
	@RequestMapping(value="/enroll", method=RequestMethod.GET)
	public @ResponseBody void create(@QueryParam("enroll_date") String enroll_date,
			@QueryParam("completion_date") String completion_date, 
			@QueryParam("assignment_type") int active,
			@QueryParam("student_id") int student_id,
			@QueryParam("course_id") int course_id,
			@QueryParam("course_enrollmentcol") String course_enrollmentcol) throws Exception {

		try {
			courseService.enroll(enroll_date, completion_date, active, student_id, course_id, course_enrollmentcol);			
		} catch (Exception e) {
			logger.error("Error enrolling course: ", e);
			throw e;
		}
	}
}
