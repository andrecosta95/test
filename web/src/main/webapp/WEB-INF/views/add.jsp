<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<div class="container">

	<h1>Add Book</h1>
	<br />

	<spring:url value="/books" var="home" />

	<form:form class="form-horizontal" method="post" modelAttribute="bookForm" action="${home}">
		
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<form:hidden path="id" />

		<spring:bind path="nome">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="nome" type="text" class="form-control " id="txtNome" placeholder="Name" />
					<form:errors path="nome" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="editora">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Publisher</label>
				<div class="col-sm-10">
					<form:input path="editora" class="form-control" id="txtEditora" placeholder="Publisher" />
					<form:errors path="editora" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="edicao">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Edition</label>
				<div class="col-sm-10">
					<form:input path="edicao" class="form-control" id="txtEdicao" placeholder="Edition" />
					<form:errors path="edicao" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="qtdPaginas">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Number of Pages</label>
				<div class="col-sm-10">
					<form:input path="qtdPaginas" class="form-control" id="txtQtdPaginas" placeholder="Number of Pages" />
					<form:errors path="qtdPaginas" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="peso">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Weight</label>
				<div class="col-sm-10">
					<form:input path="peso" class="form-control" id="txtPeso" placeholder="Weight" />
					<form:errors path="peso" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="isbn">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">ISBN Code</label>
				<div class="col-sm-10">
					<form:input path="isbn" class="form-control" id="txtISBN" placeholder="ISBN Code" />
					<form:errors path="isbn" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="categoria">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Category</label>
				<div class="col-sm-5">
					<form:select path="categoria" class="form-control">
						<form:option value="-1" label="Select a Category" />
						<form:options items="${categories}" itemLabel="descricao" itemValue="id" />
					</form:select>
					<form:errors path="categoria" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>

		<spring:bind path="idioma">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Language</label>
				<div class="col-sm-10">
					<label class="radio-inline">
						<form:radiobutton path="idioma" value="pt-BR" /> Portuguese
					</label> 
					<label class="radio-inline">
						<form:radiobutton path="idioma" value="en-US" /> English
					</label><br/>
					<form:errors path="idioma" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="contemEbook">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">eBook</label>
				<div class="col-sm-10">
					<div class="checkbox">
						<label>
							<form:checkbox path="contemEbook" id="txtEbook" />
						</label>
						<form:errors path="contemEbook" class="control-label" />
					</div>
				</div>
			</div>
		</spring:bind>

		<spring:bind path="dataLancamento">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Published On</label>
				<div id="datepicker" class="col-sm-10">
					<form:input path="dataLancamento" class="form-control" id="txtDataLancamento" />
					<form:errors path="dataLancamento" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="fragments/footer.jsp" />

</body>
</html>