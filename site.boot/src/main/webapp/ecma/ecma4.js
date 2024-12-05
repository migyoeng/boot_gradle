export function idcheck(userid){
	if(userid == ""){
		alert("아이디를 입력하세요");
	}
	else {
		console.log(userid + "확인");
	}
}

export function sum(num1, num2){
	var max = num2;
	var min = num1;
	if(num1 > num2){
		max = num1;
		min = num2;
	}
	
	var sum = 0;
	for(var i = min; i <= max; i++){
		sum += i;
	}
	
	console.log("합계 : " + sum);
}


