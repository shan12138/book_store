<%@ page import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
        Book book = (Book)request.getAttribute("book");
    %>
    <div align='center'>
    <h3>����鼮�ɹ�</h3>
    <p>����: ${book.bname}</p>
    <p>ͼƬ: ${book.image}</p>
    <p>����: ${book.author}</p>
    <p>���: ${book.descp}</p>
    <p>������: ${book.publisher}</p>
    <p>����ʱ��: ${book.publishTime}</p>
    <p>����: ${book.type}</p>
    
    <!--<c:if test="${ticket.staus==0}">
		<c:set var='staus' value='��ͣ��'></c:set>
	</c:if>
	<c:if test="${ticket.staus==1}">
		<c:set var='staus' value='��Ʊ��'></c:set>
	</c:if>
    <p>״̬: <c:out value="${staus}"/></p>-->
    <p><a href='./BooksCtrl'>������һҳ</a></p>
	</div>