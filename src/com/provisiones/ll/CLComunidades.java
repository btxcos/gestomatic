package com.provisiones.ll;

import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMListaComunidades;
import com.provisiones.dal.qm.QMMovimientosComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoComunidad;


public class CLComunidades 
{
	static String sClassName = CLComunidades.class.getName();
	
	public static MovimientoComunidad revisaMovimiento(MovimientoComunidad movimiento)
	{
		Comunidad comunidad = QMComunidades.getComunidad(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		String sEstado = QMComunidades.getEstado(movimiento.getCOCLDO(), movimiento.getNUDCOM());
		
		
		if (sEstado.equals("P"))
		{
			if (movimiento.getCOACCI().equals("A"))
			{
				
				if (movimiento.getNOMCOC().equals(""))
				{
					movimiento.setBITC01("#");
				}
				else
				{
					movimiento.setBITC01("S");
				}

				if (movimiento.getNODCCO().equals(""))
				{
					movimiento.setBITC02("#");
				}
				else
				{
					movimiento.setBITC02("S");
				}

				if (movimiento.getNOMPRC().equals(""))
				{
					movimiento.setBITC03("#");
				}
				else
				{
					movimiento.setBITC03("S");
				}
				
				if (movimiento.getNUTPRC().equals(""))
				{
					movimiento.setBITC04("#");
				}
				else
				{
					movimiento.setBITC04("S");
				}
				
				if (movimiento.getNOMADC().equals(""))
				{
					movimiento.setBITC05("#");
				}
				else
				{
					movimiento.setBITC05("S");
				}
				
				if (movimiento.getNUTADC().equals(""))
				{
					movimiento.setBITC06("#");
				}
				else
				{
					movimiento.setBITC06("S");
				}
				
				if (movimiento.getNODCAD().equals(""))
				{
					movimiento.setBITC07("#");
				}
				else
				{
					movimiento.setBITC07("S");
				}
				
				if (movimiento.getNUCCEN().equals("") && movimiento.getNUCCOF().equals("") && movimiento.getNUCCDI().equals("") && movimiento.getNUCCNT().equals(""))
				{
					movimiento.setBITC08("#");
				}
				else
				{
					movimiento.setBITC08("S");
				}
				
				if (movimiento.getOBTEXC().equals(""))
				{
					movimiento.setBITC09("#");
				}
				else
				{
					movimiento.setBITC09("A");
				}
				
				if (movimiento.getCOACES().equals(""))
				{
					movimiento.setBITC10("#");
				}
				else
				{
					movimiento.setBITC10("S");
				}
			}

		}
		if (sEstado.equals("A"))
		{	
			if (movimiento.getCOACCI().equals("M"))
			{
				if (movimiento.getNOMCOC().equals(comunidad.getNOMCOC()))
				{
					movimiento.setBITC01("#");
				}
				else
				{
					movimiento.setBITC01("S");
				}

				if (movimiento.getNODCCO().equals(comunidad.getNODCCO()))
				{
					movimiento.setBITC02("#");
				}
				else
				{
					movimiento.setBITC02("S");
				}

				if (movimiento.getNOMPRC().equals(comunidad.getNOMPRC()))
				{
					movimiento.setBITC03("#");
				}
				else
				{
					movimiento.setBITC03("S");
				}
				
				if (movimiento.getNUTPRC().equals(comunidad.getNUTPRC()))
				{
					movimiento.setBITC04("#");
				}
				else
				{
					movimiento.setBITC04("S");
				}
				
				if (movimiento.getNOMADC().equals(comunidad.getNOMADC()))
				{
					movimiento.setBITC05("#");
				}
				else
				{
					movimiento.setBITC05("S");
				}
				
				if (movimiento.getNUTADC().equals(comunidad.getNUTADC()))
				{
					movimiento.setBITC06("#");
				}
				else
				{
					movimiento.setBITC06("S");
				}
				
				if (movimiento.getNODCAD().equals(comunidad.getNODCAD()))
				{
					movimiento.setBITC07("#");
				}
				else
				{
					movimiento.setBITC07("S");
				}
				
				if (movimiento.getNUCCEN().equals(comunidad.getNUCCEN()) && movimiento.getNUCCOF().equals(comunidad.getNUCCOF()) && movimiento.getNUCCDI().equals(comunidad.getNUCCDI()) && movimiento.getNUCCNT().equals(comunidad.getNUCCNT()))
				{
					movimiento.setBITC08("#");
				}
				else
				{
					movimiento.setBITC08("S");
				}
				
				if (movimiento.getOBTEXC().equals(comunidad.getOBTEXC()))
				{
					movimiento.setBITC09("#");
				}
				else
				{
					movimiento.setBITC09("M");
				}
				movimiento.setCOACES("");
				movimiento.setBITC10("#");

			}
			if (movimiento.getCOACCI().equals("X") || movimiento.getCOACCI().equals("E"))
			{

				movimiento.setBITC01("#");
				movimiento.setNOMCOC("");
				movimiento.setBITC02("#");
				movimiento.setNODCCO("");
				movimiento.setBITC03("#");
				movimiento.setNOMPRC("");
				movimiento.setBITC04("#");
				movimiento.setNUTPRC("");
				movimiento.setBITC05("#");
				movimiento.setNOMADC("");
				movimiento.setBITC06("#");
				movimiento.setNUTADC("");
				movimiento.setBITC07("#");
				movimiento.setNODCAD("");
				movimiento.setBITC08("#");
				movimiento.setNUCCEN("");
				movimiento.setNUCCOF("");
				movimiento.setNUCCDI("");
				movimiento.setNUCCNT("");
				movimiento.setBITC09("#");
				movimiento.setOBTEXC("");

				if (!movimiento.getCOACES().equals(""))
				{
					movimiento.setBITC10("S");
				}
			}
		}

		return movimiento;

	}
	
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
			
			if (QMListaComunidades.existeRelacionComunidad(comunidad.getCOCLDO(),comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento))
				QMListaComunidades.setValidado(comunidad.getCOCLDO(),comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento, sValidado);
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
}
