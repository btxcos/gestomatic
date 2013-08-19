package com.provisiones.test;

import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.misc.Utils;

public class TestCuotas {
	static String sClassName = TestCuotas.class.getName();

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		//Cuota cuota1 
		//QMListaCuotas.buscaCuotasActivo("3109139");
		
		boolean bTraza = true;
		String sMethod = "compruebaImporte";
		
		
		String sImporte = "";
		String sImporteReal = "#";
		
		String sSeparador = "";
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Importe:|"+sImporte+"|");
		
		if (sImporte.matches("-?[\\d]+([\\.|,][\\d][\\d]?)?$"))
		{
			//sImporteReal = sImporte.replace(".", "");
			//sImporteReal = sImporteReal.replace(".", "");
			
			if (sImporte.contains("."))
			{
				sSeparador = "\\.";
			}
			else if (sImporte.contains(","))
			{
				sSeparador = ",";
			}
			
			if (!sSeparador.equals(""))
			{
				String[] arrayimporte = sImporte.split(sSeparador);
				String sEuros = arrayimporte[0];
				String sCentimos = arrayimporte[1];
				if (sCentimos.length()<2)
				{
					sCentimos = sCentimos +"0";
				}
				
				Utils.debugTrace(bTraza, sClassName, sMethod, "sEuros:|"+sEuros+"|");
				Utils.debugTrace(bTraza, sClassName, sMethod, "sCentimos:|"+sCentimos+"|");
			
				sImporteReal = sEuros + sCentimos;
			}
			else
			{
				sImporteReal = sImporte;
			}
		}
		else if (sImporte.equals(""))
		{
			sImporteReal= "0";
		}
		Utils.debugTrace(bTraza, sClassName, sMethod, "Importe Real:|"+sImporteReal+"|");
	}

}
