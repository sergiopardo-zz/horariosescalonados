package com.gonet.horariosescalonados.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fecha {
	
	public Date fechas(){
		SimpleDateFormat fechalec = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		//Date date = c.getTime();
		String fecha = fechalec.format(c.getTime());
		Date fechas = null;
		try {
			fechas = fechalec.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fechas;
	}
	
	public String fechass(){
		SimpleDateFormat fechalec = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		//Date date = c.getTime();
		String fecha = fechalec.format(c.getTime());
		
		return fecha;
	}

}
