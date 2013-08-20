package com.provisiones.ll;

import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoGasto;

public class CLGastos 
{

	static String sClassName = CLGastos.class.getName();
	
	/*public static boolean actualizaGastoLeido(String linea)
	{
		String sMethod = "actualizaGastoLeido";

		boolean bSalida = false;
		
		MovimientoGasto gasto = Parser.leerGasto(linea);
		String sCodGastos = "";
		
		String sValidado = "";
		String sBKCOTERR = "";
		
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando gasto leido...");
		
		if (gasto.getCOTERR().equals(ValoresDefecto.DEF_COTERR))
			sValidado = "V";
		else
		{
			sValidado = "X";
			sBKCOTERR = gasto.getCOTERR();
		}
		
		
		////sCodGastos = QMGastos.getGastoID(gasto);
		
		
		
		//lista_rechazados.add(sCodGastos);
		
		
		bSalida = !(sCodGastos.equals(""));
		
		if (bSalida)
		{
		
			QMGastos.modGasto(gasto);
			QMListaGastos.setValidado(sCodGastos, sValidado);
		}
		else 
		{
			QMGastos.addGasto(gasto);
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		
		return bSalida;
	}*/
	
	public static int registraMovimiento(MovimientoGasto movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		//Gasto gasto = QMGastos.getGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
		String sEstado = QMGastos.getEstado(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
				
		Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOSIGA()+"|");
		
		
		if (movimiento.getNUPROF().equals(""))
		{
			//Error 800 - Error, gasto sin provision  
			iCodigo = -800;
		}
		
		if (!movimiento.getFEAGTO().equals("") && !QMGastos.existeGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			//Error 002 - Llega fecha de anulación y no existe gasto en la tabla  
			iCodigo = -2;
		}
		else if ((sEstado.equals("0") || sEstado.equals("1")) && movimiento.getYCOS02().equals("-"))
		{
			//Error 003 - Llega un abono de un gasto que NO está pagado           
			iCodigo = -3;
		}		
		else if (Double.parseDouble(movimiento.getIMDTGA()) > Double.parseDouble(movimiento.getIMNGAS()))
		{
			//Error 004 - Descuento mayor que importe nominal del gasto
			iCodigo = -4;
		}
		else if (CLProvisiones.estaCerrada(movimiento.getNUPROF()))
		{
			//Error 006 - La provisión ya está cerrada
			iCodigo = -6;
		}
		else if (CLActivos.compruebaActivo(movimiento.getCOACES()))
		{
			//Error 008 - No existe el activo en la base corporativa
			iCodigo = -8;
		}
		else if (sEstado.equals("4") && movimiento.getYCOS02().equals("-"))
		{
			//Error 012 - Llega un abono de un gasto que está anulado
			iCodigo = -12;
		}
		else if (sEstado.equals("5") && movimiento.getYCOS02().equals("-"))
		{
			//Error 013 - Llega un abono de un gasto que ya está abonado, o bien está en la misma provisión sin anular.
			iCodigo = -13;
		}
		else if (movimiento.getPTPAGO().equals("0") || movimiento.getPTPAGO().equals(""))
		{
			//Error 019 - Periodicidad del gasto es cero o espacios.
			iCodigo = -19;
		}		
		else if (!movimiento.getFEAGTO().equals("") && sEstado.equals("3"))
		{
			//Error 023 - Llega anulación de un gasto que YA está pagado
			iCodigo = -23;
		}		
		/*else if (!movimiento.getFEAGTO().equals("") && sEstado.equals("3"))
		{
			//Error 024 - Llega modificación de un gasto que YA está pagado
			iCodigo = -024;
		}*/		
		else if (!movimiento.getFEPGPR().equals("") && CLProvisiones.estaCerrada(movimiento.getNUPROF()))
		{
			//Error 061 - La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.
			iCodigo = -61;
		}
		
		else if ((Integer.parseInt(movimiento.getCOSBGA()) > 49) && !movimiento.getYCOS02().equals("-"))
		{
			//Error 062 - Llega una devolución con importe positivo.
			iCodigo = -61;
		}
		
		else if (movimiento.getCOSIGA().equals(""))
		{
			//Error no se ha elegido una situacion del gasto.
			iCodigo = -801;
		}
		else if (movimiento.getIMNGAS().equals(""))
		{
			//error no se ha informado el campo importe
			iCodigo = -802;
		}
		else if (sEstado.equals(""))
		{
			//error estado no disponible
			iCodigo = -803;
		}
		else
		{
			MovimientoGasto movimiento_revisado = CLGastos.revisaMovimiento(movimiento);
			
			/*if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -804;	
			}
			else*/
			{
				int indice = QMMovimientosGastos.addMovimientoGasto(movimiento_revisado);
		
			}
		}
		return iCodigo;
	}
	
