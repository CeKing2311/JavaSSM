package com.ceking.crud.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	/**
	 * 员工删除
	 * 支持批量删除，多个员工ID之间以-分隔
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public WebResult deleteByEmpId(@PathVariable("ids") String ids) {
		if(ids.contains("-")){
			List<Integer> int_ids= new ArrayList<>();
			String[] str_id = ids.split("-");
			for (String strId : str_id) {
				int_ids.add(Integer.parseInt(strId));
			}
			employeeService.deleteBatchById(int_ids);
			
		}else{
			employeeService.deleteByEmpId( Integer.parseInt(ids));	
		}
		
		return WebResult.success();
	}
	
	/**
	 * 员工更新
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public WebResult saveEmp(@PathVariable("empId")Integer empId,Employee employee) {
		employee.setEmpId(empId);
		System.out.println(employee);
		employeeService.updateEmp(employee);
		return WebResult.success();
	}
	
	/**
	 * 查询员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public WebResult getEmp(@PathVariable("id") Integer id) {
	    Employee employee= employeeService.getEmp(id);
		return WebResult.success().add("emp", employee);
	}
	/**
	 * 检查输入的名字是否合法
	 * @param empName
	 * @return
	 */
	@RequestMapping("/chkempname")
	@ResponseBody
	public WebResult chkEmpName(@RequestParam("empName") String empName) {
		//先判断用户名是否合法
		String regx= "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)";
		if (!empName.matches(regx)) {
			return WebResult.fail().add("msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合!");
		}
		boolean isExist = employeeService.chkEmpName(empName);
		if (isExist) {
			return WebResult.success();
		} else {
			return WebResult.fail().add("msg", "用户名重复!");
		}		
	}

	/**
	 * 员工保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public WebResult saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()){
			Map<String, Object> errMap=new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return WebResult.fail().add("errFileds", errMap);
		}else{
			employeeService.saveEmp(employee);
			return WebResult.success();
		}
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
