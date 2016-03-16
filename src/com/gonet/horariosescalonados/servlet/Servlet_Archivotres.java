package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.classes.Generar_Archivo;
import com.gonet.horariosescalonados.dao.QueryTables;


@SuppressWarnings("serial")
public class Servlet_Archivotres extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String opcion = req.getParameter("opcionReporte")!=null?req.getParameter("opcionReporte").toString():"";
		String dia = req.getParameter("hiddenHasta")!=null?req.getParameter("hiddenHasta").toString():"";
		String desde = req.getParameter("hiddenDesde")!=null?req.getParameter("hiddenDesde").toString():"";
		String hasta = req.getParameter("hiddenHasta")!=null?req.getParameter("hiddenHasta").toString():"";
		String semana = req.getParameter("hiddenSemana")!=null?req.getParameter("hiddenSemana").toString():"";
		String mes = req.getParameter("hiddenMes")!=null?req.getParameter("hiddenMes").toString():"";
		String usuario = req.getParameter("hiddenUsuario")!=null?req.getParameter("hiddenUsuario").toString():"";
		String tipousuario = req.getParameter("hiddenTipoUsuario")!=null?req.getParameter("hiddenTipoUsuario").toString():"";
			
//		String desde = "";
//		String hasta = "";
//		String mes = "";
//		String opcion = "";
//		String tipousuario = "";
//		String usuario = "";
		
			Generar_Archivo Archivo = new Generar_Archivo();
//			QueryTables daoSelect = new QueryTables();
			
//			String cuenta = req.getUserPrincipal().getName();
			
//			boolean acceder = false;
			
//			acceder = daoSelect.Acceder(cuenta);
			
//			if (acceder)
//			{
				Archivo.Archivodos(req, resp, desde, hasta, mes,opcion,tipousuario,usuario);
//			}

			
	
			}
	        
	   
	}