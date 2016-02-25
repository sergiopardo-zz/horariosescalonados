package com.gonet.horariosescalonados.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Column;


import com.gonet.horariosescalonados.classes.Fecha;
import com.gonet.horariosescalonados.interfaces.Archivo;
import com.google.appengine.api.users.UserServiceFactory;


@PersistenceCapable(objectIdClass=BeanCyge.ComposedIdKey.class,catalog = "horariosescalonadosv2", table = "Cyge" )
public class BeanCyge implements Archivo {
	
		@PrimaryKey
		@Persistent (name = "noCyge")
		private String NoCyge;
		
		@Persistent (name = "usuario")
		private String Usuario;
		
		@Persistent (name = "nombre")
		private String Nombre;
		
		@Persistent (name = "apePaterno")
		private String ApePaterno;
		
		@Persistent (name = "apeMaterno")
		private String ApeMaterno;
		
		@Persistent (name = "dirGeneral")
		private String DirGeneral;
				
		@Persistent
		@Column (name = "DirCorporativa")
		private String DirCorporativa;
		
		@Persistent
		@Column (name = "Area")
		private String Area;
		
		@Persistent
		@Column (name = "EntOficial")
		private String EntOficial;
		
		@Persistent
		@Column (name = "AutorizadorID")
		private String AutorizadorId;
		
		@Persistent
		@Column (name = "Autorizador")
		private String Autorizador;
		
		@Persistent
		@Column (name = "Proveedor")
		private String Proveedor;
		
		@Persistent
		@Column (name = "Proyecto")
		private String Proyecto;
		
		@Persistent
		@Column (name = "Estatus")
		private String Estatus;
		
		@Persistent
		@Column (name = "FechaCreacionRegistro")
		private Date FechaCreacionRegistro;
		
		@Persistent
		@Column (name = "Creadopor")
		private String CreadoPor;
		
		@Persistent
		@Column (name = "RegistroActivo")
		private String EstatusArchivo;
		
		@Persistent
		@Column (name = "EspacioFisico")
		private String EspacioFisico;
		
		@PrimaryKey
		@Persistent
		@Column (name = "LugarAsignadoEdificio")
		private String LugarAsignadoEdificio;
		
		@Persistent
		@Column (name = "Email")
		private String Email;
					
		public BeanCyge(Object []arrayArchivo){
			Fecha tiempo = new Fecha();
			
			this.NoCyge = (String) arrayArchivo[0];
			this.Usuario = (String) arrayArchivo[1];
			this.Nombre = (String) arrayArchivo[2];
			this.ApePaterno = (String) arrayArchivo[3];
			this.ApeMaterno = (String) arrayArchivo[4];
			this.DirGeneral = (String) arrayArchivo[5];
			this.DirCorporativa = (String) arrayArchivo[6];
			this.Area = (String) arrayArchivo[7];
			this.EntOficial = (String) arrayArchivo[8];
			this.AutorizadorId = (String) arrayArchivo[9];
			this.Autorizador = (String) arrayArchivo[10];
			this.Proveedor = (String) arrayArchivo[11];
			this.Proyecto = (String) arrayArchivo[12];
			this.Estatus = (String) arrayArchivo[13];
			this.EspacioFisico = (String) arrayArchivo[14];
			this.LugarAsignadoEdificio = (String) arrayArchivo[15];
			this.Email = (String) arrayArchivo[16];
			this.CreadoPor = " ";
			this.FechaCreacionRegistro = (Date)tiempo.fechas();
			this.EstatusArchivo = "Activo";
			
		}
			
		public BeanCyge(String noCyge, String usuario, String nombre, String apePaterno, String apeMaterno,
				String dirGeneral, String dirCorporativa, String area, String entOficial, String autorizadorId,
				String autorizador, String proveedor, String proyecto, String estatus, String espacioFisico, String lugarAsignadoEdificio,
				String strEmail, Date fechaCreacionRegistro, String creadoPor,String estatusArchivo) {
			super();
			NoCyge = noCyge;
			Usuario = usuario;
			Nombre = nombre;
			ApePaterno = apePaterno;
			ApeMaterno = apeMaterno;
			DirGeneral = dirGeneral;
			DirCorporativa = dirCorporativa;
			Area = area;
			EntOficial = entOficial;
			AutorizadorId = autorizadorId;
			Autorizador = autorizador;
			Proveedor = proveedor;
			Proyecto = proyecto;
			Estatus = estatus;
			EspacioFisico = espacioFisico;
			LugarAsignadoEdificio = lugarAsignadoEdificio;			
			Email = strEmail;
			FechaCreacionRegistro = fechaCreacionRegistro;
			CreadoPor = creadoPor;
			EstatusArchivo = estatusArchivo;
		    
		}

