
function checkBname(val) {
	var flag = (val != "");
	document.getElementById("bnameErr").innerHTML = flag ? "" : "��������Ϊ��";
	return flag;
}

function checkAuthor(val) {
	var flag = (val != "");
	document.getElementById("authorErr").innerHTML = flag ? "" : "���߲���Ϊ��";
	return flag;
}

function checkDescp(val) {
	var flag = (val != "");
	document.getElementById("descpErr").innerHTML = flag ? "" : "��鲻��Ϊ��";
	return flag;
}

function checkPublisher(val) {
	var flag = (val != "");
	document.getElementById("publisherErr").innerHTML = flag ? "" : "�����粻��Ϊ��";
	return flag;
}

function checkPrice(val) {
	var flag = (val != "");
	document.getElementById("priceErr").innerHTML = flag ? "" : "�۸���Ϊ��";
	return flag;
}

function checkPublishTime(val) {
	var flag = (val != "");
	document.getElementById("publishTimeErr").innerHTML = flag ? "" : "����ʱ�䲻��Ϊ��";
	return flag;
}


function checkType(val) {
	var flag = (val != "");
	document.getElementById("typeErr").innerHTML = flag ? "" : "���಻��Ϊ��";
	return flag;
}
/*function checkStaus() {
	var staus = document.getElementsByName("staus");
	var selectFlag = false;
	for (var index = 0; index < staus.length; index++) {
		if (!staus[index].checked) continue;
		selectFlag = true; 
		break;
	}
	
	var textObj = document.getElementById("stausErr");
	textObj.innerHTML = selectFlag ? "" : "��ѡ��Ʊ��״̬";
	return selectFlag;
}*/

function checkUpdateBookForm(curform) {
	var flag1 = checkBname(curform.bname.value);
	var flag2 = checkAuthor(curform.author.value);
	var flag3 = checkDescp(curform.descp.value);
	var flag4 = checkPublisher(curform.publisher.value);
	var flag5 = checkPrice(curform.price.value);
	var flag6 = checkPublishTime(curform.publishTime.value);
	var flag7 = checkType(curform.type.value);
	if (!flag1 || !flag2 || !flag3 || !flag4|| !flag5|| !flag6 || !flag7) 
		return false;
	return confirm("ȷ���Ƿ��޸��鼮��Ϣ��");
}

function checkAddBookForm(curform) {
	var flag1 = checkBname(curform.bname.value);
	var flag2 = checkAuthor(curform.author.value);
	var flag3 = checkDescp(curform.descp.value);
	var flag4 = checkPublisher(curform.publisher.value);
	var flag5 = checkPrice(curform.price.value);
	var flag6 = checkPublishTime(curform.publishTime.value);
	var flag7 = checkType(curform.type.value);
	if (!flag1 || !flag2 || !flag3 || !flag4|| !flag5|| !flag6 || !flag7) 
		return false;
	return confirm("ȷ���Ƿ�����鼮��Ϣ��");
}

//-----------------------------userMng---------------------------------------
function changeStatu(changeUrl,uid){
	var status = $("#showStatus"+uid).html().trim();
	changeUrl +=(status == "����" ? 0:1);
	$.ajax({
        type     : "get",
        async    : true,
        url      : changeUrl,
        dataType : "text",
        cache    : false,
        success  : function(data){
        	if(data !=null && data.trim() != ""){
        		var statu = data.trim();
        		 $("#showStatus"+uid).html(statu == "1" ? "�" : "����");
        		 $("#setStatus"+uid).html(statu == "1" ? "����" : "�");
        	}
        },
        error:function(data){
        	console.log("worng");
        }
    });
}
