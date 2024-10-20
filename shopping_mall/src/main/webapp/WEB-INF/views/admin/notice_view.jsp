<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/notice.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/imagemodal.css?v=3">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<%@ include file="./admin_banner.jsp" %>

<main class="maincss">
<section>
    <p>공지사항 확인 페이지</p>
	<cr:forEach var="data" items="${resultList}">
	<div class="write_view">
		<ul>
		    <li>공지사항 제목</li>
		    <li>
		    	${data.an_title}
		    </li>
		</ul>
		<ul>
		    <li>글쓴이</li>
		    <li>
		     	${data.an_adname}
		    </li>
		</ul>
		<ul>
		    <li>첨부파일</li>
		    <li>
		    <cr:choose>
		    	<cr:when test="${data.an_filename!=''}">
				<input type="button" value="보기" title="이미지 상세보기" class="n_image" onclick="imageModalOpen()">
				<div id="imageModal" class="modal">
	        		<span class="close" onclick="imageModalClose()">&times;</span>
	        		<img class="modal-content" src="/upload/${data.an_fileurl}">
	        		<div class="caption">${data.an_filename}</div>
	        	</div>
		    	</cr:when>
		    	<cr:otherwise>
		    	-
		    	</cr:otherwise>
		    </cr:choose>
		    </li>
		</ul>
		<ul class="ul_height">
		    <li>공지내용</li>
		    <li>
		        <div class="notice_input3" style="overflow-y: auto;">
		        	${data.an_cktext}
		        </div>
		    </li>
		</ul>
	</div>
	<div class="board_btn">
	    <input type="button" value="공지목록" class="border_del" onclick="location.href='./notice_list';">
	    <input type="button" value="공지수정" class="border_add" onclick="noticeMod(${data.an_idx})">
	    <input type="button" value="공지삭제" class="border_modify" style="margin-left: 8px;" onclick="noticeDel(${data.an_idx})">
	</div>
	</cr:forEach>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/resources/admin/js/notice_view.js?v=1"></script>
</html>