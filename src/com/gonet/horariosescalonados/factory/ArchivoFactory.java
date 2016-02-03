package com.gonet.horariosescalonados.factory;

import com.gonet.horariosescalonados.bean.BeanArchivoPerfilConsulta;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanZeit;
import com.gonet.horariosescalonados.bean.EmpleadoExterno;
import com.gonet.horariosescalonados.interfaces.Archivo;
import com.gonet.horariosescalonados.persistence.TipoArchivo;

public class ArchivoFactory {


	public static Archivo getArchivo (TipoArchivo tipoArchivo, String [] arrstrRegistros, int intColumnasArchivo)

	{
		if (arrstrRegistros.length == intColumnasArchivo)
		{

			switch (tipoArchivo)
			{
			case ZEIT:
				return new BeanZeit(arrstrRegistros);

			case CYGE:

				return new BeanCyge(arrstrRegistros);

			case PERFILCONSULTA: 

				return new BeanArchivoPerfilConsulta(arrstrRegistros);	


			default:

				if (arrstrRegistros[14].equals("SI"))
				{
					return new EmpleadoExterno(arrstrRegistros);
				}

				return null;
			}
		}

		return null;
	}
}