	public static MovimientoGasto revisaMovimiento(MovimientoGasto movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		movimiento.pintaGasto();
	
		MovimientoGasto movimiento_revisado = new MovimientoGasto("0","0","0","0","","0","0","0","0","0","0","0","0","0","0","","0","","0","","0","","0","","0","0","0","0","0","0","0","0","0","0","0","","0","0","0","0","0","","","0");
		
		//Utils.debugTrace(true, sClassName, sMethod, "Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
		movimiento_revisado.setCOACES(movimiento.getCOACES());
		
		movimiento_revisado.setCOGRUG(movimiento.getCOGRUG());
		movimiento_revisado.setCOTPGA(movimiento.getCOTPGA());
		movimiento_revisado.setCOSBGA(movimiento.getCOSBGA());
		movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());
		
		movimiento_revisado.setFEDEVE(movimiento.getFEDEVE());
		movimiento_revisado.setFFGTVP(movimiento.getFFGTVP());
		movimiento_revisado.setFEPAGA(movimiento.getFEPAGA());
		movimiento_revisado.setFELIPG(movimiento.getFELIPG());
		
		movimiento_revisado.setCOSIGA(movimiento.getCOSIGA());
		movimiento_revisado.setFEEESI(movimiento.getFEEESI());
		movimiento_revisado.setFEECOI(movimiento.getFEECOI());
		movimiento_revisado.setFEEAUI(movimiento.getFEEAUI());
		movimiento_revisado.setFEEPAI(movimiento.getFEEPAI());
		
				
		

				
		if (movimiento.getIMNGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS02("-");
			movimiento_revisado.setIMNGAS(movimiento.getIMNGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMNGAS(movimiento.getIMNGAS());
		}
		
		if (movimiento.getIMRGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS04("-");
			movimiento_revisado.setIMRGAS(movimiento.getIMRGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMRGAS(movimiento.getIMRGAS());
		}

		if (movimiento.getIMDGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS06("-");
			movimiento_revisado.setIMDGAS(movimiento.getIMDGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMDGAS(movimiento.getIMDGAS());
		}		


		if (movimiento.getIMCOST().startsWith("-"))
		{
			movimiento_revisado.setYCOS08("-");
			movimiento_revisado.setIMCOST(movimiento.getIMCOST().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMCOST(movimiento.getIMCOST());
		}
		
		if (movimiento.getIMOGAS().startsWith("-"))
		{
			movimiento_revisado.setYCOS10("-");
			movimiento_revisado.setIMOGAS(movimiento.getIMOGAS().replaceFirst("-", ""));
		}
		else
		{
			movimiento_revisado.setIMOGAS(movimiento.getIMOGAS());
		}

		movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
		movimiento_revisado.setCOUNMO(movimiento.getCOUNMO());
		movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
		movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
		movimiento_revisado.setCOTNEG(movimiento.getCOTNEG());
		movimiento_revisado.setCOENCX(movimiento.getCOENCX());
		movimiento_revisado.setCOOFCX(movimiento.getCOOFCX());
		movimiento_revisado.setNUCONE(movimiento.getNUCONE());
		movimiento_revisado.setNUPROF(movimiento.getNUPROF());
		movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());

		movimiento_revisado.setCOMONA(movimiento.getCOMONA());
		movimiento_revisado.setBIAUTO(movimiento.getBIAUTO());
		movimiento_revisado.setFEAUFA(movimiento.getFEAUFA());
		movimiento_revisado.setCOTERR(movimiento.getCOTERR());
		movimiento_revisado.setFMPAGN(movimiento.getFMPAGN());
		movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
		movimiento_revisado.setFEAPLI(movimiento.getFEAPLI());
		movimiento_revisado.setCOAPII(movimiento.getCOAPII());
		movimiento_revisado.setCOSPII(movimiento.getCOSPII());
		movimiento_revisado.setNUCLII(movimiento.getNUCLII());		
		
		Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		
		movimiento_revisado.pintaGasto();
		
		return movimiento_revisado;

	}
	
}
