function checkAdminLoginForm(curform) {
	var name = checkAdminName(curform.name.value);
    var password = checkPassword(curform.password.value);
    if (!name || !password ) return false; 
}

function checkAdminName(val) {
	var flag = (val != "");
	document.getElementById("nameErr").innerHTML = flag ? "" : "�˺Ų���Ϊ��";
	return flag;
}

function checkPassword(val) {
	var flag = (val != "");
	document.getElementById("passwordErr").innerHTML = flag ? "":"���벻��Ϊ��";
	return flag;
}