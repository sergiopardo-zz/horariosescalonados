package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.util.ObtenFechas;

public class GeneraDocServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String html = notNull(req,"hiddenTexto");
		String fileName = notNull(req,"hiddenNombre");
		resp.setContentType("txt/html");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		resp.setContentLength(html.length());
		resp.getWriter().println(html);
		resp.getWriter().close();		
	}

	public String notNull(HttpServletRequest request, String id){
		String value = request.getParameter(id)!=null?request.getParameter(id).toString():"";
		return value;
	}
}
