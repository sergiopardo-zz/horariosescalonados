package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.classes.MantenimientoHorarios;
import com.gonet.horariosescalonados.classes.MantenimientoMensajes;
//import com.gonet.horariosescalonados.datastore.HorasDisponibles;


public class CargaHorariosServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		System.out.println("-----------------------> Ingresando a Ventana \"Mantenimiento de Horario\"  <-----------------------");
		RequestDispatcher dispatcher;
		MantenimientoHorarios mantenimientoHorarios = new MantenimientoHorarios();
		List<BeanHorario> listHorarios = new ArrayList<>();
		try{
			req.setAttribute("message5", MantenimientoMensajes.getMessage("MSG05"));
			req.setAttribute("message6", MantenimientoMensajes.getMessage("MSG06"));
			req.setAttribute("message7", MantenimientoMensajes.getMessage("MSG07"));
			req.setAttribute("message8", MantenimientoMensajes.getMessage("MSG08"));
		
			String action = req.getParameter("HAccion")!=null?req.getParameter("HAccion").toString():"sin accion";			

			if(action.equals("eliminar")){			
				mantenimientoHorarios.eliminaHorario(req, resp);
			}else if(action.equals("agrega")){
				String entrada = req.getParameter("HEntrada").toString();
				String salida = req.getParameter("HSalida").toString();
				mantenimientoHorarios.agregaHorarioMySQL(entrada, salida);
			}else if(action.equals("bloqueado")){
				mantenimientoHorarios.bloqueaHorario(req);
			}else if(action.equals("desbloqueado")){
				mantenimientoHorarios.desbloqueaHorario(req);	
			}
			req.setAttribute("entity",listHorarios);	
		}catch(Exception e){
			e.printStackTrace();
		}		
		listHorarios = mantenimientoHorarios.cargaInicial();
		req.setAttribute("entity",listHorarios);
		dispatcher = getServletContext().getRequestDispatcher("/horarioUpdated.jsp");
		dispatcher.forward(req, resp);	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
}
