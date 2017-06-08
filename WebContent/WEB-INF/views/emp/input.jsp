<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--get请求显示这个页面，post请求添加新的employee  -->
<!--modelAttribute的值必须与handler的map中key对应  -->
	<form:form action="${pageContext}/ees" method="POST" modelAttribute="employee">
		最新名字：<form:input path="lastName"/><br>
		电子邮件：<form:input path="email"/><br>
		生日：<form:input path="birth"/><br>
		部门：<form:select path="dept.id" items="${departments}" 
		itemLabel="departmentName" itemValue="id"></form:select><br>
		<input type="submit" value="提交"/>
	</form:form>
</body>
</html>