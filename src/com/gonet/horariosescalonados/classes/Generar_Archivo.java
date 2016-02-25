package com.gonet.horariosescalonados.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.connector.Connector;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.dao.QueryTables;


public class Generar_Archivo {
	
	public void Archivouno (HttpServletRequest req, HttpServletResponse resp, String desde, String hasta, String mes,String opcion, String tipousuario, String usuario, String semana, String dia ) throws IOException
	{
		
		String cuenta = req.getUserPrincipal().getName();
		InsertarRegistro daoInsert = new InsertarRegistro();
		
		req.setAttribute("dia", dia);
		req.setAttribute("mes", mes);
		req.setAttribute("semana", semana);
        req.setAttribute("lstOpcion", opcion);
		
		
		RequestDispatcher dispatcher;
		if(dia !=""){
	    TiempoReporte tiempo = new TiempoReporte();
	    hasta = tiempo.Tiempo5(hasta, desde);
		}if(mes!=""){
			TiempoReporte tiempo = new TiempoReporte();
			desde = tiempo.Tiempo4(mes, desde);
			hasta = tiempo.Tiempo3(mes, hasta);
		}if(semana!=""){
		    TiempoReporte tiempo = new TiempoReporte();
			desde = tiempo.Tiempo(semana, desde);
			hasta = tiempo.Tiempo2(desde, hasta);
		}
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
			String selectSql = "";
				resp.setContentType("application/vnd.ms-excel");
				PrintWriter out = null;
				Connection conn = null;		        	
		        	       
		        	out = resp.getWriter();

			       
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
				             selectSql = "SELECT HIGH_PRIORITY Usuario, Nombre, CRDireccionGeneral, direccionGeneral, CRDireccionCorporativa, direccionCorporativa, CRArea, Area, Fecha, Quincena, Mes, TEA, entradaOficial, TED, entradaReal, CalificacionEntrada, TSA, salidaOficial ,TSD, salidaReal, CalificacionSalida, Jornada, CalificacionJornada, CalificacionTotal, PorcentajeCumplimiento, EdificioAsignado FROM horariosescalonadosv2.CumplimientoExternoRRHH where horariosescalonadosv2.CumplimientoExternoRRHH.Usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"')and Fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' limit 100000";
				            }else{
				             selectSql = "SELECT HIGH_PRIORITY Usuario, Nombre, CRDireccionGeneral, direccionGeneral, CRDireccionCorporativa, direccionCorporativa, CRArea, Area, Fecha, Quincena, Mes, TEA, entradaOficial, TED, entradaReal, CalificacionEntrada, TSA, salidaOficial ,TSD, salidaReal, CalificacionSalida, Jornada, CalificacionJornada, CalificacionTotal, PorcentajeCumplimiento, EdificioAsignado FROM horariosescalonadosv2.CumplimientoExternoRRHH where fecha between '"+desdeDate+"' and '"+hastaDate+"' limit 100000";
				            }
				        	conn = Connector.getConexion();
				            st = conn.createStatement();
				            rs = st.executeQuery(selectSql);
				            while (rs.next()) {
				                out.write(rs.getString(1).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(2).replace(',',' '));
				                out.write(',');
				                out.write(rs.getString(3).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(4).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(5).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(6).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(7).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(8).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(9).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(10).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(11).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(12).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(13).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(14).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(15).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(16).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(17).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(18).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(19).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(20).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(21).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(22).replace(',',' '));
				                out.write(',');
				                out.write(rs.getString(23).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(24).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(25).replace(',',' ')); 
				                out.write(',');
				                out.write(rs.getString(26).replace(',',' ')); 
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
					             selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where horariosescalonadosv2.cumplimiento.empleadoID in (select horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioReporteInterno from horariosescalonadosv2.PerfilConsultaInternos where horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioConsultaInterno = '"+usuario+"' )and horariosescalonadosv2.cumplimiento.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' limit 100000";
					         }else{
					        	 selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where fecha between '"+desdeDate+ "' and '"+hastaDate+ "' limit 100000 ";
					         }
					        	conn = Connector.getConexion();
					            st = conn.createStatement();
					            rs = st.executeQuery(selectSql);
					            while (rs.next()) {
					                out.write(rs.getString(1).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(2).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(3).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(4).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(5).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(6).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(7).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(8).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(9).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(10).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(11).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(12).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(13).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(14).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(15).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(16).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(17).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(18).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(19).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(20).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(21).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(22).replace(',',' ')); 
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
					             selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno where horariosescalonadosv2.cumplimientoExterno.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.cumplimientoExterno.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'limit 100000";
					         }else{
					        	 selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno where fecha between '"+desdeDate+ "' and '"+hastaDate+ "'limit 100000 ";
					         }
					        	conn = Connector.getConexion();
					            st = conn.createStatement();
					            rs = st.executeQuery(selectSql);
					            while (rs.next()) {
					            	out.write(rs.getString(1).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(2).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(3).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(4).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(5).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(6).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(7).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(8).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(9).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(10).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(11).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(12).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(13).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(14).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(15).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(16).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(17).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(18).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(19).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(20).replace(',',' '));
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
					             selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado where horariosescalonadosv2.Incumplimiento.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.Incumplimiento.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'limit 100000";
					         }else{
					        	 selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.Incumplimiento where fecha between '"+desdeDate+ "' and '"+hastaDate+ "'limit 100000 ";
					         }
					        	conn = Connector.getConexion();
					            st = conn.createStatement();
					            rs = st.executeQuery(selectSql);
					            while (rs.next()) {
					            	out.write(rs.getString(1).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(2).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(3).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(4).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(5).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(6).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(7).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(8).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(9).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(10).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(11).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(12).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(13).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(14).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(15).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(16).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(17).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(18).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(19).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(20).replace(',',' '));
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
					             selectSql = "SELECT HIGH_PRIORITY NoCyge, Usuario, Nombre, ApePaterno, ApeMaterno, DirGeneral, DirCorporativa, Area, EntOficial, AutorizadorID, Autorizador, Proveedor, Proyecto, Estatus, EspacioFisico, LugarAsignadoEdificio, Email ,FechaCreacionRegistro, CreadoPor, RegistroActivo FROM horariosescalonadosv2.Cyge where horariosescalonadosv2.Cyge.empleadoID in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.Cyge.FechaRegistroArchivo BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' limit 100000";
					         }else{
					        	 selectSql = "SELECT HIGH_PRIORITY NoCyge, Usuario, Nombre, ApePaterno, ApeMaterno, DirGeneral, DirCorporativa, Area, EntOficial, AutorizadorID, Autorizador, Proveedor, Proyecto, Estatus, EspacioFisico, LugarAsignadoEdificio, Email ,FechaCreacionRegistro, CreadoPor, RegistroAcitvo FROM horariosescalonadosv2.Cyge where FechaRegistroArchivo between '"+desdeDate+ "' and '"+hastaDate+ "' limit 100000";
					         }
					        	conn = Connector.getConexion();
					            st = conn.createStatement();
					            rs = st.executeQuery(selectSql);
					            while (rs.next()) {
					                out.write(rs.getString(1).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(2).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(3).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(4).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(5).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(6).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(7).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(8).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(9).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(10).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(11).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(12).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(13).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(14).replace(',',' ')); 
					                out.write(',');
					                out.write(rs.getString(15).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(16).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(17).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(18).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(19).replace(',',' '));
					                out.write(',');
					                out.write(rs.getString(20).replace(',',' '));  
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
						
				      }
	
