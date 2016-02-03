package com.gonet.horariosescalonados.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.connector.Connector;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Mensaje {

	public Mensaje() {
		super();
	}
	
	public void fillMensajes(){
		List<String> lista = new ArrayList<String>();
		lista.add("HEMOS REGISTRADO TU SOLICITUD, LA CUAL SE HA ENVIADO A TU JEFE DIRECTO PARA SER AUTORIZADA.");
		lista.add("HEMOS REGISTRADO TU SOLICITUD DE CAMBIO DE HORARIO, LA CUAL SE HA ENVIADO A TU JEFE DIRECTO PARA SER AUTORIZADA.");
		lista.add("SU JORNADA LABORAL SERA DE EHH:MM A SHH:MM hrs.");
		lista.add("EL PROCESO SE HA REALIZADO CORRECTAMENTE.");
		lista.add("EL HORARIO DE EHH:MM A SHH:MM hrs. SE MANTENDRA BLOQUEADO PARA NUEVAS SOLICITUDES.");
		lista.add("¿CONFIRMA QUE EL HORARIO DE EHH:MM A SHH:MM hrs. SERA ELIMINADO?");
		lista.add("EL HORARIO DE EHH:MM A SHH:MM hrs SE HA ELIMINADO SATISFACTORIAMENTE.");
		lista.add("EL HORARIO DE EHH:MM A SHH:MM hrs. SE MANTENDRA DESBLOQUEADO PARA NUEVAS SOLICITUDES.");
		lista.add("EL REPORTE SE HA GENERADO CORRECTAMENTE.");
		
		for(int i = 0; i<lista.size(); i++){
			System.out.println("--> Nuevo Mensaje: "+"MSG0"+(i+1));
			BeanMensaje beanMensaje = new BeanMensaje();
			beanMensaje.setMensajeClave("MSG0"+(i+1));
			beanMensaje.setTexto(lista.get(i));
			InsertarRegistro.mensaje(beanMensaje);						
		}			
	}
}
