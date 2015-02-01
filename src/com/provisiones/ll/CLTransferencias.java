package com.provisiones.ll;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMTransferenciasN34;
import com.provisiones.dal.qm.QMTransferenciasN3414;
import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.misc.Longitudes;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Activo;
import com.provisiones.types.Cuenta;
import com.provisiones.types.Gasto;
import com.provisiones.types.transferencias.N34.OrdenanteN34;
import com.provisiones.types.transferencias.N34.ResumenN34;
import com.provisiones.types.transferencias.N34.TransferenciaN34;
import com.provisiones.types.transferencias.N3414.CabeceraTransferenciasN3414;
import com.provisiones.types.transferencias.N3414.OrdenanteN3414;
import com.provisiones.types.transferencias.N3414.ResumenN3414;
import com.provisiones.types.transferencias.N3414.TotalesN3414;
import com.provisiones.types.transferencias.N3414.TransferenciaN3414;

public class CLTransferencias 
{
	private static Logger logger = LoggerFactory.getLogger(CLTransferencias.class.getName());

	private CLTransferencias(){}
	
	public static OrdenanteN34 generarOrdenanteN34(Cuenta cuenta)
	{
		
		String sCodOrdenante = ValoresDefecto.DEF_CODIGO_ORDENANTE;
		String sFechaEnvio = Utils.aFechaN34(Utils.sumaDiasFecha(Utils.fechaDeHoy(false), -1));
		String sFechaEmision = Utils.fechaDeHoyN34(false);
		String sNUCCEN = cuenta.getsNUCCEN();
		String sNUCCOF = cuenta.getsNUCCOF();
		String sNUCCNT = cuenta.getsNUCCNT();	
		String sDetalleCargo = ValoresDefecto.DEF_ORDENANTE_DETALLE_CARGO;
		String sNUCCDI = cuenta.getsNUCCDI();
		String sNombre = ValoresDefecto.DEF_ORDENANTE;
		String sDomicilio = ValoresDefecto.DEF_ORDENANTE_DOMICILIO;
		String sPlaza = ValoresDefecto.DEF_ORDENANTE_PLAZA;
		

		return new OrdenanteN34 (
				sCodOrdenante,
				sFechaEnvio,
				sFechaEmision,
				sNUCCEN,
				sNUCCOF,
				sNUCCNT,
				sDetalleCargo,
				sNUCCDI,
				sNombre, 
				sDomicilio,
				sPlaza);
	}
	
