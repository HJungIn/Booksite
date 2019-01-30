<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


	<div class="container-wrapper">
	<div class="container">
	
		<h2>All Books Inventory</h2>
		<p>내 db에 있는 책들!</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-info">
					<!-- <th>Photo Thumb</th> -->
					<th>Name</th>
					<th>Category</th>
					<th>Manufacturer</th>
					<th>Description</th>
					<th>Writer</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
<%-- 					<td> <img src="<c:url value="/resources/images/${product.imageFilename}" /> " alt="image" style="width:60%"/> </td> --%>
						<td>${book.name}</td>
						<td>${book.category}</td>
						<td>${book.manufacturer}</td>
						<td>${book.description}</td>
						<td>${book.writer}</td>
						<%-- <td> <a  href="<c:url value="/viewProduct/${product.id}" />">    <i class="fa fa-info-circle"> </i> </a>   </td>  --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>


