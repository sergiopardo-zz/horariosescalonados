package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.classes.Solicitud;

public class GenerarSolicitudServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("-----------------------> Ingresando a Servlet \"Genera Solcitud\"  <-----------------------");
		RequestDispatcher dispatcher;		
		Solicitud solicitud = new Solicitud();
		try {
			solicitud.generaSolictud(req, resp);
		} catch (Exception e) {
			System.err.println("Usuario No existe");
			e.printStackTrace();
		}																
		dispatcher = getServletContext().getRequestDispatcher("/MainMenu");
		dispatcher.forward(req, resp);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

}
