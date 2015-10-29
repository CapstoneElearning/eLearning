package com.capstone.eLearning.dao;

import java.util.List;

import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.DaoException;

public interface UserDao {
	  public int saveUser(User userDto) throws DaoException;
	  public User login(User user) throws DaoException;
	  public User getUserByName(String username) throws DaoException;
	  public boolean activateUser(String email) throws DaoException;
	  public boolean forgotpassword(String email) throws DaoException;
	  public boolean isEmailExists(String email) throws DaoException;
	  public boolean resetPassword(String email, String password) throws DaoException;
	  public List<User> getAllUsers() throws DaoException;
	  public boolean isUniqueUser(String username) throws DaoException;


}
