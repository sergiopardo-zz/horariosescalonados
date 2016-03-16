package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.classes.Generar_Archivo;
import com.gonet.horariosescalonados.classes.TiempoReporte;
import com.gonet.horariosescalonados.connector.Connector;
import com.gonet.horariosescalonados.dao.QueryTables;

@SuppressWarnings("serial")
public class Servlet_Archivodos extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		RequestDispatcher dispatcher;
		
		String opcion = req.getParameter("opcionReporte")!=null?req.getParameter("opcionReporte").toString():"";
		String dia = req.getParameter("hiddenHasta")!=null?req.getParameter("hiddenHasta").toString():"";
		String desde = req.getParameter("hiddenDesde")!=null?req.getParameter("hiddenDesde").toString():"";
		String hasta = req.getParameter("hiddenHasta")!=null?req.getParameter("hiddenHasta").toString():"";
		String semana = req.getParameter("hiddenSemana")!=null?req.getParameter("hiddenSemana").toString():"";
		String mes = req.getParameter("hiddenMes")!=null?req.getParameter("hiddenMes").toString():"";
		String usuario = req.getParameter("hiddenUsuario")!=null?req.getParameter("hiddenUsuario").toString():"";
		String tipousuario = req.getParameter("hiddenTipoUsuario")!=null?req.getParameter("hiddenTipoUsuario").toString():"";
		
		req.setAttribute("dia", dia);
		req.setAttribute("semana", semana);
		req.setAttribute("mes", mes);
        req.setAttribute("lstOpcion", opcion);
			Generar_Archivo Archivo = new Generar_Archivo();
						
			Archivo.Archivouno(req, resp, desde, hasta, mes, opcion, tipousuario, usuario);
			
	//		dispatcher = getServletContext().getRequestDispatcher("/Servlet_Archivodos");
//			Archivo.Archivodos(req, resp, desde, hasta, mes);
	
			}
	        
	   
	}


