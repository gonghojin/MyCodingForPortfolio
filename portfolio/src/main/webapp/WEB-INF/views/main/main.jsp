<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>

<!-- Header -->
<header class="masthead">
	<div class="container">
		<div class="intro-text">
			<div class="intro-lead-in">Welcome To Our Studio!</div>
			<div class="intro-heading">It's Nice To Meet You</div>
			<a class="btn btn-xl js-scroll-trigger" href="#services">Tell Me
				More</a>
		</div>
	</div>
	
</header>



<!-- Portfolio Grid -->
<section class="bg-light" id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Portfolio</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
				<button id="portfolioRegister" class="btn btn-ml">Portfolio
					Register</button>
			</div>
		</div>
		<div class="row">
			<c:forEach var="board" items="${list }" varStatus="status">
				<div class="col-md-4 col-sm-6 portfolio-item">
					<a class="portfolio-link" data-toggle="modal"
						href="#portfolioModal" data-bno = "${board.bno }">
						<div class="portfolio-hover">
							<div class="portfolio-hover-content">
								<i class="fa fa-plus fa-3x"></i>
							</div>
						</div> <img class="img-fluid"
						src="<c:url value ='/resources/img/portfolio/02-thumbnail.jpg' />"
						alt="" />
					</a>
					<div class="portfolio-caption">
						<h4 id="boardTitle">${board.title }</h4>
						<p class="text-muted">${board.writer }</p>
						<a class = "btn btn-primary btn-xs" data-toggle = "modal" data-target = "#modifyModal" data-bno = "${board.bno }">Modify</a>
					</div>
				</div>
			</c:forEach>

		</div>
		<div>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test = "${pageMaker.prev }">
						<li class="page-item"><a class="page-link" href="<c:url value = '/main${pageMaker.makeSearch(pageMaker.startPage -1) }' />">Previous</a></li>
					</c:if>
					<c:forEach begin = "${pageMaker.startPage }" end = "${pageMaker.endPage }" var = "idx">
						<li class = "page-item <c:out value = "${pageMaker.reqPage.page == idx? 'active': '' }" />"> <a class="page-link" href="<c:url value = '/main${pageMaker.makeSearch(idx) }' />">${idx }</a></li>
					</c:forEach>
					<c:if test = "${pageMaker.next }">
						<li class="page-item"><a class="page-link" href="<c:url value = "/main${pageMaker.makeSearch(pageMaker.endPage +1) }" /> ">Next</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
		<div class="form-row align-items">
			<div class="col-auto">
				<select class="custom-select">
					<option value =""selected>Open this select menu</option>
					<option value="t">Title</option>
					<option value="w">Writer</option>
				</select>
			</div>
			<div class="col-auto">
				<input type="text" class="form-control"
					aria-label="Text input with dropdown button" id = "keywordInput">
			</div>
			<div class="col-auto">
				<button type="button" class="btn btn-primary" id = "searchBtn">Search</button>
			</div>

		</div>
	</section>
<!-- Services -->
<section id="services">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Services</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-shopping-cart fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">E-Commerce</h4>
				<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Minima maxime quam architecto quo inventore harum
					ex magni, dicta impedit.</p>
			</div>
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-laptop fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">Responsive Design</h4>
				<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Minima maxime quam architecto quo inventore harum
					ex magni, dicta impedit.</p>
			</div>
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-lock fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">Web Security</h4>
				<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Minima maxime quam architecto quo inventore harum
					ex magni, dicta impedit.</p>
			</div>
		</div>
	</div>
</section>
<!-- About -->
<section id="about">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">About</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<ul class="timeline">
					<li>
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="<c:url value = '/resources/img/about/1.jpg' />" alt="" />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>2009-2011</h4>
								<h4 class="subheading">Our Humble Beginnings</h4>
							</div>
							<div class="timeline-body">
								<p class="text-muted">Lorem ipsum dolor sit amet,
									consectetur adipisicing elit. Sunt ut voluptatum eius sapiente,
									totam reiciendis temporibus qui quibusdam, recusandae sit vero
									unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
							</div>
						</div>
					</li>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="<c:url value = '/resources/img/about/2.jpg' />" alt="" />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>March 2011</h4>
								<h4 class="subheading">An Agency is Born</h4>
							</div>
							<div class="timeline-body">
								<p class="text-muted">Lorem ipsum dolor sit amet,
									consectetur adipisicing elit. Sunt ut voluptatum eius sapiente,
									totam reiciendis temporibus qui quibusdam, recusandae sit vero
									unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
							</div>
						</div>
					</li>
					<li>
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="<c:url value = '/resources/img/about/3.jpg' />" alt="" />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>December 2012</h4>
								<h4 class="subheading">Transition to Full Service</h4>
							</div>
							<div class="timeline-body">
								<p class="text-muted">Lorem ipsum dolor sit amet,
									consectetur adipisicing elit. Sunt ut voluptatum eius sapiente,
									totam reiciendis temporibus qui quibusdam, recusandae sit vero
									unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
							</div>
						</div>
					</li>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="<c:url value = '/resources/img/about/4.jpg' />" alt="" />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>July 2014</h4>
								<h4 class="subheading">Phase Two Expansion</h4>
							</div>
							<div class="timeline-body">
								<p class="text-muted">Lorem ipsum dolor sit amet,
									consectetur adipisicing elit. Sunt ut voluptatum eius sapiente,
									totam reiciendis temporibus qui quibusdam, recusandae sit vero
									unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
							</div>
						</div>
					</li>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<h4>
								Be Part <br>Of Our <br>Story!
							</h4>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</section>

