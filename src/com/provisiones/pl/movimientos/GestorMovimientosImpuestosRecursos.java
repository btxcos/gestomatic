package com.provisiones.pl.movimientos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoImpuestoRecurso;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ImpuestoRecursoTabla;

public class GestorMovimientosImpuestosRecursos implements Serializable 
{
	private static final long serialVersionUID = -6724439464144511204L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorMovimientosImpuestosRecursos.class.getName());

	//Accion
	private String sCOACCI = "";
	
	//Buscar activos
	private String sCOACES = "";

	//Filtro activo
	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sNURCATF = "";
	
	//Referecia Catastral
	private String sNURCAT = "";

	//Solicitud
	private String sDesCOSBAC = "";
	private String sCOSBAC = "";
	private String sFEPRRE = "";
	
	//Resolucion
	private String sDesBIRESO = "";
	private String sBIRESO = "";
	private String sFERERE = "";
	
	//Devolucion
	private String sDesBISODE = "";
	private String sBISODE = "";
	private String sFEDEIN = "";
	
	//Observaciones
	private String sOBTEXC = "";

	//Notas
	private String sNota = "";
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ImpuestoRecursoTabla> tablaimpuestos = null;
	private transient ImpuestoRecursoTabla impuestoseleccionado = null;
	

	public GestorMovimientosImpuestosRecursos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorMovimientosImpuestosRecursos...");	
		}
	}
	
	public void borrarPlantillaActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	this.sNUFIRE = "";
    	
    	this.sNURCATF = "";
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaActivo();
    	
    	borrarResultadosActivo();
   	
    }
    
	public void borrarPlantillaImpuesto()
	{
		
        this.sNURCAT = "";
        this.sCOSBAC = "";
        this.sFEPRRE = "";
        this.sFERERE = "";
        this.sFEDEIN = "";
        this.sBISODE = "";
        this.sBIRESO = "";
        this.sOBTEXC = "";
        
        this.sDesCOSBAC = "";
        this.sDesBISODE = "";
        this.sDesBIRESO = "";
	}
	
	public void borrarResultadosImpuesto()
	{
    	this.impuestoseleccionado = null;
    	this.tablaimpuestos = null;
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarPlantillaImpuesto();
    	
    	borrarResultadosActivo();
    	borrarResultadosImpuesto();
   	
    }
    
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			this.setTablaactivos(null);
			
			if (sNURCATF.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOIN.toUpperCase(), 
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(), 
						sNOVIAS.toUpperCase(), 
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(), 
						sNUPUAC.toUpperCase(), 
						sNUFIRE.toUpperCase(),
						"");
				
				this.setTablaactivos(CLImpuestos.buscarActivosConImpuestos(filtro));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (getTablaactivos().size() == 1)
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				else
				{
					sMsg = "Encontrados "+getTablaactivos().size()+" Activos relacionados.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}
			else if (CLReferencias.existeReferenciaCatastral(sNURCATF))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConRecursos(sNURCATF));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}
			else
			{
				
				sMsg = "La Referencia Catastral informada no se encuentrar registrada en el sistema. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";

	    	this.sCOACES  = activoseleccionado.getCOACES();

	    	this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(Integer.parseInt(sCOACES));
	    	
	    	if (sNURCAT.equals("") || !CLReferencias.estadoReferencia(sNURCAT).equals("A"))
	    	{
	    		sMsg = "La referencia catastral seleccionada no esta de alta.";
	    		msg = Utils.pfmsgError(sMsg);
	    		logger.error(sMsg);

	    		this.sNURCAT  = "";
	    	}
	    	else
	    	{
	    		sMsg = "Referencia "+ sNURCAT +" cargada.";
	    		msg = Utils.pfmsgInfo(sMsg);
	    		logger.info(sMsg);
	    	}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
    
	public void cargarImpuestos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.impuestoseleccionado = null;
			
			this.tablaimpuestos = null;
			
	    	if (sCOACES.isEmpty())
	    	{
				sMsg = "ERROR: El Activo debe de ir informado. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
	    	}
	    	else
	    	{
				try
				{
					this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(Integer.parseInt(sCOACES));
			    	

			    	if (!sNURCAT.equals("") && CLReferencias.estadoReferencia(sNURCAT).equals("A") )
					{
			    		this.tablaimpuestos = CLImpuestos.buscarImpuestosActivos(Integer.parseInt(sCOACES));
			    		
			    		sMsg = "Encontrados "+getTablaimpuestos().size()+" impuestos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
			    	else
			    	{
			    		sMsg = "ERROR: No existe referencia catastral de alta para el activo consultado.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
			        }
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
	    	}
			
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarImpuesto(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
	    	FacesMessage msg;
	    	
	    	this.sCOSBAC = impuestoseleccionado.getCOSBAC();
	    	this.sDesCOSBAC = impuestoseleccionado.getDCOSBAC();
	    	this.sFEPRRE = impuestoseleccionado.getFEPRRE();
	    	this.sFERERE = impuestoseleccionado.getFERERE();
	    	this.sFEDEIN = impuestoseleccionado.getFEDEIN();
	    	this.sBISODE = impuestoseleccionado.getBISODE();
	    	this.sDesBISODE = impuestoseleccionado.getDBISODE();
	    	this.sBIRESO = impuestoseleccionado.getBIRESO();
	    	this.sDesBIRESO = impuestoseleccionado.getBIRESO();
	    	this.sOBTEXC = impuestoseleccionado.getOBTEXC();
	    	
	    	String sMsg = "Recurso de '"+sDesCOSBAC +"' Seleccionado.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);

			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void hoyFEPRRE (ActionEvent actionEvent)
	{
		this.setsFEPRRE(Utils.fechaDeHoy(true));
		logger.debug("sFEPRRE:|{}|",sFEPRRE);
	}
	
	public void hoyFERERE (ActionEvent actionEvent)
	{
		this.setsFERERE(Utils.fechaDeHoy(true));
		logger.debug("sFERERE:|{}|",sFERERE);
	}
	
	public void hoyFEDEIN (ActionEvent actionEvent)
	{
		this.setsFEDEIN(Utils.fechaDeHoy(true));
		logger.debug("sFEDEIN:|{}|",sFEDEIN);
	}
    
	public void registraDatos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				Integer.parseInt(sCOACES);
				
				if (!CLImpuestos.existeImpuestoRecurso(sNURCAT.toUpperCase(), sCOSBAC))
				{
					sMsg = "ERROR:066 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{

					
					MovimientoImpuestoRecurso movimiento = new MovimientoImpuestoRecurso (
							ValoresDefecto.DEF_E4_CODTRN, 
							ValoresDefecto.DEF_COTDOR, 
							ValoresDefecto.DEF_IDPROV, 
							sCOACCI.toUpperCase(), 
							ValoresDefecto.DEF_COENGP, 
							sCOACES,
							sNURCAT,
							ValoresDefecto.DEF_COGRUG_E4, 
							ValoresDefecto.DEF_COTACA_E4, 
							sCOSBAC,
							"", 
							Utils.compruebaFecha(sFEPRRE),
							"", 
							Utils.compruebaFecha(sFERERE),
							"", 
							Utils.compruebaFecha(sFEDEIN),
							"", 
							Utils.compruebaCodigoAlfa(sBISODE),
							"", 
							Utils.compruebaCodigoAlfa(sBIRESO),
							ValoresDefecto.DEF_COTEXA,
							"", 
							sOBTEXC, 
							ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR);
					
					String sNotaAntigua = CLImpuestos.buscarNota(CLImpuestos.buscarCodigoImpuesto(sNURCAT, sCOSBAC));
					
					Nota nota = new Nota (sNotaAntigua.equals(sNota),sNota);
					
					if (nota.isbInvalida())
					{
						nota.setsContenido("");
					}
					
					int iSalida = CLImpuestos.registraMovimiento(movimiento, nota);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						sMsg = "El movimiento se ha registrado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
						sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -3: //Error 003 - NO EXISTE EL ACTIVO
						sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -32: //Error 032 - EL SUBTIPO DE ACCION NO EXISTE
						sMsg = "ERROR:032 - No se a informado el concepto. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -54: //Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
						sMsg = "ERROR:054 - La referencia catastral es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -55: //Error 055 - LA FECHA PRESENTACION DE RECURSO DEBE SER LOGICA Y OBLIGATORIA
						sMsg = "ERROR:055 - La fecha de presentacion de recurso es obligatoria.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;			

					case -61: //Error 061 - NO SE PUEDE REALIZAR EL ALTA PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
						sMsg = "ERROR:061 - La referencia catastral proporcionada no se encuentra registrada.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -62: //Error 062 - INDICADOR SOLICITUD DEVOLUCION DEBE SER 'S' O 'N'
						sMsg = "ERROR:062 - El indicador de solicitud de devolucion es obligatorio.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -64: //Error 064 - NO SE PUEDE REALIZAR EL ALTA PORQUE YA EXISTE EL REGISTRO EN GMAE57
						sMsg = "ERROR:064 - El recurso o impuesto ya se encuentra registrado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					/*case -66: //Error 066 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE EL REGISTRO EN GMAE57
						sMsg = "ERROR:066 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;*/
						
					case -67: //Error 067 - NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13
						sMsg = "ERROR:067 - La referencia catrastral no se encuentra registrada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -68: //Error 068 - NO SE PUEDE ELIMINAR PORQUE NO EXISTE REGISTRO EN GMAE57
						sMsg = "ERROR:068 - El recurso o impuesto no se encuentra registrado. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -101: //Error 101 - TIENE F.PRESENTACION, TIPO RESOLUCION Y NO F.RESOLUCION
						sMsg = "ERROR:101 - El recurso o impuesto tiene informada la fecha de resolucion, el tipo de resolucion pero no la fecha de resolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -102: //Error 102 - TIENE F.PRESENTACION, F.RESOLUCION Y NO TIPO RESOLUCION
						sMsg = "ERROR:102 - El recurso o impuesto tiene informada la fecha de presentacion, la fecha de resolucion pero no el tipo de resolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -103: //Error 103 - NO TIENE F.PRESENTACION Y SI TIPO RESOLUCION
						sMsg = "ERROR:103 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el tipo de resolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -104: //Error 104 - NO TIENE F.PRESENTACION, TIPO RESOLUCION Y SI F.RESOLUCION
						sMsg = "ERROR:104 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el tipo de resolucion y fecha de resolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -105: //Error 105 - NO TIENE S.DEVOLUCION, Y SI F.DEVOLUCION
						sMsg = "ERROR:105 - El recurso o impuesto no tiene informado el indicador de devolucion pero si la fecha de devolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -106: //Error 106 - NO TIENE F.PRESENTACION, Y SI F.DEVOLUCION
						sMsg = "ERROR:106 - El recurso o impuesto no tiene informada la fecha de presentacion pero si la fecha de devolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -107: //Error 107 - NO TIENE TIPO RESOLUCION, Y SI F.DEVOLUCION
						sMsg = "ERROR:107 - El recurso o impuesto no tiene informado el tipo de resolucion pero si la fecha de devolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -108: //Error 108 - NO TIENE F.PRESENTACION, Y SI S.DEVOLUCION
						sMsg = "ERROR:108 - El recurso o impuesto no tiene informada la fecha de presentacion pero si el indicador de devolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -109: //Error 109 - EL TIPO RESOLUCION ES DESFAVORABLE Y TIENE F.DEVOLUCION
						sMsg = "ERROR:109 - El tipo de resolucion es 'DESFAVORABLE' y tiene informada la fecha de devolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -110: //Error 110 - LA F.RESOLUCION ES MENOR A LA F.PRESENTACION
						sMsg = "ERROR:110 - La fecha de resolucion es anterior a la fecha de presentacion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -111: //Error 111 - LA F.DEVOLUCION ES MENOR A LA F.PRESENTACION
						sMsg = "ERROR:111 - La fecha de devolucion es anterior a la fecha de presentacion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -112: //Error 112 - LA F.DEVOLUCION ES MENOR A LA F.RESOLUCION
						sMsg = "ERROR:112 - La fecha de devolucion es anterior a la fecha de resolucion. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -700: //Error 700 - no existe relacion con el activo
						sMsg = "ERROR:700 - El activo suministrado no esta relacionado con la referencia catastral informada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -801: //Error 801 - alta de un impuesto/recurso en alta
						sMsg = "ERROR:801 - El impuesto o recurso ya esta dada de alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -802: //Error 802 - impuesto/recurso de baja no puede recibir movimientos
						sMsg = "ERROR:802 - El impuesto o recurso esta baja y no puede recibir movimientos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -803: //Error 803 - estado no disponible
						sMsg = "ERROR:803 - El estado del impuesto o recurso informado no esta disponible. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -804: //Error 804 - modificacion sin cambios
						sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -805: //Error 805 - fecha de resolucion es invalida
						sMsg = "ERROR:805 - La fecha de resolucion es invalida. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -806: //Error 806 - fecha de devolucion es invalida
						sMsg = "ERROR:806 -  La fecha de devolucion es invalida. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -807: //Error 807 - fecha de devolucion es invalida
						sMsg = "ERROR:807 -  La fecha de presentacion es invalida. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -900: //Error 900 - al crear un movimiento
						sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -901: //Error 901 - error y rollback - error al crear el impuesto/recurso
						sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar el impuesto o recurso. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -902: //Error 902 - error y rollback - error al registrar la relaccion
						sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado del impuesto o recurso. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al modificar el impuesto/recurso
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar el impuesto o recurso. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -915: //Error 915 - error y rollback - error al guardar la nota
						sMsg = "[FATAL] ERROR:915 - Se ha producido un error al guardar la nota del impuesto o recurso. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					default: //error generico
						sMsg = "[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
					}
				}
				
				logger.debug("Finalizadas las comprobaciones.");
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El activo debe ser numérico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}			
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFEPRRE() {
		return sFEPRRE;
	}

	public void setsFEPRRE(String sFEPRRE) {
		this.sFEPRRE = sFEPRRE;
	}

	public String getsFERERE() {
		return sFERERE;
	}

	public void setsFERERE(String sFERERE) {
		this.sFERERE = sFERERE;
	}

	public String getsFEDEIN() {
		return sFEDEIN;
	}

	public void setsFEDEIN(String sFEDEIN) {
		this.sFEDEIN = sFEDEIN;
	}

	public String getsBISODE() {
		return sBISODE;
	}

	public void setsBISODE(String sBISODE) {
		this.sBISODE = sBISODE;
	}

	public String getsBIRESO() {
		return sBIRESO;
	}

	public void setsBIRESO(String sBIRESO) {
		this.sBIRESO = sBIRESO;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC.trim().toUpperCase();
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES.trim();
	}

	public String getsCOPOIN() {
		return sCOPOIN;
	}

	public void setsCOPOIN(String sCOPOIN) {
		this.sCOPOIN = sCOPOIN;
	}

	public String getsNOMUIN() {
		return sNOMUIN;
	}

	public void setsNOMUIN(String sNOMUIN) {
		this.sNOMUIN = sNOMUIN;
	}

	public String getsNOPRAC() {
		return sNOPRAC;
	}

	public void setsNOPRAC(String sNOPRAC) {
		this.sNOPRAC = sNOPRAC;
	}

	public String getsNOVIAS() {
		return sNOVIAS;
	}

	public void setsNOVIAS(String sNOVIAS) {
		this.sNOVIAS = sNOVIAS;
	}

	public String getsNUPIAC() {
		return sNUPIAC;
	}

	public void setsNUPIAC(String sNUPIAC) {
		this.sNUPIAC = sNUPIAC;
	}

	public String getsNUPOAC() {
		return sNUPOAC;
	}

	public void setsNUPOAC(String sNUPOAC) {
		this.sNUPOAC = sNUPOAC;
	}

	public String getsNUPUAC() {
		return sNUPUAC;
	}

	public void setsNUPUAC(String sNUPUAC) {
		this.sNUPUAC = sNUPUAC;
	}

	public String getsNUFIRE() {
		return sNUFIRE;
	}

	public void setsNUFIRE(String sNUFIRE) {
		this.sNUFIRE = sNUFIRE;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ImpuestoRecursoTabla getImpuestoseleccionado() {
		return impuestoseleccionado;
	}

	public void setImpuestoseleccionado(ImpuestoRecursoTabla impuestoseleccionado) {
		this.impuestoseleccionado = impuestoseleccionado;
	}

	public ArrayList<ImpuestoRecursoTabla> getTablaimpuestos() {
		return tablaimpuestos;
	}

	public void setTablaimpuestos(ArrayList<ImpuestoRecursoTabla> tablaimpuestos) {
		this.tablaimpuestos = tablaimpuestos;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesBISODE() {
		return sDesBISODE;
	}

	public void setsDesBISODE(String sDesBISODE) {
		this.sDesBISODE = sDesBISODE;
	}

	public String getsDesBIRESO() {
		return sDesBIRESO;
	}

	public void setsDesBIRESO(String sDesBIRESO) {
		this.sDesBIRESO = sDesBIRESO;
	}

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

	public String getsNURCATF() {
		return sNURCATF;
	}

	public void setsNURCATF(String sNURCATF) {
		this.sNURCATF = sNURCATF.trim().toUpperCase();
	}
	
	
	
	
	
}
