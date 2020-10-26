<%@ page language="java" import="java.util.*,com.hgd.ebp.domain.*,com.hgd.ebp.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="shop_detail_box_clearfix">
		<!-- 左侧图 -->
		<div class="newbook_left">
			<div class="bookCover_area">
				<img src="<c:url value='${getBook.image}' />" alt="${getBook.bname}" class='img_book'>
			</div>
		</div>
		 <!-- 图书描述 -->
		<div class="newbook_right">
			<div class="title_words">
				<h1 class="bookname_box"><span>${getBook.bname}</span></h1>
			</div>
			<div class="desc_box">
				<p style="color:gray;font-size:15px;"><i>简介:
				</i></p>
				<p style="color:gray;font-size:15px;"><i>${getBook.descp }</i></p>
			</div>
			<div class="info_box">
				<p>售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<span class="normal_price">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥<i>${getBook.price}</i></span>
				</p>
				<p dd_name="作者" id="author">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${getBook.author }</span>
				</p>
				<p dd_name="出版社" id="publisher">出&nbsp;&nbsp;版&nbsp;&nbsp;社：<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${getBook.publisher }</span>
				</p>
				<p>出版时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${getBook.publishTime }</p>
				<p>字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${getBook.price}</p>
			</div>
			<div class="button_box">
					<c:url  var="cartUrl" value='/user/AddShoppingCartCtrl' />
					<a href="#" onclick="addCart('${cartUrl}')" class="green_btn_2_btn" id="addShopCartBtn" dd_name="加入购物车">加入购物车
					<a href="<c:url value='/user/ShoppingCart.jsp' />" class="green_btn_2_btn" id="addShopCartBtn" dd_name="加入购物车">查看购物车
					</a>
			</div>
		</div>
		<div class="view">
			<div class="myview_box">
				<p>请发表你的评论...</p>
				<jsp:useBean id="commentbean" scope="request" class="com.hgd.ebp.domain.CommentBean" />
    			<form:form modelAttribute="commentbean" action="/EBP/user/SendCommentCtrl" method="POST">
					<div class="view_content" style="width:90%;height:100px;">
						<form:textarea path='comments' cols='150' rows='3'/>
						<div class="view_button">
							<button type="submit">发表</button>
						</div>
					</div>
				</form:form>
			</div>
			<c:if test="${empty GetComments}">
				<p style="font-size:20px;color:red;align:center">暂无评论，快快抢占评论沙发！</p>
			</c:if>
			<c:if test="${!empty GetComments}">
			<div class="views">
				<h3 style="color:#153f1c;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>精彩书评</b></h3>
				<c:forEach  var="comment" items='${GetComments}'>
					<div class="views_box">
						<div class="views_info">
							<p>${comment.comments}</p>
						</div>
						<div class="views_auth" >
							<p>${comment.username}</p>
						</div>
						<div class="views_auth" >
							<p>${comment.commentTime}</p>
						</div>
					</div>
				</c:forEach>
			</div>
			</c:if>
		</div>
	</div>