	public static TransferenciaN34 generarTransferenciaN34(long liCodGasto, Cuenta cuenta, long liRecargo)
	{
		Connection conexion = ConnectionManager.getDBConnection();
		
		String sCodOrdenante = "";
		String sReferenciaBeneficiario = "";
		String sImporte = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sNombreBeneficiario = "";
		String sDomicilio1Beneficiario = "";
		String sDomicilio2Beneficiario = "";
		String sPlazaBeneficiario = "";
		String sProvinciaBeneficiario = "";
		String sConcepto1Transferencia = "";
		String sConcepto2Transferencia = "";
    	
		if (conexion != null)
		{
			sCodOrdenante = ValoresDefecto.DEF_CODIGO_ORDENANTE;
			Gasto gasto = QMGastos.getGasto(conexion, liCodGasto);
			gasto.setValor_total();
			sReferenciaBeneficiario = gasto.getCOACES();
			
			sImporte = Long.toString(gasto.getValor_total()+liRecargo);
			
			sNUCCEN = cuenta.getsNUCCEN();
			sNUCCOF = cuenta.getsNUCCOF();
			sNUCCDI = cuenta.getsNUCCDI();
			sNUCCNT = cuenta.getsNUCCNT();
			
			Activo activo = QMActivos.getActivo(conexion, Integer.parseInt(gasto.getCOACES()));
			
	    	long liCodComunidad = QMListaComunidadesActivos.getCodComunidadActivo(conexion, Integer.parseInt(gasto.getCOACES()));
	    	
	    	sNombreBeneficiario = QMComunidades.getNombreComunidad(conexion, liCodComunidad);
	    	
	    	if (sNombreBeneficiario.equals(""))
	    	{
	    		//Longitud maxima (36)
	    		sNombreBeneficiario = "ADMINISTRADOR DE LA FINCA";
	    	}
	    	else if (sNombreBeneficiario.length() > Longitudes.N34_CAMPO_L)
	    	{
	    		sNombreBeneficiario = sNombreBeneficiario.substring(0, Longitudes.N34_CAMPO_L);
	    	}
			
	    	String sDomicilio = activo.getNOVIAS() + " " + activo.getNUPOAC();
	    	
	    	if (sDomicilio.length() > Longitudes.N34_CAMPO_L)
	    	{
	    		sDomicilio1Beneficiario = sDomicilio.substring(0, Longitudes.N34_CAMPO_L);
	    		sDomicilio2Beneficiario = sDomicilio.substring(Longitudes.N34_CAMPO_L);
	    	}
	    	else
	    	{
	    		sDomicilio1Beneficiario = sDomicilio;
	    	}
	    	
	    	String sPlaza = activo.getCOPOIN()+ " "+ activo.getNOMUIN();
	    	

	    	
	    	if (sPlaza.length() > Longitudes.N34_CAMPO_L)
	    	{
	    		sPlazaBeneficiario = sPlaza.substring(0, Longitudes.N34_CAMPO_L);
	    	}
	    	else
	    	{
	    		sPlazaBeneficiario = sPlaza;
	    	}
	    	
	    	String sProvincia = activo.getNOPRAC();
	    	
	    	if (!sProvincia.equals(activo.getNOMUIN()) && !sProvincia.equals(""))
	    	{
	    		sProvinciaBeneficiario = sProvincia;
	    	}
	    	
	    	String sConcepto1 = CLDescripciones.descripcionGasto(gasto.getCOGRUG(), gasto.getCOTPGA(), gasto.getCOSBGA());
	    	
	    	String sConcepto2 = activo.getNUPIAC() 
	    			+ (activo.getNUPUAC().isEmpty()? "" : " " + activo.getNUPUAC()) 
	    			+ (activo.getNUESAC().isEmpty()? "" : " " + activo.getNUESAC());
	    	
	    	String sFechaConcepto = Utils.recuperaFecha(gasto.getFEDEVE());
	    	
	    	if (sConcepto1.length() < (Longitudes.N34_CAMPO_L-sFechaConcepto.length()-1))
	    	{
	    		sConcepto1Transferencia = sConcepto1 + " " + sFechaConcepto;
	    	}
	    	else
	    	{
	    		sConcepto1Transferencia = sConcepto1.substring(0, (Longitudes.N34_CAMPO_L-sFechaConcepto.length()-1)) + " " + sFechaConcepto;
	    	}
	    	

	    	if (!sConcepto2.equals("  "))
	    	{
	    		sConcepto2Transferencia  = sConcepto2;
	    	}
	    	
	    	logger.debug("sCodOrdenante:|"+sCodOrdenante+"|"+sCodOrdenante.length());
	    	logger.debug("sReferenciaBeneficiario:|"+sReferenciaBeneficiario+"|"+sReferenciaBeneficiario.length());
	    	logger.debug("sImporte:|"+sImporte+"|"+sImporte.length());
	    	logger.debug("sNUCCEN:|"+sNUCCEN+"|"+sNUCCEN.length());
	    	logger.debug("sNUCCOF:|"+sNUCCOF+"|"+sNUCCOF.length());
	    	logger.debug("sNUCCDI:|"+sNUCCDI+"|"+sNUCCDI.length()); 
	    	logger.debug("sNUCCNT:|"+sNUCCNT+"|"+sNUCCNT.length());
	    	logger.debug("sNombreBeneficiario:|"+sNombreBeneficiario+"|"+sNombreBeneficiario.length());
	    	logger.debug("sDomicilio1Beneficiario:|"+sDomicilio1Beneficiario+"|"+sDomicilio1Beneficiario.length());
	    	logger.debug("sDomicilio2Beneficiario:|"+sDomicilio2Beneficiario+"|"+sDomicilio2Beneficiario.length());
	    	logger.debug("sPlazaBeneficiario:|"+sPlazaBeneficiario+"|"+sPlazaBeneficiario.length());
	    	logger.debug("sProvinciaBeneficiario:|"+sProvinciaBeneficiario+"|"+sProvinciaBeneficiario.length());
	    	logger.debug("sConcepto1Transferencia:|"+sConcepto1Transferencia+"|"+sConcepto1Transferencia.length());
	    	logger.debug("sConcepto2Transferencia:|"+sConcepto2Transferencia+"|"+sConcepto2Transferencia.length());
	    	
		}
		
    	
		return new TransferenciaN34 (
				sCodOrdenante,
				sReferenciaBeneficiario,
				sImporte,
				sNUCCEN,
				sNUCCOF,
				sNUCCDI, 
				sNUCCNT,
				sNombreBeneficiario,
				sDomicilio1Beneficiario,
				sDomicilio2Beneficiario,
				sPlazaBeneficiario,
				sProvinciaBeneficiario, 
				sConcepto1Transferencia,
				sConcepto2Transferencia);
		
	}
	
