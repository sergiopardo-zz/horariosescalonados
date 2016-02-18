package com.gonet.horariosescalonados.classes;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import com.gonet.horariosescalonados.bean.BeanCumplimiento;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanIncumplimiento;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExterno;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoCyge;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoRRHH;

/*
import com.gonet.horariosescalonados.datastore.Empleado;
import com.gonet.horariosescalonados.datastore.EntradaSalida;
import com.gonet.horariosescalonados.datastore.Horario;
import com.gonet.horariosescalonados.datastore.SolicitudData;
*/

import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.google.appengine.api.datastore.Entity;

public class Reportes {	
	public static String ENCABEZADO = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'> <html> <head><style type='text/css'> "
    +".tblCandidato {border: 0px solid #999; border-collapse: separate; width: 100%;}"
    +".tblCandidato tr th {border-radius:4px; -moz-border-radius: 4px; -webkit-border-radius : 4px; height: 22px;" 
    +"font-size: 12px; color: #fff; background-color: #069; text-align: center; vertical-align: middle;"
    +"font-weight: bold; padding: 0px 0px 0px 0px;}"
	+".tblCandidato tr td {height: 22px; font-size: 12px; background-color: #FFF;" 
    +"text-align: center; vertical-align: middle;}"
    +"</style> </head> <body>"
    +"<table width='80%' cellspacing='0' cellpadding='0' class='tblCandidato' bordercolor='#CCCCCC' border='1'>";
	
	
	public static String ALTA_BAJA_MODIF = "<thead>"
    		+ "<tr>"
    		+ "<th width='300px;'>NOMBRE</th>"
    		+ "<th width='150px;'>USUARIO</th>"    		
    		+ "<th width='150px;'>FECHA ALTA</th>"
    		+ "<th width='150px;'>ENTRADA ACTUAL</th>"
    		+ "<th width='150px;'>SALIDA ACTUAL</th>"
    		+ "<th width='150px;'>ENTRADA SOLICITADA</th>"
    		+ "<th width='150px;'>SALIDA SOLICITADA</th>"
    		+ "<th width='150px;'>USUARIO SUPERVISOR</th>"
    		+ "<th width='150px;'>NOMBRE SUPERVISOR</th>"
    		+ "<th width='150px;'>SOLICITUD</th>"
    		+ "<th width='150px;'>ESTATUS DE AUTORIZACION</th>"
    		+ "<th width='150px;'>FECHA DE RESPUESTA (AUTORIZADOR)</th>"
    		+ "<th width='150px;'>MOTIVO DEL RECHAZO (AUTORIZADOR)</th>"
    		+ "</tr>"
    		+ "</thead><tbody>";
	
	public static String CUMPLIMIENTO = "<thead>"
    		+ "<tr>"
    		+ "<th width='40%'>NOMBRE</th>"
    		+ "<th width='10%'>USUARIO</th>"    		
    		+ "<th width='10%'>NOMCR</th>"
    		+ "<th width='10%'>DGA (LN3)</th>"
    		+ "<th width='10%'>FECHA</th>"
    		+ "<th width='10%'>Quincena</th>"
    		+ "<th width='10%'>Mes</th>"
    		+ "<th width='10%'>TEA</th>"
    		+ "<th width='10%'>H Entrada Oficial</th>"
    		+ "<th width='10%'>TED</th>"
    		+ "<th width='10%'>H Entrada Real</th>"
    		+ "<th width='10%'>Calificación Entrada</th>"
    		+ "<th width='10%'>TSA</th>"
    		+ "<th width='10%'>H Salida Oficial</th>"
    		+ "<th width='10%'>TSD</th>"
    		+ "<th width='10%'>H Salida Real</th>"
    		+ "<th width='10%'>Calificación Salida</th>"
    		+ "<th width='10%'>Calificación Jornada</th>"
    		+ "<th width='10%'>Calificación Total</th>"
    		+ "<th width='10%'> Cumplimiento</th>" 
    		+ "</tr>"
    		+ "</thead><tbody>";
	//-------------------------------------------------------------------//
	public static String CUMPLIMIENTOEXTERNOCYGE = "<thead>"
    		+ "<tr>"
    		+ "<th width='10%'>No.Cyge</th>"
    		+ "<th width='40%'>Nombre</th>"
    		+ "<th width='10%'>Direccion General</th>"
    		+ "<th width='10%'>Direccion Corporativa</th>"
    		+ "<th width='10%'>Area</th>"
    		+ "<th width='10%'>Fecha</th>"
    		+ "<th width='10%'>Mes</th>"
    		+ "<th width='10%'>Quincena</th>"
    		+ "<th width='10%'>Entrada Oficial</th>"
    		+ "<th width='10%'>Entrada Real</th>"
    		+ "<th width='10%'>Salida Real</th>"
    		+ "<th width='10%'>Jornada</th>"
    		+ "<th width='10%'>Eestancia</th>"
    		+ "<th width='10%'>Edificio</th>"
    		+ "<th width='10%'>Autorizador</th>"
    		+ "<th width='10%'>Proveedor</th>"
    		+ "<th width='10%'>Proyecto</th>"
    		+ "<th width='10%'>Estatus</th>"
    		+ "<th width='10%'>Edificio Asignado</th>"
    		+ "</tr>"
    		+ "</thead><tbody>";
	

