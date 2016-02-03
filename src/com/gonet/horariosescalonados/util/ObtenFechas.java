package com.gonet.horariosescalonados.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObtenFechas {
	public static String fechaHoy() {
		Calendar cfecha = new GregorianCalendar();
		int anio = cfecha.get(Calendar.YEAR);
        int mes = cfecha.get(Calendar.MONTH);
        String sMes = (mes+1)<10 ? "0"+(mes+1) : ""+(mes+1);
        int dia = cfecha.get(Calendar.DAY_OF_MONTH);
        String sDia = dia<10 ? "0"+dia : ""+dia;
        
        //System.out.println("---->fecha Hoy = "+sDia+"/"+sMes+"/"+anio);
        
		return sDia+"/"+sMes+"/"+anio;
	}
	
	public static Date fechaHoyDate() {
		try {
			Date date = new Date();		
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String d = df.format(date);
			Date newDate = df.parse(d);
	        //System.out.println("---->fecha Hoy = "+sDia+"/"+sMes+"/"+anio);
			//System.out.println(newDate);
	        return newDate;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static String removeSlash(String date){
		Pattern pattern = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");
		Matcher matcher = pattern.matcher(date);
		while(matcher.find()){
			date = matcher.replaceAll("$1$2$3");
		}
		return date;
	}
	
	public static String removeSlash(Date date){
		Pattern pattern = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String newDate = df.format(date);
		Matcher matcher = pattern.matcher(newDate);
		while(matcher.find()){
			newDate = matcher.replaceAll("$1$2$3");
		}
		return newDate;
	}
}
