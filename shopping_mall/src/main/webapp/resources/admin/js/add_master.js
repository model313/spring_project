function id_check(){
	if(add_admin_frm.ad_id.value==""){
		alert("생성할 관리자 아이디를 입력하세요");
	}
	else{
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function(){
			var idInput = document.getElementById("ad_id");
			if(ajax.readyState==4 && ajax.status==200){
				//console.log(this.response);
				switch(this.response){
					case "0" : 
						alert("사용가능한 아이디입니다");
						idInput.setAttribute("readonly", true);
						idInput.setAttribute("style", "background-color:#c6c6c6");
						document.getElementById("checkIdApproved").setAttribute("value", "Y");
						break;
					case "1" :
						alert("이미 사용중인 아이디입니다");
						idInput.focus();
						break;
					case "error" : 
						alert("데이터 전송 문제로 아이디 조회 불가능합니다");
						break;
					case "error2" :
						alert("DB접속 문제로 아이디 조회 불가능합니다");
						break;
				}
			}
		}
		ajax.open("GET","./id_check.do?ad_id="+add_admin_frm.ad_id.value,true);
		ajax.send();
	}
}

function admin_add(){
	var inputCheck = checkEmpty();
	var passCheck = checkPass();
	addTelNum();
	
	if (inputCheck=="Y" && passCheck=="Y"){
		add_admin_frm.method="POST";
		add_admin_frm.action="./admin_add"
		add_admin_frm.submit();
	}
}

function admin_cancel(){
	//location.href="/admin/index.do"
}

function checkEmpty(){
	check="N";
	if(add_admin_frm.ad_id.value==""){
		alert("생성할 관리자 아이디를 입력하세요");
	}
	else if(add_admin_frm.ad_pass.value==""){
		alert("생성할 관리자 패스워드를 입력하세요");
	}
	else if(add_admin_frm.pw_check.value==""){
		alert("관리자 패스워드를 한번더 입력하세요");
	}
	else if(add_admin_frm.ad_name.value==""){
		alert("담당자 이름을 입력하세요");
	}
	else if(add_admin_frm.ad_email.value==""){
		alert("담당자 이메일을 입력하세요");
	}
	else if(add_admin_frm.tel1.value==""||add_admin_frm.tel2.value==""||add_admin_frm.tel3.value==""){
		alert("담당자 연락처을 입력하세요");
	}
	else if(add_admin_frm.ad_dep.value==""){
		alert("담당자 부서를 선택하세요");
	}
	else if(add_admin_frm.ad_pos.value==""){
		alert("담당자 직책을 선택하세요");
	}
	else if(add_admin_frm.checkIdApproved.value=="N"){
		alert("아이디 중복 체크해주세요");
	}
	else{
		check="Y";
	}
	return check;
}

function checkPass(){
	var check = "N";
	var pass1 = document.getElementById("ad_pass").value;
	var pass2 = document.getElementById("pw_check").value;
	if(pass1==pass2){
		check="Y";
	}
	else{
		alert("비밀번호가 동일하지 않습니다.");
	}
	return check;
}

function addTelNum(){
	var tel = add_admin_frm.tel1.value + add_admin_frm.tel2.value + add_admin_frm.tel3.value
	document.getElementById("ad_tel").setAttribute("value",tel);
}

function cancelAddAdmin(){
	if(confirm("이 페이지를 떠나시면 변경 사항이 모두 사라집니다")){
		location.href="/admin/login";
	}
}