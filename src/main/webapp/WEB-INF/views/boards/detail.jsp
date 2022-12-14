<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<input id="page" type="hidden" value="${sessionScope.refferer.currentPage}">
<input id="keyword" type="hidden" value="${sessionScope.refferer.keyword}">
<div class="container">
	<br /> <br />

	<c:if test="${principal.id == boardsContent.usersId}">
		<div class="d-flex">
			<a href="/boards/${boardsContent.id}/updateForm"
				class="btn btn-warning">수정하러가기</a>
			<form>
				<input id="id" type="hidden" value="${boardsContent.id}">
				<button id="btnDelete" class="btn btn-danger">삭제</button>
			</form>
		</div>
	</c:if>

	<br />
	<div class="d-flex justify-content-between">
		<h3>${boardsContent.title}</h3>
		<div>
			<i id="iconHeart" class="fa-regular fa-heart"></i> : 10
		</div>
	</div>
	<hr />
	<input id="boardsId" type="hidden" value="${boardsContent.id}">
	<input id="usersId" type="hidden" value="${principal.id}">
	<div>${boardsContent.content}</div>
</div>

<script>
$("#btnDelete").click(()=>{
	deleteById();
});
function deleteById(){
	let back = {
		page: $("#page").val(),
		keyword: $("#keyword").val()
	};
	let id = $("#id").val();
	
	$.ajax("/boards/" + id, { //id 값을 받을 때, 스크립트 에서는 EL 표현식을 안쓴다 왜냐하면 파일을 따로 빼면 톰캣이 안먹음
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			// location.href = document.referrer;  referrer 는 이전 페이지의 주소값을 가진다 
			location.href = "/?page="+back.page+"&keyword="+back.keyword;
		} else {
			alert("삭제에실패했습니다");
		}
	});
}


	$("#iconHeart").click((event)=>{ //event 변수를 적은자리에 click 됐을 때의 정보가 들어옴 
		//console.log(event.target); // 들어오는 정보 중 target 정보만 체크 해본다
		
		/* j 쿼리로 css 를 변경 해본다 */
		let check = $("#iconHeart").hasClass("fa-regular"); // 불린 형태로 리턴 받는다
		let data ={
				usersId: $("#usersId").val(),
				boardsId: $("#boardsId").val()
		};
		if(data.usersId == false){
			location.href = "/loginForm";
		}
		if(check == true){
			$("#iconHeart").removeClass("fa-regular");
			$("#iconHeart").addClass("fa-solid");
			$("#iconHeart").css("color", "red");
			$.ajax("/api", {
				type: "POST",
				dataType: "json",
				data: JSON.stringify(data),
				headers: {
				"Content-Type": "application/json" // 나 json 타입으로 줄거야
				}
			}).done((res) => {
				if(res.code == 1){
					alert("좋아요 콜백");
				}
			});
		}else{
			$("#iconHeart").removeClass("fa-solid");
			$("#iconHeart").addClass("fa-regular");
			$("#iconHeart").css("color", "black");
			console.log("좋아요가 해제되었습니다.");
			$.ajax("/api", {
				type: "DELETE",
				dataType: "json",
				data: JSON.stringify(data),
				headers: {
				"Content-Type": "application/json" // 나 json 타입으로 줄거야
				}
			});
		}
	});
</script>

<%@ include file="../layout/footer.jsp"%>

