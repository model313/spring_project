var noticeSelCK = document.querySelectorAll("[name='noticeCk']");
var noticeSelAllCK = document.querySelector("[name='noticeAllCk']");

const noticeSel = () => {
	var count = 0;
	for(var f=0;f<noticeSelCK.length;f++){
		if(noticeSelCK[f].checked==true){
			count++;
		}
	}
	if(count==prodSelCK.length){
		noticeSelAllCK.checked = true;
	}
	else{
		noticeSelAllCK.checked = false;
	};
}

const noticeSelAll = () => {
	for(var f=0;f<noticeSelCK.length;f++){
		noticeSelCK[f].checked = noticeSelAllCK.checked;
	};
}

const noticeSelDel = () => {
	var anIdxList = new Array();
	for(var f=0;f<noticeSelCK.length;f++){
		if(noticeSelCK[f].checked==true){
			anIdxList.push(noticeSelCK[f].value);
		}
	};
	
	if(confirm("삭제시, 해당 공지사항 데이터는 복구 되지 않습니다")){
		fetch(`./notice_delete?anIdxList=${anIdxList}`,{
			method : "GET",
			headers : {"content-type":"application/html"}
		})
		.then(response => {
			return response.text();
		})
		.then(response2 => {
			if(response2 > 0){
				alert('선택하신 공지사항 데이터 삭제되었습니다');
				window.location.reload();
			}
			else if (response2 == 0){
				alert('서버 문제로 인해 선택하신 공지사항 데이터 삭제 못했습니다');
				window.location.reload();
			}
		})
		.catch(error => {
			alert('데이터 정송 오류로 인해 공지사항 데이터를 삭제 못했습니다');
			window.location.reload();
		});
	}
}

const openNoticeView = (idx) => {
	location.href = `./notice_view?an_idx=${idx}`;
}