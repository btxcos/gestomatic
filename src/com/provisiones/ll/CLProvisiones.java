package com.provisiones.ll;

import java.util.ArrayList;
import java.util.Calendar;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Provision;
import com.provisiones.types.ProvisionTabla;

public class CLProvisiones
{
	static String sClassName = CLProvisiones.class.getName();

	public static ArrayList<ProvisionTabla> buscarProvisionesAbiertas()
	{
		return QMProvisiones.buscaProvisionesAbiertas();
	}
	
	public static long buscarNumeroProvisionesCerradas()
	{
		return (QMProvisiones.buscaCantidadProvisionesCerradasPendientes());
	}
	
	public static String ultimaProvisionCerrada (String sCodCOSPAT)
	{
		return QMProvisiones.getUltimaProvisionCerrada(sCodCOSPAT);
	}
	public static String provisionAsignada (String sCodCOACES)
	{
		
		String sMethod = "provisionAsignada";
		
		String sCOSPAT = CLActivos.sociedadPatrimonialAsociada(sCodCOACES);
		
		if (QMCodigosControl.getDesSociedadesTitulizadas(sCOSPAT).equals(""))
		{
			sCOSPAT = "0";
		}

		String sTipo = CLActivos.compruebaTipoActivoSAREB(sCodCOACES);

		String sProvision = QMProvisiones.getProvisionAbierta(sCOSPAT,sTipo);

		if (sProvision.equals(""))
		{
			sProvision = QMProvisiones.getUltimaProvision();
	
			Calendar fecha = Calendar.getInstance();
			
			if (sProvision.equals(""))
			{
				sProvision = "00" + fecha.get(Calendar.YEAR)%100 + ValoresDefecto.DEF_COREAE + "000";
			}
			else
			{
				//Ampliacion de numeros de provision de fondos
				sProvision = Parser.formateaCampoNumerico(sProvision,9);
				int iAmpliacion = Integer.parseInt(sProvision.substring(0, 2));
				
				int iAño = Integer.parseInt(sProvision.substring(2, 4));
				int iNumero = Integer.parseInt(sProvision.substring(6));
			
				if (iAño < fecha.get(Calendar.YEAR)%100)
				{
					iAño = fecha.get(Calendar.YEAR)%100;
				}
			 
				iNumero++;
				
				String sNumero = Integer.toString(iNumero);
				
				if (iNumero < 10)
				{
					sNumero = "00"+sNumero;
				}
				else if (iNumero < 100)
				{
					sNumero = "0"+sNumero;
				}
				//Ampliacion de numeros de provision de fondos
				else if (iNumero > 999)
				{
					sNumero = "000";
					iNumero = 0;
					iAmpliacion++; 
					
				}
				
				String sAmpliacion = Integer.toString(iAmpliacion);
				
				if (iAmpliacion < 10)
				{
					sAmpliacion = "0"+sAmpliacion;
				}
				else if (iAmpliacion == 19 || iAmpliacion == 20)
				{
					sAmpliacion = "21";
				}
				else if (iAmpliacion > 99)
				{
					sNumero = "ERROR";
				}
		
				sProvision = sAmpliacion+iAño+ValoresDefecto.DEF_COREAE+sNumero;
			}			
			
			Provision provision = new Provision (sProvision, sCOSPAT, sTipo , "0","0","0","0",ValoresDefecto.DEF_ALTA);
			
			QMProvisiones.addProvision(provision);
		}
		
		return sProvision;
	}
	
	public static Provision detallesProvision (String sCodNUPROF)
	{
		return QMProvisiones.getProvision(sCodNUPROF);
	}
	
	public static boolean existeProvision (String sCodNUPROF)
	{
		return QMProvisiones.existeProvision(sCodNUPROF);
	}
	
	public static boolean estaCerrada (String sCodNUPROF)
	{
		return QMProvisiones.provisionCerrada(sCodNUPROF);
	}
	
	public static boolean cerrarProvision (Provision provision)
	{
		//Anular todos los gastos pendientes con fecha actual
		return QMProvisiones.modProvision(provision, provision.getsNUPROF());
	}

}
