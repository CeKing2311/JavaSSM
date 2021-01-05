package com.ceking.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceking.crud.bean.Employee;
import com.ceking.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {	
		List<Employee> list = employeeMapper.selectByExampleWithDept(null);
		return list;
	}
	
	
	
}