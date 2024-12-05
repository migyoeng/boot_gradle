export class tels{	//class 선언
	//생성자 - 클래스 호출 시 즉시 실행되는 메소드
	constructor(){
		console.log("즉시 실행되는 메소드");
	}
	
	login_check(){
		if(frm.mid.value == ""){
			alert("아이디를 입력하세요");
		}
		else if(frm.mpass.value == ""){
			alert("패스워드를 입력하세요");
		}
		else{
			frm.submit();
		}
	}
	
	
	//setter
	tel_check(){
		this.tel1 = document.querySelector("#tel1").value;
		this.tel2 = document.querySelector("#tel2").value;
		this.tel3 = document.querySelector("#tel3").value;
		this.total = this.tel1 + this.tel2 + this.tel3;
		if(this.total.length < 10){
			alert("휴대폰 번호를 모두 입력해 주셔야 합니다.");
		}
		else {
			alert("인증번호가 발송되었습니다.");
		}
	}
	
	//getter
	/*
	get tel_check(){
		
	}
	*/
	
}