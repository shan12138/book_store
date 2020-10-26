<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href='<c:url value="/style/t_style.css"/>' rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="<c:url value='/js/commom.js'/>" /></script>
</head>
<body>
	<div id="page_login">
		<div id="main_body_login">
			 <jsp:useBean id="user" scope="request" class="com.hgd.ebp.domain.User" />
				<div id="login_form">
				<form:form id="loginform" modelAttribute="user" action="./UserLogin" method="post" 
				 	onsubmit="checkLoginForm(this)">
				 <p class="err_msg"><form:errors /></p>
				<table>
					<tr>
						<td><form:input class="inputbox"  path="userName" placeholder='ÇëÊäÈëÓÃ»§Ãû'
							autofocus="true" required="true" /></td>
		                <td>
	                		<span class="err_msg"><form:errors path="userName"/></span>
	                		<br/><br/>
	          		   </td>
					</tr>
					
					<tr>
						<td><form:password class="inputbox" path="password" placeholder='ÇëÊäÈëÃÜÂë' required="true"/></td>
		                <td>
	                		<span class="err_msg"><form:errors path="password"/></span>
	                		<br/><br/>
	          		   </td>
					</tr>
					<tr>
						<td><input type="submit" value="µÇÂ¼" class='inputsubmit'></td>
					</tr>
					<td><a href='../Register.jsp' style='width:150px;border-radius:10px;height:40px;float:right;color:red'>
					Î´×¢²á £¿µã»÷×¢²á</a></td>
					</tr>
			</table>
		</form:form>
	 </div>	
		<div id="footer"></div>
	 </div>
</body>
</html>


