<%@ page language="java" import="java.util.*,com.hgd.ebp.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�û�ע��</title>
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
                       placeholder='�û���' onBlur="checkUserName(this.value)"/>
                </td>
	             <td>
	                <span id="userNameErr" class="err_msg"><form:errors path="userName"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:password class="inputbox" path="password"
                         placeholder='����'  onBlur="checkPassword(this.value)" />
                </td>
                <td>
	                <span id="passwordErr" class="err_msg"><form:errors path="password"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:password class="inputbox" path="passwordagain" placeholder='ȷ������'/>
                </td>
	            <td>
	                <span class="err_msg"><form:errors path="passwordagain"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:input path="uname" class="inputbox" 
                    placeholder='��ʵ����' onBlur="checkUname(this.value)"/>
                </td>
	             <td>
	                <span id="unameErr" class="err_msg"><form:errors path="uname"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:radiobutton  path="gender" value="��" label="��" onBlur="checkGender()" />
                           &nbsp;&nbsp;
                    <form:radiobutton  path="gender" value="Ů" label="Ů" onBlur="checkGender()" />
                </td>
               <td>
	                <span id="genderErr" class="err_msg"><form:errors path="gender"/></span>
	                <br/><br/>
	            </td>
            </tr>
            <tr>
                <td>
                    <form:input class="inputbox" path="idCard" 
                    placeholder='���֤��' onBlur="checkIdCard(this.value)"/>
                </td>
                
  				<td>
  					<span id="idCardErr" class="err_msg"><form:errors path="idCard"/></span>
	                <br/><br/>
  				</td>
            </tr>
            <tr>
                <td>
                   <form:input class="inputbox" path="address" 
                   	placeholder='ͨѶ��ַ' onBlur="checkAdress(this.value)"/>
                </td>
                <td>
  					<span id="adressErr" class="err_msg"><form:errors path="address"/></span>
	                <br/><br/>
  				</td>
            </tr>
            <tr>
                <td>
                    <form:input class="inputbox" path="telNo"
                    	 placeholder='��ϵ�绰' onBlur="checkTelNo(this.value)"/>
                </td>
                <td>
  					<span id="telNoErr" class="err_msg"><form:errors path="telNo"/></span>
	                <br/><br/>
  				</td>
            </tr>
            <tr>
               <td>
                    <input type="submit" value="ע��" class='registersubmit'/>
                </td>
                <td align="center">
                   <input type="reset" class="register_reset" value="����"/>
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
