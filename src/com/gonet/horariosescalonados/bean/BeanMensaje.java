package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanMensaje{
	private String mensajeClave;
	private String texto;
	
	public BeanMensaje() {
		super();
	}

	public BeanMensaje(String mensajeClave, String texto) {
		super();
		this.mensajeClave = mensajeClave;
		this.texto = texto;
	}
	
	public BeanMensaje(ResultSet resultSet) throws SQLException {
		this.mensajeClave = resultSet.getString("mensajeClave");
		this.texto = resultSet.getString("texto");
	}

	public String getMensajeClave() {
		return mensajeClave;
	}

	public void setMensajeClave(String mensajeClave) {
		this.mensajeClave = mensajeClave;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "BeanMensaje ["
				+ "mensajeClave=" + mensajeClave 
				+ ", texto=" + texto
				+ "]";
	}
}
