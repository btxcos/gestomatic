package com.provisiones.pl.detalles;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLPagos;
import com.provisiones.ll.CLTransferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Gasto;
import com.provisiones.types.Pago;
import com.provisiones.types.Transicion;
import com.provisiones.types.transferencias.N34.TransferenciaN34;

public class GestorDetallesGasto implements Serializable 
{
	private static final long serialVersionUID = -2868110080833865958L;

	private static Logger logger = LoggerFactory.getLogger(GestorDetallesGasto.class.getName());
	
	private boolean bDevolucion = false;
	
	private String sCOACES = "";
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sPTPAGO = "";
	private String sFEDEVE = "";
	private String sFFGTVP = "";
	private String sFEPAGA = "";
	private String sFELIPG = "";
	private String sCOSIGA = "";
	private String sFEEESI = "";
	private String sFEECOI = "";
	private String sFEEAUI = "";
	private String sFEEPAI = "";
	private String sIMNGAS = "";
	private String sYCOS02 = "";
	private String sIMRGAS = "";
	private String sYCOS04 = "";
	private String sIMDGAS = "";
	private String sYCOS06 = "";
	private String sIMCOST = "";
	private String sYCOS08 = "";
	private String sIMOGAS = "";
	private String sYCOS10 = "";
	private String sIMDTGA = "";
	private String sCOUNMO = "";
	private String sIMIMGA = "";
	private String sCOIMPT = "";
	private String sCOTNEG = "";
	private String sCOENCX = "";
	private String sCOOFCX = "";
	private String sNUCONE = "";
	private String sNUPROF = "";
	private String sFEAGTO = "";
	private String sCOMONA = "";
	private String sBIAUTO = "";
	private String sFEAUFA = "";
	private String sCOTERR = "";
	private String sFMPAGN = "";
	private String sFEAPLI = "";
	private String sCOAPII = "";
	private String sCOSPII = "";
	private String sNUCLII = "";
	
	private long liCodGasto = 0;
	
	//Pago
	private String sFEPGPR = "";
	private String sTipoPago = "";
	
	//Recargo
	private String sRecargoAdicional = "0";

	//Transferencia
	private boolean bDetallesTransferencia = true;
	
	private String sPais = "";	
	private String sDCIBAN = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	
	private String sDescripcion = "";

	private String sNota = "";
	private String sNotaOriginal = "";
	private boolean bConNotas = false;
	
	private long liCodCuota = 0;
	
