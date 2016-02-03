package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.classes.Empleado;
import com.gonet.horariosescalonados.classes.Horario;
import com.gonet.horariosescalonados.classes.MantenimientoMensajes;
import com.gonet.horariosescalonados.classes.Solicitud;


public class CambioHorarioServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher;
		String ruta="";
		try{		
			System.out.println("-----------------------> Ingresando a Ventana \"Cambio\"  <-----------------------");
			Horario horarios = new Horario();
			List<BeanHorario> listHorarios = horarios.listHorarios();
			req.setAttribute("Horarios", listHorarios);
			req.setAttribute("message", MantenimientoMensajes.getMessage("MSG01"));
			BeanEmpleado empleado = Empleado.getEmpleado(req);
			BeanEmpleadoHorario fechas = Horario.getHorarioEmpleado(empleado.getEmpleadoID());			
			BeanEmpleado supervisor = Empleado.getEmpleado(empleado.getSupervisorID());
			req.setAttribute("idAutorizador", supervisor.getEmpleadoID());
			req.setAttribute("HorarioActual", empleado.getHorarioID());
			req.setAttribute("nomAutorizador", supervisor.getApePaterno()+" "+supervisor.getApeMaterno()+", "+supervisor.getNombre());
		
			/*Cambiar por implementacion del cambio*/
			req.setAttribute("message", MantenimientoMensajes.getMessage("MSG02"));
			if((fechas.getFechaAplicacion()==null) && (fechas.getFechaSolicitud()!=null)){
				ruta = "/proceso.jsp";
			}else{
				ruta = "/cambio.jsp";
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		dispatcher = getServletContext().getRequestDispatcher(ruta);
		dispatcher.forward(req, resp);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

}