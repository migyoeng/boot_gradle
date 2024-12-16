//ajax 의 경우 form 태그와 name 속성을 사용하지 않음,,
//AJAX로 이미지 전송
function cdn_file(){
	var myfile = document.getElementById("mfile");
	var ajax;	//ajax 통신을 위한 변수
	let formdata = new FormData();	//form 형태의 태그 이용
	formdata.append("mfile", myfile.files[0]);	//첫번째 파일을 mfile 키명으로 등록
	
	ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(this.response);
		} else {
			alert("통신 오류 발생");
		}
	}
	ajax.open("POST", "./cdn_fileok.do", true);
	ajax.send(formdata);	//form에 있는 데이터를 Back-end로 전송
}