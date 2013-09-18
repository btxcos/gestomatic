package com.provisiones.ll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMActivos;

import com.provisiones.misc.Parser;
import com.provisiones.types.Activo;
import com.provisiones.types.ActivoTabla;


public class CLActivos 
{
	private static Logger logger = LoggerFactory.getLogger(CLActivos.class.getName());
	
	
	public static ArrayList<ActivoTabla> buscarActivos (ActivoTabla activobuscado)
	{
			
		return QMActivos.buscaActivos(activobuscado);
	}

	public static boolean compruebaActivo (String sCodCOACES)
	{
			
		return QMActivos.existeActivo(sCodCOACES);
	}
	
	public static String compruebaTipoActivoSAREB (String sCodCOACES)
	{

		String sTipo = "#";
		
		logger.debug("sCodCOACES:|{}|",sCodCOACES);

		if (QMActivos.getSociedadPatrimonial(sCodCOACES).equals("9999"))
		{
			if (QMActivos.getCOTSINActivo(sCodCOACES).startsWith("SU"))
			{
				sTipo = "S"; //SUELOS Y OBRA EN CURSO 
			}
			else if (QMActivos.getBIARREActivo(sCodCOACES).equals("S"))
			{
				sTipo = "A"; //ARRENDAMIENTOS
			}
			else
			{
				sTipo = "T"; //PRODUCTO TERMINADO
			}
		}
		logger.debug("sTipo:|{}|",sTipo);
		return sTipo;
	}
	
	public static String sociedadPatrimonialAsociada (String sCodCOACES)
	{
			
		return QMActivos.getSociedadPatrimonial(sCodCOACES);
	}
	
	public static boolean actualizaActivoLeido(String linea)
	{
		boolean bSalida = false;
		
		Activo activo = Parser.leerActivo(linea);
		
		String sCodActivo =  activo.getCOACES();
				
		bSalida = QMActivos.addActivo(activo);
		
		logger.debug("sCodActivo:|{}|",sCodActivo);
		
	
		bSalida =  bSalida && !sCodActivo.equals("");
		
		if (!bSalida)
		{
			logger.warn("El siguiente registro ya se encuentre en el sistema:");
			logger.warn("|{}|",linea);
		}
		
		return bSalida;
	}

}
