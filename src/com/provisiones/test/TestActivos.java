package com.provisiones.test;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.ll.CLComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;


public class TestActivos 
{
	private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	static boolean bTraza = true;


	
	public static void main(String[] args) throws IOException 
	{
		
		String sNumero1 = "00000000000000000000000000000123";
		String sNumero2 = sNumero1;
		
		/*String sResultado1 = "";
		String sResultado2 = "";
		
		Utils.debugTrace(true, "TEST", "TEST", "PARSE INT");
		
		Utils.debugTrace(true, "TEST", "TEST", "|"+Utils.timeStamp()+"|");
		
		sNumero1 = String.valueOf(Integer.parseInt(sNumero1));
		
		Utils.debugTrace(true, "TEST", "TEST", "|"+Utils.timeStamp()+"|");
		
		Utils.debugTrace(true, "TEST", "TEST", "WHILE");		
		
		Utils.debugTrace(true, "TEST", "TEST", "|"+Utils.timeStamp()+"|");
		
        while ((sNumero2.startsWith("0")) && (sNumero2.length() > 1) ) 
        {
        	sNumero2=sNumero2.substring(1);
        }
   
        Utils.debugTrace(true, "TEST", "TEST", "|"+Utils.timeStamp()+"|");*/
		
		
		//Utils.debugTrace(true, "TEST", "TEST", "|"+CLComunidades.buscarComunidad("3100058").logComunidad()+"|");
		
		//FileManager.splitter("168AC3.txt");
		
		
		//QMActivos.addActivo(NuevoActivo);

		        
		 //Calendar ahoraCal = Calendar.getInstance();

		        
		 //logger.debug("|"+QMListaComunidadesActivos.activoVinculadoComunidad("3106403")+"|");
		
		Utils.debugTrace(true, "TEST", "TEST", "|"+sNumero1+"|"+sNumero2+"|");

	}
}
