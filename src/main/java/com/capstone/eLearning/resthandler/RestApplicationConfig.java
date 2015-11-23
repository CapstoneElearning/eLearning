package com.capstone.eLearning.resthandler;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.capstone.eLearning.controllers.CourseController;
import com.capstone.eLearning.controllers.SearchController;
import com.capstone.eLearning.exception.UnknownResourceExResolver;;

@ApplicationPath("/")
public class RestApplicationConfig extends Application {
	private Set<Class<?>> restClassSet = new HashSet<Class<?>>();
	
	public RestApplicationConfig() {
		restClassSet.add(JacksonFeature.class);
		restClassSet.add(UserRestHandler.class);
		restClassSet.add(CourseController.class);
		restClassSet.add(SearchController.class);
		restClassSet.add(UnknownResourceExResolver.class);
	}
	
	public Set<Class<?>> getClasses() {
		return restClassSet;
	}
}
