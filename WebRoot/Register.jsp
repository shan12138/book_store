<%@ page language="java" import="java.util.*,com.hgd.ebp.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
    <link href='<c:url value="/style/t_style.css"/>' rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value='/js/common.js'/>" /></script>
  </head>
  <body>
  <div id="page_login">
		<div id="main_body_register">
  	<jsp:useBean id="user" scope="request" class="com.hgd.ebp.domain.User" />
  			<div id="register_form">
  	<form:form modelAttribute="user" action="./Register" method="post"  onsubmit="return checkRegisterForm(this)">
        <table style="border-bottom: 1 solid black; width: 80%"> 
            <tr>
                <td>
                    <form:input class="inputbox" path="userName" autofocus="true"
                       placeholder='用户名' onBlur="checkUserName(this.value)"/>
                </td>
	             <td>
	                <span id="userNameErr" class="err_msg"><form:errors path="userName"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:password class="inputbox" path="password"
                         placeholder='密码'  onBlur="checkPassword(this.value)" />
                </td>
                <td>
	                <span id="passwordErr" class="err_msg"><form:errors path="password"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:password class="inputbox" path="passwordagain" placeholder='确认密码'/>
                </td>
	            <td>
	                <span class="err_msg"><form:errors path="passwordagain"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:input path="uname" class="inputbox" 
                    placeholder='真实姓名' onBlur="checkUname(this.value)"/>
                </td>
	             <td>
	                <span id="unameErr" class="err_msg"><form:errors path="uname"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:radiobutton  path="gender" value="男" label="男" onBlur="checkGender()" />
                           &nbsp;&nbsp;
                    <form:radiobutton  path="gender" value="女" label="女" onBlur="checkGender()" />
                </td>
               <td>
	                <span id="genderErr" class="err_msg"><form:errors path="gender"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:input class="inputbox" path="idCard" 
                    placeholder='身份证号' onBlur="checkIdCard(this.value)"/>
                </td>
                
  				<td>
  					<span id="idCardErr" class="err_msg"><form:errors path="idCard"/></span>
	                <br/><br/>
  				</td>
            </tr>
            <tr>
                <td>
                   <form:input class="inputbox" path="address" 
                   	placeholder='通讯地址' onBlur="checkAdress(this.value)"/>
                </td>
                <td>
  					<span id="adressErr" class="err_msg"><form:errors path="address"/></span>
	                <br/><br/>
  				</td>
            </tr>
            <tr>
                <td>
                    <form:input class="inputbox" path="telNo"
                    	 placeholder='联系电话' onBlur="checkTelNo(this.value)"/>
                </td>
                <td>
  					<span id="telNoErr" class="err_msg"><form:errors path="telNo"/></span>
	                <br/><br/>
  				</td>
            </tr>
            <tr>
               <td>
                    <input type="submit" value="注册" class='registersubmit'/>
                </td>
                <td align="center">
                   <input type="reset" class="register_reset" value="重置"/>
                </td>
            </tr>
        </table>
    </form:form>
    			</div>
    		<div id="footer"></div>
		</div>
	</div>
  </body>
</html>
