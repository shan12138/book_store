<%@ page language="java" import="java.util.*,com.hgd.ebp.vi.*"  pageEncoding="gbk" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>'_layout.jsp'</title>
    <meta HTTP-EQUIV=Content-Type content="text/html; charset=gbk">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    
    <link href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href='<c:url value="/style/model.css"/>' rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/style.css"/>' rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/shoppingcart.css"/>' rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value='/js/js.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/user.js'/>"></script>
  </head>
  <body>  
<div class="contain">
	<div class='header'>
		<div class='header1'>
			<img style='margin-top:10px;' class='img' src='<c:url value='/images/logo.jpg' />' ></img>
		</div>
		<div class='header2'>
			<p>爱上阅读</p>
		</div>
		<div class='header3'>
			  <ul class="nav nav-pills">
				<li class="nav-item" style="margin-left:10%;">
				  <a class="nav-link " href="<c:url value='/index.jsp'/>" >首页</a>
				</li>
				<li class="nav-item" style="margin-left:25px;">
				  <a class="nav-link" href="<c:url value='/ListBooksCtrl'/>">书城</a>
				</li>
				<li class="nav-item" style="margin-left:25px;">
				  <a class="nav-link" href="<c:url value='/user/ShoppingCart.jsp' />">我的购物车</a>
				</li>
				<li class="nav-item" style="margin-left:25px;">
				  <a class="nav-link" href="<c:url value='/user/getOrders'/>">我的书架</a>
				</li>
				<li class="nav-item" style="margin-left:25px;">
				  <a class="nav-link " href="<c:url value='/user/UserInforCtrl'/>">个人信息</a>
				</li>
				<li class="nav-item" style="margin-left:25px;">
				  <a class="nav-link " href="<c:url value='/user/UserAccount'/>">账户充值</a>
				</li>
				<li class="nav-item" style="margin-left:25px;">
				  <a class="nav-link " href="<c:url value='/user/UserQuit'/>">退出</a>
				</li>
			  </ul>
		</div>
		 <div class='header4'>
		 	<c:if test="${msg==false || sessionScope.UserID == null || sessionScope.UserID == ''}">
				<button onclick="document.getElementById('login').style.display='block'" class='loginButton'>登录</button>
				<button onclick="document.getElementById('regist').style.display='block'" class='regisButton'>注册</button>
			</c:if>
			<c:if test="${msg==true && sessionScope.UserID != null && sessionScope.UserID != ''}">
				<div style="width:50px;height:50px;border-radius:50%;overflow:hidden;">
					<img src='<c:url value="${images}" />' style="width:100%;height:100%;"/>
				</div>
			</c:if>
			<div id="login" class="modal">
				<div id="login_form">
				<jsp:useBean id="userbean" scope="request" class="com.hgd.ebp.vi.UserBean" />
					<c:set var='address' value="/EBP/user/UserLogin"></c:set>
					<form:form id="loginform" modelAttribute="userbean" action="${address}" method="post" 
					 	onsubmit="checkLoginForm(this)" class='modal-content animate' style='width:40%'>
						<div class="container">
						  <label><b>用户名</b></label><label class="err_msg"><form:errors/></label>
						  <form:input class="inputbox textPassword" path="userName" placeholder='请输入用户名' onBlur="checkUserName(this.value)" 
						   autofocus="true" required="true"/>
						  <span class="err_msg" id='userNameErr'><form:errors path="userName"/></span><br/>
						  <label><b>密码</b></label>
						  <form:password path="upassword" class="inputbox textPassword" placeholder='请输入密码' 
						  onBlur="checkPassword(this.value)" required="true"/>
						  <span class="err_msg" id='passwordErr'><form:errors path="upassword"/></span><br/>
						  <button type="submit">登陆</button>
						</div>
						<div class="container" style="background-color:#f1f1f1">
						  <button type="button" onclick="document.getElementById('login').style.display='none'" class="cancelbtn">返回</button>
						</div>
				 	 </form:form>
				</div>
			</div>
			<div id="regist" class="modal">
				<jsp:useBean id="user" scope="request" class="com.hgd.ebp.domain.User" />
	  			<div id="register_form">
		  			<c:set var="regAddr" value="/EBP/Register"></c:set>
		  			<form:form modelAttribute="user" class="modal-content animate"
		  			action="${regAddr}" method="post"  onsubmit="return checkRegisterForm(this)" style='width:40%'>
					<div class="container">
						  <label class="err_msg"><form:errors/></label><br/>
						  <label><b>用户名</b></label>
						 	<form:input class='textPassword inputbox'  placeholder='请输入用户名' path="userName" 
						 	autofocus="true" onBlur="checkRegUserName(this.value)" required="true" />
						  <span class="err_msg" id='reguserNameErr'><form:errors path="userName"/></span><br/>
						  <label><b>密码</b></label>
						  <form:password class='textPassword inputbox' placeholder="请输入密码" path="password" 
	  						onBlur="checkRegPassword(this.value)" required="true"/>
	  					  <span class="err_msg" id='regpasswordErr'><form:errors path="password"/></span><br/>
						  <label><b>确认密码</b></label>
						  <form:password class='textPassword inputbox' placeholder="请再次输入密码" path="passwordagain"
						   required="true" onBlur="checkPasswordAgain(this.value)" />
						   <span id='passwordagainErr' class="err_msg"><form:errors path="passwordagain"/></span><br/>
						  <label><b>真实姓名</b></label>
						 <form:input path="uname" class='textPassword inputbox' placeholder="请输入真实姓名" 
						 required="true" onBlur="checkUname(this.value)"/>
						 <span id="unameErr" class="err_msg"><form:errors path="uname"/></span><br/>
						  <label><b>性别</b></label>
						  <form:radiobutton  path="gender" value="男" label="男" style='margin-left:50px' onBlur="checkGender()" required="true"/>
	                      <form:radiobutton  path="gender" value="女" label="女" style='margin-left:50px' onBlur="checkGender()" required="true"/>
						  <span id="genderErr" class="err_msg"><form:errors path="gender"/></span><br/>
						  <label><b>身份证号码</b></label>
						  <form:input class='textPassword inputbox' placeholder="请输入身份证号码" path="idCard"  required="true"
						  onBlur="checkIdCard(this.value)"/>
						  <span id="idCardErr" class="err_msg"><form:errors path="idCard"/></span><br/>
						  
						  <label><b>通讯地址</b></label>
						  <form:input class='textPassword inputbox' placeholder="请输入通讯地址" path="address" 
	                   		onBlur="checkAdress(this.value)"/>
	                   	  <span id="addressErr" class="err_msg"><form:errors path="address"/></span><br/>
	                   		
						  <label><b>联系电话</b></label>
						  <form:input class='textPassword inputbox' placeholder="请输入联系电话" path="telNo"
							 onBlur="checkTelNo(this.value)"/>
							 <span id="telNoErr" class="err_msg"><form:errors path="telNo"/></span><br/>
						  <div class="clearfix">
							<button type="button" onclick="document.getElementById('regist').style.display='none'" class="cancelbtn">返回</button>
							<button type="reset"  class="cancelbtn">重置</button>
							<button type="submit" class="signupbtn">确定</button>
						  </div>
						</div>
					 </form:form>
				</div>
			</div>
		</div>
	</div>
		<div class='line'>
		</div>
  		<div id="main_body">
  			<div id="content">
                   <jsp:include page="/WEB-INF/jspf/${main_body}" />
            </div> 
  		</div>
  		<!--  <div id="footer">
		<hr noshade />
		<h6>
			 &copy; 爱上阅读, 2000 -
			 <%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>
		</h6>
		</div>-->
	</div>
<c:if test="${sessionScope.msg==false}">
<c:set var="msg" scope="session" value="true"></c:set>
	<script>
		document.getElementById('login').style.display='block';
	</script>
</c:if>
<input type="hidden" id="guessLike" value="${sessionScope.msg == false ? 1:0}"/>
<c:if test="${sessionScope.guessLike == null ||sessionScope.guessLike ==''}">
	<script type="text/javascript">
		$("document").ready(function(){
			if($("#guessLike").val() == 0){
				$("#guessLike").attr("value",1);
				$.ajax({
			       	type     : "post",
			        async    :  true,
			        url      :  "/EBP/user/GuessLike",
			        dataType :  "text",
			        cache    :  false,
			        success  :  function(data){
			        	console.log(data);
			        	if(data.trim() != "")
			        		$("#guessLike").attr("value",1);
			        		
			        },
			        error    :  function(status){
			        	console.log(status);
			        }
	   		 });
	    }
		
		});
	</script>
</c:if>
</body>
</html>