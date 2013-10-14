package com.provisiones.ll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.listas.QMListaImpuestos;
import com.provisiones.dal.qm.listas.errores.QMListaErroresImpuestos;
import com.provisiones.dal.qm.movimientos.QMMovimientosImpuestos;

import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.ActivoTabla;
import com.provisiones.types.ImpuestoRecurso;
import com.provisiones.types.ImpuestoRecursoTabla;
import com.provisiones.types.MovimientoImpuestoRecurso;

public class CLImpuestos 
{
	private static Logger logger = LoggerFactory.getLogger(CLImpuestos.class.getName());
	
	public static int actualizaImpuestoLeido(String linea)
	{
		int iCodigo = 0;
		
		MovimientoImpuestoRecurso impuesto = Parser.leerImpuestoRecurso(linea);
		
		logger.debug(impuesto.logMovimientoImpuestoRecurso());
		
		String sCodMovimiento = QMMovimientosImpuestos.getMovimientoImpuestoRecursoID(impuesto);
		
		logger.debug("sCodMovimiento|"+sCodMovimiento+"|");
		
		if (!(sCodMovimiento.equals("")))
		{

			
			String sEstado = QMListaImpuestos.getValidado(sCodMovimiento);;
			
			if (sEstado.equals("P"))
			{
				iCodigo = -11;
			}
			else if (sEstado.equals("X") || sEstado.equals("V") || sEstado.equals("R"))
			{
				iCodigo = -12;
			}
			else if (sEstado.equals("E"))
			{
				String sValidado = "";
				
				logger.debug("impuesto.getCOTDOR()|{}|",impuesto.getCOTDOR());
				logger.debug("ValoresDefecto.DEF_COTDOR|{}|",ValoresDefecto.DEF_COTDOR);

				if (impuesto.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
				{
					sValidado = "V";
				}
				else
				{
					sValidado = "X";
				}
				
				logger.debug("sValidado|{}|",sValidado);
				
				logger.debug("impuesto.getCOACCI()|{}|",impuesto.getCOACCI());

				ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(impuesto.getCOACCI());

				switch (COACCI)
				{
				case A: case M: case B:
					if (QMListaImpuestos.existeRelacionImpuesto(impuesto.getNURCAT(), impuesto.getCOSBAC(), impuesto.getCOACES(), sCodMovimiento))
					{
						if(QMListaImpuestos.setValidado(sCodMovimiento, sValidado))
						{
							if (sValidado.equals("X"))
							{
								//recibido error
								if (QMListaErroresImpuestos.addErrorImpuesto(sCodMovimiento, impuesto.getCOTDOR()))
								{
									iCodigo = 1;
								}
								else
								{
									QMListaImpuestos.setValidado(sCodMovimiento, "E");
									iCodigo = -4;
								}
							}
							else
							{
								//recibido OK
								logger.info("Movimiento validado.");
							}
						}
						else
						{
							iCodigo = -3;
						}
					}
					else
					{
						iCodigo = -2;
					}
					break;
					
				default:
					logger.error("Se ha recibido un movimiento con acción desconocida:|{}|.",impuesto.getCOACCI());
					iCodigo = -9;
					break;
				
				}
				
				//bSalida = QMMovimientosImpuestos.modMovimientoImpuesto(impuesto, sCodMovimiento);
				//nos ahorramos modificar el movimiento y posteriormente en el bean de gestion de errores
				//recuperaremos el codigo de error de la tabla pertinente.
			}
			else
			{
				iCodigo = -10;
			}
				
		}
		else 
		{
			logger.error("El siguiente registro no se encuentra en el sistema:");
			logger.error("|{}|",linea);
			iCodigo = -1;
		}
		
		logger.error("iCodigo:|{}|",iCodigo);
		
		return iCodigo;
	}
	
	public static MovimientoImpuestoRecurso convierteImpuestoenMovimiento(ImpuestoRecurso impuesto, String sCodCOACES, String sCodCOACCI)
	{
		logger.debug("Convirtiendo...");
		
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
		logger.debug("Convirtiendo...");
		
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
	
	public static ArrayList<ActivoTabla> buscarActivosConImpuestosResueltos (ActivoTabla activo)
	{
		return QMListaImpuestos.buscaActivosAsociadosResueltos(activo);
	}

	public static ArrayList<ImpuestoRecursoTabla> buscarImpuestosActivos (String sCodCOACES)
	{
		return QMListaImpuestos.buscaImpuestosActivo(sCodCOACES);
	}
	
	public static ArrayList<ImpuestoRecursoTabla> buscarDevolucionesDelActivo (String sCodCOACES)
	{
		return QMListaImpuestos.buscaDevolucionesActivo(sCodCOACES);
	}
	
	public static MovimientoImpuestoRecurso buscarMovimientoImpuestoRecurso (String sCodMovimiento)
	{

		return QMMovimientosImpuestos.getMovimientoImpuestoRecurso(sCodMovimiento);
	}
	
	public static boolean existeMovimientoImpuestoRecurso (String sCodMovimiento)
	{

		return QMMovimientosImpuestos.existeMovimientoImpuestoRecurso(sCodMovimiento);
	}
	
	public static long buscarNumeroMovimientosImpuestosPendientes()
	{
		return (QMListaImpuestos.buscaCantidadValidado(ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}	
	
	public static boolean comprobarRelacion (String sCodNURCAT,String sCodCOSBAC, String sCodCOACES)
	{
		return QMListaImpuestos.compruebaRelacionImpuestoActivo(sCodNURCAT, sCodCOSBAC, sCodCOACES);
	}	
	
	public static boolean existeImpuestoRecurso (String sCodNURCAT,String sCodCOSBAC)
	{
		return QMImpuestos.existeImpuestoRecurso(sCodNURCAT, sCodCOSBAC);
	}
	
	public static boolean estaDeBaja (String sCodNURCAT,String sCodCOSBAC)
	{
		return QMImpuestos.getEstado(sCodNURCAT, sCodCOSBAC).equals(ValoresDefecto.DEF_BAJA);
	}
	
	public static MovimientoImpuestoRecurso revisaCodigosControl(MovimientoImpuestoRecurso movimiento)
	{
		ImpuestoRecurso impuesto = QMImpuestos.getImpuestoRecurso(movimiento.getNURCAT(),movimiento.getCOSBAC());
		
		
		logger.debug(impuesto.logImpuestoRecurso());
		
		logger.debug(movimiento.logMovimientoImpuestoRecurso());
		
		MovimientoImpuestoRecurso movimiento_revisado = new MovimientoImpuestoRecurso("","0","0","","0","0","","0","0","0","","0","","0","","0","","#","","#","0","","","");
		
		logger.debug("Revisando Acción:|{}|",movimiento.getCOACCI());
		
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
		
				
		
			if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
			{
				
				if (movimiento.getFEPRRE().equals("0"))
				{
					movimiento_revisado.setBITC18("#");
				}
				else
				{
					movimiento_revisado.setBITC18("S");
					movimiento_revisado.setFEPRRE(movimiento.getFEPRRE());
				}
				
				movimiento_revisado.setBITC19("#");
				movimiento_revisado.setFERERE(movimiento.getFERERE());
				
				
				movimiento_revisado.setBITC20("#");
				movimiento_revisado.setFEDEIN(movimiento.getFEDEIN());
				

				if (movimiento.getBISODE().equals(""))
				{
					movimiento_revisado.setBITC21("#");
				}
				else
				{
					movimiento_revisado.setBITC21("S");
					movimiento_revisado.setBISODE(movimiento.getBISODE());
				}
				
				movimiento_revisado.setBITC22("#");
				movimiento_revisado.setBIRESO(movimiento.getBIRESO());
				
				if (movimiento.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("#");
				}
				else
				{
					movimiento_revisado.setBITC09(ValoresDefecto.DEF_ALTA);
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
					movimiento_revisado.setBITC09(ValoresDefecto.DEF_BAJA);
					movimiento_revisado.setOBTEXC("");
					bCambio = true;
				}
				else if (!movimiento.getOBTEXC().equals("") &&  impuesto.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09(ValoresDefecto.DEF_ALTA);
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
			else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA))
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


		

		logger.debug("Revisado! Nuevo movimiento:");
		
		logger.debug(movimiento_revisado.logMovimientoImpuestoRecurso());
		
		return movimiento_revisado;

	}
	
	public static int revisaMovimiento(MovimientoImpuestoRecurso movimiento)
	{
		int iCodigo = 0;
		
		logger.debug("Comprobando estado...");
		
		String sEstado = QMImpuestos.getEstado(movimiento.getNURCAT(),movimiento.getCOSBAC());
		
		logger.debug(movimiento.logMovimientoImpuestoRecurso());
		
		logger.debug("Estado:|{}|",sEstado);
		logger.debug("Accion:|{}|",movimiento.getCOACCI());
		
		if (movimiento.getCOACCI().equals(""))
		{
			//Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			iCodigo = -1;
		}
		else if (movimiento.getCOACES().equals("") || !QMActivos.existeActivo(movimiento.getCOACES()))
		{
			//Error 003 - NO EXISTE EL ACTIVO
			iCodigo = -3;
		}
		else if (movimiento.getNURCAT().equals(""))
		{
			//Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
			iCodigo = -54;
		}
		else if (!CLReferencias.comprobarRelacion(movimiento.getNURCAT(), movimiento.getCOACES()))
		{
		
			//error no existe relacion con el activo.
			iCodigo = -700;
		
		}
		else if (movimiento.getCOSBAC().equals(""))
		{
			//Error 032 - EL SUBTIPO DE ACCION NO EXISTE
			iCodigo = -32;
		}

		else if (movimiento.getFEPRRE().equals("0"))
		{
			//Error 055 - LA FECHA PRESENTACION DE RECURSO DEBE SER LOGICA Y OBLIGATORIA
			iCodigo = -55;
		}
		else if (movimiento.getFEPRRE().equals("#"))
		{
			//Error fecha de presentacion invalida
			iCodigo = -807;
		}
		else if (movimiento.getBISODE().equals("#"))
		{
			//Error 062 - INDICADOR SOLICITUD DEVOLUCION DEBE SER 'S' O 'N'
			iCodigo = -62;
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
		else if (movimiento.getFEPRRE().equals("0") && movimiento.getBIRESO().equals("#") && !movimiento.getFERERE().equals("0"))
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
		
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA) && !CLReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 061 - NO SE PUEDE REALIZAR EL ALTA PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
			iCodigo = -61;
		}
		
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA) && comprobarRelacion(movimiento.getNURCAT(), movimiento.getCOSBAC(),movimiento.getCOACES()) && !estaDeBaja(movimiento.getNURCAT(), movimiento.getCOSBAC()))
		{
			//Error 064 - NO SE PUEDE REALIZAR EL ALTA PORQUE YA EXISTE EL REGISTRO EN GMAE57
			iCodigo = -64;
		}
		else if (movimiento.getCOACCI().equals("M") && !comprobarRelacion(movimiento.getNURCAT(), movimiento.getCOSBAC(),movimiento.getCOACES()))
		{
			//Error 066 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE EL REGISTRO EN GMAE57
			iCodigo = -66;
		}
		else if (movimiento.getCOACCI().equals("M") && !CLReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 067 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
			iCodigo = -67;
		}
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA) && !comprobarRelacion(movimiento.getNURCAT(), movimiento.getCOSBAC(),movimiento.getCOACES()))
		{
			//Error 068 - NO SE PUEDE ELIMINAR PORQUE NO EXISTE REGISTRO EN GMAE57
			iCodigo = -68;
		}		
		
