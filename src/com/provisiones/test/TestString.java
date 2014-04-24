package com.provisiones.test;

import com.provisiones.misc.Utils;

public class TestString{

    public static void main( String[] args )
    {
    /*	
    int i = 0;
    long prev_time = System.currentTimeMillis();
    long time;
    
    int n = 1000000;//12582911;
    */


    /*String s = "";
    for( i = 0; i < n; i++)
    {
       s = s + "zim";
    }
    time = System.currentTimeMillis() - prev_time;

    System.out.println("Tamaño del String: " + s.length());
    System.out.println("Tiempo del +: " + time);

    s = "";
    prev_time = System.currentTimeMillis();
    for( i = 0; i < n; i++)
    {
        s = String.format("zim%s", s);
    }
    time = System.currentTimeMillis() - prev_time;
    System.out.println("Tamaño del String: " + s.length());
    System.out.println("Tiempo del String.format: " + time);

   
    StringBuffer sbuffer = new StringBuffer();
    prev_time = System.currentTimeMillis();
    for (i=0; i < n; i++) 
    {
        sbuffer.append("zim");
    }
    time = System.currentTimeMillis() - prev_time;
    System.out.println("Tamaño del String: " + sbuffer.toString().length());
    System.out.println("Tiempo del StringBuffer: " + time);

    StringBuilder sbuilder = new StringBuilder();
    prev_time = System.currentTimeMillis();
    for (i=0; i < n; i++) 
    {
        sbuilder.append("zim");
    }
    time = System.currentTimeMillis() - prev_time;
    System.out.println("Tamaño del String: " + sbuilder.toString().length());
    System.out.println("Tiempo del StringBuilder: " + time);
    
    
    System.out.println("Mejora: " + Double.toString(100.0-((38.0/55.0)*100.0)));*/
    



	/*Runtime runtime = Runtime.getRuntime();
    long memory;
	
    memory = runtime.freeMemory();
	System.out.println("Tiempo: " + System.currentTimeMillis());
	System.out.println("Memoria libre: " + runtime.freeMemory());
    memory = memory-runtime.freeMemory();
    System.out.println("Memoria: " + memory);*/
    	
    	/*int iDias = -1;
    	
    	String sImporte = "1";
    	System.out.println("sImporte: " + sImporte);
    	System.out.println("sImporte invertido: " + Utils.invierteSigno(sImporte));
    	
    	String sFecha = Utils.fechaDeHoy(false);
    	System.out.println("sFecha: " + sFecha);
    	System.out.println("sFecha en "+iDias+" dias: " + Utils.sumaDiasFecha(sFecha, iDias));
    	System.out.println("sFechaN34: " + Utils.aFechaN34(sFecha));*/
    	
    	
    	String sValorRecargo = "1000";
    	long liValorTotal = 0;
    	
    	long liRecargoAdicional = 0;
    	
    	String sRecargoRecuperado = "1995000";
    	
		String sRecargo = Long.toString((liValorTotal * Long.parseLong(sValorRecargo))/10000);
    	
		String sCentimillos = Long.toString((liValorTotal * Long.parseLong(sValorRecargo))%10000);
		
		System.out.println("sRecargo:|" + sRecargo +"|");
		
		System.out.println("sCentimillos:|" + sCentimillos +"|");
		
		System.out.println("recuperaRecargo:|"+ Utils.recuperaRecargo(false, sRecargoRecuperado) +"|");
		
		System.out.println("recuperaImporte:|"+ Utils.recuperaImporte(false, Long.toString(liValorTotal)) +"|");
		
		System.out.println("liRecargoAdicional:|"+ Utils.redondeaRecargo(liRecargoAdicional) +"|");

    }
    
    
    
    
}
