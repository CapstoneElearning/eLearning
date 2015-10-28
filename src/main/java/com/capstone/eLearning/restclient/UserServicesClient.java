package com.capstone.eLearning.restclient;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.services.UserService;
import com.capstone.eLearning.services.UserServiceImpl;


public class UserServicesClient {
	static private Logger logger = Logger.getLogger(UserServicesClient.class);
	private static String USER_SERVICES_URL = "http://localhost:8080/eLearning/webservices/user/";
	private static String USER_LOOKUP_URL = USER_SERVICES_URL + "{id}";
	private static Client client=null;  
	
	public static void main(String args[]) {
		testPost();
	}
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
		}
		
		return client;
	}
	public static void testPost() {
		int responseCode;
		User user;
		Client client = getClient();
		
		user = createNewUser();
		
		WebTarget target = client.target(USER_SERVICES_URL);
		
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_XML_TYPE);
		Response response = request.post(Entity.xml(user));
		
		responseCode = response.getStatus();
		logger.info("The response code from Post operation is: " + responseCode);
		
		if (responseCode == 200) {
			//User createdUser = response.readEntity(User.class);
			//logger.debug(createdUser);
		}
	}
	

	public static User createNewUser() {
		User user;
		
		user = new User();
		
		user.setUsername("deepti1729");
		user.setPassword("abc");

		user.setAddress1("2500 ");
		user.setAddress2("medallion");
		user.setCity("Union city");
		user.setCountry("USA");
		user.setDob(new Date());
		user.setEmail("deepti1729@gmail.com");
		user.setFname("Deepthi");
		//user.setId(1);
		user.setActive(0);
		user.setMname("Reddy");
		user.setPhone_cell("10102020");
		user.setPhone_home("8527419638");
		user.setPwd_hint_ans("Toy");
		user.setPwd_hint("What is your first gift?");
		user.setRegistered_on(new Date());
		user.setRole_id(1);
		user.setState("CA");
		return user;
	}




}
