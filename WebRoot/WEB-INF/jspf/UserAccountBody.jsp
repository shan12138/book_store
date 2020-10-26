<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
			<div class="container">
				<div  class="row" style="height:80%;text-align:center">
					<div style="width:50%;margin-top:100px;margin-left:100px" >
						<form id="accountform" action="./UserAccount" method="POST" >
			    			<div class="error">
			    				${errorMap['GLOBAL'] }
			    			</div>
			    			<div id="table">
			    			<table id="accounttable" class="table table-sm">
			            		<tr>
			                		<td>用户名： </td>
			                		<td>
			                			<font name="uname">${ChargeBean.uname}</font> 
			               			</td>
			           			</tr>
			            		<tr>
			                		<td>账户余额： </td>
			                		<td>
			                			<font name="surplusmoney">${ChargeBean.surplusmoney}</font> 
			               		 	</td>
			            		</tr>
					            <tr>
					                <td>充值方式： </td>
					                <td>
					                    <select name="styles">
					                        <option  value="空中充值" selected="selected">空中充值</option>
					                        <option  value="支付宝">支付宝</option>
					                        <option  value="微信">微信</option>
					                        <option  value="京东">京东</option>
					                    </select>
					                </td>
					            </tr>
					            <tr>
					                <td>充值金额： </td>
					                <td>
					                    <input name="chargemoney" autofocus="true" value="100" width="15px" palceholder="充值金额在100-2000之间"/>元
					                </td>
					                <td class="error">
					                	 ${errorMap['money'] }
					                </td>
					            </tr>
					            </table>
			    			</div>
			        		<div >
			        			 <button id="chargebutton" type="submit" name="charge" style="height:50px">充值</button>
			        		</div>
			    	</form>
			    </div>
				</div>
			 </div>