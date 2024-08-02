const menuCodeCheck = (input) => {
	if(input.value.length == 1){
    	input.value = "0" + input.value;
  	}
	
	let resCondition = new cateDataTools().codeChecker();
	
	if (resCondition=="N"){
		alert('이미 존재하고있는 대메뉴 코드입니다');
	}
}


const cateFormSend = () => {
	let resCondition1 = new cateDataTools().emptyCheck();
	let resCondition2 = new cateDataTools().codeChecker();
	if(resCondition1 == "Y"
	&& resCondition2 == "Y"){
		cateForm.method="POST";
		cateForm.action="./cate_add"
		cateForm.submit();
	}
	else if(resCondition1 == "Y"
	&& resCondition2 == "N"){
		alert('이미 존재하고있는 대메뉴 코드입니다');
	}
}


class cateDataTools{
	emptyCheck(){
		var resultCheck = "N";
		if(document.querySelector("[name='ca_class']").value == ""){
			alert('분류코드를 입력해주세요');
		}
		else if(document.querySelector("[name='ca_code']").value == ""){
			alert('대메뉴 코드를 입력해주세요');
		}
		else if(document.querySelector("[name='ca_name']").value == ""){
			alert('대메뉴명을 입력해주세요');
		}
		else{
			resultCheck = "Y";
		}
		return resultCheck;
	}
	
	codeChecker(){
		var resultCheck = "Y";
		var menuCode = document.querySelector("[name='ca_code']").value;
		var	codeList = document.querySelector("#lg_menu").options;
		
		for(var f=0; f<codeList.length; f++){
			if(menuCode == codeList[f].value){
				resultCheck = "N";
			}
		}
		return resultCheck;
	}
}