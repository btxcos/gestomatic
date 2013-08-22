package com.provisiones.test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;

import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;

import com.provisiones.misc.ValoresDefecto;

public class TestCuotas {
	static String sClassName = TestCuotas.class.getName();

	/**
	 * @param args
	 */

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		//Cuota cuota1 
		//QMListaCuotas.buscaCuotasActivo("3109139");
		
		boolean bTraza = true;
		String sMethod = "testFicheros";
		
		//Parser.escribirActivo(activo);
		
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Generando ficheros...");
		
		int n = 26; //Caracter ->
		String aChar = new Character((char)n).toString();
		
		ArrayList<String> resultcomunidades = QMListaComunidades.getComunidadesPorEstado("P");
		ArrayList<String> resultcuotas =      QMListaCuotas.getCuotasPorEstado("P");
		ArrayList<String> resultreferencias = QMListaReferencias.getReferenciasPorEstado("P");
		ArrayList<String> resultimpuestos =   QMListaImpuestos.getImpuestosPorEstado("P");
		ArrayList<String> resultgastos =      QMListaGastos.getGastosPorEstado("P");
		
        FileWriter ficheroE1 = null;
        FileWriter ficheroE2 = null;
        FileWriter ficheroE3 = null;
        FileWriter ficheroE4 = null;
        FileWriter ficheroGA = null;
        
        PrintWriter pw = null;
        try
        {
        	ficheroE1 = new FileWriter("c:/"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E1+".txt");
            pw = new PrintWriter(ficheroE1);

            for (int i = 0; i < resultcomunidades.size() ; i++)
            {
                pw.println(Parser.escribirComunidad(QMMovimientosComunidades.getMovimientoComunidad(resultcomunidades.get(i))));
            }
            pw.print(aChar);
            
            ficheroE2 = new FileWriter("c:/"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E2+".txt");
            pw = new PrintWriter(ficheroE2);
            
            for (int i = 0; i < resultcuotas.size() ; i++)
            {
                pw.println(Parser.escribirCuota(QMMovimientosCuotas.getMovimientoCuota(resultcuotas.get(i))));
            }
            pw.print(aChar);
            
            ficheroE3 = new FileWriter("c:/"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E3+".txt");
            pw = new PrintWriter(ficheroE3);
            
            for (int i = 0; i < resultreferencias.size() ; i++)
            {
                pw.println(Parser.escribirReferenciaCatastral(QMMovimientosReferencias.getMovimientoReferenciaCatastral(resultreferencias.get(i))));
            }
            pw.print(aChar);
            
            ficheroE4 = new FileWriter("c:/"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_E4+".txt");
            pw = new PrintWriter(ficheroE4);
            
            for (int i = 0; i < resultimpuestos.size() ; i++)
            {
                pw.println(Parser.escribirImpuestoRecurso(QMMovimientosImpuestos.getMovimientoImpuestoRecurso(resultimpuestos.get(i))));
            }
            pw.print(aChar);

            ficheroGA = new FileWriter("c:/"+ValoresDefecto.DEF_COAPII+ValoresDefecto.DEF_COSPII_GA+".txt");
            pw = new PrintWriter(ficheroGA);
            
            for (int i = 0; i < resultgastos.size(); i++)
            {
                pw.println(Parser.escribirGasto(QMMovimientosGastos.getMovimientoGasto(resultgastos.get(i))));
            }
            pw.print(aChar);
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para
           // asegurarnos que se cierra el fichero.
           if (null != ficheroE1)
        	   ficheroE1.close();
           if (null != ficheroE2)
        	   ficheroE2.close();
           if (null != ficheroE3)
        	   ficheroE3.close();
           if (null != ficheroE4)
        	   ficheroE4.close();
           if (null != ficheroGA)
              ficheroGA.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        Utils.debugTrace(bTraza, sClassName, sMethod, "Generados!");
	}
}
