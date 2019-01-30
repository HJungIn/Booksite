<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>


<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<title>Login Demo - Kakao JavaScript SDK</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


<title>Home</title>


</head>


<body>



	<div class="container-wrapper">
		<div class="container">
			<h2>Login with username and password</h2>
			
			<c:if test="${not empty errorMsg}">
				<div style="color:#ff0000;"> <h3>${errorMsg} </h3> </div>
			</c:if>
			
			<c:if test="${not empty logoutMsg}">
				<div style="color:#0000ff;"> <h3>${logoutMsg} </h3> </div>
			</c:if>
			
			<form action="<c:url value="/login"/>" method="post">
				<div class="form-group">
					<label for="username">USer name:</label> <input type="text"
						class="form-control" id="username" placeholder="Enter username"
						name="username" style="width: 50%">
				</div>

				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" id="pwd" placeholder="Enter password"
						name="password" style="width: 50%">
				</div>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>

		<br> <hr> <br>





		<div class="container">
			<h3>REST api를 이용한 SDK</h3>
			<a id="custom-login-btn"
				href="">
				<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300" />
			</a>
			<%--  ${refresh}

	 <script>
		self.location = '/';
	</script> --%>

			<br> <br> <br> <br> <br> <br>

		</div>
	</div>
</body>

</html>
