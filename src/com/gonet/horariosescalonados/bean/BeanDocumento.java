package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanDocumento {
	public static String keyDocument;
	
	public BeanDocumento() {
		super();
	}
	
	public BeanDocumento(String keyDocument) {
		super();
		this.keyDocument = keyDocument;
	}
	
	public BeanDocumento(ResultSet resultSet) throws SQLException{
		this.keyDocument = resultSet.getString("blobClave");
	}

	public String getKeyDocument() {
		return keyDocument;
	}

	public void setKeyDocument(String keyDocument) {
		this.keyDocument = keyDocument;
	}	
}
