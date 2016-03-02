package com.gonet.horariosescalonados.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoCyge;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoRRHH;
import com.gonet.horariosescalonados.bean.BeanIncumplimiento;

public class CompletarReporteCumplimientoCyge {

	Locale spanish = new Locale("es", "ES");

	public List<BeanCumplimientoExternoCyge> ReporteCyge (List<BeanCumplimientoExternoCyge> registrosReporte)
	{

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");	
		DateTimeFormatter formatterHorario = DateTimeFormat.forPattern("HH:mm");
		DateTimeFormatter formatterJornada = DateTimeFormat.forPattern("HH:mm:ss");
		DateTime fecha = null;	

		for (BeanCumplimientoExternoCyge registro : registrosReporte)
		{
			fecha = new DateTime(registro.getStrFecha());
			//fecha = formatter.parseDateTime(registro.getStrFecha());

			registro.setStrMes(fecha.toString("MMM", spanish));

			registro.setStrQuincena(fecha.getDayOfMonth()<= 15 ?"Q1":"Q2");
			
			String strEntrada = registro.getStrEntradaReal();
			String strSalida = registro.getStrSalidaReal();
			
			DateTime horaEntradaReal =  (!(strEntrada.equals(HeConstantes.DATO_INVALIDO_REPORTE)))? formatterHorario.parseDateTime(strEntrada): null;
			DateTime horaSalidaReal =  (!(strSalida.equals(HeConstantes.DATO_INVALIDO_REPORTE))) ? formatterHorario.parseDateTime(strSalida) : null;

			if ((!(registro.getStrEntradaReal().equals(HeConstantes.DATO_INVALIDO_REPORTE))) && (!(registro.getStrSalidaReal().equals(HeConstantes.DATO_INVALIDO_REPORTE))))
			{
				if (horaEntradaReal.isAfter(horaSalidaReal)){
					registro.setStrJornada(HeConstantes.DATO_INVALIDO_REPORTE);
				}else{
				registro.setStrJornada(registro.getStrJornada());
				}
			}
			
			else 
			{
				registro.setStrJornada(HeConstantes.DATO_INVALIDO_REPORTE);
			}
		}

		return registrosReporte;

	}
	
	public List<BeanIncumplimiento> ReportesIncumplimiento (List<BeanIncumplimiento> registrosReporte)
	{

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");	
		DateTimeFormatter formatterHorario = DateTimeFormat.forPattern("HH:mm");
		DateTimeFormatter formatterJornada = DateTimeFormat.forPattern("HH:mm:ss");
		
		DateTime fecha = null;	

		for (BeanIncumplimiento registro : registrosReporte)
		{
			fecha = new DateTime(registro.getStrFecha());
			//fecha = formatter.parseDateTime(registro.getStrFecha());

			registro.setStrMes(fecha.toString("MMM", spanish));

			registro.setStrQuincena(fecha.getDayOfMonth()<= 15 ?"Q1":"Q2");
			
			String strEntrada = registro.getStrEntradaReal();
			String strSalida = registro.getStrSalidaReal();
			
			DateTime horaEntradaReal =  (!(strEntrada.equals(HeConstantes.DATO_INVALIDO_REPORTE)))? formatterHorario.parseDateTime(strEntrada): null;
			DateTime horaSalidaReal =  (!(strSalida.equals(HeConstantes.DATO_INVALIDO_REPORTE))) ? formatterHorario.parseDateTime(strSalida) : null;
			
			
			
			if ((!(registro.getStrEntradaReal().equals(HeConstantes.DATO_INVALIDO_REPORTE))) && (!(registro.getStrSalidaReal().equals(HeConstantes.DATO_INVALIDO_REPORTE))))
			{
				if (horaEntradaReal.isAfter(horaSalidaReal)){
					registro.setStrJornada(HeConstantes.DATO_INVALIDO_REPORTE);
				}else{
				registro.setStrJornada(registro.getStrJornada());
				}
			}
			
			else 
			{
				registro.setStrJornada(HeConstantes.DATO_INVALIDO_REPORTE);
			}
		}

		return registrosReporte;

	}
	
	


