package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable (catalog = "horariosescalonadosv2", table = "empleadohorario")
public class BeanEmpleadoHorario{
	
	@PrimaryKey
	@Persistent
	@Column(name="empleadoID")
	private String empleadoID;
	
	@Persistent
	@Column(name="fechaAplicacion")
	private String fechaAplicacion;
	
	@Persistent
	@Column(name="fechaSolicitud")
	private String fechaSolicitud;
	
	public BeanEmpleadoHorario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeanEmpleadoHorario(String empleadoID,
			String fechaAplicacion, String fechaSolicitud) {
		super();		
		this.empleadoID = empleadoID;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public BeanEmpleadoHorario(ResultSet resultSet) throws SQLException{
		this.empleadoID = resultSet.getString("empleadoID");
		this.fechaAplicacion = resultSet.getString("fechaAplicacion");
		this.fechaSolicitud = resultSet.getString("fechaSolicitud");

	}

	public String getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(String empleadoID) {
		this.empleadoID = empleadoID;
	}

	public String getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	
	public BeanEmpleadoHorario(BeanEmpleado empleado)
	{
		this.empleadoID = empleado.getEmpleadoID();
	}

	@Override
	public String toString() {
		return "BeanEmpleadoHorario ["
				+ "empleadoID=" + empleadoID 
				+ ", fechaAplicacion="+ fechaAplicacion 
				+ ", fechaSolicitud=" + fechaSolicitud
				+ "]";
	}	
}
