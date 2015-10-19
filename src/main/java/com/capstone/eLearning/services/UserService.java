package com.capstone.eLearning.services;

import com.capstone.eLearning.domain.User;

public interface UserService {
	  public int saveUser(User user);
	  public User login(User user);
	  
	  public User getUserByName(String username);

}
