var host = window.location.host;
//console.log(host);

//가상의 TCP 소켓을 오픈 및 연결(Back-end Mapping에 연결)
var chat = new StompJs.Client({
	brokerURL : "ws://" + host + "/gs-websocket"	//ws : websocket
});

//socket 서버 연결 실패 시 출력되는 함수
chat.onWebSocketError = function(error){
	console.log("소켓 서버 연결 실패: " + error);
}

//메세지를 주고 송/수신 오류 발생
chat.onStompError = function(error){
	alert("메세지 전송 오류 발생 " + error);
}

//socket 서버 연결 성공 시 출력되는 함수
chat.onConnect = function(z){
	setConnect(true);	//socket server 연결
	console.log("소켓 연결 서버 : " + z);	//z : 연결 정보
	//chat room을 생성하여 연결시키는 함수
	chat.subscribe("/room/geeting", function(z){
		showGreeting(JSON.parse(z.body).content)	//Back-end가 해당 메세지를 JSON 형태로 받음
	});
}

function showGreeting(msg){
	$("#greetings").append("<tr><td>"+ msg +"</td></tr>");
}


function connect(){	//ws에 접속 허가하는 역할
	chat.activate();
}

function disconnection(){	//ws 접속 종료
	chat.deactivate();	//stompjs
	setConnect(false);	//ws 완전 종료
	alert("소켓 서버 종료");
}

//메세지 주고 받는 API 경로
function sends(){
	chat.publish({
		destination : "/app/conchat",
		body : JSON.stringify({	//Front-end가 Back-end에게 AJAX로 값을 전송
			'name' : $("#name").val(),
			'msg' : $("#msg").val()
		})
	});
}

$(function(){	//이벤트 핸들링
	$("form").on("submit",(e) => e.preventDefault());
	$("#connect").click(()=>connect());
	$("#disconnect").click(()=>disconnection());
	$("#send").click(function(){
		sends();	//메세지를 전송하는 함수를 작동시킴
	});
});

function setConnect(c){	//핸들링 부분
	$("#connect").prop("disabled", c);
	$("#disconnect").prop("disabled", !c);
	if(c == true){
		$("#conversation").show();
	}
	else{
		$("#conversation").hide();
	}
	$("#greetings").html("");
}