<!-- Team -->
<section class="bg-light" id="team">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Our Amazing Team</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="team-member">
					<img class="mx-auto rounded-circle"
						src="<c:url value = '/resources/img/team/1.jpg' />" alt="" />
					<h4>Kay Garland</h4>
					<p class="text-muted">Lead Designer</p>
					<ul class="list-inline social-buttons">
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-twitter"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-facebook"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-linkedin"></i>
						</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="team-member">
					<img class="mx-auto rounded-circle"
						src="<c:url value = '/resources/img/team/2.jpg' />" alt="" />
					<h4>Larry Parker</h4>
					<p class="text-muted">Lead Marketer</p>
					<ul class="list-inline social-buttons">
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-twitter"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-facebook"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-linkedin"></i>
						</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="team-member">
					<img class="mx-auto rounded-circle"
						src="<c:url value = '/resources/img/team/3.jpg' />" alt="" />
					<h4>Diana Pertersen</h4>
					<p class="text-muted">Lead Developer</p>
					<ul class="list-inline social-buttons">
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-twitter"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-facebook"></i>
						</a></li>
						<li class="list-inline-item"><a href="#"> <i
								class="fa fa-linkedin"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 mx-auto text-center">
				<p class="large text-muted">Lorem ipsum dolor sit amet,
					consectetur adipisicing elit. Aut eaque, laboriosam veritatis, quos
					non quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
			</div>
		</div>
	</div>
</section>

<!-- Clients -->
<section class="py-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-sm-6">
				<a href="#"> <img class="img-fluid d-block mx-auto"
					src="<c:url value = '/resources/img/logos/envato.jpg' />" alt="" />
				</a>
			</div>
			<div class="col-md-3 col-sm-6">
				<a href="#"> <img class="img-fluid d-block mx-auto"
					src="<c:url value = '/resources/img/logos/designmodo.jpg' />"
					alt="" />
				</a>
			</div>
			<div class="col-md-3 col-sm-6">
				<a href="#"> <img class="img-fluid d-block mx-auto"
					src="<c:url value = '/resources/img/logos/themeforest.jpg' />"
					alt="" />
				</a>
			</div>
			<div class="col-md-3 col-sm-6">
				<a href="#"> <img class="img-fluid d-block mx-auto"
					src="<c:url value = '/resources/img/logos/creative-market.jpg' />"
					alt="" />
				</a>
			</div>
		</div>
	</div>

</section>

<!-- Contact -->
<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Contact Us</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<form id="contactForm" name="sentMessage" novalidate>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input class="form-control" id="name" type="text"
									placeholder="Your Name *" required
									data-validation-required-message="Please enter your name.">
								<p class="help-block text-danger"></p>
							</div>
							<div class="form-group">
								<input class="form-control" id="email" type="email"
									placeholder="Your Email *" required
									data-validation-required-message="Please enter your email address.">
								<p class="help-block text-danger"></p>
							</div>
							<div class="form-group">
								<input class="form-control" id="phone" type="tel"
									placeholder="Your Phone *" required
									data-validation-required-message="Please enter your phone number.">
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<textarea class="form-control" id="message"
									placeholder="Your Message *" required
									data-validation-required-message="Please enter a message."></textarea>
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="col-lg-12 text-center">
							<div id="success"></div>
							<button id="sendMessageButton" class="btn btn-xl" type="submit">Send
								Message</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>



<!-- Portfolio Modals -->

<div class="portfolio-modal modal fade" id="portfolioModal"
	tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
			
			</div>
		</div>
	</div>
