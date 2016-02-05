package com.gonet.horariosescalonados.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.gonet.horariosescalonados.interfaces.Archivo;


@PersistenceCapable (catalog = "horariosescalonadosv2", table = "Zeit")

public class BeanZeit implements Archivo{
	
	//@Column (name = "id")
	//private int intId;
	
	@Persistent
	@Column (name = "NoCyGE")
	private String strNoCyge;
	
	@Persistent
	@Column (name = "Nombre")
	private String strSubcontrataNombre;
	
	@PrimaryKey
	@Persistent
	@Column (name = "Fecha")
	private Date strFecha;
	
	@Persistent
	@Column (name = "Hora")
	private String strHora;
	
	@Persistent
	@Column (name = "TipoFuncion")
	private String strTipoFuncion;
	
	@Persistent
	@Column (name = "NombreLector")
	private String strNombreLector;
	
	@Persistent
	@Column (name = "Edificio")
	private String strEdificio;
	
	@Persistent
	@Column (name = "Estancia")
	private String strEstancia;
	
	//@Transient 
	//protected Object[] jdoDetachedState; 

	
	public BeanZeit(String [] arrayZeit){
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		this.strNoCyge = arrayZeit[0];
		this.strSubcontrataNombre = arrayZeit[1];
		try {
			this.strFecha = formatter.parse(arrayZeit[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.strHora = arrayZeit[3];
		this.strTipoFuncion = arrayZeit[4];
		this.strNombreLector = arrayZeit[5];
		this.strEdificio = arrayZeit[6];
		this.strEstancia = arrayZeit[7];
	}
	
	public BeanZeit( String strNoCyge, String strSubcontrataNombre, Date strFecha, String strHora, String strTipoFuncion,
			String strNombreLector, String strEdificio, String strEstancia){
		super();
		this.strNoCyge = strNoCyge;
		this.strSubcontrataNombre = strSubcontrataNombre;
		this.strFecha = strFecha;
		this.strHora = strHora;
		this.strTipoFuncion = strTipoFuncion;
		this.strNombreLector = strNombreLector;
		this.strEdificio = strEdificio;
		this.strEstancia = strEstancia;
	}

	public BeanZeit() {
		// TODO Auto-generated constructor stub
	}

	public void setnoCyge (String strNoCyge){
		this.strNoCyge = strNoCyge;
	}
	
	public String getnoCyge(){
		return strNoCyge;
	}
	
	public void setsubcontrataNombre (String strSubcontrataNombre){
		this.strSubcontrataNombre = strSubcontrataNombre;
	}
	
	public String getsubcontrataNombre(){
		return strSubcontrataNombre;
	}
	
	public void setfecha(Date strFecha){
		this.strFecha = strFecha;
	}
	
	public Date getfecha(){

		return strFecha;
	}
	
	public void sethora(String strHora){
		this.strHora = strHora;
	}
	
	public String gethora(){
		return strHora;
	}
	
	public void settipoFuncion(String strTipoFuncion){
		this.strTipoFuncion = strTipoFuncion;
	}
	
	public String gettipoFuncion(){
		return strTipoFuncion;
	}
	
	public void setnombreLector(String strNombreLector){
		this.strNombreLector = strNombreLector;
	}
	
	public String getnombreLector(){
		return strNombreLector;
	}
	
	public void setedificio (String strEdificio){
		this.strEdificio = strEdificio;
	}
	
	public String getedificio(){
		return strEdificio;
	}

	public void  setestancia (String strEstancia){
		this.strEstancia = strEstancia;
	}
	
	public String getestancia(){
		return strEstancia;
	}
	
	@Override
	public String toString() {
		return "Zeit[ noCyge =" + strNoCyge +", subcontrataNombre =" + strSubcontrataNombre + ", fecha = " + strFecha + ", tipoFunsion = " 
				+ strTipoFuncion +", nombreLector = " + strNombreLector +", edificio = " + strEdificio + ", estancia = " + strEstancia +"]";
	}

	@Override
	public void VerificarArchivo(String [] arrayZeit){
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		this.strNoCyge = arrayZeit[0];
		this.strSubcontrataNombre = arrayZeit[1];
		try {
			this.strFecha = formatter.parse(arrayZeit[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.strHora = arrayZeit[3];
		this.strTipoFuncion = arrayZeit[4];
		this.strNombreLector = arrayZeit[5];
		this.strEdificio = arrayZeit[6];
		this.strEstancia = arrayZeit[7];
	}

}
