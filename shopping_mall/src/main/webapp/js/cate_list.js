var cateSelCK = document.querySelectorAll("[name='cateCk']");
var cateSelAllCK = document.querySelector("[name='cateAllCk']");

const cateSel = () => {
	var count = 0;
	for(var f=0;f<cateSelCK.length;f++){
		if(cateSelCK[f].check==true){
			count++;
		}
	}
	if(count==cateSelCK.length){
		cateSelAllCK.checked = true;
	}
	else{
		cateSelAllCK.checked = false;
	};
};

const cateSelAll = () => {
	for(var f=0;f<cateSelCK.length;f++){
		cateSelCK[f].checked = cateSelAllCK.checked;
	};
};

const cateSelDel = () => {
	var caIdxList = new Array();
	for(var f=0;f<cateSelCK.length;f++){
		if(cateSelCK[f].checked==true){
			caIdxList.push(cateSelCK[f].value);
		}
	};
	fetch(`./cate_delete?caIdxList=${caIdxList}`,{
		method : "GET",
		headers : {"content-type":"application/html"}
	})
	.then(response => {
		return response.text();
	})
	.then(response2 => {
		console.log(response2);
		if(response2 > 0){
			alert('성공적으로 카테고리 삭제되었습니다');
			location.href='./cate_list';
		}
		else if (response2 == 0){
			alert('서버 문제로 인해 선택하신 카테고리를 삭제 못했습니다');
			location.href='./cate_list';
		}
	})
	.catch(error => {
		alert('데이터 정송 오류로 인해 카테고리를 삭제 못했습니다');
		location.href='./cate_list';
	});
};