package com.provisiones.test;

import java.io.IOException;
import java.util.Calendar;

import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;


public class TestComunidades 
{
	static String sClassName = TestComunidades.class.getName();

	public static void main(String[] args) throws IOException 
	{
		boolean bTraza = true;
		String sMethod = "testCC";
		
		//FileManager.splitter("168E1.txt");
		
		Calendar fecha = Calendar.getInstance();
		
		
		int iA�o2 = fecha.get(Calendar.YEAR)%100;
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "iA�o2:|"+iA�o2+"|"+fecha.get(Calendar.YEAR));
		
		String sProvision = "11" + fecha.get(Calendar.YEAR)%100 + ValoresDefecto.DEF_COREAE + "000";
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "sProvision:|"+sProvision+"|");
		
		int iAmpliacion = Integer.parseInt(sProvision.substring(0, 2));
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "iAmpliacion:|"+iAmpliacion+"|");
		
		int iA�o = Integer.parseInt(sProvision.substring(2, 4));
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "iA�o:|"+iA�o+"|");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Provision:|"+CLProvisiones.estaCerrada("001304000")+"|");

		//Utils.debugTrace(bTraza, sClassName, sMethod, "Provision:|"+CLProvisiones.ultimaProvisionCerrada("9999")+"|");
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Provision:|"+CLProvisiones.provisionAsignada("3100058")+"|");
		
		//Utils.inicializarDirectorios();
		
		//FileManager.escribirCierres();
		
		/*try{
		      String executionPath = System.getProperty("user.dir");
		      System.out.print("Executing at =>"+executionPath);
		    }catch (Exception e){
		      System.out.println("Exception caught ="+e.getMessage());
		    }*/

		
		//CLProvisiones.provisionAsignada("3101105");
		
	}
}