		public BeanCyge(ResultSet resultSet) throws SQLException{
			this.NoCyge = resultSet.getString("noCyge"); 
			this.Usuario = resultSet.getString("usuario");
			this.Nombre = resultSet.getString("nombre");
			this.ApePaterno = resultSet.getString("apePaterno");
			this.ApeMaterno = resultSet.getString("apeMaterno");
			this.DirGeneral = resultSet.getString("dirGeneral");
			this.DirCorporativa = resultSet.getString("dirCorporativa");
			this.Area = resultSet.getString("area");
			this.EntOficial = resultSet.getString("entOficial");
			this.AutorizadorId = resultSet.getString("autorizadorID");
			this.Autorizador = resultSet.getString("autorizador");
			this.Proveedor = resultSet.getString("proveedor");
			this.Proyecto = resultSet.getString("proyecto");
			this.Estatus = resultSet.getString("estatus");
			this.EspacioFisico = resultSet.getString("espacioFisico");
			this.LugarAsignadoEdificio = resultSet.getString("edificio");
			this.Email = resultSet.getString("Email");
		}
		
		public BeanCyge() 
		{
			
		}
		

		public String getNoCyge() {
			return NoCyge;
		}


		public void setNoCyge(String noCyge) {
			NoCyge = noCyge;
		}


		public String getUsuario() {
			return Usuario;
		}


		public void setUsuario(String usuario) {
			Usuario = usuario;
		}


		public String getNombre() {
			return Nombre;
		}


		public void setNombre(String nombre) {
			Nombre = nombre;
		}


		public String getApePaterno() {
			return ApePaterno;
		}


		public void setApePaterno(String apePaterno) {
			ApePaterno = apePaterno;
		}


		public String getApeMaterno() {
			return ApeMaterno;
		}


		public void setApeMaterno(String apeMaterno) {
			ApeMaterno = apeMaterno;
		}


		public String getDirGeneral() {
			return DirGeneral;
		}


		public void setDirGeneral(String dirGeneral) {
			DirGeneral = dirGeneral;
		}


		public String getDirCorporativa() {
			return DirCorporativa;
		}


		public void setDirCorporativa(String dirCorporativa) {
			DirCorporativa = dirCorporativa;
		}


		public String getArea() {
			return Area;
		}


		public void setArea(String area) {
			Area = area;
		}


		public String getEntOficial() {
			return EntOficial;
		}


		public void setEntOficial(String entOficial) {
			EntOficial = entOficial;
		}


		public String getAutorizadorId() {
			return AutorizadorId;
		}


		public void setAutorizadorId(String autorizadorId) {
			AutorizadorId = autorizadorId;
		}


		public String getAutorizador() {
			return Autorizador;
		}


		public void setAutorizador(String autorizador) {
			Autorizador = autorizador;
		}


		public String getProveedor() {
			return Proveedor;
		}


		public void setProveedor(String proveedor) {
			Proveedor = proveedor;
		}


		public String getProyecto() {
			return Proyecto;
		}


		public void setProyecto(String proyecto) {
			Proyecto = proyecto;
		}


		public String getEstatus() {
			return Estatus;
		}


		public void setEstatus(String estatus) {
			Estatus = estatus;
		}


		public Date getFechaCreacionRegistro() {
			return FechaCreacionRegistro;
		}


		public void setFechaCreacionRegistro(Date FechaCreacionRegistro) {
			this.FechaCreacionRegistro = FechaCreacionRegistro;
		}


		public String getCreadoPor() {
			return CreadoPor;
		}


		public void setCreadoPor(String creadoPor) {
			CreadoPor = creadoPor;
		}


