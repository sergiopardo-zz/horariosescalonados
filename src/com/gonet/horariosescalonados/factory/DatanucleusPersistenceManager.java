package com.gonet.horariosescalonados.factory;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.google.appengine.api.utils.SystemProperty;

public class DatanucleusPersistenceManager 

{
	private static PersistenceManagerFactory factory ;
	
	protected DatanucleusPersistenceManager()
	{
		
	}
	
	public static PersistenceManagerFactory getInstance()
	{
		if (factory == null)
		{
			try 
			{
				if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
				{
					factory=JDOHelper.getPersistenceManagerFactory("HorariosEscalonados");
				}
				else
				{
					factory = JDOHelper.getPersistenceManagerFactory("HorariosEscalonadosDev");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return factory;
		
	}
}
