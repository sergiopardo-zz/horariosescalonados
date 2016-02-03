package com.gonet.horariosescalonados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gonet.horariosescalonados.bean.BeanCumplimiento;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanDocumento;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoExterno;
import com.gonet.horariosescalonados.bean.BeanEmpleadoExternoRRHH;
import com.gonet.horariosescalonados.bean.EmpleadoExterno;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.bean.BeanZeit;
import com.gonet.horariosescalonados.connector.Connector;


public class InsertarRegistro {
	
	public static void empleado(BeanEmpleado empleado){		
		try{
			System.out.println(empleado);
			Connection conn = Connector.getConexion();
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.empleado (empleadoID, apePaterno, apeMaterno, nombre,"
									+ "email, edificio, tipoEmpleado, registro, nombreCR, dga, horarioID,  horaEntrada, horaSalida,"
									+ "supervisorID)"
									+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);
				stmt.setString(1, empleado.getEmpleadoID());
			    stmt.setString(2, empleado.getApePaterno());
			    stmt.setString(3, empleado.getApeMaterno());
			    stmt.setString(4, empleado.getNombre());
			    stmt.setString(5, empleado.getEmail());
			    stmt.setString(6, empleado.getEdificio());
			    stmt.setString(7, empleado.getTipoEmpleado());
			    stmt.setString(8, empleado.getRegistro());
			    stmt.setString(9, empleado.getNombreCR());
			    stmt.setString(10, empleado.getDga());
			    stmt.setString(11, empleado.getHorarioID());
			    stmt.setString(12, empleado.getHoraEntrada());
			    stmt.setString(13, empleado.getHoraSalida());
			    stmt.setString(14, empleado.getSupervisorID());
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	  
			    String statement1 = "INSERT INTO horariosescalonadosv2.empleadohorario (empleadoID, fechaAplicacion,fechaSolicitud)"
						+ "VALUES(?,?,?);";
			    PreparedStatement stmt1 = conn.prepareStatement(statement1);
			    stmt1.setString(1, empleado.getEmpleadoID());
			    stmt1.setDate(2, null );
			    stmt1.setDate(3, null );
			    int success1 = 2;
			    success1 = stmt1.executeUpdate();
			    if (success1 == 1) {
			    	System.out.println("Success!!");
			    } else if (success1 == 0) {
			    	System.err.println("Failure! Please try again!");
			    }  
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void empleadoExterno(BeanEmpleadoExternoRRHH empleadoExterno){		
		try{
			System.out.println(empleadoExterno);
			Connection conn = Connector.getConexion();
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.EmpleadoExternoRRHH (Usuario, DireccionGeneral, DireccionCorporativa, Area, CRDireccionGeneral, CRDireccionCorporativa, CRArea, Edificio)"
									+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);
				stmt.setString(1, empleadoExterno.getStrUsuario());
			    stmt.setString(2, empleadoExterno.getStrDirGeneral());
			    stmt.setString(3, empleadoExterno.getStrDirCorporativa());
			    stmt.setString(4, empleadoExterno.getStrArea());
			    stmt.setString(5, empleadoExterno.getCRDireccionGeneral());
			    stmt.setString(6, empleadoExterno.getCRDireccionCorporativa());
			    stmt.setString(7, empleadoExterno.getCRArea());
			    stmt.setString(8, empleadoExterno.getEdificio());
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	    
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	
	
	
	public static void empleadoHorario(BeanEmpleado empleado){		
		try{
			System.out.println(empleado);
			Connection conn = Connector.getConexion();
			try {	
				String statement1 = "INSERT INTO horariosescalonadosv2.empleadohorario (empleadoID, fechaAplicacion,fechaSolicitud)"
						+ "VALUES(?,?,?);";
			    PreparedStatement stmt1 = conn.prepareStatement(statement1);
			    stmt1.setString(1, empleado.getEmpleadoID());
			    stmt1.setDate(2, null );
			    stmt1.setDate(3, null );
			    int success = 2;
			    success = stmt1.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void cumplimiento(BeanCumplimiento cumplimiento){		
		try{
			Connection conn = Connector.getConexion();
			java.sql.Date newDateSql = new java.sql.Date(cumplimiento.getFecha().getTime());
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.cumplimiento (empleadoID, apePaterno, apeMaterno, nombre,"
									+ "nombreCR, dga, fecha, quincena, mes, tae, entrada,  tde, entradaReal,"
									+ "califEntrada, tas, salida, tds, salidaReal, califSalida, jornada, total, porcentaje)"
											//1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22
									+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);				  
				stmt.setString(1, cumplimiento.getEmpleadoID());
			    stmt.setString(2, cumplimiento.getApePaterno());
			    stmt.setString(3, cumplimiento.getApeMaterno());
			    stmt.setString(4, cumplimiento.getNombre());
			    stmt.setString(5, cumplimiento.getNombreCR());
			    stmt.setString(6, cumplimiento.getDga());
			    stmt.setDate(7, newDateSql);
			    stmt.setString(8, cumplimiento.getQuincena());
			    stmt.setString(9, cumplimiento.getMes());
			    stmt.setString(10, cumplimiento.getTae());
			    stmt.setString(11, cumplimiento.getEntrada());
			    stmt.setString(12, cumplimiento.getTde());
			    stmt.setString(13, cumplimiento.getEntradaReal());
			    stmt.setString(14, cumplimiento.getCalifEntrada());
			    stmt.setString(15, cumplimiento.getTas());
			    stmt.setString(16, cumplimiento.getSalida());
			    stmt.setString(17, cumplimiento.getTds());
			    stmt.setString(18, cumplimiento.getSalidaReal());
			    stmt.setString(19, cumplimiento.getCalifSalida());
			    stmt.setString(20, cumplimiento.getJornada());
			    stmt.setString(21, cumplimiento.getTotal());
			    stmt.setString(22, cumplimiento.getPorcentaje());			    
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
		
	public static void horario(BeanHorario horario){		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.horario (horarioId, entrada, salida, enabled,"
									+ "empleadosActivos)"
											//1  2  3  4  5
									+ "VALUES(?, ?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);				  
				stmt.setString(1, horario.getHorarioId());
			    stmt.setString(2, horario.getEntrada());
			    stmt.setString(3, horario.getSalida());
			    stmt.setInt(4, horario.getEnabled());
			    stmt.setString(5, horario.getEmpleadosActivos());
			    
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void mensaje(BeanMensaje mensaje){		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.mensaje (mensajeClave, Texto)"
											//1  2 
									+ "VALUES(?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);				  
				stmt.setString(1, mensaje.getMensajeClave());
			    stmt.setString(2, mensaje.getTexto());
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void empleadoHorario(BeanEmpleadoHorario empleadoHorario){		
		try{
			Connection conn = Connector.getConexion();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateAplicacionUtil = null;
			java.sql.Date dateAplicacionSql = null;
			java.util.Date dateSolicitudUtil = null;
			java.sql.Date dateSolicitudSql = null;
			if(empleadoHorario.getFechaAplicacion()!=""){							
				dateAplicacionUtil = df.parse(empleadoHorario.getFechaAplicacion());
				dateAplicacionSql = new java.sql.Date(dateAplicacionUtil.getTime());
			}
			  
			if(empleadoHorario.getFechaSolicitud()!=""){
				dateSolicitudUtil = df.parse(empleadoHorario.getFechaSolicitud());
				dateSolicitudSql = new java.sql.Date(dateSolicitudUtil.getTime());
			}
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.empleadohorario "
						+ "(empleadoID,"
						+ "fechaAplicacion,"
						+ "fechaSolicitud"		
						+ ")"
								//1  2  3
						+ "VALUES(?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);				  
				stmt.setString(1, empleadoHorario.getEmpleadoID());
				stmt.setDate(2, dateAplicacionSql);
				stmt.setDate(3, dateSolicitudSql);			    
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		}catch (ParseException pe) {
			System.err.println(pe);
		}catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static boolean solicitud(BeanSolicitud solicitud){		
		try{
			System.out.println(solicitud);
			Connection conn = Connector.getConexion();
			java.sql.Date newDateSql = new java.sql.Date(solicitud.getFechaAlta().getTime());
			java.sql.Date newDateSql_1 = null;
			if(solicitud.getFechaRespuesta()!=null){
				newDateSql_1 = new java.sql.Date(solicitud.getFechaRespuesta().getTime());	
			}
			
			try {	
				String statement = "INSERT INTO horariosescalonadosv2.solicitud (solicitudID, empleadoID, nombre, supervisorID, fechaAlta, fechaRespuesta,"
									+ "horaAct, horaSel, entradaAct, entradaSol, salidaAct, salidaSol, tipo,  motivo, decision)"
									+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(statement);
				stmt.setString(1, solicitud.getSolicitudID());
				stmt.setString(2, solicitud.getEmpleadoID());
			    stmt.setString(3, solicitud.getNombre());
			    stmt.setString(4, solicitud.getSupervisorID());
			    stmt.setDate(5, newDateSql);
			    stmt.setDate(6, newDateSql_1);
			    stmt.setString(7, solicitud.getHoraAct());
			    stmt.setString(8, solicitud.getHoraSel());
			    stmt.setString(9, solicitud.getEntradaAct());
			    stmt.setString(10, solicitud.getEntradaSol());
			    stmt.setString(11, solicitud.getSalidaAct());
			    stmt.setString(12, solicitud.getSalidaSol());
			    stmt.setString(13, solicitud.getTipo());
			    stmt.setString(14, solicitud.getMotivo());
			    stmt.setString(15, solicitud.getDecision());
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    	return true;
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	
			    return false;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public void blobDocument(BeanDocumento beanDocumento){ 
		try{
			//System.out.println(cumplimiento);
			Connection conn = Connector.getConexion();
			try {	
				String statement = "INSERT INTO horariosescalonados.blobdocs (blobClave)"
											//1
									+ "VALUES(?);";
				PreparedStatement stmt = conn.prepareStatement(statement);				  
				stmt.setString(1, beanDocumento.getKeyDocument());
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
