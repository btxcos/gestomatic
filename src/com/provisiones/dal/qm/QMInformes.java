package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.listas.QMListaComunidadesActivos;
import com.provisiones.dal.qm.listas.QMListaGastosProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.types.informes.CierreInforme;


public class QMInformes 
{
	private static Logger logger = LoggerFactory.getLogger(QMInformes.class.getName());
	
	private QMInformes(){}
	
	public static ArrayList<CierreInforme> buscaCierreInformeProvision(Connection conexion, String sNUPROF)
	{
		ArrayList<CierreInforme> resultado = new ArrayList<CierreInforme>();

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");

			String sQuery = "SELECT "
						   + QMGastos.CAMPO1 + "," 
						   + QMGastos.CAMPO2 + ","        
						   + QMGastos.CAMPO3 + ","
						   + QMGastos.CAMPO4 + ","
						   + QMGastos.CAMPO5 + ","
						   + QMGastos.CAMPO6 + ","
						   + QMGastos.CAMPO7 + ","
						   + QMGastos.CAMPO9 + ","
						   + QMGastos.CAMPO15 + ","
						   + QMGastos.CAMPO16 + ","
						   + QMGastos.CAMPO35 +

						   " FROM " 
						   + QMGastos.TABLA + 
						   " WHERE ("
						   + QMGastos.CAMPO1 + 
						   " IN (SELECT "
						   +  QMListaGastosProvisiones.CAMPO1 + 
						   " FROM " 
						   + QMListaGastosProvisiones.TABLA + 
						   " WHERE " 
						   + QMListaGastosProvisiones.CAMPO2 + " = '"+ sNUPROF + "'))";					   
						   
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				
				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;
						
						String sGastoID = rs.getString(QMGastos.CAMPO1);
						String sCOACES  = rs.getString(QMGastos.CAMPO2);
						
						int iCOACES = rs.getInt(QMGastos.CAMPO2);
						
						String sFEADAC  = Utils.recuperaFecha(QMActivos.getFEADACActivo(conexion,iCOACES));
						String sDTAS =  QMCodigosControl.getDesCampo(conexion, QMCodigosControl.TTIACSA,QMCodigosControl.ITIACSA,com.provisiones.ll.CLActivos.compruebaTipoObra(iCOACES));
						
						String sCOGRUG  = rs.getString(QMGastos.CAMPO3);
						String sCOTPGA  = rs.getString(QMGastos.CAMPO4);
						String sCOSBGA  = rs.getString(QMGastos.CAMPO5);
						String sFEDEVE  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO7));
						String sFELIPG  = Utils.recuperaFecha(rs.getString(QMGastos.CAMPO9));
						String sDCOSBGA = QMCodigosControl.getDesCOSBGA(conexion,sCOGRUG,sCOTPGA,sCOSBGA);
						String sDPTPAGO = QMCodigosControl.getDesCampo(conexion,QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,rs.getString(QMGastos.CAMPO6));
						String sIMNGAS  = Utils.recuperaImporte(rs.getString(QMGastos.CAMPO16).equals("-"),rs.getString(QMGastos.CAMPO15));
						String sCIF = QMListaComunidadesActivos.getCIFComunidadActivo(conexion,iCOACES);
						String sFAACTA = Utils.recuperaFecha(QMCuotas.getFAACTACuota(conexion,com.provisiones.ll.CLGastos.obtenerCuotaDeGasto(rs.getInt(QMGastos.CAMPO1))));
						String sNURCAT = QMActivos.getReferenciaCatastral(conexion,iCOACES);
						
						CierreInforme entrada = new CierreInforme(
								"G"+sGastoID,
								sCOACES,
								sFEADAC,
								sDTAS,
								sCOGRUG,
								sCOTPGA,
								sCOSBGA,
								sFEDEVE,
								sFELIPG,
								sDCOSBGA,
								sDPTPAGO,
								sIMNGAS,
								sCIF,
								sFAACTA,
								sNURCAT,
								"");
						
						resultado.add(entrada);
						
						logger.debug("Encontrado el registro!");

					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				logger.error("ERROR NUPROF:|"+sNUPROF+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return resultado;
	}
}
