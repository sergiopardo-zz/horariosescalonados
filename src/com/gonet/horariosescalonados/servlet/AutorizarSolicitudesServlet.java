package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.bean.BeanSolicitud;
import com.gonet.horariosescalonados.classes.Autorizar;
import com.gonet.horariosescalonados.classes.CargaMasiva;
import com.gonet.horariosescalonados.classes.EmailAutorizar;
import com.gonet.horariosescalonados.classes.Empleado;
import com.gonet.horariosescalonados.classes.MantenimientoMensajes;
import com.gonet.horariosescalonados.classes.Mensaje;
import com.gonet.horariosescalonados.classes.Solicitud;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;


@SuppressWarnings("serial")
public class AutorizarSolicitudesServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("-----------------------> Ingresando a Ventana \"Autorizar Solicitudes\"  <-----------------------");
		RequestDispatcher dispatcher;
		
		try {
			HttpSession session = req.getSession();
			BeanEmpleado empleado = Empleado.getEmpleado(req);
			Iterable<BeanSolicitud> ieListaSolicitudes = Solicitud.getList(empleado.getEmpleadoID(), "P");
			req.setAttribute("ListaSolicitudes", ieListaSolicitudes);
			session.setAttribute("ListaSolicitudes", ieListaSolicitudes);
			BeanMensaje sMensaje= MantenimientoMensajes.getMessage("MSG04");
			req.setAttribute("message4", sMensaje);
		} catch (Exception e) {
			System.out.println(e);
		}		
		dispatcher = getServletContext().getRequestDispatcher("/autorizar3.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("-----------------------> Ingresando a Servlet \"Autorizar Solicitudes\"  <-----------------------");
		RequestDispatcher dispatcher;
		try{
			/*String listSolicitudesInformacion = req.getParameter("hidMapSolicitudes");
			String strMotivoDesicion = req.getParameter("hidMapMotivo");

			
			String strMotivoDesicionLimpia = strMotivoDesicion.substring(1,strMotivoDesicion.length()-1 );
			String stringListRespuestas = listSolicitudesInformacion.substring(1,listSolicitudesInformacion.length()-1 );
			
			
			String[] arrSolicitudes = stringListRespuestas.split(",");
			String[] arrMotivos = strMotivoDesicionLimpia.split(",");
			
			
			TreeMap<String, String[]> mapaDeRepuestaSolicitudes = new TreeMap<String, String[]>();
			
			for(int x = 0;x<arrSolicitudes.length;x++){
				
				String solicitud=arrSolicitudes[x].substring(0,arrSolicitudes[x].indexOf(':')).replace("\"", "");
				String estatus = arrSolicitudes[x].substring(arrSolicitudes[x].indexOf(':')+1,arrSolicitudes[x].length()).replace("\"", "");
				String motivo = arrMotivos[x].substring(arrSolicitudes[x].indexOf(':')+1,arrMotivos[x].length()).replace("\"", "");
	
				String[] respuestaSolicitud = new String[2];
				respuestaSolicitud[0]= estatus;
				respuestaSolicitud[1]= motivo;
			
				mapaDeRepuestaSolicitudes.put(solicitud,respuestaSolicitud );
			}
			*/
			
			
			String sAutorizaciones = req.getParameter("cadena_autorizaciones")==null ? "" : (String)req.getParameter("cadena_autorizaciones");
			StringTokenizer stkAutorizaciones = new StringTokenizer(sAutorizaciones, "|");
			Autorizar.setRespuesta(stkAutorizaciones, req);
			/*EmailAutorizar.setRespuestaeEmail(mapaDeRepuestaSolicitudes, req);
			DataNucleusQuery query = new DataNucleusQuery();
			query.updateSolicitud(mapaDeRepuestaSolicitudes);
			*/
		}catch(Exception e){
			e.printStackTrace();
		}
		dispatcher = getServletContext().getRequestDispatcher("/MainMenu");
		dispatcher.forward(req, resp);
	}
}
