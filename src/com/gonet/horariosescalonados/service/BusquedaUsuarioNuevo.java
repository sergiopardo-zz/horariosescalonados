package com.gonet.horariosescalonados.service;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mysql.fabric.xmlrpc.base.Array;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoExternoRRHH;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanUsuarioDirectorio;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class BusquedaUsuarioNuevo 
{	

	private List<String> listaEmailsUsuarios = null;
	private PrivateKey key = null;
	private List<BeanCyge> listaRegistrosCyge = null;
	private static final Logger log = Logger.getLogger("BusquedaUsuarioNuevo");
	List<BeanUsuarioDirectorio> empleadosNuevos = null;

	public void BuscarUsuarioInterno ()
	{
		log.fine("Funciona ");
		List<BeanEmpleado> empleadosInternos = new ArrayList <BeanEmpleado>();
		List<BeanEmpleadoHorario> empleadosHorarios = new ArrayList <BeanEmpleadoHorario>();
		empleadosNuevos = BuscarUsuario();

		for (BeanUsuarioDirectorio empleadoNuevo : empleadosNuevos )
		{
			BeanEmpleado empleadoInterno = new BeanEmpleado(empleadoNuevo);
			BeanEmpleadoHorario empleadoHorario = new BeanEmpleadoHorario(empleadoInterno);
			
			
			
			empleadosInternos.add(empleadoInterno);
			empleadosHorarios.add(empleadoHorario);
		}
		
		if (!(empleadosInternos.isEmpty()))
		{
			DataNucleusQuery query = new DataNucleusQuery();

			query.PersistirEmpleado(empleadosInternos);
			query.PersistirEmpleadoHorario(empleadosHorarios);
					
			
		}

	}

	public void BuscarUsuarioExterno()
	{
		
		
		List<BeanEmpleadoExternoRRHH> empleadosExternos = new ArrayList <BeanEmpleadoExternoRRHH>();
		empleadosNuevos = BuscarUsuario();

		for (BeanUsuarioDirectorio empleadoNuevo : empleadosNuevos )
		{
			BeanEmpleadoExternoRRHH empleadoExterno = new BeanEmpleadoExternoRRHH(empleadoNuevo);
			
			empleadosExternos.add(empleadoExterno);
		}

		if (!(empleadosExternos.isEmpty()))
		{
			DataNucleusQuery query = new DataNucleusQuery();

			query.InsertarRegistrosRRHH(empleadosExternos);
		}
	}


	public List<BeanUsuarioDirectorio> BuscarUsuario(){

		String resultado = null;
		List<BeanUsuarioDirectorio> empleadosNuevos = new ArrayList <BeanUsuarioDirectorio>();
		BeanUsuarioDirectorio empleadoNuevo = null;

		try { 

			List<String> SCOPES = Arrays.asList("email");
			
			String emailAddressDev = "655437132761-r6s117vkdnpv19qtohncn0eh5rf9360m@developer.gserviceaccount.com";
			//String emailAddressDev = "766407286522-pbcsl1nr2f5cf89833o41j9ntnu5v2u7@developer.gserviceaccount.com";

			JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
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

		if(listaRegistrosCyge != null)

			{
				ArrayList<BeanCyge> arrMail = new ArrayList<BeanCyge>();
				boolean flag= false;
				
				for (BeanCyge registroCyge: listaRegistrosCyge)
			    {
			     if (arrMail.size() == 0){
			      arrMail.add(registroCyge);
			     } else {
			      for (int i=0;i < arrMail.size();i++) 
			      {
			                if(registroCyge.getEmail().equals(arrMail.get(i).getEmail()))
			                {
			                 flag = true;
			                 i = arrMail.size() + 1;
			                }
			            }
			      if (!flag)
			      {
			       arrMail.add(registroCyge);
			      }
			      flag= false;
			     }
			    }
			    for (BeanCyge registroCyge: arrMail)
			    {
			     if ((registroCyge.getEmail() != null)
			       && (!registroCyge.getEmail().isEmpty())
			       && !registroCyge.getEmail().trim().isEmpty())
			     {
						
						/*PRUEBA OFFLINE*/

						try 
						{
						String URI = "http://bbva-gapis.appspot.com/gprofile/users/"+registroCyge.getEmail();
						HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
						GenericUrl url = new GenericUrl(URI);
						HttpRequest request = requestFactory.buildGetRequest(url);
						HttpResponse response = request.execute();
						String content = response.parseAsString();

						resultado = content;
						/*END PRUEBA OFFLINE*/

						//resultado = " ";
						ParsearJson parser = new ParsearJson();

						empleadoNuevo = parser.ConvertirAUsuario(resultado);

						System.out.println(resultado);

						empleadosNuevos.add(empleadoNuevo);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}

			else 
			{
				for (String strEmailUsuario: listaEmailsUsuarios)
				{

					/*PRUEBA OFFLINE*/

					String URI = "http://bbva-gapis.appspot.com/gprofile/users/"+strEmailUsuario;
					HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
					GenericUrl url = new GenericUrl(URI);
					HttpRequest request = requestFactory.buildGetRequest(url);
					HttpResponse response = request.execute();
					String content = response.parseAsString();

					resultado = content;
					/*END PRUEBA OFFLINE*/

					//resultado = "{\"code\":\"OK\",\"message\":\"successful\",\"data\":{\"userDefinedFields\":[{\"key\":\"uid\",\"value\":\"MB58756\"},{\"key\":\"descOUPadre\",\"value\":\"SISTEMAS Y OPERACIONES\"},{\"key\":\"codCorporativo\",\"value\":\"00003719381\"},{\"key\":\"descEmpresaExterno\",\"value\":\" \"},{\"key\":\"codCargo\",\"value\":\"IHJ\"},{\"key\":\"descEmpresa\",\"value\":\"BBVA BANCOMER SERVICIO ADMINIS\"},{\"key\":\"codCSB\",\"value\":\"9016\"},{\"key\":\"descOUBase\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descOUNivel10\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descCentroCoste\",\"value\":\"D Y D CANALES\"},{\"key\":\"uidJefe\",\"value\":\"M901737\"},{\"key\":\"codEstado\",\"value\":\"DF\"},{\"key\":\"codOUNivel10\",\"value\":\"00012899\"},{\"key\":\"codOUPadre\",\"value\":\"00005833\"},{\"key\":\"descpais\",\"value\":\"MÉXICO\"},{\"key\":\"c\",\"value\":\"MEX\"},{\"key\":\"telephoneNumber\",\"value\":\" \"},{\"key\":\"rangoGlobal\",\"value\":\" \"},{\"key\":\"codPostalCentroTrabajo\",\"value\":\"03339\"},{\"key\":\"codBancoOficinaPers\",\"value\":\"01853307\"},{\"key\":\"descCentroTrabajo\",\"value\":\"CENTRO BANCOMER D.F. (CON COME\"},{\"key\":\"descEstado\",\"value\":\"DISTRITO FEDERAL\"},{\"key\":\"mobile\",\"value\":\" \"},{\"key\":\"descLargaPlanta\",\"value\":\"N-6 C-A C-E M-7 E-24\"},{\"key\":\"esEmpleado\",\"value\":\"E\"},{\"key\":\"title\",\"value\":\"LIDER DE PROYECTO DYD A\"},{\"key\":\"o\",\"value\":\"0185\"},{\"key\":\"codCentroCoste\",\"value\":\"0000\"},{\"key\":\"posicion\",\"value\":\"00238344\"},{\"key\":\"pager\",\"value\":\" \"},{\"key\":\"apellido2\",\"value\":\"RAMIREZ\"},{\"key\":\"codCentroTrabajo\",\"value\":\"25001\"},{\"key\":\"descOUNivel2\",\"value\":\"MEXICO\"},{\"key\":\"descOUNivel1\",\"value\":\"MEXICO\"},{\"key\":\"mail\",\"value\":\"PEDRO.ROSAS2@BBVA.COM\"},{\"key\":\"codOficina\",\"value\":\"01853307\"},{\"key\":\"codOUNivel1\",\"value\":\"00000141\"},{\"key\":\"codOUNivel2\",\"value\":\"00000141\"},{\"key\":\"apellido1\",\"value\":\"ROSAS\"},{\"key\":\"codOUNivel3\",\"value\":\"00005833\"},{\"key\":\"descCodPostalCentroTrabajo\",\"value\":\"DF\"},{\"key\":\"descOUNivel9\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descOUNivel8\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descOUNivel7\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descOUNivel6\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descOUNivel5\",\"value\":\"CANALES ATENDIDOS\"},{\"key\":\"descOUNivel4\",\"value\":\"TECNOLOGIA MULTICANAL\"},{\"key\":\"descOUNivel3\",\"value\":\"SISTEMAS Y OPERACIONES\"},{\"key\":\"descBancoOficinaPers\",\"value\":\"3307\"},{\"key\":\"corporateNumber\",\"value\":\" \"},{\"key\":\"codOUNivel5\",\"value\":\"00012899\"},{\"key\":\"codOUNivel4\",\"value\":\"00010142\"},{\"key\":\"codOUNivel7\",\"value\":\"00012899\"},{\"key\":\"codOUNivel6\",\"value\":\"00012899\"},{\"key\":\"codOUNivel9\",\"value\":\"00012899\"},{\"key\":\"codOUNivel8\",\"value\":\"00012899\"}],\"id\":\"102286709937216158038\",\"addresses\":[{\"country\":\"MÉXICO\",\"countryCode\":\"ES\",\"formatted\":\"DISTRITO FEDERAL\\n03339\\nMÉXICO\",\"locality\":\"DF\",\"poBox\":\"03339\",\"postalCode\":\"03339\",\"streetAddress\":\"N-6 C-A C-E M-7 E-24\",\"type\":\"work\"}],\"updateTime\":1449889391185,\"customSchemas\":{\"ldapUserData\":{\"uid\":\"MB58756\",\"descOUPadre\":\"SISTEMAS Y OPERACIONES\",\"codCorporativo\":\"00003719381\",\"descEmpresaExterno\":\" \",\"codCargo\":\"IHJ\",\"descEmpresa\":\"BBVA BANCOMER SERVICIO ADMINIS\",\"codCSB\":\"9016\",\"descOUBase\":\"CANALES ATENDIDOS\",\"descOUNivel10\":\"CANALES ATENDIDOS\",\"descCentroCoste\":\"D Y D CANALES\",\"uidJefe\":\"M901737\",\"codEstado\":\"DF\",\"codOUNivel10\":\"00012899\",\"codOUPadre\":\"00005833\",\"descpais\":\"MÉXICO\",\"codPais\":\"MEX\",\"telephoneNumber\":\" \",\"rangoGlobal\":\" \",\"codPostalCentroTrabajo\":\"03339\",\"codBancoOficinaPers\":\"01853307\",\"descCentroTrabajo\":\"CENTRO BANCOMER D.F. (CON COME\",\"descEstado\":\"DISTRITO FEDERAL\",\"mobile\":\" \",\"descLargaPlanta\":\"N-6 C-A C-E M-7 E-24\",\"esEmpleado\":\"E\",\"title\":\"LIDER DE PROYECTO DYD A\",\"organization\":\"0185\",\"codCentroCoste\":\"0000\",\"posicion\":\"00238344\",\"pager\":\" \",\"apellido2\":\"RAMIREZ\",\"codCentroTrabajo\":\"25001\",\"descOUNivel2\":\"MEXICO\",\"descOUNivel1\":\"MEXICO\",\"mail\":\"PEDRO.ROSAS2@BBVA.COM\",\"codOficina\":\"01853307\",\"codOUNivel1\":\"00000141\",\"codOUNivel2\":\"00000141\",\"apellido1\":\"ROSAS\",\"codOUNivel3\":\"00005833\",\"descCodPostalCentroTrabajo\":\"DF\",\"descOUNivel9\":\"CANALES ATENDIDOS\",\"descOUNivel8\":\"CANALES ATENDIDOS\",\"descOUNivel7\":\"CANALES ATENDIDOS\",\"descOUNivel6\":\"CANALES ATENDIDOS\",\"descOUNivel5\":\"CANALES ATENDIDOS\",\"descOUNivel4\":\"TECNOLOGIA MULTICANAL\",\"descOUNivel3\":\"SISTEMAS Y OPERACIONES\",\"descBancoOficinaPers\":\"3307\",\"corporateNumber\":\" \",\"codOUNivel5\":\"00012899\",\"codOUNivel4\":\"00010142\",\"codOUNivel7\":\"00012899\",\"codOUNivel6\":\"00012899\",\"codOUNivel9\":\"00012899\",\"codOUNivel8\":\"00012899\"}},\"emails\":[{\"address\":\"pedro.rosas2@dev.bbva.com\",\"primary\":true}],\"etag\":\"\\\"KuAr0EK1uUI7oJNfO_dWOREN2ro/dbE6bs1euQMov6wc4Dmi3rfnjUc\\\"\",\"externalIds\":[{\"customType\":\"Employee ID\",\"type\":\"custom\",\"value\":\"MB58756\"}],\"kind\":\"admin#directory#user\",\"name\":{\"familyName\":\"ROSAS RAMIREZ\",\"fullName\":\"PEDRO ROSAS RAMIREZ\",\"givenName\":\"PEDRO\"},\"nonEditableAliases\":[\"pedro.rosas2@dev.bbva.com.test-google-a.com\"],\"organizations\":[{\"customType\":\"Work\",\"department\":\"CANALES ATENDIDOS\",\"location\":\"018533073307\",\"name\":\"BBVA BANCOMER SERVICIO ADMINIS\",\"primary\":true,\"title\":\"LIDER DE PROYECTO DYD A\"}],\"primaryEmail\":\"pedro.rosas2@dev.bbva.com\",\"suspended\":false,\"status\":\"ACTIVE\",\"ouResponsible\":{\"id\":\"00012899\",\"name\":\"CANALES ATENDIDOS\",\"responsibleEmployeeId\":\"M62139\",\"updateDate\":1450243038394}}}";
					ParsearJson parser = new ParsearJson();

					empleadoNuevo = parser.ConvertirAUsuario(resultado);

					System.out.println(resultado);

					empleadosNuevos.add(empleadoNuevo);

				}
			}

		} 
		catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} 
		for(int i = 0; i < empleadosNuevos.size();i++){
			System.out.println("---> " + empleadosNuevos.get(i).getMail());
		}
		return empleadosNuevos;
	}


	public BusquedaUsuarioNuevo()
	{

	}

	public BusquedaUsuarioNuevo(List<String> listaCorreos, PrivateKey key)
	{
		this.listaEmailsUsuarios = listaCorreos;
		this.key = key;

	}

	public BusquedaUsuarioNuevo(List<BeanCyge> listaRegistros, PrivateKey key, int intNumero)
	{
		this.listaRegistrosCyge = listaRegistros;
		this.key = key;
	}


	public PrivateKey getKey() {
		return key;
	}

	public void setKey(PrivateKey key) {
		this.key = key;
	}

	public List<String> getListaEmailsUsuarios() {
		return listaEmailsUsuarios;
	}

	public void setListaEmailsUsuarios(List<String> listaEmailsUsuarios) {
		this.listaEmailsUsuarios = listaEmailsUsuarios;
	}

	public List<BeanCyge> getListaRegistros() {
		return listaRegistrosCyge;
	}

	public void setListaRegistros(List<BeanCyge> listaRegistros) {
		this.listaRegistrosCyge = listaRegistros;
	}	
}