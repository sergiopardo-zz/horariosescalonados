package com.gonet.horariosescalonados.bean;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable (catalog = "horariosescalonadosv2", table = "PerfilConsultaExternos")
public class BeanPerfilConsultaExternos {


	@PrimaryKey
	@Persistent (name = "IdUsuarioConsulta")
	private String IdUsuarioConsulta;

	@Persistent (name = "IdUsuarioReporte")
	private String IdUsuarioReporte;
	
	public BeanPerfilConsultaExternos()
	{

	}
	
	public BeanPerfilConsultaExternos(String idUsuarioConsulta, String idUsuarioReporte) {
		super();
		IdUsuarioConsulta = idUsuarioConsulta;
		IdUsuarioReporte = idUsuarioReporte;
	}
	
	public String getIdUsuarioConsulta() {
		return IdUsuarioConsulta;
	}

	public void setIdUsuarioConsulta(String idUsuarioConsulta) {
		IdUsuarioConsulta = idUsuarioConsulta;
	}

	public String getIdUsuarioReporte() {
		return IdUsuarioReporte;
	}

	public void setIdUsuarioReporte(String idUsuarioReporte) {
		IdUsuarioReporte = idUsuarioReporte;
	}
	
	
}
