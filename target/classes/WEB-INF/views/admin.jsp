<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">

	<div class="container">
		<h2>Administrator Page</h2>
		<p class="lead">Book를 관리할 수 있는 페이지 입니다.</p>
	</div>
	
	<div class="container">
		<h2> <a href= "<c:url value="/admin/bookInventory" />" > Book Inventory Page </a> </h2>
		<p class="lead">Here you can view, check, modify the product Inventory !!!</p>
	</div>
	
</div>