<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QR 생성기 - 주문 번호(URL)</title>
</head>
<body>

	주문 번호 : <input type="text" id="scode" maxlength="6"><br>
	<input type="button" value="QR 생성" onclick="qr_make()">
	<div id="qrview"></div>
	<img src="" id="qrimg">
</body>

<script src="./qr.js?v=1">
</script>
</html>