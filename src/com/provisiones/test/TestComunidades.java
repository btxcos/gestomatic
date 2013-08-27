package com.provisiones.test;

import java.io.IOException;

import com.provisiones.misc.Utils;

public class TestComunidades 
{
	static String sClassName = TestComunidades.class.getName();
	
	public static void main(String[] args) throws IOException 
	{
		boolean bTraza = true;
		String sMethod = "testCC";
		
		//FileManager.splitter("168E1.txt");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "CC|"+Utils.compruebaCC("1111", "1111", "30", "1111111111")+"|");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "CIF|"+Utils.compruebaCIF("0")+"|");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Correo|"+Utils.compruebaCorreo("a@b.com")+"|");
	}
}
