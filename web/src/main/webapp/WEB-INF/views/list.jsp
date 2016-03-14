<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<body>
	
	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Books</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Publisher</th>
					<th>Published On</th>
					<th>Action</th>
				</tr>
			</thead>
			
			<c:if test="${empty books}">
				<tr>
					<td><fmt:message key="fiveware.bookshelf.empty" /></td>
				</tr>
			</c:if>
			
			<c:forEach var="book" items="${books}">
				<tr>
					<td>${book.id}</td>
					<td>${book.nome}</td>
					<td>${book.editora}</td>
					<td><fmt:formatDate value="${book.dataLancamento}" pattern="dd/MM/yyyy" /></td>
					<td>
						<spring:url value="/books/${book.id}" var="showBook" />
						<spring:url value="/books/${book.id}/delete" var="deleteBook" />
						
						<button class="btn btn-info" onclick="location.href='${showBook}'">Details</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteBook}')">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="fragments/footer.jsp" />

</body>
</html>