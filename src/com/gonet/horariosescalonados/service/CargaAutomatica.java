package com.gonet.horariosescalonados.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoCyge;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoRRHH;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanIncumplimiento;
import com.gonet.horariosescalonados.bean.BeanZeit;
import com.gonet.horariosescalonados.interfaces.Archivo;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.gonet.horariosescalonados.persistence.TipoArchivo;
import com.gonet.horariosescalonados.util.CompletarReporteCumplimientoCyge;

public class CargaAutomatica 

{
	private PrivateKey key;
	private String string;
	private TipoArchivo tipoArchivo;

	public void CargaAutomaticaArchivo() throws UnsupportedEncodingException
	{
		CargaArchivo carga = new CargaArchivo();

		DataNucleusQuery query = new DataNucleusQuery();
		
		BufferedReader bufferedReader= new BufferedReader(new StringReader(string));
		List<? extends Archivo> registrosArchivo = carga.LeerArchivo(bufferedReader, tipoArchivo);

		switch (tipoArchivo)
		{
		case CYGE:
			
			query.InsertarRegistrosCyge((List<BeanCyge>) registrosArchivo);
			
			
			BusquedaUsuarioNuevo busquedaUsuario = new BusquedaUsuarioNuevo((List<BeanCyge>) registrosArchivo, key, 1);
			String pruebaDeEntrada = "asdasd";
			busquedaUsuario.BuscarUsuarioExterno();
			String segundaPrueba = "No entro";
			
			break;

		case ZEIT:

			CompletarReporteCumplimientoCyge reporteCumplimientoCyge = new CompletarReporteCumplimientoCyge();
			
			query.InsertarRegistrosZeit((List<BeanZeit>) registrosArchivo);
			
			List<BeanCumplimientoExternoCyge> registrosIncompletosReporteCyge = query.RegistrosCumplimientoCyge();
			
			List<BeanCumplimientoExternoCyge> registrosCompletosReporteCyge =  reporteCumplimientoCyge.ReporteCyge(registrosIncompletosReporteCyge);
			
			List<BeanIncumplimiento> registrosIncumplimientoCompletos = new ArrayList<BeanIncumplimiento>();
			
			List<BeanCumplimientoExternoCyge> registrosResultadosDelQuery = new ArrayList<BeanCumplimientoExternoCyge>();
			
			for (Iterator<BeanCumplimientoExternoCyge> iterator = registrosCompletosReporteCyge.iterator(); iterator.hasNext();) {
				BeanCumplimientoExternoCyge registro = iterator.next();
			    if (!(registro.getStrEstatus().equals("VIGENTE"))) {
			        BeanIncumplimiento registroIncumplimiento = new BeanIncumplimiento(registro);
			        registrosIncumplimientoCompletos.add(registroIncumplimiento);
			    }
			    else{
			    	registrosResultadosDelQuery.add(registro);
			    }
			}
			
//			query.InsertarRegistrosIncumplimiento(registrosIncumplimientoCompletos);
			
//			query.InsertarRegistrosCumplimientoCyge(registrosResultadosDelQuery);
					
			List<BeanCumplimientoExternoRRHH> registrosIncompletoCygeRRHH = query.RegistrosCumplimientoRRHH();
					
			List<BeanCumplimientoExternoRRHH> reporteCumplimiettoCygeRRHH = reporteCumplimientoCyge.ReporteRRHH(registrosIncompletoCygeRRHH);
			
//			query.InsertarCumplimientoCygeRH(reporteCumplimiettoCygeRRHH);
			
			break;

		case PERFILCONSULTA:

			break;

		default:

			try 
			{
				throw new Exception("Tipo de archivo no reconocido");
			} 
			catch (Exception e) 
			{	
				e.printStackTrace();
			}
			break;
		}
	}
	
	
	public CargaAutomatica()
	{
		
	}
	
	public CargaAutomatica(String string, TipoArchivo tipoArchivo)
	{
		this.string = string;
		this.tipoArchivo = tipoArchivo;
	}
	
	public CargaAutomatica(String string, TipoArchivo tipoArchivo, PrivateKey key)
	{
		this.string = string;
		this.tipoArchivo = tipoArchivo;
		this.key = key;
	}
	
	public String getString() {
		return string;
	}

	public void setInputString(String string) {
		this.string = string;
	}

	public TipoArchivo getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(TipoArchivo tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}


	public PrivateKey getKey() {
		return key;
	}


	public void setKey(PrivateKey key) {
		this.key = key;
	}

	
}