		else if (sEstado.equals(ValoresDefecto.DEF_ALTA) && movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
		{
			//error alta de una referencia en alta
			iCodigo = -801;
		}
		else if (sEstado.equals(ValoresDefecto.DEF_BAJA) && !movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
		{
			//error referencia de baja no recibe altas
			iCodigo = -802;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
		{
			//error estado no disponible
			iCodigo = -803;
		}
		
		return iCodigo;

	}
	
	public static int registraMovimiento(MovimientoImpuestoRecurso movimiento)
	{
		int iCodigo = revisaMovimiento(movimiento);
		

		if (iCodigo == 0)
		{
			MovimientoImpuestoRecurso movimiento_revisado = revisaCodigosControl(movimiento);
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

							logger.debug("Dando de alta la referencia...");

							logger.debug(impuestodealta.logImpuestoRecurso());

							if (estaDeBaja (movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC())) //Alta de baja
							{
								if (QMListaImpuestos.addRelacionImpuestos(movimiento_revisado.getCOACES(), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
								{
									if (QMImpuestos.setEstado(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(),ValoresDefecto.DEF_ALTA))
									{
										if(QMImpuestos.modImpuestoRecurso(convierteMovimientoenImpuesto(movimiento), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC()))
										{
											//OK 
											iCodigo = 0;
										}
										else
										{
											//error modificacion impuesto - Rollback
											QMImpuestos.setEstado(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(),ValoresDefecto.DEF_BAJA);
											QMMovimientosImpuestos.delMovimientoImpuestoRecurso(Integer.toString(indice));
											QMListaImpuestos.delRelacionImpuestos(Integer.toString(indice));
											iCodigo = -904;						
										}
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

							}
							else //Alta nueva
							{
								if (QMImpuestos.addImpuesto(impuestodealta))
								{
									//OK - impuesto creado
									logger.debug("Hecho!");
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

							}
						
							break;
						case B:
							if (QMListaImpuestos.addRelacionImpuestos(movimiento_revisado.getCOACES(), movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
							{
							
								if (QMImpuestos.setEstado(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOSBAC(),ValoresDefecto.DEF_BAJA))
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
		logger.debug("iCodigo:|{}|",iCodigo);
		
		return iCodigo;
	}
}
