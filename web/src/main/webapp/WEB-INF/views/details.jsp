<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="detailBookModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 id="dtlNome" class="modal-title"></h4>
			</div>
			<div class="modal-body form-horizontal">
				
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Publisher</label>
					<label id="dtlEditora" class="col-sm-8 pad-top-7"></label>
				</div>
				
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Edition</label>
					<label id="dtlEdicao" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Number of Pages</label>
					<label id="dtlQtdPaginas" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Weight</label>
					<label id="dtlPeso" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">ISBN Code</label>
					<label id="dtlIsbn" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Category</label>
					<label id="dtlCategoria" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Language</label>
					<label id="dtlIdioma" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">e-Book Version</label>
					<label id="dtlEbook" class="col-sm-8 pad-top-7"></label>
				</div>
			
				<div class="row">
					<label class="col-sm-3 control-label cor-cinza">Release Date</label>
					<label id="dtlDataLancamento" class="col-sm-8 pad-top-7"></label>
				</div>
						
			</div>
		</div>
	</div>
</div>