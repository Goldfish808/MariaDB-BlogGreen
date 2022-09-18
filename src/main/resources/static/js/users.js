// joinForm
let isNameSameCheck = false; // 회원가입 버튼을 눌렀을 때 아이디 중복확인 안되면 submit 안되도록


//2번째 
$("#btnJoin").click(() => {
	join();
});

$("#btnUsernameSameCheck").click(() => {
	checkUsername();
});

$("#btnLogin").click(() => {
	login();
	//myTest();
});


$("#btnDelete").click(() => {
	resign();
});

$("#btnUpdate").click(() => {
	update();
});



function join() {
	if (isNameSameCheck == false) {
		alert("중복체크를 진행해주세요"); // 아래에 중복체크 AJAX 에서 중복체크 후 true 를 리턴하지 않으면 
		return;
	}
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val()
	};

	if (data.username && data.password && data.email) {
		$.ajax("/join", {
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json" // 나 json 타입으로 줄거야
			}
		}).done((res) => {
			if (res.code == 1) {
				location.href = "/loginForm";
			} else {
				alert("제대로 처리되지 않았습니다");
			}
		});
	} else {
		alert("입력되지 않은 값이 있습니다");
	}
}

function checkUsername(){
	let username = $("#username").val();

	$.ajax("/users/usernameIsSame?username=" + username, { //username 은 위의 let 으로 받은거임
		type: "Get",
		dataType: "json" //dataType 이 없을 때에는 디폴트 값이 Json ( javaScript 오브젝트가 파싱된 ) 이다
		//async: true //이벤트는 무조건 비동기로 , .. 동기면 네임체크하는동안 다른걸 못함
	}).done((res) => {
		console.log(res);
		if (res.code == 1) { // 통신성공 
			if (res.data == false) {
				alert("아이디가 중복되지 않았습니다");
				isNameSameCheck = true;
			} else {
				alert("아이디가 중복되었어요, 다른아이디 사용하세요");
				isNameSameCheck = false;
				$("#username").val(""); // value 를 비워주기
			}
		}
	});
}

function myTest(){
	let remember = $("#remember").prop("checked"); //체크박스가 어떻게 보이는지 체크 
	console.log(remember); 
}

function login(){
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};
	$.ajax("/login", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8" // 나 json 타입으로 줄거야
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("로그인 실패");
		}
	});
}

function update(){
	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};
	let passwordCheck = $("#passwordSameCheck").val();
	let id = $("#id").val();

	if (data.password != passwordCheck) {
		alert("변경할 비밀번호가 일치하지 않습니다");
	} else {
		$.ajax("/users/" + id, { //id 값을 받을 때, 스크립트 에서는 EL 표현식을 안쓴다 왜냐하면 파일을 따로 빼면 톰캣이 안먹음
			type: "PUT",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json; charset=utf-8" // 나 json 타입으로 줄거야
			}
		}).done((res) => {
			if (res.code == 1) {
				alert("수정에 성공하였습니다");
				location.reload(); // 새로고침 F5 
			} else {
				alert("업데이트에 실패했습니다");
			}
		});
	}
}

function resign(){
	let id = $("#id").val();

	$.ajax("/users/" + id, { //id 값을 받을 때, 스크립트 에서는 EL 표현식을 안쓴다 왜냐하면 파일을 따로 빼면 톰캣이 안먹음
		type: "DELETE",
		dataType: "json"
		// 			data: JSON.stringify(data),
		// 			headers : {
		// 				"Content-Type" : "application/json; charset=utf-8" // 나 json 타입으로 줄거야
		// 			}  DELETE 일 때에는 바디가 없기 때문에 필요없다
	}).done((res) => {
		if (res.code == 1) {
			alert("탈퇴 완료");
			location.href = "/";
		} else {
			alert("탙퇴에실패했습니다");
		}
	});
}