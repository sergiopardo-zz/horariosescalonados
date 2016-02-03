package com.gonet.horariosescalonados.servlet;
import java.io.IOException;
import java.util.StringTokenizer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.gonet.horariosescalonados.bean.BeanPerfilConsulta;

import com.gonet.horariosescalonados.classes.Autorizar;
import com.gonet.horariosescalonados.classes.CargaMasiva;

public class PerfilConsultaServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		RequestDispatcher dispatcher;
		Empleado empleado = new Empleado();		
		
		List<BeanEmpleado> empleadoBusqueda = null;
		List<BeanPerfilConsulta> consultaBusqueda = null;
		DataNucleusQuery query = new DataNucleusQuery ();

		try{
			
		String idEmpleado = req.getParameter("inputIdUsuario");
		empleadoBusqueda = Empleado.listEmpleados("empleadoID", idEmpleado);
		consultaBusqueda = query.consulta("IdUsuario", idEmpleado);

		req.setAttribute("consultaBusqueda", consultaBusqueda);
		req.setAttribute("empleadoBusqueda", empleadoBusqueda);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		dispatcher = getServletContext().getRequestDispatcher("/perfilConsulta.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatcher;
		try{
		
		}catch(Exception e){
			e.printStackTrace();
		}
		dispatcher = getServletContext().getRequestDispatcher("/perfilConsulta.jsp");
		dispatcher.forward(req, resp);
	}
}
