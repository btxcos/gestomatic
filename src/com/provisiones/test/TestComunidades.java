package com.provisiones.test;

import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.ValoresDefecto;


public class TestComunidades 
{
	private static Logger logger = LoggerFactory.getLogger(TestComunidades.class.getName());

	public static void main(String[] args) throws IOException 
	{
	
		//FileManager.splitter("168E1.txt");
		
		Calendar fecha = Calendar.getInstance();
		
		
		int iA�o2 = fecha.get(Calendar.YEAR)%100;
		
		logger.debug("iA�o2:|"+iA�o2+"|"+fecha.get(Calendar.YEAR));
		
		String sProvision = "11" + fecha.get(Calendar.YEAR)%100 + ValoresDefecto.DEF_COREAE + "000";
		
		logger.debug("sProvision:|"+sProvision+"|");
		
		int iAmpliacion = Integer.parseInt(sProvision.substring(0, 2));
		
		logger.debug("iAmpliacion:|"+iAmpliacion+"|");
		
		int iA�o = Integer.parseInt(sProvision.substring(2, 4));
		
		logger.debug("iA�o:|"+iA�o+"|");
		
		logger.debug("Provision:|"+CLProvisiones.estaCerrada("001304000")+"|");

		//logger.debug("Provision:|"+CLProvisiones.ultimaProvisionCerrada("9999")+"|");
		
		logger.debug("Provision:|"+CLProvisiones.provisionAsignada("3100058")+"|");
		
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
