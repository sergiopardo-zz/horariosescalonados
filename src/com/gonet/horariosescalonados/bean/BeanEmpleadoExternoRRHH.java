package com.gonet.horariosescalonados.bean;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.gonet.horariosescalonados.interfaces.Archivo;

@PersistenceCapable(catalog = "horariosescalonadosv2", table = "EmpleadoExternoRRHH")
public class BeanEmpleadoExternoRRHH implements Archivo{

															//PROPIEDADES
	@PrimaryKey
	@Persistent (name = "Usuario")
	private String usuario;
	
	@Persistent
	@Column (name = "DireccionGeneral")
	private String dirGeneral;
	
	@Persistent
	@Column (name = "DireccionCorporativa")
	private String dirCorporativa;
	
	@Persistent (name = "Area")
	private String area;
	
	@Persistent (name = "CRDireccionGeneral")
	private String CRDireccionGeneral;
	
	@Persistent (name = "CRDireccionCorporativa")
	private String CRDireccionCorporativa;
	
	@Persistent (name = "CRArea")
	private String CRArea;
	
	@Persistent (name ="Edificio")
	private String Edificio;
	
															//CONSTRUCTORES
	
	public BeanEmpleadoExternoRRHH(String usuario,
			String dirGeneral, String dirCorporativa,
			String area, String cRDireccionGeneral,
			String cRDireccionCorporativa, String cRArea,
			String edificio) {
		super();
		this.usuario = usuario;
		this.dirGeneral = dirGeneral;
		this.dirCorporativa = dirCorporativa;
		this.area = area;
		CRDireccionGeneral = cRDireccionGeneral;
		CRDireccionCorporativa = cRDireccionCorporativa;
		CRArea = cRArea;
		Edificio = edificio;
	}
	

	
	public BeanEmpleadoExternoRRHH(String [] arrstrValores)
	{
		this.usuario = arrstrValores[1];
		this.dirGeneral = arrstrValores[5];
		this.dirCorporativa = arrstrValores[6];
		this.area = arrstrValores[7];
	}
				
	public BeanEmpleadoExternoRRHH (BeanUsuarioDirectorio usuarioDirectorio)
	{
		this.usuario = usuarioDirectorio.getUid();
		this.dirGeneral = usuarioDirectorio.getDescOUPadre();
		this.dirCorporativa = usuarioDirectorio.getDescCentroCoste();
		this.CRDireccionGeneral = usuarioDirectorio.getCodOUPadre();
		this.CRDireccionCorporativa = usuarioDirectorio.getCodBancoOficinaPers();
		this.CRArea = usuarioDirectorio.getCodOUNivel10();
		this.area = usuarioDirectorio.getDescOUNivel10();		
		this.Edificio = usuarioDirectorio.getDescCentroTrabajo();
	}
															//GETTERS Y SETTERS

	public String getStrUsuario() 
	{
		return usuario;
	}

	public void setStrUsuario(String strUsuario) 
	{
		this.usuario = strUsuario;
	}

	public String getStrDirGeneral() 
	{
		return dirGeneral;
	}

	public void setStrDirGeneral(String strDirGeneral) 
	{
		this.dirGeneral = strDirGeneral;
	}

	public String getStrDirCorporativa() 
	{
		return dirCorporativa;
	}

	public void setStrDirCorporativa(String strDirCorporativa) 
	{
		this.dirCorporativa = strDirCorporativa;
	}

	public String getStrArea() 
	{
		return area;
	}

	public void setStrArea(String strArea) 
	{
		this.area = strArea;
	}
	
	public String getCRDireccionGeneral() 
	{
		return CRDireccionGeneral;
	}

	public void setCRDireccionGeneral(String cRDireccionGeneral) 
	{
		CRDireccionGeneral = cRDireccionGeneral;
	}

	public String getCRDireccionCorporativa() 
	{
		return CRDireccionCorporativa;
	}

	public void setCRDireccionCorporativa(String cRDireccionCorporativa) 
	{
		CRDireccionCorporativa = cRDireccionCorporativa;
	}

	public String getCRArea() 
	{
		return CRArea;
	}

	public void setCRArea(String cRArea) 
	{
		CRArea = cRArea;
	}
	
	public String getEdificio ()
	{
		return Edificio;
	}
	public void setEdificio(String Edificio)
	{
		this.Edificio = Edificio;
	}

	@Override
	public void VerificarArchivo(String[] lineas) {
		// TODO Auto-generated method stub
		
	}
	
}
