package com.capstone.eLearning.dao.jdbc;

import java.sql.Types;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.capstone.eLearning.dao.UserDao;
import com.capstone.eLearning.domain.User;
import com.capstone.eLearning.exception.DaoException;
import com.capstone.eLearning.services.UserServiceImpl;
import com.capstone.eLearning.util.DataEncryptDecrypt;


@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("userPOJO")
	private UserPOJO userPojo;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private SimpleJdbcInsert jdbcInsertAdd;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("users")
				.usingGeneratedKeyColumns("id_pk")
				.usingColumns("username", "password", "fname","lname","mname","dob","email","phone_home","phone_cell","address1","address2","city","state","country","pwd_hint","pwd_hint_ans","active","registered_on","role_id");
	}

	@Override
	public int saveUser(User user) throws DaoException{
		int userId;
		try{
		if(user.getRole_id()==null)
		user.setRole_id(1);
        String dePassword = DataEncryptDecrypt.encryptData(user.getPassword());
        user.setPassword(dePassword);
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		userId = newId.intValue();
		user.setId(userId);
		}catch(Exception e){
		 logger.error("Exception occured in UserDaoImpl :: saveUser() email : " + user.getEmail(), e);
		 throw new DaoException("Exception occured in UserDaoImpl :: saveUser() email : " + user.getEmail(), e);

		}
		return userId;

	}
	
	@Override
	public User login(User user) throws DaoException {
		User user1 = new User();
		try{
		String username = user.getUsername();
		String password = user.getPassword();
		String sql = "SELECT * FROM users WHERE username = :username";
		MapSqlParameterSource params = new MapSqlParameterSource("username",username);		
		user1 = namedTemplate.queryForObject(sql, params, userPojo);
        String decrypted = DataEncryptDecrypt.decryptData(user1.getPassword());
        user1.setPassword(decrypted);
        if(user.getPassword().equals(user1.getPassword()) && user1.getActive()==1){
        	return user1;
        }}catch(Exception e){
		      logger.error("Exception occured in UserDaoImpl :: login() for email : " + user.getEmail(), e);
		      throw new DaoException("Exception occured in UserDaoImpl :: login() for email : " + user.getEmail(), e);

        }
		
		return null;
	}

	@Override
	public User getUserByName(String username) throws DaoException {
		User user1 = new User();
		try{
		String sql = "SELECT * FROM users WHERE username = :username";
		MapSqlParameterSource params = new MapSqlParameterSource("username",username);		
		 user1 = namedTemplate.queryForObject(sql, params, userPojo);
		}catch(Exception e){
		      logger.error("Exception occured in UserDaoImpl :: getUserByName() for username : " + username, e);
		      throw new DaoException("Exception occured in UserDaoImpl :: getUserByName() for username : " + username, e);

		}
		return user1;
        
	}
	
	  public boolean activateUser(String email) throws DaoException{
		  
		  boolean flag = false;
		  try{
			String sql = "UPDATE users SET active=1 WHERE email=?";
			int c = jdbcTemplate.update(sql,email);
             if(c == 1){
            	 flag = true; 
             }
		  }catch(Exception e){
		      logger.error("Exception occured in UserDaoImpl :: activateUser() for email : " + email, e);
		      throw new DaoException("Exception occured in UserDaoImpl :: activateUser() for email : " + email, e);

		  }
		   return flag;
		  
		  
	  }
	  
	  public boolean forgotpassword(String email) throws DaoException{
		  
		  boolean flag;
		  try{
		  flag = isEmailExists(email);
		  }catch(Exception e){
		      logger.error("Exception occured in UserDaoImpl :: forgotpassword() for email : " + email, e);
		      throw new DaoException("Exception occured in UserDaoImpl :: forgotpassword() for email : " + email, e);
 
		  }
		  return flag;
	  }
	  
	  public boolean isEmailExists(String email) throws DaoException{
			User user1 = new User();
		    boolean flag = false;	
		    try{
			String sql = "SELECT * FROM users WHERE email = :email";
			MapSqlParameterSource params = new MapSqlParameterSource("email",email);		
			user1 = namedTemplate.queryForObject(sql, params, userPojo);
			if(user1 != null){
				flag = true;
			}}catch(Exception e){
			      logger.error("Exception occured in UserDaoImpl :: isEmailExists() for email : " + email, e);
			      throw new DaoException("Exception occured in UserDaoImpl :: isEmailExists() for email : " + email, e);
			
			}
			return flag;
		
	  }
	   
	    @Override
		public boolean resetPassword(String email, String password) throws DaoException {
	    	try{
	        String newPassword = DataEncryptDecrypt.encryptData(password);
			boolean flag = false;
			String sql = "UPDATE users SET password= ? WHERE email= ?";
	        Object[] params = { newPassword, email};
 	        int[] types = {Types.VARCHAR, Types.VARCHAR};
			  int rows = jdbcTemplate.update(sql, params, types);
             if(rows == 1){
            	 flag = true; 
             }
	    	}catch(Exception e){
			      logger.error("Exception occured in UserDaoImpl :: resetPassword() for email : " + email, e);
			      throw new DaoException("Exception occured in UserDaoImpl :: resetPassword() for email : " + email, e);
		
	    	}

			return false;
			

    }

		

		}
