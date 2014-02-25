package com.provisiones.test;

import java.math.BigInteger;

import com.provisiones.misc.Utils;


@SuppressWarnings("unused")
public class TestConstantes 
{
	
	//private static Logger logger = LoggerFactory.getLogger(TestConstantes.class.getName());


	public static void main(String[] args) 
	{
		
		String sPais = "ES";
		String sNUCCEN = "0012";
		String sNUCCOF = "0345";
		String sNUCCDI = "03";
		String sNUCCNT = "0000067890";
        
        String sValorCompleto = sNUCCEN+sNUCCOF+sNUCCDI+sNUCCNT+(sPais.charAt(0)-55)+(sPais.charAt(1)-55)+"00";
 
        BigInteger biValorCompleto = new BigInteger(sValorCompleto);
       
        BigInteger biISO7604 = new BigInteger("97");

        int iDCIBAN = 98 - biValorCompleto.mod(biISO7604).intValue();
        
        System.out.println("iDCIBAN:"+iDCIBAN);
        
        String sPrefijo = (iDCIBAN > 9) ? "":"0";
        
        String sDCIBAN =  sPrefijo+iDCIBAN;
        
        System.out.println("sDCIBAN:"+sDCIBAN);
        
        
        System.out.println("SEPA:"+sPais+sDCIBAN+sNUCCEN+sNUCCOF+sNUCCDI+sNUCCNT);

	}

}
