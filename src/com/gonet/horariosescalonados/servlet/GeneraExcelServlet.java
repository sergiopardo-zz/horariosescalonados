package com.gonet.horariosescalonados.servlet;


import java.io.IOException;
import java.text.ParseException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.io.IOUtils;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.classes.Reportes;
import com.gonet.horariosescalonados.classes.TiempoReporte;
import com.gonet.horariosescalonados.util.ObtenFechas;



@SuppressWarnings("serial")
public class GeneraExcelServlet extends HttpServlet {
	private String encoding = "ISO-8859-1";
	public GeneraExcelServlet() {
		super();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String initParameterEncoding = config.getInitParameter("encoding");
		if (initParameterEncoding != null) {
			setEncoding(initParameterEncoding); 
		}
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}	
	
	@SuppressWarnings("static-access")
	public void  doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String html = "";
		String opcion = req.getParameter("opcionReporte")!=null?req.getParameter("opcionReporte").toString():"";
		String desde = req.getParameter("hiddenDesde")!=null?req.getParameter("hiddenDesde").toString():"";
		String hasta = req.getParameter("hiddenHasta")!=null?req.getParameter("hiddenHasta").toString():"";
		String semana = req.getParameter("hiddenSemana")!=null?req.getParameter("hiddenSemana").toString():"";
		String mes = req.getParameter("hiddenMes")!=null?req.getParameter("hiddenMes").toString():"";
		String usuario = req.getParameter("hiddenUsuario")!=null?req.getParameter("hiddenUsuario").toString():"";
		String tipousuario = req.getParameter("hiddenTipoUsuario")!=null?req.getParameter("hiddenTipoUsuario").toString():"";
		String fileName="";	
		if(semana!= ""){
			TiempoReporte tiempo = new TiempoReporte();
			desde = tiempo.Tiempo(semana, desde);
			hasta = tiempo.Tiempo2(desde, hasta);
		}
		if(mes != ""){
			TiempoReporte tiempo = new TiempoReporte();
			desde = tiempo.Tiempo4(mes, desde);
			hasta = tiempo.Tiempo3(mes, hasta);
		}
		if(desde ==""){
		    TiempoReporte tiempo = new TiempoReporte();
		    desde = tiempo.Tiempo5(hasta, desde);
		}
		Reportes reportes = new Reportes();
		if(opcion.equals("alta")){
			html += Reportes.altaBajaModif("ALTA", desde, hasta);			
			fileName = "Alta_"+ObtenFechas.fechaHoy();
	    }else if(opcion.equals("modificacion")){
	    	html += Reportes.altaBajaModif("MODIFICACION", desde, hasta);			
			fileName = "Modificacion_"+ObtenFechas.fechaHoy();
	//---------------------------------------------------------------//
	    }else if (opcion.equals("cyge")){
	    	try {
				html += reportes.Cyge(desde, hasta);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			fileName = "Registros CYGE_"+ObtenFechas.fechaHoy();
		
	//--------------------------------------------------------------//
	    }else if(opcion.equals("baja")){
	    	html += Reportes.altaBajaModif("BAJA", desde, hasta);			
			fileName = "Baja_"+ObtenFechas.fechaHoy();
			
			
	    }else if(opcion.equals("cumplimientoExternoCyge")){
	    	if(tipousuario.equals("A") ||tipousuario.equals("SS") || tipousuario.equals("AA")){
	    		try{
	    	    	html += reportes.CumplimientoExternoAdmin(desde, hasta);
	    	    	}catch(Exception e){
	    	    		e.printStackTrace();
	    	    	}
	    	}else{
	    		try{
	    		html += reportes.CumplimientoExternoCyge(desde, hasta, usuario);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	    	fileName = "CumplimientoExternoCyge_" + ObtenFechas.fechaHoy();
//-------------------------------------------------------------------------------------
	    }else if(opcion.equals("incumplimiento")){
	    	if(tipousuario.equals("A") ||tipousuario.equals("SS") || tipousuario.equals("AA")){
	    		try{
	    	    	html += reportes.IncumplimientoExternoAdmin(desde, hasta);
	    	    	}catch(Exception e){
	    	    		e.printStackTrace();
	    	    	}
	    	}else{
	    		try{
	    		html += reportes.IncumplimientoCyge(desde, hasta, usuario);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	    	fileName = "Incicencias Externos_" + ObtenFechas.fechaHoy();
	    	
//-------------------------------------------------------------------------------------
	    	
	    	
	    }else if(opcion.equals("cumplimientoExternoRRHH")){
	    	if(tipousuario.equals("A") ||tipousuario.equals("SS") || tipousuario.equals("AA")){
	    		try{
	    	    	html += reportes.CumplimientoCygeRHAdmin(desde, hasta);
	    	    	}catch(Exception e){
	    	    		e.printStackTrace();
	    	    	}
	    	}else{
	    		try{
	    			html += reportes.CumplimientoCygeRRHH(desde, hasta, usuario);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	    	fileName = "CumplimientoExternoRRHH_" + ObtenFechas.fechaHoy();
	    	
	    	
	    }else if(opcion.equals("noasignacion")){
	    	html += Reportes.vacio();			
			fileName = "NoAsignacion_"+ObtenFechas.fechaHoy();
	    }else if(opcion.equals("cumplimiento")){
	    		if(tipousuario.equals("A") || tipousuario.equals("SS") ||tipousuario.equals("AA")){
	    			try {
	    	    		html += reportes.CumplimientoInternoAdmin(desde, hasta);			
	    				fileName = "Cumplimiento_"+ObtenFechas.fechaHoy();
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    		}else{
	    			try {
	    				html += reportes.CumplimientoInterno(desde, hasta, usuario);			
	    				fileName = "Cumplimiento_"+ObtenFechas.fechaHoy();
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    		}
	    }
		
	    }else{
	    	fileName="SIN_DATOS";
	    }	
		
		if (!fileName.endsWith(".xls")) {
			fileName = fileName + ".xls";
		}
		resp.setContentType("application/vnd.ms-excel");// magic is here
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		resp.setContentLength(html.length());
		resp.getWriter().println(html);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
		doPost(req,resp);
	}	
	
	public void errorExcel(HttpServletResponse resp, String fileName, String html){
		try {
			
			fileName = "Alta_"+ObtenFechas.fechaHoy();
			if (!fileName.endsWith(".xlsx")) {
				fileName = fileName + ".xlsx";
			}
			resp.setContentType("application/vnd.ms-excel");// magic is here
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			resp.setContentLength(html.length());
			resp.getWriter().println(html);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
