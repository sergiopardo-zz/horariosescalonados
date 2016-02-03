package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.classes.Empleado;
import com.gonet.horariosescalonados.classes.Solicitud;

public class BuscaSupervisoresServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BuscaSupervisoresServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String opcion = notNull(req, "opcion");
		System.out.println("-----------------------> Ingresando a Ventana \"Busqueda de Supervisores\"  <-----------------------");
		BeanEmpleado empleado = Empleado.getEmpleado(req);
		if(opcion.equals("solicitud")){			
			System.out.println("--> Buscar Supervisores - Solicitud");
			req.setAttribute("hiddenSelectedRadio", req.getParameter("hiddenSelectedRadio"));
			req.setAttribute("hiddenIsChecked", req.getParameter("hiddenIsChecked"));
			req.setAttribute("hiddenFrom", "fromSolicitud");
			req.setAttribute("empleadoID", empleado.getEmpleadoID());
		}else if(opcion.equals("cambio")){
			System.out.println("--> Buscar Supervisores - Cambio");
			req.setAttribute("hiddenSelectedRadio", req.getParameter("hiddenSelectedRadio"));
			req.setAttribute("hiddenIsChecked", req.getParameter("hiddenIsChecked"));
			req.setAttribute("empleadoID", empleado.getEmpleadoID());
			req.setAttribute("hiddenFrom", "fromCambio");
//----------------------------------------------------------------------------------------
		}else if(opcion.equals("usuario")){
			System.out.println("--> Busqueda de Usuario");
			System.out.println("--> IdUser: " + req.getParameter("hiddenIdUser"));
			System.out.println("--> NameUser: " + req.getParameter("hiddenNameUser"));
			System.out.println("--> FirstNameUser: " + req.getParameter("hiddenFirstNameUser"));
			System.out.println("--> SecondNameUser: " + req.getParameter("hiddenSecondNameUser"));
			req.setAttribute("empleadoID", empleado.getEmpleadoID());
			
//----------------------------------------------------------------------------------------
		}else{
			Solicitud solicitud = new Solicitud();
			System.out.println("--> Busqueda de Supervisores");
			System.out.println("--> IdUser: " + req.getParameter("hiddenIdUser"));
			System.out.println("--> RegisterUser: " + req.getParameter("hiddenRegisterUser"));
			System.out.println("--> NameUser: " + req.getParameter("hiddenNameUser"));
			System.out.println("--> FirstNameUser: " + req.getParameter("hiddenFirstNameUser"));
			System.out.println("--> SecondNameUser: " + req.getParameter("hiddenSecondNameUser"));
			System.out.println("--> hiddenFrom: " + req.getParameter("hiddenFrom"));
			System.out.println("--> SelectedHorario: " + req.getParameter("hiddenSelectedRadio"));
			System.out.println("--> Checked?: " + req.getParameter("hiddenIsChecked"));
			req.setAttribute("empleadoID", empleado.getEmpleadoID());
			solicitud.getSupervisores(req, resp);
			
		}
		dispatcher = getServletContext().getRequestDispatcher("/SolicitudBuscar.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	public String notNull(HttpServletRequest request, String id){
		String value = request.getParameter(id)!=null?request.getParameter(id).toString():"";
		return value;
	}
}
