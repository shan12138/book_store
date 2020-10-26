<%@page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.Book,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="book2" scope="request" class="com.hgd.ebp.domain.Book" />
    <form:form modelAttribute="book" action="./UpdateBookCtrl" method="POST" class='ticketForm'
    onsubmit="return checkUpdateBookForm(this)">
        <p class='err_msg'><form:errors /></p>
        <form:hidden path="bid"/>
        <table class='Form' align='center'>
            <tr>
                <td style='text-align:right'><p>书名:</p></td>
                <td>
                    <form:input path="bname" onBlur="checkBname(this.value)"/>
                </td>
                <td style='text-align:left'>
                   <p class='err_msg' id='bnameErr'><form:errors path="bname"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'><p>图片:</p></td>
                <td>
                    <div style="width:200px;height:100px;margin-left:50px;">
						<img id="bookimgs" src='<c:url value="${book.image}"/>' 
									class="rounded" style="width:100%;height:100%;">
					</div>
					<input type="file" align="bottom" onchange="upload(this)"/>
					<form:input path="image" type='hidden' />
                </td>
                <td style='text-align:left'>
                    <p class='err_msg' id='imageErr'><form:errors path="image"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'><p>作者:</p></td>
                <td>
                    <form:input path="author" onBlur="checkAuthor(this.value)"/>
                </td>
                <td style='text-align:left'>
                    <p class='err_msg' id='authorErr'><form:errors path="author"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'><p>简介:</p></td>
                <td>
                    <form:input path="descp" onBlur="checkDescp(this.value)"/>
                </td>
                <td style='text-align:left'>
                     <p class='err_msg' id='descpErr'><form:errors path="descp"/></p>
                </td>
            </tr>
             <tr>
                <td style='text-align:right'><p>单价:</p></td>
                <td>
                    <form:input path="price" onBlur="checkPrice(this.value)"/>
                </td>
                <td style='text-align:left'>
                    <p class='err_msg' id='priceErr'><form:errors path="price"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'><p>出版社:</p></td>
                <td>
                    <form:input path="publisher" onBlur="checkPublisher(this.value)"/>
                </td>
                <td style='text-align:left'>
                    <p class='err_msg' id='publisherErr'><form:errors path="publisher"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'><p>出版时间:</p></td>
                <td>
                    <form:input type='date' path="publishTime" onBlur="checkPublishTime(this.value)"/>
                </td>
                <td style='text-align:left'>
                    <p class='err_msg' id='publishTimeErr'><form:errors path="publishTime"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'><p>种类:</p></td>
                <td>
                    <form:select path='type' onBlur="checkType(this.value)" class='bookType'>
					  <option value ="散文" selected>散文</option>
					  <option value ="小说">小说</option>
					  <option value="教育">教育</option>
					  <option value="游记">游记</option>
					  <option value="工具书">工具书</option>
					  <option value="历史">历史</option>
					</form:select>
                </td>
                <td style='text-align:left'>
                    <p class='err_msg' id='typeErr'><form:errors path="type"/></p>
                </td>
            </tr>
            <tr>
                <td style='text-align:right'></td>
                <td>
                	<input type=submit value='修改' style="width:70px;background:#ccffff"/>
                	<input type=reset value='重置' style="margin-left:50px;width:70px;background:#ccffff"/>
                </td>
            </tr>
        </table>
    </form:form>