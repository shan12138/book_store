<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="container">
		<div id="main-header" class="row">
			<h1>��Ա��Ϣ</h1>
		</div>
		<div class="row" style="height:60%">
			<div  class="col-md-10 col-md-offset-4">
				<jsp:useBean id="RenewUserBean" scope="request" class="com.hgd.ebp.vi.RenewUserBean" />
				<form:form modelAttribute="RenewUserBean" action="./ChangeUserInforCtrl" method="POST" 
				onsubmit="return checkUserInforForm(this)">
					<span class="error">
						<form:errors/>
					</span>
					<table id="userinfor" class="table .table-condensed">
						<tbody>
							<tr>
								<td style="width: 25%" rowspan="3">
									<img id="avarimgs" src='<c:url value="${RenewUserBean.images}"/>' alt="${RenewUserBean.images}" 
									class="rounded" style="width:90%;max-width:95%">
									<input type="file" align="bottom" onchange="upload(this)"/>
									<form:input path="images" type='hidden' />
								</td>
								<td style="width: 13%">�û����� </td>
								<td style="text-align: left;width: 25%">
									${requestScope.RenewUserBean.userName}
								</td>
								<td style="width: 12%">�˻���</td>
								<td>
									${requestScope.RenewUserBean.balance}
								</td>
							</tr>
							<tr>
								<td>��&nbsp;&nbsp;&nbsp;&nbsp;�룺 </td>
								<td >
									<form:password path="password" autofocus="true"
									 placeholder="���������Ӧ��8-20�ַ�" 
									 onBlur="checkPassword(this.value)"/>
								</td>
								<td  colspan="2">
									<span id="passwordErr" class="error">
										<form:errors path="password" />
									</span>
								</td>
							</tr>
							<tr>
								<td>����ȷ�ϣ� </td>
								<td>
									<form:password path="passwordagain" 
									 placeholder="���������Ӧ��8-20�ַ�" onBlur="checkPasswordMatch(this.value)"/>
								</td>
								<td colspan="2">
									<span id="passwordAgainErr" class="error">
										<form:errors path="passwordagain" />
									</span>
								</td>
							</tr>
							<tr>
								<td ></td>
								<td >��ʵ������ </td>
								<td  >
									<form:input path="uname" 
									onBlur="checkName(this.value)"/>
								</td>
								<td  colspan="2">
									<span id="unameErr" class="error">
										<form:errors path="uname" />
									</span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>��&nbsp;&nbsp;&nbsp;&nbsp;�� </td>
								<td>
									<form:radiobutton path="gender" value="��" label="��" /> &nbsp;&nbsp;&nbsp;&nbsp;
									<form:radiobutton path="gender" value="Ů" label="Ů" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td>���֤�ţ� </td>
								<td>
									<form:input path="idCard" onBlur="checkIdCard(this.value)" />
								</td>
								<td colspan="2">
									<span id="idCardErr" class="error">
										<form:errors path="idCard" />
									</span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>��&nbsp;&nbsp;&nbsp;&nbsp;�䣺 </td>
								<td>26</td>
							</tr>
							<tr>
								<td></td>
								<td>ͨѶ��ַ�� </td>
								<td>
									<form:input path="address" placeholder="��������ϸ��ַ"
									/>
								</td>
								<td  colspan="2">
									<span id="addressErr" class="error">
										<form:errors path="address" />
									</span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>��ϵ�绰�� </td>
								<td>
									<form:input type="tel" path="telNo" onBlur="checkTelNo(this.value)"/>
								</td>
								<td  colspan="2" >
									<span id="telNoErr" class="error">
										<form:errors path="telNo" />
									</span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>&nbsp;&nbsp;
									<input type="submit" value='�޸�'/>
								</td>
								<td>&nbsp;&nbsp;
									<button type="button" onclick="resetInfor()">����</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
