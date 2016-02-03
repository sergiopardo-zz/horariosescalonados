package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.classes.Empleado;
import com.gonet.horariosescalonados.classes.Horario;
import com.gonet.horariosescalonados.classes.MantenimientoEmpleado;

public class MainMenuServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		RequestDispatcher dispatcher;
		try{		
			System.out.println("-----------------------> Ingresando a Menu\"  <-----------------------");
		
		if(req.getParameter("hiddenOpcionReporte")!=null){			
			MantenimientoEmpleado.setEmpleado(req);
			
			
			
			//BeanEmpleado beanEmpleado = new BeanEmpleado("XM05287", "MORALES", "MORENO", "NOE", "noe.morales.contractor@bbva.com", "REFORMA", "SS", "12345", "CR", "DGA", "MB21090", "00000000", "00:00", "00:00");
			//InsertarRegistro.empleado(beanEmpleado);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		dispatcher = getServletContext().getRequestDispatcher("/inicioaplicacion");
		dispatcher.forward(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		doPost(req, resp);
	}
}
