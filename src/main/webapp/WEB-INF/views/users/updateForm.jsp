<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br />
	<button id="btnDelete" class="btn btn-danger">회원탈퇴</button>
	<form>
	<input id="id" type="hidden" value="${users.id}"></input>
		<div class="mb-3 mt-3">
		
			<input type="text" class="form-control" placeholder="Enter username" readonly="readonly"
				value="${users.username}">
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control" placeholder="Enter password" 
			value="">
		</div>
		<div class="mb-3">
			<input id="passwordSameCheck" type="password" class="form-control" placeholder="Enter password" 
			value="">
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control" placeholder="Enter email" value="${users.email}">
		</div>
		<button id="btnUpdate" type="btnUpdate" class="btn btn-primary">수정완료</button>
	</form>
</div>

<script>
	$("#btnDelete").click(()=>{
		let id = $("#id").val();
		
		$.ajax("/users/"+id,{ //id 값을 받을 때, 스크립트 에서는 EL 표현식을 안쓴다 왜냐하면 파일을 따로 빼면 톰캣이 안먹음
			type: "DELETE",
			dataType: "json"
// 			data: JSON.stringify(data),
// 			headers : {
// 				"Content-Type" : "application/json; charset=utf-8" // 나 json 타입으로 줄거야
// 			}  DELETE 일 때에는 바디가 없기 때문에 필요없다
		}).done((res)=>{
			if(res.code == 1){
				alert("탈퇴 완료");
				location.href ="/";
			}else{
				alert("탙퇴에실패했습니다");
			}
		});
	});
	
	
	
	$("#btnUpdate").click(()=>{
		let data = {
				password: $("#password").val(),
				email: $("#email").val()
		};
		let passwordCheck = $("#passwordSameCheck").val();
		let id = $("#id").val();
		
		if(data.password != passwordCheck){
			alert("변경할 비밀번호가 일치하지 않습니다");
		}else{
			$.ajax("/users/"+id,{ //id 값을 받을 때, 스크립트 에서는 EL 표현식을 안쓴다 왜냐하면 파일을 따로 빼면 톰캣이 안먹음
				type: "PUT",
				dataType: "json",
				data: JSON.stringify(data),
				headers : {
					"Content-Type" : "application/json; charset=utf-8" // 나 json 타입으로 줄거야
				}
			}).done((res)=>{
				if(res.code == 1){
					alert("수정에 성공하였습니다");
					location.reload(); // 새로고침 F5 
				}else{
					alert("업데이트에 실패했습니다");
				}
			});
		}
		
	});
	</script>
	<%@ include file="../layout/footer.jsp"%>