	public static String CYGE = "<thead>"
    		+ "<tr>"
    		+ "<th width='10%'>NoCyge</th>"
    		+ "<th width='10%'>Usuario</th>"    		
    		+ "<th width='10%'>nombre</th>"
    		+ "<th width='10%'>Apellido Paterno</th>"
    		+ "<th width='10%'>Apellido Materno</th>"
    		+ "<th width='10%'>Direccion General</th>"
    		+ "<th width='10%'>Direccion Corporativa</th>"
    		+ "<th width='10%'>Area</th>"
    		+ "<th width='10%'>Entrada Oficial</th>"
    		+ "<th width='10%'>Id Autorizador</th>"
    		+ "<th width='40%'>Autorizador</th>"
    		+ "<th width='10%'>Proveedor</th>"
    		+ "<th width='10%'>Proyecto</th>"
    		+ "<th width='10%'>Estatus</th>"
    		+ "<th width='10%'>EspacioFisico</th>"
    		+ "<th width='10%'>Lugar Asignado Edificio</th>"
    		+ "<th width='10%'>Email</th>"    		
    		+ "<th width='10%'>Fecha Creacion Registro</th>"
    		+ "<th width='10%'>Creado Por</th>"
    		+ "<th width='10%'>Registro Archivo</th>"
    		+ "</tr>"
    		+ "</thead><tbody>";
	
	public static String CUMPLIMIENTOEXTERNORRHH = "<thead>"
    		+ "<tr>"
    		+ "<th width='10%'>Usuario</th>"    		
    		+ "<th width='10%'>Nombre</th>"
    		+ "<th width='10%'>CR Direccion General</th>"
    		+ "<th width='10%'>Direccion General</th>"
    		+ "<th width='10%'>CR Direccion Corporativa</th>"
    		+ "<th width='10%'>Direccion Corporativa</th>"
    		+ "<th width='10%'>CR Area</th>"
    		+ "<th width='10%'>Area</th>"
    		+ "<th width='10%'>Fecha</th>"
    		+ "<th width='10%'>Quincena</th>"
    		+ "<th width='10%'>Mes</th>"
    		+ "<th width='10%'>TEA (Tolerancia Entrada Antes)</th>"
    		+ "<th width='10%'>Entrada Oficial</th>"
    		+ "<th width='10%'>TED (Tolerancia Entrada Despues)</th>"
    		+ "<th width='10%'>Entrada Real</th>"
    		+ "<th width='10%'>Calificacion Entrada</th>"
    		+ "<th width='10%'>TSA (Tolerancia Salida Antes)</th>"
    		+ "<th width='10%'>Salida Oficial</th>"
    		+ "<th width='10%'>TSD (Tolerancia Salida Despues)</th>"
    		+ "<th width='10%'>Salida Real</th>"
    		+ "<th width='10%'>Calificacion Salida</th>"
    		+ "<th width='10%'>Jornada</th>"
    		+ "<th width='10%'>Calificacion Jornada</th>"
    		+ "<th width='10%'>Calificacion Total</th>"
    		+ "<th width='10%'>% Cumplimiento</th>"
    		+ "<th width='10%'>Edificio Asignado</th>"
    		+ "</tr>"
    		+ "</thead><tbody>";	
	
