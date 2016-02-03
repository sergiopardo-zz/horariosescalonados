package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gonet.horariosescalonados.bean.BeanDocumento;
import com.gonet.horariosescalonados.classes.CargaMasiva;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class CargaDocumentoMesServlet extends HttpServlet{
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		RequestDispatcher dispatcher;
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("myFile");		
		if(blobKeys == null && blobKeys.isEmpty()){
			resp.sendRedirect("/");
		}else{
			BeanDocumento beanDocumento = new BeanDocumento(blobKeys.get(0).getKeyString());
			InsertarRegistro insertar = new InsertarRegistro();
			insertar.blobDocument(beanDocumento);
			System.out.println("Key del documento upload: ["+blobKeys.get(0).getKeyString()+"]");
			//BeanDocumento.keyDocument = blobKeys.get(0).getKeyString();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		RequestDispatcher dispatcher;
		dispatcher = getServletContext().getRequestDispatcher("/cargaDoc.jsp");
		dispatcher.forward(req, resp);
	}
}
