<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Book Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${book.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${book.nome}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Publisher</label>
		<div class="col-sm-10">${book.editora}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Edition</label>
		<div class="col-sm-10">${book.edicao}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Number of Pages</label>
		<div class="col-sm-10">${book.qtdPaginas}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Weight</label>
		<div class="col-sm-10">${book.peso}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">ISBN</label>
		<div class="col-sm-10">${book.isbn}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Category</label>
		<div class="col-sm-10">${book.categoria.descricao}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Language</label>
		<div class="col-sm-10">${book.language}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">eBook</label>
		<div class="col-sm-10">${book.contemEbook ? 'Yes' : 'No'}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Published On</label>
		<div class="col-sm-10"><fmt:formatDate value="${book.dataLancamento}" pattern="dd/MM/yyyy" /></div>
	</div>

</div>

<jsp:include page="fragments/footer.jsp" />

</body>
</html>