		public String getEstatusArchivo() {
			return EstatusArchivo;
		}


		public void setEstatusArchivo(String estatusArchivo) {
			EstatusArchivo = estatusArchivo;
		}


		public String getEspacioFisico() {
			return EspacioFisico;
		}


		public void setEspacioFisico(String espacioFisico) {
			EspacioFisico = espacioFisico;
		}


		public String getLugarAsignadoEdificio() {
			return LugarAsignadoEdificio;
		}


		public void setLugarAsignadoEdificio(String lugarAsignadoEdificio) {
			LugarAsignadoEdificio = lugarAsignadoEdificio;
		}


		public String getEmail() {
			return Email;
		}


		public void setEmail(String strEmail) {
			this.Email = strEmail;
		}
			
		@Override
		public String toString() {
			return "<tr class='tblSeguimientoI2'>"
					+ "<td>"+ NoCyge +"</td>"
					+ "<td>"+ Usuario + "</td>" 
					+ "<td>"+ Nombre + "</td>"
					+ "<td>"+ ApePaterno + "</td>"
					+ "<td>"+ ApeMaterno + "</td>"
					+ "<td>"+ DirGeneral + "</td>" 
					+ "<td>"+ DirCorporativa + "</td>" 
					+ "<td>"+ Area + "</td>"
					+ "<td>"+ EntOficial + "</td>" 
					+ "<td>"+ AutorizadorId + "</td>" 
					+ "<td>"+ Autorizador + "</td>"
					+ "<td>"+ Proveedor + "</td>" 
					+ "<td>"+ Proyecto + "</td>"
					+ "<td>"+ Estatus + "</td>" 
					+ "<td>"+ EspacioFisico + "</td>"
					+ "<td>"+ LugarAsignadoEdificio + "</td>" 
					+ "<td>"+ Email + "</td>"
					+ "<td>"+ FechaCreacionRegistro + "</td>" 
					+ "<td>"+ CreadoPor + "</td>" 
					+ "<td>"+ EstatusArchivo + "</td>"  
					+ "</tr>";
		}

		@Override
		public void VerificarArchivo(String[] lineas) {

			Fecha tiempo = new Fecha();
			
			this.NoCyge = lineas[0];
			this.Usuario = lineas[1];
			this.Nombre = lineas[2];
			this.ApePaterno = lineas[3];
			this.ApeMaterno = lineas[4];
			this.DirGeneral = lineas[5];
			this.DirCorporativa = lineas[6];
			this.Area = lineas[7];
			this.EntOficial = lineas[8];
			this.AutorizadorId = lineas[9];
			this.Autorizador = lineas[10];
			this.Proveedor = lineas[11];
			this.Proyecto = lineas[12];
			this.Estatus = lineas[13];
			this.FechaCreacionRegistro = tiempo.fechas();
			this.CreadoPor = "Carga Masiva";
			this.EstatusArchivo = "Activo";
			
			}
		
		
		public static class ComposedIdKey implements Serializable
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public String NoCyge;
		    public String LugarAsignadoEdificio;
		 
		    public ComposedIdKey ()
		    {
		    }
		 
		    /**
		     * Constructor accepting same input as generated by toString().
		     */
		    public ComposedIdKey(String value)
		    {
		    	String[] constructorParam = value.split("::");
		        this.NoCyge = constructorParam[1];
		        this.LugarAsignadoEdificio = constructorParam[2];
		    }
		 
		    public boolean equals(Object obj)
		    {
		        if (obj == this)
		        {
		            return true;
		        }
		        if (!(obj instanceof ComposedIdKey))
		        {
		            return false;
		        }
		        ComposedIdKey c = (ComposedIdKey)obj;
		        return NoCyge.equals(c.NoCyge) && LugarAsignadoEdificio.equals(c.LugarAsignadoEdificio);
		    }
		 
		    public int hashCode ()
		    {
		        return this.NoCyge.hashCode() ^ this.LugarAsignadoEdificio.hashCode();
		    }
		 
		    public String toString ()
		    {
		        // Give output expected by String constructor
		        return this.getClass().getName() + "::"  + this.NoCyge + "::" + this.LugarAsignadoEdificio;
		    }
		}
		

}
