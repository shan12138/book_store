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
			                		<td>�û����� </td>
			                		<td>
			                			<font name="uname">${ChargeBean.uname}</font> 
			               			</td>
			           			</tr>
			            		<tr>
			                		<td>�˻��� </td>
			                		<td>
			                			<font name="surplusmoney">${ChargeBean.surplusmoney}</font> 
			               		 	</td>
			            		</tr>
					            <tr>
					                <td>��ֵ��ʽ�� </td>
					                <td>
					                    <select name="styles">
					                        <option  value="���г�ֵ" selected="selected">���г�ֵ</option>
					                        <option  value="֧����">֧����</option>
					                        <option  value="΢��">΢��</option>
					                        <option  value="����">����</option>
					                    </select>
					                </td>
					            </tr>
					            <tr>
					                <td>��ֵ�� </td>
					                <td>
					                    <input name="chargemoney" autofocus="true" value="100" width="15px" palceholder="��ֵ�����100-2000֮��"/>Ԫ
					                </td>
					                <td class="error">
					                	 ${errorMap['money'] }
					                </td>
					            </tr>
					            </table>
			    			</div>
			        		<div >
			        			 <button id="chargebutton" type="submit" name="charge" style="height:50px">��ֵ</button>
			        		</div>
			    	</form>
			    </div>
				</div>
			 </div>