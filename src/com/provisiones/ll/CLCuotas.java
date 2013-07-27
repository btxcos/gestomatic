package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMListaCuotas;
import com.provisiones.dal.qm.QMListaImpuestos;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;

public class CLCuotas 
{
  
	static String sClassName = CLCuotas.class.getName();
	
	public static boolean actualizaCuota(Cuota NuevaCuota)
	{
		String sMethod = "actualizaCuota";

		boolean bSalida = false;
		
		String sCodCuota = "";
		
		Cuota CuotaAntigua = new Cuota(                  
				NuevaCuota.getCODTRN(), 
				ValoresDefecto.DEF_COTDOR, 
				NuevaCuota.getIDPROV(), 
				NuevaCuota.getCOACCI(), 
				NuevaCuota.getCOCLDO(), 
				NuevaCuota.getNUDCOM(), 
				NuevaCuota.getCOENGP(), 
				NuevaCuota.getCOACES(), 
				NuevaCuota.getCOGRUG(), 
				NuevaCuota.getCOTACA(), 
				NuevaCuota.getCOSBAC(), 
				NuevaCuota.getBITC11(), 
				NuevaCuota.getFIPAGO(), 
				NuevaCuota.getBITC12(), 
				NuevaCuota.getFFPAGO(), 
				NuevaCuota.getBITC13(), 
				NuevaCuota.getIMCUCO(), 
				NuevaCuota.getBITC14(), 
				NuevaCuota.getFAACTA(), 
				NuevaCuota.getBITC15(), 
				NuevaCuota.getPTPAGO(), 
				NuevaCuota.getBITC09(), 
				NuevaCuota.getOBTEXC(), 
				ValoresDefecto.DEF_OBDEER);

		
		ArrayList<String> lista_cuotas = QMListaCuotas.getCuotas(NuevaCuota.getCOACES(), NuevaCuota.getNUDCOM());
		
		
		for(int i=1; i < lista_cuotas.size() ; i++)
		{
			sCodCuota = lista_cuotas.get(i);
			Cuota cuotaTemp = QMCuotas.getCuota(sCodCuota);
			bSalida = cuotaTemp.equals(CuotaAntigua);
			if (bSalida)
			{
				QMCuotas.modCuota(NuevaCuota, sCodCuota);
				System.out.println("["+sClassName+"."+sMethod+"] Cuota encontrada!");
			}
		}
		if (bSalida == false) 
		{
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
}
