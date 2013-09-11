package com.provisiones.test;

import java.io.IOException;

import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;


public class TestComunidades 
{
	static String sClassName = TestComunidades.class.getName();
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException 
	{
		boolean bTraza = true;
		String sMethod = "testCC";
		
		//FileManager.splitter("168E1.txt");
		

		//Utils.debugTrace(bTraza, sClassName, sMethod, "Correo|"+CLProvisiones.provisionAsignada("3100058")+"|");
		
		Utils.inicializarDirectorios();
		
		FileManager.escribirCierres();
		
		/*try{
		      String executionPath = System.getProperty("user.dir");
		      System.out.print("Executing at =>"+executionPath);
		    }catch (Exception e){
		      System.out.println("Exception caught ="+e.getMessage());
		    }*/

		
	}
}
