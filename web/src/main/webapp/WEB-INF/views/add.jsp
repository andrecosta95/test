<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="fragments/header.jsp" />

<div class="container">

	<h1>Add Book</h1>
	<br />

	<form id="frmAddBook" class="form-horizontal" autocomplete="off">
		
		<div class="alert alert-dismissible" role="alert" style="display:none;">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<label id="lblMsgAlert" style="font-weight: bold;"></label>
		</div>
		
		<div id="divNome" class="form-group">
			<label class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<input id="txtNome" type="text" class="form-control" placeholder="Name" />
				<label id="lblErrorNome" class="control-label" style="display: none;"></label>
			</div>
		</div>

		<div id="divEditora" class="form-group">
			<label class="col-sm-2 control-label">Publisher</label>
			<div class="col-sm-10">
				<input id="txtEditota" type="text" class="form-control" placeholder="Publisher" />
				<label id="lblErrorEditora" class="control-label" style="display: none;"></label>
			</div>
		</div>

		<div id="divEdicao" class="form-group">
			<label class="col-sm-2 control-label">Edition</label>
			<div class="col-sm-10">
				<input id="txtEdicao" type="text" class="form-control" placeholder="Edition" />
				<label id="lblErrorEdicao" class="control-label" style="display: none;"></label>
			</div>
		</div>

		<div id="divQtdPaginas" class="form-group">
			<label class="col-sm-2 control-label">Number of Pages</label>
			<div class="col-sm-10">
				<input id="txtQtdPaginas" type="text" class="form-control" placeholder="Number of Pages" />
				<label id="lblErrorQtdPaginas" class="control-label" style="display: none;"></label>
			</div>
		</div>
	
		<div id="divPeso" class="form-group">
			<label class="col-sm-2 control-label">Weight</label>
			<div class="col-sm-10">
				<input id="txtPeso" type="text" class="form-control" placeholder="Weight" />
				<label id="lblErrorPeso" class="control-label" style="display: none;"></label>
			</div>
		</div>
		
		<div id="divISBN" class="form-group">
			<label class="col-sm-2 control-label">ISBN Code</label>
			<div class="col-sm-10">
				<input id="txtISBN" type="text" class="form-control" placeholder="ISBN Code" />
				<label id="lblErrorISBN" class="control-label" style="display: none;"></label>
			</div>
		</div>
		
		<div id="divCategoria" class="form-group">
			<label class="col-sm-2 control-label">Category</label>
			<div class="col-sm-5">
				<select id="cmbCategoria" class="form-control">
					<option value="-1">Select a Category</option>
				</select>
				<label id="lblErrorCategoria" class="control-label" style="display: none;"></label>
			</div>
		</div>
		
		<div id="divIdioma" class="form-group">
			<label class="col-sm-2 control-label">Language</label>
			<div class="col-sm-10">
				<label class="radio-inline">
					<input name="rdbIdioma" type="radio" value="pt-BR" /> Portuguese
				</label> 
				<label class="radio-inline">
					<input name="rdbIdioma" type="radio" value="en-US" /> English
				</label><br/>
				<label id="lblErrorIdioma" class="control-label" style="display: none;"></label>
			</div>
		</div>
		
		<div id="divEbook" class="form-group">
			<label class="col-sm-2 control-label">eBook</label>
			<div class="col-sm-10">
				<div class="checkbox">
					<label>
						<input id="chkEbook" type="checkbox" />
					</label>
					<label id="lblErrorEbook" class="control-label" style="display: none;"></label>
				</div>
			</div>
		</div>

		<div id="divDataLancamento" class="form-group">
			<label class="col-sm-2 control-label">Release Date</label>
			<div id="datepicker" class="col-sm-10">
				<input class="form-control" id="txtDataLancamento" />
				<label id="lblErrorDataLancamento" class="control-label" style="display: none;"></label>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
			</div>
		</div>
	</form>

</div>

<jsp:include page="fragments/footer.jsp" />

<script>
	$(document).ready(function() {
		loadAddBookComponents();
	});
</script>

</body>
</html>