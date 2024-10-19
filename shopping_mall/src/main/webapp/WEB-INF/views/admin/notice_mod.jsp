<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 수 페이지</title>
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
<form id="noticeModForm">
	<main class="maincss">
	<section>
	    <p>공지사항 등록페이지</p>
	<div class="write_view">
		<cr:forEach var="data" items="${resultList}">
			<ul>
			    <li>공지사항 여부</li>
			    <li>
			        <label class="label_notice">
			        	<em class="cbox">
			        		<input type="checkbox" id="showtopcheck" onclick="showCheckBoxValue()">
			        		<input type="hidden" name="an_showtop" value="${data.an_showtop}">
		        		</em> 공지 출력
		       		</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
			    </li>
			</ul>
			<ul>
			    <li>공지사항 제목</li>
			    <li>
			        <input type="text" name="an_title" class="notice_input1" value="${data.an_title}"> ※ 최대 150자까지 입력이 가능
			        <input type="hidden" name="an_idx" value="${data.an_idx }">
			    </li>
			</ul>
			<ul>
			    <li>글쓴이</li>
			    <li>
			        <input type="text" name="an_adname" class="notice_input2" value="${data.an_adname}" readonly> ※ 관리자 이름이 노출 됩니다.       
			    </li>
			</ul>
			<ul>
			    <li>첨부파일</li>
			    <li id="imageHTML">
			    	<cr:choose>
			    		<cr:when test="${data.an_filename!=''}">
			    			${data.an_filename}&nbsp; 
						    	<input type="button" value="보기" title="이미지 상세보기" class="n_image" onclick="imageModalOpen()">
						    	<input type="button" value="삭제" title="첨부파일 이미지 삭제" class="n_image" onclick="imageDel()">
							<div id="imageModal" class="modal">
					        		<span class="close" onclick="imageModalClose()">&times;</span>
					        		<img class="modal-content" src="/upload/${data.an_fileurl}">
					        		<div class="caption">${data.an_filename}</div>
					        	</div>
					        	<input type="hidden" name="an_filename" value="${data.an_filename}">
					        	<input type="hidden" name="an_fileurl" value="${data.an_fileurl}">
			    		</cr:when>
			    		<cr:otherwise>
			    			<input type="file" name="an_file" accept="image/*" onchange="onAttachTypeCheck(this)"> ※ 첨부파일 최대 용량은 2MB 입니다.
			    		</cr:otherwise>
			    	</cr:choose>
			    </li>
			</ul>
			<ul class="ul_height">
			    <li>공지내용</li>
			    <li>
			        <textarea id="editor" name="an_cktext" class="notice_input3" placeholder="공지내용을 입력하세요!">
			        	${data.an_cktext}
			        </textarea>
			    </li>
			</ul>
		</cr:forEach>
	</div>
	<div class="board_btn">
	    <input type="button" value="수정 취소" class="border_del" onclick="location.href='./notice_list';">
	    <input type="button" value="수정등록" class="border_add" onclick="noticeModFormSend()">
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
<script src="/resources/admin/js/notice_mod.js?v=3"></script>
</html>