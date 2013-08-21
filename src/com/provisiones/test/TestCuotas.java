package com.provisiones.test;

import com.provisiones.ll.CLProvisiones;
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
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+CLProvisiones.provisionAsignada("3109139")+"|");
	}
}
