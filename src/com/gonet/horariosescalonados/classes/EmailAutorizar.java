package com.gonet.horariosescalonados.classes;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.util.EnviarCorreo;
import com.gonet.horariosescalonados.util.ObtenFechas;

public class EmailAutorizar {

	public static void setRespuestaeEmail(TreeMap<String, String[]> solicitudes , HttpServletRequest req) throws Exception{
		//Obtenemos los datos del Empleado firmado y el horario Seleccionado
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		EnviarCorreo enviarCorreo = new EnviarCorreo();
		String decisionStr ="";
		for (Map.Entry<String, String[]> entry :  solicitudes.entrySet()){
			String idSolic = entry.getKey();
			String[] arrValues = entry.getValue();
			String decision = arrValues[0];
			String motivo = arrValues[1];
			
			BeanSolicitud beanSolicitud = Solicitud.getSolicitud(idSolic);
			String idNewHora = beanSolicitud.getHoraSel();
			String idUser = beanSolicitud.getEmpleadoID();
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
				
				
				beanSolicitud.setDecision(decisionStr);
				beanSolicitud.setFechaRespuesta(new java.sql.Date(ObtenFechas.fechaHoyDate().getTime()));
				Solicitud.modifySolicitud(beanSolicitud);
								
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
			
		}
	}
}
