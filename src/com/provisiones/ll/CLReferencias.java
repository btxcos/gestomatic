package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
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
	
	public static boolean existeReferenciaCatastral (String sCodNURCAT)
	{

		return QMReferencias.existeReferenciaCatastral(sCodNURCAT);
	}
	
	
	public static String referenciaCatastralActivo(String sCodCOACES)
	{
		return QMActivos.getReferenciaCatastral(sCodCOACES);
	}
	
	public static String referenciaCatastralAsociada(String sCodCOACES)
	{
		return QMListaReferencias.referenciaAsociada(sCodCOACES);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosSinReferencias (ActivoTabla activo)
	{
		return QMListaReferencias.buscaActivosNoAsociados(activo);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConReferencias (ActivoTabla activo)
	{
		return QMListaReferencias.buscaActivosAsociados(activo);
	}

	public static boolean estaAsociado(String sCodCOACES)
	{
		return QMListaReferencias.activoAsociado(sCodCOACES);
	}

	public static String estadoReferencia(String sCodNURCAT)
	{
		return QMReferencias.getEstado(sCodNURCAT);
	}
	
	public static ReferenciaCatastral buscaReferencia (String sCodNURCAT)
	{
		return QMReferencias.getReferenciaCatastral(sCodNURCAT);
	}
	
	public static int registraMovimiento(MovimientoReferenciaCatastral movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMReferencias.getEstado(movimiento.getNURCAT());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");
		
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
		
		else if (movimiento.getCOACCI().equals("A") && QMReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 049 - LA REFERENCIA CATASTRAL YA EXISTE NO SE PUEDE DAR DE ALTA
			iCodigo = -49;
		}
		else if (movimiento.getCOACCI().equals("M") && !QMReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 050 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE MODIFICAR
			iCodigo = -50;
		}
		else if (movimiento.getCOACCI().equals("B") && !QMReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 051 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE DAR DE BAJA
			iCodigo = -51;
		}
		else if (movimiento.getCOACCI().equals("A") && movimiento.getTIRCAT().equals(""))
		{
			//Error 052 - TITULAR CATASTRAL OBLIGATORIO. NO SE PUEDE DAR DE ALTA
			iCodigo = -52;
		}		
		else if (movimiento.getCOACCI().equals("B") && QMImpuestos.tieneImpuestoRecurso(movimiento.getNURCAT()))
		{
			//Error 053 - EXISTEN DATOS EN GMAE57. NO SE PUEDE REALIZAR LA BAJA
			iCodigo = -53;
		}		
		else if (movimiento.getNURCAT().equals(""))
		{
			//Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
			iCodigo = -54;
		}

		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//error alta de una referencia en alta
			iCodigo = -801;
		}
		else if (sEstado.equals("B") && !movimiento.getCOACCI().equals("A"))
		{
			//error referencia de baja, solo puede recibir altas
			iCodigo = -802;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -803;
		}		

		else
		{
			MovimientoReferenciaCatastral movimiento_revisado = revisaMovimiento(movimiento);
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				int indice = QMMovimientosReferencias.addMovimientoReferenciaCatastral(movimiento_revisado);
				
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -900;
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
								//OK - referencia creada
								com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
								if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error relacion referencia no creada - Rollback
									QMReferencias.delReferenciaCatastral(movimiento_revisado.getNURCAT());
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									iCodigo = -902;
								}
							}
							else
							{
								//error referencia no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -901;
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
									//ReferenciaCatastral referenciadebaja = convierteMovimientoenReferencia(movimiento);
									//error estado no establecido - Rollback
									//QMReferencias.addReferenciaCatastral(referenciadebaja);
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
									iCodigo = -903;
								}
	
							}
							else
							{
								//error relacion referencia no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -902;
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
									iCodigo = -904;						
								}

							}
							else
							{
								//error relacion referencia no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
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
