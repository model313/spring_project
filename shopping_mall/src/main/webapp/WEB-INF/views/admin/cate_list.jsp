<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/category.css?v=6">
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
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
        </ol>
    </div>

</nav>
<main class="maincss">
    <section>    
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span>등록된 카테고리 ${listSize}건</span>
    <!-- 검색 기능 -->
    <form>
    <span>
        <select class="p_select1">
            <option>카테고리명</option>
            <option>카테고리코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
    </span>
	</form>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" name="cateAllCk" onclick="cateSelAll()"></li>
        <li>분류코드</li>
        <li>대메뉴 코드</li>
        <li>대메뉴명</li>
        <li>소메뉴 코드(사용안함)</li>
        <li>소메뉴명(사용안함)</li>
        <li>사용 유/무</li>
        <li>관리</li>
    </ul>
    <cr:choose>
    	<cr:when test="${listSize==0}">
    		<ul>
        		<li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    		</ul>
    	</cr:when>
    	<cr:otherwise>
    		<cr:forEach var="cateData" items="${resultList}">
				<ul>
					<li><input type="checkbox" name="cateCk" value="${cateData.ca_idx}" onclick="cateSel()"></li>
					<li style="text-align: left; text-indent: 5px;">${cateData.ca_class}</li>
					<li>${cateData.ca_code}</li>
					<li style="text-align: left; text-indent: 5px;">${cateData.ca_name}</li>
					<li>-</li>
					<li style="text-align: left; text-indent: 5px;">-<input type="hidden" name="ca_pdexists" value="${cateData.ca_pdexists}"></li>
					<li id="ca_use">${cateData.ca_use}</li>
					<li onclick="cateMod(${cateData.ca_idx})" style="cursor: pointer">[수정]</li>
					
				</ul>
    		</cr:forEach>
    	</cr:otherwise>
    </cr:choose>
    
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="/ico/double_left.svg"></li>
        <li><img src="/ico/left.svg"></li>
        <li>1</li>
        <li><img src="/ico/right.svg"></li>
        <li><img src="/ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button" onclick="cateSelDel()">
    <span style="float: right;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" onclick="location.href='./product_list';">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" onclick="location.href='./cate_write';">
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
<script src="/js/cate_list.js?v=6"></script>
</html>