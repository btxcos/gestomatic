package com.provisiones.pl.pagos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuentas;
import com.provisiones.ll.CLPagos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.Cuenta;
import com.provisiones.types.Nota;
import com.provisiones.types.Provision;
import com.provisiones.types.RecargoImporte;
import com.provisiones.types.tablas.ComunidadTabla;
import com.provisiones.types.tablas.ProvisionTabla;

public class GestorPagosComunidad implements Serializable 
{

	private static final long serialVersionUID = 2746279640793626206L;

	private static Logger logger = LoggerFactory.getLogger(GestorPagosComunidad.class.getName());

	//Campos provisión
	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sTAS = "";

	private String sCOGRUG = "";
	private String sCOTPGA = "";
	
	private String sFEPFON = "";
	private String sNumGastos = "";	
	private String sValorTotal = "";
	private String sFechaEnvio = "";

	private String sGastosAutorizados = "";
	private String sValorAutorizado = "";
	private String sFechaAutorizado = "";

	private String sGastosPagados = "";
	private String sValorPagado = "";
	private String sFechaPagado = "";
	
	private String sGastosPorPagar = "";
	private String sValorPorPagar = "";
	private String sFechaLimite = "";
	
	private String sCodEstado = "";

	
	//Campos Comunidad
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	
	//Búsqueda provisión
	private String sNUPROFB = "";
	private String sFEPFONB = "";

	
	//Búsqueda Comunidad
	private String sCOACESB = "";
	
	
	//Fecha de pago
	private String sFEPGPR = "";
	private String sFechaUltimoDevengo = "";
	
	//Recargo
	private String sTipoRecargo = "";
	private String sValorRecargo = "0";
	private boolean bRecargo = true;
	
	//Cuenta de pago
	private String sPais = "";	
	private String sDCIBAN = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";
	private String sDescripcion = "";
	
	private String sTipoPago = "";
	
	private long iCodComunidad = 0;
	
	//Notas
	private String sNota = "";
	
	private Map<String,String> tiposrecargoHM = new LinkedHashMap<String, String>();
	
	private transient ProvisionTabla provisionseleccionada = null;
	private transient ArrayList<ProvisionTabla> tablaprovisiones = null;
	
	private transient ComunidadTabla comunidadseleccionada = null;
	private transient ArrayList<ComunidadTabla> tablacomunidades = null;
	
	private transient Cuenta cuentacomunidadseleccionada = null;
	private transient ArrayList<Cuenta> tablacuentascomunidad = null;
	
	public GestorPagosComunidad()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorPagosComunidad...");
			
