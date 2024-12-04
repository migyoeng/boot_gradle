<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색어에 따른 select 파트</title>
</head>
<body>

<form id="frm" method="post" action="./search.do" onsubmit="return abc()">
	검색 형태 : 
	<select name="part">
		<option value="1">상품명</option>
		<option value="2">상품코드</option>
	</select>
	<input type="text" name="search">
	<input type="submit" value="검색">
</form>

</body>

<script>
	function abc(){
		if(frm.search.value == ""){
			alert("검색어를 입력하세요");
			return false;
		}
		else{
			return true;
		}
	}

</script>
</html>