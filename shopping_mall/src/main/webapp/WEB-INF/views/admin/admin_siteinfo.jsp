<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/subpage.css?v=5">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
        </ol>
    </div>

</nav>
<form id="siteInfoForm">
<main class="maincss">
	<section>
	<p>홈페이지 가입환경 설정</p>
	<div class="subpage_view">
		<ul class="info_form">
		    <li>홈페이지 제목</li>
		    <li>
		        <input type="text" name="si_title" class="in_form1"> 
		    </li>
		</ul>    
		<ul class="info_form">
		    <li>관리자 메일 주소</li>
		    <li>
		        <input type="text" name="si_ademail" class="in_form2"> ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
		    </li>
		</ul> 
		<ul class="info_form">
		    <li>포인트 사용 유/무</li>
		    <li class="checkcss">
		        <em><label><input type="radio" name="si_pointuse" class="ckclass" value="Y">포인트 사용</label></em> 
		        <em><label><input type="radio" name="si_pointuse" class="ckclass" value="N" checked>포인트 미사용</label></em>
		    </li>
		</ul>
		<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
		    <li>회원가입시 적립금</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_joinpoint" 
		        	class="in_form3" 
		        	maxlength="5" 
		        	value="0"
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 점
		    </li>
		    <li>회원가입시 권한레벨</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_userlevel" 
		        	class="in_form3" 
		        	maxlength="1" 
		        	oninput="this.value = this.value.toUpperCase()"
	        	/> 레벨
		    </li>
		</ul> 
	</div>
	<p>홈페이지 기본환경 설정</p>
	<div class="subpage_view">
		<ul class="info_form2">
		    <li>회사명</li>
		    <li>
		        <input type="text" name="si_coname" class="in_form0"> 
		    </li>
		    <li>사업자등록번호</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_coregno" 
		        	class="in_form0" 
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 
		    </li>
		</ul> 
		<ul class="info_form2">
		    <li>대표자명</li>
		    <li>
		        <input type="text" name="si_ceoname" class="in_form0"> 
		    </li>
		    <li>대표전화번호</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_ceotel" 
		        	class="in_form0" 
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>통신판매업 신고번호</li>
		    <li>
		        <input type="text" name="si_mosregno" class="in_form0" value=""> 
		    </li>
		    <li>부가통신 사업자번호</li>
		    <li>
		        <input type="text" name="si_vatbregno" class="in_form0" value=""> 
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>사업장 우편번호</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_copostal" 
		        	class="in_form0"
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 
		    </li>
		    <li>사업장 주소</li>
		    <li>
		        <input type="text" name="si_coaddr" class="in_form2"> 
		    </li>
		</ul>
		<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
		    <li>정보관리책임자명</li>
		    <li>
		        <input type="text" name="si_imadminname" class="in_form0"> 
		    </li>
		    <li>정보책임자 E-mail</li>
		    <li>
		        <input type="text" name="si_imadminemail" class="in_form1"> 
		    </li>
		</ul>
	</div>
	<p>결제 기본환경 설정</p>
	<div class="subpage_view">  
		<ul class="info_form2">
		    <li>무통장 은행</li>
		    <li>
		        <input type="text" name="si_nbkname" class="in_form0"> 
		    </li>
		    <li>은행계좌번호</li>
		    <li>
		        <input type="text" name="si_bkaccno" class="in_form1"> 
		    </li>
		</ul>
		<ul class="info_form">
		    <li>신용카드 결제 사용</li>
		    <li class="checkcss">
		        <em><label><input type="radio" name="si_cardpayuse" class="ckclass" value="Y"> 사용</label></em> 
		        <em><label><input type="radio" name="si_cardpayuse" class="ckclass" value="N" checked> 미사용</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
		    </li>
		</ul>
		<ul class="info_form">
		    <li>휴대폰 결제 사용</li>
		    <li class="checkcss">
		        <em><label><input type="radio" name="si_telpayuse" class="ckclass" value="Y"> 사용</label></em> 
		        <em><label><input type="radio" name="si_telpayuse" class="ckclass" value="N" checked> 미사용</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
		    </li>
		</ul>
		<ul class="info_form">
		    <li>도서상품권 결제사용</li>
		    <li class="checkcss">
		        <em><label><input type="radio" name="si_dcmcpayuse" class="ckclass" value="Y"> 사용</label></em> 
		        <em><label><input type="radio" name="si_dcmcpayuse" class="ckclass" value="N" checked> 미사용</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>결제 최소 포인트</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_pointusemin" 
		        	class="in_form0" 
		        	maxlength="5" 
		        	value="1000" 
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 점
		    </li>
		    <li>결제 최대 포인트</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_pointusemax" 
		        	class="in_form0" 
		        	maxlength="5" 
		        	value="0" 
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 점
		    </li>
		</ul>
		<ul class="info_form">
		    <li>현금 영수증 발급사용</li>
		    <li class="checkcss">
		        <em><label><input type="radio" name="si_cashrcptuse" class="ckclass" value="Y"> 사용</label></em> 
		        <em><label><input type="radio" name="si_cashrcptuse" class="ckclass" value="N" checked> 미사용</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>배송업체명</li>
		    <li>
		        <input type="text" name="si_delconame" class="in_form0"> 
		    </li>
		    <li>배송비 가격</li>
		    <li>
		        <input 
		        	type="text" 
		        	name="si_delprice" 
		        	class="in_form0" 
		        	maxlength="7" 
		        	value="0" 
		        	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	        	/> 원
		    </li>
		</ul>
		<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
		    <li>희망배송일</li>
		    <li class="checkcss">
		        <em><label><input type="radio" name="si_deldateuse" class="ckclass" value="Y"> 사용</label></em> 
		        <em><label><input type="radio" name="si_deldateuse" class="ckclass" value="N" checked> 미사용</label></em> ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.
		    </li>
		</ul>
	</div>
	<div class="btn_div">
	    <button type="button" class="sub_btn1" title="설정 저장" onclick="siteFormSend()">설정 저장</button>
	    <button type="button" class="sub_btn2" title="저장 취소">저장 취소</button>
	</div>
	</section>
<section></section>
<section></section>
</main>
</form>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/resources/admin/js/admin_siteinfo.js?v=1"></script>
</html>