

function checkLoginForm(curform) {
	var username = checkUserName(curform.userName.value);
    var password = checkPassword(curform.upassword.value);
    if (! username || ! password ) return false; 
}

function checkUserName(val) {
	var flag = (val != "");
	document.getElementById("userNameErr").innerHTML = flag ? "" : "用户名不能为空";
	return flag;
}

function checkPassword(val) {
	var flag = (val != "");
	document.getElementById("passwordErr").innerHTML = flag ? "":"密码不能为空";
	return flag;
}
function checkRegUserName(val) {
	var flag = (val != "");
	document.getElementById("reguserNameErr").innerHTML = flag ? "" : "用户名不能为空";
	return flag;
}

function checkRegPassword(password) {
	var flag = (val != "");
	document.getElementById("regpasswordErr").innerHTML = flag ? "":"密码不能为空";
	return flag;
}

function checkPasswordAgain(passwordagain){
	var password =  document.getElementById("password").value;
	var flag = (password == passwordagain);
	document.getElementById("passwordagainErr").innerHTML = flag ? "":"两次密码不匹配";
	return flag;
}

function checkUname(val) {
	var flag = (val != "");
	document.getElementById("unameErr").innerHTML = flag ? "" : "真实姓名不能为空";
	return flag;
}
function checkGender() {
	var genders = document.getElementsByName("gender");
	var selectFlag = false;
	for (var index = 0; index < genders.length; index++) {
		if (!genders[index].checked) continue;
		selectFlag = true; 
		break;
	}
	
	var textObj = document.getElementById("genderErr");
	textObj.innerHTML = selectFlag ? "" : "请选择性别";
	return selectFlag;
}

function checkIdCard(val) {
	/*/^[1-9]\\d{7}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}$/*/
	var regExp = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
	var flag = (val != "" && regExp.test(val));
	document.getElementById("idCardErr").innerHTML = flag ? "" : "请输入正确的身份证号";
	return flag;
}

function checkTelNO(val) {
	var regExp =/(\d{11})|^ ((\d{7, 8})| (\d{4})|d{3})-(\d{7, 8})|(\d{4}|d{3})-(\d{7, 8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7, 8})-(d{4}|\d{3}|\d{2}|\d{1})$/; 
	var flag = (val != "" && regExp.test(val));
	document.getElementById("telNoErr").innerHTML = flag ? "" : "请输入正确的电话号码";
	return flag;
}

function checkRegisterForm(curform) {
	var flag1 = checkRegUserName(curform.userName.value);
	var flag2 = checkGender();
	var flag3 = checkRegPassword(curform.password.value);
	var flag8 = checkPasswordAgain(curform.passwordagain.value);
	var flag4 = checkUname(curform.uname.value);
	var flag5 = checkIdCard(curform.idCard.value);
	var flag6 = checkTelNO(curform.telNo.value);
	var flag7 = checkAddress(curform.address.value);
	
	
	if (!flag7) curform.address.focus();
	if (!flag6) curform.telNo.focus();
	if (!flag5) curform.idCard.focus();
	if (!flag4) curform.uname.focus();
	if (!flag3) curform.telNo.focus();
	if (!flag2) {
		document.getElementsByName("gender")[0].focus();
	}
	if (!flag1) curform.userName.focus();

	return flag1 && flag2 && flag3 &&  flag4 && flag5 && flag6 && flag7 && flag8;
}

