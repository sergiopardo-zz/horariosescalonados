function start() {
  $('#datepicker').datepicker({
      buttonImage: 'config/img/calendar.png',
      buttonImageOnly: true,
      buttonText: 'Seleccionar día',
      closeText: 'Cerrar',
      currentText: 'Hoy',
      dateFormat: 'dd/mm/yy',
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
      dayNamesMin: ['D','L','M','M','J','V','S'],
      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
      firstDay: 1,
      isRTL: false,
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      nextText: '',
      prevText: '',
      showMonthAfterYear: false,
      showOn: "button",
      showWeek: true,
      weekHeader: 'Sem',
      yearSuffix: ''
  });
  
  $('#fechaDesde').datepicker({  
      buttonImage: 'config/img/calendar.png',
      buttonImageOnly: true,
      buttonText: 'Seleccionar día',
      closeText: 'Cerrar',
      currentText: 'Hoy',
      dateFormat: 'dd/mm/yy',
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
      dayNamesMin: ['D','L','M','M','J','V','S'],
      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
      firstDay: 1,
      isRTL: false,
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      nextText: '',
      prevText: '',
      showMonthAfterYear: false,
      showOn: 'button',
      showWeek: true,
      weekHeader: 'Sem',
      yearSuffix: '',
	  maxDate: "0",
	  minDate: "-13M",
	  beforeShowDay: noWeekendsOrHolidays,
	  onClose: function( selectedDate ) {
	  var minDateObject= new Date();
	  $sD = new Date();
    $( "#fechaHasta" ).datepicker( "option", "minDate", selectedDate );
  }
});

$('#fechaHasta').datepicker({  
  buttonImage: 'config/img/calendar.png',
  buttonImageOnly: true,
  buttonText: 'Seleccionar día',
  closeText: 'Cerrar',
  currentText: 'Hoy',
  dateFormat: 'dd/mm/yy',
  dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
  dayNamesMin: ['D','L','M','M','J','V','S'],
  dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
  firstDay: 1,
  isRTL: false,
  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
  monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
  nextText: '',
  prevText: '',
  showMonthAfterYear: false,
  showOn: 'button',
  showWeek: true,
  weekHeader: 'Sem',
  yearSuffix: '',
  minDate: "0d",
  maxDate: "0d",
  beforeShowDay: $.datepicker.noWeekends
});

  $('#fechaSemana').datepicker({
      buttonImage: 'config/img/calendar.png',
      buttonImageOnly: true,
      dateFormat: 'dd/mm/yy',
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
      dayNamesMin: ['D','L','M','M','J','V','S'],
      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],      
	  firstDay: 1,
      isRTL: false,      
	  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      nextText: '',
      prevText: '',
      showOn: 'button',
      showWeek: true,
      weekHeader: 'Sem',
	  beforeShowDay: $.datepicker.noWeekends,	 
	  onSelect: function(value, date){ 		
		var week=$.datepicker.iso8601Week (new Date(date.selectedYear, date.selectedMonth, date.selectedDay));
		$(this).val((week<10?'0':'')+week+' '+date.selectedYear);
	  },
	  maxDate: '+0d',
	  minDate: diaActual()
  });
  
  $('#fechaMes').datepicker({  
      buttonImage: 'config/img/calendar.png',
      buttonImageOnly: true,
      buttonText: 'Seleccionar día',
      closeText: 'Cerrar',
      currentText: 'Hoy',
      dateFormat: 'MM yy',
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
      dayNamesMin: ['D','L','M','M','J','V','S'],
      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
      firstDay: 1,
      isRTL: false,
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      nextText: '',
      prevText: '',
      showMonthAfterYear: false,
      showOn: 'button',
      showWeek: true,
      weekHeader: 'Sem',
      yearSuffix: '',
	  maxDate: "today",
	  minDate: "-13M",
      beforeShowDay: $.datepicker.noWeekends
  });


