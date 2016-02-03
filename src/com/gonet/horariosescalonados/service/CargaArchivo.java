package com.gonet.horariosescalonados.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.gonet.horariosescalonados.interfaces.Archivo;
import com.gonet.horariosescalonados.persistence.DataNucleusQuery;
import com.gonet.horariosescalonados.persistence.TipoArchivo;
import com.gonet.horariosescalonados.util.CompletarReporteCumplimientoCyge;
import com.gonet.horariosescalonados.util.HeConstantes;
import com.gonet.horariosescalonados.bean.BeanZeit;
import com.gonet.horariosescalonados.factory.ArchivoFactory;


public class CargaArchivo {

	public File CargarArchivoDesdeRuta (String ruta) throws FileNotFoundException
	{
		File archivo = null;

		try {
			archivo = new File (ruta);
		}
		catch (Exception ex)
		{
			System.out.println("Error desconocido");
		}

		return archivo;

	}

	public List <?extends Archivo> LeerArchivo (BufferedReader bufferedReader, TipoArchivo tipoArchivo)
	{
		String strLineaArchivo = null;
		String[] arrstrLineas = null;
		List<Archivo> registrosLeidos = new ArrayList <Archivo>();
		int intColumnasArchivo = 0;
		

		try {
			//FileReader reader = new FileReader(archivo);

																	// Se lee la primera línea para saltarnos los encabezados.
			bufferedReader.readLine();
			
			switch (tipoArchivo)
			{
			case ZEIT:
				
				intColumnasArchivo = HeConstantes.COLUMNAS_ARCHIVO_ZEIT;
				
				break;

			case CYGE:
				
				intColumnasArchivo = HeConstantes.COLUMNAS_ARCHIVO_CYGE;

				break;
				
			case PERFILCONSULTA:
				
				intColumnasArchivo = HeConstantes.COLUMNAS_ARCHIVO_PERFILCONULTA;

			default:
				System.out.println("tipo de archivo no reconocido");
				
				break;
			}

			while ((strLineaArchivo = bufferedReader.readLine()) != null)
			{
				arrstrLineas = strLineaArchivo.split("\\|");
				
				Archivo archivoNuevo = ArchivoFactory.getArchivo(tipoArchivo, arrstrLineas,intColumnasArchivo );
				
				if (archivoNuevo != null)	
				{
					registrosLeidos.add(archivoNuevo);
				}
			}

			bufferedReader.close();
			
			if (!(registrosLeidos.isEmpty()))
			{
				registrosLeidos.toArray();
			}

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return registrosLeidos;
	}
	
			
}
