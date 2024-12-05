export class delete_check{
	
	ajax_delete(arr){
		//arr : FormData 형태이기 때문에 JSON.stringify 형태로 전송 시 값은 전달되지 않는다
		fetch("./ajax11.do", {
			method : "POST",
			//headers : {"list" : "migyeong"},	//json 값 형태 전달
			body : new URLSearchParams(arr)
			//body : JSON.stringify(arr)
		})
		.then(function(aa){
			return aa.text();
		})
		.then(function(bb){
			console.log(bb);
		})
		.catch(function(error){
			console.log("Ajax 통신 오류 발생");
		});

	}
	
}