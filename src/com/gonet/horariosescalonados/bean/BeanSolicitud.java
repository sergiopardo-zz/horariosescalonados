package com.gonet.horariosescalonados.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BeanSolicitud implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String solicitudID;
	private String empleadoID;
	private String nombre;
	private String supervisorID;
	private Date fechaAlta;
	private Date fechaRespuesta;
	private String horaAct;
	private String horaSel;
	private String entradaAct;
	private String entradaSol;
	private String salidaAct;
	private String salidaSol;
	private String tipo;
	private String motivo;
	private String decision;
	
	public BeanSolicitud(){}
	
	public BeanSolicitud(String solicitudID, String empleadoID, String nombre, String supervisorID,
			Date fechaAlta, Date fechaRespuesta, String horaAct,
			String horaSel, String entradaAct, String entradaSol,
			String salidaAct, String salidaSol, String tipo, String motivo,
			String decision) {
		super();
		this.solicitudID = solicitudID;
		this.empleadoID = empleadoID;
		this.nombre = nombre;
		this.supervisorID = supervisorID;
		this.fechaAlta = fechaAlta;
		this.fechaRespuesta = fechaRespuesta;
		this.horaAct = horaAct;
		this.horaSel = horaSel;
		this.entradaAct = entradaAct;
		this.entradaSol = entradaSol;
		this.salidaAct = salidaAct;
		this.salidaSol = salidaSol;
		this.tipo = tipo;
		this.motivo = motivo;
		this.decision = decision;
	}
	
	public BeanSolicitud(ResultSet resultSet) throws SQLException {
		super();
		this.solicitudID = resultSet.getString("solicitudID");
		this.empleadoID = resultSet.getString("empleadoID");
		this.nombre = resultSet.getString("nombre");
		this.supervisorID = resultSet.getString("supervisorID");
		this.fechaAlta = resultSet.getDate("fechaAlta");
		this.fechaRespuesta = resultSet.getDate("fechaRespuesta");
		this.horaAct = resultSet.getString("horaAct");
		this.horaSel = resultSet.getString("horaSel");
		this.entradaAct = resultSet.getString("entradaAct");
		this.entradaSol = resultSet.getString("entradaSol");
		this.salidaAct = resultSet.getString("salidaAct");
		this.salidaSol = resultSet.getString("salidaSol");
		this.tipo = resultSet.getString("tipo");
		this.motivo = resultSet.getString("motivo");
		this.decision = resultSet.getString("decision");
	}
	
	public String getSolicitudID() {
		return solicitudID;
	}
	
	public void setSolicitudId(String solicitudID) {
		this.solicitudID = solicitudID;
	}

	public String getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(String empleadoID) {
		this.empleadoID = empleadoID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public String getHoraAct() {
		return horaAct;
	}

	public void setHoraAct(String horaAct) {
		this.horaAct = horaAct;
	}

	public String getHoraSel() {
		return horaSel;
	}

	public void setHoraSel(String horaSel) {
		this.horaSel = horaSel;
	}

	public String getEntradaAct() {
		return entradaAct;
	}

	public void setEntradaAct(String entradaAct) {
		this.entradaAct = entradaAct;
	}

	public String getEntradaSol() {
		return entradaSol;
	}

	public void setEntradaSol(String entradaSol) {
		this.entradaSol = entradaSol;
	}

	public String getSalidaAct() {
		return salidaAct;
	}

	public void setSalidaAct(String salidaAct) {
		this.salidaAct = salidaAct;
	}

	public String getSalidaSol() {
		return salidaSol;
	}

	public void setSalidaSol(String salidaSol) {
		this.salidaSol = salidaSol;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	@Override
	public String toString() {
		return "BeanSolicitud ["
				+ "solicitudID=" + solicitudID
				+ ", empleadoID=" + empleadoID 
				+ ", nombre=" + nombre
				+ ", supervisorID=" + supervisorID 
				+ ", fechaAlta=" + fechaAlta
				+ ", fechaRespuesta=" + fechaRespuesta 
				+ ", horaAct=" + horaAct
				+ ", horaSel=" + horaSel 
				+ ", entradaAct=" + entradaAct
				+ ", entradaSol=" + entradaSol 
				+ ", salidaAct=" + salidaAct
				+ ", salidaSol=" + salidaSol 
				+ ", tipo=" + tipo 
				+ ", motivo=" + motivo 
				+ ", decision=" + decision 
				+ "]";
	}
	
	
	
	
}
