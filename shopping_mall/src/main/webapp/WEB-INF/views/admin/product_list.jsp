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
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/product.css?v=6">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/imagemodal.css?v=3">
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
<main class="maincss">
<section>
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 ${listSize}건</span>
	<form>
    <span>
        <select class="p_select1">
            <option>상품명</option>
            <option>상품코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="상품검색" class="p_submit">
    </span>
	</form>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" name="prodAllCk" onclick="prodSelAll()"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
    <cr:choose>
    	<cr:when test="${listSize==0}">
    		<ul>
        		<li style="width: 100%;">등록된 상품이 없습니다.</li>
    		</ul>
    	</cr:when>
    	<cr:otherwise>
		    <cr:forEach var="data" items="${resultList}">
			    <ul>
			        <li><input type="checkbox" name="prodCk" value="${data.pr_idx}" onclick="prodSel()"></li>
			        <li>${data.pr_code}</li>
			        <li>
			        	<input type="button" value="보기" title="이미지 상세보기" class="p_image" onclick="imageModalOpen(${data.pr_idx})">
			        	<div id="imageModal${data.pr_idx}" class="modal">
			        		<span class="close" onclick="imageModalClose(${data.pr_idx})">&times;</span>
			        		<img class="modal-content" src="/upload/${data.pr_file1url}">
			        		<div class="caption">${data.pr_file1name}</div>
		        			<cr:if test="${data.pr_file2name!=''}">
				        		<img class="modal-content" src="/upload/${data.pr_file2url}">
				        		<div class="caption">${data.pr_file2name}</div>
		        			</cr:if>
		        			<cr:if test="${data.pr_file3name!=''}">
				        		<img class="modal-content" src="/upload/${data.pr_file3url}">
				        		<div class="caption">${data.pr_file3name}</div>
		        			</cr:if>
			        	</div>
		        	</li>
			        <li>${data.pr_name}</li>
			        <li>
			        	${data.pr_caname}
			        	<input type="hidden" name="caname" value="${data.pr_caname}">
		        	</li>
			        <li><fmt:formatNumber value="${data.pr_price}" pattern="#,###"/>원</li>
			        <li><fmt:formatNumber value="${data.pr_dcprice}" pattern="#,###"/>원</li>
			        <li>${data.pr_dc}%</li>
			        <li>${data.pr_stock} EA</li>
			        <li>${data.pr_selluse}</li>
			        <li>${data.pr_earlystockuse}</li>
			        <li>관리</li>
			    </ul>
		    </cr:forEach>
    	</cr:otherwise>
    </cr:choose>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="/resources/admin/ico/double_left.svg"></li>
        <li><img src="/resources/admin/ico/left.svg"></li>
        <li>1</li>
        <li><img src="/resources/admin/ico/right.svg"></li>
        <li><img src="/resources/admin/ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button" onclick="prodSelDel()">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1" onclick="location.href='./product_write';">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" onclick="location.href='./cate_list';">
    </span>
</div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/resources/admin/js/product_list.js?v=5"></script>
</html>