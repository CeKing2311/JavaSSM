package com.ceking.crud.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ceking.crud.bean.Employee;
import com.ceking.crud.bean.WebResult;
import com.ceking.crud.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工请求
 * 
 * @author cjq
 *
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	
	@ResponseBody
	public WebResult saveEmp() {
		return null;
	}
	
	/**
	 * 自动转为json数据返回
	 * 
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public WebResult getEmps(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
		PageHelper.startPage(pageIndex, 10);
		List<Employee> list = employeeService.getAll();
		// 使用PageInfo 包装查询后的结果
		PageInfo page = new PageInfo(list, 5);
		return WebResult.success().add("pageInfo", page);
	}

	/**
	 * 分页查询员工数据
	 * 
	 * @return
	 */
	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, Model model) {
		System.out.println("emps");
		PageHelper.startPage(pageIndex, 10);
		List<Employee> list = employeeService.getAll();
		// 使用PageInfo 包装查询后的结果
		PageInfo page = new PageInfo(list, 5);
		model.addAttribute("pageInfo", page);
		return "list";
	}
}
