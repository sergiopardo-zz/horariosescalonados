package com.gonet.horariosescalonados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.gonet.horariosescalonados.bean.BeanCumplimiento;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanMensaje;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.connector.Connector;

public class DeleteRegistro {
	
	public Boolean horario(String keyhorario){
		boolean flag = false;		
		String result = "";
		try{
			Connection conn = Connector.getConexion();
			try {
				String statement = "DELETE FROM horariosescalonadosv2.horario WHERE horarioID = ?;";
				PreparedStatement stmt = conn.prepareStatement(statement);
			    stmt.setString(1, keyhorario);
			    int success = 2;
			    success = stmt.executeUpdate();
			    if (success == 1) {
			    	System.out.println("Success!!");
			    	result = "Success!!";
			    	flag = true;
			    	return flag;
			    	//return result;
			    } else if (success == 0) {
			    	System.err.println("Failure! Please try again!");			    	
			    	result = "Failure! Please try again!";
			    	return flag;
			    	//return result;
			    }	
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println(e);			
			result = e.toString();
			return flag;
			//return result;
		}
		return flag;
	}

	
}
