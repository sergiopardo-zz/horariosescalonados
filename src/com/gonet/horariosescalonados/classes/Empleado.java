package com.gonet.horariosescalonados.classes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;








import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.dao.UpdateTables;

public class Empleado {
	public static BeanEmpleado getEmpleado(HttpServletRequest req){
		HttpSession session = req.getSession();		
		String email = session.getAttribute("emailSession").toString();
		BeanEmpleado beanEmpleado = (BeanEmpleado)QueryTables.getDato("empleado", "email",email);
		return beanEmpleado;
	}
	
	public static void setEmpleado(BeanEmpleado beanEmpleado){
		InsertarRegistro insert = new InsertarRegistro();
		insert.empleado(beanEmpleado);
	}
		
	public static BeanEmpleado getEmpleado(String value){
		return (BeanEmpleado) QueryTables.getDato("empleado", "empleadoID", value);
	}
	
	public static List<BeanEmpleado> listEmpleados(String field, String value){
		return QueryTables.listEmpleados(field, value);
	}
	
	public static List<BeanEmpleado> listEmpleados(String field, String value, String field2, String value2){
		return QueryTables.listEmpleados(field, value, field2, value2);
	}
	
	public static List<BeanEmpleado> listEmpleados(String field, String value, String field2, String value2, String field3, String value3){
		return QueryTables.listEmpleados(field, value, field2, value2, field3, value3);
	}
	
	public static void setSupervisor(String table, String field, String value, String field2, String value2){
		UpdateTables.setDato(table, field, value, field2, value2);
	}
	
	public static boolean existe(String id){
		boolean result = false;
		BeanEmpleado empleado = (BeanEmpleado)QueryTables.getDato("empleado", "empleadoID", id);
		if(empleado.getEmpleadoID() != null){
			result = true;
		}			
		return result;
	}
	
	public static void setEmpleadoHorario(BeanEmpleado empleado){
		InsertarRegistro insert = new InsertarRegistro();
		BeanEmpleadoHorario empleadoHorario = new BeanEmpleadoHorario(empleado.getEmpleadoID(),"","");
		insert.empleadoHorario(empleadoHorario);
	}
		
}
