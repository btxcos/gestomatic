package com.provisiones.test;

import java.io.IOException;

import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;

public class TestComunidades 
{
	static String sClassName = TestComunidades.class.getName();
	
	public static void main(String[] args) throws IOException 
	{
		boolean bTraza = true;
		String sMethod = "testCC";
		
		//FileManager.splitter("168E1.txt");
		

		Utils.debugTrace(bTraza, sClassName, sMethod, "Correo|"+CLProvisiones.provisionAsignada("3100058")+"|");
		
		
		
	}
}
