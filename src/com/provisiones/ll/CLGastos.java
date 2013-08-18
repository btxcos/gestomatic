package com.provisiones.ll;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;
import com.provisiones.types.Gasto;
import com.provisiones.types.MovimientoCuota;

public class CLGastos 
{

	static String sClassName = CLGastos.class.getName();
	
	public static boolean actualizaGastoLeido(String linea)
	{
		String sMethod = "actualizaGastoLeido";

		boolean bSalida = false;
		
		Gasto gasto = Parser.leerGasto(linea);
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
		
		sCodGastos = QMGastos.getGastoID(gasto);
		//lista_rechazados.add(sCodGastos);
		
		
		bSalida = !(sCodGastos.equals(""));
		
		if (bSalida)
		{
		
			QMGastos.modGasto(gasto, sCodGastos);
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
	}
	
/*	public static int registraMovimiento(Gasto movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMGastos.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
		
		Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");

		
		if (movimiento.getCOACCI().equals(""))
		{
			//Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			iCodigo = -1;
		}
		else if (!QMActivos.existeActivo(movimiento.getCOACES()))
		{
			//Error 003 - NO EXISTE EL ACTIVO
			iCodigo = -3;
		}		
		else if (movimiento.getNUDCOM().equals(""))
		{
			//Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
			iCodigo = -4;
		}
		else if (movimiento.getCOSBAC().equals(""))
		{
			//Error 032 - EL SUBTIPO DE ACCION NO EXISTE
			iCodigo = -32;
		}
		else if (movimiento.getFIPAGO().equals("#") || movimiento.getFIPAGO().equals("0"))
		{
			//Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
			iCodigo = -33;
		}
		else if (movimiento.getFFPAGO().equals("#") || movimiento.getFFPAGO().equals("0"))
		{
			//Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
			iCodigo = -34;
		}
		else if (Integer.parseInt(movimiento.getFFPAGO()) <  Integer.parseInt(movimiento.getFIPAGO()))
		{
			//Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
			iCodigo = -35;
		}
		else if (movimiento.getIMCUCO().equals("#"))
		{
			//Error 805 - importe incorrecto
			iCodigo = -805;
		}		
		else if (Double.parseDouble(movimiento.getIMCUCO()) <= 0)
		{
			//Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
			iCodigo = -36;
		}		
		else if (!CLComunidades.existeComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
			iCodigo = -41;
		}		

		else if (movimiento.getCOACCI().equals("A") && 
				QMListaComunidadesActivos.activoVinculadoComunidad(movimiento.getCOACES()) && 
				!QMListaComunidadesActivos.activoPerteneceComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM(),movimiento.getCOACES()))
		{
			//Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
			iCodigo = -42;
		}
		else if (movimiento.getCOACCI().equals("M") &&  
				!QMListaComunidadesActivos.activoPerteneceComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM(),movimiento.getCOACES()))
		{
			//Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
			iCodigo = -43;
		}
		
		else if (movimiento.getPTPAGO().equals(""))
		{
			//Error 044 - NO EXISTE PERIOCIDAD DE PAGO
			iCodigo = -44;
		}
		else if (movimiento.getCOACCI().equals("M") &&  
				!QMListaComunidadesActivos.activoPerteneceComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM(),movimiento.getCOACES()))
		{
			//Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
			iCodigo = -45;
		}		
		
		else if (movimiento.getFAACTA().equals("#") || movimiento.getFAACTA().equals("0"))
		{
			//Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
			iCodigo = -46;
		}

		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//Error alta de una comunidad en alta
			iCodigo = -801;
		}
		else if (sEstado.equals("B"))
		{
			//error comunidad de baja
			iCodigo = -802;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -803;
		}
		else
		{
			MovimientoCuota movimiento_revisado = CLCuotas.revisaMovimiento(movimiento);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				int indice = QMMovimientosCuotas.addMovimientoCuota(movimiento_revisado);
				
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -900;
				}
				else
				{	
			
					ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
				
					switch (COACCI)
					{
						case A:
							Cuota cuotadealta = convierteMovimientoenCuota(movimiento_revisado);

							Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la cuota...");
							cuotadealta.pintaCuota();
						
							if (QMCuotas.addCuota(cuotadealta))
							{
								//OK - Cuota creada
								Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
								if (QMListaCuotas.addRelacionCuotas(movimiento_revisado.getCOACES(), movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error relacion cuota no creada - Rollback
									QMCuotas.delCuota(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC());
									QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
									iCodigo = -902;
								}
							}
							else
							{
								//error cuota no creada - Rollback
								QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
								iCodigo = -901;
							}
							break;
						case B:
							if (QMListaCuotas.addRelacionCuotas(movimiento_revisado.getCOACES(), movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
							{
								if (QMCuotas.setEstado(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC(), "B"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error estado no establecido - Rollback
									QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
									QMListaCuotas.delRelacionCuotas(Integer.toString(indice));
									iCodigo = -903;
								}
							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
								iCodigo = -902;
							}
							break;
						case M:
							if (QMListaCuotas.addRelacionCuotas(movimiento_revisado.getCOACES(), movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
							{
								//Cuota cuotamodificada = QMCuotas.getCuota( movimiento_revisado.getCOCLDO(), movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC());
								if(QMCuotas.modCuota(convierteMovimientoenCuota(movimiento), movimiento_revisado.getCOCLDO(), movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC()))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//Error cuota no modificada
									QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
									QMListaCuotas.delRelacionCuotas(Integer.toString(indice));
									iCodigo = -904;									
								}

							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
								iCodigo = -902;
							}
							break;
						default:
							break;
					}
				}
			}
		}
		return iCodigo;
	}*/
	
	public static Gasto revisaMovimiento(Gasto movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		movimiento.pintaGasto();
	
		Gasto movimiento_revisado = new Gasto("0","0","0","0","","0","0","0","0","0","0","0","0","0","0","","0","","0","","0","","0","","0","0","0","0","0","0","0","0","0","0","0","","0","0","0","0","0","","","0");
		
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
