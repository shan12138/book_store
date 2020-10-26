<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="container">
		<div id="main-header" class="row">
			<h1>会员信息</h1>
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
								<td style="width: 13%">用户名： </td>
								<td style="text-align: left;width: 25%">
									${requestScope.RenewUserBean.userName}
								</td>
								<td style="width: 12%">账户余额：</td>
								<td>
									${requestScope.RenewUserBean.balance}
								</td>
							</tr>
							<tr>
								<td>密&nbsp;&nbsp;&nbsp;&nbsp;码： </td>
								<td >
									<form:password path="password" autofocus="true"
									 placeholder="输入的密码应在8-20字符" 
									 onBlur="checkPassword(this.value)"/>
								</td>
								<td  colspan="2">
									<span id="passwordErr" class="error">
										<form:errors path="password" />
									</span>
								</td>
							</tr>
							<tr>
								<td>密码确认： </td>
								<td>
									<form:password path="passwordagain" 
									 placeholder="输入的密码应在8-20字符" onBlur="checkPasswordMatch(this.value)"/>
								</td>
								<td colspan="2">
									<span id="passwordAgainErr" class="error">
										<form:errors path="passwordagain" />
									</span>
								</td>
							</tr>
							<tr>
								<td ></td>
								<td >真实姓名： </td>
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
								<td>性&nbsp;&nbsp;&nbsp;&nbsp;别： </td>
								<td>
									<form:radiobutton path="gender" value="男" label="男" /> &nbsp;&nbsp;&nbsp;&nbsp;
									<form:radiobutton path="gender" value="女" label="女" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td>身份证号： </td>
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
								<td>年&nbsp;&nbsp;&nbsp;&nbsp;龄： </td>
								<td>26</td>
							</tr>
							<tr>
								<td></td>
								<td>通讯地址： </td>
								<td>
									<form:input path="address" placeholder="请输入详细地址"
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
								<td>联系电话： </td>
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
									<input type="submit" value='修改'/>
								</td>
								<td>&nbsp;&nbsp;
									<button type="button" onclick="resetInfor()">重置</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
