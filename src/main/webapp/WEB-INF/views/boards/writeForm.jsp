<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/boards/write" method="POST">
		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control" placeholder="Enter title" >
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8" name="content"></textarea>
		</div>
		<button id="btnSave" type="button" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>

<script>
	$("#btnSave").click(()=>{
		save(); //save 메서드
		//saveTest();
	});
	/* function saveTest(){
		let data = {
				title: $("#title").val(),
				content: $("#content").val()
			};
		console.log(data.content);
	} */
	function save(){
		let data = {
				title: $("#title").val(),
				content: $("#content").val()
			};
				$.ajax("/boards/write", {
					type: "POST",
					dataType: "json",
					data: JSON.stringify(data),
					headers: {
						"Content-Type": "application/json" // 나 json 타입으로 줄거야
					}
				}).done((res) => {
					if (res.code == 1) {
						location.href = "/";
					} else {
						alert("제대로 처리되지 않았습니다");
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

