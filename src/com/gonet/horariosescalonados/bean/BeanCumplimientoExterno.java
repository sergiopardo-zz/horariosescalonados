package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.gonet.horariosescalonados.util.HeConstantes;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.NotPersistent;


@PersistenceCapable (catalog = "horariosescalonadosv2", table = "cumplimientoExterno")
public class BeanCumplimientoExterno {

	@PrimaryKey
	@Persistent
	@Column(name="usuario")
	private String strUsuario;

	@Persistent
	@Column(name="ncyge")
	private String strNoCyge;

	@Persistent
	@Column(name="nombre")
	private String strNombre;
	
	@Persistent
	@Column(name="direccionGeneral")
	private String strDireccionGeneral;

	@Persistent
	@Column(name="direccionCorporativa")
	private String strDireccionCorporativa;

	@Persistent
	@Column(name="area")
	private String strArea;

	@Persistent
	@Column(name="fecha")
	private String strFecha;

	@Persistent
	@Column(name="mes")
	private String strMes;
	
	@Persistent
	@Column(name="quincena")
	private String strQuincena;

	@Persistent
	@Column(name="entradaOficial")
	private String strEntradaOficial;

	@Persistent
	@Column(name="entradaReal")
	private String strEntradaReal;

	@Persistent
	@Column(name="salidaReal")
	private String strSalidaReal;

	@Persistent
	@Column(name="jornada")
	private String strJornada;

	@Persistent
	@Column(name="estancia")
	private String strEstancia;

	@Persistent
	@Column(name="edificio")
	private String strEdificio;

	@Persistent
	@Column(name="autorizador")
	private String strAutorizador;

	@Persistent
	@Column(name="provedor")
	private String strProveedor;

	@Persistent
	@Column(name="proyecto")
	private String strProyecto;

	@Persistent
	@Column(name="estatus")
	private String strEstatus;

	@Persistent
	@Column(name="edificioAsignado")
	private String strEdificioAsignado;


	public BeanCumplimientoExterno(){
		super();
	}


	public BeanCumplimientoExterno(String strUsuario,String strNoCyge,String strNombre,
			String strDireccionGeneral,String strDireccionCorporativa,String strArea,
			String strFecha,String strMes,String strQuincena,String entradaOficial,String entradaReal,
			String strsalidaReal, String strJornada,String strEstancia,String strEdificio,String strAutorizador,
			String strProveedor,String strProyecto,String strEstatus,String strEdificioAsignado){
		super();
		this.strUsuario = strUsuario;
		this.strNoCyge= strNoCyge;
		this.strNombre=strNombre;
		this.strDireccionGeneral = strDireccionGeneral;
		this.strDireccionCorporativa = strDireccionCorporativa;
		this.strArea=strArea;
		this.strFecha=strFecha;
		this.strMes=strMes;
		this.strQuincena=strQuincena;
		this.strEntradaOficial= entradaOficial;
		this.strEntradaReal=entradaReal;
		this.strSalidaReal=strsalidaReal;
		this.strJornada=strJornada;
		this.strEstancia=strEstancia;
		this.strEdificio=strEdificio;
		this.strAutorizador=strAutorizador;
		this.strProveedor=strProveedor;
		this.strProyecto=strProyecto;
		this.strEstatus=strEstatus;
		this.strEdificioAsignado=strEdificioAsignado;
		}

	public BeanCumplimientoExterno(ResultSet resultSet) throws SQLException{
		this.strUsuario = resultSet.getString("usuario");
		this.strNoCyge = resultSet.getString("ncyge"); 
		this.strNombre = resultSet.getString("nombre");
		this.strDireccionGeneral = resultSet.getString("direccionGeneral");
		this.strDireccionCorporativa = resultSet.getString("direccionCorporativa");
		this.strArea = resultSet.getString("area");
		this.strFecha = resultSet.getString("fecha");
		this.strMes = resultSet.getString("mes");
		this.strQuincena = resultSet.getString("quincena");
		this.strEntradaOficial = resultSet.getString("entradaOficial");
		this.strEntradaReal = resultSet.getString("entradaReal");	
		this.strSalidaReal = resultSet.getString("salidaReal");
		this.strJornada = resultSet.getString("jornada");
		this.strEstancia = resultSet.getString("estancia");
		this.strEdificio = resultSet.getString("edificio");
		this.strAutorizador = resultSet.getString("autorizador");
		this.strProveedor = resultSet.getString("provedor");
		this.strProyecto = resultSet.getString("proyecto");
		this.strEstatus = resultSet.getString("estatus");
		this.strEdificioAsignado = resultSet.getString("edificioAsignado");		
	}


	public BeanCumplimientoExterno(Object[] arrobj)
	{
		this.strNoCyge = (String) arrobj[0]; 
		this.strUsuario = (String) arrobj[1]; 
		/*this.strNombre = resultSet.getString("nombre");
			this.strDireccionGeneral = resultSet.getString("direccionGeneral");
			this.strDireccionCorporativa = resultSet.getString("direccionCorporativa");
			this.strArea = resultSet.getString("area");
			this.strFecha = resultSet.getString("fecha");
			this.strQuincena = resultSet.getString("quincena");
			this.strMes = resultSet.getString("mes");
			this.strEntradaOficial = resultSet.getString("entradaOficial");
			this.strEntradaReal = resultSet.getString("entradaReal");		
			this.strJornada = resultSet.getString("jornada");
			this.strEstancia = resultSet.getString("estancia");
			this.strEdificio = resultSet.getString("edificio");
			this.strAutorizador = resultSet.getString("autorizador");
			this.strProveedor = resultSet.getString("proveedor");
			this.strProyecto = resultSet.getString("proyecto");
			this.strEstatus = resultSet.getString("estatus");
			this.strEdificioAsignado = resultSet.getString("creadoen");	

		 */
	}


