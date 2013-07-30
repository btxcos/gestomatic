package com.provisiones.ll;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMDatosActivos;
import com.provisiones.misc.Parser;
import com.provisiones.types.Activo;
import com.provisiones.types.DatosActivo;


public class CLActivos 
{
	static String sClassName = CLActivos.class.getName();
	
	public static boolean actualizaActivoLeido(String linea)
	{
		String sMethod = "actualizaActivoLeido";

		boolean bSalida = false;
		
		DatosActivo activo = Parser.leerActivo(linea);
		
		String sCodActivo = QMActivos.addDatosActivo(activo);
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sCodActivo|"+sCodActivo+"|");
		QMActivos.addActivo(new Activo(activo.getCOACES(), sCodActivo, sMethod, sMethod));
		
		if (!bSalida)
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro ya se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
		}
		
		return bSalida;
	}

}
