var http;	//ajax 통신
var data;	//Back-end 에서 전달한 결과값 저장

http = new XMLHttpRequest();
http.onreadystatechange = function(){
	if(http.readyState == 4 || http.status == 200){
		data = JSON.parse(this.response);
		//console.log(data);
		html_view(data);
	}
}

http.open("GET", "./event_all", false);
http.send();

function html_view(data){
	var hv = document.getElementById("datalist");
	var ea = data["event_member"].length;
	console.log(ea);
	
	var views = "";
	var w = 0;
	do{
		views += `		
			<tr>
				<td>`+w+`</td>
				<td>`+data["event_member"][w]["mname"]+`</td>
				<td>`+data["event_member"][w]["tel_no"]+`</td>
				<td><input type="text" id="`+data["event_member"][w]["eidx"]+`" value="`+data["event_member"][w]["memail"]+`"></td>
				<td>`+data["event_member"][w]["event_data"]+`</td>
				<td>
				<input type="button" value="수정" onclick="member_update(`+data["event_member"][w]["eidx"]+`)">
				<input type="button" value="삭제" onclick="member_delete(`+data["event_member"][w]["eidx"]+`)">
				</td>
			</tr>`;
			w++;
	}while(w < ea);
	hv.innerHTML = views;
	/*
	<tr>
		<td>1</td>
		<td>고객명</td>
		<td>연락처</td>
		<td><input type="text" id="memail" value=""></td>
		<td>등록일</td>
		<td><input type="button" value="수정"><input type="button" value="삭제"></td>
	</tr>
	*/
}

function member_update(eidx){	//사용자 이메일 수정 요청
	var http;
	var data;
	var dataload = document.getElementById(eidx);
	//console.log(dataload.value);
	
	/*
	var arr = new Array();
	arr[0] = eidx;
	arr[1] = dataload.value;
	*/
	
	var patch_data = JSON.stringify(eidx + "," + dataload.value);
	//var patch_data = JSON.stringify({"edix":arr});
	console.log(patch_data);
	
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 || http.status == 200){
			if(this.response == "ok"){
				alert("해당 정보가 정상적으로 수정되었습니다.");
				window.location.reload();
			}
			else{
				alert("통신 불안정으로 수정이 불가능합니다.");
			}
		}
	}

	http.open("PATCH", "./event-update/"+patch_data, false);
	http.setRequestHeader("Content-Type", "application/json");
	http.send();
}

function member_delete(eidx){
	console.log(eidx);
}



