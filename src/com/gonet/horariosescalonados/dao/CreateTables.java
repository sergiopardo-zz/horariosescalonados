package com.gonet.horariosescalonados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.gonet.horariosescalonados.connector.Connector;

public class CreateTables {
	
	public void cumplimiento(){
		System.out.println("Creando tabla Cumplimiento...");
		try{
			Connection conn = Connector.getConexion(); 
			try {
				String statement = "CREATE TABLE hoariosescalonadosv2.cumplimiento ("
						+ "empleadoID VARCHAR(10) NOT NULL,"
						+ "apePaterno VARCHAR(50),"
						+ "apeMaterno VARCHAR(50),"
						+ "nombre VARCHAR(50),"
						+ "nombreCR VARCHAR(100),"
						+ "dga VARCHAR(100),"
						+ "fecha DATE,"
						+ "quincena VARCHAR(10),"
						+ "mes VARCHAR(20),"
						+ "tae VARCHAR(10),"
						+ "entrada VARCHAR(10),"
						+ "tde VARCHAR(10),"
						+ "entradaReal VARCHAR(10),"
						+ "califEntrada VARCHAR(2),"
						+ "tas VARCHAR(10),"
						+ "salida VARCHAR(10),"
						+ "tds VARCHAR(10),"
						+ "salidaReal VARCHAR(10),"
						+ "califSalida VARCHAR(2),"
						+ "jornada VARCHAR(2),"
						+ "total VARCHAR(2),"
						+ "porcentaje VARCHAR(5))";
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
		System.out.println("Creando tabla Empleado...");
		try{
			Connection conn = Connector.getConexion();
			try {				
				String statement = "CREATE TABLE hoariosescalonadosv2.empleado "
						+ "("
						+ "empleadoID VARCHAR(10) NOT NULL,"
						+ "apePaterno VARCHAR(50),"
						+ "apeMaterno VARCHAR(50),"
						+ "nombre VARCHAR(50),"
						+ "email VARCHAR(100),"
						+ "edificio VARCHAR(100),"
						+ "tipoEmpleado VARCHAR(2),"
						+ "registro VARCHAR(10),"
						+ "nombreCR VARCHAR(100),"
						+ "dga VARCHAR(150),"
						+ "horarioID VARCHAR(10),"
						+ "horaEntrada VARCHAR(10),"
						+ "horaSalida VARCHAR(10),"
						+ "supervisorID VARCHAR(10),"
						+ "PRIMARY KEY(empleadoID)"
						+ ");";
				PreparedStatement stmt = conn.prepareStatement(statement);
			    int success = 2;
			    success = stmt.executeUpdate();
			    
			    //statement = "SELECT * FROM empleado;"
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
	
/*	public void Cyge(){
		System.out.println("Creando tabla Cyge...");
		try{
			Connection conn = Connector.getConexion();
			try {
				String statement = "CREATE TABLE hoariosescalonadosv2.Cyge ("
				+ "noCyge VARCHAR(10) NOT NULL,"
				+ "usuario VARCHAR(10),"
				+ "nombre VARCHAR(50),"
				+ "apePaterno VARCHAR(50),"
				+ "apeMaterno VARCHAR(50),"
				+ "dirGeneral VARCHAR(150),"
				+ "dirCorporativa VARCHAR(150),"
				+ "area VARCHAR(150),"
				+ "entOficial VARCHAR(10),"
				+ "autorizadorID VARCHAR(10),"
				+ "utorizador VARCHAR(50),"
				+ "proveedor VARCHAR(150),"
				+ "proyecto VARCHAR(150),"
				+ "estatus VARCHAR(50),"
				+ "espacioFisico CHAR(2),"
				+ "salidaReal VARCHAR(10),"
				+ "edificio VARCHAR(50))";

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
	public void zeit(){
		System.out.println("Creando tabla Zeit...");
		try{
			Connection conn = Connector.getConexion();
			try {
				String statement = "CREATE TABLE hoariosescalonadosv2.ziet ("
				+ "noCyge VARCHAR(10) NOT NULL,"
				+ "nsubcontrata VARCHAR(150),"
				+ "fecha DATE,"
				+ "hora VARCHAR(10),"
				+ "tipoFuncion VARCHAR(50),"
				+ "lector VARCHAR(150),"
				+ "edificio VARCHAR(50),"
				+ "estancia  VARCHAR (10))";

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
	*/
}
