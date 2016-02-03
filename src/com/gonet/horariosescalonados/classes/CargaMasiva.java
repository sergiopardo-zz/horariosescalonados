package com.gonet.horariosescalonados.classes;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import com.gonet.horariosescalonados.bean.BeanCumplimiento;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanHorario;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.dao.QueryTables;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.gonet.horariosescalonados.util.ObtenFechas;
import com.gonet.horariosescalonados.util.ObtenKeyHora;
import com.google.api.client.util.IOUtils;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


public class CargaMasiva {

	public String cargaArchivo(List<BlobKey> blobKeys, HttpServletResponse resp){
		String swriter = "";
		try{				
			InputStream isArchivo = new BlobstoreInputStream(blobKeys.get(0));
			WorkbookSettings opciones = new WorkbookSettings();
			opciones.setEncoding("ISO-8859-1");			
			Workbook wbArchExel = Workbook.getWorkbook(isArchivo, opciones);			
			System.out.println(this.toString() + "-----> Creado objeto Workbook wbArchExel.");
			Sheet shHoja = wbArchExel.getSheet(0);
			int iRenglones = shHoja.getRows();
			System.out.println(this.toString() + "-----> Registros de la hoja "+shHoja.getName()+" = "+iRenglones);
			swriter+="Cargando archivo con ... "+iRenglones+" registros \n\n";
			swriter+="Nota: Este achivo lo puede copiar a excel. \n\n";
			BeanEmpleado beanEmpleado;
			BeanHorario beanHorario;
			for(int r=0; r<iRenglones; r++) {
				Cell celdasRenglon[] = shHoja.getRow(r); // Se obtiene el renglón
				String sNombre = celdasRenglon[0].getContents();
				if(!sNombre.equals("")) {
					swriter+="";
					if(celdasRenglon.length!=12){
						swriter+="-------->\n";
						swriter+="El dato no presenta el formato requerido. Favor de validarlo.\n";
						System.out.println("El dato no presenta el formato requerido. Favor de validarlo.\n");
					}else{
						String idUser = getDato(celdasRenglon[0].getContents());
						String idJefe = getDato(celdasRenglon[1].getContents());
						String ubicacion = getDato(celdasRenglon[2].getContents());
						String tipoEmp = getDato(celdasRenglon[3].getContents());						
						String apePat = getDato(celdasRenglon[4].getContents());
						String apeMat = getDato(celdasRenglon[5].getContents());
						String nombre = getDato(celdasRenglon[6].getContents());
						String email = getDato(celdasRenglon[7].getContents());
						String horario = ObtenKeyHora.getKeyHora(celdasRenglon[8].getContents());
						String registro = getDato(celdasRenglon[9].getContents());
						String nomCR = getDato(celdasRenglon[10].getContents());
						String dga = getDato(celdasRenglon[11].getContents());
						System.out.println("\nREGISTRO "+(r+1)+": IDUSUARIO=["+idUser+"] NOMBRE_EMPLEADO=["+apePat+" "+apeMat+" "+nombre+"]"
								+ " TIPO_EMPLEADO=["+tipoEmp+"] IDSUPERVISOR=["+idJefe+"]");
						swriter+="";
						swriter+="\nREGISTRO "+(r+1)+": \tIDUSUARIO=\t["+idUser+"] \tNOMBRE_EMPLEADO=\t["+apePat+" "+apeMat+" "+nombre+"]"
								+ " \tTIPOEMPLEADO=\t["+tipoEmp+"] \tIDSUPERVISOR=\t["+idJefe+"]\t";

						if(horario.equals("N/A")){
							horario = "00000000";
						}
						beanHorario = Horario.getHorario("horario", "horarioId", horario);
						beanEmpleado = new BeanEmpleado(idUser, apePat, apeMat, nombre, email.toLowerCase(), ubicacion, tipoEmp, registro, 
								nomCR, dga, idJefe, beanHorario.getHorarioId(), beanHorario.getEntrada(), beanHorario.getSalida());
						if(!Empleado.existe(idUser)){
							swriter+="registrado.\t";
							Empleado.setEmpleado(beanEmpleado);
							Empleado.setEmpleadoHorario(beanEmpleado);
						}else{
							swriter+="existente.\t";
						}

						if(Empleado.existe(idJefe)){
							System.out.println("Supervisor ["+idJefe+"] registrado, se complementan datos.");
							swriter+="Supervisor \t["+idJefe+"] \tmodifica a S.\n";
							Empleado.setSupervisor("empleado", "tipoEmpleado", "S", "empleadoID", idJefe);
						}else{
							swriter+="Supervisor \t["+idJefe+"] \tinexistente.\n";
						}
					}				
				}					
			}
		} catch (Exception e) {			
			return swriter+="--> Ocurrio un error al cargar el archivo:\n"+ e;			
		}
		return swriter;		
	}

