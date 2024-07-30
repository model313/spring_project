const adminApprove = (idx) => {
	console.log();
	fetch(`./admin_update?ad_idx=${idx}&ad_approve=Y`,{
		method : "GET",
		headers : {"content-type":"application/html"}
	})
	.then(response => {
		return response.text();
	})
	.then(response2 => {
		if(response2 == "1"){
			alert('정상적으로 승인 완료 되었습니다');
			location.href='./admin_list';
		}
		else if (response2 == "0"){
			alert('데이터 전송 문제로 승인을 처리 하지 못했습니다');
			location.href='./admin_list';
		}
		else if (response2 == "-1"){
			alert('DB 오류로 인하여 승인을 처리 하지 못했습니다');
			location.href='./admin_list';
		}
	})
	.catch(error => {
		alert('승인을 처리 하지 못했습니다');
		location.href='./admin_list';
		alert(error);
	});
};

const adminNotApprove = (idx) => {
	console.log();
	fetch(`./admin_update?ad_idx=${idx}&ad_approve=N`,{
		method : "GET",
		headers : {"content-type":"application/html"}
	})
	.then(response => {
		return response.text();
	})
	.then(response2 => {
		if(response2 == "1"){
			alert('정상적으로 승인 완료 되었습니다');
			location.href='./admin_list';
		}
		else if (response2 == "0"){
			alert('데이터 전송 문제로 승인을 처리 하지 못했습니다');
			location.href='./admin_list';
		}
		else if (response2 == "-1"){
			alert('DB 오류로 인하여 승인을 처리 하지 못했습니다');
			location.href='./admin_list';
		}
	})
	.catch(error => {
		alert('승인을 처리 하지 못했습니다');
		alert(error);
		location.href='./admin_list';
	});
};