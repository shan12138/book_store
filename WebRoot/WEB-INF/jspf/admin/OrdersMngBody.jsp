<%@page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.Book,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align='center'>
	<p class="err_msg">${errorMap['GLOBAL']}</p>
	<form action="./OrdersTimeCtrl" method="POST">
		<label>起始日期:</label>
		<input type="date" name="beginTime" value='${param.beginTime}' style="margin-left:50px">
		<label style="margin-left:50px">终止日期:</label>
		<input type="date" name="endTime" value='${param.endTime}' style="margin-left:50px">
		<input type="submit" name="inquire" class='form_submit' value="查询"></input>
	</form>
</div>
<div align='center'>
	<p class="err_msg">${errorMap['beginTime']}</p>
	<p class="err_msg">${errorMap['endTime']}</p>
	<p class="err_msg">${errorMap['wrong']}</p>
</div>
<div align='center'>
	<form action="./OrdersInfoCtrl" method="POST">
		<label>姓名/用户名:</label>
		<input type="text" name="name" value='${param.name}' style="margin-left:50px;">
		<label style="margin-left:50px">订单号:</label>
		<input type="text" name="oid" value='${param.oid}' style="margin-left:50px">
		<label style="margin-left:50px">身份证号码:</label>
		<input type="text" name="idCard" value='${param.idCard}' style="margin-left:50px">
		<input type="submit" name="inquire" class='form_submit' value="查询"></input>
	</form>
</div>	
<div align='center'>
	<p class="err_msg">${errorMap['oid']}</p>
	<p class="err_msg">${errorMap['wrong']}</p>
	<p class="err_msg">${errorMap['lengthError']}</p>
</div>		
<div class="container-fluid" style='width:80%;align:center;'>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover">
				<thead>
					<tr style='background-color:#ccccff;height:50px'>
						<th width="40">
							<b>编号</b>
						</th>
						<th width="50">
							<b>订单号</b>
						</th>
						<th width="200">
							<b>订单内容</b>
						</th>
						<th width="30">
							<b>成交时间</b>
						</th>
						<th width="30">
							<b>金额(元)</b>
						</th>
						<th width="50">
							<b>姓名</b>
						</th>
						<th width="50">
							<b>用户名</b>
						</th>
						<th width="50">
							<b>身份证号码</b>
						</th>
					</tr>
				</thead>
				<tbody>
					<%  int num = 1 + mapUtil.MAX_PAGE_LINES * ((OrderQueryState)session.getAttribute("OrderQueryState")).getCurPage(); %>
					<c:forEach var="order" items='${orderList}'>
				        <tr>
				            <td><%= num %></td>
				            <td>${order.oid}</td>
				            <td>${order.descp}</td>
				            <td>${order.commitTime}</td>
				            <td>${order.amount}</td>
				            <td>${order.name}</td>
				            <td>${order.userName}</td>
				            <td>${order.idCard}</td>
				            <% num++;%>
				        </tr>
			        </c:forEach>
			     </tbody>
			</table>
		</div>
	</div>
</div>
<div align='center'>
	<a href='./OrdersCtrl?page=0'>首 页</a>  
    <a href='./OrdersCtrl?page=prev' style="margin-left:50px">上一页</a>
    <a href='./OrdersCtrl?page=next' style="margin-left:50px">下一页</a>
    <a href='./OrdersCtrl?page=${requestScope.lastPage}' style="margin-left:50px">末 页</a>
</div>
	