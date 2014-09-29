package com.provisiones.misc;

import java.io.File;

public final class ValoresDefecto 
{
	
	public static final int CHAR_FIN = 26;
	
	public static final int DECIMALES = 2;
	
	public static final int MAX_GASTOS_PROVISION = 500;
	
	public static final int ID_COMUNIDAD = 1;
	public static final int ID_CUOTA = 2;
	public static final int ID_REFERENCIA = 3;
	public static final int ID_RECURSO = 4;
	public static final int ID_ACTIVO = 5;
	public static final int ID_GASTO = 6;
	public static final int ID_PROVISION = 7;
	
	
	public static final String CAMPO_ALFA_SIN_INFORMAR = "";
	public static final String CAMPO_NUME_SIN_INFORMAR = "0";
	
	public static final String CAMPO_NUME_INCORRECTO = "#";
	
	public static final byte ACTIVO = 1;
	public static final byte INACTIVO = 0;
	
	public static final byte GASTO_URGENTE = 1;
	public static final byte GASTO_CONVENCIONAL = 0;
	
	public static final byte CUENTA_COMUNIDAD = 1;
	public static final byte CUENTA_CONVENCIONAL = 0;
	public static final byte CUENTA_TODAS = 2;
	
	public static final byte PAGO_ENVIADO = 1;
	public static final byte PAGO_EMITIDO = 0;
	
	public static final String DEF_SI = "1";
	public static final String DEF_NO = "0";
	
	public static final String ABONO_EMITIDO = "0";

	public static final String DEF_COAPII = "168";
	public static final String DEF_NUCLII = "2306894218400";
	public static final String DEF_IDPROV = "68942184";
	public static final String DEF_COREAE = "04";
	public static final String DEF_COUNMO = "281";
	
	public static final String CIFRADO_LLAVE_SIMETRICA = "glsl1234glsl1234";
	public static final String CIFRADO_LONGITUD = "512";
	public static final String CIFRADO_ALGORITMO = "AES";
	

	public static final String DEF_COSPII_GA = "GA";
	public static final String DEF_COSPII_E1 = "E1";
	public static final String DEF_COSPII_E2 = "E2";
	public static final String DEF_COSPII_E3 = "E3";
	public static final String DEF_COSPII_E4 = "E4";
	public static final String DEF_COSPII_PA = "PA";
	public static final String DEF_COSPII_RG = "RG";
	public static final String DEF_COSPII_AC = "AC";
	public static final String DEF_COSPII_PP = "PP";
	
	public static final String DEF_PAGOS = "PAGOS";
	
	public static final String DEF_NORMA34 = "N34";
		
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
	public static final String DEF_MODIFICACION = "M";
	
	public static final String DEF_ACTIVO_BLOQUEADO = "B";
	public static final String DEF_ACTIVO_DESBLOQUEADO = "D";
	
	public static final String DEF_COACCI_COMUNIDAD_ALTA = "A";
	public static final String DEF_COACCI_COMUNIDAD_MODIFICACION = "M";
	public static final String DEF_COACCI_COMUNIDAD_ALTA_ACTIVO = "X";
	public static final String DEF_COACCI_COMUNIDAD_BAJA_ACTIVO = "E";
	public static final String DEF_COACCI_COMUNIDAD_BAJA = "B";

	public static final String DEF_COACCI_CUOTA_ALTA = "A";
	public static final String DEF_COACCI_CUOTA_MODIFICACION = "M";
	public static final String DEF_COACCI_CUOTA_BAJA = "B";
	
	public static final String DEF_COACCI_REFERENCIA_ALTA = "A";
	public static final String DEF_COACCI_REFERENCIA_MODIFICACION = "M";
	public static final String DEF_COACCI_REFERENCIA_BAJA = "B";

	public static final String DEF_COACCI_RECURSO_ALTA = "A";
	public static final String DEF_COACCI_RECURSO_MODIFICACION = "M";
	public static final String DEF_COACCI_RECURSO_BAJA = "B";
	
	public static final String DEF_MOVIMIENTO_BLOQUEADO = "B";
	public static final String DEF_MOVIMIENTO_PENDIENTE = "P";
	public static final String DEF_MOVIMIENTO_ENVIADO = "E";
	public static final String DEF_MOVIMIENTO_VALIDADO = "V";
	public static final String DEF_MOVIMIENTO_RESUELTO = "R";
	public static final String DEF_MOVIMIENTO_ERRONEO = "X";
	
	public static final String DEF_ACCION_ERROR_REPARAR = "R";
	public static final String DEF_ACCION_ERROR_REENVIAR = "E";
	public static final String DEF_ACCION_ERROR_IGNORAR = "I";
	
	public static final String DEF_NEGATIVO = "-";
	
	public static final String DEF_COGRUG_COMPRAVENTA = "1";
	public static final String DEF_COGRUG_PENDIENTES = "2";
	public static final String DEF_COGRUG_ACCIONES = "3";
	
	public static final String DEF_COTPGA_PLUSVALIA = "1";
	public static final String DEF_COTPGA_IMPUESTO = "1";
	
