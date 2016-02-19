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

import com.gonet.horariosescalonados.classes.TiempoReporte;
import com.gonet.horariosescalonados.connector.Connector;
import com.gonet.horariosescalonados.dao.QueryTables;

@SuppressWarnings("serial")
public class Servlet_Archivo extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String opcion = req.getParameter("opcionReporte")!=null?req.getParameter("opcionReporte").toString():"";
		String desde = req.getParameter("hiddenDesde")!=null?req.getParameter("hiddenDesde").toString():"";
		String hasta = req.getParameter("hiddenHasta")!=null?req.getParameter("hiddenHasta").toString():"";
		String semana = req.getParameter("hiddenSemana")!=null?req.getParameter("hiddenSemana").toString():"";
		String mes = req.getParameter("hiddenMes")!=null?req.getParameter("hiddenMes").toString():"";
		String usuario = req.getParameter("hiddenUsuario")!=null?req.getParameter("hiddenUsuario").toString():"";
		String tipousuario = req.getParameter("hiddenTipoUsuario")!=null?req.getParameter("hiddenTipoUsuario").toString():"";
		
		RequestDispatcher dispatcher;
		
		TiempoReporte tiempo = new TiempoReporte();
		desde = tiempo.Tiempo4(mes, desde);
		hasta = tiempo.Tiempo3(mes, hasta);
		java.sql.Date desdeDate = null;
		java.sql.Date hastaDate = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 
			try {
				desdeDate = new java.sql.Date(df.parse(desde).getTime());
				hastaDate = new java.sql.Date(df.parse(hasta).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
			int maximoreg = 0;
			
			QueryTables daoSelect = new QueryTables();
			
			maximoreg = daoSelect.maximoCumplimiento(desdeDate, hastaDate );
			
			
			if (maximoreg<120000)
				
			{
							
		
			resp.setContentType("application/vnd.ms-excel");
	        PrintWriter out = resp.getWriter();

	        Connection conn = null;
	        ResultSet rs = null;
	        Statement st = null;
	        
	        int contador = 0;
	        

	        try { 	
	        	        		
	        		
	        	resp.setContentType("text/csv");

	            out.write("empleado ID");
	            out.write(',');
	            out.write("apellido paterno");
	            out.write(',');
	            out.write("apellido materno");
	            out.write(',');
	            out.write("nombre");
	            out.write(',');
	            out.write("nombre CR");
	            out.write(',');
	            out.write("DGA");
	            out.write(',');
	            out.write("fecha");
	            out.write(',');
	            out.write("quincena");
	            out.write(',');
	            out.write("mes");
	            out.write(',');
	            out.write("tae");
	            out.write(',');
	            out.write("Entrada");
	            out.write(',');
	            out.write("TDE");
	            out.write(',');
	            out.write("Entrada Real");
	            out.write(',');
	            out.write("calif Entrada");
	            out.write(',');
	            out.write("TAS");
	            out.write(',');
	            out.write("Salida");
	            out.write(',');
	            out.write("TDS");
	            out.write(',');
	            out.write("Salida Real");
	            out.write(',');
	            out.write("calif Salida");
	            out.write(',');
	            out.write("Jornada");
	            out.write(',');
	            out.write("total");
	            out.write(',');
	            out.write("porcentaje");
	            out.write('\n');

	            String selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where fecha between '"+desdeDate+"' and '"+hastaDate+"'";
	        	conn = Connector.getConexion();
	            st = conn.createStatement();
	            rs = st.executeQuery(selectSql);
	            while (rs.next()) {
	                out.write(rs.getString(1)); 
	                out.write(',');
	                out.write(rs.getString(2));
	                out.write(',');
	                out.write(rs.getString(3)); 
	                out.write(',');
	                out.write(rs.getString(4)); 
	                out.write(',');
	                out.write(rs.getString(5)); 
	                out.write(',');
	                out.write(rs.getString(6)); 
	                out.write(',');
	                out.write(rs.getString(7)); 
	                out.write(',');
	                out.write(rs.getString(8)); 
	                out.write(',');
	                out.write(rs.getString(9)); 
	                out.write(',');
	                out.write(rs.getString(10)); 
	                out.write(',');
	                out.write(rs.getString(11)); 
	                out.write(',');
	                out.write(rs.getString(12)); 
	                out.write(',');
	                out.write(rs.getString(13)); 
	                out.write(',');
	                out.write(rs.getString(14)); 
	                out.write(',');
	                out.write(rs.getString(15)); 
	                out.write(',');
	                out.write(rs.getString(16)); 
	                out.write(',');
	                out.write(rs.getString(17)); 
	                out.write(',');
	                out.write(rs.getString(18)); 
	                out.write(',');
	                out.write(rs.getString(19)); 
	                out.write(',');
	                out.write(rs.getString(20)); 
	                out.write(',');
	                out.write(rs.getString(21)); 
	                out.write(',');
	                out.write(rs.getString(22)); 
	                out.write('\n');
	              
	                
	                
	                	               
	            }
	         

	            resp.setContentType("application/download");
	            resp.setHeader("Content-disposition", "attachment; filename =prueba.csv");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            out.close();
	            try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }	       
			
		} 
			else
			{
				
				dispatcher = getServletContext().getRequestDispatcher("/repMesFull.jsp");
				
				dispatcher.forward(req, resp);
				
//				req.setAttribute("anuncios", daoAnuncio.Obtener_Anuncio());
//				req.getRequestDispatcher("/repMesFull.jsp").forward(req, resp);
				
			}
			
	}

}