package com.provisiones.ll;

import com.provisiones.dal.qm.QMProvisiones;
import com.provisiones.types.Provision;

public class CLProvisiones
{
	static String sClassName = CLProvisiones.class.getName();
	
	public static String ultimaProvisionAbierta ()
	{
		return QMProvisiones.getProvisionAbierta();
	}
	
	public static Provision detallesProvision (String sCodNUPROF)
	{
		return QMProvisiones.getProvision(sCodNUPROF);
	}
	
	public static boolean existeProvision (String sCodNUPROF)
	{
		return QMProvisiones.existeProvision(sCodNUPROF);
	}
	
	public static boolean nuevaProvision (Provision provision)
	{
		boolean bSalida = false;
		
		String sUltimaProvision = ultimaProvisionAbierta();
		
		if (sUltimaProvision.equals("") && !existeProvision(provision.getsNUPROF()))
		{
			bSalida = QMProvisiones.addProvision(provision);
		}
		
		return bSalida;
	}
	
	public static boolean cerrarProvision (Provision provision)
	{
		return QMProvisiones.modProvision(provision, provision.getsNUPROF());
	}

}
