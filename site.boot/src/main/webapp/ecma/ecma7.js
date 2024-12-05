export class delete_check{
	
	ajax_delete(arr){
		console.log(arr);
		fetch("./ajax10.do", {
			method : "POST",
			headers : {"Content-type" : "application/json"},	//json 값 형태 전달
			body : JSON.stringify(arr)
			/*
			headers : {"Content-type" : "application/x-www-form-urlencoded"},	//name 값 형태 전달
			body : "list=" + arr
			*/
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