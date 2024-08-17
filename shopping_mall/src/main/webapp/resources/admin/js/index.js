class adminLoginTools{
	
	emptyCheck(){
		var checkResult = "N";
		if(document.querySelector("#ad_id").value=="" || document.querySelector("#ad_pass").value==""){
			alert("로그인할 아이디 및 비밀번호 입력해주세요");
		}
		else{
			checkResult = "Y";
		}
		return checkResult;
	}
}
let loginForm = document.querySelector("#adminLoginFrm");

loginForm.addEventListener("submit", (event) => {
	event.preventDefault();
	let resCondition = new adminLoginTools().emptyCheck();
	if(resCondition=="Y"){
		loginForm.method="POST";
		loginForm.action="./ad_login_check"
		loginForm.submit();
	}
})