class box{	//class
	
	abc(data){	//return 메소드
		//var a = "";	//변수 정의할 때마다 메모리 할당
		this.msg = data + "님 환영합니다";	//this를 활용하여 해당 메소드에 가상 변수
		return this.msg;
	}
}

class box2 extends box {	//외부 클래스를 해당 클래스로 상속 받는다
	
	bbb(user){
		this.bx = this.abc(user);
		console.log(this.bx);
	}
}

var oc = new box2();	//class 객체화 하여 인스턴스 생성
oc.bbb("홍길동");			//해당 메소드 호출