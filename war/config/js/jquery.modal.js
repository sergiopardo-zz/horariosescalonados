/* 
 * Plugin para mostrar el modal.
 */

(function($){
	$.fn.extend({
		 closeModal: function(options_row){
			var idModal = $(this).attr('id');
			$('#' + idModal + '').css('display','none');
			$('.divblockpage_' + idModal + '').css('display','none');
		 },
		 openModal: function(options_row){
			defaults = {
                anchoModal: '',
				altoModal: '',
				posicionizq: '',
				posicionTop: '',
				cerrarModal: '',
				tituloModal: '',
				contenidoModal: '',
				informacionModal: '',
				contenidoBoton: ''			
            };
			
			var optRow = $.extend({}, defaults, options_row);
			var anchoModalR = '';
			var altoModalR = '';
			var posicionizqR = '';
			var posicionTopR = '';
			var cerrarModalR ='';
			var tituloModalR = '';
			var contenidoModalR = '';
			var informacionModalR = '';
			var contenidoBotonR = '';
			var idModal = $(this).attr('id');
			var $divCargo = $(this);
			
			$.each(optRow, function(k, val){
				switch(k){
					case 'anchoModal':
						anchoModalR = val;
					break;
					case 'altoModal':
						altoModalR = val;
					break;
					case 'posicionizq':
						posicionizqR = val;
					break;
					case 'posicionTop':
						posicionTopR = val;
					break;
					case 'cerrarModal':
						cerrarModalR = val;
					break;
					case 'tituloModal':
						tituloModalR = val;
					break;
					case 'contenidoModal':
						contenidoModalR = val;
					break;
					case 'informacionModal':
						informacionModalR = val;
					break;
					case 'contenidoBoton':
						contenidoBotonR = val;
					break;
					default:
					break;
				}
            });
			
			addblockdiv(idModal);
			
			var pageHeight = $(window.parent.document).height();
			var pageWidth = $(window.parent.document).width();
			
			var anM = anchoModalR;
			var altM = altoModalR;
			//Obtenemos posicion central
			var mleft = ( pageWidth - anM ) / 2;
			var mtop = ( pageHeight - altM ) / 2;
			
			$('.divblockpage_' + idModal + '').css({
				'text-align':'center',
				'position':'absolute',
				'top':'0',
				'left':'0',
				'margen':'0',
				'padding':'0',
				'height': pageHeight,
				'width': pageWidth,
				'background':'#000000',
				'background-color':'rgba(0,0,0,0.6)',
				'filter': 'alpha(opacity=60)',		
				'z-index':'10'
			});
			$('#' + idModal + '').css({ 
				'position':'absolute', 
				'left': posicionizqR + '%',
				'top': posicionTopR  + '%',
				'display':'block',
				'height': altoModalR + 'px',
				'width': anchoModalR + 'px',
				'border':'1px solid #fff',
				'box-shadow': '0px 2px 7px #292929',
				'-moz-box-shadow': '0px 2px 7px #292929',
				'-webkit-box-shadow': '0px 2px 7px #292929',
				'border-radius':'5px',
				'-moz-border-radius':'5px',
				'-webkit-border-radius':'5px',
				'background': '#FFFFFF', 
				'z-index':'50',
				'font-family': 'Arial Regular',
				'padding': '23px 20px 20px 30px'
			});
			$('.' + cerrarModalR + '').css({
				'background': 'url(img/Ic_Cerrar.png) no-repeat',
				'width': '18px',
				'height': '18px',
				'display': 'inline',
				'position': 'absolute',
				'top': '10px',
				'right': '10px',
				'cursor': 'pointer'
			});
			$('.' + tituloModalR + '').css({
				'font-size': '18px',
				'color': '#006EC1'
			});
			$('.' + contenidoModalR + '').css({
				'padding-top': '20px',
				'padding-bottom': '20px',
				'text-align':'center'
			});			
			$('.' + informacionModalR + '').css({
				'background': 'url(img/Ic_Informacion2.png) no-repeat 10px',
				'font-size': '14px',
				'color': '#363636',
				'padding': '10px 10px',
				'padding-left': '52px'
			});
			$('.' + contenidoBotonR + '').css({				
				'height': '30px',
				'margin-top': '30px',
				'margin-bottom': '10px',
				'text-align': 'center'
			});
			
			$(window).resize(function(){
				var pageHt = $(window.parent.document).height();
				var pageWh = $(window.parent.document).width();
				var altoP = $(document).height();
				var anchP = $(window).width();
				$('.divblockpage_' + idModal + '').css({
					'height': pageHt + 'px', 
					'width': pageWh + 'px',
					'background':'#000000',
					'background-color':'rgba(0,0,0,0.6)',				
					'filter': 'alpha(opacity=60)'
				});
				//Obtenemos el ancho del modal
				var wcnt = $('#' + idModal + '').width();
				var hcnt = $('#' + idModal + '').height();
				var mleftD = ( anchP - wcnt ) / 2;
				var mtopD = ( altoP - hcnt ) / 5;
				$('#' + idModal + '').css({
					'left': mleftD + 'px',
					'top': mtopD + 'px'
				});
			});
		 }
	});
	
	function addblockdiv(idModal){
		var block_div = $('<div class="divblockpage_' + idModal + ' style="-ms-filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=60); -moz-opacity:0.6; -khtml-opacity: 0.6;"></div>');
		$(block_div).appendTo('body');
	}
	
})(jQuery);