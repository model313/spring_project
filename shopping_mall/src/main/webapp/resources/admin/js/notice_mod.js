let allowedExtension = [
	'image/xbm','image/tif','image/jfif','image/ico','image/tiff',
	'image/gif','image/svg','image/webp','image/svgz','image/jpg',
	'image/jpeg','image/png','image/bmp','image/pjp','image/apng',
	'image/pjpeg','image/avif'
];

window.addEventListener("load", () => {
	CKEDITOR.replace("editor");
	
	if (document.querySelector("[name='an_showtop']").value=="Y"){
		document.querySelector("#showtopcheck").checked = true;
	}
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

const imageModalOpen = () => {
	var modal = document.getElementById(`imageModal`);
	modal.style.display = "block";
}

const imageModalClose = () => {
	var modal = document.getElementById(`imageModal`);
	modal.style.display = "none";
}

const imageDel = () => {
	var imageSection = document.querySelector("#imageHTML");
	imageSection.innerHTML = `<input type="file" name="an_file" accept="image/*" onchange="onAttachTypeCheck(this)"> ※ 첨부파일 최대 용량은 2MB 입니다.`;
}

const onAttachTypeCheck = (input) => {
	new noticeDataTools().fileTypeCheck(input);
};

const noticeModFormSend = () => {
	let resCondition = new noticeDataTools().emptyCheck();
	
	if (resCondition == "Y"){
		noticeModForm.method="POST";
		noticeModForm.action="./notice_update";
		noticeModForm.enctype = "multipart/form-data";
		noticeModForm.submit();
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