package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.classes.MantenimientoMensajes;
import com.gonet.horariosescalonados.bean.BeanMensaje;
//import com.gonet.horariosescalonados.datastore.HorasDisponibles;

public class CargaMensajesServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		System.out.println("-----------------------> Ingresando a Ventana \"Mantenimiento de Mensajes\"  <-----------------------");
		RequestDispatcher dispatcher;
		
		MantenimientoMensajes mantenimientoMensajes = new MantenimientoMensajes();		
		List<BeanMensaje> listMensajes = mantenimientoMensajes.cargaInicial();		
				
		String action = req.getParameter("HAccion")!=null?req.getParameter("HAccion").toString():"sin accion";
		
		if(action.equals("actualizar")){			
			mantenimientoMensajes.actualizaMensaje(req);
			listMensajes = mantenimientoMensajes.cargaInicial();
		}	
		req.setAttribute("entity", listMensajes);
		
		dispatcher = getServletContext().getRequestDispatcher("/mensajes.jsp");
		dispatcher.forward(req, resp);	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
}
