<%@page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.hgd.ebp.util.ErrMap"%>
<%@page import="com.hgd.ebp.domain.Book,com.hgd.ebp.state.*,com.hgd.ebp.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align='center'>

<form action="./keyTimeCtrl" method=post>
	<p class='err_msg'>${errorMap.get("GLOBAL")}</p>
	<label>��ʼ����:</label>
	<input type="date" name="beginTime" value='${param.beginTime}' style="margin-left:50px"/>
	<label style="margin-left:50px">��ֹ����:</label>
	<input type="date" name="endTime" value='${param.endTime}' style="margin-left:50px"/>
	<input type="submit" name="inquire" class='form_submit' value="��ѯ" />
	<p class='err_msg'>${requestScope.errorMap['timeRange']}</p>
	<p class='err_msg'>${requestScope.errorMap['empty']}</p>
</form>
<form action="./keyUserinforCtrl" method=post align='center'>
	<label>����/�û���:</label>
	<input type="text" name="name" value='${param.name}' style="margin-left:50px"></input>
	<label style="margin-left:50px">���֤����:</label>
	<input type="text" name="idCard" value='${param.idCard}' style="margin-left:50px"></input>
	<label style="margin-left:50px">��ϵ�绰:</label>
	<input type="text" name="telNo" value='${param.telNo}' style="margin-left:50px"></input>
	<input type="submit" value="��ѯ" class='form_submit'/>
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
						<th width="40">���</th>
						<th width="50">����</th>
						<th width="60">�û���</th>
						<th width="20">�Ա�</th>
						<th width="80">���֤��</th>
						<th width="20">����</th>
						<th width="50">ͷ��</th>
						<th width="30">��ϵ�绰</th>
						<th width="30">ע��ʱ��</th>
						<th width="30">�˻����</th>
						<th width="30">״̬</th>
						<th width="40">��ע</th>
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
									${usersinfor.status==1 ? "�" : "����"}
								</span>
							</td>
							<td>
								<c:url var="changeUrl" value='./changeStatusCtrl?uid=${usersinfor.uid}&status='></c:url>
								<button id="setStatus${usersinfor.uid}" onclick="changeStatu('${changeUrl}',${usersinfor.uid})">${usersinfor.status==1 ? "����" : "�" }</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div align='center'>
	<a href='./UsermangeCtrl?page=0' >�� ҳ</a>
	<a href='./UsermangeCtrl?page=prev' style="margin-left:50px">��һҳ</a>
	<a href='./UsermangeCtrl?page=next' style="margin-left:50px">��һҳ</a>
	<a href='./UsermangeCtrl?page=${requestScope.lastPage}' style="margin-left:50px">ĩ ҳ</a>
</div>
