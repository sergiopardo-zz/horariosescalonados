package com.gonet.horariosescalonados.bean;

import com.gonet.horariosescalonados.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanPerfilConsulta 
{

	private String IdUsuario;

	private Integer TipoConsulta;

	private Integer Estatus;



	public BeanPerfilConsulta()
	{

	}

	public BeanPerfilConsulta(ResultSet resultSet) throws SQLException{
		this.IdUsuario = resultSet.getString("IdUsuario");
		this.TipoConsulta = resultSet.getInt("TipoConsulta");
		//this.Estatus = resultSet.getBoolean("Estatus");
		
	}
	
	public boolean getEstatus() {
		return Estatus == 1 ? true : false ;
	}

	public void setEstatus(Integer Estatus) {
		this.Estatus = Estatus;
	}

	public TipoPerfilConsulta getTipoConsulta() {

		switch (TipoConsulta)
		{
		case 1:
		
			return TipoPerfilConsulta.INTERNOS;

		case 2:

			return TipoPerfilConsulta.EXTERNOS;

		case 3:
		
			return TipoPerfilConsulta.AMBOS;

		default:
			
			return null;		
		}
	}


	public void setTipoConsulta(Integer TipoConsulta) {

		this.TipoConsulta = TipoConsulta;	
	}

	public String getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(String IdUsuario) {
		this.IdUsuario = IdUsuario;
	}

}