	public String cargaArchivoHoras(List<BlobKey> blobKeys, HttpServletResponse resp){
		String swriter = "";
		try{				
			InputStream isArchivo = new BlobstoreInputStream(blobKeys.get(0));
			Workbook wbArchExel = Workbook.getWorkbook(isArchivo);
			System.out.println("-----> Creado objeto Workbook wbArchExel.");
			Sheet shHoja = wbArchExel.getSheet(0);
			int iRenglones = shHoja.getRows();
			System.out.println( "-----> Registros de la hoja "+shHoja.getName()+" = "+iRenglones);			
			swriter+="Cargando archivobet1 con... "+iRenglones+" registros \n\n";
			BeanEmpleado empleado;
			BeanHorario horario;
			for(int r=0; r<iRenglones; r++) {

				try
				{
					Cell celdasRenglon[] = shHoja.getRow(r); // Se obtiene el renglón
					String sNombre = celdasRenglon[0].getContents();
					if(!sNombre.equals("")) {
						if(celdasRenglon.length!=3){
							swriter+="El dato no presenta el formato requerido. Favor de validarlo.\n";
							System.out.println("El dato no presenta el formato requerido. Favor de validarlo.\n");
						}else{
							String idUser = getDato(celdasRenglon[0].getContents());					
							String one = ObtenKeyHora.getNumberHora(getDato(celdasRenglon[1].getContents()));
							String two = ObtenKeyHora.getNumberHora(getDato(celdasRenglon[2].getContents()));
							String idHorario = one+two;
							if(Empleado.existe(idUser)){
								System.out.println("-----> REGISTRO "+(r+1)+" : IDUSUARIO=["+idUser+
										"]\tNUEVOHORARIO=["+getDato(celdasRenglon[1].getContents())+"-"+getDato(celdasRenglon[2].getContents())+"]");
								swriter+="REGISTRO "+(r+1)+" : \tIDUSUARIO=\t["+idUser+
										"]\tNUEVOHORARIO=\t["+getDato(celdasRenglon[1].getContents())+"-"+getDato(celdasRenglon[2].getContents())+"]\t";
								//Obtenemos los datos del empleado					
								empleado = Empleado.getEmpleado(idUser);			
								if(Horario.existe(idHorario)){
									swriter+="Existente\t";
									horario = Horario.getHorario("horario", "horarioId", idHorario);
									Horario.setHoraEmpleado(empleado, horario);
									Horario.setFecha(empleado, "fechaSolicitud", new java.sql.Date(ObtenFechas.fechaHoyDate().getTime()),
											"fechaAplicacion", new java.sql.Date(ObtenFechas.fechaHoyDate().getTime()));
								}else{
									swriter+="Inexistente\t\n";
								}							
								Horario set = new Horario();
								set.setOneToHorario(idHorario, true);
								set.setOneToHorario(empleado.getHorarioID(), false);
								swriter+="\n";
							}else{
								swriter+="\tEmpleado no registrado \tIdUser: \t["+idUser+"] \tHorarioSolic: \t["+idHorario+"]";
								System.out.println("-----> IdUser: "+idUser+" --HorarioSolic: "+idHorario);
							}

						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (Exception e) {			
			return swriter+= "--> Ocurrio un error al cargar el archivo:"+ e;			
		}
		return swriter;
	}

	public String cargaEntradaSalida(List<BlobKey> blobKeys, HttpServletResponse resp){
		String swriter = "Carga de Empleados.. ";
		int iRenglones =0;
		try{
			InputStream isArchivo = new BlobstoreInputStream(blobKeys.get(0));
			WorkbookSettings opciones = new WorkbookSettings();
			opciones.setEncoding("ISO-8859-1");
			Workbook wbArchExel = Workbook.getWorkbook(isArchivo, opciones);
			System.out.println("-----> Creado objeto Workbook wbArchExel.");
			Sheet shHoja = wbArchExel.getSheet(0);
			iRenglones = shHoja.getRows();
			System.out.println("-----> Registros de la hoja "+shHoja.getName()+" = "+iRenglones);			
			swriter+="Cargando archivo con ... "+iRenglones+" renglones\n";
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date tempDate = new Date();
			String tempFecha = "";
			String tempIdUser = "";
			String tempEntrada = "";
			String heRealEntrada = "";
			String tempSalida = "";
			String heRealSalida = "";
			int intEntrada = 0;
			int intSalida = 0;
			int intJornada = 0;
			int intTotal = 0;
			int count=1;
			boolean bEntrada = false;
			boolean bSalida = false;
			QueryTables query = new QueryTables();
			BeanEmpleado tempBeanEmpleado = new BeanEmpleado();
			BeanEmpleado beanEmpleado;;
			InsertarRegistro insertar = new InsertarRegistro();
			for(int r=0; r<iRenglones; r++) {
				System.out.println("count..."+count++);
				Cell celdasRenglon[] = shHoja.getRow(r);
				String sNombre = celdasRenglon[0].getContents();
				if(!sNombre.equals("")) {
					if(celdasRenglon.length!=13){
						swriter+="El dato no presenta el formato requerido. Favor de validarlo.\n";
						System.out.println("El dato no presenta el formato requerido. Favor de validarlo.\n");
					}else{
						String usuario = getDato(celdasRenglon[4].getContents());
						String fecha = celdasRenglon[0].getContents().toString();
						Date date = df.parse(fecha);
						String hora = celdasRenglon[1].getContents().toString();
						String tipoFuncion = getDato(celdasRenglon[11].getContents());
						if(!Empleado.existe(usuario)){
							swriter+="Empleado no registrado: "+usuario+"\n";
						}
						beanEmpleado = Empleado.getEmpleado(usuario);
						if((tempIdUser!="") && (!tempIdUser.equals(usuario))){
							if(intEntrada==1 && intSalida==1){
								intJornada = 1;
							}else {
								intJornada = 0;
							}
							intTotal = intEntrada + intSalida +intJornada;
							String porcentaje = (intTotal==3?"100%":(intTotal==1?"33%":"0%"));
							if(bEntrada && bSalida){
								String tempHE = tempBeanEmpleado.getHoraEntrada();
								String tempHS = tempBeanEmpleado.getHoraSalida();						    		
								BeanCumplimiento beanCumplimiento = new BeanCumplimiento(tempBeanEmpleado.getEmpleadoID(), tempBeanEmpleado.getApePaterno(), 
										tempBeanEmpleado.getApeMaterno(), tempBeanEmpleado.getNombre(), tempBeanEmpleado.getNombreCR(), tempBeanEmpleado.getDga(),
										tempDate, quincena(tempFecha), mounth(tempFecha), resta(tempHE), tempHE, suma(tempHE), heRealEntrada, intEntrada+"", resta(tempHS), tempHS, 
										suma(tempHS), heRealSalida, intSalida+"", intJornada+"", intTotal+"", porcentaje);
								swriter+= ("\n"+beanCumplimiento.toStringA()+"\n");
								insertar.cumplimiento(beanCumplimiento);
							}else if(bEntrada && !bSalida){					    			
								String tempHE = tempBeanEmpleado.getHoraEntrada();
								String tempHS = tempBeanEmpleado.getHoraSalida();
								BeanCumplimiento beanCumplimiento = new BeanCumplimiento(tempBeanEmpleado.getEmpleadoID(), tempBeanEmpleado.getApePaterno(), 
										tempBeanEmpleado.getApeMaterno(), tempBeanEmpleado.getNombre(), tempBeanEmpleado.getNombreCR(), tempBeanEmpleado.getDga(),
										tempDate, quincena(tempFecha), mounth(tempFecha), resta(tempHE), tempHE, suma(tempHE), heRealEntrada, intEntrada+"", resta(tempHS), tempHS, 
										suma(tempHS), "00:00", intSalida+"", intJornada+"", intTotal+"", porcentaje);
								swriter+= ("\n"+beanCumplimiento.toStringA()+"\n");
								insertar.cumplimiento(beanCumplimiento);
							}else if(!bEntrada && bSalida){					    			
								String tempHE = tempBeanEmpleado.getHoraEntrada();
								String tempHS = tempBeanEmpleado.getHoraSalida();
								BeanCumplimiento beanCumplimiento = new BeanCumplimiento(tempBeanEmpleado.getEmpleadoID(), tempBeanEmpleado.getApePaterno(), 
										tempBeanEmpleado.getApeMaterno(), tempBeanEmpleado.getNombre(), tempBeanEmpleado.getNombreCR(), tempBeanEmpleado.getDga(),
										tempDate, quincena(tempFecha), mounth(tempFecha), resta(tempHE), tempHE, suma(tempHE), "00:00", intEntrada+"", resta(tempHS), tempHS, 
										suma(tempHS), heRealSalida, intSalida+"", intJornada+"", intTotal+"", porcentaje);
								swriter+= ("\n"+beanCumplimiento.toStringA()+"\n");
								insertar.cumplimiento(beanCumplimiento);
							}
							bEntrada = false;
							bSalida = false;
							intEntrada = 0;
							intSalida = 0;
							intJornada = 0;
							intTotal = 0;
						}
						if(tipoFuncion.equals("ENTRADA") && beanEmpleado.getEmpleadoID()!=null){
							tempEntrada = beanEmpleado.getHoraEntrada();
							heRealEntrada = hora;
							intEntrada = cumple(heRealEntrada, tempEntrada);
							tempFecha = fecha;
							tempDate = date;
							tempIdUser = usuario;
							tempBeanEmpleado = beanEmpleado;
							bEntrada = true;
						} 
						if(tipoFuncion.equals("SALIDA") && beanEmpleado.getEmpleadoID()!=null){
							tempSalida = beanEmpleado.getHoraSalida();
							heRealSalida = hora;
							intSalida = cumple(heRealSalida, tempSalida);
							tempFecha = fecha;
							tempDate = date;
							tempIdUser = usuario;
							tempBeanEmpleado = beanEmpleado;
							bSalida = true;
						}			    		
						if((r+1)>=iRenglones){			    			
							if(intEntrada==1 && intSalida==1){
								intJornada = 1;
							}else {
								intJornada = 0;
							}
							intTotal = intEntrada + intSalida +intJornada;
							String porcentaje = (intTotal==3?"100%":(intTotal==1?"33%":"0%"));
							if(bEntrada && bSalida){
								String tempHE = tempBeanEmpleado.getHoraEntrada();
								String tempHS = tempBeanEmpleado.getHoraSalida();						    		
								BeanCumplimiento beanCumplimiento = new BeanCumplimiento(tempBeanEmpleado.getEmpleadoID(), tempBeanEmpleado.getApePaterno(), 
										tempBeanEmpleado.getApeMaterno(), tempBeanEmpleado.getNombre(), tempBeanEmpleado.getNombreCR(), tempBeanEmpleado.getDga(),
										tempDate, quincena(tempFecha), mounth(tempFecha), resta(tempHE), tempHE, suma(tempHE), heRealEntrada, intEntrada+"", resta(tempHS), tempHS, 
										suma(tempHS), heRealSalida, intSalida+"", intJornada+"", intTotal+"", porcentaje);
								swriter+= ("\n"+beanCumplimiento.toStringA()+"\n");
								insertar.cumplimiento(beanCumplimiento);
							}else if(bEntrada && !bSalida){					    			
								String tempHE = tempBeanEmpleado.getHoraEntrada();
								String tempHS = tempBeanEmpleado.getHoraSalida();
								BeanCumplimiento beanCumplimiento = new BeanCumplimiento(tempBeanEmpleado.getEmpleadoID(), tempBeanEmpleado.getApePaterno(), 
										tempBeanEmpleado.getApeMaterno(), tempBeanEmpleado.getNombre(), tempBeanEmpleado.getNombreCR(), tempBeanEmpleado.getDga(),
										tempDate, quincena(tempFecha), mounth(tempFecha), resta(tempHE), tempHE, suma(tempHE), heRealEntrada, intEntrada+"", resta(tempHS), tempHS, 
										suma(tempHS), "00:00", intSalida+"", intJornada+"", intTotal+"", porcentaje);
								swriter+= ("\n"+beanCumplimiento.toStringA()+"\n");
								insertar.cumplimiento(beanCumplimiento);
							}else {					    			
								String tempHE = tempBeanEmpleado.getHoraEntrada();
								String tempHS = tempBeanEmpleado.getHoraSalida();
								BeanCumplimiento beanCumplimiento = new BeanCumplimiento(tempBeanEmpleado.getEmpleadoID(), tempBeanEmpleado.getApePaterno(), 
										tempBeanEmpleado.getApeMaterno(), tempBeanEmpleado.getNombre(), tempBeanEmpleado.getNombreCR(), tempBeanEmpleado.getDga(),
										tempDate, quincena(tempFecha), mounth(tempFecha), resta(tempHE), tempHE, suma(tempHE), "00:00", intEntrada+"", resta(tempHS), tempHS, 
										suma(tempHS), heRealSalida, intSalida+"", intJornada+"", intTotal+"", porcentaje);
								swriter+= ("\n"+beanCumplimiento.toStringA()+"\n");
								insertar.cumplimiento(beanCumplimiento);
							}
							bEntrada = bSalida = false;
							intEntrada = 0;
							intSalida = 0;
							intJornada = 0;
							intTotal = 0;
						}
					}			    
				}				
			}
		} catch (Exception e) {		
			System.err.println(e);
			return swriter+="--> Ocurrio un error al cargar el archivo.\n";			
		}
		return swriter;
	}

	public String getDato(String dato){
		if(dato==null || dato == ""){
			return "N/A";
		}else{
			return dato.trim();
		}		
	}



	public int cumple(String heReal,String he) throws ParseException{
		int retorna = 0;
		Date date1 = null;
		Date date2 = null;
		String FormatoHoraAMPM = "HH:mm";
		String FormatoHora = "HH:mm";
		SimpleDateFormat sdfReal = new SimpleDateFormat(FormatoHoraAMPM, Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat(FormatoHora, Locale.ENGLISH);

		date1 = sdfReal.parse(heReal); //heReal
		date2 = sdf.parse(he);

		Calendar calendarDespues = Calendar.getInstance();
		Calendar calendarAntes = Calendar.getInstance();

		calendarDespues.setTime(date2); // Configuramos la fecha que se recibe
		calendarDespues.add(Calendar.MINUTE, 15); // numero de horas a añadir, o restar en caso de horas<0
		calendarAntes.setTime(date2); // Configuramos la fecha que se recibe
		calendarAntes.add(Calendar.MINUTE, -15); // numero de horas a añadir, o restar en caso de horas<0
		Date TheA = calendarAntes.getTime();
		Date TheD = calendarDespues.getTime();

		int resA = date1.compareTo(TheA); //Si es mayor de cero significa que date1 es despues de date2.
		int resD = date1.compareTo(TheD); //Si es mayor de cero significa que date1 es despues de date2.
		if(resD > 0){
			//System.out.println("e/s despues de la hora de Tolerancia"); //RETORNAR VALOR 0
			retorna = 0;
		}else if(resA < 0){
			//System.out.println("e/s antes de la hora de Tolerancia"); //RETORNAR VALOR 0
			retorna = 0;
		}else{
			//System.out.println("e/s dentro de su Horario");
			retorna = 1;
		}
		return retorna;		
	}

	public static String resta(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormat sdf = new SimpleDateFormat("HH:mm");
			date = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();		
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, -15);
			return sdf.format(calendar.getTime());
		}
		return "";	
	}

	public static String suma(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormat sdf = new SimpleDateFormat("HH:mm");
			date = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();		
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, 15); 		
			return sdf.format(calendar.getTime());
		}
		return "";
	}

	public static String quincena(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			date = sdf.parse(time);
			int day = date.getDay();
			if(day<=15){
				return "Q1";
			} 		
			return "Q2";
		}
		return "";

	}

	public static String mounth(String time) throws ParseException{
		if(time!=""&&time!=null){
			Date date = null;
			DateFormatSymbols dfs = new DateFormatSymbols();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			date = sdf.parse(time);
			int intMonth = date.getMonth();
			String[] months = dfs.getMonths();
			return months[intMonth];
		}
		return "";		
	}


	public String cargaPrueba(List<BlobKey> blobKeys, HttpServletResponse resp){
		String swriter = "Carga de Empleados.. ";
		int iRenglones =0;

		try{
			InputStream isArchivo = new BlobstoreInputStream(blobKeys.get(0));
			WorkbookSettings opciones = new WorkbookSettings();
			opciones.setEncoding("ISO-8859-1");
			Workbook wbArchExel = Workbook.getWorkbook(isArchivo, opciones);
			System.out.println("-----> Creado objeto Workbook wbArchExel.");
			Sheet shHoja = wbArchExel.getSheet(0);
			iRenglones = shHoja.getRows();
			System.out.println("-----> Registros de la hoja "+shHoja.getName()+" = "+iRenglones);			
			swriter+="Cargando archivo con ... "+iRenglones+" renglones\n";
			String tempFecha = "", tempIdUser = "";
			String heRealEntrada = "", heRealSalida = "";
			int count=1;
			String firstString="", secondString="";
			boolean bEntrada = false, bSalida = false;
			for(int r=0; r<iRenglones; r++) {
				Cell celdasRenglon[] = shHoja.getRow(r);
				String sNombre = celdasRenglon[0].getContents();
				if(!sNombre.equals("")) {
					if(celdasRenglon.length!=13){
						swriter+="El dato no presenta el formato requerido. Favor de validarlo.\n";
						System.out.println("El dato no presenta el formato requerido. Favor de validarlo.\n");
					}else{
						String fecha = celdasRenglon[0].getContents().toString();
						String hora = celdasRenglon[1].getContents().toString();						
						String usuario = getDato(celdasRenglon[4].getContents());
						String tipoFuncion = getDato(celdasRenglon[11].getContents());
						if(((tempIdUser!="") && (!tempIdUser.equals(usuario)))){						    		
							if(bEntrada && bSalida){
								System.out.println(firstString+"="+secondString);

							}else if(bEntrada && !bSalida){
								secondString = tempIdUser+"-"+tempFecha.replaceAll("/", "_")+"-00:00-SALIDA";
								System.out.println(firstString+"="+secondString);
							}else {
								firstString = tempIdUser+"-"+tempFecha.replaceAll("/", "_")+"-00:00-ENTRADA";
								System.out.println(firstString+"="+secondString);	
							}
							bEntrada = bSalida = false;				    		
						}
						System.out.println("count..."+count++);
						if(tipoFuncion.equals("ENTRADA")){
							System.out.println("ENTRADA");
							tempIdUser = usuario;
							tempFecha = fecha;
							heRealEntrada = hora;
							firstString = tempIdUser+"-"+tempFecha.replaceAll("/", "_")+"-"+heRealEntrada+"-"+tipoFuncion;
							bEntrada = true;
						}
						if(tipoFuncion.equals("SALIDA")){
							System.out.println("SALIDA");
							tempIdUser = usuario;
							tempFecha = fecha;
							heRealSalida = hora;						    	
							secondString = tempIdUser+"-"+tempFecha.replaceAll("/", "_")+"-"+heRealSalida+"-"+tipoFuncion;
							bSalida = true;
						}

						if((r+1)==iRenglones){
							if(bEntrada && bSalida){
								System.out.println(firstString+"="+secondString);
							}else if(bEntrada && !bSalida){
								secondString = tempIdUser+"-"+tempFecha.replaceAll("/", "_")+"-00:00-SALIDA";
								System.out.println(firstString+"="+secondString);
							}else {
								firstString = tempIdUser+"-"+tempFecha.replaceAll("/", "_")+"-00:00-ENTRADA";
								System.out.println(firstString+"="+secondString);	
							}
							bEntrada = bSalida = false;
						}
					}
				}				
			}
		}catch (Exception e) {		
			System.err.println(e);
			return swriter+="--> Ocurrio un error al cargar el archivo.\n";			
		}
		return swriter;
	}


	public String cargaPerfilConsulta(String idEmpleado,String TipoConsulta){

		DataNucleusQuery query = new DataNucleusQuery ();

		query.agregaPerfilConsulta(idEmpleado, TipoConsulta);

		return"";
	}

	public String cargaUsuarios(List<BlobKey> blobKeys, HttpServletResponse resp,String idEmpleado,String TipoConsulta){
		String swriter = "Carga de Empleados..\n ";
		int iRenglones =0;
		try{
			InputStream isArchivo = new BlobstoreInputStream(blobKeys.get(0));

			byte[] buffer = new byte[10000];
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int byteRead;

			while((byteRead = isArchivo.read(buffer)) != -1){
				output.write(buffer,0,byteRead);
			}

			byte[] last = output.toByteArray();

			String strUsuarios = new String(last);

			String[] arrUsuarios = strUsuarios.split("\r?\n|\r");
			DataNucleusQuery query = new DataNucleusQuery ();


			swriter= swriter + query.agregarUsuario(idEmpleado, arrUsuarios,TipoConsulta) + "\n";



		} catch (Exception e) {		
			System.err.println(e);
			return swriter+="--> Ocurrio un error al cargar el archivo.\n" + e;			
		}
		return swriter;
	}

}

