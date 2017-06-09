<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#lname").change(function(){
			var val=$(this).val();
			val=$.trim(val);   /* 去除前后空格 */
			$(this).val(val); 	/* 将去除的前后空格还原 */
			var  url="${pageContext.request.contextPath}/ajaxValidateLastNames";
			var args = {"lastName":val,"date":new Date()}; /* json格式 ，date记录缓存的*/
			
			$.post(url, args, function(data){
				if(data == "0"){
					alert("lastName 可用!");
				}else if(data == "1"){
					alert("lastName 不可用!");
				}else{
					alert("网络或程序出错. ");
				}
			});
		});
	})
</script>
<!-- 实现效果输入一个字段，该字段会自动与数据库进行校验，而不是点击提交按钮 -->
</head>
<body>
<!--get请求显示这个页面，post请求添加新的employee  -->
<!--modelAttribute的值必须与handler的map中key对应  -->
	<%-- <form:form action="${pageContext}/ees" method="POST" modelAttribute="employee"> --%>
	<form:form action="${pageContext.request.contextPath}/ees" method="POST" modelAttribute="employee">
		最新名字：<form:input path="lastName" id="lname"/><br>
		电子邮件：<form:input path="email"/><br>
		生日：<form:input path="birth"/><br>
		部门：<form:select path="dept.id" items="${departments}" 
		itemLabel="departmentName" itemValue="id"></form:select><br>
		<input type="submit" value="提交"/>
	</form:form>
</body>
</html>