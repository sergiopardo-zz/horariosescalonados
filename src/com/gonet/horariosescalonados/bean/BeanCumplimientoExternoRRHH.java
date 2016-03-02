package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.*;

import com.gonet.horariosescalonados.util.HeConstantes;

@PersistenceCapable (catalog = "horariosescalonadosv2", table = "CumplimientoExternoRRHH")
public class BeanCumplimientoExternoRRHH {

	@PrimaryKey
	@Persistent
	@Column(name="usuario")
	private String Usuario;
	
	@Persistent
	@Column(name="nombre")
	private String Nombre;
	
	@Column(name="CRDireccionGeneral")
	private String CRDireccionGeneral;
	
	@Column(name = "DireccionGeneral")
	private String DireccionGeneral;

	@Column(name = "CRDireccionCorporativa")
	private String CRDireccionCorporativa;
	
	@Column(name = "DireccionCorporativa")
	private String DireccionCorporativa;

	@Column(name = "CRArea")
	private String CRArea;
	
	@Column(name = "Area")
	private String Area;

	@PrimaryKey
	@Column(name = "fecha")
	private Date Fecha;

	@Column(name = "Quincena")
	private String Quincena;

	@Column(name="Mes")
	private String Mes;

	@Column(name="TEA")
	private String TEA;
	
	@Column(name="EntOficial")
	private String EntradaOficial;

	@Column(name="TED")
	private String TED;
	
	@Column(name="entradaReal")
	private String EntradaReal;
	
	@Column (name = "CalificacionEntrada")
	private String CalificacionEntrada;
	
	@Column (name = "TSA")
	private String TSA;

	@Column (name ="salidaOficial")
	private String SalidaOficial;
	
	@Column (name = "TSD")
	private String TSD;
	
	@Column(name="salidaReal")
	private String SalidaReal;

	@Column (name ="CalificacionSalida")
	private String CalificacionSalida;
	
	@Column(name="Jornada")
	private String Jornada;

	@Column (name ="CalificacionJornada")
	private String CalificacionJornada;

	@Column (name ="CalificacionTotal")
	private String CalificacionTotal;
	
	@Column (name = "PorcentajeCumplimiento")
	private String PorcentajeCumplimiento;
	
 	@Column (name = "EdificioAsignado")
	private String EdificioAsignado;

	


	public BeanCumplimientoExternoRRHH(){
		super();
	}


	public BeanCumplimientoExternoRRHH(String strUsuario, String strNombre, 
			String strDireccionGeneral, String strDireccionCorporativa, 
			String strArea, Date strFecha, String entradaOficial, String entradaReal, 
			String strSalidaReal/*, String Edificio*/)
	{
		super();
		this.Usuario = strUsuario;
		this.Nombre=strNombre;
		this.DireccionGeneral = strDireccionGeneral;
		this.DireccionCorporativa = strDireccionCorporativa;
		this.Area=strArea;
		this.Fecha=strFecha;
		this.EntradaOficial= entradaOficial;
		this.EntradaReal = (entradaReal == null)?HeConstantes.DATO_INVALIDO_REPORTE:entradaReal;
		this.SalidaReal = (strSalidaReal == null)?HeConstantes.DATO_INVALIDO_REPORTE:strSalidaReal;
		//this.EdificioAsignado=Edificio;
		//this.strJornada=strJornada;
	}

	
	public BeanCumplimientoExternoRRHH(String usuario, String nombre, String cRDireccionGeneral,
			String direccionGeneral, String cRDireccionCorporativa, String direccionCorporativa, String cRArea,
			String area, Date fecha, String quincena, String mes, String tEA, String entradaOficial, String tED,
			String entradaReal, String calificacionEntrada, String tSA, String salidaOficial, String tSD,
			String salidaReal, String calificacionSalida, String jornada, String calificacionJornada, String calificacionTotal,
			String porcentajeCumplimiento, String edificioAsignado) {
		super();
		Usuario = usuario;
		Nombre = nombre;
		CRDireccionGeneral = cRDireccionGeneral;
		DireccionGeneral = direccionGeneral;
		CRDireccionCorporativa = cRDireccionCorporativa;
		DireccionCorporativa = direccionCorporativa;
		CRArea = cRArea;
		Area = area;
		Fecha = fecha;
		Quincena = quincena;
		Mes = mes;
		TEA = tEA;
		EntradaOficial = entradaOficial;
		TED = tED;
		EntradaReal  = (entradaReal == null)?HeConstantes.DATO_INVALIDO_REPORTE:entradaReal;
		CalificacionEntrada = calificacionEntrada;
		TSA = tSA;
		SalidaOficial = salidaOficial;
		TSD = tSD;
		SalidaReal =(salidaReal == null)?HeConstantes.DATO_INVALIDO_REPORTE:salidaReal;
		CalificacionSalida = calificacionSalida;
		Jornada = (jornada == null)?HeConstantes.DATO_INVALIDO_REPORTE :jornada;;
		CalificacionJornada = calificacionJornada;
		CalificacionTotal = calificacionTotal;
		PorcentajeCumplimiento = porcentajeCumplimiento;
		EdificioAsignado = edificioAsignado;
	}
	
