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

public class SolicitudServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String ruta = "";
		try{
			System.out.println("-----------------------> Ingresando a Ventana \"Solicitud\"  <-----------------------");
			Horario horarios = new Horario();
			List<BeanHorario> listHorarios = horarios.listHorarios();
			req.setAttribute("Horarios", listHorarios);
			req.setAttribute("message", MantenimientoMensajes.getMessage("MSG01"));
			BeanEmpleado empleado = Empleado.getEmpleado(req);
			BeanEmpleadoHorario fechas = Horario.getHorarioEmpleado(empleado.getEmpleadoID());			
			BeanEmpleado supervisor = Empleado.getEmpleado(empleado.getSupervisorID());
			req.setAttribute("idAutorizador", supervisor.getEmpleadoID());
			req.setAttribute("nomAutorizador", supervisor.getApePaterno()+" "+supervisor.getApeMaterno()+", "+supervisor.getNombre());
			
			if(fechas.getFechaSolicitud()!=null){
				ruta = "/proceso.jsp";
			}else{
				ruta = "/solicitud.jsp";
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		dispatcher = getServletContext().getRequestDispatcher(ruta);
		dispatcher.forward(req, resp);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
