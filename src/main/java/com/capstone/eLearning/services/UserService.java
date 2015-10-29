package com.capstone.eLearning.services;

import java.util.List;

import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.ServiceException;

public interface UserService {
	  public int saveUser(User user) throws ServiceException;
	  public User login(User user) throws ServiceException;
	  public User getUserByName(String username) throws ServiceException;
	  public boolean activateUser(String email) throws ServiceException;
	  public boolean forgotpassword(String email) throws ServiceException;
	  public boolean resetPassword(String email, String password)throws ServiceException;
	  public List<User> getAllUsers() throws ServiceException;
	  public boolean isUniqueUser(String username) throws ServiceException;

}
