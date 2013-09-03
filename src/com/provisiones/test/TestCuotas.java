package com.provisiones.test;

import java.io.File;
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
		String sMethod = "testFicheros";
		
		//Parser.escribirActivo(activo);
		
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Generando ficheros...");
		
		
		String sArchivo = "ID_168e1.txt";
		
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+sArchivo.substring(sArchivo.length()-9)+"|");
		
		if (sArchivo.substring(sArchivo.length()-9).toUpperCase().matches("(168)(AC|RG|PA|GA|PP|E1|E2|E3|E4)(\\.TXT)$"))
		{
			Utils.debugTrace(bTraza, sClassName, sMethod, "|"+sArchivo+"| Reconocido!");
		}
		else
		{
			Utils.debugTrace(bTraza, sClassName, sMethod, "|"+sArchivo+"| NO Reconocido.");
		}
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+File.separator+"|");

		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+Utils.fechaDeHoy(true)+"|");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+Utils.timeStamp()+"|");
		
	
		
	}
}
