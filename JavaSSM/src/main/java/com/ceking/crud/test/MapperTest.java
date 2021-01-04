package com.ceking.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceking.crud.bean.Department;
import com.ceking.crud.bean.Employee;
import com.ceking.crud.dao.DepartmentMapper;
import com.ceking.crud.dao.EmployeeMapper;

/**
 * 测试dao层
 * 
 * @author cjq Spring的项目使用Spring的单元测试，自动注入需要的组件 1.导入SpringTest模块
 *         2.@ContextConfiguration 指定Spring配置文件的位置 3.直接Autowired 要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		// 原生的写法
		/*
		 * ApplicationContext context = new
		 * ClassPathXmlApplicationContext("applicationContext.xml");
		 * DepartmentMapper mapper = context.getBean(DepartmentMapper.class);
		 */
		
		// 1.插入部门数据
		/*
		 * Department department = new Department(null, "行政部");
		 * departmentMapper.insertSelective(department);
		 * departmentMapper.insertSelective(new Department(null, "研发部"));
		 * departmentMapper.insertSelective(new Department(null, "人事部"));
		 * departmentMapper.insertSelective(new Department(null, "开发部"));
		 * departmentMapper.insertSelective(new Department(null, "测试部"));
		 */
		// 2.插入员工数据
		/*for (int i = 0; i < 1000; i++) {
			employeeMapper.insertSelective(new Employee(null, UUID.randomUUID().toString().substring(0, 6) + i, "1",
					UUID.randomUUID().toString().substring(0, 6) + i + "@qq.com", 1));
		}*/

	}
}
