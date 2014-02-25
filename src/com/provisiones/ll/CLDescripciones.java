package com.provisiones.ll;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMCodigosControl;

public class CLDescripciones 
{
	//private static Logger logger = LoggerFactory.getLogger(CLDescripciones.class.getName());

	private CLDescripciones(){}
	
	public static String descripcionEstadoGasto (String sEstado)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TESGAST, QMCodigosControl.IESGAST, sEstado);
	}
	
	public static String descripcionTipoImpuestoGasto (String sCOIMPT)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TCOIMPT, QMCodigosControl.ICOIMPT, sCOIMPT);
	}
	
	public static String descripcionTipoDocumento (String sCOCLDO)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, sCOCLDO);
	}
	
	public static String descripcionTipoCuota (String sCOSBAC)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TCOSBGAT22, QMCodigosControl.ICOSBGAT22, sCOSBAC);
	}
	
	public static String descripcionPeriodicidad (String sPTPAGO)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TPTPAGO, QMCodigosControl.IPTPAGO, sPTPAGO);
	}
	
	public static String descripcionTipoImpuesto (String sCOSBAC)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TCOSBGAT21, QMCodigosControl.ICOSBGAT21, sCOSBAC);
	}
	
	public static String descripcionBinaria (String sCampo)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TBINARIA, QMCodigosControl.IBINARIA, sCampo);
	}
	
	public static String descripcionResolucion (String sBIRESO)
	{
		return QMCodigosControl.getDesCampo(ConnectionManager.getDBConnection(), QMCodigosControl.TBIRESO, QMCodigosControl.IBIRESO, sBIRESO);
	}
}
