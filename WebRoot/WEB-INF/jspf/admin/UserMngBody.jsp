<%@page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.Book,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align='center'>

<form action="./keyTimeCtrl" method=post>
	<p class='err_msg'>${errorMap.get("GLOBAL")}</p>
	<label>起始日期:</label>
	<input type="date" name="beginTime" value='${param.beginTime}' style="margin-left:50px"/>
	<label style="margin-left:50px">终止日期:</label>
	<input type="date" name="endTime" value='${param.endTime}' style="margin-left:50px"/>
	<input type="submit" name="inquire" class='form_submit' value="查询" />
	<p class='err_msg'>${requestScope.errorMap['timeRange']}</p>
	<p class='err_msg'>${requestScope.errorMap['empty']}</p>
</form>
<form action="./keyUserinforCtrl" method=post align='center'>
	<label>姓名/用户名:</label>
	<input type="text" name="name" value='${param.name}' style="margin-left:50px"></input>
	<label style="margin-left:50px">身份证号码:</label>
	<input type="text" name="idCard" value='${param.idCard}' style="margin-left:50px"></input>
	<label style="margin-left:50px">联系电话:</label>
	<input type="text" name="telNo" value='${param.telNo}' style="margin-left:50px"></input>
	<input type="submit" value="查询" class='form_submit'/>
	<p class="err_msg">${requestScope.errorMap['lengthError']}</p>
	<p class="err_msg">${requestScope.errorMap['emptyError']}</p>
</form>
</div>
<div class="container-fluid" style='width:80%;align:center;'>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover">
				<thead>
					<tr style='background-color:#ccccff;height:50px'>
						<th width="40">编号</th>
						<th width="50">姓名</th>
						<th width="60">用户名</th>
						<th width="20">性别</th>
						<th width="80">身份证号</th>
						<th width="20">年龄</th>
						<th width="50">头像</th>
						<th width="30">联系电话</th>
						<th width="30">注册时间</th>
						<th width="30">账户余额</th>
						<th width="30">状态</th>
						<th width="40">备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usersinfor" items="${requestScope.userList}" >
						<tr>
							<td>
								${usersinfor.uid}
							</td>
							<td>
								${usersinfor.uname}
							</td>
							<td>
								${usersinfor.userName}
							</td>
							<td>
								${usersinfor.gender}
							</td>
							<td>
								${usersinfor.idCard}
							</td>
							<td>26</td>
							<td>
								<img src="<c:url value='${usersinfor.images}' /> " width="50">
							</td>
							<td>
								${usersinfor.telNo}
							</td>
							<td>
								${usersinfor.regTime}
							</td>
							<td>
								<p class='err_msg'>${usersinfor.balance}</p>
							</td>									
							<td>
								<span id="showStatus${usersinfor.uid}">
									${usersinfor.status==1 ? "活动" : "禁用"}
								</span>
							</td>
							<td>
								<c:url var="changeUrl" value='./changeStatusCtrl?uid=${usersinfor.uid}&status='></c:url>
								<button id="setStatus${usersinfor.uid}" onclick="changeStatu('${changeUrl}',${usersinfor.uid})">${usersinfor.status==1 ? "禁用" : "活动" }</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div align='center'>
	<a href='./UsermangeCtrl?page=0' >首 页</a>
	<a href='./UsermangeCtrl?page=prev' style="margin-left:50px">上一页</a>
	<a href='./UsermangeCtrl?page=next' style="margin-left:50px">下一页</a>
	<a href='./UsermangeCtrl?page=${requestScope.lastPage}' style="margin-left:50px">末 页</a>
</div>