	@Override
	public String toString() {
		return "<tr class='tblSeguimientoI2'>"
				+ "<td>"+ strUsuario + "</td>"
				+ "<td>"+ strNoCyge +"</td>"
				+ "<td>"+ strNombre + "</td>"
				+ "<td>"+ strDireccionGeneral + "</td>" 
				+ "<td>"+ strDireccionCorporativa + "</td>" 
				+ "<td>"+ strArea + "</td>"
				+ "<td>"+ strFecha + "</td>"  
				+ "<td>"+ strMes + "</td>"
				+ "<td>"+ strQuincena + "</td>"
				+ "<td>"+ strEntradaOficial + "</td>"
				+ "<td>"+ strEntradaReal + "</td>"
				+ "<td>"+ strSalidaReal + "</td>"
				+ "<td>"+ strJornada + "</td>"
				+ "<td>"+ strEstancia + "</td>"
				+ "<td>"+ strEdificio + "</td>"
				+ "<td>"+ strAutorizador + "</td>"
				+ "<td>"+ strProveedor + "</td>" 
				+ "<td>"+ strProyecto + "</td>"
				+ "<td>"+ strEstatus + "</td>" 
				+ "<td>"+ strEdificioAsignado + "</td>"
				+ "</tr>";
	}
	public String getStrUsuario() {
		return strUsuario;
	}


	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}


	public String getStrNoCyge() {
		return strNoCyge;
	}


	public void setStrNoCyge(String strNoCyge) {
		this.strNoCyge = strNoCyge;
	}


	public String getStrNombre() {
		return strNombre;
	}


	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}


	public String getStrDireccionGeneral() {
		return strDireccionGeneral;
	}


	public void setStrDireccionGeneral(String strDireccionGeneral) {
		this.strDireccionGeneral = strDireccionGeneral;
	}


	public String getStrDireccionCorporativa() {
		return strDireccionCorporativa;
	}


	public void setStrDireccionCorporativa(String strDireccionCorporativa) {
		this.strDireccionCorporativa = strDireccionCorporativa;
	}


	public String getStrArea() {
		return strArea;
	}


	public void setStrArea(String strArea) {
		this.strArea = strArea;
	}


	public String getStrFecha() {
		return strFecha;
	}


	public void setStrFecha(String strFecha) {
		this.strFecha = strFecha;
	}


	public String getStrMes() {
		return strMes;
	}


	public void setStrMes(String strMes) {
		this.strMes = strMes;
	}


	public String getStrQuincena() {
		return strQuincena;
	}


	public void setStrQuincena(String strQuincena) {
		this.strQuincena = strQuincena;
	}


	public String getStrEntradaOficial() {
		return strEntradaOficial;
	}


	public void setStrEntradaOficial(String strEntradaOficial) {
		this.strEntradaOficial = strEntradaOficial;
	}


	public String getStrEntradaReal() {
		return strEntradaReal;
	}


	public void setStrEntradaReal(String strEntradaReal) {
		this.strEntradaReal = strEntradaReal;
	}


	public String getStrSalidaReal() {
		return strSalidaReal;
	}


	public void setStrSalidaReal(String strSalidaReal) {
		this.strSalidaReal = strSalidaReal;
	}


	public String getStrJornada() {
		return strJornada;
	}


	public void setStrJornada(String strJornada) {
		this.strJornada = strJornada;
	}


	public String getStrEstancia() {
		return strEstancia;
	}


	public void setStrEstancia(String strEstancia) {
		this.strEstancia = strEstancia;
	}


	public String getStrEdificio() {
		return strEdificio;
	}


	public void setStrEdificio(String strEdificio) {
		this.strEdificio = strEdificio;
	}


	public String getStrAutorizador() {
		return strAutorizador;
	}


	public void setStrAutorizador(String strAutorizador) {
		this.strAutorizador = strAutorizador;
	}


	public String getStrProveedor() {
		return strProveedor;
	}


	public void setStrProveedor(String strProveedor) {
		this.strProveedor = strProveedor;
	}


	public String getStrProyecto() {
		return strProyecto;
	}


	public void setStrProyecto(String strProyecto) {
		this.strProyecto = strProyecto;
	}


	public String getStrEstatus() {
		return strEstatus;
	}


	public void setStrEstatus(String strEstatus) {
		this.strEstatus = strEstatus;
	}


	public String getStrEdificioAsignado() {
		return strEdificioAsignado;
	}


	public void setStrEdificioAsignado(String strEdificioAsignado) {
		this.strEdificioAsignado = strEdificioAsignado;
	}

	
	
	
	
/*	public void setentrada(String strEntradaReal){
		if (strEntradaReal == null)
		{
			this.strEntradaReal = HeConstantes.DATO_INVALIDO_REPORTE;
		}
		else
		{
		this.strEntradaReal=strEntradaReal;
		}
	}*/

}
