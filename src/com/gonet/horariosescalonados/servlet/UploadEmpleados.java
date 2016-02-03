package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gonet.horariosescalonados.classes.CargaMasiva;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class UploadEmpleados extends HttpServlet{
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		RequestDispatcher dispatcher;
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");		
		if(blobKeys == null && blobKeys.isEmpty()){
			resp.sendRedirect("/");
		}else{
			CargaMasiva cargaMasiva = new CargaMasiva();
			String html = cargaMasiva.cargaArchivo(blobKeys, resp);			
			String fileName = "logEmpleados_"+ObtenFechas.fechaHoy()+".txt";
			if (!fileName.endsWith(".txt")) {
				fileName = fileName + ".txt";
			}
			req.setAttribute("sTexto", html);
			req.setAttribute("sNombre", fileName);
			dispatcher = getServletContext().getRequestDispatcher("/downLoad.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
 