package com.capstone.eLearning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.eLearning.dao.UserDao;
import com.capstone.eLearning.dao.jdbc.UserDaoImpl;
import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.DaoException;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Transactional(readOnly = false)
	@Override
	public int saveUser(User userDto) throws DaoException {
		int count = userDao.saveUser(userDto);
		return count;
	}

	@Override
	public User login(User user) throws DaoException {

		User user1 = userDao.login(user);
		return user1;
	}

	@Override
	public User getUserByName(String username) {
		User user1 = userDao.getUserByName(username);
		return user1;
	}

}
