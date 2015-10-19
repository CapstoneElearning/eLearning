package com.capstone.eLearning.dao;

import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.DaoException;

public interface UserDao {
	  public int saveUser(User userDto) throws DaoException;
	  public User login(User user) throws DaoException;
	  public User getUserByName(String username) throws DaoException;

}
