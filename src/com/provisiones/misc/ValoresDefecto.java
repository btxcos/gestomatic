package com.provisiones.misc;

import java.io.File;

public class ValoresDefecto 
{
	
	public static final int iNumChar = 26;
	public static final String DEF_COAPII = "168";
	public static final String DEF_NUCLII = "2306894218400";
	public static final String DEF_IDPROV = "68942184";
	public static final String DEF_COREAE = "04";
	public static final String DEF_COUNMO = "281";
	
	//Eliminar tras soporte a usuarios
	public static final String DEF_USUARIO = "GLSLUSUP"; 
	
	
	public static final String DEF_COSPII_GA = "GA";
	public static final String DEF_COSPII_E1 = "E1";
	public static final String DEF_COSPII_E2 = "E2";
	public static final String DEF_COSPII_E3 = "E3";
	public static final String DEF_COSPII_E4 = "E4";
	public static final String DEF_COSPII_PA = "PA";
	public static final String DEF_COSPII_RG = "RG";
	public static final String DEF_COSPII_AC = "AC";
	public static final String DEF_COSPII_PP = "PP";
	
		
	public static final String DEF_E1_CODTRN = "EE41";
	public static final String DEF_E2_CODTRN = "EE42";
	public static final String DEF_E3_CODTRN = "EE43";
	public static final String DEF_E4_CODTRN = "EE44";
	
	public static final String DEF_FECHA = "0";
	public static final String DEF_CODIGO_ALFA = "#";
	public static final String DEF_CODIGO_NUM = "0";
		
	public static final String DEF_COENGP = "0";
	public static final String DEF_COTEXA = "0";

	public static final String DEF_COGRUG_E2 = "2";
	public static final String DEF_COTACA_E2 = "2";
	
	public static final String DEF_COGRUG_E4 = "2";
	public static final String DEF_COTACA_E4 = "1";
	
	public static final String DEF_ALTA = "A";
	public static final String DEF_BAJA = "B";
	public static final String DEF_PENDIENTE = "P";
	public static final String DEF_ENVIADO = "E";
	
	public static final String DEF_GASTO_ANULADO = "4";
	
	public static final String DEF_GASTO_ESTIMADO = "1";
	public static final String DEF_GASTO_CONOCIDO = "2";
	
	public static final String DEF_COTERR = "0";
	public static final String DEF_COTDOR = "0";
	public static final String DEF_OBDEER = "                                                                                ";

	public static final String DEF_COENCX = "0";
	public static final String DEF_COOFCX = "0";
	public static final String DEF_NUCONE = "0";
	public static final String DEF_FMPAGN = "0";
	
	public static final String DEF_COTNEG = "0";
	public static final String DEF_FEAPLI = "0";
	
	public static final String DEF_COMONA = "0";
	public static final String DEF_BIAUTO = "0";
	public static final String DEF_FEAUFA = "0";
	public static final String DEF_FEAGTO = "0";
	public static final String DEF_FEPGPR = "0";

	
	
	public enum TIPOSFICHERO 
	{
		AC, RG, PA, GA, PP, E1, E2, E3, E4
	}
	
	public enum TIPOSACCIONES 
	{
		A, B, E, M, X
	}
	public enum TIPOSACCIONESGASTO 
	{
		G, M, N, D, A
	}
	

	public static final String DEF_FIN_FICHERO = new Character((char) iNumChar).toString();

	public static final String DEF_PATH = "provisiones"+File.separator;
	public static final String DEF_EXEC_PATH = System.getProperty("user.dir")+File.separator;
	public static final String DEF_PATH_BACKUP_RECIBIDOS = DEF_EXEC_PATH+"recibidos"+File.separator;
	public static final String DEF_PATH_BACKUP_GENERADOS = DEF_EXEC_PATH+"generados"+File.separator;
	public static final String DEF_PATH_LOGS = DEF_EXEC_PATH+"logs"+File.separator;
	
	
}
