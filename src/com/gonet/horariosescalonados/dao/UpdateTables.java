package com.gonet.horariosescalonados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;

import com.gonet.horariosescalonados.connector.Connector;

public class UpdateTables {
//	public void setSupervisor(String idEmpleado, String idSupervisor){
//		/*public void setSupervisor(){
//			String idEmpleado="XM07648", idSupervisor="MB21090";*/
//			
//			try{
//				Statement stmnt = null;
//				Connection conn = Connector.getConexion();
//				try {	
//					String updateSuper = "SELECT supervisorID "
//										  +"FROM hoariosescalonadosv2.empleado "
//										  +"WHERE empleadoID = '"+idEmpleado+"';";
//
//					stmnt = (Statement) conn.createStatement();
//					ResultSet rs = stmnt.executeQuery(updateSuper);
//
//					while(rs.next()){
//						String oldSuper = rs.getString("supervisorID");
//						if(oldSuper != idSupervisor)
//							reemplazaSupervisor(idEmpleado, idSupervisor);
//					}
//
//				}finally{
//					conn.close();
//				}
//			}catch(SQLException e){
//				System.err.println(e);
//			}
//		}
		
//		public void reemplazaSupervisor(String idEmpleado, String idSuperV){
//			try{
//				Connection conn = Connector.getConexion();
//				try {	
//					String updateHorario = "UPDATE hoariosescalonadosv2.empleado "
//										  +"SET supervisorID = ? "
//										  +"WHERE empleadoID = ?;";
//					
//					PreparedStatement stmt = conn.prepareStatement(updateHorario);				
//					stmt.setString(1, idSuperV);
//					stmt.setString(2, idEmpleado);
//					
//					int success = 2;
//					success = stmt.executeUpdate();
//					
//					if (success == 1) {
//				    	System.out.println("Supervisor Updated!");
//				    } else if (success == 0) {
//				    	System.err.println("Failure! Please try again!");
//				    }
//				}finally{
//					conn.close();
//				}
//			}catch(SQLException e){
//				System.err.println(e);
//			}
//		}
		
//		public static String modificarHorario(String empleado,String newIdH,String sEntrada,String sSalida){
//			System.out.println("Modificando SQL DataBase");
//			String result = "";
//			
//			try{
//				Connection conn = Connector.getConexion();
//				try {	
//					String updateHorario = "UPDATE hoariosescalonadosv2.empleado "
//										  +"SET horaEntrada = ? , horaSalida = ?, horarioID = ? "
//										  +"WHERE empleadoID = ?;";
//					
//					PreparedStatement stmt = conn.prepareStatement(updateHorario);
//					
//					stmt.setString(1, sEntrada);
//					stmt.setString(2, sSalida);
//					stmt.setString(3, newIdH);
//					stmt.setString(4, empleado);
//					
//					int success = 2;
//					success = stmt.executeUpdate();
//					
//					if (success == 1) {
//				    	System.out.println("Success!!");
//				    	result = "Success!!";
//				    	return result;
//				    } else if (success == 0) {
//				    	System.err.println("Failure! Please try again!");
//				    	result = "Failure! Please try again!";
//				    	return result;
//				    }
//				}finally{
//					conn.close();
//				}
//			}catch(SQLException e){
//				System.err.println(e);
//				result = e.toString();
//				return result;
//			}
//			return result;
//			
//		}
	
	
///*****************************************************************************************************		
	public static void setDato(String table, String field, String value, String field2, String value2){
		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String update = "UPDATE horariosescalonadosv2."+table.trim()+" "
									  +"SET "+field+" = ? "
									  +"WHERE "+field2+" = ?;";
				PreparedStatement stmt = conn.prepareStatement(update);
				stmt.setString(1, value);
				stmt.setString(2, value2);
				System.out.println(update);
				int success = 2;
				success = stmt.executeUpdate();
				if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }
			}finally{
				conn.close();
			}
		}catch(SQLException e){
			System.err.println(e);
		}
	}
	
	public static void setDato(String table, String field,String value, String field2, String value2, String field3, String value3, String field4, String value4){		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String updateHorario = "UPDATE horariosescalonadosv2."+table.trim()+" "
									  +"SET "+field+" = ? , "
									  + field2+" = ? , "
									  + field3+" = ? "									  
									  +"WHERE "+field4+" = ?;";
				PreparedStatement stmt = conn.prepareStatement(updateHorario);
				stmt.setString(1, value);
				stmt.setString(2, value2);
				stmt.setString(3, value3);
				stmt.setString(4, value4);
				int success = 2;
				success = stmt.executeUpdate();
				if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }
			}finally{
				conn.close();
			}
		}catch(SQLException e){
			System.err.println(e);
		}
	}
	
	
	
	public static void setDato(String table, String field,String value, String field2, String value2, String field3, Date value3, String field4, String value4){		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String updateHorario = "UPDATE horariosescalonadosv2."+table.trim()+" "
									  +"SET "+field+" = ? , "
									  + field2+" = ? , "
									  + field3+" = ? "									  
									  +"WHERE "+field4+" = ?;";
				PreparedStatement stmt = conn.prepareStatement(updateHorario);
				stmt.setString(1, value);
				stmt.setString(2, value2);
				stmt.setDate(3, value3);
				stmt.setString(4, value4);
				int success = 2;
				success = stmt.executeUpdate();
				if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }
			}finally{
				conn.close();
			}
		}catch(SQLException e){
			System.err.println(e);
		}
	}
	
	public static void setDato(String table, String field, Date value, String field2, String value2){
		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String updateHorario = "UPDATE horariosescalonadosv2."+table.trim()+" "
									  +"SET "+field+" = ? "
									  +"WHERE "+field2+" = ?;";
				PreparedStatement stmt = conn.prepareStatement(updateHorario);
				stmt.setDate(1, value);
				stmt.setString(2, value2);
				int success = 2;
				success = stmt.executeUpdate();
				if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }
			}finally{
				conn.close();
			}
		}catch(SQLException e){
			System.err.println(e);
		}
	}
	
