<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="messages" var="msgs" scope="session" />

<head>
<title>Fiveware Test</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" />
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap-datepicker.min.css" rel="stylesheet" />
</head>

<spring:url value="/" var="home" />
<spring:url value="/books/add" var="addBook" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${home}">Fiveware Bookshelf</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${addBook}">Add Book</a></li>
			</ul>
		</div>
	</div>
</nav>
