package com.provisiones.ll;

import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;


public class CLComunidades 
{
	static String sClassName = CLComunidades.class.getName();
	
	public static boolean actualizaComunidadLeida(String linea)
	{
		String sMethod = "actualizaComunidadLeida";

		boolean bSalida = false;
		
		Comunidad comunidad = Parser.leerComunidad(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (comunidad.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = comunidad.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = comunidad.getCOTDOR();
			sBKOBDEER = comunidad.getOBDEER();
			comunidad.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		comunidad.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodComunidad = comunidad.getNUDCOM();
		
		bSalida = !(sCodComunidad.equals(""));
		
		if (bSalida)
		{
		
			comunidad.setCOTDOR(sBKCOTDOR);
			comunidad.setCOTDOR(sBKOBDEER);
			
			bSalida = QMComunidades.modComunidad(comunidad, sCodComunidad,sValidado);
		}
		else 
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
}
