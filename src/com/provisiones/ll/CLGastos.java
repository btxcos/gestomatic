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
	
	/*public static String decideAccion(String sEstado, String sMovimiento)
	{
		String sAccion = "";
		if ((sEstado.equals("") && sMovimiento.equals("0"))
				|| (sEstado.equals("") && sMovimiento.equals("1")))
		{
			sAccion = "A";
		}		
		else if ((sEstado.equals("0") && sMovimiento.equals("0"))
			|| (sEstado.equals("0") && sMovimiento.equals("1"))
			|| (sEstado.equals("1") && sMovimiento.equals("0"))//revisar
			|| (sEstado.equals("1") && sMovimiento.equals("1"))
			|| (sEstado.equals("2") && sMovimiento.equals("2"))
			|| (sEstado.equals("3") && sMovimiento.equals("3")))
		{
			sAccion = "M";
		}
		else if ((sEstado.equals("0") && sMovimiento.equals("A"))
				|| (sEstado.equals("1") && sMovimiento.equals("A"))
				|| (sEstado.equals("2") && sMovimiento.equals("3")))
		{
			sAccion = "B";
		}
		else
			sAccion = "#";
		
		return sAccion;
	}*/
	
	
	public static String decideAccion(MovimientoGasto movimiento, String sEstado)
	{
		String sMethod = "decideAccion";
		
		String sAccion = "";
		if (QMGastos.gastoAnulado(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			sAccion = "#"; //Error
		}
		else if (QMGastos.existeGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			
			if (!movimiento.getFEAGTO().equals("0") && (sEstado.equals("1") || sEstado.equals("2")))
			{
				sAccion = "N"; //Anular
			}
			else if (movimiento.getYCOS02().equals("-") && (sEstado.equals("3") || sEstado.equals("4")))
			{
				sAccion = "A"; //Abono
			}
			else if (movimiento.getYCOS02().equals("") && (sEstado.equals("1") || sEstado.equals("2")))
			{
				sAccion = "M"; //Modificacion
			}
			else if (movimiento.getYCOS02().equals("") && sEstado.equals("3"))
			{
				sAccion = "P"; //Pago
			}
			else
			{
				sAccion = "#"; //Error
			}
			
		}
		else
		{
			if (movimiento.getYCOS02().equals("-") && (Integer.parseInt(movimiento.getCOSBGA()) > 49))
			{
				sAccion = "D"; //Devolucion
			}
			else if (movimiento.getYCOS02().equals("") && (Integer.parseInt(movimiento.getCOSBGA()) < 10))
			{
				sAccion = "G"; //Gasto
			}
			else
			{
				sAccion = "#"; //Error
			}
			
		}
		
		Utils.debugTrace(true, sClassName, sMethod, "sAccion:|"+sAccion+"|");
		return sAccion;
	}
	

	public static int registraMovimiento(MovimientoGasto movimiento)
	{
		String sMethod = "registraMovimiento";
		
		//movimiento.pintaMovimientoGasto();
		
		int iCodigo = 0;
		
		Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		//Gasto gasto = QMGastos.getGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
		String sEstado = QMGastos.getEstado(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
		
		MovimientoGasto movimiento_revisado = CLGastos.revisaMovimiento(movimiento);
		
		String sAccion = decideAccion(movimiento_revisado,sEstado);
		
				
		Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOSIGA()+"|");
		
		
		
		if (movimiento_revisado.getNUPROF().equals(""))
		{
			//Error 800 - Error, gasto sin provision  
			iCodigo = -800;
		}
		else if (!movimiento_revisado.getFEAGTO().equals("0") && !QMGastos.existeGasto(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE()))
		{
			//Error 002 - Llega fecha de anulación y no existe gasto en la tabla  
			iCodigo = -2;
		}
		else if ((sEstado.equals("0") || sEstado.equals("1")) && movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 003 - Llega un abono de un gasto que NO está pagado           
			iCodigo = -3;
		}		
		else if (Double.parseDouble(movimiento_revisado.getIMDTGA()) > Double.parseDouble(movimiento_revisado.getIMNGAS())) 
		{
			//Error 004 - Descuento mayor que importe nominal del gasto
			iCodigo = -4;
		}
		else if (CLProvisiones.estaCerrada(movimiento_revisado.getNUPROF()))
		{
			//Error 006 - La provisión ya está cerrada
			iCodigo = -6;
		}
		else if ((movimiento_revisado.getCOGRUG().equals("")) 
				|| (movimiento_revisado.getCOTPGA().equals("")) 
				|| (movimiento_revisado.getCOSBGA().equals("")))
		{
			//Error 007 - Error en grupo / tipo / subtipo de acción     
			iCodigo = -7;
		}
		else if (!CLActivos.compruebaActivo(movimiento_revisado.getCOACES()))
		{
			//Error 008 - No existe el activo en la base corporativa
			iCodigo = -8;
		}
		else if (sEstado.equals("4") && movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 012 - Llega un abono de un gasto que está anulado
			iCodigo = -12;
		}
		else if (sEstado.equals("5") && movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 013 - Llega un abono de un gasto que ya está abonado, o bien está en la misma provisión sin anular.
			iCodigo = -13;
		}
		else if (movimiento_revisado.getPTPAGO().equals("0") || movimiento_revisado.getPTPAGO().equals(""))
		{
			//Error 019 - Periodicidad del gasto es cero o espacios.
			iCodigo = -19;
		}		
		else if (!movimiento_revisado.getFEAGTO().equals("0") && sEstado.equals("3"))
		{
			//Error 023 - Llega anulación de un gasto que YA está pagado
			iCodigo = -23;
		}		
		/*else if (!movimiento_revisado.getFEAGTO().equals("") && sEstado.equals("3"))
		{
			//Error 024 - Llega modificación de un gasto que YA está pagado
			iCodigo = -024;
		}*/		
		else if (!movimiento_revisado.getFEPGPR().equals("") && CLProvisiones.estaCerrada(movimiento_revisado.getNUPROF()))
		{
			//Error 061 - La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.
			iCodigo = -61;
		}
		else if ((Integer.parseInt(movimiento_revisado.getCOSBGA()) > 49) && !movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 062 - Llega una devolución con importe positivo.
			iCodigo = -61;
		}
		else if (sAccion.equals("#"))
		{
			//Error 801 - Error, Accion no permitida  
			iCodigo = -801;
		}
		else if (movimiento_revisado.getFEDEVE().equals("0"))
		{
			//Error no se ha elegido una situacion del gasto.
			iCodigo = -803;
		}
		else if (movimiento_revisado.getCOSIGA().equals(""))
		{
			//Error no se ha elegido una situacion del gasto.
			iCodigo = -804;
		}
		else if (movimiento_revisado.getIMNGAS().equals(""))
		{
			//error no se ha informado el campo importe
			iCodigo = -805;
		}
		else if (sEstado.equals("") && !movimiento_revisado.getCOSIGA().equals("0") && !movimiento_revisado.getCOSIGA().equals("1"))
		{
			//error estado no disponible
			iCodigo = -806;
		}
		else
		{
			
			
			/*if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -807;	
			}
			else*/
			//{
				int indice = QMMovimientosGastos.addMovimientoGasto(movimiento_revisado);
		
			//}
		}
		Utils.debugTrace(true, sClassName, sMethod, "iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
	
	public static MovimientoGasto revisaMovimiento(MovimientoGasto movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		movimiento.pintaMovimientoGasto();
	
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
		
		movimiento_revisado.pintaMovimientoGasto();
		
		return movimiento_revisado;

	}
	
}
