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

import com.capstone.eLearning.domain.Assignment;
import com.capstone.eLearning.exception.ServiceException;
import com.capstone.eLearning.services.AssignmentService;
import com.google.gson.Gson;

/*
 * Controller that can handle Create/Retrieve/Delete operations for Assignment
 */
@RestController
@RequestMapping("/assignment")
public class AssignmentController {
	@Autowired
	@Qualifier("assignmentServiceImpl")
	private AssignmentService assignmentService;

	private Gson gson = new Gson();
	
	/*
	 * Create assignment from input Json String (POST json representation of assignment obj to be created)
	 * 
	 * URL Format:
	 * http://localhost:8080/assignment/create (you should HTTP POST a json string representing an assignment)
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Response create(
			@RequestBody String assignmentJsonString) throws ServiceException {
		if (StringUtils.isEmpty(assignmentJsonString)) {
			throw new ServiceException("JSON input string cannot be empty!");
		}

		try {
			Assignment assignment = gson.fromJson(assignmentJsonString, Assignment.class);
			assignmentService.create(assignment);
			return Response.ok("Create assignment succeeded").build();
		}
		catch (Exception e) {
			throw new ServiceException("Create assignment failed", e);
		}
	}

	/*
	 * Delete assignment by Id
	 * 
	 * URL Format:
	 * http://localhost:8080/assignment/delete/<<assignment_id_goes_here>>
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(
			@PathVariable(value="id") Long id) throws ServiceException {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException("id is empty!");
		}

		try {
			assignmentService.delete(id);
			return "Delete assignment succeeded";
		}
		catch (Exception e) {
			throw new ServiceException("Delete assignment failed", e);
		} 
	}

	/*
	 * Retrieve assignment by Id
	 * 
	 * Returns Json representation of the assignment obj
	 * 
	 * URL Format (HTTP GET):
	 * http://localhost:8080/assignment/retrieve/<<assignment_id_goes_here>>
	 */
	@RequestMapping(value="/retrieve/{id}", method=RequestMethod.GET)
	public String retrieve(
			@PathVariable("id") Long id) throws ServiceException {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException("id is empty!");
		}

		Assignment assignment = assignmentService.retrieve(id);
		
		if (assignment != null) {
			return gson.toJson(assignment);
		}
		
		return null;
	}
	
	/*
	 * Update assignment
	 * 
	 * URL should be HTTP GET of the form:
	 * http://localhost:8080/assignment/update/<<assignment_id_goes_here>>?due_date=yyyy-mm-dd&
	 * allow_late_submission=1&assignment_type=1&total_marks=100&secured_marks=75
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, 
			@QueryParam("due_date") String due_date, @QueryParam("allow_late_submit") int allow_late_submit, 
			@QueryParam("assignment_type") int assignment_type, @QueryParam("total_marks") double total_marks,
			@QueryParam("secured_marks") double secured_marks) throws ServiceException {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException("id is empty!");
		}

		try {
			assignmentService.update(id, due_date, allow_late_submit, assignment_type, total_marks, secured_marks);
	
			return "Update assignment succeeded";
		}
		catch (Exception e) {
			throw new ServiceException("Update assignment failed", e);
		}
	}
}
