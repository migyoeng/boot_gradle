<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMAScript - AJAX(GET 통신)</title>
</head>
<body>
<!-- form 태그 안에 submit 2개 이상 만들 수 없다 -->
<form id="frm">
이메일 인증 : <input type="text" name="email" id="email">
<input type="button" value="이메일 인증" id="btn">
</form>
</body>

<script type="module">
import {email_check} from "./ecma6.js";
document.querySelector("#btn").addEventListener("click", function(){
	if(frm.email.value == ""){
		alert("이메일을 입력해주세요");
	}
	else{
		new email_check().ajax_email(frm.email.value);
		
	}
});

</script>
</html>