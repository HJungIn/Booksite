<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">
	<div class="container">
		<h2>알라딘 책 상세 페이지</h2>



		<div class="row">
			<div class="col-md-6">
				<img src="${bookDetail.cover}" alt="image" style="width: 40%" />
			</div>

			<div class="col-md-6">

				<br>
				<h3>
					<a href="${bookDetail.link}"> ${bookDetail.title} </a>
				</h3>
				<p>
					<b>작가 : </b>${bookDetail.author}</p>

				<p>
					<b>출판사 : </b>${bookDetail.publisher}</p>

				<br />
			</div>
		</div>

		<br>
		<hr>
		<br>
		<p>${bookDetail.description}</p>
		<br>
		<hr>
		<br>



<%-- disqus 디스커스를 이용한 댓글 기능 --%>
		<div id="disqus_thread"></div>
		<script>
			(function() { // DON'T EDIT BELOW THIS LINE
				var d = document, s = d.createElement('script');
				s.src = 'https://booksite-2.disqus.com/embed.js';
				s.setAttribute('data-timestamp', +new Date());
				(d.head || d.body).appendChild(s);
			})();
		</script>
		<noscript>
			Please enable JavaScript to view the <a
				href="https://disqus.com/?ref_noscript">comments powered by
				Disqus.</a>
		</noscript>









		<c:if test="${pageContext.request.userPrincipal.name == null }">
			로그인하고 와라
			
			<br>
			<Br>
			<br>
		</c:if>


		<c:if test="${pageContext.request.userPrincipal.name != null }">
			댓글 남길래?
			
			<br>
			<Br>
			<br>
		</c:if>




	</div>
</div>