	public BeanCumplimientoExternoRRHH(ResultSet resultSet) throws SQLException{
		//this.strNoCyge = resultSet.getString("noCyge"); 
		this.Usuario = resultSet.getString("usuario");
		this.Nombre = resultSet.getString("nombre");
		this.DireccionGeneral = resultSet.getString("direccionGeneral");
		this.DireccionCorporativa = resultSet.getString("direccionCorporativa");
		this.Area = resultSet.getString("area");
		this.Fecha = resultSet.getDate("fecha");
		this.Quincena = resultSet.getString("quincena");
		this.Mes = resultSet.getString("mes");
		this.EntradaOficial = resultSet.getString("entradaOficial");
		this.EntradaReal = resultSet.getString("entradaReal");		
		this.Jornada = resultSet.getString("jornada");
	//	this.EdificioAsignado = resultSet.getString("EdificioAsignado");
	}


	public BeanCumplimientoExternoRRHH(Object[] arrobj)
	{
		//this.strNoCyge = (String) arrobj[0]; 
		this.Usuario = (String) arrobj[1]; 
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

	public String getUsuario() {
		return Usuario;
	}


	public void setUsuario(String strUsuario) {
		this.Usuario = strUsuario;
	}

	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String strNombre) {
		this.Nombre = strNombre;
	}


	public String getCRDireccionGeneral() {
		return CRDireccionGeneral;
	}


	public void setCRDireccionGeneral(String strCRDireccionGeneral) {
		this.CRDireccionGeneral = strCRDireccionGeneral;
	}


	public String getDireccionGeneral() {
		return DireccionGeneral;
	}


	public void setDireccionGeneral(String strDireccionGeneral) {
		this.DireccionGeneral = strDireccionGeneral;
	}


	public String getCRDireccionCorporativa() {
		return CRDireccionCorporativa;
	}


	public void setCRDireccionCorporativa(String strCRDireccionCorporativa) {
		this.CRDireccionCorporativa = strCRDireccionCorporativa;
	}


	public String getDireccionCorporativa() {
		return DireccionCorporativa;
	}


	public void setDireccionCorporativa(String strDireccionCorporativa) {
		this.DireccionCorporativa = strDireccionCorporativa;
	}


	public String getCRArea() {
		return CRArea;
	}


	public void setCRArea(String strCRArea) {
		this.CRArea = strCRArea;
	}


	public String getArea() {
		return Area;
	}


	public void setArea(String strArea) {
		this.Area = strArea;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date strFecha) {
		this.Fecha = strFecha;
	}


	public String getQuincena() {
		return Quincena;
	}


	public void setQuincena(String strQuincena) {
		this.Quincena = strQuincena;
	}


	public String getMes() {
		return Mes;
	}


	public void setMes(String strMes) {
		this.Mes = strMes;
	}


	public String getTEA() {
		return TEA;
	}


	public void setTEA(String strTEA) {
		this.TEA = strTEA;
	}


	public String getEntradaOficial() {
		return EntradaOficial;
	}


