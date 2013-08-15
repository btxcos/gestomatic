package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaCuotas;
import com.provisiones.dal.qm.movimientos.QMMovimientosCuotas;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
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
			Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
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

		Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static MovimientoCuota convierteCuotaenMovimiento(Cuota cuota, String sCodCOACES, String sCodCOACCI)
	{
		String sMethod = "convierteComunidadenMovimiento";
		
		Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
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
		
		Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
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
		
		Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		
		String sEstado = QMCuotas.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
		
		Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");

		
		if (movimiento.getCOACCI().equals(""))
		{
			//Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			iCodigo = -1;
		}
		else if (!QMActivos.existeActivo(movimiento.getCOACES()) && !movimiento.getCOACCI().equals("B"))
		{
			//Error 003 - NO EXISTE EL ACTIVO
			iCodigo = -3;
		}		
		else if (movimiento.getNUDCOM().equals(""))
		{
			//Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
			iCodigo = -4;
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
	}
	
	
	public static MovimientoCuota revisaMovimiento(MovimientoCuota movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		Cuota cuota = QMCuotas.getCuota(movimiento.getCOCLDO(), movimiento.getNUDCOM(), movimiento.getCOSBAC());
		
		
		cuota.pintaCuota();
		
		movimiento.pintaMovimientoCuota();
		
		MovimientoCuota movimiento_revisado = new MovimientoCuota("", "", "", "", "", "", "", "0", "", "", "", "", "0", "", "0", "", "0", "", "0", "", "0", "", "", "");
		
		Utils.debugTrace(true, sClassName, sMethod, "Revisando Accion: |"+movimiento.getCOACCI()+"|");
		
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


		

		Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		movimiento_revisado.pintaMovimientoCuota();
		
		return movimiento_revisado;

	}
}
