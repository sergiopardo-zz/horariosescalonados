package com.gonet.horariosescalonados.bean;

import com.gonet.horariosescalonados.interfaces.Archivo;

public class BeanArchivoPerfilConsulta implements Archivo
{
private String usuario;

private int tipoUsuario;


public BeanArchivoPerfilConsulta()
{
	
}

public BeanArchivoPerfilConsulta (String[] registros)
{
	this.usuario = registros[0];
	this.tipoUsuario = usuario.toUpperCase().startsWith("X")? 2 : 1;
}


public int getTipoUsuario() 
{
	return tipoUsuario;
}

public void setTipoUsuario(int tipoUsuario) 
{
	this.tipoUsuario = tipoUsuario;
}

public String getUsuario() 
{
	return usuario;
}

public void setUsuario(String usuario) 
{
	this.usuario = usuario;
}

@Override
public void VerificarArchivo(String[] lineas) {
	// TODO Auto-generated method stub
	
}
}
