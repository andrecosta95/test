<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<th>Release Date</th>
					<th>Action</th>
				</tr>
			</thead>
			
			<c:if test="${empty books}">
				<tr>
					<td colspan="5"><fmt:message key="fiveware.bookshelf.empty" /></td>
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
						<button class="btn btn-danger" data-toggle="modal" data-target="#deleteBookModal" onclick="$('#deleteBookPath').val('${deleteBook}');">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>

		<input id="deleteBookPath" type="hidden" />

		<div class="modal fade" id="deleteBookModal" role="dialog">
    		<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Delete Book</h4>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete this book?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('.btn-danger').prop('disabled',true);post($('#deleteBookPath').val());">Yes</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>
		
	</div>

	<jsp:include page="fragments/footer.jsp" />

</body>
</html>