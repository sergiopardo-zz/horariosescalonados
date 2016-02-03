package com.gonet.horariosescalonados.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.gonet.horariosescalonados.service.ParsearJson;

import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

@SuppressWarnings("serial")
public class ConsumeSRestWEBServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		RequestDispatcher dispatcher;	
		
		resp.setContentType("text/plain");

		String resultado = null;

		try {
			
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


			String URI = "https://bbva-gapis.appspot.com/gprofile/users/"+parametro;
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
			GenericUrl url = new GenericUrl(URI);
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse response = request.execute();
			String content = response.parseAsString();
			
			resultado = content;
			
			ParsearJson parser = new ParsearJson();
			
			parser.ConvertirAUsuario(resultado);
			
			System.out.println(content);

		} catch (GeneralSecurityException e) {
			
			e.printStackTrace();
			
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			resultado = sw.toString();
			
		} catch (TransformerConfigurationException e) {

			e.printStackTrace();
			
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			resultado = sw.toString();
			
		} catch (TransformerFactoryConfigurationError e) {
			
			e.printStackTrace();
			
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			resultado = sw.toString();
			
		} catch (TransformerException e) {
			e.printStackTrace();
			
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			resultado = sw.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			resultado = sw.toString();
		}
		
		
		req.setAttribute("resultado", resultado);
		
		dispatcher = getServletContext().getRequestDispatcher("/bloqueo2.jsp");
		
		dispatcher.forward(req, resp);

	}

}
