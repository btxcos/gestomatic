package com.provisiones.ll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.registros.QMRegistroActivos;

import com.provisiones.misc.Parser;
import com.provisiones.types.Activo;
import com.provisiones.types.tablas.ActivoTabla;

public final class CLActivos 
{
	private static Logger logger = LoggerFactory.getLogger(CLActivos.class.getName());
	
	private CLActivos(){}

	//Interfaz básico
	public static Activo buscarActivo (int iCodCOACES)
	{
		return QMActivos.getActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static Activo buscarDetallesActivo (int iCodCOACES)
	{
		return QMActivos.getDetallesActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static String buscarFechaVentaActivo (int iCodCOACES)
	{
		return QMActivos.getFEVACTActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static ArrayList<ActivoTabla> buscarActivos (ActivoTabla filtro)
	{
		return QMActivos.buscaListaActivos(ConnectionManager.getDBConnection(),filtro);
	}

	public static ArrayList<ActivoTabla> buscarActivosConFichaInmovilizado (ActivoTabla activobuscado)
	{
		return QMActivos.buscaListaActivosPorInmovilizado(ConnectionManager.getDBConnection(),activobuscado, true);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosSinFichaInmovilizado (ActivoTabla activobuscado)
	{
		return QMActivos.buscaListaActivosPorInmovilizado(ConnectionManager.getDBConnection(),activobuscado, false);
	}
	
	public static ArrayList<ActivoTabla> buscarActivoUnico (int iCodCOACES)
	{
		return QMActivos.buscaActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}

	public static ArrayList<ActivoTabla> buscarListaActivosConReferencia (ActivoTabla activo)
	{
		return QMActivos.buscaListaActivosReferencias(ConnectionManager.getDBConnection(),activo);
	}
	
	public static boolean existeActivo (int iCodCOACES)
	{
		return QMActivos.existeActivo(ConnectionManager.getDBConnection(),iCodCOACES);
	}

	public static String referenciaCatastralAsociada (int iCodCOACES)
	{
		return QMActivos.getReferenciaCatastral(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static String sociedadPatrimonialAsociada (int iCodCOACES)
	{
		return QMActivos.getSociedadPatrimonial(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	//Gestion de Notas
	public static String buscarNota (int iCodCOACES)
	{
		return QMRegistroActivos.getNota(ConnectionManager.getDBConnection(),iCodCOACES);
	}
	
	public static boolean guardarNota (int iCodCOACES, String sNota)
	{
		return QMRegistroActivos.setNota(ConnectionManager.getDBConnection(),iCodCOACES, sNota);
	}
	
	//Interfaz avanzado
	public static int actualizaActivoLeido(String linea)
	{
		int iCodigo = -5;//Error de conexion
		
		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			iCodigo = 0;
			
			Activo activo = Parser.leerActivo(linea);
			
			int iCodCOACES =  Integer.parseInt(activo.getCOACES());
			
			//logger.debug("sCodActivo:|"+sCodActivo+"|");

			try 
			{
				conexion.setAutoCommit(false);
				
				if (!QMActivos.existeActivo(conexion,iCodCOACES))
				{
					if (QMActivos.addActivo(conexion,activo))
					{
						//logger.info("Nuevo Activo registrado.");
						if (QMRegistroActivos.addRegistroActivo(conexion, iCodCOACES))
						{
							conexion.commit();
						}
						else
						{
							iCodigo = -3;
							conexion.rollback();
						}
						
					}
					else
					{
						iCodigo = -1;
						conexion.rollback();
					}
				}
				else
				{
					if (!QMActivos.compruebaActivo(conexion,activo))
					{
						if (QMActivos.modActivo(conexion,activo,iCodCOACES))
						{
							//logger.info("Activo actualizado.");
							if (QMRegistroActivos.modRegistroActivo(conexion, iCodCOACES))
							{
								iCodigo = 1;
								conexion.commit();
							}
							else
							{
								iCodigo = -4;
								conexion.rollback();
							}
						}
						else
						{
							iCodigo = -2;
							conexion.rollback();
						}
					}
					else
					{
						//logger.warn("El siguiente registro ya se encuentra en el sistema:");
						if (QMRegistroActivos.modRegistroActivo(conexion, iCodCOACES))
						{
							iCodigo = 2;
							conexion.commit();
						}
						else
						{
							iCodigo = -4;
							conexion.rollback();
						}
					}
				}
				conexion.setAutoCommit(true);
			} 
			catch (SQLException e) 
			{
				//error de conexion con base de datos.
				iCodigo = -5;

				try 
				{
					//reintentamos
					conexion.rollback();
					conexion.setAutoCommit(true);
					conexion.close();
				} 
				catch (SQLException e1) 
				{
					try 
					{
						conexion.close();
					}
					catch (SQLException e2) 
					{
						logger.error("[FATAL] Se perdió la conexión de forma inesperada.");
					}
				}
				
				
			}


		}
				
		return iCodigo;
	}	
	
	public static String compruebaTipoActivoSAREB (int iCodCOACES)
	{
		String sTipo = "";

		Connection conexion = ConnectionManager.getDBConnection();
		
		if (conexion != null)
		{
			sTipo = "#";
			
			logger.debug("iCodCOACES:|"+iCodCOACES+"|");
			
			String sCOSPAT = QMActivos.getSociedadPatrimonial(conexion,iCodCOACES);

			if (sCOSPAT.equals("9999") || sCOSPAT.equals("9998"))
			{
				if (QMActivos.getCOTSINActivo(conexion,iCodCOACES).startsWith("SU"))
				{
					//SUELOS Y OBRA EN CURSO
					sTipo = "S";
				}
				else if (QMActivos.getBIARREActivo(conexion,iCodCOACES).equals("S"))
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
