<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript - AJAX(POST + FormData)</title>
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
		//Formdata 전송
		var formdata = new FormData();	//<form> 형식으로 전송
		//formdata.append("product", "pd1");	//formdata.append("key 이름", "value")) - 동일 key 값 불가
		//formdata.append("product", "pd2");
		
		var cnt = 0;	//배열 번호를 순차적으로 입력
		for(var f=0; f < pd.length; f++){
			if(pd[f].checked == true){
				formdata.append("product", pd[f].value);
				cnt++;
			}
		}
		console.log(formdata.getAll("product"));
		
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
		ajax_data.open("POST", "./ajax4.do", true);
		
		//setRequestHeader : 어떤 방식으로 전송할 것인지 (content-type, application/json 또는 multipart/form-data)
		//ajax_data.setRequestHeader("content-type", "multipart/form-data");
		ajax_data.setRequestHeader("basket", "data");
		ajax_data.send(formdata);
	}

</script>
</html>