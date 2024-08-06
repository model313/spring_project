<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Date today = new Date();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 리스트 페이지</title>
</head>
<body>
	<p>쿠폰리스트 총 개수 : <span id="total"></span></p>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>쿠폰명</th>
				<th>할인율</th>
				<th>사용 유/무</th>
				<th>만료기한</th>
			</tr>
		</thead>
		<tbody id="list">
			
		</tbody>
	</table>
	<!-- 페이지 번호 출력 -->
	<table border="1">
		<tr id="pages">
		
		</tr>
	</table>
</body>
<script src="./coupon_listapi.js?v=<%=today%>"></script>
</html>