String.prototype.capitalize = function() {
    return this.replace(/(?:^|\s)\S/g, function(a) { return a.toUpperCase(); });
};

$(document).ready(function() {
    $("#txtEdicao,#txtQtdPaginas,#txtIsbn,#txtPeso").each(function() {
    	$(this).keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                 // Allow: Ctrl+A
                (e.keyCode == 65 && e.ctrlKey === true) ||
                 // Allow: Ctrl+C
                (e.keyCode == 67 && e.ctrlKey === true) ||
                 // Allow: Ctrl+X
                (e.keyCode == 88 && e.ctrlKey === true) ||
                 // Allow: home, end, left, right
                (e.keyCode >= 35 && e.keyCode <= 39)) {
                     // let it happen, don't do anything
                     return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        });
    });
    
    $('#datepicker input').datepicker({
    	format: "dd/mm/yyyy",
	  	todayBtn: "linked",
	  	language: "pt-BR",
	  	autoclose: true
	});
});

function loadAddBookComponents() {
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "categories",
		headers: {
	        Accept:'application/json;charset=utf-8'
	    },
		timeout : 100000,
		success : function(data) {
			if(data.code == 200)
				loadCategories(data);
		},
		error : function(e) {
			alert(e);
		}
	});
	
	$("#frmAddBook").submit(function(event) {
		event.preventDefault();
		addBookViaAjax();
	});
}

function loadCategories(data) {

	var categories = data.result;
	var combo = $('#cmbCategoria');
	$.each(categories, function(i, item) {
		var cat = categories[i];
		combo.append('<option value="'+ cat.id +'">'+ cat.descricao +'</option>');
	});
}

function deleteBookViaAjax(id) {
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "books/delete",
		data : JSON.stringify(id),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			
			if(data.code == 200) {
				removeAndShowMessage(data.message, id);
			}else{
				showMessage('alert-danger', data.message);
			}
		},
		error : function(e) {
			showMessage('alert-danger', 'An error occurred while deleting book, try again later.');
		}
	});
}

function removeAndShowMessage(msg, id) {
	
	$('.table').find('tr#' + id).remove();
	
	if($('.table tr').length == 1) {
		$('.table').append('<tr><td colspan="5">No books found.</td></tr>');
	}
	
	showMessage('alert-success', msg)
}

function addBookViaAjax() {
	
	var book = {};
	book["nome"] = $('#txtNome').val();
	book["editora"] = $('#txtEditora').val();
	book["edicao"] = $('#txtEdicao').val();
	book["qtdPaginas"] = $('#txtQtdPaginas').val();
	book["peso"] = $('#txtPeso').val();
	book["isbn"] = $('#txtIsbn').val();
	book["categoria"] = {id : $('#cmbCategoria').find(':selected').val(), descricao : $('#cmbCategoria').find(':selected').text()};
	book["idioma"] = $('input[name=rdbIdioma]').filter(':checked').val();
	book["contemEbook"] = $('#chkEbook').is(':checked');
	book["dataLancamento"] = $('#txtDataLancamento').val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "add",
		data : JSON.stringify(book),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			
			if(data.code == 200) {
				cleanAndShowMessage(data.message);
			}else if(data.code == 300) {
				handleValidationErrors(data.result);
			}else{
				showMessage('alert-danger', data.message);
			}
		},
		error : function(e) {
			showMessage('alert-danger', 'An error occurred while adding book, try again later.');
		}
	});
	
}

function cleanAndShowMessage(msg) {
	
	cleanAllErrors();
	
	$('#txtNome').val('');
	$('#txtEditora').val('');
	$('#txtEdicao').val('');
	$('#txtQtdPaginas').val('');
	$('#txtPeso').val('');
	$('#txtIsbn').val('');
	$('#cmbCategoria').val(-1);
	$('input[name=rdbIdioma]').filter(':checked').prop('checked', false);
	$('#chkEbook').removeAttr('checked');
	$('#txtDataLancamento').val('');
	
	showMessage('alert-success', msg);
}

function handleValidationErrors(errors) {
	
	cleanAllErrors();
	
	$.each(errors, function(i, item) {
		showErrors(errors[i].field, errors[i].message);
	});
}

function showBookDetailModal(id) {
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "books/get",
		data : JSON.stringify(id),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			
			if(data.code == 200) {
				loadBookDetails(data.result);
				$('#detailBookModal').modal();
			}else{
				showMessage('alert-danger', data.message);
			}
		},
		error : function(e) {
			showMessage('alert-danger', 'An error occurred while retrieving book, try again later.');
		}
	});
}

function loadBookDetails(book) {
	$('#dtlNome')			.text(book.nome);
	$('#dtlEditora')		.text(book.editora);
	$('#dtlEdicao')			.text(book.edicao);
	$('#dtlQtdPaginas')		.text(book.qtdPaginas);
	$('#dtlPeso')			.text(book.peso + 'g');
	$('#dtlIsbn')			.text(book.isbn);
	$('#dtlCategoria')		.text(book.categoria.descricao);
	$('#dtlIdioma')			.text(book.idioma == 'pt-BR' ? 'Portuguese' : 'English');
	$('#dtlEbook')			.text(book.contemEbook ? 'Yes' : 'No');
	$('#dtlDataLancamento')	.text(book.dataLancamento);
}

function cleanAllErrors() {
	cleanErrors('nome');
	cleanErrors('editora');
	cleanErrors('qtdPaginas');
	cleanErrors('isbn');
	cleanErrors('categoria');
	cleanErrors('idioma');
	cleanErrors('dataLancamento');
}

function cleanErrors(field) {
	var div = 'div' + field.capitalize();
	var label = 'lblError' + field.capitalize();
	
	$('#' + div).removeClass('has-error');
	$('#' + label).text('');
	$('#' + label).hide();
}

function showErrors(field, msg) {
	var div = 'div' + field.capitalize();
	var label = 'lblError' + field.capitalize();
	
	$('#' + div).addClass('has-error');
	$('#' + label).text(msg);
	$('#' + label).show();
}

function showMessage(css, msg) {
	$('.alert').addClass(css);
	$('#lblMsgAlert').text(msg);
	$('.alert').show();
}