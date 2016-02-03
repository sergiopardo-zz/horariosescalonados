package com.gonet.horariosescalonados.classes;

import javax.servlet.http.HttpServletRequest;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanHorario;
//import com.gonet.horariosescalonados.datastore.Empleado;
//import com.gonet.horariosescalonados.datastore.Supervisor;

public class MantenimientoEmpleado {
	public static void setEmpleado(HttpServletRequest req){
		
		System.out.println("Entra a MAntenimiento de Empleados TEST BETY");
		
		String idUser = req.getParameter("hiddenIdUser")!=null?req.getParameter("hiddenIdUser").toString():"";
		System.out.println("--> setEmpleado: "+idUser);
		String nombre = req.getParameter("hiddenNombre")!=null?req.getParameter("hiddenNombre").toString():"";
		String apePat = req.getParameter("hiddenPaterno")!=null?req.getParameter("hiddenPaterno").toString():"";
		String apeMat = req.getParameter("hiddenMaterno")!=null?req.getParameter("hiddenMaterno").toString():"";
		String tipoEmp = req.getParameter("hiddenTipoEmpleado")!=null?req.getParameter("hiddenTipoEmpleado").toString():"";
		String ubicacion = req.getParameter("hiddenUbicacion")!=null?req.getParameter("hiddenUbicacion").toString():"";
		String email = req.getParameter("hiddenCorreo")!=null?req.getParameter("hiddenCorreo").toString():"";
		String idJefe = req.getParameter("hiddenIdJefe")!=null?req.getParameter("hiddenIdJefe").toString():"";
		String registro = req.getParameter("hiddenRegistro")!=null?req.getParameter("hiddenRegistro").toString():"";
		String nomCR = req.getParameter("hiddenNombreCR")!=null?req.getParameter("hiddenNombreCR").toString():"";
		String dga = req.getParameter("hiddenDGA")!=null?req.getParameter("hiddenDGA").toString():"";
		
		
		System.out.println(idUser);
		System.out.println(nombre);
		System.out.println(apePat);
		System.out.println(apeMat);
		System.out.println(tipoEmp);
		System.out.println(ubicacion);
		System.out.println(email);
		System.out.println(idJefe);
		System.out.println(registro);
		System.out.println(nomCR);
		System.out.println(dga);		
		
		//BETY
		//BeanEmpleado beanEmpleado = new BeanEmpleado(idUser, apePat, apeMat, nombre, email, ubicacion, tipoEmp, registro, nomCR, dga, idjefe, "00000000", "00:00", "00:00");				
		//Empleado.setEmpleado(beanEmpleado);
		String horario = "00000000";
				
		BeanHorario beanHorario = Horario.getHorario("horario", "horarioId", horario);
		BeanEmpleado beanEmpleado = new BeanEmpleado(idUser, apePat, apeMat, nombre, email.toLowerCase(), ubicacion, tipoEmp, registro, 
				nomCR, dga, idJefe, beanHorario.getHorarioId(), beanHorario.getEntrada(), beanHorario.getSalida());
		if(!Empleado.existe(idUser)){			
			Empleado.setEmpleado(beanEmpleado);
			Empleado.setEmpleadoHorario(beanEmpleado);
		}
		
		if(Empleado.existe(idJefe)){
			System.out.println("Supervisor ["+idJefe+"] registrado, se complementan datos.");			
			Empleado.setSupervisor("empleado", "tipoEmpleado", "S", "empleadoID", idJefe);
		}		
		
	}
}
