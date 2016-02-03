package com.gonet.horariosescalonados.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.connector.Connector;
import com.gonet.horariosescalonados.dao.DeleteRegistro;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.dao.UpdateTables;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.gonet.horariosescalonados.util.ObtenKeyHora;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.TransactionOptions;

public class Horario {
	
	public Horario(){
		//Constructor
	}
	
	public void fillList(){		
		BeanHorario beanHorario = new BeanHorario();
		List<String> listEntrada = new ArrayList<String>();
		List<String> listSalida = new ArrayList<String>();
		String entradaKey = "";
		String salidaKey = "";
		listEntrada.add("00:00");
		listSalida.add("00:00");
		for(int i=6,j=15,x=0; x<5; i++,j++,x++){
			listEntrada.add(i+":30");
			listSalida.add(j+":30");
		}
		
		for(int x=0; x<listEntrada.size(); x++){		
			System.out.println("--> Nuevo horario: "+(listEntrada.get(x)+""+listSalida.get(x)).replaceAll(":", ""));
			beanHorario.setHorarioId((listEntrada.get(x)+""+listSalida.get(x)).replaceAll(":", ""));
			beanHorario.setEntrada(listEntrada.get(x));
			beanHorario.setSalida(listSalida.get(x));
			beanHorario.setEnabled(1);
			beanHorario.setEmpleadosActivos("0");
			InsertarRegistro.horario(beanHorario);
		}		
	}
	
	public List<BeanHorario> listHorarios(){
		try{
			List<BeanHorario> listaHorarios = QueryTables.listHorarios();		
			return listaHorarios;
		}catch(Exception e){
			e.printStackTrace();
			return null;			
		}
	}
	
	public Iterable<Entity> ordenaHorarios(Iterable<Entity> horarios){
		System.out.println("--> ordenaHorarios()");
		return null;		
	}
	
	public static BeanEmpleadoHorario getHorarioEmpleado(String key) throws Exception{
		BeanEmpleadoHorario beanEmpleadoHorario = (BeanEmpleadoHorario) QueryTables.getDato("empleadohorario", "empleadoID", key);		
		return beanEmpleadoHorario;
	}
	
	public static BeanHorario getHorario(String table, String field, String value){
		return (BeanHorario)QueryTables.getDato(table, field, value);
	}
	
	public String enabledHorario(String key, String value){
		return null;
	}
	
	public Boolean eliminaHorario(String key){
		boolean flag = false;	
		try{
			DeleteRegistro eliminarhorario = new DeleteRegistro();
			flag = eliminarhorario.horario(key);				
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return null;			
		}			
	}
	
	public static void deleteHoraEmpleado(Key keyAnt){
		System.out.println("--> deleteHoraEmpleado(): "+keyAnt);
	}

	public static void setHoraEmpleado(BeanEmpleado empleado, BeanHorario horaSol){
		UpdateTables.setDato("empleado", "HorarioID", horaSol.getHorarioId(), "horaEntrada", horaSol.getEntrada(), "horaSalida", horaSol.getSalida(), "empleadoID", empleado.getEmpleadoID());
	}
	
	public void setOneToHorario(String key, boolean suma){
		if(suma){
			System.out.println("--> Agregamos un empleado al horario seleccionado: "+key);
			BeanHorario beanHorario = (BeanHorario)QueryTables.getDato("horario", "horarioId", key);
			UpdateTables.setDato("horario", "empleadosActivos", (Integer.parseInt(beanHorario.getEmpleadosActivos())+1)+"", "horarioId", key);
		}
		if(!suma){
			System.out.println("--> Quitamos un empleado al horario seleccionado: "+key);
			BeanHorario beanHorario = (BeanHorario)QueryTables.getDato("horario", "horarioId", key);
			UpdateTables.setDato("horario", "empleadosActivos", (Integer.parseInt(beanHorario.getEmpleadosActivos())-1)+"", "horarioId", key);
		}
	}
	
	public static void addAndDelete(String newKey, String oldKey){
		System.out.println("--> addAndDelete(): (+1) "+newKey+" (-1) "+oldKey);
	}
	
	public static void setFecha(BeanEmpleado empleado, String field){
		UpdateTables.setDato("empleadohorario", field, new java.sql.Date(ObtenFechas.fechaHoyDate().getTime()), "empleadoID", empleado.getEmpleadoID());
	}
	
	public static void setFecha(BeanEmpleado empleado, String field, java.sql.Date value, String field2, java.sql.Date value2){
		UpdateTables.setDato("empleadohorario", field, value, 
				field2, value, "empleadoID", empleado.getEmpleadoID());
	}
	//
	public static void deleteDato(String field, String id){
		UpdateTables.deleteDato(field, id);
	}
	
	//BETY
	public String enabledHorario(String key, int value){
		System.out.println(this.toString()+"-- setHorarioMySQL(): "+value);		
		return UpdateTables.BloqueaHorario(key,value);
	}

	//BETY
	public String agregaHorario(String entrada, String salida){
		String key = ObtenKeyHora.getNumberHora(entrada)+""+ObtenKeyHora.getNumberHora(salida);
		BeanHorario beanHorario = getHorario("horario", "horarioID", key);
		if(beanHorario.getHorarioId() != null){			
			return "Horario Existente";	
		}
		beanHorario = new BeanHorario(key, entrada, salida, 1, "0");
		InsertarRegistro.horario(beanHorario);		
		return "Horario Agregado Correctamente";		
	}
	
	public static void deleteFechaSolic(String empleadoId) {
		try {		
			 UpdateTables.ModificarFechaSolicitud(empleadoId);
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static boolean existe(String id){
		boolean result = false;
		BeanHorario horario = (BeanHorario)QueryTables.getDato("horario", "horarioId", id);
		if(horario.getHorarioId() != null){
			result = true;
		}			
		return result;
	}
	
}


