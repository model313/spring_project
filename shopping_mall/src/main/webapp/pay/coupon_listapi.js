var uri = window.location.search;		//URL ?에있는 파라미터 값 호출
var pageno = 0;				//페이지 번호

console.log(uri);
if(uri == ""){		//최초 접속시
	pageno = 1;
}
else{				//페이지 번호 클릭시
	pageno = uri.split("?page=")[1];	//페이지 번호
}
console.log(pageno);

function ajax_data(){
	var http;
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 && http .status == 200){
			html_code(JSON.parse(this.response));
		}
	}
	http.open("GET","./coupon_api.do",true);
	http.send();
}
ajax_data();


//JSON 데이터를 HTML로 출력
function html_code(data){
	//원레 전역변수로 사용
	var dataperpage = 2;		//한페이지당 2개의 데이터
	/*
	var startpg = pageno * dataperpage;
	var endpage = startpg + dataperpage
	*/
	
	var startpg = (pageno-1) * dataperpage;
	var endpg = pageno * dataperpage;
	
	/*
		1 번 : 0,1	0 < 2
		2 번 : 2,3	2 < 4
		3 번 : 4,5	4 < 6
		4 번 : 6,7	6 < 8
	*/
	
	var ea = data.length;		//데이터 총개수
	var resulthtml = document.getElementById("list");
	var pagehtml = document.getElementById("pages");
	
	//개수 출력
	document.getElementById("total").append(ea);
	
	//데이터 출력
	data.forEach(function(a,b,c){
		if(b >= startpg && b < endpg){
			resulthtml.innerHTML += `
				<tr>
				<td>`+(ea-b)+`</td>
				<td>`+data[b]["cpname"]+`</td>
				<td>`+data[b]["cprate"]+`</td>
				<td>`+data[b]["cpuse"]+`</td>
				<td>`+data[b]["cpdate"]+`</td>
				</tr>
			`;
		}
	});
	
	//페이징 출력
	
	/*
		현재상황에서 페이지 누를때마다 DB 접속됨 > Session Storage 사용
		하지만, DB에 개로운 데이터 추가되면 핸들링 따로 필요
		back역할 : 데이터 새로 추가시 session 업데이트
	*/
	var pgtotal = Math.ceil(ea / dataperpage);		//소수점 올림
	for(var f=1;f<=pgtotal;f++){
		pagehtml.innerHTML += `<td><a href='./coupon_listapi?page=`+f+`'>`+f+`</a></td>`;
	}
	
}