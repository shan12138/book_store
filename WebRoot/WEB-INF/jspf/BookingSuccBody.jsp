<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<p align=center>����Ԥ���ɹ���</p>
	<br>
	<table align='center'>
		<tr>
			<td>�����ţ�
				<p class='err_msg'>${order.oid}</p>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>��������ʱ�䣺
				<p class='err_msg'>${order.commitTime}</p>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>�ɽ���
				<p class='err_msg'>${order.amount}Ԫ</p>
			</td>
		</tr>
	</table>
<div class="container-fluid" style='width:60%;align:center;'>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover" >
				<thead>
					<tr style='background-color:#ccccff;height:50px'>
						<th align="center">���</th>
						<th align="center">����</th>
						<th align="center">���ۣ�Ԫ��</th>
						<th align="center">��Ʊ��(�ţ�</th>
						<th align="center">�ܽ�Ԫ��</th>
					</tr>
				</thead>
				<tbody>
					<c:if test = "${shoppingList == null}">
						 <tr>
							 <td><p class='err_msg'>�޶���</p></td>
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
					     <td>�ܼ�</td>
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