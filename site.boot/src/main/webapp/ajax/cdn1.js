function fileview(z){
	var reader = new FileReader();	//byte 단위로 객체를 새롭게 생성
	
	reader.onload = function(e){	//해당 페이지가 로드되었을 때 실행되는 함수
		console.log(e);
		//해당 아이디 태그의 src에
		//e.target -> 
		//document.getElementById("views").src = e.target.result;
		document.getElementById("views").src = e.srcElement.result;
	};
	//첨부 파일 실제 경로를 로드하여 출력
	reader.readAsDataURL(z.files[0]);
	
}


//CDN API로 파일 전송
function cdn_file(){
	frm.submit();
	
}