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

public class SupervisorSeleccionadoServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		System.out.println("-----------------------> Regresamos a la Ventana \"Solicitud\"  <-----------------------");
		RequestDispatcher dispatcher = null;
		String opcion = notNull(req, "hiddenFrom");
		String cancelar = notNull(req, "hiddenCancelar");
	
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		BeanEmpleado supervisor = Empleado.getEmpleado(empleado.getSupervisorID());
		String horarioActual = empleado.getHorarioID();
		req.setAttribute("idAutorizador", supervisor.getEmpleadoID());
		req.setAttribute("nomAutorizador", supervisor.getApePaterno()+" "+supervisor.getApeMaterno()+", "+supervisor.getNombre());
		req.setAttribute("HorarioActual", horarioActual);
		
		try {
			Horario horarios = new Horario();
			List<BeanHorario> listHorarios = horarios.listHorarios();
			req.setAttribute("Horarios", listHorarios);
			if(cancelar.equals("true")){
				Solicitud.actualizaDatosCancelar(req, resp);
			}else{
				Solicitud.actualizaDatos(req, resp);
			}
			if(opcion.equals("fromSolicitud")){
				System.out.println("--> Solicitud - Supervisor Seleccionado");
				req.setAttribute("message", MantenimientoMensajes.getMessage("MSG01"));
				dispatcher = getServletContext().getRequestDispatcher("/solicitud.jsp");
			}else{
				System.out.println("--> Cambio - Supervisor Seleccionado");
				req.setAttribute("message", MantenimientoMensajes.getMessage("MSG02"));
				dispatcher = getServletContext().getRequestDispatcher("/cambio.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispatcher.forward(req, resp);
	}	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
	
	public String notNull(HttpServletRequest request, String id){
		String value = request.getParameter(id)!=null?request.getParameter(id).toString():"";
		return value;
	}
}
