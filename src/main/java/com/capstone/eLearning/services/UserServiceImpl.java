package com.capstone.eLearning.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capstone.eLearning.dao.UserDao;
import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.ServiceException;
import com.capstone.eLearning.util.Constants;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("mailService")
	private MailService mailService;

	@Transactional(readOnly = false)
	@Override
	public int saveUser(User userDto) throws ServiceException {
		int count = 0;
		try {
			boolean flag = userDao.isEmailExists(userDto.getEmail());
			if (flag == false) {
				count = userDao.saveUser(userDto);
				mailService.sendMail(Constants.email, userDto.getEmail(), " eLearning - Activate Registration ",
						"Please click on the below link to activate your account ,Thanku. http://localhost:8080/eLearning/ActivationServlet?userId="
								+ userDto.getEmail());

			} else {
				logger.error("User already exists for email::  " + userDto.getEmail());

			}
		} catch (Exception e) {
			logger.error("Exception while saving user for email::  " + userDto.getEmail());
			throw new ServiceException("Exception while saving user for email::  " + userDto.getEmail());
		}
		return count;
	}

	@Override
	public User login(User user) throws ServiceException {
		User user1 = new User();
		try {
			user1 = userDao.login(user);
		} catch (Exception e) {
			logger.error("Exception while logging user for email::  " + user.getEmail());
			throw new ServiceException("Exception while logging user for email::  " + user.getEmail());

		}
		return user1;
	}

	@Override
	public User getUserByName(String username) throws ServiceException {
		User user1 = new User();
		try {
			user1 = userDao.getUserByName(username);
		} catch (Exception e) {
			logger.error("Exception while getting user for username::  " + username);
			throw new ServiceException("Exception while username::  " + username);

		}
		return user1;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean activateUser(String email) throws ServiceException {
		boolean flag;
		try {
			flag = userDao.activateUser(email);
		} catch (Exception e) {
			logger.error("Exception while activating user for email::  " + email);
			throw new ServiceException("Exception while activating user for email::  " + email);

		}
		return flag;
	}

	@Override
	public boolean forgotpassword(String email) throws ServiceException {
		boolean flag;
		try {
			flag = userDao.forgotpassword(email);
			if (flag == true) {
				mailService.sendMail(Constants.email, email, "Reset Password for eLearning",
						"Please click on the below link to Reset password for eLearning ,Thanku. http://localhost:8080/eLearning/ResetPasswordServlet?email="
								+ email);
			}
		} catch (Exception e) {
			logger.error("Exception in forgot password for user with email::  " + email);
			throw new ServiceException("Exception in forgot password for user with email::  " + email);

		}
		return flag;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean resetPassword(String email, String password) throws ServiceException {
		boolean flag;
		try {
			flag = userDao.resetPassword(email, password);
		} catch (Exception e) {
			logger.error("Exception in reset password for user with email::  " + email);
			throw new ServiceException("Exception in reset password for user with email::  " + email);

		}
		return flag;
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		List<User> users = null;
		try {
			users = userDao.getAllUsers();
		} catch (Exception e) {
			logger.error("Exception while getting all users");
			throw new ServiceException("Exception while getting all users");

		}
		return users;

	}

}
