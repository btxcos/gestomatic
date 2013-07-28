package com.provisiones.ll;

import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.QMListaImpuestos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ImpuestoRecurso;

public class CLImpuestos 
{
	static String sClassName = CLImpuestos.class.getName();
	
	public static boolean actualizaImpuestoLeido(String linea)
	{
		String sMethod = "actualizaImpuestoLeido";

		boolean bSalida = false;
		
		ImpuestoRecurso impuesto = Parser.leerImpuestoRecurso(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (impuesto.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = impuesto.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = impuesto.getCOTDOR();
			sBKOBDEER = impuesto.getOBDEER();
			impuesto.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		impuesto.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodImpuesto = QMImpuestos.getImpuestoRecursoID(impuesto);
		
		bSalida = !(sCodImpuesto.equals(""));
		
		if (bSalida)
		{
		
			impuesto.setCOTDOR(sBKCOTDOR);
			impuesto.setCOTDOR(sBKOBDEER);
			
			QMImpuestos.modImpuestoRecurso(impuesto, sCodImpuesto);
			QMListaImpuestos.setValidado(sCodImpuesto, sValidado);
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
