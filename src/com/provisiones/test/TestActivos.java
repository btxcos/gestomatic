package com.provisiones.test;

import java.io.IOException;
import java.util.Calendar;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class TestActivos 
{
	static String sClassName = TestActivos.class.getName();
	
	static boolean bTraza = true;


	
	public static void main(String[] args) throws IOException 
	{
		String sMethod = "main";
		
		//FileManager.splitter("168AC3.txt");
		
		
		//QMActivos.addActivo(NuevoActivo);

		        
		 Calendar ahoraCal = Calendar.getInstance();

		        
		 Utils.debugTrace(bTraza, sClassName, sMethod,"|"+QMListaComunidadesActivos.activoVinculadoComunidad("3106403")+"|");

	}
}
