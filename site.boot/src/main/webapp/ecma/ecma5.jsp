<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script Class로 핸들링</title>
</head>
<body>

	연락처 : <input type="text" id="tel1" maxlength="3"> - 
	<input type="text" id="tel2" maxlength="4"> - 연락처 : <input type="text" id="tel3" maxlength="4">
	<input type="button" value="인증번호 받기" id="btn">
	
	<br><br><br>
	
	<form id="frm" method="post" action="./ecmaok.do">
		아이디 : <input type="text" name="mid"><br>
		패스워드 : <input type="password" name="mpass"><br>
		<button type="submit">로그인</button>
	
	</form>
</body>

<script type="module">
import {tels} from "./ecma5.js";

//form submit의 경우
document.querySelector("#frm").addEventListener("submit", function(z){
	z.preventDefault();	//submit 전송이 안되도록 = return false 와 같음
	new tels().login_check();	//아이디 및 패스워드 체크 메소드 호출
});



//addEventListener : 이벤트를 발생시키기 위한 대기 모드
document.querySelector("#btn").addEventListener("click", function(){
	new tels().tel_check();
});

</script>
</html>