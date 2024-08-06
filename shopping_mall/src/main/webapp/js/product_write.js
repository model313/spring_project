let allowedExtension = [
	'image/xbm','image/tif','image/jfif','image/ico','image/tiff',
	'image/gif','image/svg','image/webp','image/svgz','image/jpg',
	'image/jpeg','image/png','image/bmp','image/pjp','image/apng',
	'image/pjpeg','image/avif'
];

const codeGen = () => {
	let codeInputElement = document.querySelector("[name='pr_code']");
	let code = new productDataTools().codeGenerator();
	
	codeInputElement.value = code;
};

const codeCheck = () => {
	if (document.querySelector("[name='pr_code']").value==""){
		alert("상품코드 입력해주세요");
	}
	else{
		new productDataTools().codeDupCheck();
	}
};

const dcPriceCompute = () => {
	let price = document.querySelector("[name='pr_price']").value;
	let dc = document.querySelector("[name='pr_dc']").value;
	let dcprice = document.querySelector("[name='pr_dcprice']");
	
	if (price!=""){
		dcprice.setAttribute("value",price * (100 - dc)/100);
	}
}

const onAttachTypeCheck = (input) => {
	new productDataTools().fileTypeCheck(input);
};

const productFormSend = () => {
	new productDataTools().emptyCheck();
}

class productDataTools{
	codeGenerator(){
		var code = "";
		var codeLength = 7;
		for(var f = 0;f < codeLength;f++){
			code += Math.floor(Math.random()*10);
		};
		return code;
	};
	
	codeDupCheck(){
		let codeInputElement = document.querySelector("[name='pr_code']");
		fetch(`./product_code_check?pr_code=${codeInputElement.value}`,{
			method : "GET",
			headers : {"content-type":"application/html"}
		})
		.then(response => {
			return response.text();
		})
		.then(response2 => {
			if(response2 > 0){
				alert('이미 사용중인 상품 코드입니다');
				codeInputElement.setAttribute("value","");
			}
			else if (response2 == 0){
				alert('사용가능한 상품 코드입니다');
				codeInputElement.setAttribute("readonly",true);
				codeInputElement.setAttribute("style", "background-color:#c6c6c6");
				document.getElementById("codeGenBtn").setAttribute("disabled",true);
			}
		})
		.catch(error => {
			alert('데이터 정송 오류로 인해 현재 중복 확인 못하고있습니다');
			codeInputElement.setAttribute("value","");
		});
	};
	
	fileTypeCheck(input){
		if(allowedExtension.indexOf(input.files[0].type)<=-1){
			alert('이미지 파일만 첨부 가능합니다');
			input.value = "";
		};
	};
	
	codeDupHistoryCheck(){
		var result = "N";
		if(document.querySelector("[name='pr_code']").readonly!=true){
			alert("상품코드 중복확인 해주세요");
		}
		else{
			result = "Y";	
		}
		return result;
	};
	
	emptyCheck(){
		var result = "N";
		if(document.querySelector("[name='pr_caname']").value == ""){
			alert("대메뉴 카테고리름 선택 해주세요");
		}
		else if (document.querySelector("[name='pr_code']").value == ""){
			alert("상품코드를 등록해주세요");
		}
		else if (document.querySelector("[name='pr_name']").value == ""){
			alert("상품명을 입력해주세요");
		}
		else if (document.querySelector("[name='pr_info']").value == ""){
			alert("상품 부가설명을 작성해주세요");
		}
		else if (document.querySelector("[name='pr_price']").value == ""){
			alert("판매가격을 지정해주세요");
		}
		else if (document.querySelector("[name='pr_dc']").value == ""){
			alert("할인율을 입력해주세요");
		}
		else if (document.querySelector("[name='pr_stock']").value == ""){
			alert("상품 재고를 등록해주세요");
		}
		else if (document.querySelector("[name='pr_file1']").value == ""){
			//작동 안됨
			let pr_file1 = document.querySelector("[name='pr_file1']");
			let pr_file2 = document.querySelector("[name='pr_file2']");
			let pr_file3 = document.querySelector("[name='pr_file3']");
			if (pr_file2.value != ""){
				pr_file1.setAttribute("value",pr_file2.value);
			}
			else if (pr_file3.value == ""){
				pr_file1.setAttribute("value",pr_file3.value);
			}
			alert("첨부파일 최소한 하나가 필수입니다 (썸네일용)");
		}
		else{
			result = "Y";
			console.log("Test");
		}
		
		return result;
	}
};