const siteModFormSend = () => {
	let resCondition = new siteDataTools();
	
	if(resCondition.emptyCheck() == "Y" 
	&& resCondition.bkCheck() == "Y"
	&& resCondition.minPointCheck() == "Y"
	&& resCondition.maxPointCheck() == "Y")
	{
		siteInfoForm.method = "POST";
		siteInfoForm.action = "./site_update";
		siteInfoForm.submit();
	}
}

const siteModCancel = () => {
	if(confirm("이 페이지를 떠나시면 변경 사항이 모두 사라집니다")){
		location.href="/admin/site_info";
	}
}

class siteDataTools{
	emptyCheck(){
		var checkResult="N";
		if(document.querySelector("[name='si_title']").value==""){
			alert('홈페이지 제목을 입력해주세요');
		}
		else if(document.querySelector("[name='si_ademail']").value==""){
			alert('관리자 메일 주소를 입력해주세요');
		}
		else if(document.querySelector("[name='si_userlevel']").value==""){
			alert('회원가입시 권한레벨을 입력해주세요');
		}
		else if(document.querySelector("[name='si_coname']").value==""){
			alert('회사명을 입력해주세요');
		}
		else if(document.querySelector("[name='si_coregno']").value==""){
			alert('사업자등록번호를 입력해주세요');
		}
		else if(document.querySelector("[name='si_ceoname']").value==""){
			alert('대표자명을 입력해주세요');
		}
		else if(document.querySelector("[name='si_ceotel']").value==""){
			alert('대표전화번호를 입력해주세요');
		}
		else if(document.querySelector("[name='si_copostal']").value==""){
			alert('사업장 우편번호를 입력해주세요');
		}
		else if(document.querySelector("[name='si_coaddr']").value==""){
			alert('사업장 주소를 입력해주세요');
		}
		else if(document.querySelector("[name='si_imadminname']").value==""){
			alert('정보관리책임자명을 입력해주세요');
		}
		else if(document.querySelector("[name='si_imadminemail']").value==""){
			alert('정보책임자 이메일을 입력해주세요');
		}
		else if(document.querySelector("[name='si_delconame']").value==""){
			alert('배송업체명을 입력해주세요');
		}
		else{
			checkResult = "Y";
		}
		return checkResult;
	}
	
	bkCheck(){
		var checkResult = "N";
		if(document.querySelector("[name='si_nbkname']").value!="" 
		&& document.querySelector("[name='si_bkaccno']").value==""){
			alert('무통장 은행을 입력하셨으면 은행계좌번호는 필수로 입력하셔야합니다');
		}
		else{
			checkResult = "Y";
		}
		return checkResult;
	}
	
	minPointCheck(){
		var checkResult = "N";
		if(document.querySelector("[name='si_pointusemin']").value < 1000){
			alert('결제 최소 포인트는 1,000점 이상부터만 설정 가능합니다');
		}
		else{
			checkResult = "Y";
		}
		return checkResult;
	}
	
	maxPointCheck(){
		var checkResult = "N";
		if(document.querySelector("[name='si_pointusemax']").value -0 
		<= document.querySelector("[name='si_pointusemin']").value -0){
			alert('결제 최대 포인트는 지정하신 최소 포인트보다 높게 설정 하셔야됩니다');
		}
		else{
			checkResult = "Y";
		}
		console.log(checkResult);
		return checkResult;
	}
}