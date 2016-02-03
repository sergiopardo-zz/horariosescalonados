package com.gonet.horariosescalonados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gonet.horariosescalonados.connector.Connector;

public class DropTables {
	public void cumplimiento(){
		System.out.println("Eliminando tabla Cumplimiento...");
		try{
			Connection conn = Connector.getConexion(); 
			try {
				String statement = "DROP TABLE hoariosescalonadosv2.cumplimiento";
				PreparedStatement stmt = conn.prepareStatement(statement);
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 0) {
			    	System.out.println("Success!!");
			    } else if (success == 1) {
			    	System.out.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}	
	
	public void empleado(){
		System.out.println("Eliminando tabla Empleado...");
		try{
			Connection conn = Connector.getConexion();
			try {				
				String statement = "DROP TABLE hoariosescalonadosv2.empleado";
				PreparedStatement stmt = conn.prepareStatement(statement);
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 0) {
			    	System.out.println("Success!!");
			    } else if (success == 1) {
			    	System.out.println("Failure! Please try again!");
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
