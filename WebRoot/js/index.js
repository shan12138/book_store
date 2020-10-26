var timmerID = window.setInterval("updateTime()", 2500);
var imgFile = ['/1.jpg', '/2.jpg', '/3.jpg', '/4.jpg'];
var curImg = 0;

function updateTime() {
	var imgObj = document.getElementById("films");
    var path = imgObj.src;
    curImg = (curImg < imgFile.length - 1) ? curImg + 1 : 0;
    imgObj.src = path.substring(0, path.lastIndexOf("/")) + imgFile[curImg];
}