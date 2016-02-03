package com.gonet.horariosescalonados.persistence;

import java.util.List;


import com.gonet.horariosescalonados.interfaces.Archivo;
import com.gonet.horariosescalonados.util.HibernateUtils;

public class TestQuery extends HibernateUtils {

	public void algo (List<Archivo> registros)
	{
		/*SessionFactory factory = getSessionFactory();
		System.out.println("Session factory object created : " + factory);
		Session session = factory.openSession();
		try{

			Transaction transaction = session.beginTransaction();

			int i = 0;
			
			for (Archivo registro : registros)
			{
				session.saveOrUpdate(registro);

				 i = i + 1;
					System.out.println(i);
					
					if (i % 50 == 0)
					{
						session.flush();
						session.clear();
					}
			}
			
			transaction.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		finally {

			if (session != null) {

				session.close();

			}

			/*if (factory != null) {

				factory.close();

			}*/

		}

	}
//}
