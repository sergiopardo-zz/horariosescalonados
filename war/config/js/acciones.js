$(document).ready(function(){
	//modal
	$('#mostrarModal').click(function(){			
		//Abrir modal
		$('#modalAviso').openModal({
			anchoModal: '450',
			altoModal: '157',
			posicionizq: '31',
			posicionTop: '63',
			cerrarModal: 'cerrarModal', 
			tituloModal: 'tituloModal',
			contenidoModal: 'contenidoModal',
			informacionModal: 'informacionModal',
			contenidoBoton: 'contenidoBoton'
		});		
		//CerrarModal
		$('#modalCerrar').click(function(){
			$('#modalAviso').closeModal();
		});		
	});
	
	//cambiar valor del select
	$('.valorSeleccionado').click(function(){
		if($(".primerOpcion").text() != $(this).text())
		{
			$(this).closest("div").closest("td").next().next().children(".inputMensajes").val('');
		}
		$(this).closest( "div.opcionesSelecciona" ).prev().text($(this).text());		
		$(this).closest( "div.opcionesSelecciona" ).prev().addClass('colorSeleccionado');
		$(this).closest("div").closest("td").next().children("#muestraMensaje").text($(this).text());
		$(this).closest("div").closest("td").next().next().children().removeAttr("readonly");
		
		var valorSeleccionado = $.trim($(this).closest( "div.opcionesSelecciona" ).prev().text());
		var posicion = $(this).closest( "div.opcionesSelecciona" ).prev();
		var textArea= $(this).closest( "div.contenidoSelecciona" ).parent().parent().children().eq(6).children();
		
		// .prop('tagName')
		// alert(textArea);
		
		if(valorSeleccionado!= "Selecciona"){
			posicion.removeClass("campoObligatorio");
			 if(valorSeleccionado === 'Rechazado'){
				textArea.removeAttr("readonly");
				if(textArea.val()!=''){
					textArea.removeClass("campoObligatorio");
				}else{
					textArea.addClass("campoObligatorio");
					textArea.focus();
				}							
			 }else{
				textArea.val('');
				textArea.attr('readonly', true);
				textArea.removeClass("campoObligatorio");
			 }
		}else{
			textArea.attr('readonly', true);
			textArea.val('');
			posicion.addClass("campoObligatorio");
		 }
		 
		 activaBoton();	
		
		//$("#muestraSolicitud").;
    });
	
	// validacione textarea autorizar
	
	$(".textLleno").bind("keyup blur change", function (){
			 $.each($(".textLleno"), function () {
				 if($(this).val()!=null || $(this).val()!= "undefined"){
					 if($(this).val()!= ""){
						 $(this).removeClass("campoObligatorio");
						 var valorSelec=$(this).parent().parent().children().eq(5).children().children().html();
						 //alert(valorSelec);
						 var posicionSelec=$(this).parent().parent().children().eq(5).children().children();
						 
						  if(valorSelec==='Aceptado'){
							  $(this).removeClass("campoObligatorio");
						  }
					}else{
						if(valorSelec==="Rechazado"){
							$(this).addClass("campoObligatorio");
						}
						if(valorSelec==="Aceptado"){
							$(this).removeClass("campoObligatorio");
						}
						// if(valorSelec)==="Selecciona"){
							// $(this).removeClass("campoObligatorio");
						// }
					 }
				 }
			});
			activaBoton();
		});
		
		// termina validaciones textarea autorizar
	
	// Habilita el boton Aceptar de Mensajes
	$(".fTextoMensajes").bind("keyup blur change", function (){
		if($('.campoObligatorio').val().length == 0){
			$(".muestraDeshabilitado").removeClass("noMostrar");
			$(".muestraReposo").addClass("noMostrar");
		}else{
			$(".muestraReposo").removeClass("noMostrar");
			$(".muestraDeshabilitado").addClass("noMostrar");
		}
	});	
	// Termina habilita el boton Aceptar de Mensajes	
	
	
	//muestra opciones del select
	$('.contenidoSelecciona').hover(
		function() {
			if ($(".hasDatepicker").val() != "") 
			$(this).children('.opcionesSelecciona').css('display','block');
			//$('.opcionesSelecciona').css('display','block');			
		}, function() {
			$(this).children('.opcionesSelecciona').css('display','none');
			//$('.opcionesSelecciona').css('display','none');
		}
	);
	
	//cambiar color del valor seleccionado "select"
    $('.primerOpcion').click(function(){
		if ($(this).prev('div').text()!='Selecciona'){			
			$(this).prev('div').removeClass('colorSeleccionado');
		}else{
			$(this).prev('div').addClass('colorSeleccionado');
		}
	});
	
	//Contro de radios
 	$(".radio-group li").bind("click", function(){
		$(".radio-group li").removeClass("radio-group-active");
		$(this).addClass("radio-group-active");
		val = $(this).attr("value");
		if(val =='seis' || val=='siete' || val=='ocho'){
			activaBoton();			
		}else{
			$(".muestraReposo").removeClass("noMostrar");
			$(".muestraDeshabilitado").addClass("noMostrar");			
		}
	});
	//Contro de checks
	$(".check1").bind("click", function(){
	var clase = $(".check1").attr('class');
		if(clase=='check1'){
			$(".check1").removeClass("check1");
			$(this).addClass("check2");
		}else{
			$(".check2").removeClass("check2");
			$(this).addClass("check1");
		}
	});
	
	$("#fechaDesde,#fechaHasta,#fechaMes").bind("change", function(){
		if($(this).val().length<1){
			$(this).addClass("campoObligatorio");
		}else{
			$(this).removeClass("campoObligatorio");
		}
		activaBoton();
	
	});
	
	//Funcion para la descarga de archivos.
	 $("#upload").on('change', function(e){
	// alert($(this).val());
        e.preventDefault();
		if($(this).val()!=null && $.trim($(this).val()).length >0){
        $("#iptExaminar").val($(this).val());
		 $("#seecionEnviar .muestraReposo").removeClass("noMostrar");
		 $("#seecionEnviar .muestraDeshabilitado").addClass("noMostrar");
		}
		
    });
	
	$("#upload_link").on('click', function(e){
        e.preventDefault();
        $("#upload:hidden").trigger('click');
    });
	
	//Funcion para cargar archivo
	$("#iptExaminar").bind("change keyup blur",function(e){
		if($(this).val() == null || $.trim($(this).val()).length == 0){
		 $("#seecionEnviar .muestraReposo").addClass("noMostrar");
		 $("#seecionEnviar .muestraDeshabilitado").removeClass("noMostrar");
		}
	});
	
	//funcion simula busca usuario
	$('.fBuscaUsuario').bind('click',  function(){
		if($('#ingresarUsuario').val()==='MXCXXX'){
			$('#nombreUsuario').val('JONATHAN SÁNCHEZ SÁNCHEZ');
			$('#nombreUsuario').removeClass("campoObligatorio");
		}else{
			$('#nombreUsuario').val('');
			$('#nombreUsuario').addClass("campoObligatorio");
		}
		activaBoton();
	});
	
	

});

function activaBoton(){
	if($('.campoObligatorio').length === 0){
		$(".muestraReposo").removeClass("noMostrar");
		$(".muestraDeshabilitado").addClass("noMostrar");
	}else{
		$(".muestraDeshabilitado").removeClass("noMostrar");
		$(".muestraReposo").addClass("noMostrar");
	}
}