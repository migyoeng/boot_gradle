<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script(핸들링 이벤트 사용) - es7 응용</title>
</head>
<body>

	<input type="text" name="mid" id="mid"><br>
	<input type="button" value="중복확인" id="btn">
	
	<br><br><br>
	<!-- 
	문제 : 두 개의 숫자를 입력 받은 뒤 해당 숫자의 범위 안에 있는 모든 값을 더하여 결과를 출력하는 ES 코드 작성
	[출력 예시]
	첫번째 값 : 5
	두번째 값 : 8
	결과 : 5+6+7+8
	 -->
	첫번째 값 : <input type="text" maxlength="2" id="num1">
	두번째 값 : <input type="text" maxlength="2" id="num2">
	<input type="button" value="계산하기" id="btn2">
</body>

<script type="module">
import {idcheck, sum} from "./ecma4.js?v=1";

document.querySelector("#btn").addEventListener("click", function(){
	var data = document.querySelector("#mid");
	//var data = document.getElementById("mid");
	idcheck(data);	//export로 호출된 함수
});
document.querySelector("#btn2").addEventListener("click", function(){
	var num1 = document.querySelector("#num1");
	var num2 = document.querySelector("#num2");
	sum(num1, num2);	//export로 호출된 함수
});



</script>
</html>