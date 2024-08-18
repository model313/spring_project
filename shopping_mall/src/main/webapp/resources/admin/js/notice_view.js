const imageModalOpen = () => {
	var modal = document.getElementById(`imageModal`);
	modal.style.display = "block";
}

const imageModalClose = () => {
	var modal = document.getElementById(`imageModal`);
	modal.style.display = "none";
}

const noticeMod = (idx) => {
	location.href = `./notice_mod?an_idx=${idx}`;
}

const noticeDel = (idx) => {
	if(confirm('공지사항 데이터 삭제시 복구 되지 않습니다')){
		fetch(`./notice_delete?anIdxList=${idx}`,{
			method : "GET",
			headers : {"content-type":"application/html"}
		})
		.then(response => {
			return response.text();
		})
		.then(response2 => {
			if(response2 > 0){
				alert('공지사항 데이터 삭제되었습니다');
				location.href = "./notice_list";
			}
			else if (response2 == 0){
				alert('서버 문제로 인해 공지사항 데이터 삭제 못했습니다');
				window.location.reload();
			}
		})
		.catch(error => {
			alert('데이터 정송 오류로 인해 공지사항 데이터를 삭제 못했습니다');
			window.location.reload();
		});
	}
}