	public List <BeanCumplimientoExternoRRHH> ReporteRRHH (List <BeanCumplimientoExternoRRHH> registrosReporte)
	{

		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");	
		DateTime fecha = null;


		for (BeanCumplimientoExternoRRHH registro : registrosReporte)
		{
			//fecha = formatter.parseDateTime(registro.getFecha());
			fecha = new DateTime(registro.getFecha());

			registro.setMes(fecha.toString("MMM", spanish));

			registro.setQuincena(fecha.getDayOfMonth()<= 15 ? "Q1":"Q2");


			DateTimeFormatter formatterHorario = DateTimeFormat.forPattern("HH:mm");
			DateTimeFormatter formatterJornada = DateTimeFormat.forPattern("HH:mm:ss");
			 
			String strEntrada = registro.getEntradaReal();
			String strSalida = registro.getSalidaReal();

			DateTime horaEntradaOficial = formatterHorario.parseDateTime(registro.getEntradaOficial());
			DateTime horaEntradaReal =  (!(strEntrada.equals(HeConstantes.DATO_INVALIDO_REPORTE)))? formatterHorario.parseDateTime(strEntrada): null;
			DateTime horaSalidaReal =  (!(strSalida.equals(HeConstantes.DATO_INVALIDO_REPORTE))) ? formatterHorario.parseDateTime(strSalida) : null;
			DateTime toleranciaEntradaAntes = horaEntradaOficial.minusMinutes(15);
			DateTime toleranciaEntradaDespues = horaEntradaOficial.plusMinutes(15);
			DateTime horaSalidaOficial = horaEntradaOficial.plusHours(9);
			DateTime toleranciaSalidaAntes = horaSalidaOficial.minusMinutes(15);
			DateTime toleranciaSalidaDespues = horaSalidaOficial.plusMinutes(15);
			if (horaEntradaReal.isAfter(horaSalidaReal))
			{
				registro.setJornada("DATO INVÁLIDO");
			}
			
			DateTime jornada = formatterJornada.parseDateTime(registro.getJornada().equals("DATO INVÁLIDO") ? "00:00:00" : registro.getJornada());
			DateTime dt8horas = formatterJornada.parseDateTime("08:30:00");
			DateTime dt9horas = formatterJornada.parseDateTime("09:30:00");
			
			
			
			int intCalificacionSalida = ((horaSalidaReal != null)&&(horaSalidaReal.isBefore(toleranciaSalidaDespues)&& (horaSalidaReal.isAfter(toleranciaSalidaAntes)))? 1:0);
			int intCalificacionEntrada = ((horaEntradaReal != null)&&(horaEntradaReal.isBefore(toleranciaEntradaDespues)&& (horaEntradaReal.isAfter(toleranciaEntradaAntes)))? 1: 0);
			int intCalificacionJornada = ((jornada != null) && (jornada.isBefore(dt9horas)&& (jornada.isAfter(dt8horas))) ? 1 :0);
			int intCalificacionTotal = (intCalificacionSalida + intCalificacionEntrada + intCalificacionJornada);

			registro.setSalidaOficial(horaSalidaOficial.toString(formatterHorario));
			registro.setTEA(toleranciaEntradaAntes.toString(formatterHorario));
			registro.setTED(toleranciaEntradaDespues.toString(formatterHorario));
			registro.setTSA(toleranciaSalidaAntes.toString(formatterHorario));
			registro.setTSD(toleranciaSalidaDespues.toString(formatterHorario));
			registro.setCalificacionSalida((intCalificacionSalida+""));
			registro.setCalificacionEntrada(intCalificacionEntrada+"");
			registro.setCalificacionJornada(intCalificacionJornada+"");
			registro.setCalificacionTotal(intCalificacionTotal+"");

			//TODO: Convertir a decimal y limitar a dos decimales.

			int total = 3;
			int porcentaje = (intCalificacionTotal*100)/total;


			String strPorcentaje = Integer.toString(porcentaje);
			registro.setPorcentajeCumplimiento(strPorcentaje + "%");

			System.out.println(registro.toString());

		}

		return registrosReporte;
	}

}
