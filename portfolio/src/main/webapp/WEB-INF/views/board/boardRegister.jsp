<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link
	href="<c:url value = '/resources/vendor/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value = '/resources/dist/css/skins/_all-skins.min.css' />"
	rel="stylesheet">
<link href="<c:url value = '/resources/dist/css/AdminLTE.min.css' />"
	rel="stylesheet">


<style>
.fileDrop {
	width: 80%;
	height: 100px;
	border: 1px dotted gray;
	background-color: lightslategrey;
	margin: auto;
}
.has-error{
	color:red;
	font-weight:bold;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<form:form method="POST" modelAttribute="board" >
					<input type = "hidden" name = "page" value = "${reqPage.page }" />
					<input type = "hidden" name = "searchType" value = "${reqPage.searchType }" />
					<input type = "hidden" name = "keyword" value = "${reqPage.keyword }" />
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> 
							<form:input type="text"
								path = "title" name="title" class="form-control" placeholder="Enter Title" />
							<div class = "has-error">
								<form:errors path = "title" class="help-inline"/>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<form:textarea class="form-control" path = "content" name="content" row="3"
								placeholder="Enter....."></form:textarea>
							<div class = "has-error">
								<form:errors path = "content" class = "help-inline" />
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label> 
							<form:input type="text"
								path = "writer" name="writer" class="form-control" />
							<div class = "has-error">
								<form:errors path = "writer" class="help-inline"/>
							</div>
						</div>
						<div>
							<label for="exampleInputEmail1">File Drop Here</label>
							<div class="fileDrop"></div>
						</div>
					</div>
					<div class="box-footer">
						<div>
							<hr>
						</div>
						<ul class="mailbox-attachments clearfix uploadedList">
						</ul>
						<button type="submit" class="btn btn-primary">Submit</button>
						<button type="button" class="btn btn-warning">Cancel</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</section>

<script src="<c:url value = '/resources/vendor/jquery/jquery.js' />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".btn-warning").on("click", function(){
			//window.history.back();
			self.location = "${pageContext.request.contextPath}" +
			"/main?page=${reqPage.page}&searchType=${reqPage.searchType}&keyword=${reqPage.keyword}";
		});
	});	
</script>
</body>
</html>