package com.provisiones.test;

import com.provisiones.misc.Utils;

public class TestConstantes 
{
	
	static String sClassName = TestConstantes.class.getName();

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		boolean bTraza = true;
		String sMethod = "testConstantes";
		
		//Parser.escribirActivo(activo);
		
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Generando ficheros...");
		
		String sPrueba = "XXXXXXXXXXXXXXXXX%sXXXXXXXXXXX";
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "|"+String.format(sPrueba,"Hola")+"|");

	}

}
