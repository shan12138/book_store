<%@ page language="java" import="java.util.*" pageEncoding="gbk" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
  <head>
  <title>'_layout.jsp'</title>
    <meta HTTP-EQUIV=Content-Type content="text/html; charset=gbk">
    <meta HTTP-EQUIV=Content-Type content="text/html; charset=gbk">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link href='<c:url value="/style/model.css"/>' rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/style.css"/>' rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value='/js/admin.js' />"></script>
  </head>
  <body>
  <div id="login" class="model">
	  <jsp:useBean id="admin" scope="request" class="com.hgd.ebp.domain.Admin" />
  		<div id="login_form">
			<form:form id="loginform" modelAttribute="admin" action="./AdminLogin" method="post"
				onsubmit="checkAdminLoginForm(this)" class="modal-content animate" style='width:40%'>
			<div class="container">
			 	<label><b>账号</b></label>
				<form:input class="inputbox textPassword" path="name" placeholder='请输入账号' 
				onBlur="checkAdminName(this.value)" autofocus="true" required="true"/>
				<span class="err_msg" id='nameErr'><form:errors path="name"/></span><br/>
				<label><b>密码</b></label>
				<form:password class="inputbox textPassword" path="password" placeholder='请输入密码' 
				onBlur="checkPassword(this.value)" required="true"/>
				<span class="err_msg" id='passwordErr'><form:errors path="password"/></span><br/>
				<button type="submit">登陆</button>
			</div>
			</form:form>
		</div>
	</div>
</body>
</html>