	public static String INCUMPLIMIENTOEXTERNOCYGE = "<thead>"
    		+ "<tr>"
    		+ "<th width='10%'>No.Cyge</th>"
    		+ "<th width='40%'>Nombre</th>"
    		+ "<th width='10%'>Direccion General</th>"
    		+ "<th width='10%'>Direccion Corporativa</th>"
    		+ "<th width='10%'>Area</th>"
    		+ "<th width='10%'>Fecha</th>"
    		+ "<th width='10%'>Mes</th>"
    		+ "<th width='10%'>Quincena</th>"
    		+ "<th width='10%'>Entrada Oficial</th>"
    		+ "<th width='10%'>Entrada Real</th>"
    		+ "<th width='10%'>Salida Real</th>"
    		+ "<th width='10%'>Jornada</th>"
    		+ "<th width='10%'>Eestancia</th>"
    		+ "<th width='10%'>Edificio</th>"
    		+ "<th width='10%'>Autorizador</th>"
    		+ "<th width='10%'>Proveedor</th>"
    		+ "<th width='10%'>Proyecto</th>"
    		+ "<th width='10%'>Estatus</th>"
    		+ "<th width='10%'>Edificio Asignado</th>"
    		+ "</tr>"
    		+ "</thead><tbody>";
	
	
	static <T> List<List<T>> chopped(List<T> list, final int L) {
		List<List<T>> parts = new ArrayList<List<T>>();
	    final int N = list.size();
	    for (int i = 0; i < N; i += L) {
	        parts.add(new ArrayList<T>(
	            list.subList(i, Math.min(N, i + L)))
	        );
	    }
	    return parts;
	}
	
	public static void fechas(String periocidad, String semana) throws IOException{
		System.out.println("Calculo fechas");
	}
	
	
	
