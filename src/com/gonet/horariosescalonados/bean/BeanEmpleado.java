package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable (catalog = "horariosescalonadosv2", table = "empleado")
public class BeanEmpleado{

	@PrimaryKey
	@Persistent
	private String empleadoID;
	@Persistent
	private String apePaterno;
	@Persistent
	private String apeMaterno;
	@Persistent
	private String nombre;
	@Persistent
	private String email;
	@Persistent
	private String edificio;
	@Persistent
	private String tipoEmpleado;
	@Persistent
	private String registro;
	@Persistent
	private String nombreCR;
	@Persistent
	private String dga;	
	@Persistent
	private String supervisorID;
	@Persistent
	private String horarioID;
	@Persistent
	private String horaEntrada;
	@Persistent
	private String horaSalida;
	
	public BeanEmpleado(){}
	
	public BeanEmpleado(String empleadoID, String apePaterno, String apeMaterno,
			String nombre, String email, String edificio, String tipoEmpleado,
			String registro, String nombreCR, String dga, String supervisorID,
			String horarioID, String horaEntrada, String horaSalida) {
		super();
		this.empleadoID = empleadoID;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.nombre = nombre;
		this.email = email;
		this.edificio = edificio;
		this.tipoEmpleado = tipoEmpleado;
		this.registro = registro;
		this.nombreCR = nombreCR;
		this.dga = dga;
		this.supervisorID = supervisorID;
		this.horarioID = horarioID;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}
	
	public BeanEmpleado(ResultSet resultSet) throws SQLException{
		this.empleadoID = resultSet.getString("empleadoID");
		this.apePaterno = resultSet.getString("apePaterno");
		this.apeMaterno = resultSet.getString("apeMaterno");
		this.nombre = resultSet.getString("nombre");
		this.email = resultSet.getString("email");
		this.edificio = resultSet.getString("edificio");
		this.tipoEmpleado = resultSet.getString("tipoEmpleado");
		this.registro = resultSet.getString("registro");
		this.nombreCR = resultSet.getString("nombreCR");
		this.dga = resultSet.getString("dga");
		this.supervisorID = resultSet.getString("supervisorID");
		this.horarioID = resultSet.getString("horarioID");
		this.horaEntrada = resultSet.getString("horaEntrada");
		this.horaSalida = resultSet.getString("horaSalida");
	}
	
	public BeanEmpleado (BeanUsuarioDirectorio usuarioDirectorio)
	{
										//Sustituir por EMPLOYEE ID.
		this.empleadoID = usuarioDirectorio.getUid();
		this.apePaterno = usuarioDirectorio.getApellido1();
		this.apeMaterno = usuarioDirectorio.getApellido2();
		this.dga = usuarioDirectorio.getCodOUNivel10();
		this.nombre = usuarioDirectorio.getGivenName();
		this.edificio = usuarioDirectorio.getDescCentroTrabajo();
		this.email = usuarioDirectorio.getMail();
		this.registro = usuarioDirectorio.getUid();
		this.supervisorID = usuarioDirectorio.getUidJefe();
		this.tipoEmpleado ="E";
		this.horarioID = "00000000";
		this.horaEntrada = "00:00";
		this.horaSalida = "00:00";
	}

	public void setEmpleadoID(String empleadoID) {
		this.empleadoID = empleadoID;
	}
	
	public String getEmpleadoID() {
		return empleadoID;
	}
		
	public String getApePaterno() {
		return apePaterno;
	}
	
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}
	
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}
	
	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	
	public String getEdificio() {
		return edificio;
	}
	
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}
	
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	
	public String getRegistro() {
		return registro;
	}
	
	public void setNombreCR(String nombreCR) {
		this.nombreCR = nombreCR;
	}
	
	public String getNombreCR() {
		return nombreCR;
	}
	
	public void setDga(String dga) {
		this.dga = dga;
	}
	
	public String getDga() {
		return dga;
	}

	public String getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(String horarioID) {
		this.horarioID = horarioID;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	@Override
	public String toString() {
		return "Empleado [empleadoID=" + empleadoID + ", apePaterno="
				+ apePaterno + ", apeMaterno=" + apeMaterno + ", nombre="
				+ nombre + ", email=" + email + ", edificio=" + edificio
				+ ", tipoEmpleado=" + tipoEmpleado + ", registro=" + registro
				+ ", nombreCR=" + nombreCR + ", dga=" + dga + ", supervisorID="
				+ supervisorID + ", horarioID=" + horarioID + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + "]";
	}	
}
