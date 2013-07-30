package com.provisiones.ll;

import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMListaGastos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;

public class CLGastos 
{

	static String sClassName = CLGastos.class.getName();
	
	public static boolean actualizaGastoLeido(String linea)
	{
		String sMethod = "actualizaGastoLeido";

		boolean bSalida = false;
		
		Gasto gasto = Parser.leerGasto(linea);
		String sCodGastos = "";
		
		String sValidado = "";
		String sBKCOTERR = "";
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando gasto leido...");
		
		if (gasto.getCOTERR().equals(ValoresDefecto.DEF_COTERR))
			sValidado = "V";
		else
		{
			sValidado = "X";
			sBKCOTERR = gasto.getCOTERR();
		}
		
		sCodGastos = QMGastos.getGastoID(gasto);
		//lista_rechazados.add(sCodGastos);
		
		
		bSalida = !(sCodGastos.equals(""));
		
		if (bSalida)
		{
		
			QMGastos.modGasto(gasto, sCodGastos);
			QMListaGastos.setValidado(sCodGastos, sValidado);
		}
		else 
		{
			QMGastos.addGasto(gasto);
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		
		return bSalida;
	}
	
}