			tiposrecargoHM.put("Cantidad fija (¤)","1");
			tiposrecargoHM.put("Proporcional (%)", "2");
		}
	}
	
	public void borrarCamposBuscarProvision()
	{
    	this.sFEPFONB = "";
	}
	
	public void borrarResultadosBuscarProvision()
	{
    	this.provisionseleccionada = null;
    	this.tablaprovisiones = null;
	}
	
    public void limpiarPlantillaProvision(ActionEvent actionEvent) 
    {  
    	this.sNUPROFB = "";

    	borrarCamposBuscarProvision();
    	
    	borrarResultadosBuscarProvision();
    }
    
	public void borrarCamposCuenta()
	{
    	this.sPais = "";
    	this.sDCIBAN = "";
    	this.sNUCCEN = "";
    	this.sNUCCOF = "";
    	this.sNUCCDI = "";
    	this.sNUCCNT = "";
    	this.sDescripcion = "";
	}
	
	public void borrarResultadosBuscarCuenta()
	{
    	this.cuentacomunidadseleccionada = null;
    	this.tablacuentascomunidad = null;
	}
	
    public void limpiarPlantillaCuenta(ActionEvent actionEvent) 
    {
    	borrarCamposCuenta();
    	borrarResultadosBuscarCuenta();
    }
    
    public void borrarCamposProvision()
    {
    	this.sNUPROF = "";
    	this.sCOSPAT = "";
    	this.sTAS = "";

    	this.sCOGRUG = "";
    	this.sCOTPGA = "";
    	
    	this.sFEPFON = "";
    	this.sNumGastos = "";	
    	this.sValorTotal = "";
    	this.sFechaEnvio = "";

    	this.sGastosAutorizados = "";
    	this.sValorAutorizado = "";
    	this.sFechaAutorizado = "";

    	this.sGastosPagados = "";
    	this.sValorPagado = "";
    	this.sFechaPagado = "";

    	this.sGastosPorPagar = "";
    	this.sValorPorPagar = "";
    	this.sFechaLimite = "";
    	
    	this.sCodEstado = "";
    }

	public void borrarCamposComunidad()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sNOMCOC = "";
	}
	
	public void borrarCamposBuscarComunidad()
	{
    	this.sCOACESB = "";
	}
	
	public void borrarResultadosBuscarComunidad()
	{
		this.iCodComunidad = 0;
    	this.comunidadseleccionada = null;
    	this.tablacomunidades = null;
	}
	
    public void limpiarPlantillaComunidad(ActionEvent actionEvent) 
    {
    	borrarCamposBuscarComunidad();
     }
    
	public void borrarCamposPago()
	{
    	this.sFEPGPR = "";
    	this.sFechaUltimoDevengo = "";
    	
    	this.sTipoRecargo = "";
    	this.sValorRecargo = "0";
    	this.bRecargo = true;
    	
    	borrarCamposCuenta();
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
		this.sNUPROFB = "";
		this.sNota = "";

		borrarCamposProvision();
		borrarCamposBuscarProvision();
		borrarResultadosBuscarProvision();
		borrarCamposComunidad();
		borrarResultadosBuscarComunidad();
		borrarCamposPago();
		borrarResultadosBuscarCuenta();
    }
    
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
    
	public void buscarProvisiones (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 
			
			String sFecha = Utils.compruebaFecha(sFEPFONB);
			
			this.setProvisionseleccionada(null);
			
			if (sFecha.equals("#"))
			{
				sMsg = "La fecha proporcionada no es válida. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				this.setTablaprovisiones(null);
			}
			else
			{
				String sNUPROFF = "";
				String sCOSPATF = "";
				String sDCOSPATF = "";
				String sTASF = "";
				String sDTASF = "";	
				String sCOGRUGF = "";
				String sDCOGRUGF = "";	
				String sCOTPGAF = "";
				String sDCOTPGAF = "";
				String sFEPFONF = sFecha;
				String sVALORF = "";
				String sGASTOSF = "";
				String sESTADOF = "";
				
				ProvisionTabla filtro = new ProvisionTabla(
						sNUPROFF, 
						sCOSPATF, 
						sDCOSPATF,
						sTASF, 
						sDTASF, 
						sCOGRUGF, 
						sDCOGRUGF, 
						sCOTPGAF, 
						sDCOTPGAF, 
						sFEPFONF, 
						sVALORF, 
						sGASTOSF,
						sESTADOF);

				//this.setTablaprovisiones(CLProvisiones.buscarProvisionesAutorizadasConFiltro(filtro));

				this.setTablaprovisiones(CLProvisiones.buscarProvisionesComunidadAutorizadasConFiltro(filtro));
				
				
				sMsg = "Encontradas "+getTablaprovisiones().size()+" provisiones relacionadas.";
				msg = Utils.pfmsgInfo(sMsg);
				
				logger.info(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void seleccionarProvision(ActionEvent actionEvent) 
    { 
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	String sMsg = "";

	    	this.sNUPROFB  = provisionseleccionada.getNUPROF();
	    	
	    	sMsg = "Provision '"+sNUPROFB+"' seleccionada.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void cargarProvision(ActionEvent actionEvent)
	{
		logger.debug("Cargando Provision...");
		
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			borrarCamposProvision();
			
			try
			{
				Long.parseLong(sNUPROFB);
				
				if (sNUPROFB.isEmpty())
				{
					sMsg = "ERROR: Debe informar la Provisión para realizar una búsqueda. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);

				}
				else if (CLProvisiones.existeProvision(sNUPROFB))
				{
					if (CLProvisiones.estaPagada(sNUPROFB))
					{
						sMsg = "La Provisión '"+sNUPROFB+"' ya esta pagada. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);

					}
					if (!CLProvisiones.tieneComunidad(sNUPROFB))
					{
						sMsg = "La Provisión '"+sNUPROFB+"' no tiene Activos asociados a una Comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);

					}
					else
					{
						this.sNUPROF = sNUPROFB;
						
						logger.debug("sNUPROF:|"+sNUPROF+"|");

					  	Provision provision = CLProvisiones.buscarDetallesProvision(sNUPROF);

				    	logger.debug(provision.logProvision());
					  	
				    	this.sCOSPAT = provision.getsCOSPAT();
				    	this.sTAS = provision.getsTAS();
				    	
				    	this.sCOGRUG = provision.getsCOGRUG();
				    	this.sCOTPGA = provision.getsCOTPGA();

				    	this.sFEPFON = Utils.recuperaFecha(provision.getsFEPFON());
				    	this.sNumGastos = provision.getsNumGastos();
				    	this.sValorTotal = Utils.recuperaImporte(false,provision.getsValorTotal());
				    	this.sFechaEnvio = Utils.recuperaFecha(provision.getsFechaEnvio());

				    	this.sGastosAutorizados = provision.getsGastosAutorizados();
				    	this.sValorAutorizado = Utils.recuperaImporte(false,provision.getsValorAutorizado());
				    	this.sFechaAutorizado = Utils.recuperaFecha(provision.getsFechaAutorizado());

				    	this.sGastosPagados = provision.getsGastosPagados();
				    	this.sValorPagado = Utils.recuperaImporte(false,provision.getsValorPagado());
				    	this.sFechaPagado = Utils.recuperaFecha(provision.getsFechaPagado());

				    	this.sCodEstado = provision.getsCodEstado();
				    	
				    	logger.debug("sGastosAutorizados:|"+sGastosAutorizados+"|");
				    	logger.debug("sGastosPagados:|"+sGastosPagados+"|");
				    				    	
				    	this.sGastosPorPagar = Long.toString(Long.parseLong(sGastosAutorizados) - Long.parseLong(sGastosPagados));
				    	logger.debug("sGastosPorPagar:|"+sGastosPorPagar+"|");
				    	
				    	logger.debug("sValorAutorizado:|"+sValorAutorizado+"|");
				    	logger.debug("sValorPagado:|"+sValorPagado+"|");
				    	
				    	this.sValorPorPagar = Utils.recuperaImporte(false,Long.toString(Long.parseLong(provision.getsValorAutorizado()) - Long.parseLong(provision.getsValorPagado())));
				    	
				    	logger.debug("sValorPorPagar:|"+sValorPorPagar+"|");
				    	
				    	this.sFechaLimite = CLProvisiones.buscarPrimeraFechaLimitePago(sNUPROF);
				    	
						borrarCamposComunidad();
						borrarResultadosBuscarComunidad();
						borrarCamposPago();
						borrarResultadosBuscarCuenta();
						
						sMsg = "Provisión '"+sNUPROF+"' cargada.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					
				}
				else
				{
					sMsg = "La Provisión '"+sNUPROFB+"' no se encuentra regristada en el sistema. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);

			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void actualizaProgreso ()
	{
		Provision provision = CLProvisiones.buscarDetallesProvision(sNUPROF);
		
    	this.sGastosPagados = provision.getsGastosPagados();
    	this.sValorPagado = Utils.recuperaImporte(false,provision.getsValorPagado());
    	this.sFechaPagado = Utils.recuperaFecha(provision.getsFechaPagado());

    	this.sGastosPorPagar = Long.toString(Long.parseLong(sGastosAutorizados) - Long.parseLong(sGastosPagados));
    	this.sValorPorPagar = Utils.recuperaImporte(false,Long.toString(Long.parseLong(provision.getsValorAutorizado()) - Long.parseLong(provision.getsValorPagado())));
    	this.sFechaLimite = CLProvisiones.buscarPrimeraFechaLimitePago(sNUPROF);
	}
	
	public void buscarComunidades (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = ""; 
			
			this.comunidadseleccionada = null;
			
			try
			{
				if (sNUPROF.isEmpty())
				{
					sMsg = "ERROR: La Provisión de fondos debe de ser informada para realizar la búsqueda. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
					
					this.setTablacomunidades(null);
				}
				else if (CLProvisiones.existeProvision(sNUPROF))
				{
					this.setTablacomunidades(CLComunidades.buscarComunidadesPagablesDeProvision (sNUPROF));

					
					if (getTablacomunidades().size() == 0)
					{
						sMsg = "No se encontraron Comunidades con los criterios solicitados.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
					}
					else if (getTablacomunidades().size() == 1)
					{
						sMsg = "Encontrada una Comunidad relacionada.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					else
					{
						sMsg = "Encontradas "+getTablacomunidades().size()+" Comunidades relacionadas.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
					
				}
				else
				{
					sMsg = "La Provisión informada no está registrada. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
					
					this.setTablacomunidades(null);
				}
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: La Provisión debe ser numérica. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				this.setTablacomunidades(null);
			}
			
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}
	
	public void cargarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.iCodComunidad  = Long.parseLong(comunidadseleccionada.getsComunidadID());
			
			this.sCOCLDO = comunidadseleccionada.getCOCLDO();
			this.sNUDCOM = comunidadseleccionada.getNUDCOM();
			this.sNOMCOC = comunidadseleccionada.getNOMCOC();
			
			borrarCamposPago();
			borrarResultadosBuscarCuenta();
			
	    	this.sFechaUltimoDevengo = CLProvisiones.buscarUltimaFechaDevengoComunidad(sNUPROF);
	    	
	    	logger.debug("sFechaUltimoDevengo:|"+sFechaUltimoDevengo+"|");
		
			sMsg = "La Comunidad '"+sNUDCOM+"' se ha cargado correctamente.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}
	
	public void hoyFEPGPR (ActionEvent actionEvent)
	{
		this.setsFEPGPR(Utils.fechaDeHoy(true));
		logger.debug("sFEPGPR:|"+sFEPGPR+"|");
	}
	
	public void cambiaRecargo()
	{
		this.bRecargo = (sTipoRecargo ==null || sTipoRecargo.isEmpty());
	}
	
	public void buscaCuentas (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.cuentacomunidadseleccionada = null;
			
			if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
			{
				sMsg = "Faltan datos de la Comunidad por informar.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				
				this.setTablacuentascomunidad(null);
			}
			else 
			{
				this.setTablacuentascomunidad(CLCuentas.buscarCuentasComunidad(iCodComunidad));

				sMsg = "Encontradas "+getTablacuentascomunidad().size()+" cuentas de la Comunidad.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void seleccionarPagoPorVentanilla(ActionEvent actionEvent)
	{
		this.sPais = "ES";
		this.sDCIBAN = "00";
		this.sNUCCEN = "0000";
		this.sNUCCOF = "0000";
		this.sNUCCDI = "00";
		this.sNUCCNT = "0000000000";
		
		this.setsDescripcion("POR VENTANILLA");
	}
	
	public void seleccionarCuentaPago(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
		
			String sMsg = "";
			
			if (cuentacomunidadseleccionada == null)
			{
				logger.debug("NULACO!!");
			}
			
	    	logger.debug("getsPais:|"+cuentacomunidadseleccionada.getsPais()+"|");
	    	logger.debug("getsDCIBAN:|"+cuentacomunidadseleccionada.getsDCIBAN()+"|");
	    	logger.debug("getsNUCCEN:|"+cuentacomunidadseleccionada.getsNUCCEN()+"|");
	    	logger.debug("getsNUCCOF:|"+cuentacomunidadseleccionada.getsNUCCOF()+"|");
	    	logger.debug("getsNUCCDI:|"+cuentacomunidadseleccionada.getsNUCCDI()+"|");
	    	logger.debug("getsNUCCNT:|"+cuentacomunidadseleccionada.getsNUCCNT()+"|");
			
			this.sPais = cuentacomunidadseleccionada.getsPais();
			this.sDCIBAN = cuentacomunidadseleccionada.getsDCIBAN();
			this.sNUCCEN = cuentacomunidadseleccionada.getsNUCCEN();
			this.sNUCCOF = cuentacomunidadseleccionada.getsNUCCOF();
			this.sNUCCDI = cuentacomunidadseleccionada.getsNUCCDI();
			this.sNUCCNT = cuentacomunidadseleccionada.getsNUCCNT();
			
			this.setsDescripcion(cuentacomunidadseleccionada.getsDescripcion());
			
			sMsg = "Cuenta cargada.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
	

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}				
	}
	
	public void registrarPago()
	{
		
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{	
				if (sFEPGPR.isEmpty())
				{
					sMsg = "ERROR: La fecha de pago no se ha informado. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sNUCCEN.isEmpty() ||
						sNUCCOF.isEmpty() ||
						sNUCCDI.isEmpty() ||
						sNUCCNT.isEmpty())
				{
					sMsg = "ERROR: Faltan campos en la cuenta de Pago por informar. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (!Utils.compruebaCC(sNUCCEN, sNUCCOF, sNUCCDI, sNUCCNT))
				{
					sMsg = "ERROR:002 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sNUPROF.isEmpty())
				{
					sMsg = "ERROR: En número de Provisión de fondos debe de ser informado para realizar el Pago. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (!CLProvisiones.existeProvision(sNUPROF))
				{
					sMsg = "ERROR: la Provisión informada no está registrada. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (sCOCLDO.isEmpty() || sNUDCOM.isEmpty())
				{
					sMsg = "ERROR: Los campos 'Documento' y 'Número' deben de ser informados para realizar el Pago. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else if (iCodComunidad == 0)
				{
					sMsg = "ERROR: la Comunidad informada no está registrada. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				/*else if (Long.parseLong(Utils.compruebaFecha(sFechaUltimoDevengo)) > Long.parseLong(Utils.compruebaFecha(sFEPGPR)))
				{
					sMsg = "ERROR: La fecha de Pago no puede ser inferior a la de Devengo. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}*/
				else if (!sTipoRecargo.isEmpty() && sValorRecargo.equals("0") )
				{
					sMsg = "Elija 'Sin recargo' si el pago no lleva recargo. Por favor, revise los datos.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else
				{
					if (sNUCCEN.equals("0000") &&
					sNUCCOF.equals("0000") &&
					sNUCCDI.equals("00") &&
					sNUCCNT.equals("0000000000"))
					{
						this.sTipoPago= ValoresDefecto.DEF_PAGO_VENTANILLA; 
					}
					else
					{
						this.sTipoPago= ValoresDefecto.DEF_PAGO_NORMA34;
					}
					
					Cuenta cuenta = new Cuenta (sPais,sDCIBAN,sNUCCEN,sNUCCOF,sNUCCDI,sNUCCNT,"");
					
					if (sTipoRecargo.isEmpty())
					{
						sValorRecargo = "0";
					}
					
					Long.parseLong(Utils.compruebaImporte(sValorRecargo));
					
					RecargoImporte recargo = new RecargoImporte(sTipoRecargo,Utils.compruebaImporte(sValorRecargo));
					
					Nota nota = new Nota (false,sNota);
					
					int iSalida = CLPagos.registraPagoComunidad(
							iCodComunidad,
							sNUPROF,
							sTipoPago,
							Utils.compruebaFecha(sFEPGPR),
							cuenta,
							recargo,
							true,
							nota);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						actualizaProgreso();
				    	borrarCamposPago();

				    	sMsg = "El pago se ha registrado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //ERROR 001 - El gasto no esta autorizado.
						sMsg = "ERROR:001 - El gasto no esta autorizado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -2: //Error 002 - Datos de cuenta incorrectos
						sMsg = "ERROR:002 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -3: //Error 003 - La fecha de pago no es correcta
						sMsg = "ERROR:004 - La fecha de pago no es correcta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -4: //Error 004 - Fecha de devengo anterior a la fecha de pago.
						sMsg = "ERROR:004 - La fecha de pago no puede ser inferior a la de  devengo. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -900: //Error 900 - al registrar el pago
						sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el pago. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al resolver la relación con el gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al revisar el gasto
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al resolver la relación gasto-provision. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -905: //Error 905 - error y rollback - error al modificar el gasto
						sMsg = "[FATAL] ERROR:905 - Se ha producido un error al establecer el nuevo estado del gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -906: //Error 906 - error y rollback - error al crear la transferencia
						sMsg = "[FATAL] ERROR:906 - Se ha producido un error al registrar la transferencia del pago. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -907: //Error 907 - error y rollback - error al desbloquear la provision del gasto
						sMsg = "[FATAL] ERROR:907 - Se ha producido un error al desbloquear la provision del gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -908: //Error 908 - error y rollback - error al desbloquear el gasto
						sMsg = "[FATAL] ERROR:908 - Se ha producido un error al desbloquear el gasto. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					default: //error generico
						sMsg = "[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
					}

					logger.debug("Finalizadas las comprobaciones.");
				}
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El valor debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsCOSPAT() {
		return sCOSPAT;
	}

	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}

	public String getsTAS() {
		return sTAS;
	}

	public void setsTAS(String sTAS) {
		this.sTAS = sTAS;
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

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}

	public String getsValorTotal() {
		return sValorTotal;
	}

	public void setsValorTotal(String sValorTotal) {
		this.sValorTotal = sValorTotal;
	}

	public String getsFechaEnvio() {
		return sFechaEnvio;
	}

	public void setsFechaEnvio(String sFechaEnvio) {
		this.sFechaEnvio = sFechaEnvio;
	}

	public String getsGastosAutorizados() {
		return sGastosAutorizados;
	}

	public void setsGastosAutorizados(String sGastosAutorizados) {
		this.sGastosAutorizados = sGastosAutorizados;
	}

	public String getsValorAutorizado() {
		return sValorAutorizado;
	}

	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}

	public String getsFechaAutorizado() {
		return sFechaAutorizado;
	}

	public void setsFechaAutorizado(String sFechaAutorizado) {
		this.sFechaAutorizado = sFechaAutorizado;
	}

	public String getsGastosPagados() {
		return sGastosPagados;
	}

	public void setsGastosPagados(String sGastosPagados) {
		this.sGastosPagados = sGastosPagados;
	}

	public String getsValorPagado() {
		return sValorPagado;
	}

	public void setsValorPagado(String sValorPagado) {
		this.sValorPagado = sValorPagado;
	}

	public String getsFechaPagado() {
		return sFechaPagado;
	}

	public void setsFechaPagado(String sFechaPagado) {
		this.sFechaPagado = sFechaPagado;
	}

	public String getsCodEstado() {
		return sCodEstado;
	}

	public void setsCodEstado(String sCodEstado) {
		this.sCodEstado = sCodEstado;
	}

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsFEPFONB() {
		return sFEPFONB;
	}

	public void setsFEPFONB(String sFEPFONB) {
		this.sFEPFONB = sFEPFONB;
	}

	public String getsNUPROFB() {
		return sNUPROFB;
	}

	public void setsNUPROFB(String sNUPROFB) {
		this.sNUPROFB = sNUPROFB.trim();
	}

	public String getsCOACESB() {
		return sCOACESB;
	}

	public void setsCOACESB(String sCOACESB) {
		this.sCOACESB = sCOACESB;
	}

	public String getsFEPGPR() {
		return sFEPGPR;
	}

	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
	}

	public String getsFechaUltimoDevengo() {
		return sFechaUltimoDevengo;
	}

	public void setsFechaUltimoDevengo(String sFechaUltimoDevengo) {
		this.sFechaUltimoDevengo = sFechaUltimoDevengo;
	}
	
	public String getsTipoRecargo() {
		return sTipoRecargo;
	}

	public void setsTipoRecargo(String sTipoRecargo) {
		this.sTipoRecargo = sTipoRecargo;
	}

	public String getsValorRecargo() {
		return sValorRecargo;
	}

	public void setsValorRecargo(String sValorRecargo) {
		this.sValorRecargo = sValorRecargo;
	}

	public boolean isbRecargo() {
		return bRecargo;
	}

	public void setbRecargo(boolean bRecargo) {
		this.bRecargo = bRecargo;
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

	public Map<String, String> getTiposrecargoHM() {
		return tiposrecargoHM;
	}

	public void setTiposrecargoHM(Map<String, String> tiposrecargoHM) {
		this.tiposrecargoHM = tiposrecargoHM;
	}

	public ProvisionTabla getProvisionseleccionada() {
		return provisionseleccionada;
	}

	public void setProvisionseleccionada(ProvisionTabla provisionseleccionada) {
		this.provisionseleccionada = provisionseleccionada;
	}

	public ArrayList<ProvisionTabla> getTablaprovisiones() {
		return tablaprovisiones;
	}

	public void setTablaprovisiones(ArrayList<ProvisionTabla> tablaprovisiones) {
		this.tablaprovisiones = tablaprovisiones;
	}

	public ComunidadTabla getComunidadseleccionada() {
		return comunidadseleccionada;
	}

	public void setComunidadseleccionada(ComunidadTabla comunidadseleccionada) {
		this.comunidadseleccionada = comunidadseleccionada;
	}

	public ArrayList<ComunidadTabla> getTablacomunidades() {
		return tablacomunidades;
	}

	public void setTablacomunidades(ArrayList<ComunidadTabla> tablacomunidades) {
		this.tablacomunidades = tablacomunidades;
	}

	public Cuenta getCuentacomunidadseleccionada() {
		return cuentacomunidadseleccionada;
	}

	public void setCuentacomunidadseleccionada(Cuenta cuentacomunidadseleccionada) {
		this.cuentacomunidadseleccionada = cuentacomunidadseleccionada;
	}

	public ArrayList<Cuenta> getTablacuentascomunidad() {
		return tablacuentascomunidad;
	}

	public void setTablacuentascomunidad(ArrayList<Cuenta> tablacuentascomunidad) {
		this.tablacuentascomunidad = tablacuentascomunidad;
	}

	public String getsFechaLimite() {
		return sFechaLimite;
	}

	public void setsFechaLimite(String sFechaLimite) {
		this.sFechaLimite = sFechaLimite;
	}

	public String getsGastosPorPagar() {
		return sGastosPorPagar;
	}

	public void setsGastosPorPagar(String sGastosPorPagar) {
		this.sGastosPorPagar = sGastosPorPagar;
	}

	public String getsValorPorPagar() {
		return sValorPorPagar;
	}

	public void setsValorPorPagar(String sValorPorPagar) {
		this.sValorPorPagar = sValorPorPagar;
	}
	
}
