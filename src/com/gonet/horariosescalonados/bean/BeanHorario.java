package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.*;

@Entity
@Table(name= "horario")
public class BeanHorario {
	
	@Id
	@Column(name="horarioId")
	private String horarioId;
	
	@Column (name="entrada")
	private String entrada;
	
	@Column (name="salida")
	private String salida;
	
	@Column (name="enabled")
	private int enabled;
	
	@Column (name="empleadosActivos")
	private String empleadosActivos;
	
	@Transient 
	protected Object[] jdoDetachedState; 
	
	
	public BeanHorario(){}
	
	public BeanHorario(String horarioId, String entrada, String salida,
			int enabled, String empleadosActivos) {
		super();
		this.horarioId = horarioId;
		this.entrada = entrada;
		this.salida = salida;
		this.enabled = enabled;
		this.empleadosActivos = empleadosActivos;		
	}
	
	public BeanHorario(ResultSet resultSet) throws SQLException{
		this.horarioId = resultSet.getString("horarioId");
		this.entrada = resultSet.getString("entrada");
		this.salida = resultSet.getString("salida");
		this.enabled = resultSet.getInt("enabled");
		this.empleadosActivos = resultSet.getString("empleadosActivos");
	}
	
	public String getHorarioId() {
		return horarioId;
	}
	public void setHorarioId(String horarioId) {
		this.horarioId = horarioId;
	}
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getEmpleadosActivos() {
		return empleadosActivos;
	}
	public void setEmpleadosActivos(String empleadosActivos) {
		this.empleadosActivos = empleadosActivos;
	}
	

	@Override
	public String toString() {
		return "BeanHorario ["
				+ "horarioId=" 			+ horarioId 
				+ ", entrada=" 			+ entrada
				+ ", salida="			+ salida 
				+ ", enabled=" 			+ enabled
				+ ", empleadosActivos=" + empleadosActivos 
				+ "]";
	}	
}
