package com.provisiones.ll;

import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMListaCuotas;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;

public class CLCuotas 
{
  
	static String sClassName = CLCuotas.class.getName();
	
	public static boolean actualizaCuotaLeida(String linea)
	{
		String sMethod = "actualizaCuotaLeida";

		boolean bSalida = false;
		
		Cuota cuota = Parser.leerCuota(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (cuota.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = cuota.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = cuota.getCOTDOR();
			sBKOBDEER = cuota.getOBDEER();
			cuota.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		cuota.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodCuota = QMCuotas.getCuotaID(cuota);
		
		bSalida = !(sCodCuota.equals(""));
		
		if (bSalida)
		{
		
			cuota.setCOTDOR(sBKCOTDOR);
			cuota.setCOTDOR(sBKOBDEER);
			
			QMCuotas.modCuota(cuota, sCodCuota);
			QMListaCuotas.setValidado(sCodCuota, sValidado);
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
