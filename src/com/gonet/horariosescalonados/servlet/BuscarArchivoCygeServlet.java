package com.gonet.horariosescalonados.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.gonet.horariosescalonados.persistence.TipoArchivo;
import com.gonet.horariosescalonados.service.CargaAutomatica;

import com.google.appengine.tools.cloudstorage.*;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;

import com.google.appengine.api.appidentity.AppIdentityService;

import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;



import java.nio.ByteBuffer;


public class BuscarArchivoCygeServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppIdentityService service;
	
	 private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
		      .initialRetryDelayMillis(10)
		      .retryMaxAttempts(10)
		      .totalRetryPeriodMillis(15000)
		      .build());
	  private static final int BUFFER_SIZE = 2 * 1024 * 1024;
	 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	
	{		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		try 
		{
			
			 
		/*OBTENER LLAVE DEL CERTIFICADO*/
		KeyStore keystore = KeyStore.getInstance("PKCS12");
		ServletContext context1 = getServletContext();
		InputStream keyFileStream  = context1.getResourceAsStream("/WEB-INF/bbva-gapis-horarios-escalonados.p12");

		if (keyFileStream == null){throw new Exception("No se encuentra archivo");}
		keystore.load(keyFileStream, "notasecret".toCharArray());
        PrivateKey key = (PrivateKey)keystore.getKey("privatekey", "notasecret".toCharArray());
		
		/*FIN DE OBTENER LLAVE DEL CERTIFICADO*/
        //byte[] bytes = IOUtils.toByteArray(keyFileStream);
       
        /*GcsFilename fileName = new GcsFilename(appName, "foo");
        GcsOutputChannel outputChannel =
                gcsService.createOrReplace(fileName, GcsFileOptions.getDefaultInstance());
            @SuppressWarnings("resource")
            ObjectOutputStream oout =
                new ObjectOutputStream(Channels.newOutputStream(outputChannel));
            oout.writeObject(bytes);
            oout.close();
        */
        
        
        
		InputStream inputStreamArchivo = context.getResourceAsStream("/WEB-INF/cygeprueba.txt");
		
		//System.out.println(inputStreamArchivo);
/*
		String appName = "enteratvdos";
        
        GcsFilename fileName = new GcsFilename(appName, "CargaPrueba.txt");
		
       
		GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(fileName, 0, BUFFER_SIZE);
		
		
		InputStream input =Channels.newInputStream(readChannel);
		*/
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		copy(inputStreamArchivo,output);
		
		byte[] bytes = output.toByteArray();
	    
		String inputString = new String(bytes,"ISO-8859-1");
		
		CargaAutomatica carga = new CargaAutomatica(inputString, TipoArchivo.CYGE,key);
		
		carga.CargaAutomaticaArchivo();
		
		System.out.println("Mapeo Correcto Cyge");
		
		//req.setAttribute("resultado", inputString);
		
	    dispatcher = getServletContext().getRequestDispatcher("/ConsumeSRestWEBServlet");
		
		dispatcher.forward(req, resp);
		
		
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
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
