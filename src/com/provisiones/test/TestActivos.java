package com.provisiones.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMPagos;
import com.provisiones.dal.qm.QMTransferenciasN34;
import com.provisiones.dal.qm.usuarios.QMUsuarios;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Pago;
import com.provisiones.types.tablas.ResultadosTabla;
import com.provisiones.types.transferencias.N34.TransferenciaN34;
import com.provisiones.types.usuarios.Usuario;


@SuppressWarnings("unused")
public class TestActivos 
{
	//private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	public static void main(String[] args) throws IOException 
	{
		Connection conx = ConnectionManager.openDBConnection();
		
		//ArrayList<TransferenciaN34> resultado = QMTransferenciasN34.getTodasTransferenciasTEMP(conx);
		
		/*
		System.out.println("TAM:"+resultado.size());
		
        for (int i = 0; i < resultado.size() ; i++)
        {
        	QMTransferenciasN34.addTransferenciaTEMP(conx, resultado.get(i));
        }

        
        TransferenciaN34 t1 = QMTransferenciasN34.getTransferenciaTEMP(conx, 1);
        
        System.out.println("t1.getsCodOrdenante():"+t1.getsCodOrdenante());
        System.out.println("t1.getsReferenciaBeneficiario():"+t1.getsReferenciaBeneficiario());
        System.out.println("t1.getsImporte():"+t1.getsImporte());
        System.out.println("t1.getsNUCCEN():"+t1.getsNUCCEN());
        System.out.println("t1.getsNUCCOF():"+t1.getsNUCCOF());
        System.out.println("t1.getsNUCCDI()"+t1.getsNUCCDI());
        System.out.println("t1.getsNUCCNT():"+t1.getsNUCCNT());
        System.out.println("t1.getsNombreBeneficiario():"+t1.getsNombreBeneficiario());
        System.out.println("t1.getsDomicilio1Beneficiario():"+t1.getsDomicilio1Beneficiario());
        System.out.println("t1.getsDomicilio2Beneficiario():"+t1.getsDomicilio2Beneficiario());        
        System.out.println("t1.getsPlazaBeneficiario():"+t1.getsPlazaBeneficiario());
        System.out.println("t1.getsProvinciaBeneficiario():"+t1.getsProvinciaBeneficiario());
        System.out.println("t1.getsConcepto1Transferencia():"+t1.getsConcepto1Transferencia());
        System.out.println("t1.getsConcepto2Transferencia():"+t1.getsConcepto2Transferencia());
     
*/        
		String sTemp = "";
		
		
		System.out.println("CI:|"+Utils.compruebaImporte(sTemp)+"|");
		
		ConnectionManager.closeDBConnection(conx);
	}

}
