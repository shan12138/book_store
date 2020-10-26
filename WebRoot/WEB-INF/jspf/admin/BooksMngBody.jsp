<%@page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.Book,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<p class='err_msg'>${errorMap['GLOBAL']}</p>
	 <div align='center'>
		<form action="./BooksCtrl" method="POST">
			<label>起始日期:</label>
			<input style="margin-left: 30px;" type="date" name="beginTime" value='${param.beginTime}'>
			<label style="margin-left: 30px;">终止日期:</label>
			<input style="margin-left: 30px;" type="date" name="endTime" value='${param.endTime}'>
			<label style="margin-left: 30px;">作者/书名</label>
			<input style="margin-left: 30px;" type="text" name="keyWord" value='${param.keyWord}'>
			<input style="margin-left: 30px; background-color:#ccffff;width: 80px; height: 30px;" type="submit" name="inquire" value="查询"></input>
			<a style="margin-left:50px;" href='./AddBook.jsp'>添加书籍</a>
		</form>
			<p class='err_msg'>${errorMap['beginTime']}</p>
			<p class='err_msg'>${errorMap['endTime']}</p>
			<p class='err_msg'>${errorMap['wrong']}</p>
		</div>
		<div class="container-fluid" style='width:80%;align:center;'>
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-hover">
						<thead>
							<tr style='background-color:#ccccff;height:50px'>
								<th style='width:5%'>
									<b>编号</b>
								</th>
								<th style='width:5%'>
									<b>书名</b>
								</th>
								<th style='width:10%'>
									<b>图片</b>
								</th>
								<th style='width:5%'>
									<b>作者</b>
								</th>
								<th style='width:40%'>
									<b>简介</b>
								</th>
								<th style='width:5%'>
									<b>单价(元)</b>
								</th>
								<th style='width:5%'>
									<b>出版社</b>
								</th>
								<th style='width:5%'><b>出版时间</b></th>
								<th style='width:8%'><b>种类</b></th>
								<th style='width:8%'><b>备注</b></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="book" items='${booksList}'>
							    <tr>
						            <td>${book.bid}</td>
						         	<td>${book.bname}</td>
								    <td>
								    	<div style="width:80px;height:80px;">
								    		<img src='<c:url value="${book.image}" />' style="width:100%;height:100%;"/>
								    	</div>
								    </td>
								    <td>${book.author}</td>
								    <td>${book.descp}</td>
								    <td>${book.price}</td>
								    <td>${book.publisher}</td>
								    <td>${book.publishTime}</td>
								    <td>${book.type}</td>
								    <td>
				                       <a href='./UpdateBookCtrl?bid=${book.bid}'>修改</a>
					                </td>
								 </tr>
							 </c:forEach>
						</tbody>
				</table>
			</div>
		</div>
	</div>
	<div align='center'>
	<a href='./BooksCtrl?page=0'>首 页</a>
    <a href='./BooksCtrl?page=prev' style="margin-left:50px">上一页</a>
    <a href='./BooksCtrl?page=next' style="margin-left:50px">下一页</a>
    <a href='./BooksCtrl?page=${requestScope.lastPage}' style="margin-left:50px">末 页</a>
    </div>
	    