	public static final String DEF_GASTO_ESTIMADO = "1";
	public static final String DEF_GASTO_CONOCIDO = "2";
	public static final String DEF_GASTO_AUTORIZADO = "3";
	public static final String DEF_GASTO_PAGADO = "4";
	public static final String DEF_GASTO_ANULADO = "5";
	public static final String DEF_GASTO_ABONADO = "6";
	public static final String DEF_GASTO_PAGADO_CONEXION = "7";
	
	public static final String DEF_GASTO_PROVISION_CONEXION = "0";
	
	public static final String DEF_PAGO_SIN = "0";
	public static final String DEF_PAGO_VENTANILLA = "1";
	public static final String DEF_PAGO_DEVOLUCION = "2";
	public static final String DEF_PAGO_NORMA34 = "3";
	public static final String DEF_PAGO_NORMA3414 = "4";
	public static final String DEF_PAGO_TRANSFERENCIA_MANUAL = "5";
	public static final String DEF_PAGO_CONEXION = "6";
	
	public static final String DEF_RECARGO_FIJO = "1";
	public static final String DEF_RECARGO_PROPORCIONAL = "2";
	
	public static final String DEF_PROVISION_ABIERTA = "A";
	public static final String DEF_PROVISION_ENVIADA = "E";
	public static final String DEF_PROVISION_PAGADA = "G";
	public static final String DEF_PROVISION_PENDIENTE = "P";
	public static final String DEF_PROVISION_RESUELTA = "R";	
	public static final String DEF_PROVISION_AUTORIZADA = "T";
	
	public static final String DEF_COTERR = "0";
	public static final String DEF_COTDOR = "0";
	public static final String DEF_OBDEER = "                                                                                ";

	public static final String DEF_COENCX = "0";
	public static final String DEF_COOFCX = "0";
	public static final String DEF_NUCONE = "0";
	public static final String DEF_FMPAGN = "0";
	
	public static final String DEF_COTNEG = "0";
	public static final String DEF_FEAPLI = "0";
	
	public static final String DEF_FEPAGA = "0";
	
	public static final String DEF_COMONA = "0";
	public static final String DEF_BIAUTO = " ";
	public static final String DEF_BIAUTO_AUTORIZADO = "1";
	public static final String DEF_FEAUFA = "0";
	public static final String DEF_FEAGTO = "0";
	public static final String DEF_FEPGPR = "0";

	public static final String DEF_CARGA_ACTUALIZADO = "ACTUALIZADO";
	public static final String DEF_CARGA_REVISAR     = "REVISAR";
	public static final String DEF_CARGA_NUEVO       = "NUEVO";
	public static final String DEF_CARGA_VALIDADO    = "VALIDADO";
	public static final String DEF_CARGA_ERROR       = "ERROR";
	public static final String DEF_CARGA_ERRORFATAL  = "FATAL";	
	public static final String DEF_CARGA_SINCAMBIOS  = "SIN CAMBIOS";
	
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
	
	public enum TIPOSACCIONESERROR
	{
		R, E, I
	}
	
	
	//Norma 34
	public static final String DEF_CODIGO_REGOPER_ORDENANTE = "0356"; 
	public static final String DEF_CODIGO_REGOPER_BENEFICIARIO = "0656"; 
	public static final String DEF_CODIGO_REGOPER_RESUMEN = "0856"; 
	
	
	public static final String DEF_CODIGO_ORDENANTE = "B79012860";
	
	public static final String DEF_ORDENANTE = "GUTIERREZ LABRADOR SL";
	public static final String DEF_ORDENANTE_DOMICILIO = "Calle Andres Mellado, 114, 1 Iz";
	public static final String DEF_ORDENANTE_PLAZA = "28003 MADRID";
	
	public static final String DEF_ORDENANTE_PAIS = "ES";
	public static final String DEF_ORDENANTE_IBAN = "25";
	public static final String DEF_ORDENANTE_ENTIDAD = "2038";
	public static final String DEF_ORDENANTE_OFICINA = "1859";
	public static final String DEF_ORDENANTE_CUENTA = "6003842008";
	public static final String DEF_ORDENANTE_DETALLE_CARGO = "1";
	public static final String DEF_ORDENANTE_DIGITO_CONTROL = "50";
	
	
	public static final String NUMERO_DATO_CABECERA_ORDENANTE = "001";
	public static final String NUMERO_DATO_NOMBRE_ORDENANTE = "002";
	public static final String NUMERO_DATO_DOMICILIO_ORDENANTE = "003";
	public static final String NUMERO_DATO_PLAZA_ORDENANTE = "004";

	public static final String NUMERO_DATO_CABECERA_TRANSFERENCIA = "010";
	public static final String NUMERO_DATO_NOMBRE_BENEFICIARIO = "011";
	public static final String NUMERO_DATO_DOMICILIO1_BENEFICIARIO = "012";
	public static final String NUMERO_DATO_DOMICILIO2_BENEFICIARIO = "013";
	public static final String NUMERO_DATO_PLAZA_BENEFICIARIO = "014";
	public static final String NUMERO_DATO_PROVINCIA_BENEFICIARIO = "015";
	public static final String NUMERO_DATO_CONCEPTO1_TRANSFERENCIA = "016";
	public static final String NUMERO_DATO_CONCEPTO2_TRANSFERENCIA = "017";
	
