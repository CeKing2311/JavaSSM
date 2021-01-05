<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<script type="text/javascript"
	src="${APP_PATH}/static/js/jQuery-2.2.0.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>


</head>
<body>
	<!-- 显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div clalss="row">
			<div class="col-md-12">
				<h2>SSM_CRUD</h2>
			</div>
		</div>
		<!-- 按钮 -->
		<div clalss="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div clalss="row">
			<div class="col-md-12">
				<table class="table table-bordered table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<tbody id="tbody">
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页 -->
		<div clalss="row">
			<div class="col-md-6" id="pageInfo"></div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination" id="pagination">
				</ul>
				</nav>
			</div>
		</div>
			<!-- 员工添加 -->
<div class="modal fade" id="addEmp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增员工</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="formInfo">
         <div class="form-group">
			    <label for="empname" class="col-sm-2 control-label">EmpName</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="empname" name="empname" placeholder="">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="email" name="email" placeholder="Email@qq.com">
			    </div>
			  </div>
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">Gender</label>
			    <div class="col-sm-10">
			      		<label class="radio-inline">
						  <input type="radio" name="gender" id="radM" value="M" checked="checked"> 男
						</label>
						<label class="radio-inline">
						  <input type="radio" name="gender" id="radF" value="F"> 女
						</label>
			    </div>
			  </div>
			   <div class="form-group">
			    <label  class="col-sm-2 control-label">DeptName</label>
			    <div class="col-sm-4">
			      	<select class="form-control" name="dId" id="sltDept">
			      		
			      	</select>			      		
			    </div>
			  </div>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="saveInfo">保存</button>
      </div>
    </div>
  </div>
</div>
	</div>

	<script type="text/javascript">
	$(function(){
		getEmps(1);		
		$("#emp_add").click(function(){
			getDepts();
			$('#addEmp').modal({
				backdrop:'static'				
			})			
		});
		$("#saveInfo").click(function(){
		  var info=	$("#formInfo").serialize();
		  console.log(info);
		  $.ajax({
				url:"${APP_PATH}/emp/1",
				type:"post",
				success:function(result){									 
				 
				}
			})  
		});
	});	
	function getDepts(){
		$.ajax({
			url:"${APP_PATH}/depts",
			type:"get",
			success:function(result){					
			  console.log(result);	
			  initDept(result.data.dept);
			}
		})
	}
	function initDept(deptList){
		$("#sltDept").empty();
		//$("<option value=''>===请选择部门===</option>").appendTo("#sltDept");
		$.each(deptList,function(index,item){
		 	$("<option></option>").attr("value",item.deptId).append(item.deptName).appendTo("#sltDept");		  
		});
	}
	
	function getEmps(pageIndex){
		$.ajax({
			url:"${APP_PATH}/emps",
			data:"pageIndex="+pageIndex,
			type:"get",
			success:function(result){					
			  //1.解析并显示员工数据
			  set_emps_table(result);			  
			  //2.解析分页信息
			  set_page_info(result.data.pageInfo);
			  set_page_nav(result.data.pageInfo);				  
			}
		})
	}
	function set_emps_table(result) {
		$("#tbody").empty();
		var emps= result.data.pageInfo.list;
		$.each(emps,function(index,item){
			var empIdTd= $("<td></td>").append(item.empId);
			var empNameTd= $("<td></td>").append(item.empName);
			var gender= item.gender=="M"?"男":"女";
			var genderTd= $("<td></td>").append(gender);
			var emailTd= $("<td></td>").append(item.email);
			var empDepartTd= $("<td></td>").append(item.dept.deptName);
			
			var _editBtn='<button class="btn btn-primary btn-sm">修改</button>';
		    var _deleteBtn='<button class="btn btn-danger btn-sm">删除</button>';				
			var handleTd=$("<td></td>").append(_editBtn).append(_deleteBtn);
		    $("<tr></tr>")
		    .append(empIdTd)
		    .append(empNameTd)
		    .append(genderTd)
		    .append(emailTd)
		    .append(empDepartTd)
		    .append(handleTd)
		    .appendTo("#tbody");				
		});
	}
	
	function set_page_info(pageInfo) {
		$("#pageInfo").html("当前"+pageInfo.pageNum+"页,总"+pageInfo.pages+"页,总"+pageInfo.total+"条记录")
	}
	function set_page_nav(pageInfo) {		
		var _page_nav="";
		_page_nav+= '<li onclick="getEmps(1)"><a href="#" >首页</a></li>';
		if(pageInfo.hasPreviousPage){
			_page_nav+='<li><a onclick="getEmps('+(pageInfo.pageNum-1)+')" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>';
		}
		$.each(pageInfo.navigatepageNums,function(index,item){
			if(pageInfo.pageNum==item){
				_page_nav+='<li class="active"><a onclick="getEmps('+item+')" href="#">'+item+'</a></li>';					
			}else{					
				_page_nav+='<li><a onclick="getEmps('+item+')" href="#">'+item+'</a></li>';
			}
		})
		if(pageInfo.hasNextPage){
			_page_nav+='<li><a onclick="getEmps('+(pageInfo.pageNum+1)+')" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>';
		}
	    _page_nav+='<li><a onclick="getEmps('+pageInfo.pages+')" href="#">末页</a></li>';
	    
	    $("#pagination").html(_page_nav);
	}
</script>
</body>
</html>