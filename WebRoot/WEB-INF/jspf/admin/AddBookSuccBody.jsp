<%@ page import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
        Book book = (Book)request.getAttribute("book");
    %>
    <div align='center'>
    <h3>添加书籍成功</h3>
    <p>描述: ${book.bname}</p>
    <p>图片: ${book.image}</p>
    <p>作者: ${book.author}</p>
    <p>简介: ${book.descp}</p>
    <p>出版社: ${book.publisher}</p>
    <p>出版时间: ${book.publishTime}</p>
    <p>种类: ${book.type}</p>
    
    <!--<c:if test="${ticket.staus==0}">
		<c:set var='staus' value='已停售'></c:set>
	</c:if>
	<c:if test="${ticket.staus==1}">
		<c:set var='staus' value='售票中'></c:set>
	</c:if>
    <p>状态: <c:out value="${staus}"/></p>-->
    <p><a href='./BooksCtrl'>返回上一页</a></p>
	</div>