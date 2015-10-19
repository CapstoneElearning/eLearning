package com.capstone.eLearning.dao.jdbc;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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


@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private SimpleJdbcInsert jdbcInsertAdd;
	@Autowired
	@Qualifier("userPOJO")
	private UserPOJO userPojo;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("users")
				.usingGeneratedKeyColumns("id_pk")
				.usingColumns("username", "password", "fname","lname","mname","dob","email","phone_home","phone_cell","address1","address2","city","state","country","pwd_hint","pwd_hint_ans","active","registered_on","role_id");
	}

	public int saveUser(User user) {
		if(user.getRole_id()==null)
			user.setRole_id(1);
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(user);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		int userId = newId.intValue();
		user.setId(userId);
		return userId;

	}
	
	@Override
	public User login(User user) {
		User user1 = new User();
		String username = user.getUsername();
		String password = user.getPassword();
		String sql = "SELECT * FROM users WHERE username = :username";
		MapSqlParameterSource params = new MapSqlParameterSource("username",username);		
		user1 = namedTemplate.queryForObject(sql, params, userPojo);
        if(user.getPassword().equals(user1.getPassword())){
        	return user1;
        }
		return null;
	}

	@Override
	public User getUserByName(String username) throws DaoException {
		String sql = "SELECT * FROM users WHERE username = :username";
		MapSqlParameterSource params = new MapSqlParameterSource("username",username);		
		User user1 = namedTemplate.queryForObject(sql, params, userPojo);
		return user1;
        
	}



	
}
