package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.misc.Parser;
import com.provisiones.types.Activo;
import com.provisiones.types.ActivoTabla;


public class CLActivos 
{
	static String sClassName = CLActivos.class.getName();
	
	
	public static ArrayList<ActivoTabla> buscarActivos (ActivoTabla activobuscado)
	{
			
		return QMActivos.buscaActivos(activobuscado);
	}

	public static boolean compruebaActivo (String sCodCOACES)
	{
			
		return QMActivos.existeActivo(sCodCOACES);
	}
	
	public static boolean actualizaActivoLeido(String linea)
	{
		String sMethod = "actualizaActivoLeido";

		boolean bSalida = false;
		
		Activo activo = Parser.leerActivo(linea);
		
		String sCodActivo =  activo.getCOACES();
				
		bSalida = QMActivos.addActivo(activo);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sCodActivo|"+sCodActivo+"|");
		
		/*
		QMActivos.getActivo(activo.getCOACES()).pintaActivo();
		
		activo.setNUINMU("666");
		
		QMActivos.modActivo(activo, activo.getCOACES());
		
		QMActivos.getActivo(activo.getCOACES()).pintaActivo();
		
		QMActivos.delActivo(activo.getCOACES());
		
		//*/
		
		bSalida =  bSalida && !sCodActivo.equals("");
		
		if (!bSalida)
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro ya se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
		}
		
		return bSalida;
	}

}
