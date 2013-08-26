package com.provisiones.test;

import java.io.IOException;

import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;

public class TestComunidades 
{
	static String sClassName = TestComunidades.class.getName();
	
	public static void main(String[] args) throws IOException 
	{
		boolean bTraza = true;
		String sMethod = "testCC";
		
		//FileManager.splitter("168E1.txt");
		
		if(Utils.compruebaCC("1111", "1111", "30", "1111111111"))
		{
			Utils.debugTrace(bTraza, sClassName, sMethod, "|"+Utils.compruebaCC("2038", "2409", "64", "3000866897")+"|");

			//Utils.debugTrace(bTraza, sClassName, sMethod, "Valido!");
		}
	}
}
