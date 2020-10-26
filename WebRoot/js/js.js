
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
//---------------------用户信息重置-----------------------------------
function resetInfor() {
	$("input").removeAttr("value");
}
	
//--------------------修改信息确认框----------------------------------

//--------------------用户信息表单验证--------------------------------
function checkUserInforForm(curform){
	var flagPassword = checkPassword(curform.password.value);
	var flagPasswordagain = checkPasswordMatch(curform.passwordagain.value);
	var flagIdCard = checkIdCard(curform.idCard.value);
	var flagUname = checkName(curform.uname.value);
	var flagTelNo = checkTelNo(curform.telNo.value);
   return (!flagPassword || !flagPasswordagain || !flagIdCard ||
  		!flagUname || !flagTelNo) ? false:confirm("确认修改信息吗？");
}
function checkPassword(password) {
	alter(password);
	var passwordagain = document.getElementById("password").value;
	alter(passwordagain);
	var flag = ((password == "" && passwordagain=="" )||(password != "" && passwordagain!="" ));
	alter(flag);
	document.getElementById("passwordErr").innerHTML = flag ? "" : "密码不能为空";
	return flag;
}

function checkPasswordMatch(passwordAgain){
	var password =  document.getElementById("password").value;
	alter(password);
	var flag = (password == passwordAgain);
		document.getElementById("passwordAgainErr").innerHTML=flag ? "":"两次密码不匹配";
	alter(flag);
	return flag;
}

function checkName(uname){
	var flag = (uname !="" && uname.length>=2 && uname.length<=8);
	console.log(flag);
		document.getElementById("unameErr").innerHTML=flag ? "":"姓名长度在2~8个字之间";
	return flag;
}

function checkIdCard(idCard) {
	var flag = (idCard != "" && idCard.length == 18);
	document.getElementById("idCardErr").innerHTML = flag ? "" : "身份证号长度有误";
	return flag;
}

function checkTelNo(telNo) {
	var flag = (telNo == "" || (telNo.length<18 && telNo.length>=3));
	document.getElementById("telNoErr").innerHTML = flag ? "" : "电话号码有误";
	return flag;
}
//--------------------------------上传图片---------------------------------
//同步预览
function upload(obj){
	 var file = obj.files[0];               
     var reader = new FileReader();
     reader.onloadstart = function (e) {
        console.log("开始读取....");
     }
       reader.onprogress = function (e) {
            console.log("正在读取中....");
     }
     reader.onabort = function (e) {
        console.log("中断读取....");
     }
     reader.onerror = function (e) {
         console.log("读取异常....");
     }
     reader.onload = function (e) {
         console.log("成功读取....");
     var img = document.getElementById("avarimgs");
         img.src = e.target.result;
         console.log(img.src);
      //或者 img.src = this.result;  //e.target == this
         changeImage(obj);
     }
         reader.readAsDataURL(file);
         
}
//异步传回图片url
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
//-----------------------购物车-----------------------------
function count(flag,bid){
	
	//取出计数框的值
	var curCount = $("#num"+bid).val();
	if(curCount == "1" && flag == -1)return;
	//取出单价
	var price = $("#price"+bid).html();
	//截取金额
	var newPrice = Number(price.substring(1));
	//计算数据
	var newCount = Number(curCount)+flag;
	var newtotal = newPrice * newCount;
	//新数据写回
	$("#num"+bid).val(newCount);
	$("#total"+bid).html("￥"+newtotal.toFixed(2));
	
	//判断当前框是否被选中
	var checkbox = document.getElementById("cb"+bid);
	if(checkbox.checked==true){
		var curtotalmoney = $("#tp").html();
		var newtotalmoney = Number(curtotalmoney)+newPrice*flag;
		$("#tp").html(newtotalmoney);
	}
}

//--------------------遍历-----------------------
//重计算
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
	//取出选中产品的总金额
	var price = $("#total"+bid).html();
	//取出计算产品的件数
	var count = $("#totalMoney").html();
	//取出当前总金额
	var curTotalPrice = $("#tp").html();
	//转换类型//计算
	var newTotalPrice = Number(curTotalPrice)+Number(price.substring(1))*flag;
	if(newTotalPrice <= 0){
		$("#tp").html(0);
		return;
	}
	//取两位小数//写回
	$("#tp").html(newTotalPrice.toFixed(2));
	$("#totalMoney").html(Number(count)+flag);
}

function selectAll(){
	var cboxs = $("input[type='checkbox']");
	var newTotalPrice=0;
	var count=0;
	console.log(cboxs.length);
	//循环取出所有单个产品总金额
	for(var i=0;i<cboxs.length;i++){
		if(cboxs[i].value!="all"){
			cboxs[i].checked=true;
			var bid=cboxs[i].value;
			//类型转换加和
			newTotalPrice += Number($("#total"+bid).html().substring(1));
			//计数
			count++;
		}
	}
	//写回
	$("#tp").html(newTotalPrice.toFixed(2));
	$("#totalMoney").html(count);
}

function selectNone(){
	var cboxs = $("input[type='checkbox']");
	for(var i=0;i<cboxs.length;i++){
		cboxs[i].checked=false;
	}
	//写回
	$("#tp").html(0);
	$("#totalMoney").html(0);
}

//-------------------------获取点选的物品------------------------------
function checkList(){
	var lists="";
	var cboxs = $("input[type='checkbox']");
	for(var i=0;i<cboxs.length;i++){
		if(cboxs[i].value!="all" && cboxs[i].checked == true){
			//获取id
			var bid=cboxs[i].value;
			//获取数量
			var num = $("#num"+bid).val();
			//类型转换//生成对象
			var checkThing =bid+","+num;
			lists += checkThing; 
			lists += ";";
			};
			//放入数组
			
	}
	console.log(lists);
	//FormData formdata = 
	if(confirm("确认购买吗？")){
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
	        			window.location.href='/EBP/user/BookingSucc.jsp';//改成你的成功页面的地址
	        		else
	        			alter("交易失败");
	        		
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
//--------------------------加入购物车----------------------------------
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
	        					alert("添加购物车成功");
	        				else
	        					alert("哎呀，小主先登录啦");
	        			}
	        },
	        error    :function(status){
	        	console.log(status);
	        }
	});
}