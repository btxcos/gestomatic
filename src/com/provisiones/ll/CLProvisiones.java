package com.provisiones.ll;

import java.util.ArrayList;
import java.util.Calendar;

import com.provisiones.dal.qm.QMCodigosControl;
import com.provisiones.dal.qm.QMProvisiones;
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
	
	public static String ultimaProvisionCerrada (String sCodCOSPAT)
	{
		return QMProvisiones.getUltimaProvisionCerrada(sCodCOSPAT);
	}
	public static String provisionAsignada (String sCodCOACES)
	{
		
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
				sProvision = fecha.get(Calendar.YEAR) + ValoresDefecto.DEF_COREAE + "000";
			}
			else
			{
				

				String[] arrayprovision = sProvision.split(ValoresDefecto.DEF_COREAE);
				int iAño = Integer.parseInt(arrayprovision[0]);
				int iNumero = Integer.parseInt(arrayprovision[1]);
			
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
	
	/*public static boolean nuevaProvision (Provision provision)
	{
		boolean bSalida = false;
		
		String sUltimaProvision = ultimaProvisionAbierta(provision.getsCOSPAT());
		
		if (sUltimaProvision.equals("") && !existeProvision(provision.getsNUPROF()))
		{
			bSalida = QMProvisiones.addProvision(provision);
		}
		
		return bSalida;
	}*/
	
	public static boolean cerrarProvision (Provision provision)
	{
		return QMProvisiones.modProvision(provision, provision.getsNUPROF());
	}

}
