package com.provisiones.types.informes;

public class GastosActivoInforme 
{
	private String REFERENCIA = "";
	private String GRUPO = "";
	private String TIPO = "";
	private String SUBTIPO = "";
	private String DEVENGO = "";	
	private String CONCEPTO = "";
	private String PERIODO = "";
	private String IMPORTE = "";
	private String PROVISION = "";
	private String PAGO = "";

	//Constructor de clase
	
	public GastosActivoInforme(String rEFERENCIA, String gRUPO, String tIPO,
			String sUBTIPO, String dEVENGO, String cONCEPTO, String pERIODO,
			String iMPORTE, String pROVISION, String pAGO) {
		super();
		setREFERENCIA(rEFERENCIA);
		GRUPO = gRUPO;
		TIPO = tIPO;
		SUBTIPO = sUBTIPO;
		DEVENGO = dEVENGO;
		CONCEPTO = cONCEPTO;
		PERIODO = pERIODO;
		IMPORTE = iMPORTE;
		PROVISION = pROVISION;
		PAGO = pAGO;
	}
	
	//Métodos de acceso

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public String getGRUPO() {
		return GRUPO;
	}

	public void setGRUPO(String gRUPO) {
		GRUPO = gRUPO;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public String getSUBTIPO() {
		return SUBTIPO;
	}

	public void setSUBTIPO(String sUBTIPO) {
		SUBTIPO = sUBTIPO;
	}

	public String getDEVENGO() {
		return DEVENGO;
	}

	public void setDEVENGO(String dEVENGO) {
		DEVENGO = dEVENGO;
	}

	public String getCONCEPTO() {
		return CONCEPTO;
	}

	public void setCONCEPTO(String cONCEPTO) {
		CONCEPTO = cONCEPTO;
	}

	public String getPERIODO() {
		return PERIODO;
	}

	public void setPERIODO(String pERIODO) {
		PERIODO = pERIODO;
	}

	public String getIMPORTE() {
		return IMPORTE;
	}

	public void setIMPORTE(String iMPORTE) {
		IMPORTE = iMPORTE;
	}

	public String getPROVISION() {
		return PROVISION;
	}

	public void setPROVISION(String pROVISION) {
		PROVISION = pROVISION;
	}

	public String getPAGO() {
		return PAGO;
	}

	public void setPAGO(String pAGO) {
		PAGO = pAGO;
	}
	
}
