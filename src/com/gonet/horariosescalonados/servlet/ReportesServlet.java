package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.classes.Empleado;

//BETY import org.apache.commons.io.IOUtils;


import com.gonet.horariosescalonados.classes.MantenimientoHorarios;
import com.gonet.horariosescalonados.classes.MantenimientoMensajes;
import com.gonet.horariosescalonados.classes.Reportes;
//BETY import com.gonet.horariosescalonados.datastore.Mensaje;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.google.appengine.api.datastore.Entity;


@SuppressWarnings("serial")
public class ReportesServlet extends HttpServlet{
	
	private String encoding = "ISO-8859-1";
	public ReportesServlet() {
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
		RequestDispatcher dispatcher;
		System.out.println("-----------------------> Ingresando a Ventana \"Reportes\"  <-----------------------");
		
		try{			
			req.setAttribute("message9", MantenimientoMensajes.getMessage("MSG09"));
					
		}catch(Exception e){
			e.printStackTrace();
		}		
		//BETY String opcion = req.getParameter("opcionReporte")!=null?req.getParameter("opcionReporte").toString():"";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String fechaActual = dateFormat.format(date).toString();
		req.setAttribute("pFecha", fechaActual);
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		if(empleado.getTipoEmpleado().equals("C")){
			dispatcher = getServletContext().getRequestDispatcher("/menuRRHH.jsp");
			dispatcher.forward(req, resp);
		}
		if(empleado.getTipoEmpleado().equals("GE") || empleado.getTipoEmpleado().equals("SS")||empleado.getTipoEmpleado().equals("RH")){	
		dispatcher = getServletContext().getRequestDispatcher("/reportes.jsp");
		dispatcher.forward(req, resp);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
		doPost(req,resp);
	}
}
