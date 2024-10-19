<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 회원관리</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/login.css?v=3">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/main.css?v=3">
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
    <p>회원 리스트</p>
    <ol class="new_admin_title">
        <li>NO</li>
        <li>고객명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>이메일 수신</li>
        <li>SMS 수신</li>
        <li>가입일자</li>
        <li>상태</li>
        <li>정지여부</li>
    </ol>
    <cr:if test="${userListSize==0}">
		<ol class="new_admin_none">
		    <li>가입된 회원이 없습니다.</li>
		</ol>
    </cr:if>
    <cr:if test="${userListSize!=0}">
	    <ol class="new_admin_lists">
	        <li>1</li>
	        <li>한석봉</li>
	        <li>hansbong</li>
	        <li>01012345678</li>
	        <li>hansbong@hanmail.net</li>
	        <li>Y</li>
	        <li>N</li>
	        <li>2024-08-02</li>
	        <li>정상</li>
	        <li>
	            <input type="button" value="정지" class="new_addbtn1" title="정지">
	            <input type="button" value="해제" class="new_addbtn2" title="해제">
	        </li>
	    </ol>
    </cr:if>
</section>

<form id="agreeForm">
	<cr:forEach var="agreeData" items="${agreeListResults}">
	<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
	    <p style="font-size: 15px;font-weight: bolder; margin-bottom: 10px;">■ 이용 약관</p>
    	<cr:if test="${agreeListSize!=0}">
		    <textarea name="ag_useinfo" style="width: 100%; height: 150px; resize: none;">
${agreeData.ag_useinfo}
		    </textarea>
		    <input type="button" value="이용약관 수정" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
    	</cr:if>
    	<cr:if test="${agreeListSize==0}">
		    <textarea name="ag_useinfo" placeholder="이용약관에 대한 내용을 입력하세요" style="width: 100%; height: 150px; resize: none;">
		    
		    </textarea>
		    <input type="button" value="이용약관 수정" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
    	</cr:if>
	</section>
	<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
	    <p style="font-size: 15px;font-weight: bolder; margin-bottom: 10px;">■ 개인정보 수집 및 이용</p>
	    <cr:choose>
	    	<cr:when test="${agreeListSize!=0}">
			    <textarea name="ag_datauseinfo" placeholder="개인정보 수집 및 이용" style="width: 100%; height: 150px; resize: none;">
${agreeData.ag_datauseinfo}
			    </textarea>
			    <input type="button" value="이용약관 수정" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
	    	</cr:when>
	    	<cr:otherwise>
			    <textarea name="ag_datauseinfo" placeholder="개인정보 수집 및 이용" style="width: 100%; height: 150px; resize: none;">
			    	
			    </textarea>
	    		<input type="button" value="이용약관 등록" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
	    	</cr:otherwise>
	    </cr:choose>
	</section>
	</cr:forEach>
</form>

</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
</html>