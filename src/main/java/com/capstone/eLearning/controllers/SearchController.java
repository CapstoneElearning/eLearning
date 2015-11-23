package com.capstone.eLearning.controllers;

import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.services.CourseSearchService;

@Path("/search")
public class SearchController {
	private Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	@Qualifier("courseSearchServiceImpl")
	private CourseSearchService courseSearchService;

	/*
	 * Search for courses by program name
	 */
	@RequestMapping(value="/program/{search_term}", method=RequestMethod.GET)
	public @ResponseBody String searchByProgramName(
			@PathVariable(value="search_term") String search_term) {
		if (StringUtils.isEmpty(search_term)) {
			return "Search program name is empty!";
		}

		String unescapedText = StringEscapeUtils.unescapeJava(search_term);
		logger.info("Searching with program name: " + unescapedText);
		StringBuffer result = new StringBuffer("");
		List<Course> courses = courseSearchService.findCoursesByProgramName(unescapedText);

		if (courses != null) {
			for (Course course : courses) {
				result.append(course);
			}
		}

		if (StringUtils.isEmpty(result.toString())) {
			return "No result found!";
		}

		return result.toString();
	}

	/*
	 * Search for courses by dept name
	 */
	@RequestMapping(value="/department/{search_term}", method=RequestMethod.GET)
	public @ResponseBody String searchByDepartmentName(
			@PathVariable(value="search_term") String search_term) {
		if (StringUtils.isEmpty(search_term)) {
			return "Search department name is empty!";
		}

		String unescapedText = StringEscapeUtils.unescapeJava(search_term);
		logger.info("Searching with department name: " + unescapedText);
		StringBuffer result = new StringBuffer("");
		List<Course> courses = courseSearchService.findCoursesByDeptName(unescapedText);

		if (courses != null) {
			for (Course course : courses) {
				result.append(course);
			}
		}

		if (StringUtils.isEmpty(result.toString())) {
			return "No result found!";
		}

		return result.toString();
	}

	/*
	 * Search for courses by subject name
	 */
	@RequestMapping(value="/subject/{search_term}", method=RequestMethod.GET)
	public @ResponseBody String searchBySubjectName(
			@PathVariable("search_term") String search_term) {
		if (StringUtils.isEmpty(search_term)) {
			return "Search subject name is empty!";
		}

		String unescapedText = StringEscapeUtils.unescapeJava(search_term);
		logger.info("Searching with subject name: " + unescapedText);
		StringBuffer result = new StringBuffer("");
		List<Course> courses = courseSearchService.findCoursesBySubjectName(unescapedText);

		if (courses != null) {
			for (Course course : courses) {
				result.append(course);
			}
		}

		if (StringUtils.isEmpty(result.toString())) {
			return "No result found!";
		}

		return result.toString();
	}
}
