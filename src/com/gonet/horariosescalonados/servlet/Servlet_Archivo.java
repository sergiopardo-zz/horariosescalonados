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
		
		
		req.setAttribute("mes", mes);
        req.setAttribute("lstOpcion", opcion);
		
		
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
			
			maximoreg = daoSelect.maximoCumplimiento(desdeDate, hastaDate,opcion,usuario );
			
			String selectSql = "";
			
			if (maximoreg<120000)
				
			{
							
		
			resp.setContentType("application/vnd.ms-excel");
	        PrintWriter out = resp.getWriter();

	        Connection conn = null;
	        ResultSet rs = null;
	        Statement st = null;
	        
	        int contador = 0;
	        

	        if(opcion.equals("cumplimientoExternoRRHH")){
	        try { 	
	        	        		
	        		
	        	resp.setContentType("text/csv");

	            out.write("Usuario");
	            out.write(',');
	            out.write("Nombre");
	            out.write(',');
	            out.write("CR Direccion General");
	            out.write(',');
	            out.write("Direccion General");
	            out.write(',');
	            out.write("CR Direccion Corporativa");
	            out.write(',');
	            out.write("Direccion Corporativa");
	            out.write(',');
	            out.write("CR Area");
	            out.write(',');
	            out.write("Area");
	            out.write(',');
	            out.write("Fecha");
	            out.write(',');
	            out.write("Quincena");
	            out.write(',');
	            out.write("Mes");
	            out.write(',');
	            out.write("TEA (Tolerancia Entrada Antes)");
	            out.write(',');
	            out.write("Entrada Oficial");
	            out.write(',');
	            out.write("TED (Tolerancia Entrada Despues)");
	            out.write(',');
	            out.write("Entrada Real");
	            out.write(',');
	            out.write("Calificacion Entrada");
	            out.write(',');
	            out.write("TSA (Tolerancia Salida Antes)");
	            out.write(',');
	            out.write("Salida Oficial");
	            out.write(',');
	            out.write("TSD (Tolerancia Salida Despues)");
	            out.write(',');
	            out.write("Salida Real");
	            out.write(',');
	            out.write("Calificacion Salida");
	            out.write(',');
	            out.write("Jornada");
	            out.write(',');
	            out.write("Calificacion Jornada");
	            out.write(',');
	            out.write("Calificacion Total");
	            out.write(',');
	            out.write("% Cumplimiento");
	            out.write(',');
	            out.write("Edificio Asignado");
	            out.write('\n');
	            
	            if(tipousuario.equals("CE")||tipousuario.equals("CA")){
	             selectSql = "SELECT HIGH_PRIORITY Usuario, Nombre, CRDireccionGeneral, direccionGeneral, CRDireccionCorporativa, direccionCorporativa, CRArea, Area, Fecha, Quincena, Mes, TEA, entradaOficial, TED, entradaReal, CalificacionEntrada, TSA, salidaOficial ,TSD, salidaReal, CalificacionSalida, Jornada, CalificacionJornada, CalificacionTotal, PorcentajeCumplimiento, EdificioAsignado FROM horariosescalonadosv2.CumplimientoExternoRRHH where horariosescalonadosv2.CumplimientoExternoRRHH.Usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"')and Fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' ";
	            }else{
	             selectSql = "SELECT HIGH_PRIORITY Usuario, Nombre, CRDireccionGeneral, direccionGeneral, CRDireccionCorporativa, direccionCorporativa, CRArea, Area, Fecha, Quincena, Mes, TEA, entradaOficial, TED, entradaReal, CalificacionEntrada, TSA, salidaOficial ,TSD, salidaReal, CalificacionSalida, Jornada, CalificacionJornada, CalificacionTotal, PorcentajeCumplimiento, EdificioAsignado FROM horariosescalonadosv2.CumplimientoExternoRRHH where fecha between '"+desdeDate+"' and '"+hastaDate+"'";
	            }
	        	conn = Connector.getConexion();
	            st = conn.createStatement();
	            rs = st.executeQuery(selectSql);
	            while (rs.next()) {
	                out.write(rs.getString(1)); 
	                out.write(',');
	                out.write(rs.getString(2).replace(',',' '));
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
	                out.write(',');
	                out.write(rs.getString(23)); 
	                out.write(',');
	                out.write(rs.getString(24)); 
	                out.write(',');
	                out.write(rs.getString(25)); 
	                out.write(',');
	                out.write(rs.getString(26)); 
	                out.write('\n');
	              
	                 	               
	            }

	            resp.setContentType("application/download");
	            resp.setHeader("Content-disposition", "attachment; filename ="+opcion+".csv");
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
			
		 
	        
	        if(opcion.equals("cumplimiento")){
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

		            
		         if(tipousuario.equals("CI")||tipousuario.equals("CA")){		            
		             selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where horariosescalonadosv2.cumplimiento.empleadoID in (select horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioReporteInterno from horariosescalonadosv2.PerfilConsultaInternos where horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioConsultaInterno = '"+usuario+"' )and horariosescalonadosv2.cumplimiento.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where fecha between '"+desdeDate+ "' and '"+hastaDate+ "' ";
		         }
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
		            resp.setHeader("Content-disposition", "attachment; filename = "+opcion+".csv");
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
	        
	        
	        if(opcion.equals("cumplimientoExternoCyge")){
		        try {
		        			
		        	resp.setContentType("text/csv");

		            out.write("USUARIO");
		            out.write(',');
		            out.write("EMPLEADO ID");
		            out.write(',');
		            out.write("NOMBRE");
		            out.write(',');
		            out.write("DIRECCION GENERAL");
		            out.write(',');
		            out.write("DIRECCION CORPORATIVA");
		            out.write(',');
		            out.write("AREA");
		            out.write(',');
		            out.write("FECHA");
		            out.write(',');
		            out.write("MES");
		            out.write(',');
		            out.write("QUINCENA");
		            out.write(',');
		            out.write("ENTRADA OFICIAL");
		            out.write(',');
		            out.write("ENTRADA REAL");
		            out.write(',');
		            out.write("SALIDA REAL");
		            out.write(',');
		            out.write("JORNADA");
		            out.write(',');
		            out.write("ESTANCIA");
		            out.write(',');
		            out.write("EDIFICIO");
		            out.write(',');
		            out.write("AUTORIZADOR");
		            out.write(',');
		            out.write("PROVEEDOR");
		            out.write(',');
		            out.write("PROYECTO");
		            out.write(',');
		            out.write("ESTATUS");
		            out.write(',');
		            out.write("EDIFICIO ASIGNADO");
		            out.write('\n');

		            
		         if(tipousuario.equals("CC")){		            
		             selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno where horariosescalonadosv2.cumplimientoExterno.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.cumplimientoExterno.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno where fecha between '"+desdeDate+ "' and '"+hastaDate+ "' ";
		         }
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
		                out.write('\n');
		                  	               
		            }
		         
		         
		            resp.setContentType("application/download");
		            resp.setHeader("Content-disposition", "attachment; filename = "+opcion+".csv");
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
			
	        
	        if(opcion.equals("incumplimiento")){
		        try {
		        			
		        	resp.setContentType("text/csv");

		        	    out.write("USUARIO");
			            out.write(',');
			            out.write("EMPLEADO ID");
			            out.write(',');
			            out.write("NOMBRE");
			            out.write(',');
			            out.write("DIRECCION GENERAL");
			            out.write(',');
			            out.write("DIRECCION CORPORATIVA");
			            out.write(',');
			            out.write("AREA");
			            out.write(',');
			            out.write("FECHA");
			            out.write(',');
			            out.write("MES");
			            out.write(',');
			            out.write("QUINCENA");
			            out.write(',');
			            out.write("ENTRADA OFICIAL");
			            out.write(',');
			            out.write("ENTRADA REAL");
			            out.write(',');
			            out.write("SALIDA REAL");
			            out.write(',');
			            out.write("JORNADA");
			            out.write(',');
			            out.write("ESTANCIA");
			            out.write(',');
			            out.write("EDIFICIO");
			            out.write(',');
			            out.write("AUTORIZADOR");
			            out.write(',');
			            out.write("PROVEEDOR");
			            out.write(',');
			            out.write("PROYECTO");
			            out.write(',');
			            out.write("ESTATUS");
			            out.write(',');
			            out.write("EDIFICIO ASIGNADO");
			            out.write('\n');

		            
		         if(tipousuario.equals("CC")){		            
		             selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.Incumplimiento where horariosescalonadosv2.Incumplimiento.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.Incumplimiento.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.Incumplimiento where fecha between '"+desdeDate+ "' and '"+hastaDate+ "' ";
		         }
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
			                out.write('\n');
		                  	               
		            }
		         
		         
		            resp.setContentType("application/download");
		            resp.setHeader("Content-disposition", "attachment; filename = "+opcion+".csv");
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
	        
	        if(opcion.equals("cyge")){
		        try {
		        			
		        	resp.setContentType("text/csv");

		            out.write("NoCyge");
		            out.write(',');
		            out.write("Usuario");
		            out.write(',');
		            out.write("Nombre");
		            out.write(',');
		            out.write("Apellido Paterno");
		            out.write(',');
		            out.write("Apellido Materno");
		            out.write(',');
		            out.write("Direccion General");
		            out.write(',');
		            out.write("Direccion Corporativa");
		            out.write(',');
		            out.write("Area");
		            out.write(',');
		            out.write("Entrada Oficial");
		            out.write(',');
		            out.write("Id Autorizador");
		            out.write(',');
		            out.write("Autorizador");
		            out.write(',');
		            out.write("Proveedor");
		            out.write(',');
		            out.write("Proyecto");
		            out.write(',');
		            out.write("Estatus");
		            out.write(',');
		            out.write("EspacioFisico");
		            out.write(',');
		            out.write("Lugar Asignado Edificio");
		            out.write(',');
		            out.write("Email");
		            out.write(',');
		            out.write("Fecha Creacion Registro");
		            out.write(',');
		            out.write("Creado Por");
		            out.write(',');
		            out.write("Registro Archivo");
		            out.write('\n');

		            
		         if(tipousuario.equals("CC")){		            
		             selectSql = "SELECT HIGH_PRIORITY NoCyge, Usuario, Nombre, ApePaterno, ApeMaterno, DirGeneral, DirCorporativa, Area, EntOficial, AutorizadorID, Autorizador, Proveedor, Proyecto, Estatus, EspacioFisico, LugarAsignadoEdificio, Email ,FechaCreacionRegistro, CreadoPor, RegistroActivo where horariosescalonadosv2.Cyge.empleadoID in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.Cyge.FechaCreacionRegistro BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY NoCyge, Usuario, Nombre, ApePaterno, ApeMaterno, DirGeneral, DirCorporativa, Area, EntOficial, AutorizadorID, Autorizador, Proveedor, Proyecto, Estatus, EspacioFisico, LugarAsignadoEdificio, Email ,FechaCreacionRegistro, CreadoPor, RegistroActivo FROM horariosescalonadosv2.Cyge where FechaCreacionRegistro between '"+desdeDate+ "' and '"+hastaDate+ "' ";
		         }
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
		                out.write('\n');
		                  	               
		            }
		         
		         
		            resp.setContentType("application/download");
		            resp.setHeader("Content-disposition", "attachment; filename ="+opcion+".csv");
		            
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
			
	      }else{
				
				dispatcher = getServletContext().getRequestDispatcher("/repMesFull.jsp");
				
				dispatcher.forward(req, resp);
				
//				req.setAttribute("anuncios", daoAnuncio.Obtener_Anuncio());
//				req.getRequestDispatcher("/repMesFull.jsp").forward(req, resp);
				
			}
	}
}