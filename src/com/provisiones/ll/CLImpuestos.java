package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
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
			Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
	
	public static MovimientoImpuestoRecurso convierteImpuestoenMovimiento(ImpuestoRecurso impuesto, String sCodCOACES, String sCodCOACCI)
	{
		String sMethod = "convierteImpuestoenMovimiento";
		
		Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
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
		
		Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
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
	
	public static ArrayList<ImpuestoRecursoTabla> buscarDevolucionesDelActivo (String sCodCOACES)
	{
		return QMListaImpuestos.buscaDevolucionesActivo(sCodCOACES);
	}
	
	public static boolean existeRelacionImpuesto (String sCodCOACES,String sCodNURCAT,String sCodCOSBAC)
	{
		return QMListaImpuestos.existeRelacionImpuesto(sCodCOACES, sCodNURCAT, sCodCOSBAC);
	}	
	
	public static int registraMovimiento(MovimientoImpuestoRecurso movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMImpuestos.getEstado(movimiento.getNURCAT(),movimiento.getCOSBAC());
		
		movimiento.pintaMovimientoImpuestoRecurso();
		
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
		else if (movimiento.getCOSBAC().equals(""))
		{
			//Error 032 - EL SUBTIPO DE ACCION NO EXISTE
			iCodigo = -32;
		}
		else if (movimiento.getNURCAT().equals(""))
		{
			//Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
			iCodigo = -54;
		}
		else if (movimiento.getFEPRRE().equals("#") || movimiento.getFEPRRE().equals("0"))
		{
			//Error 055 - LA FECHA PRESENTACION DE RECURSO DEBE SER LOGICA Y OBLIGATORIA
			iCodigo = -55;
		}
		else if (movimiento.getFERERE().equals("#"))
		{
			//Error fecha de resolucion invalida
			iCodigo = -805;
		}
		else if (movimiento.getFEDEIN().equals("#"))
		{
			//Error fecha de devolucion invalida
			iCodigo = -806;
		}
		else if (movimiento.getCOACCI().equals("A") && !CLReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 061 - NO SE PUEDE REALIZAR EL ALTA PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
			iCodigo = -61;
		}
		else if (movimiento.getBISODE().equals("#"))
		{
			//Error 062 - INDICADOR SOLICITUD DEVOLUCION DEBE SER 'S' O 'N'
			iCodigo = -62;
		}		
		else if (movimiento.getCOACCI().equals("A") && existeRelacionImpuesto(movimiento.getCOACES(), movimiento.getNURCAT(), movimiento.getCOSBAC()))
		{
			//Error 064 - NO SE PUEDE REALIZAR EL ALTA PORQUE YA EXISTE EL REGISTRO EN GMAE57
			iCodigo = -64;
		}
		else if (movimiento.getCOACCI().equals("M") && !existeRelacionImpuesto(movimiento.getCOACES(), movimiento.getNURCAT(), movimiento.getCOSBAC()))
		{
			//Error 066 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE EL REGISTRO EN GMAE57
			iCodigo = -66;
		}
		else if (movimiento.getCOACCI().equals("M") && !CLReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 067 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
			iCodigo = -67;
		}
		else if (movimiento.getCOACCI().equals("B") && !existeRelacionImpuesto(movimiento.getCOACES(), movimiento.getNURCAT(), movimiento.getCOSBAC()))
		{
			//Error 068 - NO SE PUEDE ELIMINAR PORQUE NO EXISTE REGISTRO EN GMAE57
			iCodigo = -68;
		}		
		else if (!movimiento.getFEPRRE().equals("0") && !movimiento.getBIRESO().equals("#") && movimiento.getFERERE().equals("0"))
		{
			//Error 101 - TIENE F.PRESENTACION, TIPO RESOLUCION Y NO F.RESOLUCION
			iCodigo = -101;
		}
		else if (!movimiento.getFEPRRE().equals("0") && !movimiento.getFERERE().equals("0") && movimiento.getBIRESO().equals("#") )
		{
			//Error 102 - TIENE F.PRESENTACION, F.RESOLUCION Y NO TIPO RESOLUCION
			iCodigo = -102;
		}
		else if (movimiento.getFEPRRE().equals("0") && !movimiento.getBIRESO().equals("#") )
		{
			//Error 103 - NO TIENE F.PRESENTACION Y SI TIPO RESOLUCION
			iCodigo = -103;
		}
		/*(REVISAR)*/else if (movimiento.getFEPRRE().equals("0") && !movimiento.getBIRESO().equals("#") && !movimiento.getFERERE().equals("0"))
		{
			//Error 104 - NO TIENE F.PRESENTACION, TIPO RESOLUCION Y SI F.RESOLUCION
			iCodigo = -104;
		}
		else if (movimiento.getBISODE().equals("#") && !movimiento.getFEDEIN().equals("0") )
		{
			//Error 105 - NO TIENE S.DEVOLUCION, Y SI F.DEVOLUCION
			iCodigo = -105;
		}		
		else if (movimiento.getFEPRRE().equals("0") && !movimiento.getFEDEIN().equals("0") )
		{
			//Error 106 - NO TIENE F.PRESENTACION, Y SI F.DEVOLUCION
			iCodigo = -106;
		}			
		else if (movimiento.getBIRESO().equals("#") && !movimiento.getFEDEIN().equals("0") )
		{
			//Error 107 - NO TIENE TIPO RESOLUCION, Y SI F.DEVOLUCION
			iCodigo = -107;
		}		
		else if (movimiento.getFEPRRE().equals("0") && !movimiento.getBISODE().equals("#") )
		{
			//Error 108 - NO TIENE F.PRESENTACION, Y SI S.DEVOLUCION
			iCodigo = -108;
		}		
		else if (movimiento.getBIRESO().equals("D") && !movimiento.getFEDEIN().equals("0") )
		{
			//Error 109 - EL TIPO RESOLUCION ES DESFAVORABLE Y TIENE F.DEVOLUCION
			iCodigo = -109;
		}
		else if (!movimiento.getFERERE().equals("0") && !movimiento.getFEPRRE().equals("0") && (Integer.parseInt(movimiento.getFERERE()) < Integer.parseInt(movimiento.getFEPRRE())))
		{
			//Error 110 - LA F.RESOLUCION ES MENOR A LA F.PRESENTACION
			iCodigo = -110;
		}
		else if (!movimiento.getFEDEIN().equals("0") && !movimiento.getFEPRRE().equals("0") && (Integer.parseInt(movimiento.getFEDEIN()) < Integer.parseInt(movimiento.getFEPRRE())))
		{
			//Error 111 - LA F.DEVOLUCION ES MENOR A LA F.PRESENTACION
			iCodigo = -111;
		}
		else if (!movimiento.getFEDEIN().equals("0") && !movimiento.getFERERE().equals("0") && (Integer.parseInt(movimiento.getFEDEIN()) < Integer.parseInt(movimiento.getFERERE())))
		{
			//Error 112 - LA F.DEVOLUCION ES MENOR A LA F.RESOLUCION
			iCodigo = -112;
		}		
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//error alta de una referencia en alta
			iCodigo = -801;
		}
		else if (sEstado.equals("B") && !movimiento.getCOACCI().equals("A"))
		{
			//error referencia de baja no recibe altas
			iCodigo = -802;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -803;
		}		

		else
		{
			MovimientoImpuestoRecurso movimiento_revisado = revisaMovimiento(movimiento);
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{
				int indice = QMMovimientosImpuestos.addMovimientoImpuestoRecurso(movimiento_revisado);
				
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
							ImpuestoRecurso impuestodealta = convierteMovimientoenImpuesto(movimiento_revisado);

							Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la referencia...");
							impuestodealta.pintaImpuestoRecurso();
						
							if (QMImpuestos.addImpuesto(impuestodealta))
							{
								//OK - impuesto creado
								Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
								if (QMListaImpuestos.addRelacionImpuestos(movimiento_revisado.getCOACES(), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error relacion impuesto no creada - Rollback
									QMImpuestos.delImpuestoRecurso(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC());
									QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
									iCodigo = -902;
								}
							}
							else
							{
								//error impuesto no creada - Rollback
								QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
								iCodigo = -901;
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
									iCodigo = -903;
								}
	
							}
							else
							{
								//error relacion impuesto no creada - Rollback
								QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
								iCodigo = -902;
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
									//error modificacion impuesto - Rollback
									QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
									QMListaImpuestos.delRelacionImpuestos(Integer.toString(indice));
									iCodigo = -904;						
								}

							}
							else
							{
								//error relacion impuesto no creada - Rollback
								QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
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
	
	public static MovimientoImpuestoRecurso revisaMovimiento(MovimientoImpuestoRecurso movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		ImpuestoRecurso impuesto = QMImpuestos.getImpuestoRecurso(movimiento.getNURCAT(),movimiento.getCOSBAC());
		
		
		impuesto.pintaImpuestoRecurso();
		
		movimiento.pintaMovimientoImpuestoRecurso();
		
		MovimientoImpuestoRecurso movimiento_revisado = new MovimientoImpuestoRecurso("","0","0","","0","0","","0","0","0","","0","","0","","0","","#","","#","0","","","");
		
		Utils.debugTrace(true, sClassName, sMethod, "Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
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


		

		Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		movimiento_revisado.pintaMovimientoImpuestoRecurso();
		
		return movimiento_revisado;

	}
}
