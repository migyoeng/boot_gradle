<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jquery - AJAX(GET + JSON.stringify)</title>
<script src="./jquery.js"></script>
</head>
<body>

	<input type="checkbox" id="all">전체 선택<br>
	<input type="checkbox" class="n" value="notice1">게시물1<br>
	<input type="checkbox" class="n" value="notice2">게시물2<br>
	<input type="checkbox" class="n" value="notice3">게시물3<br>
	<button type="button" id="btn">전송</button>
	
</body>

<script>
//jquery는 name 속성을 검토하지 못한다
//class와 id 만 불러올 수 있다
//jquery에서는 onclick()을 사용하지 않는다
$(function(){
	//attr(Attribute)
	//prop(Properties) : 지정한 태그의 해당 속성을 지정하여 값을 변경, 삭제, 삽입, 읽기를 할 수 있는 함수
	//attr 보다 prop가 더 빠른 속도를 가진다
	$("#all").change(function(){
		//= var ck = $("#all").checked;
		var $ck = this.checked;
		var $ea = $(".n").length;
		var $w = 0;
		do{
			$(".n").eq($w).prop("checked", $ck)	//eq() : jquery 배열 인덱스
			$w++;
		}while($w < $ea);
	});
	
	var $count = 0;	//체크된 체크박스 수를 반영하는 변수
	$(".n").change(function(){
		var $idx = $(".n").index(this);	//node 번호 확인하여 체크하는 상황
		console.log($idx);
	});
	
	//전송 버튼 클릭 시 AJAX
	$("#btn").click(function(){
		
		var $ajaxdata = new Array();	//빈 배열 0부터 순차적으로
		
		var $ea = $(".n").length;
		var $w = 0;
		var $arrno = 0;	//순차적 배열 추가를 적용하기 위한 변수 값
		do{
			//console.log($(".n").eq($w).checked);	//undefined - 체크 여부
			console.log($(".n").eq($w).is(":checked"));	//체크 여부!!
			console.log($(".n").eq($w).val());		//체크한 value 값 확인
			
			//if($(".n").eq($w).checked == true){	//undefined 로 인해 배열에 담기지 않는다
			if($(".n").eq($w).is(":checked") == true){
				$ajaxdata[$arrno] = $(".n").eq($w).val();
				$arrno++;
			}
			$w++;
		}while($w < $ea);
		
		//console.log($ajaxdata);
		
		var $fdata = JSON.stringify($ajaxdata);
		
		//$.get({});	//ajax - get 전송 방식(get mapping)
		//$.post({});	//ajax - post 전송 방식(post mapping)
		//contentType, data => 무조건 POST 방식, GET 방식은 url에 전달
		//encodeURI() : 배열의 언어셋을 세팅하여 보내는 형태
		$.ajax({
			url : "./ajax6.do?no=" + encodeURI($fdata),	//전송 url 경로
			cache : false,		//캐시 메모리 사용 유무
			type : "GET",		//전송 방식
			dataType : "html",	//전송 파일 형태 : HTML, TXT, XML, JSON
			//data : JSON.stringify($ajaxdata),	//배열 형태로 전송 - JSON.stringify는 Post방식일 경우 가능
			//contentType : "application/json",
			async : true,		//비동기(true), 동기화(false)
			success : function($data){
				console.log($data);
			},
			error : function(){
				console.log("API 서버에 접근이 되지 않습니다.");
			}
			
		});
		
		
	});
});

</script>
</html>