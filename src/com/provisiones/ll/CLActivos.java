package com.provisiones.ll;

import java.sql.Connection;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;

import com.provisiones.misc.Parser;
import com.provisiones.types.Activo;
import com.provisiones.types.tablas.ActivoTabla;


public class CLActivos 
{
	private static Logger logger = LoggerFactory.getLogger(CLActivos.class.getName());
	
	//Interfaz básico
	public static Activo buscarActivo (String sCodCOACES)
	{
		return QMActivos.getActivo(ConnectionManager.getDBConnection(),sCodCOACES);
	}
	
	public static ArrayList<ActivoTabla> buscarActivos (ActivoTabla activobuscado)
	{
		return QMActivos.buscaListaActivos(ConnectionManager.getDBConnection(),activobuscado);
	}

	public static ArrayList<ActivoTabla> buscarListaActivosConReferencia (ActivoTabla activo)
	{
		return QMActivos.buscaListaActivosReferencias(ConnectionManager.getDBConnection(),activo);
	}
	
	public static boolean existeActivo (String sCodCOACES)
	{
		return QMActivos.existeActivo(ConnectionManager.getDBConnection(),sCodCOACES);
	}

	public static String referenciaCatastralAsociada (String sCodCOACES)
	{
		return QMActivos.getReferenciaCatastral(ConnectionManager.getDBConnection(),sCodCOACES);
	}
	
	public static String sociedadPatrimonialAsociada (String sCodCOACES)
	{
		return QMActivos.getSociedadPatrimonial(ConnectionManager.getDBConnection(),sCodCOACES);
	}
	
	//Interfaz avanzado
	public static int actualizaActivoLeido(String linea)
	{
		int iCodigo = 0;
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			Activo activo = Parser.leerActivo(linea);
			
			String sCodActivo =  activo.getCOACES();
			
			logger.debug("sCodActivo:|"+sCodActivo+"|");
			
			if (!QMActivos.existeActivo(conexion,sCodActivo))
			{
				if (QMActivos.addActivo(conexion,activo))
				{
					logger.info("Nuevo Activo registrado.");
				}
				else
				{
					iCodigo = -2;
				}
			}
			else
			{
				if (!QMActivos.compruebaActivo(conexion,activo))
				{
					if (QMActivos.modActivo(conexion,activo,sCodActivo))
					{
						logger.info("Activo actualizado.");
						iCodigo = 1;
					}
					else
					{
						iCodigo = -1;
					}
				}
				else
				{
					logger.warn("El siguiente registro ya se encuentre en el sistema:");
					logger.warn("|"+linea+"|");
					
					iCodigo = 2;
				}
			}
		}
				
		return iCodigo;
	}	
	
	public static String compruebaTipoActivoSAREB (String sCodCOACES)
	{
		String sTipo = "";

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			sTipo = "#";
			
			logger.debug("sCodCOACES:|"+sCodCOACES+"|");

			if (QMActivos.getSociedadPatrimonial(conexion,sCodCOACES).equals("9999"))
			{
				if (QMActivos.getCOTSINActivo(conexion,sCodCOACES).startsWith("SU"))
				{
					//SUELOS Y OBRA EN CURSO
					sTipo = "S";
				}
				else if (QMActivos.getBIARREActivo(conexion,sCodCOACES).equals("S"))
				{
					//ARRENDAMIENTOS
					sTipo = "A";
				}
				else
				{
					//PRODUCTO TERMINADO
					sTipo = "T";
				}
			}
		}
		
		logger.debug("sTipo:|"+sTipo+"|");
		return sTipo;
	}

}
