var prodSelCK = document.querySelectorAll("[name='prodCk']");
var prodSelAllCK = document.querySelector("[name='prodAllCk']");
var prodCaNames = document.querySelectorAll("[name='caname']");

const prodSel = () => {
	var count = 0;
	for(var f=0;f<prodSelCK.length;f++){
		if(prodSelCK[f].checked==true){
			count++;
		}
	}
	if(count==prodSelCK.length){
		prodSelAllCK.checked = true;
	}
	else{
		prodSelAllCK.checked = false;
	};
}

const prodSelAll = () => {
	for(var f=0;f<prodSelCK.length;f++){
		prodSelCK[f].checked = prodSelAllCK.checked;
	};
}

const prodSelDel = () => {
	var prIdxList = new Array();
	var caNameList = new Array();
	for(var f=0;f<prodSelCK.length;f++){
		if(prodSelCK[f].checked==true){
			prIdxList.push(prodSelCK[f].value);
		}
	};
	
	fetch(`./prod_delete?prIdxList=${prIdxList}`,{
		method : "GET",
		headers : {"content-type":"application/html"}
	})
	.then(response => {
		return response.text();
	})
	.then(response2 => {
		if(response2 > 0){
			alert('선택하신 상품이 삭제되었습니다');
			location.href='./product_list';
		}
		else if (response2 == 0){
			alert('서버 문제로 인해 선택하신 상품을 삭제 못했습니다');
			location.href='./product_list';
		}
	})
	.catch(error => {
		alert('데이터 정송 오류로 인해 상품을 삭제 못했습니다');
		location.href='./product_list';
	});
}

//이미지 상세보기 Modal
const imageModalOpen = (idx) => {
	var modal = document.getElementById(`imageModal${idx}`);
	modal.style.display = "block";
}

const imageModalClose = (idx) => {
	var modal = document.getElementById(`imageModal${idx}`);
	modal.style.display = "none";
}