	public static final String LIBRE12 = "            ";
	public static final String LIBRE7 = "       ";
	public static final String LIBRE6 = "      ";
	public static final String LIBRE3 = "   ";
	public static final String LIBRE2 = "  ";
	
	public static final String DEF_CODIGO_GASTOCONCEPTO = "19";
	
	public static final String DEF_DELEGADO_ = "";
	public static final String DEF_DELEGADO_DOMICILIO = "";

	//Norma 3414
	
	public static final String DEF_CODIGO_REGISTRO_ORDENANTE_N3414 = "01";
	public static final String DEF_CODIGO_OPERACION_ORDENANTE_N3414 = "ORD";
	public static final String DEF_VERSION_CUADERNO_N3414 = "34145";
	public static final String DEF_NUMERO_DATO_ORDENANTE_N3414 = "001";
	public static final String DEF_IDENTIFICACION_ORDENANTE_N3414 = "B79012860";
	public static final String DEF_IDENTIFICACION_ORDENANTE_SUFIJO_N3414 = "000";
	public static final String DEF_IDENTIFICACION_CUENTA_ORDENANTE_N3414 = "A";
	public static final String DEF_CUENTA_ORDENANTE_N3414 = "ES2520381859506003842008";
	public static final String DEF_DETALLE_CARGO_ORDENANTE_N3414 = "0";
	public static final String DEF_NOMBRE_ORDENANTE_N3414 = "GUTIERREZ LABRADOR SL";
	public static final String DEF_DIRECCION_ORDENANTE1_N3414 = "Calle Andres Mellado 114, 1 Izquierda";
	public static final String DEF_DIRECCION_ORDENANTE2_N3414 = "28003 Madrid";
	public static final String DEF_DIRECCION_ORDENANTE3_N3414 = "Madrid";
	public static final String DEF_PAIS_ORDENANTE_N3414 = "ES";
	public static final String LIBRE311 = "                                                                                                                                                                                                                                                                                                                       ";
	
	public static final String DEF_CODIGO_REGISTRO_CEBECERA_N3414 = "02";
	public static final String DEF_CODIGO_OPERACION_CEBECERA_N3414 = "SCT";
	public static final String LIBRE578 = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ";

	public static final String DEF_CODIGO_REGISTRO_BENEFICIARIO_N3414 = "03";
	public static final String DEF_CODIGO_OPERACION_BENEFICIARIO_N3414 = "SCT";
	public static final String DEF_NUMERO_DATO_BENEFICIARIO_N3414 = "002";
	public static final String DEF_IDENTIFICACION_CUENTA_BENEFICIARIO_N3414 = "A";
	public static final String DEF_CLAVE_GASTOS_N3414 = "3";
	public static final String DEF_PAIS_BENEFICIARIO_N3414 = "ES";
	public static final String LIBRE35 = "                                   ";
	public static final String DEF_TIPO_TRANSFERENCIA_N3414 = "OTHR";
	public static final String LIBRE4 = "    ";
	public static final String LIBRE99 = "                                                                                                   ";
	
	
	public static final String DEF_CODIGO_REGISTRO_RESUMEN_N3414 = "04";
	public static final String DEF_CODIGO_OPERACION_RESUMEN_N3414 = "SCT";
	public static final String LIBRE560 = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";

	public static final String DEF_CODIGO_REGISTRO_TOTALES_N3414 = "99";
	public static final String DEF_CODIGO_OPERACION_TOTALES_N3414 = "ORD";
	
	public static final String DEF_CODIFICACION = "ISO-8859-15";
	public static final String DEF_FIN_FICHERO = new Character((char) CHAR_FIN).toString();

	public static final String DEF_PATH = "GLSL-portales"+File.separator+"provisiones"+File.separator;
	public static final String DEF_EXEC_PATH = System.getProperty("user.dir")+File.separator+DEF_PATH;

	public static final String DEF_FILE_PATH = DEF_EXEC_PATH+"archivos"+File.separator;
	public static final String DEF_PATH_BACKUP_RECIBIDOS = DEF_FILE_PATH+"recibidos"+File.separator;
	public static final String DEF_PATH_BACKUP_GENERADOS = DEF_FILE_PATH+"generados"+File.separator;
	
	public static final String DEF_DUMP_PATH = DEF_EXEC_PATH+"volcados"+File.separator;
	public static final String DEF_PATH_BACKUP_CARGADOS    = DEF_DUMP_PATH+"cargados"+File.separator;
	public static final String DEF_PATH_BACKUP_DESCARGADOS = DEF_DUMP_PATH+"descargados"+File.separator;
	
	public static final String DEF_PATH_LOGS = DEF_EXEC_PATH+"logs"+File.separator;

	private ValoresDefecto(){}
	
}
