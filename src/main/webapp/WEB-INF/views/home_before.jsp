<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<html>
<head>

<!--  <meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>Login Demo - Kakao JavaScript SDK</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script> -->


	<title>Home</title>
	
	
</head>


<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>



<!--  <h3> 자바스크립트를 이용한 SDK</h3>
<a id="custom-login-btn" href="javascript:loginWithKakao()">
<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
</a>
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('8bfb077fe1ff3461eab0c74dada4c829');
    function loginWithKakao() {
    	
    	
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
        	
        	alert('성공!');
          //alert(JSON.stringify(authObj));
          
          	createKakaptalkLogout();

          
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    };
    
    
    
    
  //]]>
</script> -->








<!-- <h3> REST api를 이용한 SDK</h3>
<a id="custom-login-btn" href="https://kauth.kakao.com/oauth/authorize?client_id=7f1d4035cc185741819896f8acba02d4&redirect_uri=http://localhost:8081/oauth&response_type=code">
<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
</a> -->


<br>
<sec:authorize access="hasRole('ROLE_ANONYMOUS')">

<a href="${pageContext.request.contextPath}/login" >로그인</a>

</sec:authorize>

<sec:authorize access="hasRole('ROLE_USER')">

<a href="${pageContext.request.contextPath}/logout" >로그아웃</a>

</sec:authorize>


<a href="${pageContext.request.contextPath}/user" >user</a>







</body>
</html>
