package com.ceking.crud.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceking.crud.bean.Employee;
import com.ceking.crud.dao.EmployeeMapper;
import com.ceking.crud.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ServiceTest {

	@Autowired
	EmployeeService service;
	
	@Test
	public void testService() {
		System.out.println(service);
		List<Employee> all = service.getAll();
		System.out.println(all);
	}
	
	public static void main(String[] args) {
				
	}	
}