	public static ResumenN34 generarResumenN34(long liSumaImportes, int iNumeroTransferencias, int iNumeroRegistros)
	{
		
		String sCodOrdenante = ValoresDefecto.DEF_CODIGO_ORDENANTE;

		return new ResumenN34 (
				sCodOrdenante,
				Long.toString(liSumaImportes),
				Integer.toString(iNumeroTransferencias),
				Integer.toString(iNumeroRegistros));
	}
	
	public static TransferenciaN34 buscarTransferenciaN34 (long liCodTransferencia)
	{
		return QMTransferenciasN34.getTransferencia(ConnectionManager.getDBConnection(),liCodTransferencia);
	}
	
	public static OrdenanteN3414 generarOrdenanteN3414(Cuenta cuenta)
	{
		String sCodRegistro = ValoresDefecto.DEF_CODIGO_REGISTRO_ORDENANTE_N3414;
		String sCodOperacion = ValoresDefecto.DEF_CODIGO_OPERACION_ORDENANTE_N3414;
		String sVersionCuaderno = ValoresDefecto.DEF_VERSION_CUADERNO_N3414;
		String sNumeroDato = ValoresDefecto.DEF_NUMERO_DATO_ORDENANTE_N3414;
		String sIdentificacionOrdenante = ValoresDefecto.DEF_IDENTIFICACION_ORDENANTE_N3414;
		String sIdentificacionOrdenanteSufijo = ValoresDefecto.DEF_IDENTIFICACION_ORDENANTE_SUFIJO_N3414;
		String sFechaCreacionFichero = Utils.sumaDiasFecha(Utils.fechaDeHoy(false), -1);
		String sFechaEjecucionOrdenes = Utils.fechaDeHoy(false);
		String sIdentificadorCuentaOrdenante = ValoresDefecto.DEF_IDENTIFICACION_CUENTA_ORDENANTE_N3414;
		String sCuentaOrdenante = cuenta.getsPais()+cuenta.getsDCIBAN()+cuenta.getsNUCCEN()+cuenta.getsNUCCOF()+cuenta.getsNUCCDI()+cuenta.getsNUCCNT();
		String sDetallesCargos = ValoresDefecto.DEF_DETALLE_CARGO_ORDENANTE_N3414;
		String sNombreOrdenante = ValoresDefecto.DEF_NOMBRE_ORDENANTE_N3414;
		String sDireccionOrdenante1 = ValoresDefecto.DEF_DIRECCION_ORDENANTE1_N3414;
		String sDireccionOrdenante2 = ValoresDefecto.DEF_DIRECCION_ORDENANTE2_N3414;
		String sDireccionOrdenante3 = ValoresDefecto.DEF_DIRECCION_ORDENANTE3_N3414;
		String sPaisOrdenante = ValoresDefecto.DEF_PAIS_ORDENANTE_N3414;
		
		logger.debug("sCodRegistro:|"+sCodRegistro+"|"+sCodRegistro.length());
		logger.debug("sCodOperacion:|"+sCodOperacion+"|"+sCodOperacion.length());
		logger.debug("sVersionCuaderno:|"+sVersionCuaderno+"|"+sVersionCuaderno.length());
		logger.debug("sNumeroDato:|"+sNumeroDato+"|"+sNumeroDato.length());
		logger.debug("sIdentificacionOrdenante:|"+sIdentificacionOrdenante+"|"+sIdentificacionOrdenante.length());
		logger.debug("sIdentificacionOrdenanteSufijo:|"+sIdentificacionOrdenanteSufijo+"|"+sIdentificacionOrdenanteSufijo.length());
		logger.debug("sFechaCreacionFichero:|"+sFechaCreacionFichero+"|"+sFechaCreacionFichero.length());
		logger.debug("sFechaEjecucionOrdenes:|"+sFechaEjecucionOrdenes+"|"+sFechaEjecucionOrdenes.length());
		logger.debug("sIdentificadorCuentaOrdenante:|"+sIdentificadorCuentaOrdenante+"|"+sIdentificadorCuentaOrdenante.length());
		logger.debug("sCuentaOrdenante:|"+sCuentaOrdenante+"|"+sCuentaOrdenante.length());
		logger.debug("sDetallesCargos:|"+sDetallesCargos+"|"+sDetallesCargos.length());
		logger.debug("sNombreOrdenante:|"+sNombreOrdenante+"|"+sNombreOrdenante.length());
		logger.debug("sDireccionOrdenante1:|"+sDireccionOrdenante1+"|"+sDireccionOrdenante1.length());
		logger.debug("sDireccionOrdenante2:|"+sDireccionOrdenante2+"|"+sDireccionOrdenante2.length());
		logger.debug("sDireccionOrdenante3:|"+sDireccionOrdenante3+"|"+sDireccionOrdenante3.length());
		logger.debug("sPaisOrdenante:|"+sPaisOrdenante+"|"+sPaisOrdenante.length());
		
		
		
		return new OrdenanteN3414 (
				sCodRegistro,
				sCodOperacion,
				sVersionCuaderno,
				sNumeroDato,
				sIdentificacionOrdenante,
				sIdentificacionOrdenanteSufijo,
				sFechaCreacionFichero,
				sFechaEjecucionOrdenes,
				sIdentificadorCuentaOrdenante, 
				sCuentaOrdenante,
				sDetallesCargos,
				sNombreOrdenante,
				sDireccionOrdenante1,
				sDireccionOrdenante2,
				sDireccionOrdenante3,
				sPaisOrdenante);
	}
	
