
package com.gonet.horariosescalonados.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;

public class Connector {
	public static Connection conn;
	public static Connection Conexion(){
		String url="";
		try {			
			if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production){
				//Load the class that provides the new "jdbc:google:mysql://" prefix
				Class.forName("com.mysql.jdbc.GoogleDriver");
				//url = "jdbc:google:mysql://au-bbva-bancomerpmt-pmo:au-bbva-bancomerpmt?user=root?";
				url = "jdbc:google:mysql://prueba1towa:horariosescalonados?user=root?";
			}else{
				//Local MySQL instance to use during development
				//Class.forName("com.mysql.jdbc.Driver");
				//url = "jdbc:mysql://127.0.0.1:8888/horariosescalonados?user=root";	
				//Alternatively, connect to a Google CLoud SQL instance using:
				//url ="jdbc:mysql://127.0.0.1:3306/horariosescalonadosv2?user=root";
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://173.194.230.213:3306/horariosescalonadosv2";
			
			}			
			return DriverManager.getConnection(url, "rogelio", "mlgore");
			//return DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	public static Connection getConexion() throws SQLException{
		conn = Conexion();
		return conn;
	}
}