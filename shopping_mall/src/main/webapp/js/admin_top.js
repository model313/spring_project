window.onload = (event) => {
	fetch("./login_status_ck",{
		method : "GET",
		headers : {"content-type":"application/html"}
	})
	.then(response => {
		return response.text();
	})
	.then(response2 => {
		if(response2 == "1"){
			
		}
		else if (response2 == "0"){
			alert('로그인 하셔야 해당 페이지 접속 가능합니다');
			location.href='./index';
		}
		else if (response2 == "-1"){
			alert('서버 문제로 인해 해당 페이지 접속 불가능합니다');
			location.href='./index';
		}
	})
	.catch(error => {
		alert('데이터 정송 오류로 인해 해당 페이지 접속 불가능합니다');
		location.href='./index';
	});
};