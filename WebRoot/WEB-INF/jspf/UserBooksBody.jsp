<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.Book,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="panel-group" id="panel-818974">
				<c:if test="${empty orderMap }">
					<div align=center style="margin-top:100px;">
						<div style="width:200px;height:200px">
							<img src="<c:url value='/images/order.jpg' /> " style="width:100%;height:100%">
						</div>
						<div>
							<p style="font-size:30px;color:red">当前无订单</p>
						</div>
					</div>
				</c:if>
				  <c:forEach var="item" items='${orderMap}'>
					<div class="panel panel-default">
						<div class="panel-heading">
							 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-818974" href="#panel-element-${item.key.oid}" >
								 <div style='float:left;'>${item.key.commitTime} </div>
								 <div style='float:left;margin-left:100px;'>${item.key.oid}</div>
								 <div style='float:left;margin-left:100px;'>${item.key.amount}</div>
							 </a>
							 <br/>
						</div>
						<div id="panel-element-${item.key.oid}" class="panel-collapse collapse">
							<div class="panel-body">
								<table class="table table-hover">
									<thead>
										<tr style='background-color:#ccccff;height:50px'>
											<th>序号</th>
											<th>书名</th>
											<th>单价</th>
											<th>数量</th>
											<th>总价</th>
										</tr>
									</thead>
									<c:forEach var = "orderlist" items ='${item.value}'>
										<tr>
											<td>${orderlist.lid}</td>
											<td>${orderlist.descp}</td>
											<td>${orderlist.price}</td>
											<td>${orderlist.quantity}</td>
											<td>${orderlist.amount}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				 </c:forEach>	
			</div>			
		</div>
	</div>
</div>
<c:if test="${! empty orderMap}">
<div align=center>
	<a href='./getOrders?page=0'>首 页</a>
	<a href='./getOrders?page=prev' style="margin-left:50px">上一页</a>
	<a href='./getOrders?page=next' style="margin-left:50px">下一页</a>
	<a href='./getOrders?page=${requestScope.lastPage}' style="margin-left:50px">末 页</a>
</div>
</c:if>