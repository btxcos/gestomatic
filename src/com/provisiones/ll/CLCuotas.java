package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Cuota;
import com.provisiones.types.CuotaTabla;
import com.provisiones.types.MovimientoCuota;

public class CLCuotas 
{
  
	static String sClassName = CLCuotas.class.getName();
	
	public static boolean actualizaCuotaLeida(String linea)
	{
		String sMethod = "actualizaCuotaLeida";

		boolean bSalida = false;
		
		MovimientoCuota cuota = Parser.leerCuota(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
		
		String sValidado = "";
		
		if (cuota.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = cuota.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = cuota.getCOTDOR();
			sBKOBDEER = cuota.getOBDEER();
			cuota.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		
		cuota.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
			   				
		String sCodMovimiento = QMMovimientosCuotas.getMovimientoCuotaID(cuota);
		
		bSalida = !(sCodMovimiento.equals(""));
		
		if (bSalida)
		{
		
			cuota.setCOTDOR(sBKCOTDOR);
			cuota.setOBDEER(sBKOBDEER);
			
			QMMovimientosCuotas.modMovimientoCuota(cuota, sCodMovimiento);
			QMListaCuotas.setValidado(cuota.getCOACES(),cuota.getCOCLDO(),cuota.getNUDCOM(), cuota.getCOSBAC(), sCodMovimiento, sValidado);
		}
		else 
		{
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
	
	public static ArrayList<ActivoTabla> buscarActivosComunidadDisponibles (ActivoTabla activo, String sCodCOCLDO, String sCodNUDCOM)
	{
		ArrayList<ActivoTabla> result = new ArrayList<ActivoTabla>();
		
		if (CLComunidades.consultaEstadoComunidad(sCodCOCLDO, sCodNUDCOM))
			result = QMListaCuotas.buscaActivosComunidadDisponibles(activo, sCodCOCLDO, sCodNUDCOM); 
			
		return result;
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConCuotas (ActivoTabla activo)
	{
		return QMListaCuotas.buscaActivosConCuotas(activo);
	}

	public static ArrayList<CuotaTabla> buscarCuotasActivo (String sCOACES)
	{
		return QMListaCuotas.buscaCuotasActivo(sCOACES);
	}
	
	public static int comprobarActivo (String sCOCLDO, String sNUDCOM, String sCOACES)
	{
		String sMethod = "comprobarActivo";
		
		int iCodigo = 0;
		
		if (QMActivos.existeActivo(sCOACES))
		{
			if (QMListaComunidadesActivos.activoPerteneceComunidad(sCOCLDO, sNUDCOM, sCOACES))
				iCodigo = 0;
			else
				iCodigo = -1;
		}
		else
			iCodigo = -2;

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static MovimientoCuota convierteCuotaenMovimiento(Cuota cuota, String sCodCOACES, String sCodCOACCI)
	{
		String sMethod = "convierteComunidadenMovimiento";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new MovimientoCuota(
				ValoresDefecto.DEF_E2_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				cuota.getCOCLDO(),
				cuota.getNUDCOM(),
				ValoresDefecto.DEF_COENGP,
				sCodCOACES,
				ValoresDefecto.DEF_COGRUG_E2,
				ValoresDefecto.DEF_COTACA_E2,
				cuota.getCOSBAC(),
				"",
				cuota.getFIPAGO(),
				"",
				cuota.getFFPAGO(),
				"",
				cuota.getIMCUCO(),
				"",
				cuota.getFAACTA(),
				"",
				cuota.getPTPAGO(),
				"",
				cuota.getOBTEXC(),
				"");
		
	}
	public static Cuota convierteMovimientoenCuota(MovimientoCuota movimiento)
	{
		String sMethod = "convierteMovimientoenComunidad";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
		return new Cuota(
				movimiento.getCOCLDO(),
				movimiento.getNUDCOM(),
				movimiento.getCOSBAC(),
				movimiento.getFIPAGO(),
				movimiento.getFFPAGO(),
				movimiento.getIMCUCO(),
				movimiento.getFAACTA(),
				movimiento.getPTPAGO(),
				movimiento.getOBTEXC());
	}
	public static int registraMovimiento(MovimientoCuota movimiento)
	{
		String sMethod = "registraMovimiento";
		
		
		int iCodigo = 0;
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMCuotas.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");
		
		if (movimiento.getFIPAGO().equals("#"))
		{
			//error fecha de inicio incorrecta
			iCodigo = -7;
		}
		else if (movimiento.getFFPAGO().equals("#"))
		{
			//error fecha de fin incorrecta
			iCodigo = -8;
		}
		else if (movimiento.getFAACTA().equals("#"))
		{
			//error fecha de acta incorrecta
			iCodigo = -9;
		}
		else if (movimiento.getCOACCI().equals(""))
		{
			//error accion vacia
			iCodigo = -1;
		}
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//error alta de una comunidad en alta
			iCodigo = -2;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -3;
		}		
		else if (sEstado.equals("B") && !movimiento.getCOACCI().equals("A"))
		{
			//error comunidad de baja no recibe altas
			iCodigo = -4;
		}
		else
		{
			MovimientoCuota movimiento_revisado = CLCuotas.revisaMovimiento(movimiento);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -5;	
			}
			else
			{
				int indice = QMMovimientosCuotas.addMovimientoCuota(movimiento_revisado);
				
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -6;
				}
				else
				{	
			
					ValoresDefecto.TIPOSACCIONES COACCES = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
				
					switch (COACCES) 
					{
						case A:
							Cuota cuotadealta = convierteMovimientoenCuota(movimiento_revisado);

							com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de alta la cuota...");
							cuotadealta.pintaCuota();
						
							if (QMCuotas.addCuota(cuotadealta))
							{
								//OK - Cuota creada
								com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Hecho!");
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
									iCodigo = -12;
								}
							}
							else
							{
								//error cuota no creada - Rollback
								QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
								iCodigo = -11;
							}
							break;
						case B:
							if (QMListaCuotas.addRelacionCuotas(movimiento_revisado.getCOACES(), movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC(), Integer.toString(indice)))
							{
								//OK
								//if (QMCuotas.delCuota(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC()))
								//{
									if (QMCuotas.setEstado(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOSBAC(), "B"))
									{
										//OK 
										iCodigo = 0; 
									}
									else
									{
										Cuota cuotadebaja = convierteMovimientoenCuota(movimiento);
										//error estado no establecido - Rollback
										QMCuotas.addCuota(cuotadebaja);
										QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
										QMListaCuotas.delRelacionCuotas(Integer.toString(indice));
										iCodigo = -13;
									}
								/*}
								else
								{
									
									//error relacion cuota no borrada - Rollback
									QMListaCuotas.delRelacionCuotas(Integer.toString(indice));
									QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
									iCodigo = -14;
								}*/
							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
								iCodigo = -12;
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
									QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
									iCodigo = -12;									
								}

							}
							else
							{
								//error relacion cuota no creada - Rollback
								QMMovimientosCuotas.delMovimientoCuota(Integer.toString(indice));
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
	
	
	public static MovimientoCuota revisaMovimiento(MovimientoCuota movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		Cuota cuota = QMCuotas.getCuota(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
		
		
		cuota.pintaCuota();
		
		movimiento.pintaMovimientoCuota();
		
		MovimientoCuota movimiento_revisado = new MovimientoCuota("", "", "", "", "", "", "", "0", "", "", "", "", "0", "", "0", "", "0", "", "0", "", "0", "", "", "");
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
		movimiento_revisado.setCODTRN(movimiento.getCODTRN());
		movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
		movimiento_revisado.setIDPROV(movimiento.getIDPROV());
		movimiento_revisado.setCOACCI(movimiento.getCOACCI());
		movimiento_revisado.setCOCLDO(movimiento.getCOCLDO());
		movimiento_revisado.setNUDCOM(movimiento.getNUDCOM());
		movimiento_revisado.setCOENGP(movimiento.getCOENGP());
		movimiento_revisado.setCOACES(movimiento.getCOACES());
		
		movimiento_revisado.setCOGRUG(movimiento.getCOGRUG());
		movimiento_revisado.setCOTACA(movimiento.getCOTACA());
		movimiento_revisado.setCOSBAC(movimiento.getCOSBAC());
		
		
		movimiento_revisado.setOBDEER(movimiento.getOBDEER());
		
				
		
			if (movimiento.getCOACCI().equals("A"))
			{
				
				if (movimiento.getFIPAGO().equals("0"))
				{
					movimiento_revisado.setBITC11("#");
				}
				else
				{
					movimiento_revisado.setBITC11("S");
					movimiento_revisado.setFIPAGO(movimiento.getFIPAGO());
				}

				if (movimiento.getFFPAGO().equals("0"))
				{
					movimiento_revisado.setBITC12("#");
				}
				else
				{
					movimiento_revisado.setBITC12("S");
					movimiento_revisado.setFFPAGO(movimiento.getFFPAGO());
				}

				if (movimiento.getIMCUCO().equals("0"))
				{
					movimiento_revisado.setBITC13("#");
				}
				else
				{
					movimiento_revisado.setBITC13("S");
					movimiento_revisado.setIMCUCO(movimiento.getIMCUCO());
				}
				
				if (movimiento.getFAACTA().equals("0"))
				{
					movimiento_revisado.setBITC14("#");
				}
				else
				{
					movimiento_revisado.setBITC14("S");
					movimiento_revisado.setFAACTA(movimiento.getFAACTA());
				}
				
				if (movimiento.getPTPAGO().equals(""))
				{
					movimiento_revisado.setBITC15("#");
				}
				else
				{
					movimiento_revisado.setBITC15("S");
					movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());
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
				
				if (movimiento.getFIPAGO().equals(cuota.getFIPAGO()))
				{
					movimiento_revisado.setBITC11("#");
				}
				else
				{
					movimiento_revisado.setBITC11("S");
					movimiento_revisado.setFIPAGO(movimiento.getFIPAGO());
					bCambio = true;
				}

				if (movimiento.getFFPAGO().equals(cuota.getFFPAGO()))
				{
					movimiento_revisado.setBITC12("#");
				}
				else
				{
					movimiento_revisado.setBITC12("S");
					movimiento_revisado.setFFPAGO(movimiento.getFFPAGO());
					bCambio = true;
				}

				if (movimiento.getIMCUCO().equals(cuota.getIMCUCO()))
				{
					movimiento_revisado.setBITC13("#");
				}
				else
				{
					movimiento_revisado.setBITC13("S");
					movimiento_revisado.setIMCUCO(movimiento.getIMCUCO());
					bCambio = true;
				}
				
				if (movimiento.getFAACTA().equals(cuota.getFAACTA()))
				{
					movimiento_revisado.setBITC14("#");
				}
				else
				{
					movimiento_revisado.setBITC14("S");
					movimiento_revisado.setFAACTA(movimiento.getFAACTA());
					bCambio = true;
				}
				
				if (movimiento.getPTPAGO().equals(cuota.getPTPAGO()))
				{
					movimiento_revisado.setBITC15("#");
				}
				else
				{
					movimiento_revisado.setBITC15("S");
					movimiento_revisado.setPTPAGO(movimiento.getPTPAGO());
					bCambio = true;
				}
				
				if (movimiento.getOBTEXC().equals(cuota.getOBTEXC()))
				{
					movimiento_revisado.setBITC09("#");
				}
				else if (movimiento.getOBTEXC().equals("") && !cuota.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("B");
					movimiento_revisado.setOBTEXC("");
					bCambio = true;
				}
				else if (!movimiento.getOBTEXC().equals("") &&  cuota.getOBTEXC().equals(""))
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
				movimiento_revisado.setBITC11("#");
				movimiento_revisado.setBITC12("#");
				movimiento_revisado.setBITC13("#");
				movimiento_revisado.setBITC14("#");
				movimiento_revisado.setBITC15("#");
				movimiento_revisado.setBITC09("#");
			}
			else
				movimiento_revisado.setCOACCI("");


		

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		movimiento_revisado.pintaMovimientoCuota();
		
		return movimiento_revisado;

	}
}
