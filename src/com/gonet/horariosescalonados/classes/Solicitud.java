package com.gonet.horariosescalonados.classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.dao.UpdateTables;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.gonet.horariosescalonados.util.EnviarCorreo;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Solicitud {
	Logger log;
	DateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	public Solicitud(){
		super();
		}
	
	public Solicitud(HttpServletRequest req, HttpServletResponse resp){
		List<BeanHorario> entHorarios = QueryTables.listHorarios();	
	}
	
	public void getSupervisores(HttpServletRequest req, HttpServletResponse resp){
		String userId = notNull(req,"hiddenIdUser");
		String userReg = notNull(req,"hiddenRegisterUser");
		String userName = notNull(req,"hiddenNameUser");
		String userApePa = notNull(req,"hiddenFirstNameUser");
		String userApeMa = notNull(req,"hiddenSecondNameUser");
		List<BeanEmpleado> entSupervisores= null;
		try {
			if(userId!=""){
				entSupervisores = Empleado.listEmpleados("empleadoID", userId);
			}
			if(userReg!=""){
				entSupervisores = Empleado.listEmpleados("registro", userReg);
			}
			if(userName!="" && userApePa!="" && userApeMa==""){
				entSupervisores = Empleado.listEmpleados("nombre", userName,  
						"apePaterno", userApePa );
			}
			if(userName!="" && userApeMa!="" && userApePa==""){
				entSupervisores = Empleado.listEmpleados("nombre", userName,  
						"apeMaterno", userApeMa );
			}
			if(userName=="" && userApeMa!="" && userApePa!=""){
				entSupervisores = Empleado.listEmpleados("apePaterno", userApePa,  
						"apeMaterno", userApeMa );
			}
			if(userName!="" && userApePa!="" && userApeMa!=""){
				entSupervisores = Empleado.listEmpleados("nombre", userName, "apePaterno", userApePa, 
						"apeMaterno", userApeMa);
			}
			req.setAttribute("listaAutorizadores", entSupervisores);
		} catch (Exception e) {
			e.printStackTrace();
		}	

		req.setAttribute("hiddenIdUser", userId);
		req.setAttribute("hiddenRegisterUser", userReg);
		req.setAttribute("hiddenNameUser", userName);
		req.setAttribute("hiddenFirstNameUser", userApePa);
		req.setAttribute("hiddenSecondNameUser", userApeMa);
		req.setAttribute("hiddenSelectedRadio", notNull(req,"hiddenSelectedRadio"));
		req.setAttribute("hiddenIsChecked", notNull(req,"hiddenIsChecked"));
		req.setAttribute("hiddenFrom", notNull(req,"hiddenFrom"));
	}
	
	public static void actualizaDatos(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("--> actualizaDatos()-Aceptar");
		String selectedIdUser ="", selectedNombre ="", radioChecked="", isChecked="";
		selectedIdUser = notNull(req,"selectedIdUser");
		selectedNombre = notNull(req,"selectedNombre");
		radioChecked = notNull(req,"hiddenSelectedRadio");
		isChecked = notNull(req,"hiddenIsChecked");
		req.setAttribute("selectedIdUser", selectedIdUser);
		req.setAttribute("selectedNombre", selectedNombre);
		req.setAttribute("radioChecked", radioChecked);
		req.setAttribute("idChecked", isChecked);
		req.setAttribute("nomAutorizador", selectedNombre);
		System.out.println("--> selectedIdUser: "+ selectedIdUser);
		System.out.println("--> selectedNombre: "+ selectedNombre);
		System.out.println("--> idChecked: "+ isChecked);
		System.out.println("--> radioChecked: "+radioChecked);
		
	}

	public static void actualizaDatosCancelar(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("--> Actualiza Datos - Cancelar");
		String radioChecked="", isChecked="";
		radioChecked = notNull(req,"hiddenSelectedRadio");
		isChecked = notNull(req,"hiddenIsChecked");
		req.setAttribute("radioChecked", radioChecked);
		req.setAttribute("idChecked", isChecked);
		System.out.println("--> idChecked: "+ isChecked);
		System.out.println("--> radioChecked. "+radioChecked);
	}

	public void generaSolictud(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//Obtenemos los datos del Empleado firmado y el horario Seleccionado
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		System.out.println("--> idUser: "+empleado.getEmpleadoID());
		BeanEmpleado supervisor = Empleado.getEmpleado(empleado.getSupervisorID());
		BeanEmpleadoHorario horario = Horario.getHorarioEmpleado(empleado.getEmpleadoID());

		System.out.println("--> (Actual)Entrada-Salida: "+empleado.getHoraEntrada()+"-"+empleado.getHoraSalida());

		//Obtenemos los datos del Supervisor Seleccionado/Registrado
		String idSuper = req.getParameter("hiddenIdSupervisor").toString();
		
		//Si el supervisor es modificado se cambia en Jerarquia
		if(empleado.getSupervisorID().trim().equals(idSuper)){
			System.out.println("--> No se registro cambio de supervisor");
		}else{
			System.out.println("--> Se modifica supervisor: "+idSuper);
			Empleado.setSupervisor("empleado", "supervisorID", idSuper, "empleadoID", empleado.getEmpleadoID());
			Empleado.setSupervisor("empleado", "tipoEmpleado", "S", "empleadoID", idSuper);
			supervisor = Empleado.getEmpleado(idSuper);
			empleado = Empleado.getEmpleado(req);
		}
		//Obtenemos el horario actual y el horario solicitado
		String horaSolic = req.getParameter("hiddenSelectedRadio").toString();
		BeanHorario horaSol = Horario.getHorario("horario", "horarioID", horaSolic);
		System.out.println("--> (Solic)Entrada-Salida: "+horaSol.getEntrada()+"-"+horaSol.getSalida());
		String from = notNull(req, "opcion");
		try {
			
			if(from.equals("solicitud")){
				EnviarCorreo enviarCorreo = new EnviarCorreo(
						"ALTA", 
						empleado.getEmail(),
						supervisor.getEmail(),
						supervisor.getApePaterno()+" "+
						supervisor.getApeMaterno()+", "+
						supervisor.getNombre()
						);
				enviarCorreo.enviar();
				setSolicitud(empleado, horaSol, "ALTA", "");
			}else {
				String motivo = notNull(req, "motivo");
				EnviarCorreo enviarCorreo = new EnviarCorreo(
						"ALTA", 
						empleado.getEmail(),
						supervisor.getEmail(),
						supervisor.getApePaterno()+" "+
						supervisor.getApeMaterno()+", "+
						supervisor.getNombre()
						);
				enviarCorreo.enviar();
				setSolicitud(empleado, horaSol, "MODIFICACION", motivo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String notNull(HttpServletRequest request, String id){
		String value = request.getParameter(id)!=null?request.getParameter(id).toString():"";
		return value;
	}
	
	
	public void setSolicitud(BeanEmpleado empleado, BeanHorario horaSol, String tipo, String motivo){
		BeanSolicitud beanSolicitud = beanSolicitud = new BeanSolicitud(empleado.getEmpleadoID()+ObtenFechas.fechaHoy().replaceAll("/","")+horaSol.getHorarioId() , empleado.getEmpleadoID(), empleado.getApePaterno()+" "+empleado.getApeMaterno()+", "+empleado.getNombre(),
			empleado.getSupervisorID(), ObtenFechas.fechaHoyDate(), null, empleado.getHorarioID(), horaSol.getHorarioId(), 
			empleado.getHoraEntrada(), horaSol.getEntrada(), empleado.getHoraSalida(), horaSol.getSalida(), tipo,
			motivo, "P");
		if(InsertarRegistro.solicitud(beanSolicitud)){
			Horario.setFecha(empleado, "fechaSolicitud");
			if(tipo.equals("MODIFICACION")){
				Horario.deleteDato("fechaAplicacion", empleado.getEmpleadoID());
			}			
		}
		
	}
	
	public static List<BeanSolicitud> getList(String supervisor, String decision){
		List<Object> l = QueryTables.getList("solicitud", "supervisorID", supervisor, "decision", decision);
		List<BeanSolicitud> list = new ArrayList<>();
		if(l!=null){
			for(Object bean: l){
				BeanSolicitud beanSolicitud = (BeanSolicitud)bean;
				list.add(beanSolicitud);
			}
		}
		return list;
		
	}
	
	public static void modifySolicitud(BeanSolicitud beanSolicitud){
		UpdateTables.setDato("solicitud", "decision", beanSolicitud.getDecision(), "motivo",
				beanSolicitud.getMotivo(), "fechaRespuesta", beanSolicitud.getFechaRespuesta()+"",
				"solicitudID", beanSolicitud.getSolicitudID());
	}
	
	public static BeanSolicitud getSolicitud(String solicitudID){
		return (BeanSolicitud)QueryTables.getDato("solicitud", "solicitudID", solicitudID);
	}
		
	//BETY 
	public static List<BeanSolicitud> rechazaSolicitudes(String table, String field, String value, String field2, String value2){
		try{						
	        List<Object> l = QueryTables.getList(table, field, value, field2, value2);
	        List<BeanSolicitud> list = new ArrayList<>();
	        if(l != null){
		        for(Object bean: l){
		        	BeanSolicitud beanSolicitud = (BeanSolicitud)bean;
		            list.add(beanSolicitud);
		        }
				return list;
	        }	 
			else
			{			
				return null;
			}
		}			
		catch(Exception e)
		{
			//Con mensaje de Error				
			e.printStackTrace();
			System.out.println("--> Error en MySQL" + e.getMessage().toString());
			return null;			
		}
	}
	
	//BETY
	public static void modifySolicitud(String solicitudId, String decision, String motivo, Date fecha){		
		String result;	
		java.sql.Date fechaRespuesta = new java.sql.Date(fecha.getTime());
		try{									  
			UpdateTables.setDato("solicitud", "decision", decision, "motivo", motivo, "fechaRespuesta", fechaRespuesta, "solicitudID", solicitudId);					
		}catch(Exception e){
			//Con mensaje de Error				
			e.printStackTrace();
			System.out.println("--> Error en MySQL" + e.getMessage().toString());
		}
	}
		
	//BETY REPORTES
	 public static List<BeanSolicitud> get(String kind, String field, String value, String field2, Date desde, Date hasta){
	  //List<BeanSolicitud> result = null;
	  
	  java.sql.Date Ddesde = new java.sql.Date(desde.getTime());
	  java.sql.Date Dhasta = new java.sql.Date(hasta.getTime());
	  
	  List<BeanSolicitud> list = new ArrayList<>();
	  try {
	   System.out.println("REPORTES TEST BETY: "+ kind + " // " +  value );
	   
	         List<Object> l = QueryTables.getListReportes(kind, field, value, field2, Ddesde, Dhasta);
	           
	         for(Object bean: l){
	          BeanSolicitud beanSolicitud = (BeanSolicitud)bean;
	             list.add(beanSolicitud);
	         }
	   return list;
	  } catch (Exception e) {
	   e.printStackTrace();
	   return null;
	  }
	 }
}

