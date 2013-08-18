package com.provisiones.test;

import com.provisiones.dal.qm.listas.QMListaCuotas;

public class TestCuotas {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		//Cuota cuota1 
		//QMListaCuotas.buscaCuotasActivo("3109139");
		
		String sOK = "1,10";
		String sError  = "-100.25";
		
		if (sOK.matches("[\\d]+([\\.|,][\\d]{2})?$"))
			

			System.out.println("Found good SSN: " + sOK);

		
		if (sError.matches("-[\\d]+([\\.|,][\\d]{2})?$"))
			System.out.println("Found good SSN: " + sError);
		
		
		//"[0-9]+([\\.|,][0-9]{2})?$"
	}

}
