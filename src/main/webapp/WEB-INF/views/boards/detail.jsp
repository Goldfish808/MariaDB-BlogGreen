<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />

	<c:if test="${principal.id == boardsContent.usersId}">
		<div class="d-flex">
			<a href="/boards/${boardsContent.id}/updateForm" class="btn btn-warning">수정하러가기</a>
			<form>
				<button class="btn btn-danger">삭제</button>
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
	<div>${boardsContent.content}</div>
</div>

<script>
	$("#iconHeart").click((event)=>{ //event 변수를 적은자리에 click 됐을 때의 정보가 들어옴 
		//console.log(event.target); // 들어오는 정보 중 target 정보만 체크 해본다
		
		/* j 쿼리로 css 를 변경 해본다 */
		let check = $("#iconHeart").hasClass("fa-regular"); // 불린 형태로 리턴 받는다
		
		if(check == true){
			$("#iconHeart").removeClass("fa-regular");
			$("#iconHeart").addClass("fa-solid");
			$("#iconHeart").css("color", "red");
		}else{
			$("#iconHeart").removeClass("fa-solid");
			$("#iconHeart").addClass("fa-regular");
			$("#iconHeart").css("color", "black");
		}
	});
</script>

<%@ include file="../layout/footer.jsp"%>

