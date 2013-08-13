package com.provisiones.ll;

import java.util.ArrayList;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.listas.QMListaComunidades;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.movimientos.QMMovimientosComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoComunidad;


public class CLComunidades 
{
	static String sClassName = CLComunidades.class.getName();
	

	public static boolean actualizaComunidadLeida(String linea)
	{
		String sMethod = "actualizaComunidadLeida";

		boolean bSalida = false;
		
		MovimientoComunidad comunidad = Parser.leerComunidad(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
				
		String sValidado = "";
		
		if (comunidad.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = comunidad.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = comunidad.getCOTDOR();
			sBKOBDEER = comunidad.getOBDEER();
			comunidad.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sValidado|"+sValidado+"|");
		
		//comunidad.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
		String sCodMovimiento = QMMovimientosComunidades.getMovimientoComunidadID(comunidad);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sCodMovimiento|"+sCodMovimiento+"|");
		
		bSalida = !(sCodMovimiento.equals(""));
		
		if (bSalida)
		{
			//String sAccion = comunidad.getCOACCI();
			
			//Accion	Estado	Validado
			/*
			
			if (sAccion.equals("A") && sValidado.equals("X"))
			{
				
			}
			if (sAccion.equals("A") && sValidado.equals("V"))
			{
				
			}
			if (sAccion.equals("A") && sValidado.equals("X"))
			{
				
			}*/
			
			comunidad.setCOTDOR(sBKCOTDOR);
			comunidad.setOBDEER(sBKOBDEER);
			
			comunidad.setBITC01("S");
			
			comunidad.pintaMovimientoComunidad();
			
			
			
			bSalida = QMMovimientosComunidades.modMovimientoComunidad(comunidad, sCodMovimiento);
			
			if (QMListaComunidadesActivos.existeRelacionComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento))
				QMListaComunidadesActivos.setValidado(comunidad.getCOCLDO(),comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento, sValidado);
			else
				System.out.println("No Existe relacion.");
		}
		else 
		{

			
			
			/*Comunidad NuevaComunidad = new Comunidad(comunidad.getCOCLDO(),
					comunidad.getNUDCOM(), comunidad.getNOMCOC(),
					comunidad.getNODCCO(), comunidad.getNOMPRC(),
					comunidad.getNUTPRC(), comunidad.getNOMADC(),
					comunidad.getNUTADC(), comunidad.getNODCAD(),
					comunidad.getNUCCEN(), comunidad.getNUCCOF(),
					comunidad.getNUCCDI(), comunidad.getNUCCNT(),
					comunidad.getOBTEXC());
			
			QMComunidades.addComunidad(NuevaComunidad);*/
			
			//comunidad.pintaMovimientoComunidad();
			
			//comunidad.setBITC01("#");
			
			//QMMovimientosComunidades.addMovimientoComunidad(comunidad);
			
			//QMListaComunidades.
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
	
	public static ArrayList<ActivoTabla> buscarActivosComunidad (String sCodCOCLDO, String sCodNUDCOM)
	{

		return QMListaComunidadesActivos.buscaActivosComunidad(sCodCOCLDO, sCodNUDCOM);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosDisponibles (ActivoTabla activobuscado)
	{
			
		return QMListaComunidadesActivos.buscaActivosDisponibles(activobuscado);
	}
	
	public static int comprobarActivo (String sCOACES)
	{
		String sMethod = "comprobarActivo";
		
		int iCodigo = 0;
		
		if (QMActivos.existeActivo(sCOACES))
		{
			if (QMListaComunidadesActivos.activoVinculadoComunidad(sCOACES))
				iCodigo = -1;
		}
		else
			iCodigo = -2;

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:|"+iCodigo+"|");
		
		return iCodigo;
	}
	
	public static Comunidad consultaComunidad (String sCOCLDO, String sNUDCOM)
	{
		//String sMethod = "consultaComunidad";
		
		Comunidad comunidad = QMComunidades.getComunidad(sCOCLDO,sNUDCOM);
		
		return comunidad;
	}
	
	public static boolean consultaEstadoComunidad(String sCOCLDO, String sNUDCOM)
	{
		return QMComunidades.getEstado(sCOCLDO,sNUDCOM).equals("A");
	}
	
	
	
	public static MovimientoComunidad convierteComunidadenMovimiento(Comunidad comunidad, String sCodCOACES, String sCodCOACCI)
	{
		String sMethod = "convierteComunidadenMovimiento";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
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
		String sMethod = "convierteMovimientoenComunidad";
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Convirtiendo...");
		
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
	
	



	
	

	
	
	public static MovimientoComunidad revisaMovimiento(MovimientoComunidad movimiento)
	{
		String sMethod = "revisaMovimiento";
		
		Comunidad comunidad = QMComunidades.getComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		String sEstado = QMComunidades.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		comunidad.pintaComunidad();
		
		movimiento.pintaMovimientoComunidad();
		
		MovimientoComunidad movimiento_revisado = new MovimientoComunidad("", "", "", "", "", "", "", "", "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "0", "0", "0", "0", "", "", "");
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisando Estado:|"+sEstado+"| Accion: |"+movimiento.getCOACCI()+"|");
		
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
			if (movimiento.getCOACCI().equals("M"))
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



		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Revisado! Nuevo movimiento:");
		movimiento_revisado.pintaMovimientoComunidad();
		
		return movimiento_revisado;

	}
	
	public static int registraMovimiento(MovimientoComunidad movimiento)
	{
		String sMethod = "registraMovimiento";
		
		int iCodigo = 0;
		
		//Comunidad comunidad = QMComunidades.getComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		
		//msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se ha realizado ninguna operativa con la comunidad '"+ comunidad.getNOMCOC() + "'. Se ha realizado una accion no permitida. Por favor, revise los datos.",null);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Comprobando estado...");
		String sEstado = QMComunidades.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Estado:|"+sEstado+"|");
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Accion:|"+movimiento.getCOACCI()+"|");

		if (movimiento.getCOACCI().equals(""))
		{
			//error accion vacia
			iCodigo = -8;
		}
		if (movimiento.getCOCLDO().equals("") || movimiento.getNUDCOM().equals(""))
		{
			//Error, faltan datos identificativos
			iCodigo = -1;
		}
		else if (sEstado.equals("A") && movimiento.getCOACCI().equals("A"))
		{
			//error alta de una comunidad en alta
			iCodigo = -2;
		}
		else if ((sEstado.equals("A") && movimiento.getCOACCI().equals("X") && movimiento.getCOACES().equals(""))
				|| (sEstado.equals("A") && movimiento.getCOACCI().equals("E") && movimiento.getCOACES().equals("")))
		{
			//error modificacion de activo en la comunidad con campo vacio
			iCodigo = -3;
		}
		else if (sEstado.equals("B"))
		{
			//error comunidad de baja no puede recibir mas movimientos
			iCodigo = -4;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals("A"))
		{
			//error estado no disponible
			iCodigo = -5;
		}
		else if (movimiento.getCOACCI().equals("B") && QMCuotas.tieneCuotas(movimiento.getCOCLDO(), movimiento.getNUDCOM()))
		{
			//error peticion de baja de una comunidad con cuotas
			iCodigo = -6;			
		}
		else
		{
			//OK correcto estado y accion
			
			//Comunidad comunidad_modificada = convierteMovimientoenComunidad(movimiento);
			
			MovimientoComunidad movimiento_revisado = CLComunidades.revisaMovimiento(movimiento);
			
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -9;	
			}
			else
			{

				int indice = QMMovimientosComunidades.addMovimientoComunidad(movimiento_revisado);
				
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -7;
				}
				else
				{
					ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
					
					switch (COACCI) 
					{
						case A:
							
							com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de Alta...");
							
							Comunidad comunidad = convierteMovimientoenComunidad(movimiento_revisado);
							if (QMComunidades.addComunidad(comunidad))
							{
							
								if (QMListaComunidades.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), Integer.toString(indice)))
								{	
																

									com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "COACES:|"+movimiento_revisado.getCOACES()+"|");
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
										//error y rollback
										iCodigo = -12;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
										QMListaComunidades.delRelacionComunidad(Integer.toString(indice));
									}
								}
								else
								{
									//error y rollback
										iCodigo = -12;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
										QMComunidades.delComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM());
								}
							}
							else
							{
								//error y rollback
									iCodigo = -12;
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
							}
							break;
						case B:
							if (QMListaComunidades.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), Integer.toString(indice)))
							{
								
								com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Dando de Baja...");
								if (QMComunidades.setEstado(movimiento_revisado.getCOCLDO(), movimiento_revisado.getNUDCOM(), "B"))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//error y rollback
									iCodigo = -11;
									QMListaComunidades.delRelacionComunidad(Integer.toString(indice));
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
								}
							}
							else
							{
								//error y rollback
								iCodigo = -12;
								QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
							}
							break;
						case M:
							if (QMListaComunidades.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), Integer.toString(indice)))
							{
								com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Modificando...");
									
								movimiento.pintaMovimientoComunidad();
								movimiento_revisado.pintaMovimientoComunidad();
									
								if (QMComunidades.modComunidad(convierteMovimientoenComunidad(movimiento), movimiento.getCOCLDO(), movimiento.getNUDCOM()))
								{	
									//OK 
									iCodigo = 0;
								}
								else
								{
									//error y rollback
									iCodigo = -11;
									QMListaComunidades.delRelacionComunidad(Integer.toString(indice));
									QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
								}
							}
							else
							{
								//error y rollback
								iCodigo = -12;
								QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
							}
							break;
						case X:
								String sMovimientoAlta = QMListaComunidadesActivos.getActivoVinculadoComunidadID(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES());
								if (sMovimientoAlta.equals(""))
								{
									//error y rollback
									iCodigo = -12;
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
										//error y rollback
										iCodigo = -12;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
									}
								}
							break;
						case E:
								String sMovimientoBaja = QMListaComunidadesActivos.getActivoVinculadoComunidadID(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES());
								
								if (sMovimientoBaja.equals(""))
								{
									//error y rollback
									iCodigo = -12;
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
										//error y rollback
										iCodigo = -12;
										QMMovimientosComunidades.delMovimientoComunidad(Integer.toString(indice));
										QMListaComunidadesActivos.addRelacionComunidad(movimiento_revisado.getCOCLDO(),movimiento_revisado.getNUDCOM(), movimiento_revisado.getCOACES(), sMovimientoBaja);
									}
								}
							break;
					}	
				}
			}
		}

		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "Codigo de Salida:|"+iCodigo+"|");
		return iCodigo;
	}
}
