package com.gonet.horariosescalonados.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable (catalog = "horariosescalonadosv2", table = "cumplimiento")
public class BeanCumplimiento {
	
	@PrimaryKey
	@Persistent
	@Column(name="empleadoID")
	private String empleadoID;
	
	@Persistent
	@Column(name="apePaterno")
	private String apePaterno;
	
	@Persistent
	@Column(name="apeMaterno")
	private String apeMaterno;
	
	@Persistent
	@Column(name="nombre")
	private String nombre;
	
	@Persistent
	@Column(name="nombreCR")
	private String nombreCR;
	
	@Persistent
	@Column(name="dga")
	private String dga;
	
	@Persistent
	@Column(name="fecha")
	private Date fecha;
	
	@Persistent
	@Column(name="quincena")
	private String quincena;
	
	@Persistent
	@Column(name="mes")
	private String mes;
	
	@Persistent
	@Column(name="tae")
	private String tae;
	
	@Persistent
	@Column(name="entrada")
	private String entrada;
	
	@Persistent
	@Column(name="tde")
	private String tde;
	
	@Persistent
	@Column(name="entradaReal")
	private String entradaReal;
	
	@Persistent
	@Column(name="califEntrada")
	private String califEntrada;
	
	@Persistent
	@Column(name="tas")
	private String tas;
	
	@Persistent
	@Column(name="salida")
	private String salida;
	
	@Persistent
	@Column(name="tds")
	private String tds;
	
	@Persistent
	@Column(name="salidaReal")
	private String salidaReal;
	
	@Persistent
	@Column(name="califSalida")
	private String califSalida;
	
	@Persistent
	@Column(name="jornada")
	private String jornada;
	
	@Persistent
	@Column(name="total")
	private String total;
	
	@Persistent
	@Column(name="porcentaje")
	private String porcentaje;
	
	public BeanCumplimiento(){}
	
	public BeanCumplimiento(String empleadoID, String apePaterno, String apeMaterno, String nombre, String nombreCR,
			String dga, Date fecha, String quincena, String mes, String tae, String entrada, String tde,
			String entradaReal, String califEntrada, String tas, String salida, String tds, String salidaReal,
			String califSalida, String jornada, String total, String porcentaje) {
		super();
		this.empleadoID = empleadoID;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.nombre = nombre;
		this.nombreCR = nombreCR;
		this.dga = dga;
		this.fecha = fecha;
		this.quincena = quincena;
		this.mes = mes;
		this.tae = tae;
		this.entrada = entrada;
		this.tde = tde;
		this.entradaReal = entradaReal;
		this.califEntrada = califEntrada;
		this.tas = tas;
		this.salida = salida;
		this.tds = tds;
		this.salidaReal = salidaReal;
		this.califSalida = califSalida;
		this.jornada = jornada;
		this.total = total;
		this.porcentaje = porcentaje;
	}
	
	public BeanCumplimiento(ResultSet resultSet) throws SQLException{
		this.empleadoID = resultSet.getString("empleadoID");
		this.apePaterno = resultSet.getString("apePaterno");
		this.apeMaterno = resultSet.getString("apeMaterno");
		this.nombre = resultSet.getString("nombre");
		this.nombreCR = resultSet.getString("nombreCR");
		this.dga = resultSet.getString("dga");
		this.fecha = resultSet.getDate("fecha");
		this.quincena = resultSet.getString("quincena");
		this.mes = resultSet.getString("mes");
		this.tae = resultSet.getString("tae");
		this.entrada = resultSet.getString("entrada");
		this.tde = resultSet.getString("tde");
		this.entradaReal = resultSet.getString("entradaReal");
		this.califEntrada = resultSet.getString("califEntrada");
		this.tas = resultSet.getString("tas");
		this.salida = resultSet.getString("salida");
		this.tds = resultSet.getString("tds");
		this.salidaReal = resultSet.getString("salidaReal");
		this.califSalida = resultSet.getString("califSalida");
		this.jornada = resultSet.getString("jornada");
		this.total = resultSet.getString("total");
		this.porcentaje = resultSet.getString("porcentaje");		
	}
	
	