</div>
	<!-- modifyModal -->
	<div id="modifyModal" class="modal modal-primary fade" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModifyModal" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body" data-rno>
					<p>
						<input type="text" id="modifyTitle" class="form-control" >
					</p>
					<p>
						<input type="text" id="modifyContent" class="form-control">
					</p>
					<p id = "errorModal" style = "color:red; font-weight:bold;">
					</p>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="selectedBoardModBtn">Modify</button>
					<button type="button" class="btn btn-danger" id="selectedBoardDelBtn">DELETE</button>
					<button type="button" class="btn btn-default closeModifyModal" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
<%@ include file="../include/footer.jsp"%>

<script id = "template" type = "text/x-handlebars-template">
	<div class="row">
					<div class="col-lg-8 mx-auto">
						<div class="modal-body">
							<!-- Project Details Go Here -->
							<h2 id="modal-title">{{title}}</h2>
							<p class="item-intro text-muted">Lorem ipsum dolor sit amet
								consectetur.</p>
							<img class="img-fluid d-block mx-auto"
								src="<c:url value = '/resources/img/portfolio/06-full.jpg' />"
								alt="" />
							<p id = "modal-content">{{content}}</p>
							<ul class="list-inline">
								<li>{{prettifyDate regdate}}</li>
								<li id = "modal-writer">{{writer}}</li>
								<li>Category: Photography</li>
							</ul>
							<button class="btn btn-primary" data-dismiss="modal"
								type="button">
								<i class="fa fa-times"></i> Close Project
							</button>
						</div>
					</div>
				</div>
</script>
<script>
	$(document).ready(function(){
		//포트폴리오 등록 버튼
		$("#portfolioRegister").on("click", function(){
			self.location = "${pageContext.request.contextPath}/board/newboard${pageMaker.makeSearch(reqPage.page)}"
		});
		
		
	});
	
	// 게시판 글 상세 보기
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		var hours = dateObj.getHours();
		var minute = dateObj.getMinutes();
		return year + "/" + month + "/" + date + "/" +hours + "시" +minute + "분";
	});
	
	function getBoard(boardInfo){
		$.getJSON(boardInfo, function(board){
			printData(board);	
			
			$("#modifyModal").modal("hide");
		});
	}
	$("#portfolioModal").on("show.bs.modal", function(event){
		var modal = $(this);
		var bno = $(event.relatedTarget).data('bno');
		getBoard("${pageContext.request.contextPath}" + "/board/" + bno);
		
	
	});
	
	var printData = function(board){
		var template = Handlebars.compile($("#template").html());
		
		var html = template(board);
		$("#portfolioModal .container").children().remove();
		$("#portfolioModal .container").append(html);
		
		
	}
	// 게시판 글 수정
	var bno;
	
	$("#modifyModal").on("show.bs.modal", function(event){
		bno = $(event.relatedTarget).data('bno');
		var url = "${pageContext.request.contextPath}" + "/board/" + bno;
		$.getJSON(url, function(data){
			title = $("#modifyTitle").val(data.title);
			content = $("#modifyContent").val(data.content);
		});
	});
	
	$('.closeModifyModal').on('click', function(){
		$('#errorModal').empty();
		
	});
	
	$("#selectedBoardModBtn").on("click", function(){
		var title = $("#modifyTitle").val();
		var content = $("#modifyContent").val();
		
		
		$.ajax({
			
			type: "PUT",
			url: "${pageContext.request.contextPath}/board/" + bno,
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			
			dataType: "text",
			data: JSON.stringify({title: title, content: content}),
			
			success:function(board){
				console.log("result :" + board);
				if(board == "success"){
					alert("수정되었습니다");
					window.location.reload();
				}
			},
			error: function(e){
				
				var provideErrorMessage =  e.responseText;
				$('#errorModal').text(provideErrorMessage);
			}
		});
		
		
	});
	
	//게시글 삭제 Start
	$('#selectedBoardDelBtn').on("click", function(){
		$.ajax({
			type: 'delete',
			url: '${pageContext.request.contextPath}/board/' + bno,
			headers: {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'delete',
			},
			dataType: 'text',
			success: function(result){
				if(result == "success"){
					alert('삭제되었습니다');
					window.location.reload();
				}
			}
		});
	});
	// - End
	
	//검색 버튼 START
		$('#searchBtn').on("click", function(event){
			self.location = "${pageContext.request.contextPath}/main${pageMaker.makeQuery(1)}"
							+ "&searchType=" + $('select option:selected').val() + "&keyword="
							+ encodeURIComponent($('#keywordInput').val());
		});
	// - END
</script>