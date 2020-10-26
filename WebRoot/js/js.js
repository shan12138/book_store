
function adjustInfor(urlStr) {
	$.ajax({
        type     : "post",
        async    : true,
        url      : "/EBP/user/GuessLike",
        dataType : "text",
        cache    : false,
        success  : function(data){
        	console.log(data);
        },
        error    : function(status){
        	console.log(status);
        }
    });
}
//---------------------�û���Ϣ����-----------------------------------
function resetInfor() {
	$("input").removeAttr("value");
}
	
//--------------------�޸���Ϣȷ�Ͽ�----------------------------------

//--------------------�û���Ϣ����֤--------------------------------
function checkUserInforForm(curform){
	var flagPassword = checkPassword(curform.password.value);
	var flagPasswordagain = checkPasswordMatch(curform.passwordagain.value);
	var flagIdCard = checkIdCard(curform.idCard.value);
	var flagUname = checkName(curform.uname.value);
	var flagTelNo = checkTelNo(curform.telNo.value);
   return (!flagPassword || !flagPasswordagain || !flagIdCard ||
  		!flagUname || !flagTelNo) ? false:confirm("ȷ���޸���Ϣ��");
}
function checkPassword(password) {
	alter(password);
	var passwordagain = document.getElementById("password").value;
	alter(passwordagain);
	var flag = ((password == "" && passwordagain=="" )||(password != "" && passwordagain!="" ));
	alter(flag);
	document.getElementById("passwordErr").innerHTML = flag ? "" : "���벻��Ϊ��";
	return flag;
}

function checkPasswordMatch(passwordAgain){
	var password =  document.getElementById("password").value;
	alter(password);
	var flag = (password == passwordAgain);
		document.getElementById("passwordAgainErr").innerHTML=flag ? "":"�������벻ƥ��";
	alter(flag);
	return flag;
}

function checkName(uname){
	var flag = (uname !="" && uname.length>=2 && uname.length<=8);
	console.log(flag);
		document.getElementById("unameErr").innerHTML=flag ? "":"����������2~8����֮��";
	return flag;
}

function checkIdCard(idCard) {
	var flag = (idCard != "" && idCard.length == 18);
	document.getElementById("idCardErr").innerHTML = flag ? "" : "���֤�ų�������";
	return flag;
}

function checkTelNo(telNo) {
	var flag = (telNo == "" || (telNo.length<18 && telNo.length>=3));
	document.getElementById("telNoErr").innerHTML = flag ? "" : "�绰��������";
	return flag;
}
//--------------------------------�ϴ�ͼƬ---------------------------------
//ͬ��Ԥ��
function upload(obj){
	 var file = obj.files[0];               
     var reader = new FileReader();
     reader.onloadstart = function (e) {
        console.log("��ʼ��ȡ....");
     }
       reader.onprogress = function (e) {
            console.log("���ڶ�ȡ��....");
     }
     reader.onabort = function (e) {
        console.log("�ж϶�ȡ....");
     }
     reader.onerror = function (e) {
         console.log("��ȡ�쳣....");
     }
     reader.onload = function (e) {
         console.log("�ɹ���ȡ....");
     var img = document.getElementById("avarimgs");
         img.src = e.target.result;
         console.log(img.src);
      //���� img.src = this.result;  //e.target == this
         changeImage(obj);
     }
         reader.readAsDataURL(file);
         
}
//�첽����ͼƬurl
function changeImage(obj){
    var file = obj.files[0];
    
    if(file == null)
    	console.log(file);
    var formData = new FormData();
    formData.append('images', file);
    
    $.ajax({
        url: "/EBP/user/ImageUploadCtrl",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
       // mimeType: "multipart/form-data",
        success: function (data) {
		        	if(data == null || data.trim() == "" || data.trim() == "fall"){
		        		return;
		        	}
		        	var Url = data.trim();
		        	console.log($("#images").text());
		        	$("#images").attr("value",Url);
		        	
        		},
        error: function (data) {
            console.log(data);
        }
    });
}
//-----------------------���ﳵ-----------------------------
function count(flag,bid){
	
	//ȡ���������ֵ
	var curCount = $("#num"+bid).val();
	if(curCount == "1" && flag == -1)return;
	//ȡ������
	var price = $("#price"+bid).html();
	//��ȡ���
	var newPrice = Number(price.substring(1));
	//��������
	var newCount = Number(curCount)+flag;
	var newtotal = newPrice * newCount;
	//������д��
	$("#num"+bid).val(newCount);
	$("#total"+bid).html("��"+newtotal.toFixed(2));
	
	//�жϵ�ǰ���Ƿ�ѡ��
	var checkbox = document.getElementById("cb"+bid);
	if(checkbox.checked==true){
		var curtotalmoney = $("#tp").html();
		var newtotalmoney = Number(curtotalmoney)+newPrice*flag;
		$("#tp").html(newtotalmoney);
	}
}

