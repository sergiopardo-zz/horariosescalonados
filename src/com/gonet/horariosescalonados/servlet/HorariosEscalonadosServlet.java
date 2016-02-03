package com.gonet.horariosescalonados.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gonet.horariosescalonados.bean.BeanDocumento;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class HorariosEscalonadosServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();			
			QueryTables query = new QueryTables();
			BeanDocumento beanDocumento = new BeanDocumento();
			if(query.blobDocs()!=null){
				query.blobDocs().getKeyDocument();
				BlobKey blobKey = new BlobKey(query.blobDocs().getKeyDocument());
				System.out.println("Key del documento download: ["+blobKey.getKeyString()+"]");
				blobstoreService.serve(blobKey, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
}