	public void setEntradaOficial(String strEntradaOficial) {
		this.EntradaOficial = strEntradaOficial;
	}


	public String getTED() {
		return TED;
	}


	public void setTED(String strTED) {
		this.TED = strTED;
	}


	public String getEntradaReal() {
		return EntradaReal;
	}


	public void setEntradaReal(String strEntradaReal) {
		this.EntradaReal = strEntradaReal;
	}


	public String getCalificacionEntrada() {
		return CalificacionEntrada;
	}


	public void setCalificacionEntrada(String intCalificacionEntrada) {
		this.CalificacionEntrada = intCalificacionEntrada;
	}


	public String getTSA() {
		return TSA;
	}


	public void setTSA(String strTSA) {
		this.TSA = strTSA;
	}


	public String getSalidaOficial() {
		return SalidaOficial;
	}


	public void setSalidaOficial(String strSalidaOficial) {
		this.SalidaOficial = strSalidaOficial;
	}


	public String getTSD() {
		return TSD;
	}


	public void setTSD(String strTSD) {
		this.TSD = strTSD;
	}


	public String getSalidaReal() {
		return SalidaReal;
	}


	public void setSalidaReal(String strSalidaReal) {
		this.SalidaReal = strSalidaReal;
	}


	public String getCalificacionSalida() {
		return CalificacionSalida;
	}


	public void setCalificacionSalida(String intCalificacionSalida) {
		this.CalificacionSalida = intCalificacionSalida;
	}


	public String getJornada() {
		return Jornada;
	}


	public void setJornada(String strJornada) {
		this.Jornada = strJornada;
	}


	public String getCalificacionJornada() {
		return CalificacionJornada;
	}


	public void setCalificacionJornada(String intCalificacionJornada) {
		this.CalificacionJornada = intCalificacionJornada;
	}


	public String getCalificacionTotal() {
		return CalificacionTotal;
	}


	public void setCalificacionTotal(String intCalificacionTotal) {
		this.CalificacionTotal = intCalificacionTotal;
	}


	public String getPorcentajeCumplimiento() {
		return PorcentajeCumplimiento;
	}


	public void setPorcentajeCumplimiento(String lgPorcentajeCumplimiento) {
		this.PorcentajeCumplimiento = lgPorcentajeCumplimiento;
	}
	
	public String getEdificoAsignado() {
		return EdificioAsignado;
	}


	public void setEdificioAsignado(String edificioAsignado) {
		this.EdificioAsignado = edificioAsignado;
	}

	
	

	@Override
	public String toString() {
		return "<tr class='tblSeguimientoI2'>"
				+ "<td>"+ Usuario + "</td>" 
				+ "<td>"+ Nombre + "</td>"
				+ "<td>"+ CRDireccionGeneral + "</td>" 
				+ "<td>"+ DireccionGeneral + "</td>" 
				+ "<td>"+ CRDireccionCorporativa + "</td>" 
				+ "<td>"+ DireccionCorporativa + "</td>" 
				+ "<td>"+ CRArea + "</td>"
				+ "<td>"+ Area + "</td>"
				+ "<td>"+ Fecha + "</td>" 
				+ "<td>"+ Quincena + "</td>" 
				+ "<td>"+ Mes + "</td>"
				+ "<td>"+ TEA + "</td>"
				+ "<td>"+ EntradaOficial + "</td>"
				+ "<td>"+ TED + "</td>"
				+ "<td>"+ EntradaReal + "</td>"
				+ "<td>"+ CalificacionEntrada + "</td>"
				+ "<td>"+ TSA + "</td>"
				+ "<td>"+ SalidaOficial + "</td>"
				+ "<td>"+ TSD + "</td>"
				+ "<td>"+ SalidaReal + "</td>"
				+ "<td>"+ CalificacionSalida + "</td>"
				+ "<td>"+ Jornada + "</td>"
				+ "<td>"+ CalificacionJornada + "</td>"
				+ "<td>"+ CalificacionTotal + "</td>"
				+ "<td>"+ PorcentajeCumplimiento + "</td>"
				+ "<td>"+ EdificioAsignado + "</td>"
				+ "</tr>";
	}

}
