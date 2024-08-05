const adminApprove = (idx, activeID) => {
	if(activeID=="master"){
		if(confirm("관리자 승인을 진행 하시겠습니까?")){
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
			});
		}
	}
	else{
		alert('권항 설정은 최고 관리자만 수정 할수있습니다.');
	}
};

const adminNotApprove = (idx,activeID) => {
	if(activeID=="master"){
		if(confirm("관리자 승인해제 하시겠습니까?")){
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
				location.href='./admin_list';
			});
		}
	}
	else{
		alert('권항 설정은 최고 관리자만 수정 할수있습니다.');
	}
};