	public static CabeceraTransferenciasN3414 generarCabeceraTransferenciaN3414()
	{
		
		String sCodRegistro = ValoresDefecto.DEF_CODIGO_REGISTRO_CEBECERA_N3414;
		String sCodOperacion = ValoresDefecto.DEF_CODIGO_OPERACION_CEBECERA_N3414;
		String sVersionCuaderno = ValoresDefecto.DEF_VERSION_CUADERNO_N3414;
		String sIdentificacionOrdenante = ValoresDefecto.DEF_IDENTIFICACION_ORDENANTE_N3414;
		String sIdentificacionOrdenanteSufijo = ValoresDefecto.DEF_IDENTIFICACION_ORDENANTE_SUFIJO_N3414;

		return new CabeceraTransferenciasN3414 (
				sCodRegistro,
				sCodOperacion,
				sVersionCuaderno,
				sIdentificacionOrdenante,
				sIdentificacionOrdenanteSufijo);
	}
	
	public static TransferenciaN3414 generarTransferenciaN3414(long liCodGasto, Cuenta cuenta, long liRecargo)
	{
		Connection conexion = ConnectionManager.getDBConnection();
		
		String sCodRegistro = "";
		String sCodOperacion = "";
		String sVersionCuaderno = "";
		String sNumeroDato = "";
		String sReferenciaOrdenante = "";
		String sIdentificadorCuentaBeneficiario = "";
		String sCuentaBeneficiario = "";
		String sImporteTransferencia = "";
		String sClaveGasto = "";
		String sBICBeneficiario = "";
		String sNombreBeneficiario = "";
		String sDireccionBeneficiario1 = "";
		String sDireccionBeneficiario2 = "";
		String sDireccionBeneficiario3 = "";
		String sPaisBeneficiario = "";
		String sConcepto = "";
		String sIdentificacionInstruccion = "";
		String sTipoTransferencia = "";
		String sPropositoTransferencia = "";
    	
		if (conexion != null)
		{

			sCodRegistro = ValoresDefecto.DEF_CODIGO_REGISTRO_BENEFICIARIO_N3414;
			sCodOperacion = ValoresDefecto.DEF_CODIGO_OPERACION_BENEFICIARIO_N3414;
			sVersionCuaderno = ValoresDefecto.DEF_VERSION_CUADERNO_N3414;
			sNumeroDato = ValoresDefecto.DEF_NUMERO_DATO_BENEFICIARIO_N3414;
			sReferenciaOrdenante = ValoresDefecto.LIBRE35;
			sIdentificadorCuentaBeneficiario = ValoresDefecto.DEF_IDENTIFICACION_CUENTA_BENEFICIARIO_N3414;
			sCuentaBeneficiario = cuenta.getsPais()+cuenta.getsDCIBAN()+cuenta.getsNUCCEN()+cuenta.getsNUCCOF()+cuenta.getsNUCCDI()+cuenta.getsNUCCNT();
			
			
			Gasto gasto = QMGastos.getGasto(conexion, liCodGasto);
			gasto.setValor_total();
			//sReferenciaBeneficiario = gasto.getCOACES();
			
			sImporteTransferencia = Long.toString(gasto.getValor_total()+liRecargo);
			
			sClaveGasto = ValoresDefecto.DEF_CLAVE_GASTOS_N3414;

			sBICBeneficiario = CLDescripciones.swiftEntidad(cuenta.getsNUCCEN());
			
			Activo activo = QMActivos.getActivo(conexion, Integer.parseInt(gasto.getCOACES()));
			
	    	long liCodComunidad = QMListaComunidadesActivos.getCodComunidadActivo(conexion, Integer.parseInt(gasto.getCOACES()));
	    	
	    	sNombreBeneficiario = QMComunidades.getNombreComunidad(conexion, liCodComunidad);
	    	
	    	if (sNombreBeneficiario.equals(""))
	    	{
	    		//Longitud maxima (70)
	    		sNombreBeneficiario = "ADMINISTRADOR DE LA FINCA";
	    	}
			
	    	String sDomicilio = activo.getNOVIAS() + " " + activo.getNUPOAC();
	    	
	    	if (sDomicilio.length() > Longitudes.N3414_DIRECCION_BENEFICIARIO1_L)
	    	{
	    		sDireccionBeneficiario1 = sDomicilio.substring(0, Longitudes.N3414_DIRECCION_BENEFICIARIO1_L);
	    		sDireccionBeneficiario2 = sDomicilio.substring(Longitudes.N3414_DIRECCION_BENEFICIARIO1_L);
	    	}
	    	else
	    	{
	    		sDireccionBeneficiario1 = sDomicilio;
	    	}
	    	
	    	String sPlaza = sDireccionBeneficiario2 + activo.getCOPOIN()+ " "+ activo.getNOMUIN();
	    	

	    	
	    	if (sPlaza.length() > Longitudes.N3414_DIRECCION_BENEFICIARIO2_L)
	    	{
	    		sDireccionBeneficiario2 = sPlaza.substring(0, Longitudes.N3414_DIRECCION_BENEFICIARIO2_L);
	    		sDireccionBeneficiario3 = sPlaza.substring(Longitudes.N3414_DIRECCION_BENEFICIARIO2_L);
	    	}
	    	else
	    	{
	    		sDireccionBeneficiario2 = sDireccionBeneficiario2 + sPlaza;
	    	}
	    	
	    	sDireccionBeneficiario3 = sDireccionBeneficiario3 + activo.getNOPRAC();

	    	sPaisBeneficiario = ValoresDefecto.DEF_PAIS_BENEFICIARIO_N3414;

	    	String sConcepto1 = CLDescripciones.descripcionGasto(gasto.getCOGRUG(), gasto.getCOTPGA(), gasto.getCOSBGA()).toUpperCase();
	    	
	    	String sConcepto2 = activo.getNUPIAC() 
	    			+ (activo.getNUPUAC().isEmpty()? "" : " " + activo.getNUPUAC()) 
	    			+ (activo.getNUESAC().isEmpty()? "" : " " + activo.getNUESAC());
	    	
	    	String sFechaConcepto = Utils.recuperaFecha(gasto.getFEDEVE());
	    	

	    	
	    	sConcepto = "PAGO DE "+ sConcepto1 + " " + sFechaConcepto + " " + sConcepto2  + " REF. G"+liCodGasto;
	    	
			sIdentificacionInstruccion = ValoresDefecto.LIBRE35;
			sTipoTransferencia = ValoresDefecto.LIBRE4;
			sPropositoTransferencia = ValoresDefecto.DEF_PROPOSITO_TRANSFERENCIA_N3414;
	

	    	
			
	    	
	    	logger.debug("sCodRegistro:|"+sCodRegistro+"|"+sCodRegistro.length());
	    	logger.debug("sCodOperacion:|"+sCodOperacion+"|"+sCodOperacion.length());
	    	logger.debug("sVersionCuaderno:|"+sVersionCuaderno+"|"+sVersionCuaderno.length());
	    	logger.debug("sNumeroDato:|"+sNumeroDato+"|"+sNumeroDato.length());
	    	logger.debug("sReferenciaOrdenante:|"+sReferenciaOrdenante+"|"+sReferenciaOrdenante.length());
	    	logger.debug("sIdentificadorCuentaBeneficiario:|"+sIdentificadorCuentaBeneficiario+"|"+sIdentificadorCuentaBeneficiario.length()); 
	    	logger.debug("sCuentaBeneficiario:|"+sCuentaBeneficiario+"|"+sCuentaBeneficiario.length());
	    	logger.debug("sImporteTransferencia:|"+sImporteTransferencia+"|"+sImporteTransferencia.length());
	    	logger.debug("sClaveGasto:|"+sClaveGasto+"|"+sClaveGasto.length());
	    	logger.debug("sBICBeneficiario:|"+sBICBeneficiario+"|"+sBICBeneficiario.length());
	    	logger.debug("sNombreBeneficiario:|"+sNombreBeneficiario+"|"+sNombreBeneficiario.length());
	    	logger.debug("sDireccionBeneficiario1:|"+sDireccionBeneficiario1+"|"+sDireccionBeneficiario1.length());
	    	logger.debug("sDireccionBeneficiario2:|"+sDireccionBeneficiario2+"|"+sDireccionBeneficiario2.length());
	    	logger.debug("sDireccionBeneficiario3:|"+sDireccionBeneficiario3+"|"+sDireccionBeneficiario3.length());
	    	logger.debug("sPaisBeneficiario:|"+sPaisBeneficiario+"|"+sPaisBeneficiario.length());
	    	logger.debug("sConcepto:|"+sConcepto+"|"+sConcepto.length());
	    	logger.debug("sIdentificacionInstruccion:|"+sIdentificacionInstruccion+"|"+sIdentificacionInstruccion.length());
	    	logger.debug("sTipoTransferencia:|"+sTipoTransferencia+"|"+sTipoTransferencia.length());
	    	logger.debug("sPropositoTransferencia:|"+sPropositoTransferencia+"|"+sPropositoTransferencia.length());
		}
    	
		return new TransferenciaN3414 (
				sCuentaBeneficiario,
				sImporteTransferencia,
				sBICBeneficiario,
				sNombreBeneficiario,
				sDireccionBeneficiario1, 
				sDireccionBeneficiario2,
				sDireccionBeneficiario3,
				sPaisBeneficiario,
				sConcepto);
		
	}
	
