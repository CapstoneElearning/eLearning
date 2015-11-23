package com.capstone.eLearning.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.services.CourseService;
import com.google.gson.Gson;

/*
 * Controller that can handle Create/Retrieve/Delete operations for Course
 */
@Path("/")
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
	@POST
	@Path("/course")
	@Produces("application/xml,application/json")
	@Consumes("application/xml,application/json")
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
	@Path("/course/{course_id}")
	@DELETE
	@Produces("application/xml,application/json")
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
	@GET
	@Path("/course/{course_id}")
	@Produces("application/xml, application/json")
	public Course retrieve(@PathParam("course_id") Long course_id) {
		Course course = null;
		if (!StringUtils.isEmpty(course_id)) {
			course = courseService.retrieve(course_id);
		}	
		return course;
	}

	@GET
	@Path("/course/list")
	@Produces("application/json, application/xml")
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
}
