package com.capstone.eLearning.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.services.CourseService;
import com.google.gson.Gson;

/*
 * Controller that can handle Create/Retrieve/Delete operations for Course
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	private Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	@Qualifier("courseServiceImpl")
	private CourseService courseService;

	private Gson gson = new Gson();

	/*
	 * Create course from input Json String (POST json representation of course obj to be created)
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody void create(
			@RequestBody String courseJsonString) throws Exception {
		if (courseJsonString == null) {
			logger.error("Course JSON input string cannot be null!");
			return;
		}

		try {
			Course course = gson.fromJson(courseJsonString, Course.class);
			courseService.create(course);
		}
		catch (Exception e) {
			logger.error("Error creating course: ", e);
			throw e;
		}
	}

	/*
	 * Delete course by Id
	 */
	@RequestMapping(value="/delete/{course_id}", method=RequestMethod.GET)
	public @ResponseBody void delete(
			@PathVariable(value="course_id") Long course_id) throws Exception {
		if (StringUtils.isEmpty(course_id)) {
			logger.error("course_id is empty!");
			return;
		}

		try {
			courseService.delete(course_id);
		}
		catch (Exception e) {
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
	public @ResponseBody String retrieve(
			@PathVariable("course_id") Long course_id) {
		if (StringUtils.isEmpty(course_id)) {
			return "course_id is empty!";
		}

		Course course = courseService.retrieve(course_id);
		
		if (course != null) {
			return gson.toJson(course);
		}
		
		return null;
	}
}
