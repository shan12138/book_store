<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.*,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="container-fluid" style='width:80%;align:center;
		height:100%;margin-top:50px;position:relative;min-height:600px;'>
		<div class="row-fluid" style="height:auto;min-height:80%">
			<div class="span12">
				<c:if test="${empty shoppingList}"> 
					<div align=center style="margin-top:50px;">
						<div style="width:200px;height:200px">
							<img src="<c:url value='/images/cart.png' /> " style="width:100%;height:100%">
						</div>
						<div>
							<p style="font-size:30px;color:red">当前购物车无添加</p>
						</div>
					</div>
			    </c:if>
			    <c:if test="${! empty shoppingList}">
				<table class="table table-hover">
					<tbody>
						<c:forEach var="book" items='${shoppingList}'>
						   
							<tr style='height:150px;text-align:center'>
								<td><input type="checkbox" id="cb${book.bid}" name="cbox" value="${book.bid}"/> </td>
								<td><img src='<c:url value='${book.image}'/>' style="width:100px;height:100px;
								max-height:150px;max-width:150px"/></td>
								<td class='textmiddle'>${book.bname}</td>
								<td id="price${book.bid}" class='textmiddle'>￥${book.price}</td>
								<td>
									<a href="#" onclick="count(1,${book.bid})"> + </a>
									<input id='num${book.bid}' type='text' size=10 value='${book.quantity}' />
									<a href="#" onclick="count(-1,${book.bid})"> - </a>
								</td>
								<c:set var='sum' value='${book.price*book.quantity}'></c:set>
								<td id="total${book.bid}" class='textmiddle'>￥<c:out value='${sum}'></c:out></td>
								<td class='textmiddle'><a href="<c:url value='/user/DeleteShoppingCartCtrl?bid=${book.bid}'/>">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="check" style="width:80%;height:10%;">
				<input type="checkbox" id="all" name="cbox" value="all" style="float:left">全选</input>
				<span id="totalPrice" style="margin-left:750px">
					<label>合计:</label>
					<label id="tp">0</label>
				</span>
				<button type="submit" class="tp_button" onclick="checkList()" style="width:20%;float:right">
					计算(
					<lable id="totalMoney">0</lable>
				)</button>
			</div>
		</c:if>
	</div>
	<div class="recomment">
		<div style="margin-left:-100px;">
			<p style="font-size:25px;color:#cfbebd;">猜你喜欢</p>
		</div>
		<%
				List<Book> list = (List<Book>)session.getAttribute("guessLikeList");
				if(list!=null){
				 for(int j = 0 ;j <list.size(); j++){
				Book book = list.get(j);
		%>
		<div class="gallery">
			
			 <div class="re-img">
			 	<img  src="<c:url value='<%= book.getImage()%>' />" style="width:70%;height:85%">
			</div>
			<div class="re-info">
			
				<a href="../BookInfoCtrl?bid=<%= book.getBid()%>" class="gallery-main-product-text"><%=book.getBname()%></a>
				<p class="gallery-book-time"><%=book.getAuthor() %></p>
				<p class="gallery-main-product-price"><i><span style="font-size:14px;color: #bf7f5f;">￥ </span>
				<span style="font-size:14px;color: #bf7f5f;"><%=book.getPrice()%></span>
				</i><span style="font-size:12px;color: gray">起</span></p>
			</div>
			<div style="weight:100%;height:1px;padding-top:-5px;background-color:yellow;float:left;"></div>
		</div>
		
		<%
		}
			}
		 %>
	</div>
</div>