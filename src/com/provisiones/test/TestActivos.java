package com.provisiones.test;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;


public class TestActivos 
{
	private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	static boolean bTraza = true;


	
	public static void main(String[] args) throws IOException 
	{
		
		//FileManager.splitter("168AC3.txt");
		
		
		//QMActivos.addActivo(NuevoActivo);

		        
		 //Calendar ahoraCal = Calendar.getInstance();

		        
		 logger.debug("|"+QMListaComunidadesActivos.activoVinculadoComunidad("3106403")+"|");

	}
}
