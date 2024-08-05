<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<header class="headercss">
    <div class="header_div">
        <p><img src="/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p>관리자 : ${sessionScope.activeLoginUser }님 <a href="#">[개인정보 수정]</a> <a href="./admin_logout">[로그아웃]</a></p>
    </div>
</header>
<script src="/js/admin_top.js?v=2"></script>
</html>