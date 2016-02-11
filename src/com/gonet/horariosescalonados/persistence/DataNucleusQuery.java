package com.gonet.horariosescalonados.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Calendar;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.gonet.horariosescalonados.bean.BeanCumplimiento;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoCyge;
import com.gonet.horariosescalonados.bean.BeanCumplimientoExternoRRHH;
import com.gonet.horariosescalonados.bean.BeanCyge;
import com.gonet.horariosescalonados.bean.BeanEmpleado;
import com.gonet.horariosescalonados.bean.BeanEmpleadoExterno;
import com.gonet.horariosescalonados.bean.BeanEmpleadoExternoRRHH;
import com.gonet.horariosescalonados.bean.BeanEmpleadoHorario;
import com.gonet.horariosescalonados.bean.BeanIncumplimiento;
import com.gonet.horariosescalonados.bean.BeanPerfilConsulta;
import com.gonet.horariosescalonados.bean.BeanZeit;
import com.gonet.horariosescalonados.dao.InsertarRegistro;
import com.gonet.horariosescalonados.factory.DatanucleusPersistenceManager;


public class DataNucleusQuery 
{
	public void InsertarRegistrosCyge (List<BeanCyge> registros)
	{

		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		try 
		{		
			//TODO: Remover contador de tiempo.

			Transaction tx = pm.currentTransaction();

			tx.begin();
			long startTime = System.currentTimeMillis();
			for (BeanCyge registro : registros)
			{
				try
				{
					BeanCyge registroCyge = (BeanCyge) pm.getObjectById(new BeanCyge.ComposedIdKey(BeanCyge.ComposedIdKey.class.getName()+"::"+registro.getNoCyge()+"::"+registro.getLugarAsignadoEdificio()));

					registroCyge.setApeMaterno(registro.getApeMaterno());
					registroCyge.setApePaterno(registro.getApePaterno());
					registroCyge.setNombre(registro.getNombre());
					registroCyge.setArea(registro.getArea());
					registroCyge.setAutorizador(registro.getAutorizador());
					registroCyge.setAutorizadorId(registro.getAutorizadorId());
					registroCyge.setDirCorporativa(registro.getDirCorporativa());
					registroCyge.setDirGeneral(registro.getDirGeneral());
					registroCyge.setLugarAsignadoEdificio(registro.getLugarAsignadoEdificio());
					registroCyge.setEntOficial(registro.getEntOficial());
					registroCyge.setEspacioFisico(registro.getEspacioFisico());
					registroCyge.setEstatus(registro.getEstatus());
					registroCyge.setProveedor(registro.getProveedor());
					registroCyge.setProyecto(registro.getProyecto());

					//pm.makePersistent(registro);
					
				}

				catch(JDOObjectNotFoundException e)
				{
					pm.makePersistent(registro);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{

				}
			}

			pm.currentTransaction().commit();
			////////////////////
			
			InsertarRegistro insertdao = new InsertarRegistro();
			insertdao.updateRegistroCero();
			
			///////////////////
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void PersisitirEmpleadoExterno (List<BeanEmpleadoExterno> registros)
	{

		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		try 
		{		
			//TODO: Remover contador de tiempo.

			Transaction tx = pm.currentTransaction();

			tx.begin();
			long startTime = System.currentTimeMillis();
			for (BeanEmpleadoExterno registro : registros)
			{
				try
				{
					BeanEmpleadoExterno registroCyge = (BeanEmpleadoExterno) pm.getObjectById(new BeanEmpleadoExterno.ComposedIdKey(BeanEmpleadoExterno.ComposedIdKey.class.getName()+"::"+registro.getNoCyge()+"::"+registro.getLugarAsignadoEdificio()));

					registroCyge.setApeMaterno(registro.getApeMaterno());
					registroCyge.setApePaterno(registro.getApePaterno());
					registroCyge.setNombre(registro.getNombre());
					registroCyge.setArea(registro.getArea());
					registroCyge.setAutorizador(registro.getAutorizador());
					registroCyge.setAutorizadorId(registro.getAutorizadorId());
					registroCyge.setDirCorporativa(registro.getDirCorporativa());
					registroCyge.setDirGeneral(registro.getDirGeneral());
					registroCyge.setLugarAsignadoEdificio(registro.getLugarAsignadoEdificio());
					registroCyge.setEntOficial(registro.getEntOficial());
					registroCyge.setEspacioFisico(registro.getEspacioFisico());
					registroCyge.setEstatus(registro.getEstatus());
					registroCyge.setProveedor(registro.getProveedor());
					registroCyge.setProyecto(registro.getProyecto());
					registroCyge.setEmail(registro.getEmail());

					//pm.makePersistent(registro);
					
				}

				catch(JDOObjectNotFoundException e)
				{
					pm.makePersistent(registro);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{

				}
			}

			pm.currentTransaction().commit();

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void InsertarRegistrosZeit (List<BeanZeit> registros)
	{	
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		long startTime = System.currentTimeMillis();
		
		try 
		{	
			//TODO: Remover contador de tiempo.	
			Transaction tx = pm.currentTransaction();

			tx.begin();

			for (BeanZeit registro : registros)
			{
				try
				{
					pm.makePersistent(registro);

					/*
					BeanZeit registroZeit = (BeanZeit) pm.getObjectById(new BeanCyge.ComposedIdKey(BeanCyge.ComposedIdKey.class.getName()+"::"+registro.getNoCyge()+"::"+registro.getEdificio()));

					registroZeit.setsubcontrataNombre(registro.getsubcontrataNombre());
					registroZeit.setfecha(registro.getfecha());
					registroZeit.sethora(registro.gethora());
					registroZeit.settipoFuncion(registro.gettipoFuncion());
					registroZeit.setnombreLector(registro.getnombreLector());
					registroZeit.setedificio(registro.getedificio());
					registroZeit.setestancia(registro.getestancia());
					 */
				}

				catch(JDOObjectNotFoundException e)
				{
					e.printStackTrace();
					pm.makePersistent(registro);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}		
			}

			tx.commit();
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		finally
		{
			pm.close();
		}
	}

	public void borrarUsuarios (String UsuarioRepren){
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
		try{
		String strDeleteExt = "DELETE FROM horariosescalonadosv2.PerfilConsultaExternos WHERE IdUsuarioConsulta "+"='"+UsuarioRepren+ "';";
		Query query2 = pm.newQuery("javax.jdo.query.SQL",strDeleteExt);
		query2.execute();

		String strDeleteInt = ("DELETE FROM horariosescalonadosv2.PerfilConsultaInternos WHERE IdUsuarioConsultaInterno "+"='"+UsuarioRepren+ "';");
		Query query3 = pm.newQuery("javax.jdo.query.SQL",strDeleteInt);
		query3.execute();
		
		String strTipoEmpleado = ("UPDATE horariosescalonadosv2.empleado SET tipoEmpleado ='E' WHERE empleadoID = '"+ UsuarioRepren+ "'");
		Query query4 = pm.newQuery("javax.jdo.query.SQL",strTipoEmpleado);
		query4.execute();
		
		}catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{

		}
	}
	public String agregarUsuario(String UsuarioRepren,String[] usuario,String tipoConsulta){

		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		Transaction tx=(Transaction) pm.currentTransaction();
		String strPrueba = null;
		String strActualiza = null;
		String strAccion="";

		try{


			tx.begin();


			String strDeleteExt = "DELETE FROM horariosescalonadosv2.PerfilConsultaExternos WHERE IdUsuarioConsulta "+"='"+UsuarioRepren+ "';";
			Query query2 = pm.newQuery("javax.jdo.query.SQL",strDeleteExt);
			query2.execute();

			String strDeleteInt = ("DELETE FROM horariosescalonadosv2.PerfilConsultaInternos WHERE IdUsuarioConsultaInterno "+"='"+UsuarioRepren+ "';");
			Query query3 = pm.newQuery("javax.jdo.query.SQL",strDeleteInt);
			query3.execute();

			if(tipoConsulta.equals("EXTERNOS")){
				for(int x = 1;x<usuario.length;x++){
					try{
						if(usuario[x].startsWith("X")){

							strPrueba = "INSERT INTO horariosescalonadosv2.PerfilConsultaExternos (IdUsuarioConsulta, IdUsuarioReporte)" +
									"VALUES (" + "'"+ UsuarioRepren+ "'" + "," + "'"+ usuario[x]+ "'" + ")";

							Query query = pm.newQuery("javax.jdo.query.SQL",strPrueba);
							query.execute();
							
							
							
							strAccion =strAccion+ "Se agrego el usuario a Externos:"+ usuario[x] +"\n";
							System.out.println("Se agrega el usuario a la tabla PerfilConsultaExternos: "+usuario[x] );

							}else{
								strAccion =strAccion+ "El usuario no esta en formato Externo:"+ usuario[x] +"\n";
								System.out.println("El usuario no esta en formato Externo y no se agrego: "+usuario[x] );
	
							}
					}catch(Exception e){
						e.printStackTrace();
					}
				}	
				strActualiza = "UPDATE horariosescalonadosv2.empleado SET tipoEmpleado ='C' WHERE empleadoID = '"+ UsuarioRepren+ "'";
				Query query1 = pm.newQuery("javax.jdo.query.SQL",strActualiza);
				query1.execute();
			}else if(tipoConsulta.equals("INTERNOS")){
				for(int x=1;x<usuario.length;x++){
					try{
						if(usuario[x].startsWith("M")){
							strPrueba = "INSERT INTO horariosescalonadosv2.PerfilConsultaInternos (IdUsuarioConsultaInterno, IdUsuarioReporteInterno)" +
									"VALUES (" + "'"+ UsuarioRepren+ "'" + "," + "'"+ usuario[x]+ "'" + ")";

							Query query = pm.newQuery("javax.jdo.query.SQL",strPrueba);
							query.execute();
							strAccion =strAccion+ "Se agrego el usuario a Internos:"+ usuario[x]+ "\n";
							System.out.println("Se agrega el usuario a la tabla PerfilConsultaInternos: " + usuario[x]);
						}else{
							strAccion =strAccion+ "El usuario no esta en formato Interno:"+ usuario[x] +"\n";
							System.out.println("El usuario no esta en formato Interno y no se agrego: "+usuario[x] );
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				strActualiza = "UPDATE horariosescalonadosv2.empleado SET tipoEmpleado ='C' WHERE empleadoID = '"+ UsuarioRepren+ "'";
				Query query1 = pm.newQuery("javax.jdo.query.SQL",strActualiza);
				query1.execute();
			}else if(tipoConsulta.equals("AMBOS")){
				for(int x=1;x<usuario.length;x++){
					try{
						if(usuario[x].startsWith("M")){
							strPrueba = "INSERT INTO horariosescalonadosv2.PerfilConsultaInternos (IdUsuarioConsultaInterno, IdUsuarioReporteInterno)" +
									"VALUES (" + "'"+ UsuarioRepren+ "'" + "," + "'"+ usuario[x]+ "'" + ")";

							Query query = pm.newQuery("javax.jdo.query.SQL",strPrueba);
							query.execute();
							strAccion =strAccion+ "Se agrego el usuario a Internos:"+ usuario[x]+ "\n";
							System.out.println("Se agrega el usuario a la tabla PerfilConsultaInternos: " + usuario[x]);
						}else if(usuario[x].startsWith("X")){

							strPrueba = "INSERT INTO horariosescalonadosv2.PerfilConsultaExternos (IdUsuarioConsulta, IdUsuarioReporte)" +
									"VALUES (" + "'"+ UsuarioRepren+ "'" + "," + "'"+ usuario[x]+ "'" + ")";

							Query query = pm.newQuery("javax.jdo.query.SQL",strPrueba);
							query.execute();
							strAccion =strAccion+ "Se agrego el usuario a Externos:"+ usuario[x] +"\n";
							System.out.println("Se agrega el usuario a la tabla PerfilConsultaExternos: "+usuario[x] );

							}else{
								strAccion =strAccion+ "El usuario no esta en formato Interno o Externo:"+ usuario[x] +"\n";
								System.out.println("El usuario no esta en formato Interno o Externo y no se agrego: "+usuario[x] );
							}
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				strActualiza = "UPDATE horariosescalonadosv2.empleado SET tipoEmpleado ='C' WHERE empleadoID = '"+ UsuarioRepren+ "'";
				Query query1 = pm.newQuery("javax.jdo.query.SQL",strActualiza);
				query1.execute();
			}
			tx.commit();
			return strAccion;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(tx.isActive()){
				tx.rollback();
			}
		}
		return strAccion;

	}
	//--------------------------------------------------------------------------------------------------------
	public void agregaPerfilConsulta(String idEmpleado,String TipoConsulta)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		Transaction tx = (Transaction) pm.currentTransaction();
		String strPrueba = null;
		try{
			int intConsulta = 0;
			int estatus = 1;
			if(TipoConsulta.equals("AMBOS")){
				intConsulta = 3;
			}else if(TipoConsulta.equals("INTERNOS")){
				intConsulta = 1;
			}else if(TipoConsulta.equals("EXTERNOS")){
				intConsulta =2;
			}else{
				estatus=0;
				intConsulta = 0;
			}
			tx.begin();
			strPrueba = ("insert into horariosescalonadosv2.PerfilConsulta(IdUsuario,TipoConsulta,Estatus)" +
					"values(" + "'"+ idEmpleado+ "'" + "," + "'"+ intConsulta+ "'"+","+ "'"+ estatus+ "'"+")" + 
					"ON DUPLICATE KEY " +
					"UPDATE IdUsuario = values(IdUsuario),TipoConsulta =values(TipoConsulta), "+
					"Estatus = values(Estatus);");

			Query q = pm.newQuery("javax.jdo.query.SQL",strPrueba);

			q.execute();
			tx.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(tx.isActive()){
				tx.rollback();
			}
		}
	}

	//--------------------------------------------------------------------------------------------------------
	public List<BeanPerfilConsulta> consulta(String field,String value)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		Transaction tx = (Transaction) pm.currentTransaction();
		String strQuery = null;
		List<BeanPerfilConsulta> resultadoBeanPerfilConsulta = null;
		try{
			tx.begin();
			strQuery=("SELECT  * FROM horariosescalonadosv2.PerfilConsulta  WHERE "+field+"='"+value+ "';");
			Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
			query.setResultClass(BeanPerfilConsulta.class);

			resultadoBeanPerfilConsulta = (List<BeanPerfilConsulta>) query.execute();

			return resultadoBeanPerfilConsulta;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(tx.isActive()){
				tx.rollback();
			}
		}
		return resultadoBeanPerfilConsulta;
	}

	//-----------------------------------HISTORICO CYGE----------------------------
	@SuppressWarnings("unchecked")
	public List<BeanCyge> HistoricoCyge (Date desdeDate, Date hastaDate)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		String strQuery = null;
		List<BeanCyge> resultadosBeanCyge = null;

		try{

			Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.Cyge.NoCyge, horariosescalonadosv2.Cyge.Usuario, "
					+ "horariosescalonadosv2.Cyge.Nombre, horariosescalonadosv2.Cyge.ApePaterno, horariosescalonadosv2.Cyge.ApeMaterno, "
					+ "horariosescalonadosv2.Cyge.DirGeneral, horariosescalonadosv2.Cyge.DirCorporativa, horariosescalonadosv2.Cyge.Area, "
					+ "horariosescalonadosv2.Cyge.EntOficial, horariosescalonadosv2.Cyge.AutorizadorID, horariosescalonadosv2.Cyge.Autorizador, "
					+ "horariosescalonadosv2.Cyge.Proveedor, horariosescalonadosv2.Cyge.Proyecto, horariosescalonadosv2.Cyge.Estatus, "
					+ "horariosescalonadosv2.Cyge.EspacioFisico, horariosescalonadosv2.Cyge.LugarAsignadoEdificio, horariosescalonadosv2.Cyge.Email,"
					+ "horariosescalonadosv2.Cyge.FechaCreacionRegistro, horariosescalonadosv2.Cyge.CreadoPor, horariosescalonadosv2.Cyge.RegistroActivo "
					+ "From horariosescalonadosv2.Cyge "
					+ "where '"+desdeDate+"' <= horariosescalonadosv2.Cyge.FechaCreacionRegistro AND horariosescalonadosv2.Cyge.FechaCreacionRegistro <= '"+hastaDate+"' ");
			q.setResultClass(BeanCyge.class);
			resultadosBeanCyge = (List<BeanCyge>)q.execute();

			return resultadosBeanCyge;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally {
			//pm.close();

		}

		return resultadosBeanCyge;
	}

//----------------------REPORTE CUMPLIMIENTO INTERNO---------------------------------------------	
	public List<BeanCumplimiento> ReporteCumplimientoInterno (Date desdeDate, Date hastaDate, String usuario)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		Transaction tx = (Transaction) pm.currentTransaction();
		String strQuery = null;
		List<BeanCumplimiento> resultadosBeanCumplimiento = null;

		try{

			tx.begin();

			Query q = pm.newQuery("SQL", "SELECT * FROM horariosescalonadosv2.cumplimiento "
					+ "where horariosescalonadosv2.cumplimiento.empleadoID in (select horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioReporteInterno "
					+ "from horariosescalonadosv2.PerfilConsultaInternos where horariosescalonadosv2.PerfilConsultaInternos.IdUsuarioConsultaInterno = '"+usuario+"' ) "
					+ "and  '"+desdeDate+"' <= horariosescalonadosv2.cumplimiento.fecha AND horariosescalonadosv2.cumplimiento.fecha <= '"+hastaDate+"' ");
			q.setResultClass(BeanCumplimiento.class);
			resultadosBeanCumplimiento = (List<BeanCumplimiento>)q.execute();

			return resultadosBeanCumplimiento;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally {
			if (tx.isActive()) 
			{
				tx.rollback();
			}
			//pm.close();

		}

		return resultadosBeanCumplimiento;
	}
	
	//----------------------COUNT CUMPLIMIENTO INTERNO ADMIN--------------------------------------------- 
	 public float CountCumplimientoInternoAdmin (Date desdeDate, Date hastaDate)
	 {
	  PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
	  List<Long> countBeanCumplimiento = null;

	  try{    
	   Query q = pm.newQuery("SQL", "SELECT COUNT(*) "
	     + "FROM horariosescalonadosv2.cumplimiento "
	     + "where '"+desdeDate+"' <= horariosescalonadosv2.cumplimiento.fecha  AND horariosescalonadosv2.cumplimiento.fecha <= '"+hastaDate+"'");
	   countBeanCumplimiento = (List<Long>) q.execute();

	   return (float) countBeanCumplimiento.get(0);
	  }
	  catch (Exception e)
	  {
	   e.printStackTrace();
	  }
	  finally {
	   //pm.close();
	  }
	  return (float) countBeanCumplimiento.get(0);
	 }
		
	//----------------------REPORTE CUMPLIMIENTO INTERNO ADMIN--------------------------------------------- 
	  public List<BeanCumplimiento> ReporteCumplimientoInternoAdmin (Date desdeDate, Date hastaDate)
	  {
	   PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
	   List<BeanCumplimiento> resultadosBeanCumplimiento = null;

	   try{    
	    Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.cumplimiento.empleadoID, horariosescalonadosv2.cumplimiento.apePaterno, "
	      + "horariosescalonadosv2.cumplimiento.apeMaterno, horariosescalonadosv2.cumplimiento.nombre, horariosescalonadosv2.cumplimiento.nombreCR, "
	      + "horariosescalonadosv2.cumplimiento.dga, horariosescalonadosv2.cumplimiento.fecha, "
	      + "horariosescalonadosv2.cumplimiento.quincena, horariosescalonadosv2.cumplimiento.mes, horariosescalonadosv2.cumplimiento.tae, "
	      + "horariosescalonadosv2.cumplimiento.entrada, horariosescalonadosv2.cumplimiento.tde, horariosescalonadosv2.cumplimiento.entradaReal, horariosescalonadosv2.cumplimiento.califEntrada,"
	      + "horariosescalonadosv2.cumplimiento.tas, horariosescalonadosv2.cumplimiento.salida, horariosescalonadosv2.cumplimiento.tds, horariosescalonadosv2.cumplimiento.salidaReal, "
	      + "horariosescalonadosv2.cumplimiento.califSalida, horariosescalonadosv2.cumplimiento.jornada, "
	      + "horariosescalonadosv2.cumplimiento.total, horariosescalonadosv2.cumplimiento.porcentaje "
	      + "FROM horariosescalonadosv2.cumplimiento "
	      + "where '"+desdeDate+"' <= horariosescalonadosv2.cumplimiento.fecha  AND horariosescalonadosv2.cumplimiento.fecha <= '"+hastaDate+"'");
	    q.setResultClass(BeanCumplimiento.class);
	    resultadosBeanCumplimiento = (List<BeanCumplimiento>)q.execute();

	    return resultadosBeanCumplimiento;
	   }
	   catch (Exception e)
	   {
	    e.printStackTrace();
	   }
	   finally {
	    //pm.close();
	   }

	   return resultadosBeanCumplimiento;
	  }
	
//-------------------------------------- REPORTE CUMPLIMIENTO CYGE -------------------------------------
	public List<BeanCumplimientoExternoCyge> CumplimientoCygeRH (Date desdeDate, Date hastaDate, String usuario)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		String strQuery = null;
		List<BeanCumplimientoExternoCyge> resultados = null;

		try{

			Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.cumplimientoExterno.ncyge, horariosescalonadosv2.cumplimientoExterno.nombre, "
					+ "horariosescalonadosv2.cumplimientoExterno.direccionGeneral, horariosescalonadosv2.cumplimientoExterno.direccionCorporativa, "
					+ "horariosescalonadosv2.cumplimientoExterno.area, horariosescalonadosv2.cumplimientoExterno.fecha, horariosescalonadosv2.cumplimientoExterno.mes, "
					+ "horariosescalonadosv2.cumplimientoExterno.quincena, horariosescalonadosv2.cumplimientoExterno.entradaOficial, horariosescalonadosv2.cumplimientoExterno.entradaReal, "
					+ "horariosescalonadosv2.cumplimientoExterno.salidaReal, horariosescalonadosv2.cumplimientoExterno.jornada, horariosescalonadosv2.cumplimientoExterno.estancia, "
					+ "horariosescalonadosv2.cumplimientoExterno.edificio, horariosescalonadosv2.cumplimientoExterno.autorizador, horariosescalonadosv2.cumplimientoExterno.provedor, "
					+ "horariosescalonadosv2.cumplimientoExterno.proyecto, horariosescalonadosv2.cumplimientoExterno.estatus, horariosescalonadosv2.cumplimientoExterno.edificioAsignado "
					+ "From horariosescalonadosv2.cumplimientoExterno "
					+ "where horariosescalonadosv2.cumplimientoExterno.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte "
					+ "from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' ) "
					+ "and horariosescalonadosv2.cumplimientoExterno.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' ");
			q.setResultClass(BeanCumplimientoExternoCyge.class);
			resultados = (List<BeanCumplimientoExternoCyge>)q.execute();

			return resultados;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally {
			//pm.close();

		}

		return resultados;
	}
	
	//-------------------------------------- REPORTE CUMPLIMIENTO CYGE ADMIN-------------------------------------
		public List<BeanCumplimientoExternoCyge> CumplimientoCygeAdmin (Date desdeDate, Date hastaDate)
		{
			PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

			String strQuery = null;
			List<BeanCumplimientoExternoCyge> resultados = null;

			try{
			
				Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.cumplimientoExterno.usuario, horariosescalonadosv2.cumplimientoExterno.ncyge, horariosescalonadosv2.cumplimientoExterno.nombre, "
						+ "horariosescalonadosv2.cumplimientoExterno.direccionGeneral, horariosescalonadosv2.cumplimientoExterno.direccionCorporativa, "
						+ "horariosescalonadosv2.cumplimientoExterno.area, horariosescalonadosv2.cumplimientoExterno.fecha, horariosescalonadosv2.cumplimientoExterno.mes, "
						+ "horariosescalonadosv2.cumplimientoExterno.quincena, horariosescalonadosv2.cumplimientoExterno.entradaOficial, horariosescalonadosv2.cumplimientoExterno.entradaReal, "
						+ "horariosescalonadosv2.cumplimientoExterno.salidaReal, horariosescalonadosv2.cumplimientoExterno.jornada, horariosescalonadosv2.cumplimientoExterno.estancia, "
						+ "horariosescalonadosv2.cumplimientoExterno.edificio, horariosescalonadosv2.cumplimientoExterno.autorizador, horariosescalonadosv2.cumplimientoExterno.provedor, "
						+ "horariosescalonadosv2.cumplimientoExterno.proyecto, horariosescalonadosv2.cumplimientoExterno.estatus, horariosescalonadosv2.cumplimientoExterno.edificioAsignado "
						+ "From horariosescalonadosv2.cumplimientoExterno "
						+ "where '"+desdeDate+"' <= horariosescalonadosv2.cumplimientoExterno.fecha  AND horariosescalonadosv2.cumplimientoExterno.fecha <= '"+hastaDate+"' ");
				q.setResultClass(BeanCumplimientoExternoCyge.class);
				resultados = (List<BeanCumplimientoExternoCyge>)q.execute();
				
				return resultados;

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			finally {
				//pm.close();

			}

			return resultados;
		}
	
	
//---------------------------------------REPORTE CUMPLIMIENTO CYGE VISTA RRHH ----------------------------------------	
	public List<BeanCumplimientoExternoRRHH> ReporteCumplimientoCygeRRHH (Date desdeDate, Date hastaDate, String usuario)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		Transaction tx = (Transaction) pm.currentTransaction();
		String strQuery = null;
		List<BeanCumplimientoExternoRRHH> resultadosBeanCumplimientoExternoRRHH = null;

		try{

			tx.begin();

			strQuery = ("SELECT * FROM horariosescalonadosv2.CumplimientoExternoRRHH"
					+ " WHERE Fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' "
					+ " and horariosescalonadosv2.CumplimientoExternoRRHH.Usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte "
					+ "from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' )");
			Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
			query.setResultClass(BeanCumplimientoExternoRRHH.class);
			resultadosBeanCumplimientoExternoRRHH = (List<BeanCumplimientoExternoRRHH>) query.execute();

			return resultadosBeanCumplimientoExternoRRHH;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally {
			if (tx.isActive()) 
			{
				tx.rollback();
			}
			//pm.close();

		}

		return resultadosBeanCumplimientoExternoRRHH;
	}
	
//-----------------------REPORTE ADMIN CYGE RRHH ---------------------------------------------
		public List<BeanCumplimientoExternoRRHH> ReporteAdminCygeRRHH (Date desdeDate, Date hastaDate)
		{
			PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

			Transaction tx = (Transaction) pm.currentTransaction();
			String strQuery = null;
			List<BeanCumplimientoExternoRRHH> resultados = null;

			try{

				tx.begin();

				strQuery = ("SELECT * FROM horariosescalonadosv2.CumplimientoExternoRRHH"
						+ " WHERE '"+desdeDate+"' <= fECHA  AND Fecha<= '"+hastaDate+"'");
				Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
				query.setResultClass(BeanCumplimientoExternoRRHH.class);
				resultados = (List<BeanCumplimientoExternoRRHH>) query.execute();

				return resultados;

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			finally {
				if (tx.isActive()) 
				{
					tx.rollback();
				}
				//pm.close();

			}

			return resultados;
		}
	
	
	public List<BeanCumplimientoExternoCyge> RegistrosCumplimientoCyge ()
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		List<BeanCumplimientoExternoCyge> registrosCumplimientoCyge = null;

		String strQuery = null;

		try{

			strQuery = "select horariosescalonadosv2.Cyge.Usuario, horariosescalonadosv2.Cyge.NoCyge, "
					+ "CONCAT(horariosescalonadosv2.Cyge.nombre,' ' ,horariosescalonadosv2.Cyge.ApePaterno, "
					+ "' ', horariosescalonadosv2.Cyge.ApeMaterno) as nombre, horariosescalonadosv2.Cyge.DirGeneral, "
					+ "horariosescalonadosv2.Cyge.DirCorporativa, horariosescalonadosv2.Cyge.Area, "
					+ "horariosescalonadosv2.Zeit.Fecha ,'1' as Mes, '1' as Quincena,horariosescalonadosv2.Cyge.EntOficial, "
					+ "max(case when horariosescalonadosv2.Zeit.TipoFuncion= 'Entrada' then horariosescalonadosv2.Zeit.Hora end) as Entrada, "
					+ "max(case when horariosescalonadosv2.Zeit.TipoFuncion= 'SALIDA' then horariosescalonadosv2.Zeit.Hora end) as Salida, "
					+ " CONVERT(CalculoJornada.Jornada, CHAR(50)) as Jornada, horariosescalonadosv2.Zeit.estancia,horariosescalonadosv2.Zeit.edificio, horariosescalonadosv2.Cyge.autorizador, "
					+ "horariosescalonadosv2.Cyge.Proveedor, horariosescalonadosv2.Cyge.Proyecto, horariosescalonadosv2.Cyge.Estatus, "
					+ "horariosescalonadosv2.Cyge.EspacioFisico "
					+ "From horariosescalonadosv2.Cyge "
					+ "inner join horariosescalonadosv2.Zeit on horariosescalonadosv2.Zeit.NoCyge = horariosescalonadosv2.Cyge.NoCyge "
					+ "and  horariosescalonadosv2.Zeit.Edificio = horariosescalonadosv2.Cyge.LugarAsignadoEdificio "
					+ "inner join (SELECT  NoCyge, Fecha, SEC_TO_TIME( SUM( TIME_TO_SEC( estancia ) ) ) AS Jornada FROM horariosescalonadosv2.Zeit "
					+ "Where TipoFuncion = 'ENTRADA'group by NoCyge, Fecha) as CalculoJornada on CalculoJornada.NoCyge = horariosescalonadosv2.Zeit.NoCyge "
					+ "and horariosescalonadosv2.Zeit.Fecha = (select max(Fecha) from horariosescalonadosv2.Zeit) "
					+ "group by horariosescalonadosv2.Cyge.NoCyge, horariosescalonadosv2.Zeit.Edificio, horariosescalonadosv2.Zeit.Fecha;";

			Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
			query.setResultClass(BeanCumplimientoExternoCyge.class);
			registrosCumplimientoCyge = (List<BeanCumplimientoExternoCyge>)query.execute();

			return registrosCumplimientoCyge;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return registrosCumplimientoCyge;
	}
	

	public List <BeanCumplimientoExternoRRHH> RegistrosCumplimientoRRHH ()
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		List <BeanCumplimientoExternoRRHH> registrosCumplimientoRRHH = null;

		String strQuery = null;

		try{
			strQuery = "select horariosescalonadosv2.Cyge.usuario, horariosescalonadosv2.Zeit.nombre, "
					+ "horariosescalonadosv2.EmpleadoExternoRRHH.CRDireccionGeneral, horariosescalonadosv2.EmpleadoExternoRRHH.DireccionGeneral, "
					+ "horariosescalonadosv2.EmpleadoExternoRRHH.CRDireccionCorporativa, horariosescalonadosv2.EmpleadoExternoRRHH.DireccionCorporativa, "
					+ "horariosescalonadosv2.EmpleadoExternoRRHH.CRArea, horariosescalonadosv2.EmpleadoExternoRRHH.Area, "
					+ "horariosescalonadosv2.Zeit.Fecha, '1' as Quincena, '1' as Mes, '1' as TEA, horariosescalonadosv2.Cyge.EntOficial, "
					+ "'1' as TED, max(case when horariosescalonadosv2.Zeit.TipoFuncion= 'Entrada' then horariosescalonadosv2.Zeit.Hora end) as entradaReal, "
					+ "'1' as CalificacionEntrada, '1' as TSA, '1' as SalidaOficial, '1' as TSD, max(case when horariosescalonadosv2.Zeit.TipoFuncion= 'SALIDA' then horariosescalonadosv2.Zeit.Hora end) as salidaReal, "
					+ "'1' as CalificacionSalida, "
					+ " CONVERT(CalculoJornada.Jornada, CHAR(50)) as Jornada, "
					+ "'1' as CalificacionJornada, '1' as CalificacionTotal, '1' as PorcentajeCumplimiento, horariosescalonadosv2.EmpleadoExternoRRHH.Edificio "
					+ "From horariosescalonadosv2.Cyge inner join horariosescalonadosv2.Zeit on horariosescalonadosv2.Zeit.NoCyge = horariosescalonadosv2.Cyge.NoCyge "
					+ "and  horariosescalonadosv2.Zeit.Edificio = horariosescalonadosv2.Cyge.LugarAsignadoEdificio "
					+ "inner join horariosescalonadosv2.EmpleadoExternoRRHH on horariosescalonadosv2.Cyge.usuario = horariosescalonadosv2.EmpleadoExternoRRHH.usuario "
					+ "inner join (SELECT  NoCyge, Fecha, SEC_TO_TIME( SUM( TIME_TO_SEC( estancia ) ) ) AS Jornada FROM horariosescalonadosv2.Zeit "
					+ "Where TipoFuncion = 'ENTRADA'group by NoCyge, Fecha) as CalculoJornada on CalculoJornada.NoCyge = horariosescalonadosv2.Zeit.NoCyge "
					+ "and horariosescalonadosv2.Zeit.fecha = (select max(fecha) from horariosescalonadosv2.Zeit) "
					+ "where horariosescalonadosv2.Cyge.usuario is not null and horariosescalonadosv2.Cyge.usuario <> '' "
					+ "group by horariosescalonadosv2.Cyge.NoCyge, horariosescalonadosv2.Zeit.Fecha;";

			Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
			query.setResultClass(BeanCumplimientoExternoRRHH.class);
			registrosCumplimientoRRHH = (List<BeanCumplimientoExternoRRHH>)query.execute();

			return registrosCumplimientoRRHH;			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return registrosCumplimientoRRHH;
	}


	public void PersistirEmpleado (List <BeanEmpleado> registrosEmpleados)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
		String strQuery = null;
		try 
		{		

			Transaction tx = pm.currentTransaction();

			tx.begin();
			long startTime = System.currentTimeMillis();
			for (BeanEmpleado registro : registrosEmpleados)
			{
				try
				{
					pm.makePersistent(registro);					
				}

				catch(Exception e)
				{
					e.printStackTrace();
				}
			}

			pm.currentTransaction().commit();
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{

		}
	}
	
	public void PersistirEmpleadoHorario (List <BeanEmpleadoHorario> registrosEmpleados)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
		try 
		{		

			Transaction tx = pm.currentTransaction();

			tx.begin();
			long startTime = System.currentTimeMillis();
			for (BeanEmpleadoHorario registro : registrosEmpleados)
			{
				try
				{
					pm.makePersistent(registro);
					System.out.println(registro);
				}

				catch(Exception e)
				{
					e.printStackTrace();
				}
			}

			pm.currentTransaction().commit();

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{

		}
	}
//---------------------------------------Incumplimiento-----------------------------
	public void InsertarRegistrosIncumplimiento ( List<BeanIncumplimiento> registros)
	{	
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
		try 
		{			

			//TODO: Remover contador de tiempo.	
			Transaction tx = pm.currentTransaction();


			tx.begin();
			long startTime = System.currentTimeMillis();


			for (BeanIncumplimiento registro : registros)
			{

				try
				{
					pm.makePersistent(registro);

				}

				catch(JDOObjectNotFoundException e)
				{
					pm.makePersistent(registro);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{

				}

			}

			pm.currentTransaction().commit();

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void InsertarRegistrosCumplimientoCyge ( List<BeanCumplimientoExternoCyge> registros)
	{	
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
		try 
		{			

			//TODO: Remover contador de tiempo.	
			Transaction tx = pm.currentTransaction();


			tx.begin();
			long startTime = System.currentTimeMillis();


			for (BeanCumplimientoExternoCyge registro : registros)
			{

				try
				{
					pm.makePersistent(registro);

				}

				catch(JDOObjectNotFoundException e)
				{
					pm.makePersistent(registro);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{

				}

			}

			pm.currentTransaction().commit();

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
//------------------------- CUMPLIMIENTO RRHH --------------------------------------------
	
	public void InsertarCumplimientoCygeRH ( List<BeanCumplimientoExternoRRHH> registros)
	{	
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();
		try 
		{			

			//TODO: Remover contador de tiempo.	
			Transaction tx = pm.currentTransaction();


			tx.begin();
			long startTime = System.currentTimeMillis();


			for (BeanCumplimientoExternoRRHH registro : registros)
			{

				try
				{
					pm.makePersistent(registro);

				}

				catch(JDOObjectNotFoundException e)
				{
					pm.makePersistent(registro);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{

				}

			}

			pm.currentTransaction().commit();

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//--------------------REPORTE CUMPLIMIENTO VISTA CYGE -----------------------------------------------------------------
	public List<BeanCumplimientoExternoCyge> ReporteCumplimientoCyge (Date desdeDate, Date hastaDate, String usuario)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		List<BeanCumplimientoExternoCyge> resultados = null;

		String strQuery = null;

		try{
			
			Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.cumplimientoExterno.usuario, "
					+ "horariosescalonadosv2.cumplimientoExterno.ncyge, horariosescalonadosv2.cumplimientoExterno.nombre, horariosescalonadosv2.cumplimientoExterno.direccionGeneral, "
					+ "horariosescalonadosv2.cumplimientoExterno.direccionCorporativa, horariosescalonadosv2.cumplimientoExterno.area, horariosescalonadosv2.cumplimientoExterno.fecha, "
					+ "horariosescalonadosv2.cumplimientoExterno.mes, horariosescalonadosv2.cumplimientoExterno.quincena, horariosescalonadosv2.cumplimientoExterno.entradaOficial,"
					+ "horariosescalonadosv2.cumplimientoExterno.entradaReal, horariosescalonadosv2.cumplimientoExterno.salidaReal, horariosescalonadosv2.cumplimientoExterno.jornada,"
					+ "horariosescalonadosv2.cumplimientoExterno.estancia, horariosescalonadosv2.cumplimientoExterno.edificio, horariosescalonadosv2.cumplimientoExterno.autorizador,"
					+ "horariosescalonadosv2.cumplimientoExterno.provedor, horariosescalonadosv2.cumplimientoExterno.proyecto, horariosescalonadosv2.cumplimientoExterno.estatus,"
					+ "horariosescalonadosv2.cumplimientoExterno.edificioAsignado FROM horariosescalonadosv2.cumplimientoExterno "
					+ "where horariosescalonadosv2.cumplimientoExterno.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte "
					+ "from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' ) "
					+ "and horariosescalonadosv2.cumplimientoExterno.fecha BETWEEN '"+desdeDate+"' AND '"+hastaDate+"' ");
			q.setResultClass(BeanCumplimientoExternoCyge.class);
			resultados = (List<BeanCumplimientoExternoCyge>)q.execute();

			
			//q.declareParameters("String usuario");
				
			/*Query q = pm.newQuery(BeanCumplimientoExternoCyge.class);
			q.setFilter("usuario");
			q.setFilter("horariosescalonadosv2.cumplimientoExterno.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte "
					+ "from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta == usuario)");
			q.declareParameters("String usuario");*/
			
			//registrosCumplimientoCyge = (List<BeanCumplimientoExternoCyge>)q.execute(usuario);
					
			//strQuery = "SELECT " + "*" + "FROM horariosescalonadosv2.CumplimientoExternoRRHH";

			return resultados;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultados;
	}
	

	public void updateSolicitud(TreeMap<String, String[]> solicitudes){
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();	
		Transaction tx = (Transaction) pm.currentTransaction();
		String strQuery = null;
		try{
			tx.begin();
			for (Map.Entry<String, String[]> entry :  solicitudes.entrySet()){
				try{
					String key = entry.getKey();
					String[] arrValues = entry.getValue();
					strQuery = ("UPDATE horariosescalonadosv2.solicitud SET motivo ='"+arrValues[1]+"' WHERE solicitudID = '"+key+"';");
					Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
					query.execute();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(tx.isActive()){
				tx.rollback();
			}
		}
	}
	
	public void InsertarRegistrosRRHH (List<BeanEmpleadoExternoRRHH> registros)
	{

		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		try 
		{		
			//TODO: Remover contador de tiempo.

			Transaction tx = pm.currentTransaction();

			tx.begin();
			long startTime = System.currentTimeMillis();
			for (BeanEmpleadoExternoRRHH registro : registros)
			{
				try
				{
					BeanEmpleadoExternoRRHH registroRRHH = (BeanEmpleadoExternoRRHH) pm.getObjectById(registro.getStrUsuario());

					registroRRHH.setStrArea(registro.getStrArea());
					registroRRHH.setStrDirCorporativa(registro.getStrDirCorporativa());
					registroRRHH.setStrDirGeneral(registro.getStrDirGeneral());
					registroRRHH.setCRArea(registro.getCRArea());
					registroRRHH.setCRDireccionCorporativa(registro.getCRDireccionCorporativa());
					registroRRHH.setCRDireccionGeneral(registro.getCRDireccionGeneral());
					
				}

				catch(JDOObjectNotFoundException e)
				{
					pm.makePersistent(registro);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{

				}
			}

			pm.currentTransaction().commit();

			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
			long inSeconds = (totalTime)/1000;
			System.out.println(inSeconds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public List<BeanEmpleado> updateSupervisor(String selectedIdUser, String usuario){
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		Transaction tx = (Transaction) pm.currentTransaction();
		String strQuery = null;
		List<BeanEmpleado> resultadosBeanEmpleado = null;

		try{

			tx.begin();

			strQuery = ("UPDATE horariosescalonadosv2.empleado "
					+ "SET supervisorID = '" +selectedIdUser+ "' WHERE empleado = '"+usuario+ "'");
			Query query = pm.newQuery("javax.jdo.query.SQL",strQuery);
			query.setResultClass(BeanEmpleado.class);
			resultadosBeanEmpleado = (List<BeanEmpleado>) query.execute();

			return resultadosBeanEmpleado;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally {
			if (tx.isActive()) 
			{
				tx.rollback();
			}
			//pm.close();

		}

		return resultadosBeanEmpleado;
	
	}
	

//-------------------------------------- REPORTE INCUMPLIMIENTO CYGE -------------------------------------
	public List<BeanIncumplimiento> Incumplimiento (Date desdeDate, Date hastaDate, String usuario)
	{
		PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

		String strQuery = null;
		List<BeanIncumplimiento> resultados = null;

		try{

			Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.Incumplimiento.ncyge, horariosescalonadosv2.Incumplimiento.nombre, "
					+ "horariosescalonadosv2.Incumplimiento.direccionGeneral, horariosescalonadosv2.Incumplimiento.direccionCorporativa, "
					+ "horariosescalonadosv2.Incumplimiento.area, horariosescalonadosv2.Incumplimiento.fecha, horariosescalonadosv2.Incumplimiento.mes, "
					+ "horariosescalonadosv2.Incumplimiento.quincena, horariosescalonadosv2.Incumplimiento.entradaOficial, horariosescalonadosv2.Incumplimiento.entradaReal, "
					+ "horariosescalonadosv2.Incumplimiento.salidaReal, horariosescalonadosv2.Incumplimiento.jornada, horariosescalonadosv2.Incumplimiento.estancia, "
					+ "horariosescalonadosv2.Incumplimiento.edificio, horariosescalonadosv2.Incumplimiento.autorizador, horariosescalonadosv2.Incumplimiento.provedor, "
					+ "horariosescalonadosv2.Incumplimiento.proyecto, horariosescalonadosv2.Incumplimiento.estatus, horariosescalonadosv2.Incumplimiento.edificioAsignado "
					+ "From horariosescalonadosv2.Incumplimiento "
					+ "where horariosescalonadosv2.Incumplimiento.usuario in (select horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioReporte "
					+ "from horariosescalonadosv2.PerfilConsultaExternos where horariosescalonadosv2.PerfilConsultaExternos.IdUsuarioConsulta = '"+usuario+"' ) "
					+ "and '"+desdeDate+"' <= horariosescalonadosv2.Incumplimiento.fecha  AND horariosescalonadosv2.Incumplimiento.fech <= '"+hastaDate+"' ");
			q.setResultClass(BeanIncumplimiento.class);
			resultados = (List<BeanIncumplimiento>)q.execute();

			return resultados;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally {
			//pm.close();

		}

		return resultados;
	}
	
	//-------------------------------------- REPORTE INCUMPLIMIENTO CYGE ADMIN-------------------------------------
		public List<BeanIncumplimiento> IncumplimientoAdmin (Date desdeDate, Date hastaDate)
		{
			PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

			String strQuery = null;
			List<BeanIncumplimiento> resultados = null;

			try{
			
				Query q = pm.newQuery("SQL", "SELECT horariosescalonadosv2.Incumplimiento.usuario, horariosescalonadosv2.Incumplimiento.ncyge, horariosescalonadosv2.Incumplimiento.nombre, "
						+ "horariosescalonadosv2.Incumplimiento.direccionGeneral, horariosescalonadosv2.Incumplimiento.direccionCorporativa, "
						+ "horariosescalonadosv2.Incumplimiento.area, horariosescalonadosv2.Incumplimiento.fecha, horariosescalonadosv2.Incumplimiento.mes, "
						+ "horariosescalonadosv2.Incumplimiento.quincena, horariosescalonadosv2.Incumplimiento.entradaOficial, horariosescalonadosv2.Incumplimiento.entradaReal, "
						+ "horariosescalonadosv2.Incumplimiento.salidaReal, horariosescalonadosv2.Incumplimiento.jornada, horariosescalonadosv2.Incumplimiento.estancia, "
						+ "horariosescalonadosv2.Incumplimiento.edificio, horariosescalonadosv2.Incumplimiento.autorizador, horariosescalonadosv2.Incumplimiento.provedor, "
						+ "horariosescalonadosv2.Incumplimiento.proyecto, horariosescalonadosv2.Incumplimiento.estatus, horariosescalonadosv2.Incumplimiento.edificioAsignado "
						+ "From horariosescalonadosv2.Incumplimiento "
						+ "where '"+desdeDate+"' <= horariosescalonadosv2.Incumplimiento.fecha  AND horariosescalonadosv2.Incumplimiento.fecha <= '"+hastaDate+"' ");
				q.setResultClass(BeanIncumplimiento.class);
				resultados = (List<BeanIncumplimiento>)q.execute();
				
				return resultados;

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			finally {
				//pm.close();

			}

			return resultados;
		}
		
		public List<BeanEmpleadoExterno> EJEMPO ()
		{
			PersistenceManager pm = DatanucleusPersistenceManager.getInstance().getPersistenceManager();

			String strQuery = null;
			List<BeanEmpleadoExterno> resultados = null;

			try{
			
				Query q = pm.newQuery("SQL", "horariosescalonadosv2`.`empleadoExterno` (`NoCyge`, `Usuario`, `ApePaterno`, `ApeMaterno`, `Nombre`, `DirGeneral`, `DirCorporativa`, `Area`, `EntOficial`, `AutorizadorID`, `Autorizador`, `Proveedor`, `Proyecto`, `Estatus`, `EspacioFisico`, `LugarAsignadoEdificio`, `Email`) VALUES ");
				q.setResultClass(BeanEmpleadoExterno.class);
				resultados = (List<BeanEmpleadoExterno>)q.execute();
				
				return resultados;

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			finally {
				//pm.close();

			}

			return resultados;
		}
		

}