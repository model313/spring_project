<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사이트 환경설정</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/subpage.css?v=5">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<%@ include file="./admin_banner.jsp" %>
<main class="maincss">
<cr:forEach var="siteData" items="${resultList}">
	<section>
	<p>홈페이지 가입환경 설정</p>
	<div class="subpage_view">
		<ul class="info_form">
		    <li>홈페이지 제목</li>
		    <li>
		        ${siteData.si_title}
		    </li>
		</ul>    
		<ul class="info_form">
		    <li>관리자 메일 주소</li>
		    <li>
		    	${siteData.si_ademail}
		    </li>
		</ul> 
		<ul class="info_form">
		    <li>포인트 사용 유/무</li>
		    <li class="checkcss">
		    	<cr:if test="${siteData.si_pointuse=='Y'}">사용</cr:if>
		    	<cr:if test="${siteData.si_pointuse=='N'}">미사용</cr:if>
		    </li>
		</ul>
		<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
		    <li>회원가입시 적립금</li>
		    <li>
		        ${siteData.si_joinpoint} 점
		    </li>
		    <li>회원가입시 권한레벨</li>
		    <li>
		        ${siteData.si_userlevel}
		    </li>
		</ul> 
	</div>
	<p>홈페이지 기본환경 설정</p>
	<div class="subpage_view">
		<ul class="info_form2">
		    <li>회사명</li>
		    <li>
		        ${siteData.si_coname} 
		    </li>
		    <li>사업자등록번호</li>
		    <li>
		    	${siteData.si_coregno}
		    </li>
		</ul> 
		<ul class="info_form2">
		    <li>대표자명</li>
		    <li>
		    	${siteData.si_ceoname}
		    </li>
		    <li>대표전화번호</li>
		    <li>
		    	${siteData.si_ceotel}
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>통신판매업 신고번호</li>
		    <li>
		    	${siteData.si_mosregno}
		    </li>
		    <li>부가통신 사업자번호</li>
		    <li>
		    	${siteData.si_vatbregno}
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>사업장 우편번호</li>
		    <li>
		    	${siteData.si_copostal}
		    </li>
		    <li>사업장 주소</li>
		    <li>
		    	${siteData.si_coaddr}
		    </li>
		</ul>
		<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
		    <li>정보관리책임자명</li>
		    <li>
		    	${siteData.si_imadminname}
		    </li>
		    <li>정보책임자 E-mail</li>
		    <li>
		    	${siteData.si_imadminemail}
		    </li>
		</ul>
	</div>
	<p>결제 기본환경 설정</p>
	<div class="subpage_view">  
		<ul class="info_form2">
		    <li>무통장 은행</li>
		    <li>
		    	${siteData.si_nbkname}
		    </li>
		    <li>은행계좌번호</li>
		    <li>
		    	${siteData.si_bkaccno}
		    </li>
		</ul>
		<ul class="info_form">
		    <li>신용카드 결제 사용</li>
		    <li class="checkcss">
 			   	<cr:if test="${siteData.si_cardpayuse=='Y'}">사용</cr:if>
		    	<cr:if test="${siteData.si_cardpayuse=='N'}">미사용</cr:if>
		    </li>
		</ul>
		<ul class="info_form">
		    <li>휴대폰 결제 사용</li>
		    <li class="checkcss">
		    	<cr:if test="${siteData.si_telpayuse=='Y'}">사용</cr:if>
		    	<cr:if test="${siteData.si_telpayuse=='N'}">미사용</cr:if>
		    </li>
		</ul>
		<ul class="info_form">
		    <li>도서상품권 결제사용</li>
		    <li class="checkcss">
 			    <cr:if test="${siteData.si_dcmcpayuse=='Y'}">사용</cr:if>
		    	<cr:if test="${siteData.si_dcmcpayuse=='N'}">미사용</cr:if>	
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>결제 최소 포인트</li>
		    <li>
	        	${siteData.si_pointusemin} 점
		    </li>
		    <li>결제 최대 포인트</li>
		    <li>
		    	${siteData.si_pointusemax} 점
		    </li>
		</ul>
		<ul class="info_form">
		    <li>현금 영수증 발급사용</li>
		    <li class="checkcss">
		    	<cr:if test="${siteData.si_cashrcptuse=='Y'}">사용</cr:if>
		    	<cr:if test="${siteData.si_cashrcptuse=='N'}">미사용</cr:if>
		    </li>
		</ul>
		<ul class="info_form2">
		    <li>배송업체명</li>
		    <li>
		    	${siteData.si_delconame}
		    </li>
		    <li>배송비 가격</li>
		    <li>
	        	${siteData.si_delprice} 원
		    </li>
		</ul>
		<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
		    <li>희망배송일</li>
		    <li class="checkcss">
		    	<cr:if test="${siteData.si_deldateuse=='Y'}">사용</cr:if>
		    	<cr:if test="${siteData.si_deldateuse=='N'}">미사용</cr:if>
		    </li>
		</ul>
	</div>
	<div class="btn_div">
	    <input type="button" value="정보 수정" class="sub_btn1" title="정보 수정" onclick="location.href='/admin/site_mod';">
	</div>
	</section>
</cr:forEach>
<section></section>
<section></section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
</html>