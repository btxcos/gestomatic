package com.provisiones.types.informes;

public class CierreInforme 
{
	private String REFERENCIA = "";
	private String ACTIVO = "";
	private String ADJUDICACION = "";
	private String OBRA = "";
	private String GRUPO = "";
	private String TIPO = "";
	private String SUBTIPO = "";
	private String DEVENGO = "";
	private String VENCIMIENTO = "";
	private String CONCEPTO = "";
	private String PERIODO = "";
	private String IMPORTE = "";
	private String CIF = "";
	private String ACTA = "";
	private String REFERENCIACATASTRAL = "";
	private String OBSERVACIONES = "";
	
	//Constructor de clase

	public CierreInforme(String rEFERENCIA, String aCTIVO, String aDJUDICACION,
			String oBRA, String gRUPO, String tIPO, String sUBTIPO,
			String dEVENGO, String vENCIMIENTO, String cONCEPTO,
			String pERIODO, String iMPORTE, String cIF, String aCTA,
			String rEFERENCIACATASTRAL, String oBSERVACIONES) {
		super();
		REFERENCIA = rEFERENCIA;
		ACTIVO = aCTIVO;
		ADJUDICACION = aDJUDICACION;
		OBRA = oBRA;
		GRUPO = gRUPO;
		TIPO = tIPO;
		SUBTIPO = sUBTIPO;
		DEVENGO = dEVENGO;
		VENCIMIENTO = vENCIMIENTO;
		CONCEPTO = cONCEPTO;
		PERIODO = pERIODO;
		IMPORTE = iMPORTE;
		CIF = cIF;
		ACTA = aCTA;
		REFERENCIACATASTRAL = rEFERENCIACATASTRAL;
		OBSERVACIONES = oBSERVACIONES;
	}
	
	//Métodos de acceso

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public String getACTIVO() {
		return ACTIVO;
	}

	public void setACTIVO(String aCTIVO) {
		ACTIVO = aCTIVO;
	}

	public String getADJUDICACION() {
		return ADJUDICACION;
	}

	public void setADJUDICACION(String aDJUDICACION) {
		ADJUDICACION = aDJUDICACION;
	}

	public String getOBRA() {
		return OBRA;
	}

	public void setOBRA(String oBRA) {
		OBRA = oBRA;
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

	public String getVENCIMIENTO() {
		return VENCIMIENTO;
	}

	public void setVENCIMIENTO(String vENCIMIENTO) {
		VENCIMIENTO = vENCIMIENTO;
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

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}

	public String getACTA() {
		return ACTA;
	}

	public void setACTA(String aCTA) {
		ACTA = aCTA;
	}

	public String getREFERENCIACATASTRAL() {
		return REFERENCIACATASTRAL;
	}

	public void setREFERENCIACATASTRAL(String rEFERENCIACATASTRAL) {
		REFERENCIACATASTRAL = rEFERENCIACATASTRAL;
	}

	public String getOBSERVACIONES() {
		return OBSERVACIONES;
	}

	public void setOBSERVACIONES(String oBSERVACIONES) {
		OBSERVACIONES = oBSERVACIONES;
	}
}

