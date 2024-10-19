<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<header class="headercss">
    <div class="header_div">
        <p onclick="location.href='/admin/admin_list';" class="custom_hover"><img src="/resources/admin/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p>관리자 : ${sessionScope.activeLoginUser }님 <a href="./admin_logout">[로그아웃]</a></p>
    </div>
</header>
<script src="/resources/admin/js/admin_top.js"></script>
</html>