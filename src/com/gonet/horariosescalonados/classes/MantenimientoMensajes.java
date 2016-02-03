package com.gonet.horariosescalonados.classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



//import com.gonet.horariosescalonados.datastore.Horario;
//import com.gonet.horariosescalonados.datastore.HorasDisponibles;






import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.dao.UpdateTables;
import com.google.appengine.api.datastore.Entity;

public class MantenimientoMensajes {
	
	private static final long serialVersionUID = 1L;
	Iterable<Entity> entities;
	Iterable<Entity> entities_;
	Iterable<Entity> horasDisp;
	Mensaje mensaje = new Mensaje();
	
	
	public static BeanMensaje getMessage(String key) throws SQLException{
		BeanMensaje beanMensaje = (BeanMensaje) QueryTables.getDato("mensaje", "mensajeClave", key);
		return beanMensaje;
	}
	
	public static List<BeanMensaje> cargaInicial(){
        List<Object> l = QueryTables.getList("mensaje");
        List<BeanMensaje> list = new ArrayList<>();
        if(l!=null){
	        for(Object bean: l){
	        	BeanMensaje beanSolicitud = (BeanMensaje)bean;
	            list.add(beanSolicitud);
	        }
        }
        return list;        
    }
	
	public  void actualizaMensaje(HttpServletRequest req){
		String key = req.getParameter("mensajeKey").toString();
		String texto = req.getParameter("mensajetexto").toString();	
		UpdateTables.setDato("mensaje", "Texto", texto, "mensajeClave", key);			
	}
}
