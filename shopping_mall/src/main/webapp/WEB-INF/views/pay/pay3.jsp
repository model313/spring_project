<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String data1 = (String) request.getAttribute("goodcode");
String data2 = (String) request.getAttribute("price");

String mid = "INIpayTest";
String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";
String goodcode = data1; 
String mKey = SignatureUtil.hash(signKey, "SHA-256");
String timestamp = SignatureUtil.getTimestamp();
String orderNumber = mid+"_"+goodcode;	
String price = data2;

Map<String, String> signParam = new HashMap<String, String>();
signParam.put("oid", orderNumber);
signParam.put("price", price);
signParam.put("timestamp", timestamp);

/*
out.print(goodcode + "  " + price);
out.print(mKey);
*/

String signature = SignatureUtil.makeSignature(signParam);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 및 결제 최종 정보 확정, 결제 API 연동</title>
</head>
<script src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js"></script>
<body>
	<form id="frm">
		<input type="hidden" name="returnUrl" value="http://localhost:8080/pay/return_url">
		<input type="hidden" name="closeUrl" value="http://localhost:8080/pay/close">
		
		<input type="hidden" name="version" value="1.0">
		
		<input type="hidden" name="mid" value="<%=mid%>">
		<input type="hidden" name="oid" value="<%=orderNumber%>">
		<input type="hidden" name="price" value="<%=price%>">
		<input type="hidden" name="timestamp" value="<%=timestamp%>">
		<input type="hidden" name="signature" value="<%=signature%>">
		<input type="hidden" name="mKey" value="<%=mKey%>">
		<input type="hidden" name="currency" value="WON">
		
		<input type="hidden" name="goodname" value="<%=request.getAttribute("goodname")%>">
		
		<input type="hidden" name="buyername" value="<%=request.getAttribute("buyername")%>">
		<input type="hidden" name="buyertel" value="<%=request.getAttribute("buyertel")%>">
		<input type="hidden" name="buyeremail" value="<%=request.getAttribute("buyeremail")%>">
		
		<input type="hidden" name="rec_post" value="<%=request.getAttribute("rec_post")%>">
		<input type="hidden" name="res_way" value="<%=request.getAttribute("res_way")%>">
		<input type="hidden" name="rec_addr" value="<%=request.getAttribute("rec_addr")%>">
		
		<input type="hidden" name="gopaymethod" value="<%=request.getAttribute("gopaymethod")%>">
		
		<p>------상품정보------</p>
		상품코드 : <%=request.getAttribute("goodcode") %><br>
		상품명 : <%=request.getAttribute("goodname") %><br>
		상품개수 : <%=request.getAttribute("goodea") %><br>
		
		<p>------결제자정보------</p>
		결제자명 : <%=request.getAttribute("buyername") %><br>
		연락처 : <%=request.getAttribute("buyertel") %><br>
		이메일 : <%=request.getAttribute("buyeremail") %><br>
		
		<p>------배송정보------</p>
		수령 우편번호 : <%=request.getAttribute("rec_post") %><br>
		도로명 주소 : <%=request.getAttribute("res_way") %><br>
		상세 주소 : <%=request.getAttribute("rec_addr") %><br>
		
		<p>------결제금액 및 결제수단------</p>
		최종 결제금액 : <%=request.getAttribute("price") %><br>
		결제방식 : <%=request.getAttribute("gopaymethod") %><br>
		
		<br>
		
		<input type="button" value="결제확인" onclick="payok()">
	</form>
</body>
<script>
	function payok(){
		INIStdPay.pay("frm");
	}
</script>
</html>