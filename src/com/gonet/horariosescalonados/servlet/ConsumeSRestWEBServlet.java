package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.gonet.horariosescalonados.bean.BeanUsuarioDirectorio;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.service.ParsearJson;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@SuppressWarnings("serial")
public class ConsumeSRestWEBServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		RequestDispatcher dispatcher;	
		
		resp.setContentType("text/plain");

		String resultado = null;
		String StrUsuarioExist = "SN";
		
		ArrayList <String> listaCorreo = new ArrayList<String>();
		
		QueryTables daoSelect = new QueryTables();
		InsertarRegistro dao  = new InsertarRegistro(); 
		
		listaCorreo = daoSelect.CorreosActual();
		
		int maximo = 0;
		int inicio = daoSelect.Busquedaregistro();
		int error = 0;
		
		maximo = listaCorreo.size();
		if(inicio <= maximo - 1)
		{
			
		int finwhile = inicio ; 
		while(inicio <= finwhile)
		{
			
		try {
			
			
			
//			ArrayList <String> listaCorreo= new ArrayList<String>(); 
//			
//			listaCorreo.add("juancarlos.ramirez.contractor@bbva.com");
//			listaCorreo.add("raul.juarez.contractor@bbva.com");
//			listaCorreo.add("guadalupeelizeth.fajardo.contractor@bbva.com");
			
			
			String parametro = req.getParameter("username"); 
			
			List<String> SCOPES = Arrays.asList("email");
			
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			
			String p12Password = "notasecret";
			
			ServletContext context = getServletContext();
			//InputStream keyFileStream  = context.getResourceAsStream("/WEB-INF/dev-bbva-gapis-horarios-escalonados.p12");
			InputStream keyFileStream  = context.getResourceAsStream("/WEB-INF/bbva-gapis-horarios-escalonados.p12");
			
			if (keyFileStream == null){
                throw new Exception("No se encuentra archivo");
            }
			
			keystore.load(keyFileStream, p12Password.toCharArray());
            PrivateKey key = (PrivateKey)keystore.getKey("privatekey", p12Password.toCharArray());
			

			String emailAddressDev = "655437132761-r6s117vkdnpv19qtohncn0eh5rf9360m@developer.gserviceaccount.com";
			
			//String emailAddressDev = "766407286522-pbcsl1nr2f5cf89833o41j9ntnu5v2u7@developer.gserviceaccount.com";

			com.google.api.client.json.JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
			NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			GoogleCredential credential;

			credential = new GoogleCredential.Builder()
					.setTransport(httpTransport)
					.setJsonFactory(JSON_FACTORY)
					.setServiceAccountId(emailAddressDev)
					.setServiceAccountPrivateKey(key)
					.setServiceAccountScopes(SCOPES)
					.setServiceAccountUser("juancarlos.ramirez.contractor@bbva.com")
					.build();
			
			
				String correo_prueba = listaCorreo.get(inicio);
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println(correo_prueba);
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------------------------");
				
				String URI = "https://bbva-gapis.appspot.com/gprofile/users/"+correo_prueba;
				HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
				GenericUrl url = new GenericUrl(URI);
				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse response = request.execute();
				String content = response.parseAsString();
				
				resultado = content;
				
				
				////////////////////////////////////////////////
				
				BeanUsuarioDirectorio bean = new BeanUsuarioDirectorio();
				BeanUsuarioDirectorio strResultadoPar=null;
						
				ParsearJson parser = new ParsearJson();
				
				strResultadoPar = parser.ConvertirAUsuario(resultado);
				
					int resultado1 = 0;
					
					StrUsuarioExist = daoSelect.ConsultarUsuario(correo_prueba);
					
					StrUsuarioExist = daoSelect.ConsultarUsuarioRH(StrUsuarioExist);
					
					
					if(StrUsuarioExist.equalsIgnoreCase("SN"))
					{
						resultado1 = dao.insertarUSER(strResultadoPar.getUid(), strResultadoPar.getDescOUPadre(), strResultadoPar.getDescCentroCoste(), strResultadoPar.getDescOUNivel10(), strResultadoPar.getCodOUPadre(), strResultadoPar.getCodBancoOficinaPers(), strResultadoPar.getCodOUNivel10(), strResultadoPar.getDescCentroTrabajo());
					}
					else
					{
						dao.updateUsuarioRecursos( strResultadoPar.getDescOUPadre(), strResultadoPar.getDescCentroCoste(), strResultadoPar.getDescOUNivel10(), strResultadoPar.getCodOUPadre(), strResultadoPar.getCodBancoOficinaPers(), strResultadoPar.getCodOUNivel10(), strResultadoPar.getDescCentroTrabajo(), StrUsuarioExist);
					}	
					
					if(resultado1==1)
					{
						error = error + 1;
					}
					
			
				
				System.out.println(content);
				System.out.println(listaCorreo.size());
				System.out.println(inicio);
				System.out.println(error);
				
				
			
//			String correo_prueba = listaCorreo.get(2);
//			
//			String URI = "https://bbva-gapis.appspot.com/gprofile/users/"+correo_prueba;
//			HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
//			GenericUrl url = new GenericUrl(URI);
//			HttpRequest request = requestFactory.buildGetRequest(url);
//			HttpResponse response = request.execute();
//			String content = response.parseAsString();
//			
//			resultado = content;
//			
//			
//			////////////////////////////////////////////////
//			
//			BeanUsuarioDirectorio bean = new BeanUsuarioDirectorio();
//			
//			BeanUsuarioDirectorio strResultadoPar=null;
//					
//			ParsearJson parser = new ParsearJson();
//			
//			strResultadoPar = parser.ConvertirAUsuario(resultado);
//			
////			strResultadoPar.getUid();// usuario
////			strResultadoPar.getDescOUPadre();//direccion general
////			strResultadoPar.getDescCentroCoste();//direccion corporativa
////			strResultadoPar.getDescOUNivel10();//area
////			strResultadoPar.getCodOUPadre();//CR direccion General
////			strResultadoPar.getCodBancoOficinaPers();//CR Direccion Corporativa
////			strResultadoPar.getCodOUNivel10();// CR Area
////			strResultadoPar.getDescCentroTrabajo();//Edificio
//			
//			InsertarRegistro dao  = new InsertarRegistro(); 
//			
//			dao.insertarUSER(strResultadoPar.getUid(), strResultadoPar.getDescOUPadre(), strResultadoPar.getDescCentroCoste(), strResultadoPar.getDescOUNivel10(), strResultadoPar.getCodOUPadre(), strResultadoPar.getCodBancoOficinaPers(), strResultadoPar.getCodOUNivel10(), strResultadoPar.getDescCentroTrabajo());
//			
//			System.out.println(content);

		} catch (GeneralSecurityException e) {
			
			error = error + 1;
//			listaCorreo.remove(inicio);
//			maximo = listaCorreo.size();
//			inicio = inicio - 1;
			e.printStackTrace();
			
			
			
			
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			resultado = sw.toString();
			
		} catch (TransformerConfigurationException e) {

			error = error + 1;
//			listaCorreo.remove(inicio);
//			maximo = listaCorreo.size();
//			inicio = inicio - 1;
			e.printStackTrace();
			
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			resultado = sw.toString();
			
		} catch (TransformerFactoryConfigurationError e) {
			
			error = error + 1;
//			listaCorreo.remove(inicio);
//			maximo = listaCorreo.size();
//			inicio = inicio - 1;
			e.printStackTrace();
			
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			resultado = sw.toString();
			
		} catch (TransformerException e) {
			
			error = error + 1;
//			listaCorreo.remove(inicio);
//			maximo = listaCorreo.size();
//			inicio = inicio - 1;
			e.printStackTrace();
			
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			resultado = sw.toString();
			
		} catch (Exception e) {
			
			error = error + 1;
//			listaCorreo.remove(inicio);
//			maximo = listaCorreo.size();
//			inicio = inicio - 1;
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			resultado = sw.toString();
			
		
			System.out.println(listaCorreo.size());
			System.out.println(inicio);
			System.out.println(error);
		}
		
		inicio = inicio + 1;
		dao.updateRegistro(inicio);
		}
		
		
		dispatcher = getServletContext().getRequestDispatcher("/prueba");
		
		dispatcher.forward(req, resp);
		}
		
		System.out.println(error);
//		req.setAttribute("resultado", resultado);
//		
		

	}

}
