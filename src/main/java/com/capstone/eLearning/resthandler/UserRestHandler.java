package com.capstone.eLearning.resthandler;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.UnknownResourceException;
import com.capstone.eLearning.services.UserService;




@Path("/")
public class UserRestHandler {
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger(UserRestHandler.class);
	
	@POST
	@Path("/user")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addUser(User user) {
		ResponseBuilder respBuilder;
		int rowsInserted = 0;
		try { 
			rowsInserted = userService.saveUser(user);
		} catch (Exception ex) {
			logger.info("Exception    "+ex.getMessage());
		}
		
		if(rowsInserted >0){
			logger.debug("Successfully created a new User: " + user);
			respBuilder = Response.status(Status.CREATED);
			respBuilder.entity(user);
			return respBuilder.build();
		}else{
			respBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
			respBuilder.entity(user);
			return respBuilder.build();
		}
	}
	
	@GET
	@Path("/user/{username}")
	@Produces("application/xml, application/json")

	public User getUser(@PathParam("username") String username){
		User user1 = new User();
		User user2 = null;
		  user1.setUsername(username);
		try {
			 user2 = userService.getUserByName(username);
		} catch (Exception ex) {
			logger.info("Exception::::::"+ex.getMessage());
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);

		}
		
		if (user2 == null) {
			logger.info("Failed Request to lookup user with id  : " + user2);
			throw new UnknownResourceException("User id: " + user2 + " is invalid");
		}
		
		return user2;
	}
	
	@POST
	@Path("/userlogin")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public User userLogin(User user1){
		User user2 = null;
		try {
			 user2 = userService.login(user1);
		} catch (Exception ex) {
			logger.info("Exception::::::"+ex.getMessage());
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);

		}
		
		return user2;
	}
	


	
}



