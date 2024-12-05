<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMAScript - AJAX(POST 통신)</title>
</head>
<body>
<!-- form 태그 안에 submit 2개 이상 만들 수 없다 -->
<form id="frm">
데이터1 : <input type="checkbox" name="data" value="data1">
데이터2 : <input type="checkbox" name="data" value="data2">
데이터3 : <input type="checkbox" name="data" value="data3">
데이터4 : <input type="checkbox" name="data" value="data4">
<input type="button" value="데이터 삭제 요청" id="btn">
</form>
</body>

<script type="module">
import {delete_check} from "./ecma8.js";
document.querySelector("#btn").addEventListener("click", function(){
	var ea = document.getElementsByName("data").length;
	var count = 0;	//체크된 카운팅
	var w = 0;
	
	let formdata = new FormData();	//폼 데이터 형태 전송

	while(w < ea){
		if(document.getElementsByName("data")[w].checked == true){
			formdata.append("data", document.getElementsByName("data")[w].value);
			count++;
		}
		w++;
	}
	//console.log(formdata.getAll("data"));
	new delete_check().ajax_delete(formdata);	//배열로 생성된 값을 ajax 메소드로 인자값으로 전달
	
});

</script>
</html>