public static void setDato(String table, String field,Date value, String field2, Date value2, String field3, String value3){
		
		try{
			Connection conn = Connector.getConexion();
			try {	
				String updateHorario = "UPDATE horariosescalonadosv2."+table.trim()+" "
									  +"SET "+field+" = ? , "
									  +field2+" = ? "
									  +"WHERE "+field3+" = ?;";
				PreparedStatement stmt = conn.prepareStatement(updateHorario);
				stmt.setDate(1, value);
				stmt.setDate(2, value2);
				stmt.setString(3, value3);
				int success = 2;
				success = stmt.executeUpdate();
				if (success == 1) {
			    	System.out.println("Success!!");
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");
			    }
			}finally{
				conn.close();
			}
		}catch(SQLException e){
			System.err.println(e);
		}
	}
	
	//BETY
//		public static String ModificarSolicitud(String solicitudId,String decision,String motivo, Date fecha) throws ParseException{
//			String result = "";
//			try{
//				Connection conn = Connector.getConexion();
//				try {	
//					String updateHorario = "UPDATE horariosescalonadosv2.solicitud "
//										  +"SET decision = ? , motivo = ?, fechaRespuesta = ?"
//										  +"WHERE solicitudId = ?;";
//					
//					PreparedStatement stmt = conn.prepareStatement(updateHorario);
//					
//					stmt.setString(1, decision);
//					stmt.setString(2, motivo);
//					stmt.setDate(3, fecha);						
//					stmt.setString(4, solicitudId);
//					
//					int success = 2;
//					success = stmt.executeUpdate();
//					
//					if (success == 1) {
//				    	System.out.println("Success!!");
//				    	result = "Success!!";
//				    	return result;
//				    } else if (success == 0) {
//				    	System.err.println("Failure! Please try again!");
//				    	result = "Failure! Please try again!";
//				    	return result;
//				    }
//				}finally{
//					conn.close();
//				}
//			}catch(SQLException e){
//				System.err.println(e);
//				result = e.toString();
//				return result;
//			}
//			return result;
//			
//		}

		//BETY
		public static String ModificarFechaSolicitud(String empleadoId) throws ParseException{
			String result = "";			
			try{			
				Connection conn = Connector.getConexion();
				try {	
					String updateHorario = "UPDATE horariosescalonadosv2.empleadohorario "
										  +"SET fechaSolicitud = null "
										  +"WHERE empleadoID = ?;";
					
					PreparedStatement stmt = conn.prepareStatement(updateHorario);
					
					
					stmt.setString(1, empleadoId);					
					
						
					int success = 2;
					success = stmt.executeUpdate();
					
					if (success == 1) {
				    	System.out.println("Success!!");
				    	result = "Success!!";
				    	return result;
				    } else if (success == 0) {
				    	System.err.println("Failure! Please try again!");
				    	result = "Failure! Please try again!";
				    	return result;
				    }
				}finally{
					conn.close();
				}
			}catch(SQLException e){
				System.err.println(e);
				result = e.toString();
				return result;
			}
			return result;
			
		}

		//BETY
		public static String BloqueaHorario(String key,int bloqueo){
			System.out.println("Modificando en la tabla de Horarios SQL DataBase"+bloqueo);
			String result = "";

			try{
				Connection conn = Connector.getConexion();
				try {	
					String updateHorario = "UPDATE horariosescalonadosv2.horario "
										  +"SET Enabled = ? "
										  +"WHERE horarioID = ?;";
					
					PreparedStatement stmt = conn.prepareStatement(updateHorario);
					
					stmt.setInt(1, bloqueo);
					stmt.setString(2, key);
					
					int success = 2;
					success = stmt.executeUpdate();
					
					if (success == 1) {
				    	System.out.println("Success!!");
				    	result = "Success!!";
				    	return result;
				    } else if (success == 0) {
				    	System.err.println("Failure! Please try again!");
				    	result = "Failure! Please try again!";
				    	return result;
				    }
				}finally{
					conn.close();
				}
			}catch(SQLException e){
				System.err.println(e);
				result = e.toString();
				return result;
			}
			return result;
			
		}
		
		public static void deleteDato(String field, String value){
			try{
				Connection conn = Connector.getConexion();
				try {	
					String update = "UPDATE horariosescalonadosv2.empleadohorario "
										  +"SET "+field+" = null "
										  +"WHERE empleadoID = ?;";
					PreparedStatement stmt = conn.prepareStatement(update);
					stmt.setString(1, value);
					System.out.println(update);
					int success = 2;
					success = stmt.executeUpdate();
					if (success == 1) {
				    	System.out.println("Success!!");
				    } else if (success == 0) {
				    	System.err.println("Failure! Please try again!");
				    }
				}finally{
					conn.close();
				}
			}catch(SQLException e){
				System.err.println(e);
			}
		}
}
