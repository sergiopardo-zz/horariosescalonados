package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelHorariosServlet extends HttpServlet{
	private String encoding = "ISO-8859-1";
	public static String ENCABEZADO = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'> <html> <head><style type='text/css'> "
		    +".tblCandidato {border: 0px solid #999; border-collapse: separate; width: 100%;}"
		    +".tblCandidato tr th {border-radius:4px; -moz-border-radius: 4px; -webkit-border-radius : 4px; height: 22px;" 
		    +"font-size: 12px; color: #fff; background-color: #069; text-align: center; vertical-align: middle;"
		    +"font-weight: bold; padding: 0px 0px 0px 0px;}"
			+".tblCandidato tr td {height: 22px; font-size: 12px; background-color: #FFF;" 
		    +"text-align: center; vertical-align: middle;}"
		    +"</style> </head> <body>"
		    +"<table width='40%' cellspacing='0' cellpadding='0' class='tblCandidato' bordercolor='#CCCCCC' border='1'>";
	
	public static String EMPLEADOS = "<tbody>"
    		+ "<tr>"
    		+ "<td style='width:100px;'>M99991</td>"
    		+ "<td style='width:100px;'>8:30</td>"    		
    		+ "<td style='width:100px;'>17:30</td>"
    		+ "</tr>"
    		+ "<tr><td>M99992</td><td>10:30</td><td>19:30</td></tr>"
    		+ "<tr><td>M99993</td><td>11:30</td><td>20:30</td></tr>"
    		+ "<tr><td>M99994</td><td>7:30</td><td>16:30</td></tr>"
    		+ "<tr><td>M99995</td><td>9:30</td><td>18:30</td></tr>"
    		+ "<tr><td>M99996</td><td>8:30</td><td>17:30</td></tr>"
    		+ "<tr><td>M99997</td><td>10:30</td><td>19:30</td></tr>"
    		+ "<tr><td>M99998</td><td>6:30</td><td>15:30</td></tr>"
    		+ "<tr>"
    		+ "<td colspan='3'>Nota: El archivo no debe contener campos vacios</td>"
    		+ "</tr>"    		
    		+ "</tbody>";
	
	public void  doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String html = "";
		html += ENCABEZADO;
		html += EMPLEADOS;
		String fileName="Ejemplo_Horarios";		
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
