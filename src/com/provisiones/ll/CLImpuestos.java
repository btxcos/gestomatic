package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.QMListaImpuestos;
import com.provisiones.dal.qm.QMMovimientosImpuestos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.ImpuestoRecurso;
import com.provisiones.types.ImpuestoRecursoTabla;
import com.provisiones.types.MovimientoImpuestoRecurso;

public class CLImpuestos 
{
	static String sClassName = CLImpuestos.class.getName();
	
	public static boolean actualizaImpuestoLeido(String linea)
	{
		String sMethod = "actualizaImpuestoLeido";

		boolean bSalida = false;
		
		MovimientoImpuestoRecurso impuesto = Parser.leerImpuestoRecurso(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (impuesto.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = impuesto.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = impuesto.getCOTDOR();
			sBKOBDEER = impuesto.getOBDEER();
			impuesto.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		impuesto.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodMovimientoImpuesto = QMMovimientosImpuestos.getMovimientoImpuestoRecursoID(impuesto);
		
		bSalida = !(sCodMovimientoImpuesto.equals(""));
		
		if (bSalida)
		{
		
			impuesto.setCOTDOR(sBKCOTDOR);
			impuesto.setOBDEER(sBKOBDEER);
			
			QMMovimientosImpuestos.modMovimientoImpuestoRecurso(impuesto, sCodMovimientoImpuesto);
			QMListaImpuestos.setValidado( impuesto.getCOACES(), impuesto.getNURCAT(), impuesto.getCOSBAC(),sCodMovimientoImpuesto, sValidado);
		}
		else 
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
	
	public static MovimientoImpuestoRecurso convierteImpuestoenMovimiento(ImpuestoRecurso impuesto, String sCodCOACES, String sCodCOACCI)
	{
		String sMethod = "convierteImpuestoenMovimiento";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new MovimientoImpuestoRecurso(
				ValoresDefecto.DEF_E3_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				ValoresDefecto.DEF_COENGP,
				sCodCOACES,
				impuesto.getNURCAT(),
				ValoresDefecto.DEF_COGRUG_E4, 
				ValoresDefecto.DEF_COTACA_E4, 
				impuesto.getCOSBAC(),
				"",
				impuesto.getFEPRRE(),
				"",
				impuesto.getFERERE(),
				"",
				impuesto.getFEDEIN(),
				"",
				impuesto.getBISODE(),
				"",
				impuesto.getBIRESO(),
				impuesto.getCOTEXA(),
				"",
				impuesto.getOBTEXC(),
				"");
		
	}
	public static ImpuestoRecurso convierteMovimientoenImpuesto(MovimientoImpuestoRecurso movimiento)
	{
		String sMethod = "convierteMovimientoenImpuesto";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new ImpuestoRecurso(
				movimiento.getNURCAT(),
				movimiento.getCOSBAC(),
				movimiento.getFEPRRE(),
				movimiento.getFERERE(),
				movimiento.getFEDEIN(),
				movimiento.getBISODE(),
				movimiento.getBIRESO(),
				movimiento.getCOTEXA(),
				movimiento.getOBTEXC());
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConImpuestos (ActivoTabla activo)
	{
		return QMListaImpuestos.buscaActivosAsociados(activo);
	}

	public static ArrayList<ImpuestoRecursoTabla> buscarImpuestosActivos (String sCodCOACES)
	{
		return QMListaImpuestos.buscaImpuestosActivo(sCodCOACES);
	}
	
	
	public static int registraMovimiento(MovimientoImpuestoRecurso movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMImpuestos.getEstado(movimiento.getNURCAT(),movimiento.getCOSBAC());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");
		
		if (movimiento.getNURCAT().equals(""))
		{
			//error nurcat sin informar
			iCodigo = -1;
		}
		if (movimiento.getCOSBAC().equals(""))
		{
			//error nurcat sin informar
			iCodigo = -2;
		}
		else if (movimiento.getCOACCI().equals(""))
		{
			//error accion vacia
			iCodigo = -3;
		}
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//error alta de una referencia en alta
			iCodigo = -4;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -5;
		}		
		else if (sEstado.equals("B") && !movimiento.getCOACCI().equals("A"))
		{
			//error referencia de baja no recibe altas
			iCodigo = -6;
		}
		else
		{
			MovimientoImpuestoRecurso movimiento_revisado = revisaMovimiento(movimiento);
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -7;	
			}
			else
			{
				int indice = QMMovimientosImpuestos.addMovimientoImpuestoRecurso(movimiento_revisado);
				
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
							ImpuestoRecurso impuestodealta = convierteMovimientoenImpuesto(movimiento_revisado);

							com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la referencia...");
							impuestodealta.pintaImpuestoRecurso();
						
							if (QMImpuestos.addImpuesto(impuestodealta))
							{
								//OK - Cuota creada
								com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
								if (QMListaImpuestos.addRelacionImpuestos(movimiento_revisado.getCOACES(), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error relacion cuota no creada - Rollback
									QMImpuestos.delImpuestoRecurso(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC());
									QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
									iCodigo = -12;
								}
							}
							else
							{
								//error cuota no creada - Rollback
								QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
								iCodigo = -11;
							}
							break;
						case B:
							if (QMListaImpuestos.addRelacionImpuestos(movimiento_revisado.getCOACES(), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
							{
							
								if (QMImpuestos.setEstado(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(),"B"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error estado no establecido - Rollback
									QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
									QMListaImpuestos.delRelacionImpuestos(Integer.toString(indice));
									iCodigo = -13;
								}
	
							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
								iCodigo = -12;
							}
							break;
						case M:
							if (QMListaImpuestos.addRelacionImpuestos(movimiento_revisado.getCOACES(), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
							{
								//ReferenciaCatastral referenciamodificada = QMReferencias.getReferenciaCatastral( movimiento_revisado.getNURCAT());
								if(QMImpuestos.modImpuestoRecurso(convierteMovimientoenImpuesto(movimiento), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC()))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
									QMListaImpuestos.delRelacionImpuestos(Integer.toString(indice));
									iCodigo = -12;						
								}

							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
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
	
	public static MovimientoImpuestoRecurso revisaMovimiento(MovimientoImpuestoRecurso movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		ImpuestoRecurso impuesto = QMImpuestos.getImpuestoRecurso(movimiento.getNURCAT(),movimiento.getCOSBAC());
		
		
		impuesto.pintaImpuestoRecurso();
		
		movimiento.pintaMovimientoImpuestoRecurso();
		
		MovimientoImpuestoRecurso movimiento_revisado = new MovimientoImpuestoRecurso("","0","0","","0","0","","0","0","0","","0","","0","","0","","#","","#","0","","","");
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
		movimiento_revisado.setCODTRN(movimiento.getCODTRN());
		movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
		movimiento_revisado.setIDPROV(movimiento.getIDPROV());
		movimiento_revisado.setCOACCI(movimiento.getCOACCI());
		movimiento_revisado.setCOENGP(movimiento.getCOENGP());
		movimiento_revisado.setCOACES(movimiento.getCOACES());
		movimiento_revisado.setNURCAT(movimiento.getNURCAT());
		
		movimiento_revisado.setCOGRUC(movimiento.getCOGRUC());
		movimiento_revisado.setCOTACA(movimiento.getCOTACA());
		movimiento_revisado.setCOSBAC(movimiento.getCOSBAC());
		
		movimiento_revisado.setCOTEXA(movimiento.getCOTEXA());
		
		movimiento_revisado.setOBDEER(movimiento.getOBDEER());
		
				
		
			if (movimiento.getCOACCI().equals("A"))
			{
				
				if (movimiento.getFEPRRE().equals(""))
				{
					movimiento_revisado.setBITC18("#");
				}
				else
				{
					movimiento_revisado.setBITC18("S");
					movimiento_revisado.setFEPRRE(movimiento.getFEPRRE());
				}

				if (movimiento.getFERERE().equals("0"))
				{
					movimiento_revisado.setBITC19("#");
				}
				else
				{
					movimiento_revisado.setBITC19("S");
					movimiento_revisado.setFERERE(movimiento.getFERERE());
				}


				if (movimiento.getFEDEIN().equals("0"))
				{
					movimiento_revisado.setBITC20("#");
				}
				else
				{
					movimiento_revisado.setBITC20("S");
					movimiento_revisado.setFEDEIN(movimiento.getFEDEIN());
				}
				
				if (movimiento.getFEDEIN().equals("0"))
				{
					movimiento_revisado.setBITC20("#");
				}
				else
				{
					movimiento_revisado.setBITC20("S");
					movimiento_revisado.setFEDEIN(movimiento.getFEDEIN());
				}				

				if (movimiento.getBISODE().equals(""))
				{
					movimiento_revisado.setBITC21("#");
				}
				else
				{
					movimiento_revisado.setBITC21("S");
					movimiento_revisado.setBISODE(movimiento.getBISODE());
				}

				if (movimiento.getBIRESO().equals(""))
				{
					movimiento_revisado.setBITC22("#");
				}
				else
				{
					movimiento_revisado.setBITC22("S");
					movimiento_revisado.setBIRESO(movimiento.getBIRESO());
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
				
				if (movimiento.getFEPRRE().equals(impuesto.getFEPRRE()))
				{
					movimiento_revisado.setBITC18("#");
				}
				else
				{
					movimiento_revisado.setBITC18("S");
					movimiento_revisado.setFEPRRE(movimiento.getFEPRRE());
					bCambio = true;
				}

				if (movimiento.getFERERE().equals(impuesto.getFERERE()))
				{
					movimiento_revisado.setBITC19("#");
				}
				else
				{
					movimiento_revisado.setBITC19("S");
					movimiento_revisado.setFERERE(movimiento.getFERERE());
					bCambio = true;
				}

				if (movimiento.getFEDEIN().equals(impuesto.getFEDEIN()))
				{
					movimiento_revisado.setBITC20("#");
				}
				else
				{
					movimiento_revisado.setBITC20("S");
					movimiento_revisado.setFEDEIN(movimiento.getFEDEIN());
					bCambio = true;
				}
				
				if (movimiento.getBISODE().equals(impuesto.getBISODE()))
				{
					movimiento_revisado.setBITC21("#");
				}
				else
				{
					movimiento_revisado.setBITC21("S");
					movimiento_revisado.setBISODE(movimiento.getBISODE());
					bCambio = true;
				}
				
				if (movimiento.getBIRESO().equals(impuesto.getBIRESO()))
				{
					movimiento_revisado.setBITC22("#");
				}
				else
				{
					movimiento_revisado.setBITC22("S");
					movimiento_revisado.setBIRESO(movimiento.getBIRESO());
					bCambio = true;
				}
				
				if (movimiento.getOBTEXC().equals(impuesto.getOBTEXC()))
				{
					movimiento_revisado.setBITC09("#");
				}
				else if (movimiento.getOBTEXC().equals("") && !impuesto.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("B");
					movimiento_revisado.setOBTEXC("");
					bCambio = true;
				}
				else if (!movimiento.getOBTEXC().equals("") &&  impuesto.getOBTEXC().equals(""))
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
				movimiento_revisado.setBITC18("#");
				movimiento_revisado.setBITC19("#");
				movimiento_revisado.setBITC20("#");
				movimiento_revisado.setBITC21("#");
				movimiento_revisado.setBITC22("#");
				movimiento_revisado.setBITC09("#");
			}
			else
				movimiento_revisado.setCOACCI("");


		

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		movimiento_revisado.pintaMovimientoImpuestoRecurso();
		
		return movimiento_revisado;

	}
}
