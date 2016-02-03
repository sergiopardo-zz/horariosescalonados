package com.gonet.horariosescalonados.classes;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanEmpleado;

import com.gonet.horariosescalonados.util.EnviarCorreo;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.gonet.horariosescalonados.classes.Empleado;

public class MantenimientoHorarios extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Horario horario = new Horario();
	//HorasDisponibles horasDisponibles = new HorasDisponibles();
	
	public MantenimientoHorarios(){
		//Constructor
	}
	
	public MantenimientoHorarios(HttpServletRequest request, HttpServletResponse response){
	
	}
		
/*
	*/
	
	//BETY
	public List<BeanHorario> cargaInicial(){
		List<BeanHorario> beans = horario.listHorarios();
		return beans;		
	}
	
	
	//BETY
	public void eliminaHorario(HttpServletRequest req, HttpServletResponse resp) throws Exception{		
		String key = req.getParameter("horarioKey").toString();
		
		if(horario.eliminaHorario(key)){
			System.out.println("Horario Eliminado Correctamente MySQL");			
			eliminaBloquea("ELIMINADO", key, req); 
		}else{
			System.out.println("Horario No Existente");
		}
	}	
	
	//BETY
	public void eliminaBloquea(String tipo, String key, HttpServletRequest req) throws Exception{
		//Obtenemos los datos del Empleado firmado y el horario Seleccionado				
		String idUserTemp ="";
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		List<BeanSolicitud> result = Solicitud.rechazaSolicitudes("solicitud", "horaSel", key, "decision", "P");
		
		if (result == null)	{
			System.out.println("No hay solicitudes relacionadas a este horario");
		}else{
			String RespuestaActualizaSolicitud, RespuestaBorrarFechaSolicitud;		
			EnviarCorreo enviarCorreo;
			for(BeanSolicitud e: result){				
				String keyBoss = e.getSupervisorID().toString();
				String idSolic = e.getSolicitudID().toString();
				String horaKey = e.getHoraAct().toString();
				String idUser = e.getEmpleadoID().toString();
				try {						
					BeanEmpleado solicitante = Empleado.getEmpleado(idUser);					
					DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaRespuesta = sdf.parse(ObtenFechas.fechaHoy());									
					//Rezachamos solicitud MySQL				
					Solicitud.modifySolicitud(idSolic, "R", "HORARIO "+tipo, fechaRespuesta);
					//Borramos fecha de Solicitd para que pueda volver a generar una nueva Solicitud MySQL
					Horario.deleteFechaSolic(idUser);
					//Enviamos correo 
					enviarCorreo = new EnviarCorreo(
							tipo, 
							empleado.getEmail().toString(),
							solicitante.getEmail().toString(),
							solicitante.getApePaterno().toString()+" "+
							solicitante.getApeMaterno().toString()+", "+
							solicitante.getNombre());
					enviarCorreo.enviar();											
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
			}

		}
		
		
	}
	
	//BETY
	public String bloqueaHorario(HttpServletRequest req) throws Exception{
		String key = req.getParameter("horarioKey").toString();
		String RespuestaBloqueaHorario = "";
		
		System.out.println(this.toString() +"--Bloquea Horario MySQL ***: "+key);
		
		RespuestaBloqueaHorario =  horario.enabledHorario(key, 0);
		System.out.println("***-->RespuestaBloqueaHorario: " + RespuestaBloqueaHorario);
		
		eliminaBloquea("BLOQUEADO", key, req);		
		return RespuestaBloqueaHorario;
	}
	
	//BETY
	public String desbloqueaHorario(HttpServletRequest req){
		String key = req.getParameter("horarioKey").toString();
		String RespuestaDesbloqueaHorario;
		System.out.println(this.toString() +"--Desbloquea HorarioMySQL: "+key);
		
		RespuestaDesbloqueaHorario =  horario.enabledHorario(key, 1);
		System.out.println("***-->RespuestaDesbloqueaHorario: " + RespuestaDesbloqueaHorario);
		
		//return horario.enabledHorario(key, "true");
		return RespuestaDesbloqueaHorario;		
	}


	//BETY
	public String agregaHorarioMySQL(String entrada, String salida){
		System.out.println("--> Agrega Nuevo Horarios");
		return horario.agregaHorario(entrada, salida);
	}	

		
}

