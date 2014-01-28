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
}
