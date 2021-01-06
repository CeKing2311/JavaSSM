package com.ceking.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceking.crud.bean.Employee;
import com.ceking.crud.bean.EmployeeExample;
import com.ceking.crud.bean.EmployeeExample.Criteria;
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
	
	public int saveEmp(Employee employee) {		
		
	   return employeeMapper.insertSelective(employee);		
	}

	/**
	 * 校验用户名是否存在
	 * @param empName
	 * @return true:当前用户名可用，false：不可用
	 */
	public boolean chkEmpName(String empName) {
		EmployeeExample example =new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);				 
		long count = employeeMapper.countByExample(example);
		return count==0;
	}
	/**
	 * 按照员工ID查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
	    return	employeeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 员工更新
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * 员工删除
	 * @param empId
	 */
	public void deleteByEmpId(Integer empId) {		
		employeeMapper.deleteByPrimaryKey(empId);
	}

	public void deleteBatchById(List<Integer> ids) {
		EmployeeExample example =new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
		
	}
	
	
	
}
