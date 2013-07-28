package com.provisiones.ll;

import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ReferenciaCatastral;

public class CLReferencias 
{
	static String sClassName = CLComunidades.class.getName();
	
	public static boolean actualizaReferenciaLeida(String linea)
	{
		String sMethod = "actualizaReferenciaLeida";

		boolean bSalida = false;
		
		ReferenciaCatastral referencia = Parser.leerReferenciaCatastral(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (referencia.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = referencia.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = referencia.getCOTDOR();
			sBKOBDEER = referencia.getOBDEER();
			referencia.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		referencia.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodReferencia = referencia.getNURCAT();
		
		bSalida = !(sCodReferencia.equals(""));
		
		if (bSalida)
		{
		
			referencia.setCOTDOR(sBKCOTDOR);
			referencia.setCOTDOR(sBKOBDEER);
			
			bSalida = QMReferencias.modReferenciaCatastral(referencia, sCodReferencia,sValidado);
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
