package com.provisiones.pl.movimientos;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLComunidades;
import com.provisiones.ll.CLCuentas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.Cuenta;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoComunidad;
import com.provisiones.types.tablas.ActivoTabla;

public class GestorMovimientosComunidades implements Serializable 
{
	private static final long serialVersionUID = -9157997142376942992L;

	private static Logger logger = LoggerFactory.getLogger(GestorMovimientosComunidades.class.getName());
	
	private String sCODTRN = ValoresDefecto.DEF_E1_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;
	private String sCOCLDO = "";
	private String sNUDCOM = "";
	private String sCOACES = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	private String sNOMPRC = "";
	private String sNUTPRC = "";
	private String sNOMADC = "";
	private String sNUTADC = "";
	private String sNODCAD = "";
	private String sNUCCEN = "";
	private String sNUCCOF = "";
	private String sNUCCDI = "";
	private String sNUCCNT = "";

	private String sOBTEXC = "";
	private String sOBDEER = "";
	
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";

	private String sNota = "";
	
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ActivoTabla> tablaactivos = null;
	
	public GestorMovimientosComunidades()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorMovimientosComunidades...");	
		}
	}
	public void borrarCamposActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
		
	}
	
	public void borrarResultadosActivo()
	{
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";

    	borrarCamposActivo();
    	
    	borrarResultadosActivo();
    }

	public void borrarCamposComunidad()
	{
		this.sCOCLDO = "";
		this.sNUDCOM = "";
		this.sNOMCOC = "";
		this.sNODCCO = "";
		this.sNOMPRC = "";
		this.sNUTPRC = "";
		this.sNOMADC = "";
		this.sNUTADC = "";
		this.sNODCAD = "";
		this.sNUCCEN = "";
		this.sNUCCOF = "";
		this.sNUCCDI = "";
		this.sNUCCNT = "";
		this.sOBTEXC = "";
	}
	
	public void limpiarPlantilla(ActionEvent actionEvent)
	{
		this.sCOACES = "";
		this.sCOACCI = "";
		
		borrarResultadosActivo();
		borrarCamposComunidad();
	}
	
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";

    }

    public void guardarNota(ActionEvent actionEvent) 
    {  
		this.sCOACES = "";

    }
	
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla filtro = new ActivoTabla(
					sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLComunidades.buscarActivosConComunidad(filtro));

			String sMsg = "Encontrados "+getTablaactivos().size()+" activos relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void seleccionarActivo(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	String sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
	    	
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void comprobarActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				int iSalida = CLComunidades.comprobarActivo(Integer.parseInt(sCOACES));

				switch (iSalida) 
				{
					case 0: //Sin errores
						sMsg = "El activo '"+sCOACES+"' esta disponible.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //error - ya vinculado
						sMsg = "ERROR: El activo '"+sCOACES+"' ya esta vinculado a otra comunidada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -2: //error - no existe
						sMsg = "ERROR: El activo '"+sCOACES+"' no se encuentra registrado en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					default: //error generico
						sMsg = "[FATAL] ERROR: El activo '"+sCOACES+"' ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
				}
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
	
	public void buscarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			borrarCamposComunidad();
			
			try
			{
				Comunidad comunidad = CLComunidades.buscarComunidad(Integer.parseInt(sCOACES));
				
				if (comunidad.getsNUDCOM().equals(""))
				{
					sMsg = "ERROR: El Activo '"+sCOACES+"' no esta asociado a ninguna comunidad.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
					
					this.sCOCLDO = comunidad.getsCOCLDO();
					this.sNUDCOM = comunidad.getsNUDCOM();
					this.sNOMCOC = comunidad.getsNOMCOC();
					this.sNODCCO = comunidad.getsNODCCO();
					this.sNOMPRC = comunidad.getsNOMPRC();
					this.sNUTPRC = comunidad.getsNUTPRC();
					this.sNOMADC = comunidad.getsNOMADC();
					this.sNUTADC = comunidad.getsNUTADC();
					this.sNODCAD = comunidad.getsNODCAD();
					this.sNUCCEN = cuenta.getsNUCCEN();
					this.sNUCCOF = cuenta.getsNUCCOF();
					this.sNUCCDI = cuenta.getsNUCCDI();
					this.sNUCCNT = cuenta.getsNUCCNT();
					this.sOBTEXC = comunidad.getsOBTEXC();
					
					this.sNota = CLComunidades.buscarNota(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM));
					
					sMsg = "La comunidad se ha cargado correctamente.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
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
	

	public void cargarComunidad(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			Comunidad comunidad = CLComunidades.consultarComunidad(sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase());
			
			String sMsg = "";

			if (comunidad.getsNUDCOM().equals(""))
			{
				sMsg = "ERROR: La comunidad '"+sNUDCOM.toUpperCase()+"' no esta registrada en el sistema.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				Cuenta cuenta = CLCuentas.buscarCuenta(Long.parseLong(comunidad.getsCuenta()));
				
				this.sCOCLDO = comunidad.getsCOCLDO();
				this.sNUDCOM = comunidad.getsNUDCOM();
				this.sNOMCOC = comunidad.getsNOMCOC();
				this.sNODCCO = comunidad.getsNODCCO();
				this.sNOMPRC = comunidad.getsNOMPRC();
				this.sNUTPRC = comunidad.getsNUTPRC();
				this.sNOMADC = comunidad.getsNOMADC();
				this.sNUTADC = comunidad.getsNUTADC();
				this.sNODCAD = comunidad.getsNODCAD();
				this.sNUCCEN = cuenta.getsNUCCEN();
				this.sNUCCOF = cuenta.getsNUCCOF();
				this.sNUCCDI = cuenta.getsNUCCDI();
				this.sNUCCNT = cuenta.getsNUCCNT();
				this.sOBTEXC = comunidad.getsOBTEXC();
				
				this.sNota = CLComunidades.buscarNota(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM));
				
				msg = Utils.pfmsgInfo("La comunidad '"+sNUDCOM.toUpperCase()+"' se ha cargado correctamente.");
				logger.info("La comunidad '{}' se ha cargado correctamente.",sNUDCOM.toUpperCase());
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}			
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				//Integer.parseInt(sCOACES);
				
				if (!CLComunidades.existeComunidad(sCOCLDO, sNUDCOM.toUpperCase()))
				{
					sMsg = "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{
					MovimientoComunidad movimiento = new MovimientoComunidad (
							sCODTRN.toUpperCase(), 
							sCOTDOR.toUpperCase(), 
							sIDPROV.toUpperCase(), 
							sCOACCI.toUpperCase(), 
							sCOENGP.toUpperCase(), 
							sCOCLDO.toUpperCase(), 
							sNUDCOM.toUpperCase(), 
							"",
							"", 
							"", 
							sNOMCOC.toUpperCase(), 
							"", 
							sNODCCO.toUpperCase(), 
							"", 
							sNOMPRC.toUpperCase(), 
							"", 
							sNUTPRC.toUpperCase(), 
							"", 
							sNOMADC.toUpperCase(), 
							"", 
							sNUTADC.toUpperCase(), 
							"", 
							sNODCAD.toUpperCase(), 
							"", 
							sNUCCEN.toUpperCase(), 
							sNUCCOF.toUpperCase(), 
							sNUCCDI.toUpperCase(), 
							sNUCCNT.toUpperCase(), 
							"", 
							sOBTEXC.toUpperCase(), 
							sOBDEER.toUpperCase());
					
					String sNotaAntigua = CLComunidades.buscarNota(CLComunidades.buscarCodigoComunidad(sCOCLDO, sNUDCOM));
					
					Nota nota = new Nota (sNotaAntigua.equals(sNota),sNota);
					
					if (nota.isbInvalida())
					{
						nota.setsContenido("");
					}
					
					int iSalida = CLComunidades.registraMovimiento(movimiento,nota);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						sMsg = "El movimiento se ha registrado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //error 001 - CODIGO DE ACCION DEBE SER A,M,B,X 
						sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -3: //error 003 - NO EXISTE EL ACTIVO
						sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O CEROS
						sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -5: //Error 005 - NO TIENE NOMBRE LA COMUNIDAD
						sMsg = "ERROR:005 - El nombre de la comunidad es obligatorio. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -6: //Error 006 - FALTAN DATOS DE LA CUENTA BANCARIA
						sMsg = "ERROR:006 - No se han informado los datos de la cuenta corriente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -8: //Error 008 - EL ACTIVO EXISTE EN OTRA COMUNIDAD
						sMsg = "ERROR:008 - El activo ya esta asociado a otra comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;


					case -9: //error 009 - YA EXISTE ESTA COMUNIDAD
						sMsg = "ERROR:009 - La comunidad ya se encuentra registrada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -10: //error 010 - EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD
						sMsg = "ERROR:010 - El activo ya esta asociado a esta comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -11: //error 011 - LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA
						sMsg = "ERROR:011 - No se puede dar de alta el activo, la comunidad no existe. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;


					/*case -12: //error 012 - LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR
						sMsg = "ERROR:012 - No se puede modificar la comunidad, no se encuentra registrada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;*/

					case -22: //error 022 - NO SE PUEDE DAR ALTA SI CONTROL DE ACTIVO NO ES S
						sMsg = "ERROR:022 - Para dar de alta la comunidad es necesario incluir un activo. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -26: //error 026 - LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA
						sMsg = "ERROR:026 - No se puede dar de baja la comunidad, no se encuentra registrada. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -27: //error 027 - NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS
						sMsg = "ERROR:027 - No se puede dar de baja la comunidad, aun tiene cuotas de alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -30: //Error 030 - LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)
						sMsg = "ERROR:030 - No se ha elegido un tipo de documento. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -31: //Error 031 - NUMERO DE DOCUMENTO CIF ERRONEO
						sMsg = "ERROR:031 - El numero de documento es incorrecto. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -701: //Error 701 - datos de cuenta incorrectos
						sMsg = "ERROR:701 - Los datos de la cuenta corriente son incorrectos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -702: //Error 702 - direccion de correo de comunidad incorrecta
						sMsg = "ERROR:702 - La direccion de correo de la comunidad es incorrecta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -703: //Error 703 - direccion de correo del administrador incorrecta
						sMsg = "ERROR:703 - La direccion de correo del adminsitrador es incorrecta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -801: //Error 801 - alta de una comunidad en alta
						sMsg = "ERROR:801 - La comunidad ya esta dada de alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -802: //Error 802 - comunidad de baja no puede recibir mas movimientos
						sMsg = "ERROR:802 - La comunidad esta de baja y no puede recibir mas movimientos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -803: //Error 803 - estado no disponible
						sMsg = "ERROR:803 - El estado de la comunidad informada no esta disponible. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -804: //Error 804 - modificacion sin cambios
						sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -900: //Error 900 - al crear un movimiento
						sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -901: //Error 901 - error y rollback - error al crear la comuidad
						sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -902: //Error 902 - error y rollback - error al registrar la relaccion
						sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al registrar el activo durante el alta
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al asociar el activo durante el alta. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al cambiar el estado de la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -905: //Error 905 - error y rollback - error al modificar la comunidad
						sMsg = "[FATAL] ERROR:905 - Se ha producido un error al modificar la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -906: //Error 906 - error y rollback - el activo ya esta vinculado
						sMsg = "[FATAL] ERROR:906 - El activo ya ha sido vinculado. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -907: //Error 907 - error y rollback - error al asociar el activo en la comunidad
						sMsg = "[FATAL] ERROR:907 - Se ha producido un error al asociar el activo a la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -908: //Error 908 - error y rollback - el activo no esta vinculado
						sMsg = "[FATAL] ERROR:908 - El activo no ha sido vinculado. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -909: //Error 909 - error y rollback - error al desasociar el activo en la comunidad
						sMsg = "[FATAL] ERROR:909 - Se ha producido un error al desasociar el activo a la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -911: //Error 911 - error y rollback - error al crear la cuenta de la comunidad
						sMsg = "[FATAL] ERROR:911 - Se ha producido un error al crear la cuenta de la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -912: //Error 912 - error y rollback - error al crear la cuenta de la comunidad
						sMsg = "[FATAL] ERROR:912 - Se ha producido un error al crear la relacion cuenta-comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -913: //Error 913 - error y rollback - error al borrar la cuenta de la comunidad
						sMsg = "[FATAL] ERROR:913 - Se ha producido un error al borrar la cuenta de la comunidad. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -914: //Error 914 - error y rollback - error la cuenta nueva ya existe
						sMsg = "[FATAL] ERROR:914 - Se ha producido un error al crear la cuenta de la comunidad, ya existe. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -915: //Error 915 - error y rollback - error al guardar la nota
						sMsg = "[FATAL] ERROR:915 - Se ha producido un error al guardar la nota de la comunidad. Por favor, revise los datos y avise a soporte.";
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
			
			borrarResultadosActivo();
		}
	}

	public String getsCODTRN() {
		return sCODTRN;
	}

	public void setsCODTRN(String sCODTRN) {
		this.sCODTRN = sCODTRN;
	}

	public String getsCOTDOR() {
		return sCOTDOR;
	}

	public void setsCOTDOR(String sCOTDOR) {
		this.sCOTDOR = sCOTDOR;
	}

	public String getsIDPROV() {
		return sIDPROV;
	}

	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
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

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsNOMPRC() {
		return sNOMPRC;
	}

	public void setsNOMPRC(String sNOMPRC) {
		this.sNOMPRC = sNOMPRC;
	}

	public String getsNUTPRC() {
		return sNUTPRC;
	}

	public void setsNUTPRC(String sNUTPRC) {
		this.sNUTPRC = sNUTPRC;
	}

	public String getsNOMADC() {
		return sNOMADC;
	}

	public void setsNOMADC(String sNOMADC) {
		this.sNOMADC = sNOMADC;
	}

	public String getsNUTADC() {
		return sNUTADC;
	}

	public void setsNUTADC(String sNUTADC) {
		this.sNUTADC = sNUTADC;
	}

	public String getsNODCAD() {
		return sNODCAD;
	}

	public void setsNODCAD(String sNODCAD) {
		this.sNODCAD = sNODCAD;
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

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
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

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}
	public String getsNota() {
		return sNota;
	}
	public void setsNota(String sNota) {
		this.sNota = sNota;
	}

}
