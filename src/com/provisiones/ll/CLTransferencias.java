package com.provisiones.ll;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMGastos;
import com.provisiones.dal.qm.QMTransferenciasN34;
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
	    		sNombreBeneficiario = "ADMINISTRADOR DE LA FINCA";
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
}
