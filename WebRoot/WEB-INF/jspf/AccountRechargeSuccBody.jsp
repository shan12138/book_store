<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
	<p style='margin-top:30px;text-align:center;font-size:25px;color:red;'>��ֵ�ɹ�!���γ�ֵ��Ϣ���£�</p>
<div class="container-fluid" style='width:60%;align:center;'>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover">
				<thead>
	            <th style="width: 15%">�û�����</th>
	            <th style="width: 15%">��ֵ��ʽ��</th>
	            <th style="width: 15%">��ֵ��</th>
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
		            <td>�˻���</td>
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
