<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/css/main.css?v=1">
    <link rel="icon" href="/img/logo.png" sizes="128x128">
    <link rel="icon" href="/img/logo.png" sizes="64x64">
    <link rel="icon" href="/img/logo.png" sizes="32x32">
    <link rel="icon" href="/img/logo.png" sizes="16x16">
    
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 상품관리">쇼핑몰 관리자 리스트</li>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
        </ol>
    </div>

</nav>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    <cr:choose>
    	<cr:when test="${listSize == 0}">
		    <ol class="new_admin_none">
		        <li>신규 등록된 관리자가 없습니다.</li>
		    </ol>
    	</cr:when>
    	<cr:when test="${listSize > 0 }">
    		<cr:forEach var="adminData" items="${resultList}" varStatus="listDetails">
			    <ol class="new_admin_lists2">
			        <li>${listDetails.index + 1}</li>
			        <li>${adminData.ad_name}</li>
			        <li>${adminData.ad_id}</li>
			        <li>${adminData.ad_tel}</li>
			        <li>${adminData.ad_email}</li>
			        <li>${adminData.ad_dep}</li>
			        <li>${adminData.ad_pos}</li>
			        <li>
			        	<fmt:parseDate var="date" value="${adminData.ad_indate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			        	<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
			        </li>
			        <li>
			        	
			        	<cr:choose>
			        		<cr:when test="${adminData.ad_approve=='N'}">
					            <input type="button" value="승인" class="new_addbtn1" title="승인" onclick="adminApprove(${adminData.ad_idx})">
			        		</cr:when>
			        		<cr:when test="${adminData.ad_approve=='Y'}">
					            <input type="button" value="미승인" class="new_addbtn2" title="미승인" onclick="adminNotApprove(${adminData.ad_idx})">
			        		</cr:when>
			        		<cr:otherwise>
			        			ERROR
			        		</cr:otherwise>
			        	</cr:choose>
			        </li>
			    </ol>
    		</cr:forEach>
    	</cr:when>
    	<cr:otherwise>
    		<ol class="new_admin_none">
		        <li>ERROR</li>
		    </ol>
    	</cr:otherwise>
    </cr:choose>
</section>
<section></section>
<section></section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/js/admin_list.js?v=2"></script>
</html>