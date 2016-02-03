package com.gonet.horariosescalonados.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.util.EnviarCorreo;
import com.gonet.horariosescalonados.util.ObtenFechas;

public class Autorizar {
	
	public static void setRespuesta(StringTokenizer strAutorizaciones, HttpServletRequest req) throws Exception{
		//Obtenemos los datos del Empleado firmado y el horario Seleccionado
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		EnviarCorreo enviarCorreo = new EnviarCorreo();
		String decisionStr ="";
		while(strAutorizaciones.hasMoreTokens()) {
			String [] retval = strAutorizaciones.nextToken().split("_");
			String idSolic = retval[0];
			String idUser = retval[1];
			String idNewHora = retval[2];
			String decision = retval[3];
			String motivo = retval[4];
			//System.out.println("idSolic: "+idSolic+" idUser: "+idUser+" idNewHora: "+idNewHora+" decision: "+decision+" motivo: "+motivo);
			try {				
				//Obtenemos los datos del empleado				
				BeanEmpleado solicitante = Empleado.getEmpleado(idUser);
				BeanHorario horaSol = Horario.getHorario("horario", "horarioId", idNewHora);
				if(decision.equals("A")){
					Horario.setHoraEmpleado(solicitante, horaSol);					
					Horario.setFecha(solicitante, "fechaAplicacion");
					//Generamos correo de aviso de solicitud APROBADA
					System.out.println("Correo("+
							"'APROBADA', "+
							empleado.getEmail()+", "+
							solicitante.getEmail()+", "+
							solicitante.getApePaterno()+" "+
							solicitante.getApeMaterno()+", "+
							solicitante.getNombre()+", "+
							horaSol
							); 
					enviarCorreo = new EnviarCorreo(
							"APROBADA",
							empleado.getEmail(),
							solicitante.getEmail(),
							solicitante.getApePaterno()+" "+
							solicitante.getApeMaterno()+", "+
							solicitante.getNombre(),
							horaSol
							);				
					 enviarCorreo.enviar();
					 //Sumamos y Restamos uno a el numero de empleados en e horario seleccionado
					 Horario horarioSetOne = new Horario();
					 horarioSetOne.setOneToHorario(idNewHora, true);
					 horarioSetOne.setOneToHorario(solicitante.getHorarioID(), false);
					 decisionStr = "A";
				}
				if(decision.equals("R")){
					Horario.deleteDato("fechaSolicitud", solicitante.getEmpleadoID());
					//Generamos correo de aviso de solicitud RECHAZADA
					System.out.println("Correo("+
							"'RECHAZADA', "+
							empleado.getEmail()+", "+
							solicitante.getEmail()+", "+
							solicitante.getApePaterno()+" "+
							solicitante.getApeMaterno()+", "+
							solicitante.getNombre()+", "+
							motivo
							); 
					enviarCorreo = new EnviarCorreo(
							"RECHAZADA", 							
							empleado.getEmail(),
							solicitante.getEmail(),
							solicitante.getApePaterno()+" "+
							solicitante.getApeMaterno()+", "+
							solicitante.getNombre(),
							motivo
							);
					enviarCorreo.enviar();
					decisionStr = "R";
				}
				//modifySolicitud(Key key, String decision, String motivo, String fecha)		
				
				BeanSolicitud beanSolicitud = Solicitud.getSolicitud(idSolic);
				beanSolicitud.setDecision(decisionStr);
				beanSolicitud.setFechaRespuesta(new java.sql.Date(ObtenFechas.fechaHoyDate().getTime()));
				Solicitud.modifySolicitud(beanSolicitud);
								
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
			
		}
	}
}
