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
     var img = document.getElementById("bookimgs");
         img.src = e.target.result;
         console.log(img.src);
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
    formData.append('image', file);
    
    $.ajax({
        url: "/EBP/admin/BookImageUploadCtrl",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
		        	if(data == null || data.trim() == ""){
		        		return;
		        	}
		        	var Url = data.trim();
		        	console.log($("#image").text());
		        	$("#image").attr("value",Url);
		        	
        		},
        error: function (data) {
            console.log(data);
        }
    });
}
