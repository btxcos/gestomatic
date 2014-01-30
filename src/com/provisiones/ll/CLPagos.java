package com.provisiones.ll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCuotas;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.types.Pago;

public class CLPagos 
{
	private static Logger logger = LoggerFactory.getLogger(CLPagos.class.getName());

	private CLPagos(){}
	
	//ID
	public static String buscarCodigoPago (String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		return QMCuotas.getCuotaID(ConnectionManager.getDBConnection(),sCodCOACES, sCodCOCLDO, sCodNUDCOM, sCodCOSBAC);
	}
	
	public static String buscarFechaPago(String sGastoID)
	{
		return QMPagos.getFechaPago(ConnectionManager.getDBConnection(),sGastoID);
	}

	public static boolean pagaGasto(Pago pago)
	{
		return (QMPagos.addPago(ConnectionManager.getDBConnection(), pago) != 0);
	}
}