//--------------------����-----------------------
//�ؼ���
window.onload=function(){
	if(Number($("#tp").html())==0){
		var cboxs = $("input[type='checkbox']");
		for(var i=0;i<cboxs.length;i++){
			if(cbox[i].checked==true){
				if(cbox.value=="all"){
					countPrice(cboxs[i].value,1);
				}else{
					selectAll();
				}
			}
		}
		
	}
}

$("document").ready(function(){
	$("input[type='checkbox']").click(function(e){
		var cboxs=e.target;
			if(cboxs.checked==true){
				if(cboxs.value != "all"){
					countPrice(cboxs.value,1);
				}
				else{
					selectAll();
				}
			}
			else{
				if(cboxs.value != "all"){
					countPrice(cboxs.value,-1);
				}
				else{
					selectNone();
				}
			}
	});
});
	
function countPrice(bid,flag){
	//ȡ��ѡ�в�Ʒ���ܽ��
	var price = $("#total"+bid).html();
	//ȡ�������Ʒ�ļ���
	var count = $("#totalMoney").html();
	//ȡ����ǰ�ܽ��
	var curTotalPrice = $("#tp").html();
	//ת������//����
	var newTotalPrice = Number(curTotalPrice)+Number(price.substring(1))*flag;
	if(newTotalPrice <= 0){
		$("#tp").html(0);
		return;
	}
	//ȡ��λС��//д��
	$("#tp").html(newTotalPrice.toFixed(2));
	$("#totalMoney").html(Number(count)+flag);
}

function selectAll(){
	var cboxs = $("input[type='checkbox']");
	var newTotalPrice=0;
	var count=0;
	console.log(cboxs.length);
	//ѭ��ȡ�����е�����Ʒ�ܽ��
	for(var i=0;i<cboxs.length;i++){
		if(cboxs[i].value!="all"){
			cboxs[i].checked=true;
			var bid=cboxs[i].value;
			//����ת���Ӻ�
			newTotalPrice += Number($("#total"+bid).html().substring(1));
			//����
			count++;
		}
	}
	//д��
	$("#tp").html(newTotalPrice.toFixed(2));
	$("#totalMoney").html(count);
}

function selectNone(){
	var cboxs = $("input[type='checkbox']");
	for(var i=0;i<cboxs.length;i++){
		cboxs[i].checked=false;
	}
	//д��
	$("#tp").html(0);
	$("#totalMoney").html(0);
}

//-------------------------��ȡ��ѡ����Ʒ------------------------------
function checkList(){
	var lists="";
	var cboxs = $("input[type='checkbox']");
	for(var i=0;i<cboxs.length;i++){
		if(cboxs[i].value!="all" && cboxs[i].checked == true){
			//��ȡid
			var bid=cboxs[i].value;
			//��ȡ����
			var num = $("#num"+bid).val();
			//����ת��//���ɶ���
			var checkThing =bid+","+num;
			lists += checkThing; 
			lists += ";";
			};
			//��������
			
	}
	console.log(lists);
	//FormData formdata = 
	if(confirm("ȷ�Ϲ�����")){
		$.ajax({
			type     : "post",
	        async    : true,
	        url      : "/EBP/user/SendListCtrl",
	        data     : {lists:lists},
	        dataType : "text",
	        cache    : false,
	        success  : function(data){
	        	if(data.trim() !="" && data !=null){
	        		var msg =data.trim();
	        		if(msg=="succ")
	        			window.location.href='/EBP/user/BookingSucc.jsp';//�ĳ���ĳɹ�ҳ��ĵ�ַ
	        		else
	        			alter("����ʧ��");
	        		
	        	}
	        		console.log(data);
	        },
	        error    :function(status){
	        	console.log(status);
	        	console.log("worng");
	        }
		});
	}
}
//--------------------------���빺�ﳵ----------------------------------
function addCart(cartUrl){
	$.ajax({
		 type     	 : "get",
	        async    : true,
	        url      : cartUrl,
	        dataType : "text",
	        cache    : false,
	        success  : function(data){
	        			var flag = data.trim();
	        			if(flag!=""){
	        				if(flag == "succ")
	        					alert("��ӹ��ﳵ�ɹ�");
	        				else
	        					alert("��ѽ��С���ȵ�¼��");
	        			}
	        },
	        error    :function(status){
	        	console.log(status);
	        }
	});
}