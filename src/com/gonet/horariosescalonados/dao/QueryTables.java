package com.gonet.horariosescalonados.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;





import com.gonet.horariosescalonados.bean.BeanCumplimiento;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExterno;
import com.gonet.horariosescalonados.bean.BeanDocumento;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.EmpleadoExterno;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanPerfilConsulta;
import com.gonet.horariosescalonados.connector.Connector;


public class QueryTables {
	
	
	
	@SuppressWarnings("unused")
	public static Object getDato(String table, String field, String id){
		try{
			Connection conn = Connector.getConexion();
			try {	
				
				String selectSql = "SELECT * FROM horariosescalonadosv2."+table+" WHERE "+field+"='"+id.trim()+"';";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				if(!resultSet.next()){
					System.out.println("--> Campos no encontrado: "+field+": "+id.trim());
					if(table.equals("empleado")){
						BeanEmpleado beanEmpleado = new BeanEmpleado();
						return beanEmpleado;
					}else if(table.equals("horario")){
						BeanHorario beanHorario = new BeanHorario();
						return beanHorario;						
					}else if(table.equals("mensaje")){
						BeanMensaje beanMensaje = new BeanMensaje();
						return beanMensaje;						
					}else if(table.equals("empleadohorario")){
						BeanEmpleadoHorario beanEmpleadoHorario = new BeanEmpleadoHorario();
						return beanEmpleadoHorario;						
					}else if(table.equals("solicitud")){
						BeanSolicitud beanSolicitud = new BeanSolicitud();
						return beanSolicitud;						
					}
					return null;
				}else{
					if(table.equals("empleado")){
						System.out.println("--> getDato(): Empleado - "+id);
						BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
						return beanEmpleado;
					}else if(table.equals("horario")){
						System.out.println("--> getDato(): Horario - "+id);
						BeanHorario beanHorario = new BeanHorario(resultSet);
						return beanHorario;						
					}else if(table.equals("mensaje")){
						System.out.println("--> getDato(): Mensaje - "+id);
						BeanMensaje beanMensaje = new BeanMensaje(resultSet);
						return beanMensaje;						
					}else if(table.equals("empleadohorario")){
						System.out.println("--> getDato(): Empleado Horario - "+id);
						BeanEmpleadoHorario beanEmpleadoHorario = new BeanEmpleadoHorario(resultSet);
						return beanEmpleadoHorario;						
					}else if(table.equals("solicitud")){
						System.out.println("--> getDato(): solicitud - "+id);
						BeanSolicitud beanSolicitud = new BeanSolicitud(resultSet);
						return beanSolicitud;						
					}
					return null;
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	@SuppressWarnings("unused")
    public static List<Object> getList(String table, String field, String id, String field2, String id2){
        try{
            Connection conn = Connector.getConexion();
            List<Object> result = new LinkedList<>();//Creamos la lista que regresareos en caso de encontrar datos
            try {
            	
            	String selectSql = "SELECT * FROM horariosescalonadosv2."+table+" WHERE "+field+"='"+id.trim()+"'"
                    		+" AND "+field2+" = '"+id2.trim()+"';";
            	          
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(selectSql);
                if(!resultSet.next()){
                    System.out.println("Campos no encontrado: "+field+": "+id.trim());
                    return null;
                }else{
                	resultSet.beforeFirst();
                    if(table.equals("empleado")){
                        System.out.println("--> Empleado");
                        while (resultSet.next()) {                
                        	BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
                        	result.add(beanEmpleado); //agregamos el Bean a nuestra lista              
                        }
                    }else if(table.equals("horario")){
                        System.out.println("--> Horario");
                        while (resultSet.next()) {                
                        	BeanHorario beanHorario = new BeanHorario(resultSet);
                        	result.add(beanHorario); //agregamos el Bean a nuestra lista              
                        }                      
                    }else if(table.equals("mesaje")){
                        System.out.println("--> Mensaje");
                        while (resultSet.next()) {                
                        	BeanMensaje beanMensaje = new BeanMensaje(resultSet);
                        	result.add(beanMensaje); //agregamos el Bean a nuestra lista              
                        }
                    }else if(table.equals("solicitud")){
                        System.out.println("--> Solicitud");                        
                        while (resultSet.next()) {                
                        	BeanSolicitud beanSolicitud = new BeanSolicitud(resultSet);
                        	result.add(beanSolicitud); //agregamos el Bean a nuestra lista              
                        }                        
                    }
                    return result;
                }
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    } 
	
	@SuppressWarnings("unused")
    public static List<Object> getList(String table){
        try{
            Connection conn = Connector.getConexion();
            List<Object> result = new LinkedList<>();//Creamos la lista que regresareos en caso de encontrar datos
            try {   
                String selectSql = "SELECT * FROM horariosescalonadosv2."+table+";";
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(selectSql);                
                if(!resultSet.next()){
                    System.out.println("Lista vacia!! ");
                    return null;
                }else{
                	resultSet.beforeFirst();
                    if(table.equals("empleado")){
                        System.out.println("--> Empleado");
                        while (resultSet.next()) {                
                        	BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
                        	result.add(beanEmpleado); //agregamos el Bean a nuestra lista              
                        }                
                    }else if(table.equals("horario")){
                        System.out.println("--> Horario");
                        while (resultSet.next()) {                
                        	BeanHorario beanHorario = new BeanHorario(resultSet);
                        	result.add(beanHorario); //agregamos el Bean a nuestra lista              
                        }                
                    }else if(table.equals("mensaje")){
                        System.out.println("--> Mensaje");
                        while (resultSet.next()) {                
                        	BeanMensaje beanMensaje = new BeanMensaje(resultSet);
                        	result.add(beanMensaje); //agregamos el Bean a nuestra lista              
                        }                
                    }else if(table.equals("solicitud")){
                        System.out.println("--> solicitud");                        
                        while (resultSet.next()) {                
                        	BeanSolicitud beanSolicitud = new BeanSolicitud(resultSet);
                        	result.add(beanSolicitud); //agregamos el Bean a nuestra lista              
                        }
                    }
                    return result;
                }
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    } 
	
	public static List<BeanHorario> listHorarios(){
		System.out.println("--> Recuperamos lista de horarios");
		try{
			Connection conn = Connector.getConexion();
			try {	
				List<BeanHorario> listHorarios = new ArrayList<>();
				String selectSql = "SELECT * FROM horariosescalonadosv2.horario ORDER BY STR_TO_DATE(entrada, '%T');";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				while(resultSet.next()){
					BeanHorario beanHorario = new BeanHorario(resultSet);
					listHorarios.add(beanHorario);
				}
				if(listHorarios.isEmpty()){
					System.out.println("--> lista Vacia!");
					return null;
				}
				return listHorarios;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	public static List<BeanEmpleado> listEmpleados(String field, String value){
		System.out.println("--> Recuperamos lista de Empleados donde "+field+"="+value);
		try{
			Connection conn = Connector.getConexion();
			try {	
				List<BeanEmpleado> listEmpleados = new ArrayList<>();
				String selectSql = "SELECT * FROM horariosescalonadosv2.empleado WHERE "+field+"='"+value+"';";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				while(resultSet.next()){
					BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
					listEmpleados.add(beanEmpleado);
				}
				if(listEmpleados.isEmpty()){
					System.out.println("--> lista Vacia!");
					return null;
				}
				return listEmpleados;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	public static List<BeanEmpleado> listEmpleados(String field, String value, 
			String field2, String value2){
		System.out.println("--> Recuperamos lista de Empleados donde "+field+"='"+value+"' y "
			+field2+"='"+value2+"'");
		try{
			Connection conn = Connector.getConexion();
			try {	
				List<BeanEmpleado> listEmpleados = new ArrayList<>();
				String selectSql = "SELECT * FROM horariosescalonadosv2.empleado WHERE "+field+"='"+value+"'"
						+ " AND "+field2+"='"+value2+"';";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				while(resultSet.next()){
					BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
					listEmpleados.add(beanEmpleado);
				}
				if(listEmpleados.isEmpty()){
					System.out.println("--> lista Vacia!");
					return null;
				}
				return listEmpleados;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	public static List<BeanEmpleado> listEmpleados(String field, String value, 
			String field2, String value2, String field3, String value3){
		System.out.println("--> Recuperamos lista de Empleados donde "+field+"='"+value+"' y "
			+field2+"='"+value2+"' y "+field3+"='"+value3+"'");
		try{
			Connection conn = Connector.getConexion();
			try {	
				List<BeanEmpleado> listEmpleados = new ArrayList<>();
				String selectSql = "SELECT * FROM horariosescalonadosv2.empleado WHERE "+field+"='"+value+"'"
						+ " AND "+field2+"='"+value2+"' AND "+field3+"='"+value3+"';";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				while(resultSet.next()){
					BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
					listEmpleados.add(beanEmpleado);
				}
				if(listEmpleados.isEmpty()){
					System.out.println("--> lista Vacia!");
					return null;
				}
				return listEmpleados;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
			
	public List<BeanCumplimiento> cumplimiento(Date desde, Date hasta, String usuario) throws ParseException{
		try{
			Connection conn = Connector.getConexion();
			try {	
				String selectSql = "SELECT * FROM horariosescalonadosv2.cumplimiento "
						+ "WHERE fecha BETWEEN '"+desde+"' AND '"+hasta+"'"+" "
						+ "horariosescalonadosv2.cumplimiento.empleadoID in (select horariosescalonadosv2.PerfilConsultaInterno.IdUsuarioReporteInterno "
						+ "from horariosescalonadosv2.PerfilConsultaInterno where horariosescalonadosv2.PerfilConsultaInterno.IdUsuarioConsultaInterno = '"+usuario+"'"; //realizamos la busqeuda de valores
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql); //ejecutamos Query
				
				if(!resultSet.next()){ //Si no trae registros salimos del metodo
					System.out.println("Sin registros!");
					return null;
				}else{
					List<BeanCumplimiento> result = new LinkedList<>();//Creamos la lista que regresareos en caso de encontrar datos
				    while (resultSet.next()) {
				    	//System.out.println(resultSet.getString("apePaterno")+"-"+resultSet.getString("apeMaterno"));
				    	BeanCumplimiento beanCumplimiento = new BeanCumplimiento(resultSet);//Ingresamos los datos dentro de nuestro Bean
				    	result.add(beanCumplimiento); //agregamos el Bean a nuestra lista
				    }
					return result; //regresamos la lista de BEans
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	
	public List<BeanPerfilConsulta> listPerfilConsulta(String field,String value){
		System.out.println("-->Recuperamos lista de Perfil Consulta donde " + field +" = " + value);
		try{
			Connection conn = Connector.getConexion();
			try{
				List<BeanPerfilConsulta> listPerfilConsulta = new ArrayList<>();
				String selectSql = "SELECT * FROM horariosescalonadosv2.PerfilConsulta WHERE"+field+"="+value+"';";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				while(resultSet.next()){
					BeanPerfilConsulta beanPerfilConsulta = new BeanPerfilConsulta(resultSet);
					listPerfilConsulta.add(beanPerfilConsulta);
				}
				if(listPerfilConsulta.isEmpty()){
					System.out.println("-->Lista Vacia!");
					return null;
				}
				return listPerfilConsulta;
			}finally{
				conn.close();
			}
		}catch(SQLException e){
			System.err.println(e);
			return null;
		}
	}
	
	 //BETY REPORTES
	 @SuppressWarnings("unused")
	 public static List<Object> getListReportes(String table, String field, String id, String field2, Date id2 , Date id3){
	         try{
	          System.out.println("Entra a capa de datos tabla: "+table);
	             Connection conn = Connector.getConexion();
	             List<Object> result = new LinkedList<>();//Creamos la lista que regresareos en caso de encontrar datos
	             try {  
	                 String selectSql = "SELECT * FROM horariosescalonadosv2."+table+" WHERE "+field+"='"+id.trim()+"' AND "+field2+" between '"+id2+"' AND '"+id3+"';";
	                 System.out.println("Query Reportes: "+selectSql);
	                
	                 Statement stmt = conn.createStatement();
	                 ResultSet resultSet = stmt.executeQuery(selectSql);
	                
	                
	                 if(!resultSet.next()){
	                     System.out.println("No existen registros en la tabla: "+table);
	                     return null;
	                 }else{
	                  resultSet.beforeFirst();
	                     if(table.equals("empleado")){
	                         System.out.println("Empleado");
	                         while (resultSet.next()) {               
	                             BeanEmpleado beanEmpleado = new BeanEmpleado(resultSet);
	                             result.add(beanEmpleado); //agregamos el Bean a nuestra lista             
	                         }               
	                        
	                     }else if(table.equals("horario")){
	                         System.out.println("Horario");
	                         while (resultSet.next()) {               
	                             BeanHorario beanHorario = new BeanHorario(resultSet);
	                             result.add(beanHorario); //agregamos el Bean a nuestra lista             
	                         }               
	                                               
	                     }else if(table.equals("mensaje")){
	                         System.out.println("Mensaje**");
	                         while (resultSet.next()) {               
	                             BeanMensaje beanMensaje = new BeanMensaje(resultSet);
	                             result.add(beanMensaje); //agregamos el Bean a nuestra lista             
	                         }               
	                         
	                     }else if(table.equals("solicitud")){
	                         System.out.println("solicitud");                       
	                         while (resultSet.next()) {               
	                             BeanSolicitud beanSolicitud = new BeanSolicitud(resultSet);
	                             result.add(beanSolicitud); //agregamos el Bean a nuestra lista             
	                         }
	                        
	                     }
	                     return result;
	                 }
	             } finally {
	                 conn.close();
	             }
	         } catch (SQLException e) {
	             System.err.println(e);
	             return null;
	         }
	     }
	 
	 public BeanDocumento blobDocs(){
		try{
			Connection conn = Connector.getConexion();
			try {	
				String selectSql = "SELECT * FROM horariosescalonados.blobdocs ORDER BY id DESC LIMIT 1;";
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql);
				if(!resultSet.next()){
					return null;
				}else{
					BeanDocumento beanDocumento = new BeanDocumento(resultSet);
					return new BeanDocumento(resultSet);
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}
	 
	 public ArrayList <String> CorreosActual()
	 {
		 
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = new GregorianCalendar();
			
			String dia, mes , annio;
			
			dia = Integer.toString(c2.get(Calendar.DATE));
			mes = Integer.toString(c2.get(Calendar.MONTH));
			annio = Integer.toString(c2.get(Calendar.YEAR));
			
			int mess = Integer.valueOf(mes);
			
			mess = mess + 1;
			
			mes= String.valueOf(mess);
			
			
			//2016-02-04
			System.out.println(annio+"-0"+ mes +"-0"+dia);
			
			String strfecha = annio+"-0"+ mes +"-0"+dia;
			
			ArrayList <String> lisCorreo = new ArrayList <String>();
			
			//strfecha = "2016-02-04";
		 
		 try{
				Connection conn = Connector.getConexion();
				try {	
					String selectSql = "SELECT Email FROM horariosescalonadosv2.Cyge where FechaCreacionRegistro= ?";
					PreparedStatement stmt = conn.prepareStatement(selectSql);
					stmt.setString(1, strfecha);					
					ResultSet resultSet = stmt.executeQuery();
					
					while(resultSet.next()){
						
						lisCorreo.add(resultSet.getString(1));
						
					}
					
					return lisCorreo;
					
				} finally {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(e);
				
			}		 
		return null;
		 
	 }

}
