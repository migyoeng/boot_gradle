<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript - AJAX(POST + JSON.stringify)</title>
</head>
<body>

<input type="checkbox" id="all" onchange="all_select(this.checked)">전체 선택<br>
<input type="checkbox" name="pd" value="pd1" class="pd" onchange="one_select()">상품 1<br>
<input type="checkbox" name="pd" value="pd2" class="pd" onchange="one_select()">상품 2<br>
<input type="checkbox" name="pd" value="pd3" class="pd" onchange="one_select()">상품 3<br>
<input type="checkbox" name="pd" value="pd4" class="pd" onchange="one_select()">상품 4<br>
<input type="checkbox" name="pd" value="pd5" class="pd" onchange="one_select()">상품 5<br>
<input type="button" value="전송" onclick="ajax()">

</body>

<script>
	function one_select(){
		var ea = document.getElementsByName("pd");
		var count = 0;
		for(var f=0; f<ea.length; f++){
			if(ea[f].checked == true){
				count += 1;
			}
		}
		
		if(count == ea.length){
			document.getElementById("all").checked = true;
		}
		else{
			document.getElementById("all").checked = false;
		}
	}

	function all_select(a){
		var ea = document.getElementsByName("pd").length;	//바르게 출력
		for(var f=0; f<ea; f++){
			document.getElementsByName("pd")[f].checked = a;
		}
		
	}

	function ajax(){
		var pd = document.getElementsByName("pd");
		var arr = new Object();	//빈 배열 Array, Object
		var cnt = 0;	//배열 번호를 순차적으로 입력
		for(var f=0; f < pd.length; f++){
			if(pd[f].checked == true){
				arr[cnt] = pd[f].value;
				cnt++;
			}
		}
		console.log(arr);
		
		var data;	//백엔드에게 전송하는 변수
		var ajax_data;	//ajax 통신
		ajax_data = new XMLHttpRequest();
		
		ajax_data.onreadystatechange = function(){
			if(ajax_data.readyState == 4){
				if(ajax_data.status == 200){
					console.log(this.response);
				}
			}
		}
		ajax_data.open("POST", "./ajax3.do", true);
		ajax_data.setRequestHeader("content-type", "application/json");
		ajax_data.send(JSON.stringify(arr));
	}

</script>
</html>