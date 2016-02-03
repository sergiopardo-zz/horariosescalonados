/**
 * Clase para almacenar las constantes que se vayan a usa
 */
package com.gonet.horariosescalonados.util;

public class HeConstantes {
	public static String ASUNTO_SOLICITUDES_PENDIENTES = 	"***SOLICITUDES PENDIENTES POR AUTORIZAR***";
	public static String ASUNTO_SOLICITUD_APROBADA = 		"***NOTIFICACIÓN DE SOLICITUD APROBADA***";
	public static String ASUNTO_SOLICITUD_RECHAZADA = 		"***NOTIFICACIÓN DE SOLICITUD RECHAZADA***";
	public static String ASUNTO_HORARIO_BLOQUEADO = 		"***NOTIFICACIÓN DE HORARIO BLOQUEADO***";
	public static String ASUNTO_HORARIO_ELIMINADO = 		"***NOTIFICACIÓN DE HORARIO ELIMINADO***";
	
	public static String ENCABEZADO_AUTORIZADOR = "Autorizador: XXXXX      Fecha: FFFFF \n";
	public static String ENCABEZADO_USUARIO = "Usuario: XXXXX      Fecha: FFFFF \n";
	
	public static String HORA_ENTRADA = "EHH:MM";
	public static String HORA_SALIDA = "SHH:MM";
		
	public static String CADENA_NOMBRE = "XXXXX"; // Cadena a buscar y sustituir en ENCABEZADO_AUTORIZADOR
	public static String CADENA_FECHA = "FFFFF"; // Cadena a buscar y sustituir en ENCABEZADO_AUTORIZADOR
	public static String CADENA_COMENTARIO = "COMENTARIO"; // Cadena a buscar y sustituir en MENSAJE_SOLICITUD_RECHAZADA
	
	public static String MENSAJE_SOLICITUDES_PENDIENTES = "--------------------------------------------------------------------\n\n"
														+ "Te informamos que tienes una solicitud de cambio de Horario pendiente de autorizar a través del aplicativo \"Horarios Escalonados\".\n\n"
														+ "Ingrese a la siguiente liga para conocer el detalle de la solicitud y emitir una respuesta.\n\n"
														+ "[https://bbva-bancomer-mihorario.appspot.com]\n\n"
														+ "Este mensaje es automático por lo que no debe ser contestado.\n\n"
														+ "RRHH\n"
														+ "Canales y CSC de RRHH.\n\n";
	
	public static String MENSAJE_SOLICITUD_APROBADA = "--------------------------------------------------------------------\n\n" 
													+ "Te informamos que tu solicitud de cambio de Horario ha sido autorizada.\n\n"
													+ "Su nuevo horario será de EHH:MM hrs a SHH:MM hrs a partir del siguiente día hábil. "
													+ "La definición o cambio del Horario no modifica el esquema de trabajo de Jornada Discontinua.\n\n"
													+ "Este mensaje es automático por lo que no debe ser contestado.\n\n"
													+ "RRHH\n"
													+ "Canales y CSC de RRHH.\n\n";
	
	public static String MENSAJE_SOLICITUD_RECHAZADA = "--------------------------------------------------------------------\n\n"
													+ "Te informamos que tu solicitud de cambio de Horario ha sido rechazada por la siguiente razón:\n\n"
													+ "COMENTARIO\n\n"
													+ "Este mensaje es automático por lo que no debe ser contestado.\n\n"
													+ "RRHH\n"
													+ "Canales y CSC de RRHH.\n\n";
	
	public static String MENSAJE_HORARIO_BLOQUEADO = "--------------------------------------------------------------------\n\n"  
													+ "Le informamos que la solicitud de horario que generó a través de la herramienta \"Horarios Escalonados\" ha sido rechazada debido a que el horario ha sido bloqueado por el administrador.\n\n"
													+ "Este mensaje es automático por lo que no debe ser contestado.\n\n"
													+ "RRHH\n"
													+ "Canales y CSC de RRHH.\n\n";
	
	public static String MENSAJE_HORARIO_ELIMINADO = "--------------------------------------------------------------------\n\n"  
													+ "Le informamos que la solicitud de horario que generó a través de la herramienta \"Horarios Escalonados\" ha sido rechazada debido a que el horario ha sido eliminado por el administrador.\n\n"
													+ "Este mensaje es automático por lo que no debe ser contestado.\n\n"
													+ "RRHH\n"
													+ "Canales y CSC de RRHH.\n\n";
	
	public static int COLUMNAS_ARCHIVO_ZEIT = 8;
	
	public static int COLUMNAS_ARCHIVO_CYGE = 17;
	
	public static int COLUMNAS_ARCHIVO_PERFILCONULTA = 1;
	
	public static String DATO_INVALIDO_REPORTE = "DATO INVÁLIDO";
	
}

