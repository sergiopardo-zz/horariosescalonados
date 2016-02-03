package com.gonet.horariosescalonados.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObtenKeyHora {
	public static String getNumberHora(String value){
		Pattern pattern = Pattern.compile("(\\d+):(\\d+)");
		Matcher matcher = pattern.matcher(value);
		while(matcher.find()){
			value = matcher.replaceAll("$1$2");
		}
		return value;
	}
	
	public static String getKeyHora(String value){
		Pattern pattern = Pattern.compile("(\\d+):(\\d+)-(\\d+):(\\d+)");
		Matcher matcher = pattern.matcher(value);
		while(matcher.find()){
			value = matcher.replaceAll("$1$2$3$4");
		}
		return value;
	}
	
	public static String getHora(String value){
		Pattern pattern = Pattern.compile("(\\d+):(\\d+):(\\d+) (\\s+)");
		Matcher matcher = pattern.matcher(value);
		while(matcher.find()){
			value = matcher.replaceAll("$1$2$3");
		}
		return value;
	}
	
}
