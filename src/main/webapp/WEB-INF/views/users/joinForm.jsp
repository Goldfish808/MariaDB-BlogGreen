<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/join" method="POST">
		<div class="mb-3 mt-3">
			<input id="username" type="text" class="form-control" placeholder="Enter username">
			<button id="btnUsernameSameCheck" class="btn btn-primary" type="button">중복확인</button>
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control" placeholder="Enter email" >
		</div>
		<button id="btnJoin" type="button" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>
	let isNameSameCheck = false; // 회원가입 버튼을 눌렀을 때 아이디 중복확인 안되면 submit 안되도록
	
	//2번째 
	$("#btnJoin").click(()=>{
		
		if(isNameSameCheck == false){
			alert("중복체크를 진행해주세요"); // 아래에 중복체크 AJAX 에서 중복체크 후 true 를 리턴하지 않으면 
			return; 
		}
		let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val(),
		};
		
		if(data.username && data.password && data.email){
			$.ajax("/join",{
				type: "POST",
				dataType: "json",
				data: JSON.stringify(data),
				headers : {
					"Content-Type" : "application/json" // 나 json 타입으로 줄거야
				}
			}).done((res)=>{
				if(res.code == 1){
					location.href = "/loginForm";
				}else{
					alert("제대로 처리되지 않았습니다");
				}
			});
		}else{
			alert("입력되지 않은 값이 있습니다");
		}
		
	});
	
	$("#btnUsernameSameCheck").click(()=>{
		//0.통신 오브젝트 생성 :JSON 통신을 위해서
// 		let body = {
// 			username: ""	
// 		};
		// 1. 사용자가 적은 username 값을 가져오기
		let username = $("#username").val();
		
		// "" 안에는 링크, {} 데이터  .done( --행위-- );
		//$.ajax("",{}).done();
		
// 		function end(){	}  를 --행위-- 에 집어넣을 때 익명함수로 집어넣는다
// 		1. 원형 done(function end(){});
// 		2. 익명 done(function(){});
// 		3. 람다식 done(()=>{}); 이 형태로 사용함
		
		// 2. Ajax 통신
		//통신이 끝난 후의 값을 변수 res로 넘긴다
		$.ajax("/users/usernameIsSame?username="+username,{ //username 은 위의 let 으로 받은거임
			type: "Get",
			dataType: "json" //dataType 이 없을 때에는 디폴트 값이 Json ( javaScript 오브젝트가 파싱된 ) 이다
			//async: true //이벤트는 무조건 비동기로 , .. 동기면 네임체크하는동안 다른걸 못함
		}).done((res)=>{
			console.log(res);
			if(res.code == 1){ // 통신성공 
				if(res.data == false){
					alert("아이디가 중복되지 않았습니다");
					isNameSameCheck = true;
				}else{
					alert("아이디가 중복되었어요, 다른아이디 사용하세요");
					isNameSameCheck = false;
					$("#username").val(""); // value 를 비워주기
				}
			}
		});
	});
</script>
<%@ include file="../layout/footer.jsp"%>

