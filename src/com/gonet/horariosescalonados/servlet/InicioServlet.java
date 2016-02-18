package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.classes.CargaInicial;
import com.gonet.horariosescalonados.classes.Empleado;
import com.gonet.horariosescalonados.classes.Horario;
import com.gonet.horariosescalonados.factory.DatanucleusPersistenceManager;
import com.gonet.horariosescalonados.persistence.TipoArchivo;
import com.gonet.horariosescalonados.service.*;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@SuppressWarnings("serial")
public class InicioServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		RequestDispatcher dispatcher;	
		
		try {
			CargaInicial.carga();
			DatanucleusPersistenceManager.getInstance();	
			
			//ServletContext context = getServletContext();
			
			//InputStream inputStreamArchivo = context.getResourceAsStream("/WEB-INF/externos20151028.txt");

			//CargaAutomatica carga = new CargaAutomatica(inputStreamArchivo, TipoArchivo.ZEIT);
			   
			//carga.CargaAutomaticaArchivo();
				
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			HttpSession session = req.getSession();
//			session.setAttribute("emailSession", "noe.morales.contractor@bbva.com");
//			if (user == null) {
//				Entity empleado = Empleado.getEmpleadoMail("noe.morales.contractor@bbva.com");
			session.setAttribute("emailSession", user);
			System.out.println("usuario sesion "+ user);
			
			
			
			
			if (user != null) {
				BeanEmpleado empleado = Empleado.getEmpleado(req);
				
				
				if (empleado.getEmpleadoID() == null)
				{
					/*OBTENER LLAVE DEL CERTIFICADO*/
					KeyStore keystore = KeyStore.getInstance("PKCS12");
					ServletContext context1 = getServletContext();
					InputStream keyFileStream  = context1.getResourceAsStream("/WEB-INF/bbva-gapis-horarios-escalonados.p12");
					//InputStream keyFileStream  = context1.getResourceAsStream("/WEB-INF/dev-bbva-gapis-horarios-escalonados.p12");
					if (keyFileStream == null){throw new Exception("No se encuentra archivo");}
					keystore.load(keyFileStream, "notasecret".toCharArray());
		            PrivateKey key = (PrivateKey)keystore.getKey("privatekey", "notasecret".toCharArray());
					/*FIN DE OBTENER LLAVE DEL CERTIFICADO*/
					
					List<String> listaCorreos = Arrays.asList(user.getEmail());

					BusquedaUsuarioNuevo busqueda = new BusquedaUsuarioNuevo(listaCorreos, key);
					
					busqueda.BuscarUsuarioInterno();
					
					empleado = Empleado.getEmpleado(req);
					System.out.println("empleadonuevo" + empleado);
				}
								
				String nombre = empleado.getNombre();
				String apePaterno =empleado.getApePaterno();
				String apeMaterno= empleado.getApeMaterno();
				String idUser = empleado.getEmpleadoID();
				String tipoEmp = empleado.getTipoEmpleado();
				String ubicacion = empleado.getEdificio();			
				String HorarioActual= empleado.getHorarioID();
				BeanEmpleadoHorario fechas = Horario.getHorarioEmpleado(idUser); 
				String fechAplicacion = fechas.getFechaAplicacion();
				String fechSolicitud = fechas.getFechaSolicitud();
				
				req.setAttribute("tipo_empleado", tipoEmp); // A,E o S
				req.setAttribute("edificio", ubicacion); // "POLANCO" o "REFORMA"
				req.setAttribute("fecha_solicitud", fechSolicitud);//si tiene fecha de solicitud se bloque la opcion solicitud
				req.setAttribute("fecha_aplicacion", fechAplicacion);//si tiene fecha de aplicacicon se bloquean todas las opciones
				req.setAttribute("hora_actual", HorarioActual);//si el horario actual es diferente de "00000000" se bloquea solicitud
				req.setAttribute("usuario",idUser);
				
				session.setAttribute("nombre"+"apePaterno"+"apeMaterno", nombre + apePaterno + apeMaterno);
				String strNombreCompleto = nombre + " " + apePaterno + " " + apeMaterno;
				session.setAttribute("nombreCompleto", strNombreCompleto);
				session.setAttribute("usuario", idUser);
				session.setAttribute("tipo_empleado", tipoEmp);
				session.setAttribute("edificio", ubicacion);
				session.setAttribute("fecha_solicitud", fechSolicitud);
				session.setAttribute("fecha_aplicacion", fechAplicacion);
				session.setAttribute("hora_actual", HorarioActual);

				
				
				System.out.println("--> nombre: "+nombre+" --usuario: "+idUser+" --TipoEmpleado: "+tipoEmp+" --Edificio: "+
				ubicacion+" --FechaSolic: "+fechSolicitud+" --FechaAplicacion: "+fechAplicacion+ " --HorarioActual: "+HorarioActual);			
				dispatcher = getServletContext().getRequestDispatcher("/menuv1.jsp");

			} else {
				
				
				System.out.println("--> usuario inexistente");				
				dispatcher = getServletContext().getRequestDispatcher("/bloqueo.html");
			}			
		} catch (Exception e) {
			System.out.println("--> Bloqueo por usuario no autoriado");
			e.printStackTrace();
			dispatcher = getServletContext().getRequestDispatcher("/bloqueo.html");
		}
		dispatcher.forward(req, resp);			
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		doPost(req, resp);
	}
}
