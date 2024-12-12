<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX - API를 이용한 데이터 출력(Front-end가 출력)</title>
</head>
<body>

	<table border="1">
		<thead>
		<tr>
			<th>번호</th>
			<th>고객명</th>
			<th>연락처</th>
			<th>이메일</th>
			<th>등록일</th>
			<th>수정 / 삭제</th>
		</tr>
		</thead>
		<tbody id="datalist">
		
		</tbody>
	</table>

</body>

<!-- api 서버로 데이터를 이관 받음, api 서버로 수정 및 삭제 핸들링 -->
<script src="./ajax_api1.js?v=1"></script>
</html>