<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript - AJAX(GET)</title>
</head>
<body>						<!-- this.checked : 체크하면 true, 미체크하면 false -->
<input type="checkbox" id="all" onchange="all_select(this.checked)">전체 선택<br> <!-- checked : checkbox, radio -->
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

	//getElement -> id, name (한 개만 있을 경우 - 이름 중복이 안될 경우)
	//getElements -> class, name (단, 같은 이름이 한 개 이상일 경우)
	function all_select(a){
		//body 구문 내 input의 name 속성은 form 이 없으면 그대로 가져올 수 없다
		//var ea = document.getElementByName("pd"); 오류남
		var ea = document.getElementsByName("pd").length;	//바르게 출력
		for(var f=0; f<ea; f++){
			document.getElementsByName("pd")[f].checked = a;
		}
		/*
		if(a == true){
			//전체 선택 칸을 체크했을 경우
			for(var f=0; f<ea; f++){
				document.getElementsByName("pd")[f].checked=true;
			}
		}
		else{
			for(var f=0; f<ea; f++){
				document.getElementsByName("pd")[f].checked=false;
			}
		}
		*/
		
		//var ea = document.getElementsByClassName("pd");
		//console.log(ea.length);
	}

	//ajax - GET 통신 - 선택된 상품만 Back-End로 전송
	//1. 같은 이름으로 문자열로 보낼 건지? product=pd2,pd3,pd4
	//2. 배열로 만들어서 보낼 건지? product = ['pd2', 'pd3', 'pd4']
	function ajax(){
		var pd = document.getElementsByName("pd");
		var arr = new Array();	//빈 배열
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
		
		ajax_data.open("GET", "./ajax1.do?product=" + arr, true);
		ajax_data.send();
	}

</script>
</html>