/*
  var $arrows = $(
      ['<div class="arrows">',
      '<div class="double-arrow-left"></div>',
      '<div class="arrow-left"></div>',
      '<div class="date-name">Hoy</div>',
      '<div class="arrow-right"></div>',
      '<div class="double-arrow-right"></div></div>'].join('')
    ),
    $close = $('<div class="close-cal"></div>'),
    $datePicker = $('.hasDatepicker'),
    $title = $(
      ['<div class="help tooltip">',
      '<div class="callout"></div>',
      '<span>Tooltip</span></div>'].join('')
    );
*/
	
	var $arrows = $(
      ['<div class="arrows">',
      '<div class="double-arrow-left"></div>',
      '<div class="date-name">Hoy</div>',
      '<div class="double-arrow-right"></div></div>'].join('')
    ),
    $close = $('<div class="close-cal"></div>'),
    $datePicker = $('.hasDatepicker'),
    $title = $(
      ['<div class="help tooltip">',
      '<div class="callout"></div>',
      '<span>Tooltip</span></div>'].join('')
    );
	
  function calendar() {
    $('.ui-datepicker-title').before($title);

    $('.ui-datepicker-title').after($close);

    $('.ui-datepicker-header').after($arrows);

    $('.close-cal').on('click', function close() {
        $datePicker.datepicker('hide');
    });
	
	$( ".ui-datepicker-prev" ).click(function() {
		calendar();
	});
	
	$( ".ui-datepicker-next" ).click(function() {
		calendar();
	});
	
	
    $('.date-name').on('click', function today() {
      var date = new Date(),
        day = date.getDate(),
        month = date.getMonth() + 1;

      $('input').val([
          (day < 10 ? '0' : '') + day,
          (month < 10 ? '0' : '') + month,
          date.getFullYear()
      ].join('/'));

      $datePicker.datepicker('hide');
    });
		
	$('.double-arrow-left').on('click', function rewind() {
      arrow(-1, 'Year');
      calendar();
    });

    $('.double-arrow-right').on('click', function rewind() {
      arrow(1, 'Year');
      calendar();
    });	
	/*
    $('.ui-datepicker-prev').appendTo('.arrow-left');
    $('.ui-datepicker-next').appendTo('.arrow-right');

    $('.ui-datepicker-next span').on('click', calendar);
    $('.ui-datepicker-prev span').on('click', calendar);

    $('.arrow-left').on('click', function rewind() {
      arrow(-1, 'Month');
      calendar();
    });

    $('.arrow-right').on('click', function rewind() {
      arrow(1, 'Month');
      calendar();
    });

    $('.double-arrow-left').on('click', function rewind() {
      arrow(-1, 'Year');
      calendar();
    });

    $('.double-arrow-right').on('click', function rewind() {
      arrow(1, 'Year');
      calendar();
    });
	*/
	$('.ui-datepicker table tbody tr').hover(
		function() {
			if($(this).closest("div.ui-datepicker").parent().children().children().eq(3).children().children("div.moduloReportes").children().children().children().next().children().children("input").attr("id")=="fechaSemana")
			{
				$(this).addClass('ui-datepicker_tr');				
			}
		}, function() {
			if($(this).closest("div.ui-datepicker").parent().children().children().eq(3).children().children("div.moduloReportes").children().children().children().next().children().children("input").attr("id")=="fechaSemana")
			{
				$(this).removeClass('ui-datepicker_tr');
			}
		}
	)
  }

  function arrow(num, type) {
   // var calendarData = $.data($('#datepicker')[0]);
	var calendarData = $.data(($('#fechaDesde')[0]) || ($('#fechaHasta')[0]) || ($('#fechaSemana')[0]) || ($('#fechaMes')[0]));
	// alert(calendarData.datepicker['selected' + type]);
    calendarData.datepicker['current' + type] += num;
    calendarData.datepicker['draw' + type] += num;
    calendarData.datepicker['selected' + type] += num;

    $('[data-handler="prev"]').trigger('click');
    $('[data-handler="next"]').trigger('click');
  }
  
  /* create an array of days which need to be disabled */
	var disabledDays = ["01/01/2015","02/02/2015","16/03/2015","05/04/2015","01/05/2015","16/09/2015","16/11/2015","25/15/2015"];

	/* utility functions */
	function nationalDays(date) {
		var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
		for (i = 0; i < disabledDays.length; i++) {
			if($.inArray(d + '/' + (m+1) + '/' + y,disabledDays) != -1) {
				return [false];
			}
		}
		return [true];
	}
	function noWeekendsOrHolidays(date) {
		var noWeekend = jQuery.datepicker.noWeekends(date);
		return noWeekend[0] ? nationalDays(date) : noWeekend;
	}
	
	function minDateNew () {
		var today = new Date();
        var newMin; 
		var numDay=today.getDay();
		switch (numDay){
			case 0: 
				newMin='-4d';
			break;
			case 1:case 2:case 3:
				newMin='-5d';
			break;
			default:
				newMin='-3d';
			break;
		}
		
		return newMin;
    }
	
	function diaActual() {
		var dia = new Date();
        var diaSemana = dia.getDay();
		var cincoSemana;
		switch (diaSemana) 
		{
			case 0:
				cincoSemana = "-5w -1d";
			break;
			case 1:
				cincoSemana = "-5w -2d";
			break;
			case 2:
				cincoSemana = "-5w -3d";
			break;
			case 3:
				cincoSemana = "-5w -4d";			
			break;
			case 4:
				cincoSemana = "-5w -5d";
			break;
			case 5:
				cincoSemana = "-5w -6d";
			break;
			case 6:
				cincoSemana = "-5w -7d";
			break;
			default:
				cincoSemana = "-5w";
			break;
		}
		return cincoSemana;
    }

	$('.ui-datepicker-trigger').click(function() {
		calendar();
	});
}

$(document).ready(start);
