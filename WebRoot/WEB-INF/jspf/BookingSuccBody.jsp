<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<p align=center>您已预订成功！</p>
	<br>
	<table align='center'>
		<tr>
			<td>订单号：
				<p class='err_msg'>${order.oid}</p>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>订单生成时间：
				<p class='err_msg'>${order.commitTime}</p>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>成交金额：
				<p class='err_msg'>${order.amount}元</p>
			</td>
		</tr>
	</table>
<div class="container-fluid" style='width:60%;align:center;'>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover" >
				<thead>
					<tr style='background-color:#ccccff;height:50px'>
						<th align="center">序号</th>
						<th align="center">描述</th>
						<th align="center">单价（元）</th>
						<th align="center">购票数(张）</th>
						<th align="center">总金额（元）</th>
					</tr>
				</thead>
				<tbody>
					<c:if test = "${shoppingList == null}">
						 <tr>
							 <td><p class='err_msg'>无订单</p></td>
						 </tr>
					 </c:if>
					 <c:if test = "${shoppingList!= null}">
					<c:forEach var="shoppingBook" items="${checkList}">
					<tr>
					 	<td>${shoppingBook.bid}</td>
					 	<td>${shoppingBook.bname}</td>
					 	<td>${shoppingBook.price}</td>	
					    <td>${shoppingBook.quantity}</td>
					    <td>${shoppingBook.amount}</td>
					</tr> 
					  </c:forEach>
					   <tr>
					     <td>总计</td>
					     <td>&nbsp;</td>
					     <td>&nbsp;</td>
					     <td> ${AmountToNum}</td>
					     <td> ${AmountToPrice}</td>
					   </tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>