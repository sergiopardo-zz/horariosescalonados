package com.gonet.horariosescalonados.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.gonet.horariosescalonados.persistence.TipoArchivo;
import com.gonet.horariosescalonados.service.CargaAutomatica;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

public class BuscarArchivoZeitServlet extends HttpServlet
{
	 private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
		      .initialRetryDelayMillis(10)
		      .retryMaxAttempts(10)
		      .totalRetryPeriodMillis(15000)
		      .build());
	  private static final int BUFFER_SIZE = 2 * 1024 * 1024;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher;

		ServletContext context = getServletContext();

		InputStream inputStreamArchivo = context.getResourceAsStream("/WEB-INF/CargaZeitStorage.txt");
		
//		String appName = "enteratvdos";
//        
//        GcsFilename fileName = new GcsFilename(appName, "CargaZeitStorage.txt");
//       
//		GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(fileName, 0, BUFFER_SIZE);
//		
//		InputStream input =Channels.newInputStream(readChannel);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	
		copy(inputStreamArchivo,output);
		
		byte[] bytes = output.toByteArray();
		
		String inputString = new String(bytes,"ISO-8859-1");

		CargaAutomatica carga = new CargaAutomatica(inputString, TipoArchivo.ZEIT);

		carga.CargaAutomaticaArchivo();

		System.out.println("Mapeo Correcto Zeit");
		
		req.setAttribute("resultado", inputString);
		
		dispatcher = getServletContext().getRequestDispatcher("/bloqueo2.jsp");
		
		dispatcher.forward(req, resp);
	}
	 private void copy(InputStream input, OutputStream output) throws IOException {
		    try {
		      byte[] buffer = new byte[BUFFER_SIZE];
		      int bytesRead = input.read(buffer);
		      while (bytesRead != -1) {
		        output.write(buffer, 0, bytesRead);
		        bytesRead = input.read(buffer);
		      }
		    } finally {
		      input.close();
		      output.close();
		    }
		  }
}
