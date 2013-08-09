package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMListaCuotas;
import com.provisiones.dal.qm.QMListaReferencias;
import com.provisiones.dal.qm.QMMovimientosCuotas;
import com.provisiones.dal.qm.QMMovimientosReferencias;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Cuota;
import com.provisiones.types.MovimientoCuota;
import com.provisiones.types.MovimientoReferenciaCatastral;
import com.provisiones.types.ReferenciaCatastral;


public class CLReferencias 
{
	static String sClassName = CLComunidades.class.getName();
	
	public static boolean actualizaReferenciaLeida(String linea)
	{
		String sMethod = "actualizaReferenciaLeida";

		boolean bSalida = false;
		
		MovimientoReferenciaCatastral referencia = Parser.leerReferenciaCatastral(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (referencia.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = referencia.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = referencia.getCOTDOR();
			sBKOBDEER = referencia.getOBDEER();
			referencia.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		referencia.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodMovimientoReferencia = referencia.getNURCAT();
		
		bSalida = !(sCodMovimientoReferencia.equals(""));
		
		if (bSalida)
		{
		
			referencia.setCOTDOR(sBKCOTDOR);
			referencia.setOBDEER(sBKOBDEER);
			
			bSalida = QMMovimientosReferencias.modMovimientoReferenciaCatastral(referencia, sCodMovimientoReferencia);
			QMListaReferencias.setValidado(referencia.getNURCAT(), referencia.getCOACES(), sCodMovimientoReferencia, sValidado);
		}
		else 
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
	
	public static MovimientoReferenciaCatastral convierteCuotaenMovimiento(ReferenciaCatastral referencia, String sCodCOACES, String sCodCOACCI)
	{
		String sMethod = "convierteComunidadenMovimiento";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new MovimientoReferenciaCatastral(
				ValoresDefecto.DEF_E3_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				ValoresDefecto.DEF_COENGP,
				sCodCOACES,
				referencia.getNURCAT(),
				"",
				referencia.getTIRCAT(),
				"",
				referencia.getENEMIS(),
				referencia.getCOTEXA(),
				"",
				referencia.getOBTEXC(),
				"");
		
	}
	public static ReferenciaCatastral convierteMovimientoenReferencia(MovimientoReferenciaCatastral movimiento)
	{
		String sMethod = "convierteMovimientoenComunidad";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new ReferenciaCatastral(
				movimiento.getNURCAT(),
				movimiento.getTIRCAT(),
				movimiento.getENEMIS(),
				movimiento.getCOTEXA(),
				movimiento.getOBTEXC());
	}
	
	public static String referenciaCatastralActivo(String sCodCOACES)
	{
		return QMActivos.getReferenciaCatastral(sCodCOACES);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosSinReferencias (ActivoTabla activo)
	{
		return QMListaReferencias.buscaActivosNoAsociados(activo);
	}

	public static boolean estaAsociado(String sCodCOACES)
	{
		return QMListaReferencias.activoAsociado(sCodCOACES);
	}
	
	public static int registraMovimiento(MovimientoReferenciaCatastral movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMReferencias.getEstado(movimiento.getNURCAT());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");
		
		if (movimiento.getNURCAT().equals(""))
		{
			//error nurcat sin informar
			iCodigo = -1;
		}
		else if (movimiento.getCOACCI().equals(""))
		{
			//error accion vacia
			iCodigo = -2;
		}
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//error alta de una referencia en alta
			iCodigo = -3;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -4;
		}		
		else if (sEstado.equals("B") && !movimiento.getCOACCI().equals("A"))
		{
			//error referencia de baja no recibe altas
			iCodigo = -5;
		}
		else
		{
			MovimientoReferenciaCatastral movimiento_revisado = revisaMovimiento(movimiento);
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -6;	
			}
			else
			{
				int indice = QMMovimientosReferencias.addMovimientoReferenciaCatastral(movimiento_revisado);
				
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -7;
				}
				else
				{	
					ValoresDefecto.TIPOSACCIONES COACCES = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
					
					switch (COACCES) 
					{
						case A:
							ReferenciaCatastral referenciadealta = convierteMovimientoenReferencia(movimiento_revisado);

							com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la referencia...");
							referenciadealta.pintaReferenciaCatastral();
						
							if (QMReferencias.addReferenciaCatastral(referenciadealta))
							{
								//OK - Cuota creada
								com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
								if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error relacion cuota no creada - Rollback
									QMReferencias.delReferenciaCatastral(movimiento_revisado.getNURCAT());
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									iCodigo = -12;
								}
							}
							else
							{
								//error cuota no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -11;
							}
							break;
						case B:
							if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
							{
							
								if (QMReferencias.setEstado(movimiento_revisado.getNURCAT(), "B"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									ReferenciaCatastral referenciadebaja = convierteMovimientoenReferencia(movimiento);
									//error estado no establecido - Rollback
									QMReferencias.addReferenciaCatastral(referenciadebaja);
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
									iCodigo = -13;
								}
	
							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -12;
							}
							break;
						case M:
							if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
							{
								//ReferenciaCatastral referenciamodificada = QMReferencias.getReferenciaCatastral( movimiento_revisado.getNURCAT());
								if(QMReferencias.modReferenciaCatastral(convierteMovimientoenReferencia(movimiento), movimiento_revisado.getNURCAT()))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
									iCodigo = -12;						
								}

							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -12;
							}
							break;
						default:
							break;
					}
				}
			}
			
		}
		return iCodigo;
	}
	
