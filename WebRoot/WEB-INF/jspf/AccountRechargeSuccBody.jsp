<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
	<p style='margin-top:30px;text-align:center;font-size:25px;color:red;'>充值成功!本次充值信息如下：</p>
<div class="container-fluid" style='width:60%;align:center;'>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover">
				<thead>
	            <th style="width: 15%">用户名：</th>
	            <th style="width: 15%">充值方式：</th>
	            <th style="width: 15%">充值金额：</th>
	        </thead>
	        <tbody>
	       		<tr style='background-color:#ccccff;height:50px'>
		        	<td style="width: 15%">
			            ${requestScope.ChargeBean.uname}
			        </td>
		             <td style="width: 20%">
		            	${requestScope.ChargeBean.chargestyle}
		            </td>
		            <td style="width: 20%">
		            	${requestScope.ChargeBean.chargemoney}
		            </td>
		       </tr>
		       <tr>
		            <td>账户余额：</td>
		            <td style="color: red">
		            	${requestScope.ChargeBean.surplusmoney}
		            </td>	
		            <td></td>
	     		</tr>
     		</tbody>
  		  </table>
  		 </div>
	</div>
</div>