	public void Archivodos(HttpServletRequest req, HttpServletResponse resp, String desde, String hasta, String mes, String opcion, String tipousuario, String usuario, String semana, String dia) throws IOException
	{
//		ArrayList<String> desdehasta = new ArrayList<String>();
		String cuenta = req.getUserPrincipal().getName();
		InsertarRegistro daoInsert = new InsertarRegistro();
		QueryTables daoSelect = new QueryTables();
		
//		desdehasta = daoSelect.desdehasta(cuenta);
//		desde = desdehasta.get(0);
//		hasta = desdehasta.get(1);
//		mes = desdehasta.get(2);
		
		req.setAttribute("dia", dia);
		req.setAttribute("mes", mes);
		req.setAttribute("semana", semana);
        req.setAttribute("lstOpcion", opcion);
		
		
		RequestDispatcher dispatcher;
		if(dia !=""){
	    TiempoReporte tiempo = new TiempoReporte();
	    desde = tiempo.Tiempo5(hasta, desde);
		}if(mes!=""){
			TiempoReporte tiempo = new TiempoReporte();
			desde = tiempo.Tiempo4(mes, desde);
			hasta = tiempo.Tiempo3(mes, hasta);
		}if(semana!=""){
		    TiempoReporte tiempo = new TiempoReporte();
			desde = tiempo.Tiempo(semana, desde);
			hasta = tiempo.Tiempo2(desde, hasta);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		java.sql.Date desdeDate = null;
		java.sql.Date hastaDate = null;
		try {
			desdeDate = new java.sql.Date(df.parse(desde).getTime());
			hastaDate =  new java.sql.Date(df.parse(hasta).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		
		
		
		int maximoreg = 0;
		
		maximoreg = daoSelect.maximoCumplimiento(desdeDate, hastaDate,opcion,usuario,tipousuario );
		
		String selectSql = "";
		resp.setContentType("application/vnd.ms-excel");
		PrintWriter out = null;
		Connection conn = null;	
		
    	out = resp.getWriter();

	       
        ResultSet rs = null;
        Statement st = null;
        
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
	             selectSql = "SELECT HIGH_PRIORITY Usuario, Nombre, CRDireccionGeneral, direccionGeneral, CRDireccionCorporativa, direccionCorporativa, CRArea, Area, Fecha, Quincena, Mes, TEA, entradaOficial, TED, entradaReal, CalificacionEntrada, TSA, salidaOficial ,TSD, salidaReal, CalificacionSalida, Jornada, CalificacionJornada, CalificacionTotal, PorcentajeCumplimiento, EdificioAsignado FROM horariosescalonadosv2.CumplimientoExternoRRHH where horariosescalonadosv2.CumplimientoExternoRRHH.Usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"')and Fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'limit 100000,"+maximoreg+"";
	            }else{
	             selectSql = "SELECT HIGH_PRIORITY Usuario, Nombre, CRDireccionGeneral, direccionGeneral, CRDireccionCorporativa, direccionCorporativa, CRArea, Area, Fecha, Quincena, Mes, TEA, entradaOficial, TED, entradaReal, CalificacionEntrada, TSA, salidaOficial ,TSD, salidaReal, CalificacionSalida, Jornada, CalificacionJornada, CalificacionTotal, PorcentajeCumplimiento, EdificioAsignado FROM horariosescalonadosv2.CumplimientoExternoRRHH where fecha between '"+desdeDate+"' and '"+hastaDate+"' limit 100000,"+maximoreg+"";
	            }
	        	conn = Connector.getConexion();
	            st = conn.createStatement();
	            rs = st.executeQuery(selectSql);
	            while (rs.next()) {
	                out.write(rs.getString(1).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(2).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(3).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(4).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(5).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(6).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(7).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(8).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(9).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(10).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(11).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(12).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(13).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(14).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(15).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(16).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(17).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(18).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(19).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(20).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(21).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(22).replace(',',' '));
	                out.write(',');
	                out.write(rs.getString(23).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(24).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(25).replace(',',' ')); 
	                out.write(',');
	                out.write(rs.getString(26).replace(',',' ')); 
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
		             selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where horariosescalonadosv2.cumplimiento.empleadoID in (select horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioReporteInterno from horariosescalonadosv2.PerfilConsultaInternos where horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioConsultaInterno = '"+usuario+"' )and horariosescalonadosv2.cumplimiento.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' limit 8000,"+maximoreg+"";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY empleadoID, apePaterno, apeMaterno, nombre, nombreCR, dga, fecha, quincena, mes, tae, entrada, tde, entradaReal, califEntrada, tas, salida, tds, salidaReal ,califSalida, jornada, total, porcentaje FROM horariosescalonadosv2.cumplimiento where fecha between '"+desdeDate+ "' and '"+hastaDate+ "'limit 8000,"+maximoreg+"";
		         }
		        	conn = Connector.getConexion();
		            st = conn.createStatement();
		            rs = st.executeQuery(selectSql);
		            while (rs.next()) {
		                out.write(rs.getString(1).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(2).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(3).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(4).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(5).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(6).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(7).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(8).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(9).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(10).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(11).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(12).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(13).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(14).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(15).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(16).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(17).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(18).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(19).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(20).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(21).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(22).replace(',',' ')); 
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
		             selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno where horariosescalonadosv2.cumplimientoExterno.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.cumplimientoExterno.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'limit 100000,"+maximoreg+"";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno where fecha between '"+desdeDate+ "' and '"+hastaDate+ "'limit 100000,"+maximoreg+"";
		         }
		        	conn = Connector.getConexion();
		            st = conn.createStatement();
		            rs = st.executeQuery(selectSql);
		            while (rs.next()) {
		                out.write(rs.getString(1).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(2).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(3).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(4).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(5).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(6).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(7).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(8).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(9).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(10).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(11).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(12).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(13).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(14).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(15).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(16).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(17).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(18).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(19).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(20).replace(',',' ')); 
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
		             selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.Incumplimiento where horariosescalonadosv2.Incumplimiento.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.Incumplimiento.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'limit 100000,"+maximoreg+"";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY usuario, ncyge, nombre, direccionGeneral, direccionCorporativa, area, fecha, mes, quincena, entradaOficial, entradaReal, salidaReal, jornada, estancia, edificio, autorizador, provedor, proyecto ,estatus, edificioAsignado FROM horariosescalonadosv2.Incumplimiento where fecha between '"+desdeDate+ "' and '"+hastaDate+ "' limit 100000,"+maximoreg+"";
		         }
		        	conn = Connector.getConexion();
		            st = conn.createStatement();
		            rs = st.executeQuery(selectSql);
		            while (rs.next()) {
		                out.write(rs.getString(1).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(2).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(3).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(4).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(5).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(6).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(7).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(8).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(9).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(10).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(11).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(12).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(13).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(14).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(15).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(16).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(17).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(18).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(19).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(20).replace(',',' ')); 
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
		             selectSql = "SELECT HIGH_PRIORITY NoCyge, Usuario, Nombre, ApePaterno, ApeMaterno, DirGeneral, DirCorporativa, Area, EntOficial, AutorizadorID, Autorizador, Proveedor, Proyecto, Estatus, EspacioFisico, LugarAsignadoEdificio, Email ,FechaCreacionRegistro, CreadoPor, RegistroActivo FROM horariosescalonadosv2.Cyge where horariosescalonadosv2.Cyge.empleadoID in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )and horariosescalonadosv2.Cyge.FechaRegistroArchivo BETWEEN '"+desdeDate+"' AND '"+hastaDate+"'limit 100000,"+maximoreg+"";
		         }else{
		        	 selectSql = "SELECT HIGH_PRIORITY NoCyge, Usuario, Nombre, ApePaterno, ApeMaterno, DirGeneral, DirCorporativa, Area, EntOficial, AutorizadorID, Autorizador, Proveedor, Proyecto, Estatus, EspacioFisico, LugarAsignadoEdificio, Email ,FechaCreacionRegistro, CreadoPor, RegistroActivo FROM horariosescalonadosv2.Cyge where FechaRegistroArchivo between '"+desdeDate+ "' and '"+hastaDate+ "'limit 100000,"+maximoreg+"";
		         }
		        	conn = Connector.getConexion();
		            st = conn.createStatement();
		            rs = st.executeQuery(selectSql);
		            while (rs.next()) {
		                out.write(rs.getString(1).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(2).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(3).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(4).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(5).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(6).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(7).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(8).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(9).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(10).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(11).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(12).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(13).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(14).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(15).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(16).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(17).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(18).replace(',',' '));
		                out.write(',');
		                out.write(rs.getString(19).replace(',',' ')); 
		                out.write(',');
		                out.write(rs.getString(20).replace(',',' ')); 
		                out.write('\n');
		                  	               
		            }
	         
      
	            resp.setContentType("application/download");
	            resp.setHeader("Content-disposition", "attachment; filename =reporte2.csv");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	
	            out.close();
	            try {
	            	daoInsert.updateReporteRegLimpia(cuenta);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
	}
}


