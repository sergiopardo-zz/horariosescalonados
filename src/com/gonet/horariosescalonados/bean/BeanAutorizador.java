package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BeanAutorizador {
	
	private String autorizadorID;
	
	private String nombre;
	
	private String apePaterno;
	
	private String apeMaterno;
	
	public BeanAutorizador(){

	}
	
	public BeanAutorizador(String autorizadorID, String nombre,
			String apePaterno, String apeMaterno){
		super();
		this.autorizadorID = autorizadorID;
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno= apeMaterno;
	}
	
	public BeanAutorizador(ResultSet resultSet) throws SQLException{
		this.autorizadorID = resultSet.getString("autorizadorID");
		this.nombre = resultSet.getString("nombre");
		this.apePaterno= resultSet.getString("apePaterno");
		this.apeMaterno = resultSet.getString("apeMaterno");		
	}
	
	
	public void setautorizadorID(String autorizadorID){
		this.autorizadorID = autorizadorID;
	}
	
	public String getautorizadorID(){
		return autorizadorID;
	}
	
	public void setnombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getnombre(){
		return nombre;
	}
	
	public void setapePaterno(String apePaterno){
		this.apePaterno = apePaterno;
	}
	
	public String getapePaterno(){
		return apePaterno;
	}
	
	public void setapeMaterno (String apeMaterno){
		this.apeMaterno = apeMaterno;
	}
	
	public String getapeMaterno(){
		return apeMaterno;
	}

}
