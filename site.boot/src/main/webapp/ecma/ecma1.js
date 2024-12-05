
//이벤트 함수
document.querySelector("#btn").addEventListener("click", function(){
	console.log("id 버튼 핸들링");
	new zzz().zbox();	//class를 호출하여 해당 메소드 호출
	new zzz().kbox(50);
});


document.querySelector("#btn").addEventListener("click", function(){
	console.log("class 버튼 핸들링");
	
});

//class 형태의 선언식
//클래스를 선언한 형태
var zzz = class box{	//해당 클래스는 회원 관련 모든 핸들링 메소드를 포함
	//가상의 메소드도 선언
	zbox(){
		console.log("연습");
	}
	
	kbox(v){
		let sum = v + 100;
		console.log(sum);
		console.log(zzz.name);	//해당 변수에 작동되고 있는 클래스명 출력
	}
	
}