	private boolean bSinCuota = true;
	
	
	public GestorDetallesGasto()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesGasto...");
			cargarDetallesGasto();
		}
	}
	
	public void volver(ActionEvent actionEvent)
	{
		//return Sesion.cargarHistorial("GestorDetallesGasto");
		
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect(Sesion.cargarHistorial());
		}
		catch (IOException e)
		{
			FacesMessage msg;
			
			String sMsg = "ERROR: Ocurrió un problema al intentar regresar. Por favor, avise a soporte.";
			
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			

		}
		
	}
	
	public void cargarDetallesGasto()
	{
		logger.debug("Cargando Gasto...");

		//String sCodGasto = Sesion.cargarDetalle();
		
		String sCodGasto = CLGastos.recuperaID();
		
		logger.debug("sCodGasto:|"+sCodGasto+"|");

		FacesMessage msg;
		
		String sMsg = "";

		if (!sCodGasto.equals(""))
		{
			
			try
			{
				
				this.liCodGasto = Long.parseLong(sCodGasto);
				
			  	Gasto gasto = CLGastos.buscarDetallesGasto(liCodGasto);

		    	logger.debug(gasto.logGasto());
		    	
		    	this.sCOACES = gasto.getCOACES();
			  	
		    	this.sCOGRUG = gasto.getCOGRUG();
		    	this.sCOTPGA = gasto.getCOTPGA();
		    	this.sCOSBGA = gasto.getCOSBGA();
		    	this.sFEDEVE = Utils.recuperaFecha(gasto.getFEDEVE());
		 

				this.sPTPAGO = gasto.getPTPAGO();

				this.sFFGTVP = Utils.recuperaFecha(gasto.getFFGTVP());
				
				//TODO sacar de datos de pago
				//this.sFEPAGA = Utils.recuperaFecha(gasto.getFEPAGA());
				this.sFELIPG = Utils.recuperaFecha(gasto.getFELIPG());
				this.sCOSIGA = gasto.getCOSIGA();
				this.sFEEESI = Utils.recuperaFecha(gasto.getFEEESI());
				this.sFEECOI = Utils.recuperaFecha(gasto.getFEECOI());
				this.sFEEAUI = Utils.recuperaFecha(gasto.getFEEAUI());
				this.sFEEPAI = Utils.recuperaFecha(gasto.getFEEPAI());
				this.sIMNGAS = Utils.recuperaImporte(gasto.getYCOS02().equals("-"),gasto.getIMNGAS());
				this.sIMRGAS = Utils.recuperaImporte(gasto.getYCOS04().equals("-"),gasto.getIMRGAS());
				this.sIMDGAS = Utils.recuperaImporte(gasto.getYCOS06().equals("-"),gasto.getIMDGAS());
				this.sIMCOST = Utils.recuperaImporte(gasto.getYCOS08().equals("-"),gasto.getIMCOST());
				this.sIMOGAS = Utils.recuperaImporte(gasto.getYCOS10().equals("-"),gasto.getIMOGAS());
				this.sIMDTGA = Utils.recuperaImporte(false,gasto.getIMDTGA());
				this.sIMIMGA = Utils.recuperaImporte(false,gasto.getIMIMGA());
				this.sCOIMPT = gasto.getCOIMPT();
				
				this.sCOTNEG = gasto.getCOTNEG();
				this.sFEAGTO = Utils.recuperaFecha(gasto.getFEAGTO());
				this.sCOMONA = gasto.getCOMONA();
				this.sBIAUTO = gasto.getBIAUTO();
				this.sFEAUFA = Utils.recuperaFecha(gasto.getFEAUFA());

				
				this.sCOUNMO = ValoresDefecto.DEF_COUNMO;
				
				//TODO sacar de datos de pago
				//this.sCOENCX = ValoresDefecto.DEF_COENCX;
				//this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
				//this.sNUCONE = ValoresDefecto.DEF_NUCONE;
				
				this.sNUPROF = CLGastos.obtenerProvisionDeGasto(liCodGasto);

				this.sCOTERR = ValoresDefecto.DEF_COTERR;
				
				//Datos de pago
				long liCodPago = CLPagos.buscarCodigoPago(liCodGasto);
				
				if (liCodPago != 0)
				{
					Pago pago = CLPagos.buscarPago(liCodPago);

					this.sFEPGPR = Utils.recuperaFecha(pago.getsFEPGPR());
					this.sTipoPago = pago.getsTipoPago();
					
					//Recargo
					this.sRecargoAdicional = Utils.recuperaRecargo(false, pago.getsRecargoAdicional());
					
					if (sTipoPago.equals(ValoresDefecto.DEF_PAGO_VENTANILLA))
					{
						this.bDetallesTransferencia = true;
						
						this.sPais = "";	
						this.sDCIBAN = "";
						this.sNUCCEN = "";
						this.sNUCCOF = "";
						this.sNUCCDI = "";
						this.sNUCCNT = "";
						
						this.sDescripcion = "Pago en ventanilla";
					}
					else if (sTipoPago.equals(ValoresDefecto.DEF_PAGO_NORMA34))
					{
						this.bDetallesTransferencia = false;
						
						long liCodOperacion = Long.parseLong(pago.getsCodOperacion());
						
						TransferenciaN34 tranferencia = CLTransferencias.buscarTransferenciaN34(liCodOperacion);
						
						this.sPais = "ES";	
						
						this.sNUCCEN = tranferencia.getsNUCCEN();
						this.sNUCCOF = tranferencia.getsNUCCOF();
						this.sNUCCDI = tranferencia.getsNUCCDI();
						this.sNUCCNT = tranferencia.getsNUCCNT();
						this.sDCIBAN = Utils.calculaDCIBAN(sPais, sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT);
						
						
						this.sDescripcion = "Pago por transferencia";
					}
					
					//Cuenta

				}

				
				//TODO sacar de datos de pago
				//this.sFMPAGN = Utils.recuperaFecha(ValoresDefecto.DEF_FMPAGN);
			
				//this.sFEAPLI = ValoresDefecto.DEF_FEAPLI;
				this.sCOAPII = ValoresDefecto.DEF_COAPII;
				this.sCOSPII = ValoresDefecto.DEF_COSPII_GA;
				this.sNUCLII = ValoresDefecto.DEF_NUCLII;
				
				this.sNotaOriginal = CLGastos.buscarNota(liCodGasto);
				this.sNota = sNotaOriginal;
				
				this.bConNotas = !sNota.isEmpty();
				
				this.liCodCuota = CLGastos.obtenerCuotaDeGasto(liCodGasto);
				
				this.bSinCuota = (liCodCuota == 0);
				
				sMsg = "El Gasto se cargó correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: Ocurrió un error al cargar los datos del Gasto. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
		}
		else
		{
			sMsg = "ERROR: Ocurrió un error al recuperar el Gasto. Por favor, revise los datos y avise a soporte.";
			msg = Utils.pfmsgFatal(sMsg);
			logger.error(sMsg);
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
    
    public void restablecerNota(ActionEvent actionEvent) 
    {  
    	this.sNota = sNotaOriginal;
    	this.bConNotas = !sNota.isEmpty();
    }
	
	public void guardaNota (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			if (sNota.equals(sNotaOriginal))
			{
				sMsg = "La Nota no se ha modificado, no ha habido cambios.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			else
			{
				if (CLGastos.guardarNota(liCodGasto, sNota))
				{
					sMsg = "Nota guardada correctamente.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				else
				{
					sMsg = "ERROR: Ocurrió un error al guardar la Nota de la Referencia Catastral. Por favor, revise los datos y avise a soporte.";
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
				}
			}
			

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
	public void cargarDetallesActivo(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("sCOACES:|"+sCOACES+"|");
			
			if (sCOACES != "")
			{
		    	//Sesion.guardaDetalle(sCOACES);
		    	//Sesion.guardarHistorial("detallesgasto.xhtml","GestorDetallesActivo");

		    	Transicion transicion = new Transicion (
		    			sCOACES,
		    			ValoresDefecto.ID_ACTIVO,
		    			"detallesgasto.xhtml",
		    			"GestorDetallesActivo");
		    	
		    	Sesion.guardarTransicion(transicion, false);
		    	
		    	sPagina = "detallesactivo.xhtml";
		    	
				try 
				{
					logger.debug("Redirigiendo...");
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					String sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado ningún gasto.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}

		//return sPagina;
    }
	
	
	public void cargarDetallesProvision(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	logger.debug("sNUPROF:"+sNUPROF);

			if (sNUPROF != "")
			{

		    	Transicion transicion = new Transicion (
		    			sNUPROF,
		    			ValoresDefecto.ID_PROVISION,
		    			"detallesgasto.xhtml",
		    			"GestorDetallesProvision");
		    	
		    	Sesion.guardarTransicion(transicion, false);

		    	//Sesion.guardaDetalle(sNUPROF);
		    	//Sesion.guardarHistorial("detallesgasto.xhtml","GestorDetallesProvision");

		    	sPagina = "detallesprovision.xhtml";
		    	
				try 
				{
					logger.debug("Redirigiendo...");
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					String sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
					

				}
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado una provisión.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			


		}

		//return sPagina;

    }
	
	public void cargarDetallesCuota(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
	    	logger.debug("liCodCuota:"+liCodCuota);

			if (liCodCuota != 0)
			{

		    	Transicion transicion = new Transicion (
		    			Long.toString(liCodCuota),
		    			ValoresDefecto.ID_CUOTA,
		    			"detallesgasto.xhtml",
		    			"GestorDetallesCuota");
		    	
		    	Sesion.guardarTransicion(transicion, false);

		    	sPagina = "detallescuota.xhtml";
		    	
				try 
				{
					logger.debug("Redirigiendo...");
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					String sMsg = "ERROR: Ocurrió un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
					

				}
			}
			else
			{
				FacesMessage msg;

				msg = Utils.pfmsgWarning("No se ha seleccionado una cuota.");
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			


		}

		//return sPagina;

    }
	
	public String getsCOACES() {
		return sCOACES;
	}
	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}
	public boolean isbDevolucion() {
		return bDevolucion;
	}

	public void setbDevolucion(boolean bDevolucion) {
		this.bDevolucion = bDevolucion;
	}

	public String getsCOGRUG() {
		return sCOGRUG;
	}
	public void setsCOGRUG(String sCOGRUG) {
		this.sCOGRUG = sCOGRUG;
	}
	public String getsCOTPGA() {
		return sCOTPGA;
	}
	public void setsCOTPGA(String sCOTPGA) {
		this.sCOTPGA = sCOTPGA;
	}
	public String getsCOSBGA() {
		return sCOSBGA;
	}
	public void setsCOSBGA(String sCOSBGA) {
		this.sCOSBGA = sCOSBGA;
	}
	public String getsPTPAGO() {
		return sPTPAGO;
	}
	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}
	public String getsFEDEVE() {
		return sFEDEVE;
	}
	public void setsFEDEVE(String sFEDEVE) {
		this.sFEDEVE = sFEDEVE;
	}
	public String getsFFGTVP() {
		return sFFGTVP;
	}
	public void setsFFGTVP(String sFFGTVP) {
		this.sFFGTVP = sFFGTVP;
	}
	public String getsFEPAGA() {
		return sFEPAGA;
	}
	public void setsFEPAGA(String sFEPAGA) {
		this.sFEPAGA = sFEPAGA;
	}
	public String getsFELIPG() {
		return sFELIPG;
	}
	public void setsFELIPG(String sFELIPG) {
		this.sFELIPG = sFELIPG;
	}
	public String getsCOSIGA() {
		return sCOSIGA;
	}
	public void setsCOSIGA(String sCOSIGA) {
		this.sCOSIGA = sCOSIGA;
	}
	public String getsFEEESI() {
		return sFEEESI;
	}
	public void setsFEEESI(String sFEEESI) {
		this.sFEEESI = sFEEESI;
	}
	public String getsFEECOI() {
		return sFEECOI;
	}
	public void setsFEECOI(String sFEECOI) {
		this.sFEECOI = sFEECOI;
	}
	public String getsFEEAUI() {
		return sFEEAUI;
	}
	public void setsFEEAUI(String sFEEAUI) {
		this.sFEEAUI = sFEEAUI;
	}
	public String getsFEEPAI() {
		return sFEEPAI;
	}
	public void setsFEEPAI(String sFEEPAI) {
		this.sFEEPAI = sFEEPAI;
	}
	public String getsIMNGAS() {
		return sIMNGAS;
	}
	public void setsIMNGAS(String sIMNGAS) {
		this.sIMNGAS = sIMNGAS;
	}
	public String getsYCOS02() {
		return sYCOS02;
	}
	public void setsYCOS02(String sYCOS02) {
		this.sYCOS02 = sYCOS02;
	}
	public String getsIMRGAS() {
		return sIMRGAS;
	}
	public void setsIMRGAS(String sIMRGAS) {
		this.sIMRGAS = sIMRGAS;
	}
	public String getsYCOS04() {
		return sYCOS04;
	}
	public void setsYCOS04(String sYCOS04) {
		this.sYCOS04 = sYCOS04;
	}
	public String getsIMDGAS() {
		return sIMDGAS;
	}
	public void setsIMDGAS(String sIMDGAS) {
		this.sIMDGAS = sIMDGAS;
	}
	public String getsYCOS06() {
		return sYCOS06;
	}
	public void setsYCOS06(String sYCOS06) {
		this.sYCOS06 = sYCOS06;
	}
	public String getsIMCOST() {
		return sIMCOST;
	}
	public void setsIMCOST(String sIMCOST) {
		this.sIMCOST = sIMCOST;
	}
	public String getsYCOS08() {
		return sYCOS08;
	}
	public void setsYCOS08(String sYCOS08) {
		this.sYCOS08 = sYCOS08;
	}
	public String getsIMOGAS() {
		return sIMOGAS;
	}
	public void setsIMOGAS(String sIMOGAS) {
		this.sIMOGAS = sIMOGAS;
	}
	public String getsYCOS10() {
		return sYCOS10;
	}
	public void setsYCOS10(String sYCOS10) {
		this.sYCOS10 = sYCOS10;
	}
	public String getsIMDTGA() {
		return sIMDTGA;
	}
	public void setsIMDTGA(String sIMDTGA) {
		this.sIMDTGA = sIMDTGA;
	}
	public String getsCOUNMO() {
		return sCOUNMO;
	}
	public void setsCOUNMO(String sCOUNMO) {
		this.sCOUNMO = sCOUNMO;
	}
	public String getsIMIMGA() {
		return sIMIMGA;
	}
	public void setsIMIMGA(String sIMIMGA) {
		this.sIMIMGA = sIMIMGA;
	}
	public String getsCOIMPT() {
		return sCOIMPT;
	}
	public void setsCOIMPT(String sCOIMPT) {
		this.sCOIMPT = sCOIMPT;
	}
	public String getsCOTNEG() {
		return sCOTNEG;
	}
	public void setsCOTNEG(String sCOTNEG) {
		this.sCOTNEG = sCOTNEG;
	}
	public String getsCOENCX() {
		return sCOENCX;
	}
	public void setsCOENCX(String sCOENCX) {
		this.sCOENCX = sCOENCX;
	}
	public String getsCOOFCX() {
		return sCOOFCX;
	}
	public void setsCOOFCX(String sCOOFCX) {
		this.sCOOFCX = sCOOFCX;
	}
	public String getsNUCONE() {
		return sNUCONE;
	}
	public void setsNUCONE(String sNUCONE) {
		this.sNUCONE = sNUCONE;
	}
	public String getsNUPROF() {
		return sNUPROF;
	}
	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}
	public String getsFEAGTO() {
		return sFEAGTO;
	}
	public void setsFEAGTO(String sFEAGTO) {
		this.sFEAGTO = sFEAGTO;
	}
	public String getsCOMONA() {
		return sCOMONA;
	}
	public void setsCOMONA(String sCOMONA) {
		this.sCOMONA = sCOMONA;
	}
	public String getsBIAUTO() {
		return sBIAUTO;
	}
	public void setsBIAUTO(String sBIAUTO) {
		this.sBIAUTO = sBIAUTO;
	}
	public String getsFEAUFA() {
		return sFEAUFA;
	}
	public void setsFEAUFA(String sFEAUFA) {
		this.sFEAUFA = sFEAUFA;
	}
	public String getsCOTERR() {
		return sCOTERR;
	}
	public void setsCOTERR(String sCOTERR) {
		this.sCOTERR = sCOTERR;
	}
	public String getsFMPAGN() {
		return sFMPAGN;
	}
	public void setsFMPAGN(String sFMPAGN) {
		this.sFMPAGN = sFMPAGN;
	}
	public String getsFEPGPR() {
		return sFEPGPR;
	}
	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
	}
	public String getsFEAPLI() {
		return sFEAPLI;
	}
	public void setsFEAPLI(String sFEAPLI) {
		this.sFEAPLI = sFEAPLI;
	}
	public String getsCOAPII() {
		return sCOAPII;
	}
	public void setsCOAPII(String sCOAPII) {
		this.sCOAPII = sCOAPII;
	}
	public String getsCOSPII() {
		return sCOSPII;
	}
	public void setsCOSPII(String sCOSPII) {
		this.sCOSPII = sCOSPII;
	}
	public String getsNUCLII() {
		return sNUCLII;
	}
	public void setsNUCLII(String sNUCLII) {
		this.sNUCLII = sNUCLII;
	}	

	public String getsTipoPago() {
		return sTipoPago;
	}

	public void setsTipoPago(String sTipoPago) {
		this.sTipoPago = sTipoPago;
	}

	public String getsRecargoAdicional() {
		return sRecargoAdicional;
	}

	public void setsRecargoAdicional(String sRecargoAdicional) {
		this.sRecargoAdicional = sRecargoAdicional;
	}

	public boolean isbDetallesTransferencia() {
		return bDetallesTransferencia;
	}

	public void setbDetallesTransferencia(boolean bDetallesTransferencia) {
		this.bDetallesTransferencia = bDetallesTransferencia;
	}

	public String getsPais() {
		return sPais;
	}

	public void setsPais(String sPais) {
		this.sPais = sPais;
	}

	public String getsDCIBAN() {
		return sDCIBAN;
	}

	public void setsDCIBAN(String sDCIBAN) {
		this.sDCIBAN = sDCIBAN;
	}

	public String getsNUCCEN() {
		return sNUCCEN;
	}

	public void setsNUCCEN(String sNUCCEN) {
		this.sNUCCEN = sNUCCEN;
	}

	public String getsNUCCOF() {
		return sNUCCOF;
	}

	public void setsNUCCOF(String sNUCCOF) {
		this.sNUCCOF = sNUCCOF;
	}

	public String getsNUCCDI() {
		return sNUCCDI;
	}

	public void setsNUCCDI(String sNUCCDI) {
		this.sNUCCDI = sNUCCDI;
	}

	public String getsNUCCNT() {
		return sNUCCNT;
	}

	public void setsNUCCNT(String sNUCCNT) {
		this.sNUCCNT = sNUCCNT;
	}

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

	public boolean isbConNotas() {
		return bConNotas;
	}

	public void setbConNotas(boolean bConNotas) {
		this.bConNotas = bConNotas;
	}

	public boolean isbSinCuota() {
		return bSinCuota;
	}

	public void setbSinCuota(boolean bSinCuota) {
		this.bSinCuota = bSinCuota;
	}
	
	
}