	public static MovimientoReferenciaCatastral revisaMovimiento(MovimientoReferenciaCatastral movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		ReferenciaCatastral referencia = QMReferencias.getReferenciaCatastral(movimiento.getNURCAT());
		
		
		referencia.pintaReferenciaCatastral();
		
		movimiento.pintaMovimientoReferenciaCatastral();
		
		MovimientoReferenciaCatastral movimiento_revisado = new MovimientoReferenciaCatastral("","0","0","","0","0","","","","","","0","","","");
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
		movimiento_revisado.setCODTRN(movimiento.getCODTRN());
		movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
		movimiento_revisado.setIDPROV(movimiento.getIDPROV());
		movimiento_revisado.setCOACCI(movimiento.getCOACCI());
		movimiento_revisado.setCOENGP(movimiento.getCOENGP());
		movimiento_revisado.setCOACES(movimiento.getCOACES());
		movimiento_revisado.setNURCAT(movimiento.getNURCAT());
		
		movimiento_revisado.setCOTEXA(movimiento.getCOTEXA());
		
		movimiento_revisado.setOBDEER(movimiento.getOBDEER());
		
				
		
			if (movimiento.getCOACCI().equals("A"))
			{
				
				if (movimiento.getTIRCAT().equals(""))
				{
					movimiento_revisado.setBITC16("#");
				}
				else
				{
					movimiento_revisado.setBITC16("S");
					movimiento_revisado.setTIRCAT(movimiento.getTIRCAT());
				}

				if (movimiento.getENEMIS().equals("0"))
				{
					movimiento_revisado.setBITC17("#");
				}
				else
				{
					movimiento_revisado.setBITC17("S");
					movimiento_revisado.setENEMIS(movimiento.getENEMIS());
				}

				if (movimiento.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("#");
				}
				else
				{
					movimiento_revisado.setBITC09("A");
					movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
				}
				

			}
			else if (movimiento.getCOACCI().equals("M"))
			{
				boolean bCambio = false;
				
				if (movimiento.getTIRCAT().equals(referencia.getTIRCAT()))
				{
					movimiento_revisado.setBITC16("#");
				}
				else
				{
					movimiento_revisado.setBITC16("S");
					movimiento_revisado.setTIRCAT(movimiento.getTIRCAT());
					bCambio = true;
				}

				if (movimiento.getENEMIS().equals(referencia.getENEMIS()))
				{
					movimiento_revisado.setBITC17("#");
				}
				else
				{
					movimiento_revisado.setBITC17("S");
					movimiento_revisado.setENEMIS(movimiento.getENEMIS());
					bCambio = true;
				}


				
				if (movimiento.getOBTEXC().equals(referencia.getOBTEXC()))
				{
					movimiento_revisado.setBITC09("#");
				}
				else if (movimiento.getOBTEXC().equals("") && !referencia.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("B");
					movimiento_revisado.setOBTEXC("");
					bCambio = true;
				}
				else if (!movimiento.getOBTEXC().equals("") &&  referencia.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("A");
					movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					bCambio = true;
				}
				else 
				{
					movimiento_revisado.setBITC09("M");
					movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					bCambio = true;
				}
				
				if (!bCambio)
					movimiento_revisado.setCOACCI("#");

			}
			else if (movimiento.getCOACCI().equals("B"))
			{
				movimiento_revisado.setBITC16("#");
				movimiento_revisado.setBITC17("#");
				movimiento_revisado.setBITC09("#");
			}
			else
				movimiento_revisado.setCOACCI("");


		

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		movimiento_revisado.pintaMovimientoReferenciaCatastral();
		
		return movimiento_revisado;

	}
}
