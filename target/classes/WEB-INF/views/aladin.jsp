<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>


<div class="container-wrapper">
	<div class="container">
		<h2>알라딘 도서 검색</h2>

		<form action="<c:url value="/aladinSearch"/>">
			<div class="form-group">
				<input type="radio" name="queryType" value="title" checked="checked">제목
				<input type="radio" name="queryType" value="author">저자
				<input type="radio" name="queryType" value="publisher">출판사
			</div>

			<div class="form-group">

				<label for="bookname"></label> <input type="text"
					class="form-control" placeholder="검색어를 입력" name="bookname"
					style="width: 50%">
			</div>

			<button type="submit" class="btn btn-primary">Search</button>
		</form>

		<hr>
		<br>
		<br>




		<c:if test="${not empty bookItems}">
			<h2>검색 결과</h2>
<p>얍얍 나와라</p>

<table>
				<c:forEach var="book" items="${bookItems}">
					<tr>

						<td> <img src="${book.cover}" alt="image"/> </td> 
						<td><a href="${book.link}">${book.title}</a></td>
						<td>${book.author}</td>
						<td> <a href="<c:url value="/viewBook/${book.isbn}" />" > <i class="fa fa-info-circle fa_custom fa-3x"></i></a></td>

					</tr>
				</c:forEach>
			</table>


		</c:if>





		<br>
		<Br>
		<br>
		<hr>


	</div>
</div>




