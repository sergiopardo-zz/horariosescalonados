package com.gonet.horariosescalonados.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Entity;

public class MyTimeComparator implements Comparator<String>{
	@Override
	public int compare(String s1, String s2){
		int result = s1.compareTo(s2);
		return result;
	}

	public MyTimeComparator(List<Entity> entities){
		Collections.sort(entities ,new Comparator<Entity>(){
	    	@Override
	    	public int compare(Entity s1, Entity s2){
	    		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	    		Date date1;
	    		Date date2;
	    		try{
	    			date1 = format.parse(s1.getProperty("Entrada").toString().split(" ")[0]);
	    			date2 = format.parse(s2.getProperty("Entrada").toString().split(" ")[0]);
	    		}catch(ParseException pe){
	    			throw new IllegalArgumentException("Cuould not parse date",pe);
	    		}
	    		return date1.compareTo(date2);
	    	}
	    });
	}
	
}
