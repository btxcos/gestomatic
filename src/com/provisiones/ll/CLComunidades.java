package com.provisiones.ll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.errores.QMListaErroresComunidades;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.ErrorComunidadTabla;
import com.provisiones.types.MovimientoComunidad;


public class CLComunidades 
{
	private static Logger logger = LoggerFactory.getLogger(CLComunidades.class.getName());
	

	public static int actualizaComunidadLeida(String linea)
	{
		int iCodigo = 0;
		
		MovimientoComunidad comunidad = Parser.leerComunidad(linea);
		
			
		String sValidado = "";
		
		logger.debug("comunidad.getCOTDOR()|{}|",comunidad.getCOTDOR());
		logger.debug("ValoresDefecto.DEF_COTDOR|{}|",ValoresDefecto.DEF_COTDOR);
		
		
		
		String sCodMovimiento = QMMovimientosComunidades.getMovimientoComunidadID(comunidad);
		
		logger.debug("sCodMovimiento|"+sCodMovimiento+"|");
		
		if (!(sCodMovimiento.equals("")))
		{
			ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(comunidad.getCOACCI());
			
			boolean bEnviado = false;
			
			switch (COACCI)
			{
			case X:case E:
				bEnviado = QMListaComunidadesActivos.getValidado(sCodMovimiento).equals("E");
				break;

			case A:
				bEnviado = (QMListaComunidadesActivos.getValidado(sCodMovimiento).equals("E") 
						&& QMListaComunidades.getValidado(sCodMovimiento).equals("E"));
				break;

			case M: case B:
				bEnviado = QMListaComunidades.getValidado(sCodMovimiento).equals("E");
				break;
				
			default:
				logger.error("Se ha recibido un movimiento con acción desconocida:|{}|.",comunidad.getCOACCI());
				iCodigo = -9;
				break;
			}
		
			if (comunidad.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
			{
				sValidado = "V";
			}
			else
			{
				sValidado = "X";
			}
			
			logger.debug("sValidado|{}|",sValidado);
			
			//comunidad.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
			
			logger.debug(comunidad.logMovimientoComunidad());
			
			
			
			logger.debug("comunidad.getCOACCI()|{}|",comunidad.getCOACCI());
			
			switch (COACCI)
			{
			case X:case E:
				if (QMListaComunidadesActivos.existeRelacionComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento))
				{
					if (QMListaComunidadesActivos.setValidado(sCodMovimiento, sValidado))
					{
						if (sValidado.equals("X"))
						{
							//recibido un error
							if (QMListaErroresComunidades.addErrorComunidad(sCodMovimiento, comunidad.getCOTDOR()))
							{
								iCodigo = 2;
							}
							else
							{
								QMListaComunidadesActivos.setValidado(sCodMovimiento, "E");
								iCodigo = -3;
							}
						}
						else
						{
							//recibido un OK
							logger.info("Movimiento validado.");
						}
					}
					else
					{
						iCodigo = -8;
					}
				}
				else
				{
					iCodigo = -6;
				}
				break;
			case A:
				if (QMListaComunidades.existeRelacionComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM(), sCodMovimiento))
				{
					if (QMListaComunidadesActivos.existeRelacionComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento))
					{
						if (QMListaComunidades.setValidado(sCodMovimiento, sValidado))
						{

							if (QMListaComunidadesActivos.setValidado(sCodMovimiento, sValidado))
							{

								if (sValidado.equals("X"))
								{
									//recibido error
									if (QMListaErroresComunidades.addErrorComunidad(sCodMovimiento, comunidad.getCOTDOR()))
									{
										iCodigo = 1;
									}
									else
									{
										
										QMListaComunidadesActivos.setValidado(sCodMovimiento, "E");
										QMListaComunidades.setValidado(sCodMovimiento, "E");
										iCodigo = -2;
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
								
								QMListaComunidades.setValidado(sCodMovimiento, "E");
								iCodigo = -8;
							}

						}
						else
						{
							iCodigo = -7;
						}

					}
					else
					{
						
						iCodigo = -6;						
					}
				}
				else
				{
					
					iCodigo = -5;
				}
				break;
			case M: case B:
				if (QMListaComunidades.existeRelacionComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM(), sCodMovimiento))
				{
					if(QMListaComunidades.setValidado(sCodMovimiento, sValidado))
					{
						if (sValidado.equals("X"))
						{
							//recibido error
							if (QMListaErroresComunidades.addErrorComunidad(sCodMovimiento, comunidad.getCOTDOR()))
							{
								iCodigo = 3;
							}
							else
							{
								
								QMListaComunidades.setValidado(sCodMovimiento, "E");
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
						iCodigo = -7;
					}
				}
				else
				{
					iCodigo = -5;
				}
				break;
				
			default:
				logger.error("Se ha recibido un movimiento con acción desconocida:|{}|.",comunidad.getCOACCI());
				iCodigo = -9;
				break;
			
			}
			
			//bSalida = QMMovimientosComunidades.modMovimientoComunidad(comunidad, sCodMovimiento);
			//nos ahorramos modificar el movimiento y posteriormente en el bean de gestion de errores
			//recuperaremos el codigo de error de la tabla pertinente.
			
			
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
	
	public static ArrayList<ActivoTabla> buscarActivosComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{

		return QMListaComunidadesActivos.buscaActivosComunidad(sCodCOCLDO, sCodNUDCOM);
	}
	
	public static Comunidad buscarComunidad (String sCodCOACES)
	{

		return QMListaComunidadesActivos.buscaComunidadPorActivo(sCodCOACES);
	}
	
	public static long buscarNumeroMovimientosComunidadesPendientes()
	{
		return (QMListaComunidadesActivos.buscaCantidadValidado(ValoresDefecto.DEF_PENDIENTE) 
				+ QMListaComunidades.buscaCantidadValidado(ValoresDefecto.DEF_PENDIENTE));
	}
	
	public static boolean existeComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{

		return QMComunidades.existeComunidad(sCodCOCLDO, sCodNUDCOM);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosSinComunidad (ActivoTabla activofiltro)
	{
			
		return QMListaComunidadesActivos.buscaActivosSinComunidad(activofiltro);
	}
	
	public static ArrayList<ActivoTabla> buscaActivosConComunidad (ActivoTabla activofiltro)
	{
			
		return QMListaComunidadesActivos.buscaActivosConComunidad(activofiltro);
	}
	
	public static ArrayList<ErrorComunidadTabla> buscarErroresComunidades()
	{
		return QMListaErroresComunidades.buscaErrores();
	}
	
	public static boolean comprobarRelacion (String sCodCOCLDO, String sCodNUDCOM, String sCodCOACES)
	{

		return QMListaComunidadesActivos.compruebaRelacionComunidadActivo(sCodCOCLDO, sCodNUDCOM, sCodCOACES);
	}
	
	public static int comprobarActivo (String sCOACES)
	{
		int iCodigo = 0;
		
		if (QMActivos.existeActivo(sCOACES))
		{
			if (QMListaComunidadesActivos.activoVinculadoComunidad(sCOACES))
			{
				//error - ya vinculado
				iCodigo = -1;
			}
		}
		else
		{
			//error - no existe
			iCodigo = -2;
		}
		
		logger.debug("Código de salida:|{}|",iCodigo);
		
		return iCodigo;
	}
	
	public static Comunidad consultaComunidad (String sCOCLDO, String sNUDCOM)
	{
		Comunidad comunidad = QMComunidades.getComunidad(sCOCLDO,sNUDCOM);
		
		return comunidad;
	}
	
	public static boolean consultaEstadoComunidad(String sCOCLDO, String sNUDCOM)
	{
		return QMComunidades.getEstado(sCOCLDO,sNUDCOM).equals("A");
	}
	
	
	
	public static MovimientoComunidad convierteComunidadenMovimiento(Comunidad comunidad, String sCodCOACES, String sCodCOACCI)
	{
		logger.debug("Convirtiendo...");
		
		return new MovimientoComunidad(ValoresDefecto.DEF_E1_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				ValoresDefecto.DEF_COENGP,
				comunidad.getCOCLDO(),
				comunidad.getNUDCOM(),
				"",
				sCodCOACES,
				"",
				comunidad.getNOMCOC(),
				"",
				comunidad.getNODCCO(),
				"",
				comunidad.getNOMPRC(),
				"",
				comunidad.getNUTPRC(),
				"",
				comunidad.getNOMADC(),
				"",
				comunidad.getNUTADC(),
				"",
				comunidad.getNODCAD(),
				"",
				comunidad.getNUCCEN(),
				comunidad.getNUCCOF(),
				comunidad.getNUCCDI(),
				comunidad.getNUCCNT(),
				"",
				comunidad.getOBTEXC(),
				"");
		
	}
	public static Comunidad convierteMovimientoenComunidad(MovimientoComunidad movimiento)
	{
		logger.debug("Convirtiendo...");
		
		return new Comunidad(movimiento.getCOCLDO(),
				movimiento.getNUDCOM(),
				movimiento.getNOMCOC(),
				movimiento.getNODCCO(),
				movimiento.getNOMPRC(),
				movimiento.getNUTPRC(),
				movimiento.getNOMADC(),
				movimiento.getNUTADC(),
				movimiento.getNODCAD(),
				movimiento.getNUCCEN(),
				movimiento.getNUCCOF(),
				movimiento.getNUCCDI(),
				movimiento.getNUCCNT(),
				movimiento.getOBTEXC());
	}

	public static MovimientoComunidad revisaCodigosControl(MovimientoComunidad movimiento)
	{
		Comunidad comunidad = QMComunidades.getComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		String sEstado = QMComunidades.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		logger.debug(comunidad.logComunidad());
		
		logger.debug(movimiento.logMovimientoComunidad());;
		
		MovimientoComunidad movimiento_revisado = new MovimientoComunidad("", "", "", "", "", "", "", "", "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "0", "0", "0", "0", "", "", "");
		
		logger.debug("Estado:|{}|",sEstado);
		logger.debug("Acción:|{}|",movimiento.getCOACCI());
		
		movimiento_revisado.setCODTRN(movimiento.getCODTRN());
		movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
		movimiento_revisado.setIDPROV(movimiento.getIDPROV());
		movimiento_revisado.setCOACCI(movimiento.getCOACCI());
		movimiento_revisado.setCOENGP(movimiento.getCOENGP());
		movimiento_revisado.setCOCLDO(movimiento.getCOCLDO());
		movimiento_revisado.setNUDCOM(movimiento.getNUDCOM());
	
		movimiento_revisado.setOBDEER(movimiento.getOBDEER());
		
				
		
			if (movimiento.getCOACCI().equals("A"))
			{
				
				if (movimiento.getNOMCOC().equals(""))
				{
					movimiento_revisado.setBITC01("#");
				}
				else
				{
					movimiento_revisado.setBITC01("S");
					movimiento_revisado.setNOMCOC(movimiento.getNOMCOC());
				}

				if (movimiento.getNODCCO().equals(""))
				{
					movimiento_revisado.setBITC02("#");
				}
				else
				{
					movimiento_revisado.setBITC02("S");
					movimiento_revisado.setNODCCO(movimiento.getNODCCO());
				}

				if (movimiento.getNOMPRC().equals(""))
				{
					movimiento_revisado.setBITC03("#");
				}
				else
				{
					movimiento_revisado.setBITC03("S");
					movimiento_revisado.setNOMPRC(movimiento.getNOMPRC());
				}
				
				if (movimiento.getNUTPRC().equals(""))
				{
					movimiento_revisado.setBITC04("#");
				}
				else
				{
					movimiento_revisado.setBITC04("S");
					movimiento_revisado.setNUTPRC(movimiento.getNUTPRC());
				}
				
				if (movimiento.getNOMADC().equals(""))
				{
					movimiento_revisado.setBITC05("#");
				}
				else
				{
					movimiento_revisado.setBITC05("S");
					movimiento_revisado.setNOMADC(movimiento.getNOMADC());
				}
				
				if (movimiento.getNUTADC().equals(""))
				{
					movimiento_revisado.setBITC06("#");
				}
				else
				{
					movimiento_revisado.setBITC06("S");
					movimiento_revisado.setNUTADC(movimiento.getNUTADC());
				}
				
				if (movimiento.getNODCAD().equals(""))
				{
					movimiento_revisado.setBITC07("#");
				}
				else
				{
					movimiento_revisado.setBITC07("S");
					movimiento_revisado.setNODCAD(movimiento.getNODCAD());
				}
				
				if (movimiento.getNUCCEN().equals("") && movimiento.getNUCCOF().equals("") && movimiento.getNUCCDI().equals("") && movimiento.getNUCCNT().equals(""))
				{
					movimiento_revisado.setBITC08("#");
				}
				else
				{
					movimiento_revisado.setBITC08("S");
					movimiento_revisado.setNUCCEN(movimiento.getNUCCEN());
					movimiento_revisado.setNUCCOF(movimiento.getNUCCOF());
					movimiento_revisado.setNUCCDI(movimiento.getNUCCDI());
					movimiento_revisado.setNUCCNT(movimiento.getNUCCNT());
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
				
				if (movimiento.getCOACES().equals(""))
				{
					movimiento_revisado.setBITC10("#");
				}
				else
				{
					movimiento_revisado.setBITC10("S");
					movimiento_revisado.setCOACES(movimiento.getCOACES());
				}
			}
			else if (movimiento.getCOACCI().equals("M"))
			{
				boolean bCambio = false;
				
				if (movimiento.getNOMCOC().equals(comunidad.getNOMCOC()))
				{
					movimiento_revisado.setBITC01("#");
				}
				else
				{
					movimiento_revisado.setBITC01("S");
					movimiento_revisado.setNOMCOC(movimiento.getNOMCOC());
					bCambio = true;
				}

				if (movimiento.getNODCCO().equals(comunidad.getNODCCO()))
				{
					movimiento_revisado.setBITC02("#");
				}
				else
				{
					movimiento_revisado.setBITC02("S");
					movimiento_revisado.setNODCCO(movimiento.getNODCCO());
					bCambio = true;
				}

				if (movimiento.getNOMPRC().equals(comunidad.getNOMPRC()))
				{
					movimiento_revisado.setBITC03("#");
				}
				else
				{
					movimiento_revisado.setBITC03("S");
					movimiento_revisado.setNOMPRC(movimiento.getNOMPRC());
					bCambio = true;
				}
				
				if (movimiento.getNUTPRC().equals(comunidad.getNUTPRC()))
				{
					movimiento_revisado.setBITC04("#");
				}
				else
				{
					movimiento_revisado.setBITC04("S");
					movimiento_revisado.setNUTPRC(movimiento.getNUTPRC());
					bCambio = true;
				}
				
				if (movimiento.getNOMADC().equals(comunidad.getNOMADC()))
				{
					movimiento_revisado.setBITC05("#");
				}
				else
				{
					movimiento_revisado.setBITC05("S");
					movimiento_revisado.setNOMADC(movimiento.getNOMADC());
					bCambio = true;
				}
				
				if (movimiento.getNUTADC().equals(comunidad.getNUTADC()))
				{
					movimiento_revisado.setBITC06("#");
				}
				else
				{
					movimiento_revisado.setBITC06("S");
					movimiento_revisado.setNUTADC(movimiento.getNUTADC());
					bCambio = true;
				}
				
				if (movimiento.getNODCAD().equals(comunidad.getNODCAD()))
				{
					movimiento_revisado.setBITC07("#");
				}
				else
				{
					movimiento_revisado.setBITC07("S");
					movimiento_revisado.setNODCAD(movimiento.getNODCAD());
					bCambio = true;
				}
				
				if (movimiento.getNUCCEN().equals(comunidad.getNUCCEN()) && movimiento.getNUCCOF().equals(comunidad.getNUCCOF()) && movimiento.getNUCCDI().equals(comunidad.getNUCCDI()) && movimiento.getNUCCNT().equals(comunidad.getNUCCNT()))
				{
					movimiento_revisado.setBITC08("#");

				}
				else
				{
					movimiento_revisado.setBITC08("S");
					movimiento_revisado.setNUCCEN(movimiento.getNUCCEN());
					movimiento_revisado.setNUCCOF(movimiento.getNUCCOF());
					movimiento_revisado.setNUCCDI(movimiento.getNUCCDI());
					movimiento_revisado.setNUCCNT(movimiento.getNUCCNT());
					bCambio = true;
				}
				
				if (movimiento.getOBTEXC().equals(comunidad.getOBTEXC()))
				{
					movimiento_revisado.setBITC09("#");
				}
				else if (movimiento.getOBTEXC().equals("") && !comunidad.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("B");
					movimiento_revisado.setOBTEXC("");
					bCambio = true;
				}
				else if (!movimiento.getOBTEXC().equals("") &&  comunidad.getOBTEXC().equals(""))
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

				movimiento_revisado.setCOACES("0");
				movimiento_revisado.setBITC10("#");
				
				if (!bCambio)
					movimiento_revisado.setCOACCI("#");

			}
			else //X, E o B
			{
				
				movimiento_revisado.setBITC01("#");
				movimiento_revisado.setBITC02("#");
				movimiento_revisado.setBITC03("#");
				movimiento_revisado.setBITC04("#");
				movimiento_revisado.setBITC05("#");
				movimiento_revisado.setBITC06("#");
				movimiento_revisado.setBITC07("#");
				movimiento_revisado.setBITC08("#");
				movimiento_revisado.setBITC09("#");

				if (movimiento.getCOACCI().equals("X") || movimiento.getCOACCI().equals("E"))
				{
					movimiento_revisado.setBITC10("S");
					movimiento_revisado.setCOACES(movimiento.getCOACES());
				}
				else
					movimiento_revisado.setBITC10("#");
			}



		logger.debug("Revisado! Nuevo movimiento:");
		logger.debug(movimiento_revisado.logMovimientoComunidad());
		
		return movimiento_revisado;

	}
	
	public static int revisaMovimiento(MovimientoComunidad movimiento)
	{
		int iCodigo = 0;
		
		logger.debug("Comprobando estado...");
		String sEstado = QMComunidades.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		logger.debug("Estado:|{}|",sEstado);
		logger.debug("Accion:|{}|",movimiento.getCOACCI());
	
		if (movimiento.getCOACCI().equals(""))
		{
			//Error 001 - CODIGO DE ACCION DEBE SER A,M,B,X o E
			iCodigo = -1;
		}
		else if (movimiento.getCOCLDO().equals(""))
		{
			//Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
			iCodigo = -30;
		}
		else if (movimiento.getNUDCOM().equals(""))
		{
			//Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
			iCodigo = -4;
		}
		else if (!Utils.compruebaCIF(movimiento.getNUDCOM()))
		{
			//Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
			iCodigo = -31;
		}
		else if (movimiento.getCOACCI().equals("A") && movimiento.getNOMCOC().equals(""))
		{
			//Error 005 - NO TIENE NOMBRE LA COMUNIDAD
			iCodigo = -5;
		}
		else if (!movimiento.getNODCCO().equals("") && !Utils.compruebaCorreo(movimiento.getNODCCO()))
		{
			//Error direccion de correo de comunidad incorrecta
			iCodigo = -702;
		}
		else if (!movimiento.getNODCAD().equals("") && !Utils.compruebaCorreo(movimiento.getNODCAD()))
		{
			//Error direccion de correo del administrador incorrecta
			iCodigo = -703;
		}
		else if (movimiento.getCOACCI().equals("A") && (movimiento.getNUCCEN().equals("")
				|| movimiento.getNUCCOF().equals("")
				|| movimiento.getNUCCDI().equals("")
				|| movimiento.getNUCCNT().equals("")))
		{
			//Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
			iCodigo = -6;
		}
		else if ((movimiento.getCOACCI().equals("A")||movimiento.getCOACCI().equals("M")) && !Utils.compruebaCC(movimiento.getNUCCEN(),movimiento.getNUCCOF(),movimiento.getNUCCDI(),movimiento.getNUCCNT()))
		{
			//Error datos de cuenta incorrectos
			iCodigo = -701;
		}
		
		else if (movimiento.getCOACCI().equals("A") && movimiento.getCOACES().equals(""))
		{
			//Error 022 - NO SE PUEDE DAR ALTA SI CONTROL DE ACTIVO NO ES S
			iCodigo = -22;
		}		
		else if ( !movimiento.getCOACES().equals("") && !QMActivos.existeActivo(movimiento.getCOACES()))
		{
			//Error 003 - NO EXISTE EL ACTIVO
			iCodigo = -3;
		}
		else if ((movimiento.getCOACCI().equals("A") || movimiento.getCOACCI().equals("X")) && QMListaComunidadesActivos.activoVinculadoComunidad(movimiento.getCOACES()))
		{
			//Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
			iCodigo = -8;
		}	
		else if ( movimiento.getCOACCI().equals("A") && QMComunidades.existeComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//Error 009 - YA EXISTE ESTA COMUNIDAD
			iCodigo = -9;
		}		
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("X") && comprobarRelacion(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOACES()))
		{
			//Error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
			iCodigo = -10;
		}
		else if (movimiento.getCOACCI().equals("X") && !QMComunidades.existeComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//Error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
			iCodigo = -11;
		}
		else if (movimiento.getCOACCI().equals("M") && !QMComunidades.existeComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//Error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
			iCodigo = -12;
		}
		else if (movimiento.getCOACCI().equals("B") && !QMComunidades.existeComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//Error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
			iCodigo = -26;
		}
		else if (movimiento.getCOACCI().equals("B") && QMCuotas.tieneCuotas(movimiento.getCOACES(),movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//Error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
			iCodigo = -27;			
		}
	
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//Error alta de una comunidad en alta
			iCodigo = -801;
		}
		else if (sEstado.equals("B"))
		{
			//Error comunidad de baja no puede recibir mas movimientos
			iCodigo = -802;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//Error estado no disponible
			iCodigo = -803;
		}
		
		return iCodigo;

	}
	public static int registraMovimiento(MovimientoComunidad movimiento)
	{
		int iCodigo = revisaMovimiento(movimiento);

		if (iCodigo == 0)
		{
			//OK correcto estado y accion
			
			MovimientoComunidad movimiento_revisado = revisaCodigosControl(movimiento);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//Error modificacion sin cambios
				iCodigo = -804;	
			}
			else
			{

				int indice = QMMovimientosComunidades.addMovimientoComunidad(movimiento_revisado);
				
				if (indice == 0)
				{
					//Error al crear un movimiento
					iCodigo = -900;
				}
				else
				{
					ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
					
					switch (COACCI) 
					{
						case A:
							
							logger.debug("Dando de Alta...");
							
							Comunidad comunidad = convierteMovimientoenComunidad(movimiento_revisado);
							if (QMComunidades.addComunidad(comunidad))
							{
							
								if (QMListaComunidades.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), Integer.toString(indice)))
								{	
																

									logger.debug("COACES:|{}|",movimiento_revisado.getCOACES());
									if (movimiento_revisado.getCOACES().equals("0") || movimiento_revisado.getCOACES().equals(""))
									{
										//OK 
										iCodigo = 0;
									}
									else if (QMListaComunidadesActivos.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
									{
										//OK 
										iCodigo = 0;
									}
									else
									{
										//Error y Rollback - error al registrar el activo durante el alta
										iCodigo = -903;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
										QMComunidades.delComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM());
										QMListaComunidades.delRelacionComunidad(Integer.toString(indice));
									}
								}
								else
								{
									//Error y Rollback - error al registrar la relaccion
										iCodigo = -902;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
										QMComunidades.delComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM());
								}
							}
							else
							{
								//Error y Rollback - error al registrar la comuidad
									iCodigo = -901;
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
							}
							break;
						case B:
							if (QMListaComunidades.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), Integer.toString(indice)))
							{
								
								logger.debug("Dando de Baja...");
								if (QMComunidades.setEstado(movimiento_revisado.getCOCLDO(), movimiento_revisado.getNUDCOM(), "B"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error y rollback - error al cambiar el estado
									iCodigo = -904;
									QMListaComunidades.delRelacionComunidad(Integer.toString(indice));
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
								}
							}
							else
							{
								//error y rollback - error al registrar la relaccion
								iCodigo = -902;
								QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
							}
							break;
						case M:
							if (QMListaComunidades.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), Integer.toString(indice)))
							{
								logger.debug("Modificando...");
									
								logger.debug(movimiento.logMovimientoComunidad());;
								logger.debug(movimiento_revisado.logMovimientoComunidad());
									
								if (QMComunidades.modComunidad(convierteMovimientoenComunidad(movimiento), movimiento.getCOCLDO(), movimiento.getNUDCOM()))
								{	
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error y rollback - error al modificar la comunidad
									iCodigo = -905;
									QMListaComunidades.delRelacionComunidad(Integer.toString(indice));
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
								}
							}
							else
							{
								//error y rollback - error al registrar la relaccion
								iCodigo = -902;
								QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
							}
							break;
						case X:
								String sMovimientoAlta = QMListaComunidadesActivos.getActivoVinculadoComunidadID(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES());
								if (!sMovimientoAlta.equals(""))
								{
									//error y rollback - el activo ya esta vinculado
									iCodigo = -906;
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
								}
								else
								{							
									if (QMListaComunidadesActivos.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
									{
										//OK 
										iCodigo = 0;
									}
									else
									{
										//error y rollback - error al asociar el activo en la comunidad
										iCodigo = -907;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
									}
								}
							break;
						case E:
								String sMovimientoBaja = QMListaComunidadesActivos.getActivoVinculadoComunidadID(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES());
								
								if (sMovimientoBaja.equals(""))
								{
									//error y rollback - el activo no esta vinculado
									iCodigo = -908;
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
								}
								else
								{	
									if (QMListaComunidadesActivos.delRelacionComunidad(sMovimientoBaja))
									{
										//OK 
										iCodigo = 0;
									}
									else
									{
										//error y rollback - error al desasociar el activo en la comunidad
										iCodigo = -909;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
										QMListaComunidadesActivos.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES(), sMovimientoBaja);
									}
								}
							break;
					}	
				}
			}
		}

		logger.debug("Codigo de Salida:|{}|",iCodigo);
		return iCodigo;
	}
}
