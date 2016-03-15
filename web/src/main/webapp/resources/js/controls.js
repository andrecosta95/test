$(document).ready(function() {
	
	
    $("#txtEdicao,#txtQtdPaginas,#txtISBN,#txtPeso").each(function() {
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

function addBookViaAjax() {
	
	var book = {};
	book["nome"] = $('#txtNome').val();
	book["editora"] = $('#txtEditota').val();
	book["edicao"] = $('#txtEdicao').val();
	book["qtdPaginas"] = $('#txtQtdPaginas').val();
	book["peso"] = $('#txtPeso').val();
	book["isbn"] = $('#txtISBN').val();
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
				cleanAndShowSuccess(data.message);
			}else if(data.code == 300) {
				handleValidationErrors(data.result);
			}else{
				console.error(data.message);
			}
		},
		error : function(e) {
			console.error(e);
		}
	});
	
}

function cleanAndShowSuccess(msg) {
	
	$('#txtNome').val('');
	$('#txtEditota').val('');
	$('#txtEdicao').val('');
	$('#txtQtdPaginas').val('');
	$('#txtPeso').val('');
	$('#txtISBN').val('');
	$('#cmbCategoria').val(-1);
	$('input[name=rdbIdioma]').filter(':checked').prop('checked', false);
	$('#chkEbook').removeAttr('checked');
	$('#txtDataLancamento').val('');
	
	$('.alert').addClass('alert-success');
	$('#lblMsgAlert').text(msg);
	$('.alert').show();
}

function handleValidationErrors(errors) {
	
}


function post(path) {
	
	method = "post"; 

	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	document.body.appendChild(form);
	
	form.submit();
}

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

function addBookViaAjax() {
	
	var book = {};
	book["nome"] = $('#txtNome').val();
	book["editora"] = $('#txtEditota').val();
	book["edicao"] = $('#txtEdicao').val();
	book["qtdPaginas"] = $('#txtQtdPaginas').val();
	book["peso"] = $('#txtPeso').val();
	book["isbn"] = $('#txtISBN').val();
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
				cleanAndShowSuccess(data.message);
			}else if(data.code == 300) {
				handleValidationErrors(data.result);
			}else{
				console.error(data.message);
			}
		},
		error : function(e) {
			console.error(e);
		}
	});
	
}

function cleanAndShowSuccess(msg) {
	
	$('#txtNome').val('');
	$('#txtEditota').val('');
	$('#txtEdicao').val('');
	$('#txtQtdPaginas').val('');
	$('#txtPeso').val('');
	$('#txtISBN').val('');
	$('#cmbCategoria').val(-1);
	$('input[name=rdbIdioma]').filter(':checked').prop('checked', false);
	$('#chkEbook').removeAttr('checked');
	$('#txtDataLancamento').val('');
	
	$('.alert').addClass('alert-success');
	$('#lblMsgAlert').text(msg);
	$('.alert').show();
}

function handleValidationErrors(errors) {
	
}
