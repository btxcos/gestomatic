package com.provisiones.ll;

import java.util.ArrayList;
import java.util.Calendar;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMProvisiones;
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
		Utils.debugTrace(true, sClassName, sMethod, "tiempo:|"+Utils.timeStamp()+"|");
		String sTipo = CLActivos.compruebaTipoActivoSAREB(sCodCOACES);
		Utils.debugTrace(true, sClassName, sMethod, "tiempo:|"+Utils.timeStamp()+"|");
		String sProvision = QMProvisiones.getProvisionAbierta(sCOSPAT,sTipo);
		Utils.debugTrace(true, sClassName, sMethod, "tiempo:|"+Utils.timeStamp()+"|");
		if (sProvision.equals(""))
		{
			sProvision = QMProvisiones.getUltimaProvision();
			
			Utils.debugTrace(true, sClassName, sMethod, "sProvision:|"+sProvision+"|");
			
			Calendar fecha = Calendar.getInstance();
			
			if (sProvision.equals(""))
			{
				sProvision = fecha.get(Calendar.YEAR) + ValoresDefecto.DEF_COREAE + "000";
			}
			else
			{
				
				int iAño = Integer.parseInt(sProvision.substring(0, 4));
				int iNumero = Integer.parseInt(sProvision.substring(6));
			
				if (iAño < fecha.get(Calendar.YEAR))
				{
					iAño = fecha.get(Calendar.YEAR);
				}
			 
				iNumero++;
				
				String sNumero = Integer.toString(iNumero);
				
				if (iNumero < 10)
				{
					sNumero = "00"+sNumero;
				}
				else if (iNumero < 100)
				{
					sNumero = "00"+sNumero;
				}
				else if (iNumero > 999)
				{
					sNumero = "ERROR";
				}					
		
				sProvision = iAño+ValoresDefecto.DEF_COREAE+sNumero;
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
