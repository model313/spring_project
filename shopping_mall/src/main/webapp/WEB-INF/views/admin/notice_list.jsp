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
    <title>공지사항 리스트 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/notice.css?v=10">
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
            <li title="쇼핑몰 상품관리">쇼핑몰 관리자 리스트</li>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
            <li title="쇼핑몰 공지사항">쇼핑몰 공지사항</li>
        </ol>
    </div>

</nav>
<main class="maincss">
<section>
    <p>공지사항 관리페이지</p>
    <div class="subpage_view">
    <ul>
        <li><input type="checkbox" name="noticeAllCk" onclick="noticeSelAll()"></li>
        <li>NO</li>
        <li>제목</li>
        <li>글쓴이</li>
        <li>날짜</li>
        <li>조회</li>
    </ul>
    <cr:choose>
    	<cr:when test="${listSize == 0}">
    		<ol class="none_text">
        		<li>등록된 공지 내용이 없습니다.</li>
    		</ol>
    	</cr:when>
    	<cr:otherwise>
    		<cr:forEach var="data" items="${resultList}" varStatus="listDetails">
    			<ol>
			        <li><input type="checkbox" name="noticeCk" value="${data.an_idx}" onclick="noticeSel()"></li>
			        <li>${listDetails.index + 1}</li>
			        <li class="open_notice" onclick="openNoticeView(${data.an_idx})">${data.an_title}</li>
			        <li>${data.an_adname}</li>
			        <li>
			        	<fmt:parseDate var="date" value="${data.an_indate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			        	<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
		        	</li>
			        <li>${data.an_viewcount}</li>
			    </ol>
    		</cr:forEach>
    	</cr:otherwise>
    </cr:choose>
    </div>
    <div class="board_btn">
        <input type="button" value="공지삭제" class="border_del" onclick="noticeSelDel()">
        <input type="button" value="공지등록" class="border_add" onclick="location.href='./notice_write';">
    </div>
    <div class="border_page">
        <ul class="pageing">
            <li><img src="/resources/admin/ico/double_left.svg"></li>
            <li><img src="/resources/admin/ico/left.svg"></li>
            <li>1</li>
            <li><img src="/resources/admin/ico/right.svg"></li>
            <li><img src="/resources/admin/ico/double_right.svg"></li>
        </ul>
    </div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/resources/admin/js/notice_list.js?v=1"></script>
</html>