<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<body>
	<div id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<c:url var="loginUrl" value="/user/login" />
					<form method="post" class="form-horizontal" action="${loginUrl }">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>아이디 또는 패스워드가 잘못되었습니다</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						<div class="input-group input-sm">
                             <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                             <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                        </div>
						<div class="input-group input-sm">
							<div class = "checkbox">
								<label><input type = "checkbox" id = "rememberme" name = "remember-me">Remember Me</label>
							</div>
						</div>
						<%-- <input type = "hidden" name = "${_csrf.parameterName }" value = "${_csrf.token }" /> --%>
						
						<div class="form-actions">
							<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>