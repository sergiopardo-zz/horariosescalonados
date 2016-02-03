/**
 * 
 */
package com.gonet.horariosescalonados.util;

/**
 * @author Daniel Julian Zepeda
 *
 */
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.gonet.horariosescalonados.bean.BeanHorario;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class EnviarCorreo {
	/**
	 * Clase pare el envío de correos de las solicitudes
	 */
	//public static String sServidor = "smtp.gmail.com";
	public static String sAsuntoCorreo = "";
	public static String sRemitente = "";
	public static String sDestinatario = "";
	public static String sEncabezado = ""; 
	public static String sMensajeCorreo = "";
	
	public EnviarCorreo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public EnviarCorreo(String sAsunto, String sDe, String sPara, String sNombreEnc) throws Exception {
		sRemitente = sDe;
		sDestinatario = sPara;
		sEncabezado = HeConstantes.ENCABEZADO_USUARIO;
		
		if(sAsunto.equals("ALTA")) {
			sAsuntoCorreo = HeConstantes.ASUNTO_SOLICITUDES_PENDIENTES;
			sEncabezado = HeConstantes.ENCABEZADO_AUTORIZADOR;
			sMensajeCorreo = HeConstantes.MENSAJE_SOLICITUDES_PENDIENTES;
		} else if(sAsunto.equals("APROBADA")) {
			sAsuntoCorreo = HeConstantes.ASUNTO_SOLICITUD_APROBADA;
			sMensajeCorreo = HeConstantes.MENSAJE_SOLICITUD_APROBADA;
		} else if(sAsunto.equals("RECHAZADA")) {
			sAsuntoCorreo = HeConstantes.ASUNTO_SOLICITUD_RECHAZADA;
			sMensajeCorreo = HeConstantes.MENSAJE_SOLICITUD_RECHAZADA;
		} else if(sAsunto.equals("BLOQUEADO")) {
			sAsuntoCorreo = HeConstantes.ASUNTO_HORARIO_BLOQUEADO;
			sMensajeCorreo = HeConstantes.MENSAJE_HORARIO_BLOQUEADO;
		} else if(sAsunto.equals("ELIMINADO")) {
			sAsuntoCorreo = HeConstantes.ASUNTO_HORARIO_ELIMINADO;
			sMensajeCorreo = HeConstantes.MENSAJE_HORARIO_ELIMINADO;
		} else {
			System.out.println("----> EnviarCorreo.enviar() : No se lanzará el correo, pues el asunto no es válido");
			Exception ex = new Exception("Asunto de correo NO válido : ["+sAsunto+"]");
			throw ex;
		}
		
		// Se agrega al encabezado el nombre de la persona  
		sEncabezado = sustituir(sEncabezado, HeConstantes.CADENA_NOMBRE, sNombreEnc);
		// Se pone en el encabezado la fecha actual
		sEncabezado = sustituir(sEncabezado, HeConstantes.CADENA_FECHA, ObtenFechas.fechaHoy());		
	}
	
	//Envia correo de solicitud "APROBADA"
	public EnviarCorreo(String sAsunto, String sDe, String sPara, String sNombreEnc, BeanHorario horario) throws Exception{
		this(sAsunto, sDe, sPara, sNombreEnc);
		//Se sustituye horario de Entrada
		sMensajeCorreo = sustituir(sMensajeCorreo, HeConstantes.HORA_ENTRADA, horario.getEntrada());
		//Se sustituye horario de Salida
		sMensajeCorreo = sustituir(sMensajeCorreo, HeConstantes.HORA_SALIDA, horario.getSalida());
	}
	
	//Envia correo de solicitud "RECHAZADA"
	public EnviarCorreo(String sAsunto, String sDe, String sPara, String sNombreEnc, String motivo) throws Exception{
		this(sAsunto, sDe, sPara, sNombreEnc);
		//Se sustituye El motivo del Rechazo
		sMensajeCorreo = sustituir(sMensajeCorreo, HeConstantes.CADENA_COMENTARIO, motivo);		
		
	}
	
	public void enviar() {
		/**
		 * Método que envía el correo
		 */
		System.out.println("----> EnviarCorreo.enviar() : Se inicia el envio del correo");
		Properties props = new Properties();
		Session sesion = Session.getDefaultInstance(props, null);
		
		try {
			// Creamos un objeto mensaje tipo MimeMessage por defecto.
			Message mensaje = new MimeMessage(sesion);
			
			// Asignamos el “de o from” al header del correo.
			mensaje.setFrom(new InternetAddress(sRemitente, "Horarios Escalonados"));
			System.out.println("----> EnviarCorreo.enviar() : Remitente = "+sRemitente);
			
			// Asignamos el “para o to” al header del correo.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(sDestinatario, "Usuario"));
			System.out.println("----> EnviarCorreo.enviar() : Destinatario = "+sDestinatario);
			
			// Asignamos el asunto
			mensaje.setSubject(MimeUtility.encodeText(sAsuntoCorreo, "utf-8", "B"));
			//mensaje.setSubject(sAsuntoCorreo);
			System.out.println("----> EnviarCorreo.enviar() : Asunto = "+sAsuntoCorreo);
			
			// Asignamos el mensaje como tal
			mensaje.setText(sEncabezado+sMensajeCorreo);
			
			// Enviamos el correo
			Transport.send(mensaje);
			
		    System.out.println("----> EnviarCorreo.enviar() : Mensaje enviado a "+sDestinatario);
		    System.out.println("----> EnviarCorreo.enviar() : Mensaje : \n"+sEncabezado+sMensajeCorreo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String sustituir(String sTextoOriginal, String sCadenaAsustituir, String sValorNuevo) {
		String sCadenaFinal = sTextoOriginal.replace(sCadenaAsustituir, sValorNuevo);
		return sCadenaFinal;
	}
}
