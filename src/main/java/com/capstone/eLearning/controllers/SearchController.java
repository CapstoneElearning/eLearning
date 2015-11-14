package com.capstone.eLearning.controllers;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.eLearning.domain.Course;
import com.capstone.eLearning.exception.ServiceException;
import com.capstone.eLearning.services.CourseSearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	private Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	@Qualifier("courseSearchServiceImpl")
	private CourseSearchService courseSearchService;

	/*
	 * Search for courses by program name
	 * 
	 * URL Format:
	 * http://localhost:8080/search/program/<<search_term_goes_here>>
	 */
	@RequestMapping(value="/program/{search_term}", method=RequestMethod.GET)
	public String searchByProgramName(
			@PathVariable(value="search_term") String search_term) throws ServiceException {
		if (StringUtils.isEmpty(search_term)) {
			throw new ServiceException("Search program name is empty!");
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
	 * 
	 * URL Format:
	 * http://localhost:8080/search/department/<<search_term_goes_here>>
	 */
	@RequestMapping(value="/department/{search_term}", method=RequestMethod.GET)
	public String searchByDepartmentName(
			@PathVariable(value="search_term") String search_term) throws ServiceException {
		if (StringUtils.isEmpty(search_term)) {
			throw new ServiceException("Search dept name is empty!");
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
	 * 
	 * URL Format:
	 * http://localhost:8080/search/subject/<<search_term_goes_here>>
	 */
	@RequestMapping(value="/subject/{search_term}", method=RequestMethod.GET)
	public String searchBySubjectName(
			@PathVariable("search_term") String search_term) throws ServiceException {
		if (StringUtils.isEmpty(search_term)) {
			throw new ServiceException("Search subject name is empty!");
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
