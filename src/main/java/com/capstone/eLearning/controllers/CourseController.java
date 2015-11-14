package com.capstone.eLearning.controllers;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.exception.ServiceException;
import com.capstone.eLearning.services.CourseService;
import com.google.gson.Gson;

/*
 * Controller that can handle Create/Retrieve/Delete operations for Course
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	@Qualifier("courseServiceImpl")
	private CourseService courseService;

	private Gson gson = new Gson();

	/*
	 * Create course from input Json String (POST json representation of course obj to be created)
	 * 
	 * URL Format:
	 * http://localhost:8080/course/create (you should HTTP POST a json string representing a course)
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Response create(
			@RequestBody String courseJsonString) throws ServiceException {
		if (StringUtils.isEmpty(courseJsonString)) {
			throw new ServiceException("Course JSON input string cannot be empty!");
		}

		try {
			Course course = gson.fromJson(courseJsonString, Course.class);
			courseService.create(course);
			return Response.ok("Create succeeded").build();
		}
		catch (Exception e) {
			throw new ServiceException("Create failed", e);
		}
	}

	/*
	 * Delete course by Id
	 * 
	 * URL Format:
	 * http://localhost:8080/course/delete/<<course_id_goes_here>>
	 */
	@RequestMapping(value="/delete/{course_id}", method=RequestMethod.GET)
	public String delete(
			@PathVariable(value="course_id") Long course_id) throws ServiceException {
		if (StringUtils.isEmpty(course_id)) {
			throw new ServiceException("course_id is empty!");
		}

		try {
			courseService.delete(course_id);
			return "Delete succeeded";
		}
		catch (Exception e) {
			throw new ServiceException("Delete failed", e);
		}
	}

	/*
	 * Retrieve course by Id
	 * 
	 * Returns Json representation of the course obj
	 * 
	 * URL Format (HTTP GET):
	 * http://localhost:8080/course/retrieve/<<course_id_goes_here>>
	 */
	@RequestMapping(value="/retrieve/{course_id}", method=RequestMethod.GET)
	public String retrieve(
			@PathVariable("course_id") Long course_id) throws ServiceException {
		if (StringUtils.isEmpty(course_id)) {
			throw new ServiceException("course_id is empty!");
		}

		Course course = courseService.retrieve(course_id);
		
		if (course != null) {
			return gson.toJson(course);
		}
		
		return null;
	}
	
	/*
	 * Update course
	 * 
	 * URL should be HTTP GET of the form:
	 * http://localhost:8080/course/update/<<course_id_goes_here>>?startd_date=yyyy-mm-dd&end_date=yyyy-mm-dd&credits=10&active=1
	 */
	@RequestMapping(value="/update/{course_id}", method=RequestMethod.GET)
	public String update(@PathVariable("course_id") Long course_id, @QueryParam("startd_date") String startd_date, @QueryParam("end_date") String end_date, 
			@QueryParam("credits") double credits, @QueryParam("active") int active) throws ServiceException {
		if (StringUtils.isEmpty(course_id)) {
			throw new ServiceException("course_id is empty!");
		}

		try {
			courseService.update(course_id, startd_date, end_date, credits, active);
			return "Update succeeded";
		}
		catch (Exception e) {
			throw new ServiceException("Update failed", e);
		}
	}
}
