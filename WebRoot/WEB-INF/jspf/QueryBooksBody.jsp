<%@ page language="java" import="java.util.*,com.hgd.ebp.domain.*,com.hgd.ebp.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="index_xinshu">
	<div class="ancient-box">
		<div class="ancient-title">
			<!--�������ƺ�-->
			<div class="floor-big-title clearfix">
				<div class='floor-big-title-book'>
					<div class='col-md-6'></div>
					<div class='col-md-3'>
						<form action='./ListBooksCtrl' method=POST>
							<div class="input-group" >
								<input type="text" class="form-control input-lg" placeholder='��������/����' name='keyWord' >
								<input type='submit' value='����' class="input-group-addon btn btn-primary" />
							</div>
						</form>
					</div>
					<div class='col-md-2'>${errorMap['keyWord']}</div>
				</div>
				<div class='floor-big-title-line'>
					<hr style="width:100%;height:100%;">
				</div>
			</div>
		</div>
		<div class="ancient-body">
			<%
				List<Book> list = (List<Book>)request.getAttribute("QueryBookList");
				for(int i = 0; i < (list.size() / 3 + 1); i++){
			%>
				<div class="gallery-main-product-xinshu clearfix" style="width:100%;height:400px;margin-left:100px">
			<% 	for(int j = 0 ;j < 3 ; j++){
				if( (i * 3 + j) == list.size())
					break;
				Book book = list.get( i * 3 + j);
			%>
				<div class="gallery-lot-slide">
					<div class="gallery-book-list-img">
					 <a href="BookInfoCtrl?bid= <%= book.getBid()%>">
					 <img class='img' src="<c:url value='<%= book.getImage()%>' />"></a>
					</div>
					<div class="gallery-book-list-info">
						<a href="BookInfoCtrl?bid= <%= book.getBid()%>" class="gallery-main-product-text" title="20���͵��������޶��棩"><%=book.getBname() %></a>
						<p class="gallery-book-time"><%= book.getAuthor() %></p>
						<p class="gallery-main-product-price"><i><span style="font-size:14px;color: #bf7f5f;">�� </span>
						<span style="font-size:14px;color: #bf7f5f;"><%= book.getPrice() %></span>
						</i><span style="font-size:12px;color: gray">��</span></p>
					</div>
				</div>
			<%}%>
			</div>
			<%}%>
		</div>
		<div style="clear:both;"></div>
	</div>
</div>
<div align=center>
	<a href='./ListBooksCtrl?page=0'>�� ҳ</a>
	<a href='./ListBooksCtrl?page=prev' style="margin-left:50px">��һҳ</a>
	<a href='./ListBooksCtrl?page=next' style="margin-left:50px">��һҳ</a>
	<a href='./ListBooksCtrl?page=${requestScope.lastPage}' style="margin-left:50px">ĩ ҳ</a>
</div>
