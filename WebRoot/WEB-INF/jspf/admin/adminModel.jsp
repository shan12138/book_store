<%@ page contentType="text/html" pageEncoding="gbk" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
     <script type="text/javascript" src="<c:url value='/js/uploadImages.js'/>"></script>
  </head>
  <body>
 <div style='width:100%'>
	<div class='header'>
		<div class='header1'>
			<img style='margin-top:10px;' class='imgModel' src='<c:url value='/images/logo.jpg' />'></img>
		</div>
		<div class='header2'>
			<p>爱上阅读</p>
		</div>
		<div class='header3'>
			  <ul class="nav nav-pills">
				<li class="nav-item" style="margin-left:10%;">
  					<a class="nav-link " href='<c:url value="/admin/BooksCtrl" />'>票项管理</a>
  				</li>
  				<li class="nav-item" style="margin-left:10%;">
  					<a class="nav-link " href='<c:url value="/admin/UsermangeCtrl" />'>用户管理</a>
  				</li>
  				<li class="nav-item" style="margin-left:10%;">
  					<a class="nav-link " href='<c:url value="/admin/OrdersCtrl" />'>订单管理</a>
  				</li>
  				<li class="nav-item" style="margin-left:10%;">
  					<a class="nav-link " href='<c:url value="/admin/index.jsp" />'>退出</a>
  				</li>
  			</ul>
  		</div>
	</div>
  		<div class="line"></div>
  		<div id="main_body">
  			<div id="content">
                    <jsp:include page="/WEB-INF/jspf/admin/${main_body}" />
            </div> 
  		</div>
  		<div id="footer">
  			<hr noshade />
  			<h6>
				 &copy; 爱上电影, 2000 -
				 <%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>
			</h6>
  		</div>
  	</div>
  </body>
</html>
