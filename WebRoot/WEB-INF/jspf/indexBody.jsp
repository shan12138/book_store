<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="demo" class="carousel slide" data-ride="carousel">
	  <!-- Ö¸Ê¾·û -->
	  <ul class="carousel-indicators">
		<li data-target="#demo" data-slide-to="0" class="active"></li>
		<li data-target="#demo" data-slide-to="1"></li>
		<li data-target="#demo" data-slide-to="2"></li>
	  </ul>
	 
	  <!-- ÂÖ²¥Í¼Æ¬ -->
	  <div class="carousel-inner">
		<div class="carousel-item active" >
		  <img class='imgModel' style='margin-left:5%;' src="<c:url value='/images/6.jpg' />">
		</div>
		<div class="carousel-item">
		  <img  class='imgModel' style='margin-left:5%;' src="<c:url value='/images/5.jpg' />">
		</div>
		<div class="carousel-item">
		  <img  class='imgModel' style='margin-left:5%;' src="<c:url value='/images/3.jpg' />">
		</div>
	  </div>
	 
	  <!-- ×óÓÒÇÐ»»°´Å¥ -->
	  <a class="carousel-control-prev" href="#demo" data-slide="prev">
		<span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#demo" data-slide="next">
		<span class="carousel-control-next-icon"></span>
	  </a>
	</div>
 

