function qr_make(){
	var html;	//ajax 변수
	var data = document.getElementById("scode");
	
	if(data.value == ""){
		alert("주문번호를 입력하세요.");
	}else{
		html = new XMLHttpRequest();
		html.onreadystatechange = function(){
			if(html.readyState == XMLHttpRequest.DONE && html.status == 200){
				if(this.responseText != "error"){
					document.getElementById("qrview").append(this.responseText);	//qr 경로 출력
					document.getElementById("qrimg").src = this.responseText;	//qr 경로에 맞는 QR 이미지 출력
				}
			}
		}
	}
	html.open("PUT","./qrmake/"+data.value, true);
	html.send();
}