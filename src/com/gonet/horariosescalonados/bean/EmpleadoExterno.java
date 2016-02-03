package com.gonet.horariosescalonados.bean;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.gonet.horariosescalonados.interfaces.Archivo;
import com.google.appengine.api.users.UserServiceFactory;

@PersistenceCapable(catalog = "horariosescalonadosv2")
public class EmpleadoExterno implements Archivo{

															//PROPIEDADES
	
	@PrimaryKey
	@Persistent (name = "NoCyge")
	private String noCyge;

	@Persistent (name = "Usuario")
	private String usuario;
	
	@Persistent (name = "Nombre")
	private String nombre;
	
	@Persistent (name = "ApePaterno")
	private String apePaterno;
	
	@Persistent (name = "ApeMaterno")
	private String apeMaterno;
	
	@Persistent (name = "DirGeneral")
	private String dirGeneral;
	
	@Persistent (name = "DirCorporativa")
	private String dirCorporativa;
	
	@Persistent (name = "Area")
	private String area;
	
	@Persistent (name = "EntOficial")
	private String entOficial;
	
	@Persistent (name = "AutorizadorID")
	private String autorizadorId;
	
	@Persistent (name = "Autorizador")
	private String autorizador;
	
	@Persistent (name = "Proveedor")
	private String proveedor;
	
	@Persistent (name = "Proyecto")
	private String proyecto;
	
	@Persistent (name = "Estatus")
	private String estatus;
	
	@Persistent (name = "Creadoen")
	private String creadoEn;
	
	@Persistent (name = "Creadopor")
	private String creadoPor;
	
	@Persistent (name = "EstatusArchivo")
	private String estatusArchivo;
	
	@Persistent (name = "EspacioFisico")
	private String espacioFisico;
	
	@Persistent (name = "Edificio")
	private String edificio;
	
															//CONSTRUCTORES
	
	public EmpleadoExterno()
	{
		
	}
	
	public EmpleadoExterno(String [] arrstrValores)
	{
		this.noCyge = arrstrValores[0];
		this.usuario = arrstrValores[1];
		this.nombre = arrstrValores[2];
		this.apePaterno = arrstrValores[3];
		this.apeMaterno = arrstrValores[4];
		this.dirGeneral = arrstrValores[5];
		this.dirCorporativa = arrstrValores[6];
		this.area = arrstrValores[7];
		this.entOficial = arrstrValores[8];
		this.autorizadorId = arrstrValores[9];
		this.autorizador = arrstrValores[10];
		this.proveedor = arrstrValores[11];
		this.proyecto = arrstrValores[12];
		this.estatus = arrstrValores[13];
		this.creadoPor = UserServiceFactory.getUserService().getCurrentUser().getUserId();
		//this.strCreadoEn = tiempo.fechas();
		this.estatusArchivo = "Activo";
		this.espacioFisico = arrstrValores[14];
		this.edificio = arrstrValores[15];
	}

				
															//GETTERS Y SETTERS
	
	
	public String getStrNoCyge() {
		return noCyge;
	}

	public void setStrNoCyge(String strNoCyge) {
		this.noCyge = strNoCyge;
	}

	public String getStrUsuario() {
		return usuario;
	}

	public void setStrUsuario(String strUsuario) {
		this.usuario = strUsuario;
	}

	public String getStrNombre() {
		return nombre;
	}

	public void setStrNombre(String strNombre) {
		this.nombre = strNombre;
	}

	public String getStrApePaterno() {
		return apePaterno;
	}

	public void setStrApePaterno(String strApePaterno) {
		this.apePaterno = strApePaterno;
	}

	public String getStrApeMaterno() {
		return apeMaterno;
	}

	public void setStrApeMaterno(String strApeMaterno) {
		this.apeMaterno = strApeMaterno;
	}

	public String getStrDirGeneral() {
		return dirGeneral;
	}

	public void setStrDirGeneral(String strDirGeneral) {
		this.dirGeneral = strDirGeneral;
	}

	public String getStrDirCorporativa() {
		return dirCorporativa;
	}

	public void setStrDirCorporativa(String strDirCorporativa) {
		this.dirCorporativa = strDirCorporativa;
	}

	public String getStrArea() {
		return area;
	}

	public void setStrArea(String strArea) {
		this.area = strArea;
	}

	public String getStrEntOficial() {
		return entOficial;
	}

	public void setStrEntOficial(String strEntOficial) {
		this.entOficial = strEntOficial;
	}

	public String getStrAutorizadorId() {
		return autorizadorId;
	}

	public void setStrAutorizadorId(String strAutorizadorId) {
		this.autorizadorId = strAutorizadorId;
	}

	public String getStrAutorizador() {
		return autorizador;
	}

	public void setStrAutorizador(String strAutorizador) {
		this.autorizador = strAutorizador;
	}

	public String getStrProveedor() {
		return proveedor;
	}

	public void setStrProveedor(String strProveedor) {
		this.proveedor = strProveedor;
	}

	public String getStrProyecto() {
		return proyecto;
	}

	public void setStrProyecto(String strProyecto) {
		this.proyecto = strProyecto;
	}

	public String getStrEstatus() {
		return estatus;
	}

	public void setStrEstatus(String strEstatus) {
		this.estatus = strEstatus;
	}

	public String getStrCreadoEn() {
		return creadoEn;
	}

	public void setStrCreadoEn(String strCreadoEn) {
		this.creadoEn = strCreadoEn;
	}

	public String getStrCreadoPor() {
		return creadoPor;
	}

	public void setStrCreadoPor(String strCreadoPor) {
		this.creadoPor = strCreadoPor;
	}

	public String getStrEstatusArchivo() {
		return estatusArchivo;
	}

	public void setStrEstatusArchivo(String strEstatusArchivo) {
		this.estatusArchivo = strEstatusArchivo;
	}

	public String getEspacioFisico() {
		return espacioFisico;
	}

	public void setEspacioFisico(String espacioFisico) {
		this.espacioFisico = espacioFisico;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	@Override
	public void VerificarArchivo(String[] lineas) {
		// TODO Auto-generated method stub
		
	}
	
}
