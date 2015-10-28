package com.capstone.eLearning.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.capstone.eLearning.domain.Department;

public class DepartmentRowMapper implements RowMapper<Department> {
	public Department mapRow(ResultSet resultSet, int row) throws SQLException {
		Department department = new Department();
		department.setDeptId(resultSet.getInt("dept_id"));
		department.setName(resultSet.getString("name"));
		department.setLocation(resultSet.getString("location"));
		department.setSchoolCode(resultSet.getString("schoool_code"));	
		return department;
	}
}