	public static ResumenN3414 generarResumenN3414(long liSumaImportes, int iNumeroTransferencias, int iNumeroRegistros)
	{
		
		String sCodRegistro = ValoresDefecto.DEF_CODIGO_REGISTRO_RESUMEN_N3414;
		String sCodOperacion = ValoresDefecto.DEF_CODIGO_OPERACION_RESUMEN_N3414;

		return new ResumenN3414 (
				sCodRegistro,
				sCodOperacion,
				Long.toString(liSumaImportes),
				Integer.toString(iNumeroTransferencias),
				Integer.toString(iNumeroRegistros));
	}
	
	public static TotalesN3414 generarTotalesN3414(long liSumaImportes, int iNumeroTransferencias, int iNumeroRegistros)
	{
		
		String sCodRegistro = ValoresDefecto.DEF_CODIGO_REGISTRO_TOTALES_N3414;
		String sCodOperacion = ValoresDefecto.DEF_CODIGO_OPERACION_TOTALES_N3414;

		return new TotalesN3414 (
				sCodRegistro,
				sCodOperacion,
				Long.toString(liSumaImportes),
				Integer.toString(iNumeroTransferencias),
				Integer.toString(iNumeroRegistros));
	}
	
	public static TransferenciaN3414 buscarTransferenciaN3414 (long liCodTransferencia)
	{
		return QMTransferenciasN3414.getTransferencia(ConnectionManager.getDBConnection(),liCodTransferencia);
	}
	
}

//Autor: Francisco Valverde Manjón