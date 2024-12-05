export class email_check{
	
	//react, vue, => axios
	ajax_email(email){	//ajax method
		//fetch => axios보다 속도가 빠르다(약 4배), nginx에서 속도가 더 빨라진다
		fetch("./ajax9.do?email=" + email)
		.then(function(aa){
			//Back-end에서 응답을 받아 값을 처리하는 역할 .json, .xml, .text, .html
			return aa.text();
		})
		.then(function(bb){
			//결과 값 출력하는 역할
			console.log(bb);
		})
		.catch(function(error){
			//예외 처리로 오류 발생 시 출력
			console.log("Ajax 통신 오류 발생");
		});
		
	}
	
}