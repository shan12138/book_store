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
     var img = document.getElementById("bookimgs");
         img.src = e.target.result;
         console.log(img.src);
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
