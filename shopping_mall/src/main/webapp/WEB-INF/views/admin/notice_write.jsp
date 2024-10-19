<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/notice.css?v=3">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<%@ include file="./admin_banner.jsp" %>
<form id="noticeForm">
	<main class="maincss">
	<section>
	    <p>공지사항 등록페이지</p>
	<div class="write_view">
	<ul>
	    <li>공지사항 여부</li>
	    <li>
	        <label class="label_notice">
	        	<em class="cbox">
	        		<input type="checkbox" id="showtopcheck" onclick="showCheckBoxValue()">
	        		<input type="hidden" name="an_showtop" value="N">
        		</em> 공지 출력
       		</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
	    </li>
	</ul>
	<ul>
	    <li>공지사항 제목</li>
	    <li>
	        <input type="text" name="an_title" class="notice_input1"> ※ 최대 150자까지 입력이 가능
	    </li>
	</ul>
	<ul>
	    <li>글쓴이</li>
	    <li>
	        <input type="text" name="an_adname" class="notice_input2" value="${activeLoginUser}" readonly> ※ 관리자 이름이 노출 됩니다.       
	    </li>
	</ul>
	<ul>
	    <li>첨부파일</li>
	    <li>
	        <input type="file" name="an_file" accept="image/*" onchange="onAttachTypeCheck(this)"> ※ 첨부파일 최대 용량은 2MB 입니다.       
	    </li>
	</ul>
	<ul class="ul_height">
	    <li>공지내용</li>
	    <li>
	        <textarea id="editor" name="an_cktext" class="notice_input3" placeholder="공지내용을 입력하세요!"></textarea>
	    </li>
	</ul>
	</div>
	<div class="board_btn">
	    <input type="button" value="공지목록" class="border_del">
	    <input type="button" value="공지등록" class="border_add" onclick="noticeFormSend()">
	</div>
	</section>
	</main>
</form>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/resources/admin/ckeditor/ckeditor.js?v=6"></script>
<script src="/resources/admin/js/notice_write.js?v=3"></script>
</html>