	@Override
	public String toString() {
		return "<tr class='tblSeguimientoI1'>"
				+ "<td>"+apePaterno +" "+ apeMaterno +" "+ nombre + "</td>"
				+ "<td>"+empleadoID +"</td>"
				+ "<td>"+nombreCR + "</td>" 
				+ "<td>"+dga + "</td>" 
				+ "<td>"+fecha + "</td>" 
				+ "<td>"+quincena + "</td>"
				+ "<td>"+mes + "</td>" 
				+ "<td>"+tae + "</td>" 
				+ "<td>"+entrada + "</td>"
				+ "<td>"+tde + "</td>" 
				+ "<td>"+entradaReal + "</td>"
				+ "<td>"+califEntrada + "</td>" 
				+ "<td>"+tas + "</td>" 
				+ "<td>"+salida + "</td>" 
				+ "<td>"+tds + "</td>" 
				+ "<td>"+salidaReal + "</td>" 
				+ "<td>"+califSalida + "</td>" 
				+ "<td>"+jornada + "</td>" 
				+ "<td>"+total + "</td>" 
				+ "<td>"+porcentaje+ "</td>" 
				+ "</tr>";

	}

	public String getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(String empleadoID) {
		this.empleadoID = empleadoID;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCR() {
		return nombreCR;
	}

	public void setNombreCR(String nombreCR) {
		this.nombreCR = nombreCR;
	}

	public String getDga() {
		return dga;
	}

	public void setDga(String dga) {
		this.dga = dga;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getQuincena() {
		return quincena;
	}

	public void setQuincena(String quincena) {
		this.quincena = quincena;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getTae() {
		return tae;
	}

	public void setTae(String tae) {
		this.tae = tae;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getTde() {
		return tde;
	}

	public void setTde(String tde) {
		this.tde = tde;
	}

	public String getEntradaReal() {
		return entradaReal;
	}

	public void setEntradaReal(String entradaReal) {
		this.entradaReal = entradaReal;
	}

	public String getCalifEntrada() {
		return califEntrada;
	}

	public void setCalifEntrada(String califEntrada) {
		this.califEntrada = califEntrada;
	}

	public String getTas() {
		return tas;
	}

	public void setTas(String tas) {
		this.tas = tas;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getSalidaReal() {
		return salidaReal;
	}

	public void setSalidaReal(String salidaReal) {
		this.salidaReal = salidaReal;
	}

	public String getCalifSalida() {
		return califSalida;
	}

	public void setCalifSalida(String califSalida) {
		this.califSalida = califSalida;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public String toStringA() {
		return "BeanCumplimiento [empleadoID=" + empleadoID + ", apePaterno="
				+ apePaterno + ", apeMaterno=" + apeMaterno + ", nombre="
				+ nombre + ", nombreCR=" + nombreCR + ", dga=" + dga
				+ ", fecha=" + fecha + ", quincena=" + quincena + ", mes="
				+ mes + ", tae=" + tae + ", entrada=" + entrada + ", tde="
				+ tde + ", entradaReal=" + entradaReal + ", califEntrada="
				+ califEntrada + ", tas=" + tas + ", salida=" + salida
				+ ", tds=" + tds + ", salidaReal=" + salidaReal
				+ ", califSalida=" + califSalida + ", jornada=" + jornada
				+ ", total=" + total + ", porcentaje=" + porcentaje + "]";
	}	
	
	
}

//return  apePaterno +" "+ apeMaterno +" "+ nombre + "\t"
//+ empleadoID +"\t"
//+ nombreCR + "\t" 
//+ dga + "\t" 
//+ fecha + "\t" 
//+ quincena + "\t"
//+ mes + "\t" 
//+ tae + "\t" 
//+ entrada + "\t"
//+ tde + "\t" 
//+ entradaReal + "\t"
//+ califEntrada + "\t" 
//+ tas + "\t" 
//+ salida + "\t" 
//+ tds + "\t" 
//+ salidaReal + "\t" 
//+ califSalida + "\t" 
//+ jornada + "\t" 
//+ total + "\t" 
//+ porcentaje;
