$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	  
	  var button = $(event.relatedTarget); 
	  
	  var idLivro = button.data('codigo');
	  var tituloLivro = button.data('descricao');
	  
	  var modal = $(this);
	  var form = modal.find('form');
	  var action = form.data('url-base');
	  if(!action.endsWith('/')){
	  	action += '/';
	  }
	  
	  form.attr('action', action + idLivro);
	  
	  modal.find('.modal-body span').html('Tem certeza que deseja excluir o livro <strong>' + tituloLivro + '</strong>?');
});