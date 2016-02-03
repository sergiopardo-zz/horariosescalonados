package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelEmpleadosServlet extends HttpServlet{
	private String encoding = "ISO-8859-1";
	public static String ENCABEZADO = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'> <html> <head><style type='text/css'> "
		    +".tblCandidato {border: 0px solid #999; border-collapse: separate; width: 100%;}"
		    +".tblCandidato tr th {border-radius:4px; -moz-border-radius: 4px; -webkit-border-radius : 4px; height: 22px;" 
		    +"font-size: 12px; color: #fff; background-color: #069; text-align: center; vertical-align: middle;"
		    +"font-weight: bold; padding: 0px 0px 0px 0px;}"
			+".tblCandidato tr td {height: 22px; font-size: 12px; background-color: #FFF;" 
		    +"text-align: center; vertical-align: middle;}"
		    +"</style> </head> <body>"
		    +"<table width='80%' cellspacing='0' cellpadding='0' class='tblCandidato' bordercolor='#CCCCCC' border='1'>";
	
	public static String EMPLEADOS = "<tbody>"
    		+ "<tr>"
    		+ "<td>M99991</td>"
    		+ "<td>M99999</td>"    		
    		+ "<td>REFORMA</td>"
    		+ "<td>E</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>LUIS MANEL</td>"
    		+ "<td>correo.empleado.cntractor@gonet.us</td>"
    		+ "<td>S/A</td>"
    		+ "<td>33331</td>"
    		+ "<td>JUAREZ CABALLITO</td>"
    		+ "<td>RED COMERCIAL</td>"
    		+ "</tr>"
    		+ "<tr>"
    		+ "<td>M99992</td>"
    		+ "<td>M99999</td>"    		
    		+ "<td>POLANCO</td>"
    		+ "<td>E</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>LUIS ANTONIO</td>"
    		+ "<td>correo.empleado.cntractor@gonet.us</td>"
    		+ "<td>S/A</td>"
    		+ "<td>33332</td>"
    		+ "<td>JUAREZ CABALLITO</td>"
    		+ "<td>RED COMERCIAL</td>"
    		+ "</tr>"
    		+ "<tr>"
    		+ "<td>M99993</td>"
    		+ "<td>M99999</td>"    		
    		+ "<td>REFORMA</td>"
    		+ "<td>E</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>JOSE LUIS</td>"
    		+ "<td>correo.empleado.cntractor@gonet.us</td>"
    		+ "<td>S/A</td>"
    		+ "<td>33333</td>"
    		+ "<td>JUAREZ CABALLITO</td>"
    		+ "<td>RED COMERCIAL</td>"
    		+ "</tr>"
    		+ "<tr>"
    		+ "<td>M99999</td>"
    		+ "<td>M99998</td>"    		
    		+ "<td>POLANCO</td>"
    		+ "<td>S</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>GARCIA</td>"
    		+ "<td>LUISA</td>"
    		+ "<td>correo.empleado.cntractor@gonet.us</td>"
    		+ "<td>S/A</td>"
    		+ "<td>33334</td>"
    		+ "<td>JUAREZ CABALLITO</td>"
    		+ "<td>RED COMERCIAL</td>"
    		+ "</tr>"
    		+ "<tr>"
    		+ "<td colspan='12'>Nota: El archivo no debe contener campos vacios</td>"
    		+ "</tr>"
    		
    		+ "</tbody>";
	
	public void  doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String html = "";
		html += ENCABEZADO;
		html += EMPLEADOS;
		String fileName="Ejemplo_Empleados";
		
		if (!fileName.endsWith(".xls")) {
			fileName = fileName + ".xls";
		}
		resp.setContentType("application/vnd.ms-excel");// magic is here
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		resp.setContentLength(html.length());
		
		resp.getWriter().println(html);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		doPost(req, resp);
	}
}
