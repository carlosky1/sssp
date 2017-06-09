<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
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
			
			//若修改的lastName与之前的一样，则不发送ajax请求，直接alert
			//
			var oldLastName = $("#oldLastName").val();
			oldLastName = $.trim(oldLastName);
			if(oldLastName != null && oldLastName != "" && oldLastName == val){
				alert("lastName 可用!");
				return;
			}
			
			
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

	<c:set value="${pageContext.request.contextPath }/ees" var="url"></c:set>
		<c:if test="${employee.id != null }">
			<c:set value="${pageContext.request.contextPath }/ees/${employee.id}" var="url"></c:set>
		</c:if>
	<form:form action="${url }" method="POST" modelAttribute="employee">
		<!--隐藏域与ModelAttribute属性相关，或者需要回显信息，需要提交的用form:hidden ;隐藏域不需要提交，隐藏域的属性和bean【ModelAttribute=""】没有任何关系的用input type="hidden"  -->
		<c:if test="${employee.id != null }">
			<input type="hidden" id="oldLastName" value="${employee.lastName }"/>
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>


<!--隐藏域,保存以前的lastName，不需要提交  -->
<%-- 	<c:if test="${employee.id!=null}">
		<input type="hidden" name="oldLastName" value="${ employee.lastName}"/>
	</c:if> --%>

<!--get请求显示这个页面，post请求添加新的employee  -->
<!--modelAttribute的值必须与handler的map中key对应  -->
	<%-- <form:form action="${pageContext}/ees" method="POST" modelAttribute="employee"> --%>
	<%-- <form:form action="${pageContext.request.contextPath}/ees" method="POST" modelAttribute="employee"> --%>
		最新名字：<form:input path="lastName" id="lname"/><br>
		电子邮件：<form:input path="email"/><br>
		生日：<form:input path="birth"/><br>
		部门：<form:select path="dept.id" items="${departments}" 
		itemLabel="departmentName" itemValue="id"></form:select><br>
		<input type="submit" value="提交"/>
	</form:form>
</body>
</html>