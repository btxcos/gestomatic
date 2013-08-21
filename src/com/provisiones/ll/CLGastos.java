package com.provisiones.ll;

import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.listas.QMListaGastos;
import com.provisiones.dal.qm.movimientos.QMMovimientosGastos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;
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
	public static boolean compruebaSiExisteGasto(String sCodCOACES, String sCodCOGRUG, String sCodCOTPGA, String sCodCOSBGA, String sFEDEVE)
	{
		return QMGastos.existeGasto(sCodCOACES, sCodCOGRUG, sCodCOTPGA, sCodCOSBGA, sFEDEVE);
	}
	
	public static MovimientoGasto convierteGastoenMovimiento(Gasto gasto, String sNUPROF)
	{
		String sMethod = "convierteGastoenMovimiento";
		
		Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new MovimientoGasto(
				gasto.getCOACES(),
				gasto.getCOGRUG(),
				gasto.getCOTPGA(),
				gasto.getCOSBGA(),
				gasto.getPTPAGO(),
				gasto.getFEDEVE(),
				gasto.getFFGTVP(),
				gasto.getFEPAGA(),
				gasto.getFELIPG(),
				gasto.getCOSIGA(),
				gasto.getFEEESI(),
				gasto.getFEECOI(),
				gasto.getFEEAUI(),
				gasto.getFEEPAI(),
				gasto.getIMNGAS(),
				gasto.getYCOS02(),
				gasto.getIMRGAS(),
				gasto.getYCOS04(),
				gasto.getIMDGAS(),
				gasto.getYCOS06(),
				gasto.getIMCOST(),
				gasto.getYCOS08(),
				gasto.getIMOGAS(),
				gasto.getYCOS10(),
				gasto.getIMDTGA(),
				ValoresDefecto.DEF_COUNMO,
				gasto.getIMIMGA(),
				gasto.getCOIMPT(),
				gasto.getCOTNEG(),
				ValoresDefecto.DEF_COENCX,
				ValoresDefecto.DEF_COOFCX,
				ValoresDefecto.DEF_NUCONE,
				sNUPROF,
				gasto.getFEAGTO(),
				gasto.getCOMONA(),
				gasto.getBIAUTO(),
				gasto.getFEAUFA(),
				ValoresDefecto.DEF_COTERR,
				ValoresDefecto.DEF_FMPAGN,
				gasto.getFEPGPR(),
				ValoresDefecto.DEF_FEAPLI,
				ValoresDefecto.DEF_COAPII,
				ValoresDefecto.DEF_COSPII,
				ValoresDefecto.DEF_NUCLII);
		
	}
	
	public static Gasto convierteMovimientoenGasto(MovimientoGasto movimiento)
	{
		String sMethod = "convierteMovimientoenGasto";
		
		Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new Gasto(
				movimiento.getCOACES(),
				movimiento.getCOGRUG(),
				movimiento.getCOTPGA(),
				movimiento.getCOSBGA(),
				movimiento.getPTPAGO(),
				movimiento.getFEDEVE(),
				movimiento.getFFGTVP(),
				movimiento.getFEPAGA(),
				movimiento.getFELIPG(),
				movimiento.getCOSIGA(),
				movimiento.getFEEESI(),
				movimiento.getFEECOI(),
				movimiento.getFEEAUI(),
				movimiento.getFEEPAI(),
				movimiento.getIMNGAS(),
				movimiento.getYCOS02(),
				movimiento.getIMRGAS(),
				movimiento.getYCOS04(),
				movimiento.getIMDGAS(),
				movimiento.getYCOS06(),
				movimiento.getIMCOST(),
				movimiento.getYCOS08(),
				movimiento.getIMOGAS(),
				movimiento.getYCOS10(),
				movimiento.getIMDTGA(),
				movimiento.getIMIMGA(),
				movimiento.getCOIMPT(),
				movimiento.getCOTNEG(),
				movimiento.getFEAGTO(),
				movimiento.getCOMONA(),
				movimiento.getBIAUTO(),
				movimiento.getFEAUFA(),
				movimiento.getFEPGPR());
		
	}
	
	
	public static String decideAccion(MovimientoGasto movimiento, String sEstado)
	{
		String sMethod = "decideAccion";
		
		String sAccion = "";
		if (QMGastos.gastoAnulado(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			sAccion = "#"; //Error
		}
		else if (compruebaSiExisteGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE()))
		{
			
			if (!movimiento.getFEAGTO().equals("0") && (sEstado.equals("1") || sEstado.equals("2")))
			{
				sAccion = "N"; //Anular
			}
			else if (movimiento.getIMNGAS().startsWith("-") && (sEstado.equals("3") || sEstado.equals("4")))
			{
				sAccion = "A"; //Abono
			}
			else if (!movimiento.getIMNGAS().startsWith("-") && (sEstado.equals("1") || sEstado.equals("2") || sEstado.equals("3")))
			{
				sAccion = "M"; //Modificacion
			}
			/*else if (!movimiento.getIMNGAS().equals("-") && sEstado.equals("3"))
			{
				sAccion = "P"; //Pago
			}*/
			else
			{
				sAccion = "#"; //Error
			}
			
		}
		else
		{
			if (movimiento.getIMNGAS().startsWith("-") && (Integer.parseInt(movimiento.getCOSBGA()) > 49))
			{
				sAccion = "D"; //Devolucion
			}
			else if (!movimiento.getIMNGAS().startsWith("-") && (Integer.parseInt(movimiento.getCOSBGA()) < 10))
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
		
		String sAccion = decideAccion(movimiento,sEstado);
		
		MovimientoGasto movimiento_revisado = CLGastos.revisaMovimiento(movimiento,sAccion);
		
				
		Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Nuevo Estado:|"+movimiento.getCOSIGA()+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+sAccion+"|");
		
		
		
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
		else if ((sEstado.equals("1") || sEstado.equals("2")) && movimiento_revisado.getYCOS02().equals("-"))
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
		else if (sEstado.equals("5") && movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 012 - Llega un abono de un gasto que está anulado
			iCodigo = -12;
		}
		else if (sEstado.equals("6") && movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 013 - Llega un abono de un gasto que ya está abonado, o bien está en la misma provisión sin anular.
			iCodigo = -13;
		}
		else if (movimiento_revisado.getPTPAGO().equals("0") || movimiento_revisado.getPTPAGO().equals(""))
		{
			//Error 019 - Periodicidad del gasto es cero o espacios.
			iCodigo = -19;
		}		
		else if (!movimiento_revisado.getFEAGTO().equals("0") && sEstado.equals("4"))
		{
			//Error 023 - Llega anulación de un gasto que YA está pagado
			iCodigo = -23;
		}		
		else if (sAccion.equals("M") && sEstado.equals("4"))
		{
			//Error 024 - Llega modificación de un gasto que YA está pagado
			iCodigo = -024;
		}		
		else if (!movimiento_revisado.getFEPGPR().equals("") && CLProvisiones.estaCerrada(movimiento_revisado.getNUPROF()))
		{
			//Error 061 - La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.
			iCodigo = -61;
		}
		else if ((Integer.parseInt(movimiento_revisado.getCOSBGA()) > 49) && !movimiento_revisado.getYCOS02().equals("-"))
		{
			//Error 062 - Llega una devolución con importe positivo.
			iCodigo = -62;
		}
		else if (movimiento_revisado.getFEDEVE().equals("0"))
		{
			//Error no se ha informado la fecha de devengo.
			iCodigo = -801;
		}
		else if (movimiento_revisado.getCOSIGA().equals(""))
		{
			//Error no se ha elegido una situacion del gasto.
			iCodigo = -802;
		}
		else if (movimiento_revisado.getIMNGAS().equals(""))
		{
			//Error no se ha informado el campo importe
			iCodigo = -803;
		}
		else if (sAccion.equals("#"))
		{
			//Error, Accion no permitida  
			iCodigo = -804;
		}
		else if (sEstado.equals("") && !movimiento_revisado.getCOSIGA().equals("1") && !movimiento_revisado.getCOSIGA().equals("2"))
		{
			//error estado no disponible
			iCodigo = -805;
		}
		else
		{
			if (movimiento_revisado.getCOSIGA().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -806;	
			}
			else
			{
				int indice = QMMovimientosGastos.addMovimientoGasto(movimiento_revisado);
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -900;
				}
				else
				{	
			
					ValoresDefecto.TIPOSACCIONESGASTO ACCION = ValoresDefecto.TIPOSACCIONESGASTO.valueOf(sAccion);
				
					switch (ACCION)
					{
						case D:case G:
							Gasto gastonuevo = convierteMovimientoenGasto(movimiento_revisado);

							Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la cuota...");
							gastonuevo.pintaMovimientoGasto();
						
							if (QMGastos.addGasto(gastonuevo,movimiento_revisado.getCOSIGA()))
							{
								//OK - gasto creado
								Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
								
								if (QMListaGastos.addRelacionGasto(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE(), movimiento_revisado.getNUPROF(),Integer.toString(indice)))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error relacion gasto no creada - Rollback
									QMGastos.delGasto(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE());
									QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
									iCodigo = -902;
								}
							}
							else
							{
								//error gasto no creado - Rollback
								QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
								iCodigo = -901;
							}
							break;
						case N:case A:
							if (QMListaGastos.addRelacionGasto(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE(), movimiento_revisado.getNUPROF(),Integer.toString(indice)))
							{
								String sNuevoEstado = "";
								if (sAccion.equals("N"))
								{
									sNuevoEstado = "5"; //Anulado
								}
								else
								{
									sNuevoEstado = "6"; //Abonado
								}
								
								if (QMGastos.setEstado(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE(), sNuevoEstado))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error estado no establecido - Rollback
									QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
									QMListaGastos.delRelacionGasto(Integer.toString(indice));
									iCodigo = -903;
								}
							}
							else
							{
								//error relacion gasto no creada - Rollback
								QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
								iCodigo = -902;
							}
							break;
						case M:
							if (QMListaGastos.addRelacionGasto(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE(), movimiento_revisado.getNUPROF(),Integer.toString(indice)))
							{
								//Gasto gastomodificado = QMGastos.getGasto(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE());
								if(QMGastos.modGasto(convierteMovimientoenGasto(movimiento_revisado)))
								{
									//OK 
									if (QMGastos.setEstado(movimiento_revisado.getCOACES(), movimiento_revisado.getCOGRUG(), movimiento_revisado.getCOTPGA(), movimiento_revisado.getCOSBGA(), movimiento_revisado.getFEDEVE(), movimiento_revisado.getCOSIGA()))
									{
										//OK 
										iCodigo = 0; 
									}
									else
									{
										//error estado no establecido - Rollback
										QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
										QMListaGastos.delRelacionGasto(Integer.toString(indice));
										iCodigo = -903;
									}
								}
								else
								{
									//Error gasto no modificado
									QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
									QMListaGastos.delRelacionGasto(Integer.toString(indice));
									iCodigo = -904;									
								}

							}
							else
							{
								//error relacion gasto no creada - Rollback
								QMMovimientosGastos.delMovimientoGasto(Integer.toString(indice));
								iCodigo = -902;
							}
							break;
						default:
							break;
					}
				}
			}
		}
		Utils.debugTrace(true, sClassName, sMethod, "iCodigo:|"+iCodigo+"|");
		return iCodigo;
	}
	
	public static MovimientoGasto revisaMovimiento(MovimientoGasto movimiento, String sAccion)
	{
		String sMethod = "revisaMovimiento";
		
		Gasto gasto = QMGastos.getGasto(movimiento.getCOACES(), movimiento.getCOGRUG(), movimiento.getCOTPGA(), movimiento.getCOSBGA(), movimiento.getFEDEVE());
		
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
		

		
		if (sAccion.equals("G") || sAccion.equals("D")) //Nuevo
		{
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
			
			movimiento_revisado.setCOSIGA(movimiento.getCOSIGA());
			movimiento_revisado.setFEEESI(movimiento.getFEEESI());
			movimiento_revisado.setFEECOI(movimiento.getFEECOI());
			movimiento_revisado.setFEEAUI(movimiento.getFEEAUI());
			movimiento_revisado.setFEEPAI(movimiento.getFEEPAI());
			movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
			movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
			movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
			movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());
			movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());

		}
		
		
		else if (sAccion.equals("N")) //Anular 
		{
			movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			movimiento_revisado.setFEEESI(gasto.getFEEESI());
			movimiento_revisado.setFEECOI(gasto.getFEECOI());
			movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			movimiento_revisado.setFEEPAI(gasto.getFEEPAI());
			movimiento_revisado.setIMDTGA(gasto.getIMDTGA());
			
			movimiento_revisado.setIMIMGA(gasto.getIMIMGA());
			movimiento_revisado.setCOIMPT(gasto.getCOIMPT());
			movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());
			movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
		}
		else if (sAccion.equals("A")) //Abono 
		{
			boolean bCambio = false;
			
			if (!movimiento.getIMNGAS().equals(gasto.getIMNGAS()) 
					|| !movimiento.getYCOS02().equals(gasto.getYCOS02()))
			{
				bCambio = true;
				if (movimiento.getIMNGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS02("-");
					movimiento_revisado.setIMNGAS(movimiento.getIMNGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMNGAS(movimiento.getIMNGAS());
				}
				
			}
			if (!movimiento.getIMRGAS().equals(gasto.getIMRGAS()) 
					|| !movimiento.getYCOS04().equals(gasto.getYCOS04()))
			{
				bCambio = true;	
				if (movimiento.getIMRGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS04("-");
					movimiento_revisado.setIMRGAS(movimiento.getIMRGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMRGAS(movimiento.getIMRGAS());
				}
			}

			if (!movimiento.getIMDGAS().equals(gasto.getIMDGAS()) 
					|| !movimiento.getYCOS06().equals(gasto.getYCOS06()))
			{
				bCambio = true;	
				if (movimiento.getIMDGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS06("-");
					movimiento_revisado.setIMDGAS(movimiento.getIMDGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMDGAS(movimiento.getIMDGAS());
				}		
			}

			if (!movimiento.getIMCOST().equals(gasto.getIMCOST()) 
					|| !movimiento.getYCOS08().equals(gasto.getYCOS08()))
			{
				bCambio = true;	
				if (movimiento.getIMCOST().startsWith("-"))
				{
					movimiento_revisado.setYCOS08("-");
					movimiento_revisado.setIMCOST(movimiento.getIMCOST().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMCOST(movimiento.getIMCOST());
				}
			}
			if (!movimiento.getIMOGAS().equals(gasto.getIMOGAS()) 
					|| !movimiento.getYCOS10().equals(gasto.getYCOS10()))
			{
				bCambio = true;	
				if (movimiento.getIMOGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS10("-");
					movimiento_revisado.setIMOGAS(movimiento.getIMOGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMOGAS(movimiento.getIMOGAS());
				}
			}
			
			if (!movimiento.getIMDTGA().equals(gasto.getIMDTGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
			}
			else
			{
				movimiento_revisado.setIMDTGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getIMIMGA().equals(gasto.getIMIMGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
			}
			else
			{
				movimiento_revisado.setIMIMGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getCOIMPT().equals(gasto.getCOIMPT()))
			{
				bCambio = true;
				movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
			}
			else
			{
				movimiento_revisado.setCOIMPT(gasto.getCOIMPT());
			}
			
			movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			movimiento_revisado.setFEEESI(gasto.getFEEESI());
			movimiento_revisado.setFEECOI(gasto.getFEECOI());
			movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			movimiento_revisado.setFEEPAI(gasto.getFEEPAI());

			movimiento_revisado.setFEAGTO(gasto.getFEAGTO());
			movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			
			if (!bCambio)
				movimiento_revisado.setCOSIGA("#");
		}
		else if (sAccion.equals("M")) //Modificacion
		{
			boolean bCambio = false;
			
			if (!movimiento.getIMNGAS().equals(gasto.getIMNGAS()) 
					|| !movimiento.getYCOS02().equals(gasto.getYCOS02()))
			{
				bCambio = true;
				if (movimiento.getIMNGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS02("-");
					movimiento_revisado.setIMNGAS(movimiento.getIMNGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMNGAS(movimiento.getIMNGAS());
				}
				
			}
			if (!movimiento.getIMRGAS().equals(gasto.getIMRGAS()) 
					|| !movimiento.getYCOS04().equals(gasto.getYCOS04()))
			{
				bCambio = true;	
				if (movimiento.getIMRGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS04("-");
					movimiento_revisado.setIMRGAS(movimiento.getIMRGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMRGAS(movimiento.getIMRGAS());
				}
			}

			if (!movimiento.getIMDGAS().equals(gasto.getIMDGAS()) 
					|| !movimiento.getYCOS06().equals(gasto.getYCOS06()))
			{
				bCambio = true;	
				if (movimiento.getIMDGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS06("-");
					movimiento_revisado.setIMDGAS(movimiento.getIMDGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMDGAS(movimiento.getIMDGAS());
				}		
			}

			if (!movimiento.getIMCOST().equals(gasto.getIMCOST()) 
					|| !movimiento.getYCOS08().equals(gasto.getYCOS08()))
			{
				bCambio = true;	
				if (movimiento.getIMCOST().startsWith("-"))
				{
					movimiento_revisado.setYCOS08("-");
					movimiento_revisado.setIMCOST(movimiento.getIMCOST().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMCOST(movimiento.getIMCOST());
				}
			}
			if (!movimiento.getIMOGAS().equals(gasto.getIMOGAS()) 
					|| !movimiento.getYCOS10().equals(gasto.getYCOS10()))
			{
				bCambio = true;	
				if (movimiento.getIMOGAS().startsWith("-"))
				{
					movimiento_revisado.setYCOS10("-");
					movimiento_revisado.setIMOGAS(movimiento.getIMOGAS().replaceFirst("-", ""));
				}
				else
				{
					movimiento_revisado.setIMOGAS(movimiento.getIMOGAS());
				}
			}
			
			if (!movimiento.getIMDTGA().equals(gasto.getIMDTGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMDTGA(movimiento.getIMDTGA());
			}
			else
			{
				movimiento_revisado.setIMDTGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getIMIMGA().equals(gasto.getIMIMGA()))
			{
				bCambio = true;
				movimiento_revisado.setIMIMGA(movimiento.getIMIMGA());
			}
			else
			{
				movimiento_revisado.setIMIMGA(gasto.getIMDTGA());
			}
			
			if (!movimiento.getCOIMPT().equals(gasto.getCOIMPT()))
			{
				bCambio = true;
				movimiento_revisado.setCOIMPT(movimiento.getCOIMPT());
			}
			else
			{
				movimiento_revisado.setCOIMPT(gasto.getCOIMPT());
			}
			
			if (!movimiento.getCOSIGA().equals(gasto.getCOSIGA()))
			{
				bCambio = true;
				movimiento_revisado.setCOSIGA(movimiento.getCOSIGA());
			}
			else
			{
				movimiento_revisado.setCOSIGA(gasto.getCOSIGA());
			}

			if (!movimiento.getFEEESI().equals(gasto.getFEEESI()))
			{
				bCambio = true;
				movimiento_revisado.setFEEESI(movimiento.getFEEESI());
			}
			else
			{
				movimiento_revisado.setFEEESI(gasto.getFEEESI());
			}

			if (!movimiento.getFEECOI().equals(gasto.getFEECOI()))
			{
				bCambio = true;
				movimiento_revisado.setFEECOI(movimiento.getFEECOI());
			}
			else
			{
				movimiento_revisado.setFEECOI(gasto.getFEECOI());
			}
			
			if (!movimiento.getFEEAUI().equals(gasto.getFEEAUI()))
			{
				bCambio = true;
				movimiento_revisado.setFEEAUI(movimiento.getFEEAUI());
			}
			else
			{
				movimiento_revisado.setFEEAUI(gasto.getFEEAUI());
			}
			
			if (!movimiento.getFEEPAI().equals(gasto.getFEEPAI()))
			{
				bCambio = true;
				movimiento_revisado.setFEEPAI(movimiento.getFEEPAI());
			}
			else
			{
				movimiento_revisado.setFEEPAI(gasto.getFEEPAI());
			}

			if (!movimiento.getFEAGTO().equals(gasto.getFEAGTO()))
			{
				bCambio = true;
				movimiento_revisado.setFEAGTO(movimiento.getFEAGTO());
			}
			else
			{
				movimiento_revisado.setFEAGTO(gasto.getFEAGTO());
			}
		
			if (!movimiento.getFEPGPR().equals(gasto.getFEPGPR()))
			{
				bCambio = true;
				movimiento_revisado.setFEPGPR(movimiento.getFEPGPR());
			}
			else
			{
				movimiento_revisado.setFEPGPR(gasto.getFEPGPR());
			}
			
			if (!bCambio)
				movimiento_revisado.setCOSIGA("#");
		}
		//Moneda
		movimiento_revisado.setCOUNMO(movimiento.getCOUNMO());

		//Provision
		movimiento_revisado.setNUPROF(movimiento.getNUPROF());
		
		//Autorizacion
		movimiento_revisado.setCOMONA(movimiento.getCOMONA());
		movimiento_revisado.setBIAUTO(movimiento.getBIAUTO());
		movimiento_revisado.setCOTNEG(movimiento.getCOTNEG());
		movimiento_revisado.setFEAUFA(movimiento.getFEAUFA());
		movimiento_revisado.setCOTERR(movimiento.getCOTERR());
				
		//Conexion
		movimiento_revisado.setCOENCX(movimiento.getCOENCX());
		movimiento_revisado.setCOOFCX(movimiento.getCOOFCX());
		movimiento_revisado.setNUCONE(movimiento.getNUCONE());
		movimiento_revisado.setFMPAGN(movimiento.getFMPAGN());
		
		//Aplicacion
		movimiento_revisado.setFEAPLI(movimiento.getFEAPLI());
		movimiento_revisado.setCOAPII(movimiento.getCOAPII());
		movimiento_revisado.setCOSPII(movimiento.getCOSPII());
		movimiento_revisado.setNUCLII(movimiento.getNUCLII());		
		
		Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		
		movimiento_revisado.pintaMovimientoGasto();
		
		return movimiento_revisado;

	}
	
}
