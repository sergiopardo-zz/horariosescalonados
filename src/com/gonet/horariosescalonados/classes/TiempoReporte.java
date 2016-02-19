package com.gonet.horariosescalonados.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class TiempoReporte {
	public static String Tiempo(String semana, String f1){
		String a = "";
		int j = 0;
		int entero = 0;
		int año = 0;
		char [] fecha = semana.toCharArray();
		int tamaño = fecha.length -1;
			if(j != 2){
				while(fecha[j]!=' '){
					a = a + fecha[j];
					j++;
				}
				entero = Integer.parseInt(a);
				a = "";
				j++;
			}if(j<=tamaño){
				while(j<=tamaño && fecha[j]!=' '){
					a = a + fecha[j];
					j++;
				}
				año = Integer.parseInt(a);
			}
			entero = entero + 1;
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/"+año+"");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, entero );   
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, año);
		f1 = sdf.format(cal.getTime());
		return f1;
		}
	
	public static String Tiempo2(String desde, String f2){
			String a = "";
			int j = 0;
			String M = "";
			int entero = 0;
			String año = "";
			char [] fecha = desde.toCharArray();
			int tamaño = fecha.length -1;
				if(j != 2){
					while(fecha[j]!='/'){
						a = a + fecha[j];
						j++;
					}
					entero = Integer.parseInt(a);
					a = "";
					j++;
				}if(j != 5){
					while(j<=tamaño && fecha[j]!='/'){
						a = a + fecha[j];
						j++;
					}
					M = a;
					a = "";
					j++;
				}while(j<= tamaño){
					a = a + fecha[j];
					j++;
				}
				año = a ;
				
				entero = entero + 6;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String Dia = Integer.toString(entero);
				f2 = Dia+ '/'+M+'/'+año;
				System.out.println(f2);
				return f2;
			}
	
	public static String Tiempo3 (String mes, String f3){
		String a = "";
		int j = 0;
		int M = 0;
		String meses = "";
		int año = 0;
		char [] fecha = mes.toCharArray();
		int tamaño = fecha.length -1;
			if(j != 2){
				while(fecha[j]!=' '){
					a = a + fecha[j];
					j++;
				}
				meses = a;
				a = "";
				j++;
			}if(j<=tamaño){
				while(j<=tamaño && fecha[j]!=' '){
					a = a + fecha[j];
					j++;
				}
				año = Integer.parseInt(a);
			}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		if(meses.equals("Enero")){
			 M = 1;
		}
		if(meses.equals("Febrero")){
			 M = 2;
		}
		if(meses.equals("Marzo")){
			 M = 3;
		}
		if(meses.equals("Abril")){
			 M = 4;
		}
		if(meses.equals("Mayo")){
			 M = 5;
		}
		if(meses.equals("Junio")){
			 M = 6;
		}
		if(meses.equals("Julio")){
			 M = 7;
		}
		if(meses.equals("Agosto")){
			M = 8;
		}
		if(meses.equals("Septirmbre")){
		    M = 9;
		}
		if(meses.equals("Octubre")){
			M = 10;
		}
		if(meses.equals("Noviembre")){
			 M = 11;
		}
		if(meses.equals("Diciembre")){
			M = 12;
		}
		cal.set(año,M,0);
		int F3 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String dia = Integer.toString(F3);
		if(M == 1||M == 2||M ==3||M ==4||M ==5||M ==6||M ==7
				||M ==8||M ==9){
			String M1 = "0"+Integer.toString(M);
			f3 = dia+ '/'+M1+'/'+año;
		}else{
			String M1 = Integer.toString(M);
			f3 = dia+ '/'+M1+'/'+año;
		}
		System.out.println(f3);
		return f3;
	}
	
	public static String Tiempo4 (String mes, String f3){
		String a = "";
		int j = 0;
		int M = 0;
		String meses = "";
		int año = 0;
		char [] fecha = mes.toCharArray();
		int tamaño = fecha.length -1;
			if(j != 2){
				while(fecha[j]!=' '){
					a = a + fecha[j];
					j++;
				}
				meses = a;
				a = "";
				j++;
			}if(j<=tamaño){
				while(j<=tamaño && fecha[j]!=' '){
					a = a + fecha[j];
					j++;
				}
				año = Integer.parseInt(a);
			}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		if(meses.equals("Enero")){
			 M = 1;
		}
		if(meses.equals("Febrero")){
			 M = 2;
		}
		if(meses.equals("Marzo")){
			 M = 3;
		}
		if(meses.equals("Abril")){
			 M = 4;
		}
		if(meses.equals("Mayo")){
			 M = 5;
		}
		if(meses.equals("Junio")){
			 M = 6;
		}
		if(meses.equals("Julio")){
			 M = 7;
		}
		if(meses.equals("Agosto")){
			M = 8;
		}
		if(meses.equals("Septirmbre")){
		    M = 9;
		}
		if(meses.equals("Octubre")){
			M = 10;
		}
		if(meses.equals("Noviembre")){
			 M = 11;
		}
		if(meses.equals("Diciembre")){
			M = 12;
		}
		cal.set(año,M,0);
		int F3 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//String dia = Integer.toString(F3);
		if(M == 1||M == 2||M ==3||M ==4||M ==5||M ==6||M ==7
				||M ==8||M ==9){
			String M1 = "0"+Integer.toString(M);
			f3 = "01"+ '/'+M1+'/'+año;
		}else{
			String M1 = Integer.toString(M);
			f3 = "01"+ '/'+M1+'/'+año;
		}
		System.out.println(f3);
		return f3;
	}
	
	public static String Tiempo5(String hasta, String f1){
		String a = "";
		int dia = 0;
		int j = 0;
		int M = 0;
		int año = 0;
		char [] fecha = hasta.toCharArray();
		int tamaño = fecha.length -1;
			if(j != 2){
				while(fecha[j]!='/'){
					a = a + fecha[j];
					j++;
				}
				dia=Integer.parseInt(a);
				a = "";
				j++;
			}if(j != 5){
				while(j<=tamaño && fecha[j]!='/'){
					a = a + fecha[j];
					j++;
				}
				M = Integer.parseInt(a);
				a = "";
				j++;
			}while(j<= tamaño){
				a = a + fecha[j];
				j++;
			}
			año = Integer.parseInt(a);
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar cal = new GregorianCalendar();
		Date fecha1 = null;
		try {
			fecha1 = sdf.parse(hasta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(fecha1);
		int entero = cal.get(Calendar.DAY_OF_WEEK);
		if (entero != 2){
			f1 = hasta;
		}else{
		  dia = dia - 2;
		  String D = Integer.toString(dia);
		  String Mes = "0" + Integer.toString (M);
		  String A = Integer.toString(año);
		  f1= D+'/'+Mes+'/'+A;
		}
		
		return f1;
		}

}
