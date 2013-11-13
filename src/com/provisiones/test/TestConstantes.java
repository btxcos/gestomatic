package com.provisiones.test;

import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class TestConstantes 
{
	
	//private static Logger logger = LoggerFactory.getLogger(TestConstantes.class.getName());


	public static void main(String[] args) 
	{


		
		String sPrueba = "LIB_20131107_1.txt";
		
		if (sPrueba.toUpperCase().matches("(LIB_)[0-9]{8}(_)[0-9]+(\\.TXT)$"))
		{
		
			System.out.println("|"+sPrueba+"|");
		}
		else
			System.out.println("|KK|");

	}

}
