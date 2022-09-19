<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${boardsContent.id}">
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title" id="title"
				value="${boardsContent.title}" required="required" maxlength="50">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8" name="content" required="required">${boardsContent.content}</textarea>
		</div>
		<button id="btnUpdate" type="button" class="btn btn-primary">글 수정 완료</button>
	</form>
</div>

<script>
$("#btnUpdate").click(()=>{
	updateById();
});

function updateById(){
	let data = {
			title: $("#title").val(),
			content: $("#content").val()
	};
	let id = $("#id").val();

	$.ajax("/boards/"+id, { //id 값을 받을 때, 스크립트 에서는 EL 표현식을 안쓴다 왜냐하면 파일을 따로 빼면 톰캣이 안먹음
		type: "PUT",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8" // 나 json 타입으로 줄거야
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("수정에 성공하였습니다");
			location.href = "/boards/"+id;
		} else {
			alert("업데이트에 실패했습니다");
		}
	});
}
</script>

<script>
$('#content').summernote({
	height : 500
});

</script>
<%@ include file="../layout/footer.jsp"%>

