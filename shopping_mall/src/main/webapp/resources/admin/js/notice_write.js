let allowedExtension = [
	'image/xbm','image/tif','image/jfif','image/ico','image/tiff',
	'image/gif','image/svg','image/webp','image/svgz','image/jpg',
	'image/jpeg','image/png','image/bmp','image/pjp','image/apng',
	'image/pjpeg','image/avif'
];

window.addEventListener("load", () => {
	CKEDITOR.replace("editor");
});

const showCheckBoxValue = () => {
	let ckvalue = document.querySelector("#showtopcheck").checked;
	if(ckvalue == true){
		document.querySelector("[name='an_showtop']").setAttribute("value","Y");
	}
	else{
		document.querySelector("[name='an_showtop']").setAttribute("value","N");
	}
}

const onAttachTypeCheck = (input) => {
	new noticeDataTools().fileTypeCheck(input);
};

const noticeFormSend = () => {
	let resCondition = new noticeDataTools().emptyCheck();
	
	if (resCondition == "Y"){
		noticeForm.method="POST";
		noticeForm.action="./notice_add";
		noticeForm.enctype = "multipart/form-data";
		noticeForm.submit();
	}
}

const cancelNoticeWrite = () => {
	if(confirm("이 페이지를 떠나시면 변경 사항이 모두 사라집니다")){
		location.href="/admin/notice_list";
	}
}

class noticeDataTools{
	fileTypeCheck(input){
		if(allowedExtension.indexOf(input.files[0].type)<=-1){
			alert('이미지 파일만 첨부 가능합니다');
			input.value = "";
		};
	};
	
	emptyCheck(){
		var result = "N";
		let CKEditorValue = CKEDITOR.instances.editor.getData();
		
		if(document.querySelector("[name='an_title']").value == ""){
			alert("공지사항 제목을 입력해주세요");
		}
		else if (document.querySelector("[name='an_adname']").value == ""){
			alert("공지사항 글쓴이를 입력해주세요");
		}
		else if (CKEditorValue == ""){
			alert("공지내용을 입력해주세요");
		}
		else{
			result = "Y";
		}
		return result;
	}
}