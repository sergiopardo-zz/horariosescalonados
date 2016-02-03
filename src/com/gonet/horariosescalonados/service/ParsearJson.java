package com.gonet.horariosescalonados.service;

import com.gonet.horariosescalonados.bean.BeanUsuarioDirectorio;
import com.gonet.horariosescalonados.util.UsuarioDirectorioDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParsearJson {

	public BeanUsuarioDirectorio ConvertirAUsuario (String strJson)
	{	
		BeanUsuarioDirectorio empleado = null;
		try {
			Gson gson = 
					new GsonBuilder()
					.registerTypeAdapter(BeanUsuarioDirectorio.class, new UsuarioDirectorioDeserializer())
					.create();

			empleado = new BeanUsuarioDirectorio();

			empleado = gson.fromJson(strJson, BeanUsuarioDirectorio.class);

			empleado.toString();

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return empleado;
	}
}