	public static String altaBajaModif(String tipo, String desde, String hasta) throws IOException{
		
		System.out.println("--Entra a altaBajaModif TEST BETY " + tipo);
		String html = (ENCABEZADO);
	    html += (ALTA_BAJA_MODIF);
	    //// Iterable<Entity> result = null;
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    try {	    	
			Date desdeDate = df.parse(desde);
			Date hastaDate = df.parse(hasta);
			System.out.println(desdeDate+"------"+hastaDate);
	    	
	    	//result = Solicitud.get("Solicitud", "Tipo", tipo, "FechaAlta", desdeDate, hastaDate);
			
			List<BeanSolicitud> resultTest = Solicitud.get("solicitud", "tipo", tipo, "FechaAlta", desdeDate, hastaDate);
			
			for(BeanSolicitud ent: resultTest){
		    	html += "<tr class='tblSeguimientoI1'>";
		    	html += "<td>"+ent.getNombre().toString()+"</td>";
		    	html += "<td>"+ent.getEmpleadoID().toString()+"</td>";	    	
		    	html += "<td>"+df.format(ent.getFechaAlta())+"</td>";
		    	html += "<td>"+ent.getEntradaAct().toString()+"</td>";
		    	html += "<td>"+ent.getSalidaAct().toString()+"</td>";
		    	html += "<td>"+ent.getEntradaSol().toString()+"</td>";
		    	html += "<td>"+ent.getSalidaSol().toString()+"</td>";
		    	html += "<td>"+ent.getSupervisorID().toString()+"</td>";
		    	try {		    		
		    		//e = Empleado.getEmpleado(entity.getProperty("SupervisorId").toString());
		    		
		    		BeanEmpleado e = new BeanEmpleado();
		    		e = Empleado.getEmpleado(ent.getSupervisorID().toString());
		    		
		    		html += "<td>"+e.getNombre().toString()+" "+
		    				e.getApePaterno().toString()+" "+
		    				e.getApeMaterno().toString()+"</td>";
		    		
		    		
				} catch (Exception exc) {
					html += "<td>"+"Usuario no registrado"+"</td>";
				}
		    	
		    	html += "<td>"+ent.getTipo().toString()+"</td>";
		    	html += "<td>"+(ent.getDecision().toString().equals("R")?
		    					"RECHAZADO":(ent.getDecision().toString().equals("A")?
		    					"AUTORIZADO":"PENDIENTE DE AUTORIZAR"))+"</td>";
		    	html += "<td>"+ent.getFechaRespuesta().toString()+"</td>";
		    	html += "<td>"+ent.getMotivo().toString()+"</td>";
		    	html += "</tr>";
		    }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    html += "</tbody> </table> </body>";
		return html;		
	}
	
	public StringBuffer CumplimientoInterno(String desde, String hasta, String usuario) throws IOException, ParseException{
		StringBuffer html = new StringBuffer(ENCABEZADO);
		html.append(CUMPLIMIENTO);
	    String paginacion = "";
	    int tamPag = 100;
	    BeanCumplimiento beanCumplimiento = new BeanCumplimiento();
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
		java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
		System.out.println(desde+"------"+hasta);
		System.out.println(desdeDate+"------"+hastaDate);
		DataNucleusQuery query = new DataNucleusQuery();
		List<BeanCumplimiento> result_1 = query.ReporteCumplimientoInterno(desdeDate, hastaDate, usuario);
		int count = 0;	
		try{
			for(BeanCumplimiento result: result_1){
				//System.out.println("Apaterno: "+result.getApePaterno()+"  Amaterno: "+result.getApeMaterno());	    	    	
				   	try {
				   		html.append(result.toString());				   		
			    	}catch (Exception e) {
			    		html.append("<tr><td>Exception</td> </tr>");
			    	}
			}
	    }catch(Exception e){
	    	System.err.println(e);
	    	html.append("<tr><td>Exception</td> </tr>");
	    	return html;
	    }
		html.append("</tbody> </table> </body>");
		return html;		
	}
	
	public StringBuffer CumplimientoInternoAdmin(String desde, String hasta) throws IOException, ParseException{
		StringBuffer html = new StringBuffer(ENCABEZADO);
		html.append(CUMPLIMIENTO);
	    String paginacion = "";
	    int tamPag = 100;
	    BeanCumplimiento beanCumplimiento = new BeanCumplimiento();
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
		java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
		System.out.println(desde+"------"+hasta);
		System.out.println(desdeDate+"------"+hastaDate);
		DataNucleusQuery query = new DataNucleusQuery();
		List<BeanCumplimiento> result_1 = query.ReporteCumplimientoInternoAdmin(desdeDate, hastaDate);
		int count = 0;	
		try{
			for(BeanCumplimiento result: result_1){
				//System.out.println("Apaterno: "+result.getApePaterno()+"  Amaterno: "+result.getApeMaterno());	    	    	
				   	try {
				   		html.append(result.toString());				   		
			    	}catch (Exception e) {
			    		html.append("<tr><td>Exception</td> </tr>");
			    	}
			}
	    }catch(Exception e){
	    	System.err.println(e);
	    	html.append("<tr><td>Exception</td> </tr>");
	    	return html;
	    }
		html.append("</tbody> </table> </body>");
		return html;		
	}
	
		
	public static String llenaHtml(String html, 
									String nombre,
									String tempIdUser,
									String nomCr, 
									String dga_ln3,
									String tempFecha,
									String entOfi,
									String entReal,
									int intEntrada,
									String salOfi,
									String salReal,
									int intSalida,
									int intJornada,
									int intTotal
									) throws ParseException{
		
		html += "<tr class='tblSeguimientoI1'>";
		html += "<td>"+nombre+"</td>";
		html += "<td>"+tempIdUser+"</td>";
		html += "<td>"+nomCr+"</td>";
		html += "<td>"+dga_ln3+"</td>";
		html += "<td>"+tempFecha+"</td>";
		html += "<td>"+quincena(tempFecha)+"</td>";
		html += "<td>"+mounth(tempFecha)+"</td>";
		html += "<td>"+resta(entOfi)+"</td>";
		html += "<td>"+entOfi+"</td>";
		html += "<td>"+suma(entOfi)+"</td>";
		html += "<td>"+entReal+"</td>";
		html += "<td>"+intEntrada+"</td>";
		html += "<td>"+resta(salOfi)+"</td>";
		html += "<td>"+salOfi+"</td>";
		html += "<td>"+suma(salOfi)+"</td>";
		html += "<td>"+salReal+"</td>";
		html += "<td>"+intSalida+"</td>";    	
    	html += "<td>"+intJornada+"</td>";
    	html += "<td>"+intTotal+"</td>";
    	html += "<td>"+(intTotal==3?"100%":(intTotal==1?"33%":"0%"))+"</td>";
    	html += "</tr>";
		return html;
	}
	
	//--------------------------------------------------------------------------------------------------------//
		public StringBuffer Cyge(String desde, String hasta) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(CYGE);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery();
			List<BeanCyge> result_1 = query.HistoricoCyge(desdeDate, hastaDate);
			try{
				for(BeanCyge result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}
		
//---------------------------------------------------------------------------------------------------------------		
		public StringBuffer CumplimientoExternoCyge(String desde, String hasta, String usuario) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(CUMPLIMIENTOEXTERNOCYGE);
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery ();
			List<BeanCumplimientoExternoCyge> result_1 = query.ReporteCumplimientoCyge(desdeDate, hastaDate, usuario);
			try{
				for(BeanCumplimientoExternoCyge result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}

//------------------------------------------------------------------------------------------------------------
		
		public StringBuffer CumplimientoExternoAdmin(String desde, String hasta) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(CUMPLIMIENTOEXTERNOCYGE);
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery ();
			List<BeanCumplimientoExternoCyge> result_1 = query.CumplimientoCygeAdmin(desdeDate, hastaDate);
			try{
				for(BeanCumplimientoExternoCyge result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}
		

//------------------------------------------------------------------------------------------------------------
	
		public StringBuffer CumplimientoCygeRRHH(String desde, String hasta, String usuario) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(CUMPLIMIENTOEXTERNORRHH);
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery();
			List<BeanCumplimientoExternoRRHH> result_1 = query.ReporteCumplimientoCygeRRHH(desdeDate, hastaDate, usuario); //TODO: sustituir por Query correcto.
			try{
				for(BeanCumplimientoExternoRRHH result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}

//---------------------------------CUMPLIMIENTO CYGE RRHH ADMIN--------------------------------

		public StringBuffer CumplimientoCygeRHAdmin(String desde, String hasta) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(CUMPLIMIENTOEXTERNORRHH);
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery();
			List<BeanCumplimientoExternoRRHH> result_1 = query.ReporteAdminCygeRRHH(desdeDate, hastaDate); //TODO: sustituir por Query correcto.
			try{
				for(BeanCumplimientoExternoRRHH result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}
//--------------------------------INCUMPLIMIENTO --------------------------------//
		public StringBuffer IncumplimientoCyge(String desde, String hasta, String usuario) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(INCUMPLIMIENTOEXTERNOCYGE);
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery();
			List<BeanIncumplimiento> result_1 = query.Incumplimiento(desdeDate, hastaDate, usuario); //TODO: sustituir por Query correcto.
			try{
				for(BeanIncumplimiento result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}
		
		//---------------------------- INCUMPLIMIENTO ADMIN --------------------------------//
		
		public StringBuffer IncumplimientoExternoAdmin(String desde, String hasta) throws IOException, ParseException{
			StringBuffer html = new StringBuffer(ENCABEZADO);
			html.append(INCUMPLIMIENTOEXTERNOCYGE);
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date desdeDate = new java.sql.Date(df.parse(desde).getTime());
			java.sql.Date hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			System.out.println(desde+"------"+hasta);
			System.out.println(desdeDate+"------"+hastaDate);
			DataNucleusQuery query = new DataNucleusQuery ();
			List<BeanIncumplimiento> result_1 = query.IncumplimientoAdmin(desdeDate, hastaDate);
			try{
				for(BeanIncumplimiento result: result_1){	    	    	
					   	try {
					   		html.append(result.toString());				   		
				    	}catch (Exception e) {
				    		html.append("<tr><td>Exception</td> </tr>");
				    	}
				}
		    }catch(Exception e){
		    	System.err.println(e);
		    	html.append("<tr><td>Exception</td> </tr>");
		    	return html;
		    }
			html.append("</tbody> </table> </body>");
			return html;		
		}
		
		
	private int retorna = 0;
	private Date date1 = null;
	private Date date2 = null;
	private String FormatoHoraAMPM = "HH:mm";
	private String FormatoHora = "HH:mm";
	private SimpleDateFormat sdfReal = new SimpleDateFormat(FormatoHoraAMPM, Locale.ENGLISH);
	private SimpleDateFormat sdf = new SimpleDateFormat(FormatoHora, Locale.ENGLISH);
		
	public static String resta(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormat sdf = new SimpleDateFormat("HH:mm");
			date = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();		
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, -15);
			return sdf.format(calendar.getTime());
		}
		return "";	
	}
	
	public static String suma(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormat sdf = new SimpleDateFormat("HH:mm");
			date = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();		
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, 15); 		
			return sdf.format(calendar.getTime());
		}
		return "";
	}
	
	public static String quincena(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			date = sdf.parse(time);
			int day = date.getDay();
			if(day<=15){
				return "Q1";
			} 		
			return "Q2";
		}
		return "";
		
	}
	
	public static String mounth(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormatSymbols dfs = new DateFormatSymbols();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			date = sdf.parse(time);
			int intMonth = date.getMonth();
			String[] months = dfs.getMonths();
			return months[intMonth];
		}
		return "";		
	}
	
	public static String vacio(){
		String html = ENCABEZADO;
	    html += ALTA_BAJA_MODIF;
		return html;
	}
	
	public static String errorExcel(){
		String html = vacio();
		html += "<tr class='tblSeguimientoI1'>";
    	html += "<td colspan='7'>No se encontraron solicitudes con los datos proporcionados</td>";
    	html += "</tr>";    	
		return "";
	}
	
}
