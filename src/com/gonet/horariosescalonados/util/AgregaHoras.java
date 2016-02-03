package com.gonet.horariosescalonados.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgregaHoras {
	public static Pattern pattern;
	public static Matcher matcher;
	
	public static String change(String value, String entrada, String salida){
		return changeS(changeE(value, entrada), salida);
	}
	
	public static String changeE(String value, String entrada){
		pattern = Pattern.compile("EHH:MM");
		matcher = pattern.matcher(value);
		while(matcher.find()){
			value = matcher.replaceFirst(entrada);
		}
		return value;
	}
	
	public static String changeS(String value, String entrada){
		pattern = Pattern.compile("SHH:MM");
		matcher = pattern.matcher(value);
		while(matcher.find()){
			value = matcher.replaceFirst(entrada);
		}
		return value;
	}
}
