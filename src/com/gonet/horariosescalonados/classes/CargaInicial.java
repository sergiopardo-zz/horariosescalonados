package com.gonet.horariosescalonados.classes;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoExterno;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;

public class CargaInicial {
	public static void carga(){
		mensajes();
		horarios();
		creaAdmin();
		creaExterno();
		
	}
	
	public static void mensajes(){
		Mensaje mensaje = new Mensaje();
		mensaje.fillMensajes();	
	}
	
	public static void creaAdmin(){
		BeanEmpleado beanEmpleado = new BeanEmpleado("XM05287", "MORALES", "MORENO", "NOE", "noe.morales.contractor@bbva.com", "REFORMA", "SS", "12345", "CR", "DGA", "MB21090", "00000000", "06:30", "15:30");
		InsertarRegistro.empleado(beanEmpleado);
		BeanEmpleadoHorario beanEmpleadoHorario = new BeanEmpleadoHorario("XM05287", "", "");
		InsertarRegistro.empleadoHorario(beanEmpleadoHorario);
	}
	
	public static void creaExterno(){
		BeanEmpleadoExterno beanEmpleado = new BeanEmpleadoExterno("XM0001", "000001","PARDO", "DEL RIO", "SERGIO", "sistema","sistemas","DBA", "08:30","M99999","PEDRO SANCHEZ MONTES","TOWA","HHEE","VIGENTE","SI","REFORMA","sergio@bbva.com");
		InsertarRegistro.empleadoExterno(beanEmpleado);
	}
			
	public static void horarios(){
		Horario creaHorarios = new Horario();
		creaHorarios.fillList();
	}
}
