package com.provisiones.misc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.application.FacesMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.types.Cuenta;
import com.provisiones.types.ImporteDevolucion;
import com.provisiones.types.informes.RangoAnual;

public final class Utils 
{
	private static Logger logger = LoggerFactory.getLogger(Utils.class.getName());

	private Utils(){}


	
	public static String cifra (String sMsg)
	{

		  byte[] campoCifrado = null;
   
		  SecretKeySpec key = new SecretKeySpec(ValoresDefecto.CIFRADO_LLAVE_SIMETRICA.getBytes(), ValoresDefecto.CIFRADO_ALGORITMO);
		  Cipher cipher;
		  try 
		  {
		   cipher = Cipher.getInstance(ValoresDefecto.CIFRADO_ALGORITMO);
		   cipher.init(Cipher.ENCRYPT_MODE, key);
		   campoCifrado = cipher.doFinal(sMsg.getBytes(ValoresDefecto.DEF_CODIFICACION));
		  } 
		  catch (Exception e) 
		  {
			  logger.error("ERROR: ocurri� un error al cifrar la cadena.");
			  //e.printStackTrace();
			  
		  }
		  
		  return new String(campoCifrado);
		
	}

	public static String descifra (String sMsg)
	{
		String sDescifrado = "";
		
		SecretKeySpec key = null;

		Cipher cipher;
		try 
		{
			key = new SecretKeySpec(ValoresDefecto.CIFRADO_LLAVE_SIMETRICA.getBytes(), ValoresDefecto.CIFRADO_ALGORITMO);
			cipher = Cipher.getInstance(ValoresDefecto.CIFRADO_ALGORITMO);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] datosDecifrados = cipher.doFinal(sMsg.getBytes(ValoresDefecto.DEF_CODIFICACION));
			sDescifrado = new String(datosDecifrados);
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			logger.error("ERROR: ocurri� un error al descifrar la cadena.");
		}
		  
		return sDescifrado;
	}
	
	public static void debugTrace(boolean bEnable, String sClass, String sMethod, String sMsg)
	{
		boolean bContrazas = true;
		
		
		if (bContrazas && bEnable)
		{
			System.out.println(timeStamp()+":["+sClass+"."+sMethod+"] "+sMsg);
		}
		
	}
	
	public static void debugTraceArrayList(boolean bEnable, String sClass, String sMethod, ArrayList<String> result)
	{
		if (bEnable)
		{
			for (int j=0;j<result.size();j++)
			{
				System.out.println("["+sClass+"."+sMethod+"] |"+result.get(j)+"|");
			}
		}
	}
	
	public static FacesMessage pfmsgInfo(String sMsg)
	{
		return new FacesMessage(sMsg);
	}
	
	public static FacesMessage pfmsgWarning(String sMsg)
	{
		return new FacesMessage(FacesMessage.SEVERITY_WARN, sMsg,null);
	}
	
	public static FacesMessage pfmsgError(String sMsg)
	{
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
	}
	
	public static FacesMessage pfmsgFatal(String sMsg)
	{
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
	}

	public static String fechaDeHoy (boolean bFormato)
	{


		Date fechaHoy = new Date();
	
		String sHoy = "";
		
		if (bFormato)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			sHoy = format.format(fechaHoy);
			
		}
		else
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			sHoy = format.format(fechaHoy);
		}
		
		
		//logger.debug(sHoy);
		
		return sHoy;
	}
	
	public static String fechaDeHoyN34 (boolean bFormato)
	{


		Date fechaHoy = new Date();
	
		String sHoy = "";
		
		if (bFormato)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
			sHoy = format.format(fechaHoy);
			
		}
		else
		{
			SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
			sHoy = format.format(fechaHoy);
		}
		
		
		//logger.debug(sHoy);
		
		return sHoy;
	}
	
	public static String primeroDeMes ()
	{


		Date fechaHoy = new Date();
	
		String sFecha = "";
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		sFecha = format.format(fechaHoy) + "01";
		
		//sFecha = sFecha.substring(0, 6) + "01";
		
		
		
		//logger.debug(sFecha);
		
		return sFecha;
	}
	
	public static String abreviaturaMes(int iMes)
	{
		String sMes = ""; 
		
		switch (iMes) 
		{
		case 1:
			sMes = "Ene";
			break;
		case 2:
			sMes = "Feb";
			break;
		case 3:
			sMes = "Mar";
			break;
		case 4:
			sMes = "Abr";
			break;
		case 5:
			sMes = "May";
			break;
		case 6:
			sMes = "Jun";
			break;
		case 7:
			sMes = "Jul";
			break;
		case 8:
			sMes = "Ago";
			break;
		case 9:
			sMes = "Sep";
			break;
		case 10:
			sMes = "Oct";
			break;
		case 11:
			sMes = "Nov";
			break;
		case 12:
			sMes = "Dec";
			break;
		default:
			sMes = "#";
			break;
		}		
		return sMes;
	}
	
	public static ArrayList<RangoAnual> rangoAnual()
	{

		ArrayList<RangoAnual> rango = new ArrayList<RangoAnual>();

		Date fechaHoy = new Date();
	
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String sFecha = format.format(fechaHoy);
		
		int iA�o = Integer.parseInt(sFecha.substring(0, 4)) - 1;
		int iMes = Integer.parseInt(sFecha.substring(4,6));
		
		for(int i=0; i<12; i++)
		{
			
			RangoAnual resumen = new RangoAnual(
					abreviaturaMes(iMes)+((iMes==1)?"-"+iA�o:""),
					iA�o+""+((iMes<10)?"0"+iMes:iMes)+"01");
			
			rango.add(resumen);
			iMes++;
			if (iMes > 12)
			{
				iA�o++;
				iMes = 1;
			}
		}
		
		return rango;
	}
	
	public static String aFechaN34 (String sFecha)
	{
		return  sFecha.substring(6, sFecha.length()) + sFecha.substring(4,6) + sFecha.substring(2,4);
	}
	
	public static String sumaDiasFecha (String sFecha,int iDias)
	{
		String sFechaNueva = "";
		
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date dtFecha = format.parse(sFecha);
			Calendar cal = Calendar.getInstance();
	        cal.setTime(dtFecha);
	        cal.add(Calendar.DATE, iDias); //minus number would decrement the days
	        sFechaNueva = format.format(cal.getTime());
		} 
		catch (ParseException e) 
		{
			sFechaNueva = "#";
		} 
        
		return sFechaNueva;
	}
	
	public static String timeStamp()
	{
		Date fechaHoy = new Date();
	
		String sAhora = "";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		sAhora = format.format(fechaHoy);
		
		return sAhora;
	}
	
	
	public static String duracion(long liInicio, long liFin)
	{
	
		//logger.debug("liInicio:"+liInicio);
		//logger.debug("liFin:"+liFin);
		long liTemporal = liFin - liInicio;
		
		//logger.debug("liTemporal:"+liTemporal);
		
		String sDuracion = "";
		
		
		if (liTemporal > 999)
		{
			sDuracion = (liTemporal%1000)+"ms";
					
			liTemporal = (liTemporal/1000);
			
			if (liTemporal > 59)
			{
				sDuracion = (liTemporal%60)+"s"+sDuracion;
				
				liTemporal = (liTemporal/60);
				
				if (liTemporal > 59)
				{
					sDuracion = (liTemporal%60)+"m"+sDuracion;
					
					liTemporal = (liTemporal/60);
					
					if (liTemporal>23)
					{
						sDuracion = (liTemporal%24)+"h"+sDuracion;
						
						liTemporal = (liTemporal/24);
						
						if (liTemporal>364)
						{
							sDuracion = (liTemporal%365)+"d"+sDuracion;
							
							liTemporal = (liTemporal/365);
						}
						else
						{
							sDuracion = liTemporal+"d"+sDuracion;
						}
					}
					else
					{
						sDuracion = liTemporal+"h"+sDuracion;
					}
						
						
						
				}
				else
				{
					sDuracion = liTemporal+"m"+sDuracion;
				}
			}
			else
			{
				sDuracion = liTemporal+"s"+sDuracion;
			}
			
			
		}
		else
		{
			sDuracion = liTemporal+"ms";
		}
		
		
	
		//logger.debug("sAhora:|{}|",sAhora);
		
		return sDuracion;
	}

	public static boolean closeResultSet ( ResultSet rs)
	{
		boolean bSalida = true;
		if (rs != null) 
		{
			try 
			{
				rs.close();
			} 
			catch (SQLException sqlEx) 
			{
				bSalida = false;
				logger.error("ERROR: la conex�on se cerr� de forma inesperada.");
			}
		}
		return bSalida;
		
	}

	public static boolean closeStatement(Statement stmt)
	{
		boolean bSalida = true;
		if (stmt != null) 
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException sqlEx) 
			{
				bSalida = false;
				logger.error("ERROR: la conex�on se cerr� de forma inesperada.");
			}
		}
		return bSalida;
		
	}

	public static void inicializarDirectorios ()
	{
		
		logger.debug("DEF_PATH_LOGS:|"+ValoresDefecto.DEF_PATH_LOGS+"|");
		logger.debug("DEF_PATH_BACKUP_RECIBIDOS:|"+ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS+"|");
		logger.debug("DEF_PATH_BACKUP_GENERADOS:|"+ValoresDefecto.DEF_PATH_BACKUP_GENERADOS+"|");
		logger.debug("DEF_PATH_BACKUP_CARGADOS:   |"+ValoresDefecto.DEF_PATH_BACKUP_CARGADOS+"|");
		logger.debug("DEF_PATH_BACKUP_DESCARGADOS:|"+ValoresDefecto.DEF_PATH_BACKUP_DESCARGADOS+"|");
		
		File dirLogs = new File(ValoresDefecto.DEF_PATH_LOGS);
		
		if(!dirLogs.exists())
		{
			dirLogs.mkdir(); 
		}
		
		File dirArchivos = new File(ValoresDefecto.DEF_FILE_PATH);
		
		if(!dirArchivos.exists())
		{
			dirArchivos.mkdir(); 
		}

		File dirVolcados = new File(ValoresDefecto.DEF_DUMP_PATH);
		
		if(!dirVolcados.exists())
		{
			dirVolcados.mkdir(); 
		}
	
		File dirRecibidos = new File(ValoresDefecto.DEF_PATH_BACKUP_RECIBIDOS);

		if(!dirRecibidos.exists())
		{
			dirRecibidos.mkdir(); 
		}
		
		File dirGenerados = new File(ValoresDefecto.DEF_PATH_BACKUP_GENERADOS);

		if(!dirGenerados.exists())
		{
			dirGenerados.mkdir(); 
		}
		
		File dirInformes = new File(ValoresDefecto.DEF_PATH_BACKUP_INFORMES);

		if(!dirInformes.exists())
		{
			dirInformes.mkdir(); 
		}
		
		File dirCargados = new File(ValoresDefecto.DEF_PATH_BACKUP_CARGADOS);

		if(!dirCargados.exists())
		{
			dirCargados.mkdir(); 
		}
		
		File dirDescargados = new File(ValoresDefecto.DEF_PATH_BACKUP_DESCARGADOS);

		if(!dirDescargados.exists())
		{
			dirDescargados.mkdir(); 
		}
		
	}
	
	public static void standardIO2File(String sNombre)
	{
		String sArchivo = sNombre;
		//redirige la salida estandar a un fichero. Alternativa a log4j. Peor rendimiento.

		logger.debug("user.dir:|{}|",System.getProperty("user.dir"));
		logger.debug("ValoresDefecto.DEF_EXEC_PATH:|{}|",ValoresDefecto.DEF_EXEC_PATH);
		
		 if(sArchivo.equals(""))
		 {
			 sArchivo=ValoresDefecto.DEF_EXEC_PATH+"javalog.txt";
         }
 
        try 
        {
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(sArchivo),true)),true);
 
            System.setOut(ps);
            System.setErr(ps);
        } 
        catch (FileNotFoundException ex) 
        {
            logger.error("Error al acceder al archivo '{}'",sArchivo);
        }
 
    }
	
	public static String compruebaCodigoPago(boolean bCodDevolucion, String sTipoPago)
	{
		String sTipo = bCodDevolucion ? "5"+ sTipoPago : sTipoPago;
		
		//logger.debug("Codigo de pago:|{}|",sTipo);
		
		
		return sTipo;
	}
	
	public static String compruebaCodigoAlfa(String sCodigo)
	{
			
		String sCodigoRevisado = sCodigo;
		
		if (sCodigoRevisado.equals(""))
		{
			sCodigoRevisado = "#";
		}
		
		//logger.debug("sCodigoRevisado:|{}|",sCodigoRevisado);
		
		return sCodigoRevisado;
	}
	public static String compruebaCodigoNum(String sCodigo)
	{
			
		String sCodigoRevisado = sCodigo;
		
		if (sCodigoRevisado.equals(""))
		{
			sCodigoRevisado = "0";
		}
		
		//logger.debug("sCodigoRevisado:|{}|",sCodigoRevisado);
		
		return sCodigoRevisado;
	}
	
	public static String cortaDecimales(String sImporte)
	{
		logger.debug("sImporte:|"+sImporte+"|");
		
		String sResultado = sImporte;
		
		String sSeparador = "";
		
		if (sImporte.contains("."))
		{
			sSeparador = "\\.";
		}
		else if (sImporte.contains(","))
		{
			sSeparador = ",";
		}
		
		if (!sSeparador.equals(""))
		{
			String[] arrayimporte = sImporte.split(sSeparador);
			String sEuros = arrayimporte[0];
			String sCentimos = arrayimporte[1];
			if (sCentimos.length() < ValoresDefecto.DECIMALES)
			{
				sCentimos = sCentimos +"0";
			}
			else if (sCentimos.length() > ValoresDefecto.DECIMALES)
			{
				sCentimos = sCentimos.substring(0, ValoresDefecto.DECIMALES);
			}
			
			logger.debug("sEuros:|"+sEuros+"|");
			logger.debug("sCentimos:|"+sCentimos+"|");
		
			sResultado = sEuros + sCentimos;
		}
		logger.debug("sResultado:|"+sResultado+"|");
		return sResultado;
	}
	
	public static String compruebaRecargo(String sRecargo)
	{

		String sRecargoReal = "#";
		
		String sSeparador = "";
		
		//logger.debug("sImporte:|{}|",sImporte);
		
		if (sRecargo.matches("-?[\\d]+([\\.|,][\\d][\\d]*)?$"))
		{
			
			if (sRecargo.contains("."))
			{
				sSeparador = "\\.";
			}
			else if (sRecargo.contains(","))
			{
				sSeparador = ",";
			}
			
			if (!sSeparador.equals(""))
			{
				String[] arrayimporte = sRecargo.split(sSeparador);
				String sEuros = arrayimporte[0];
				String sCentimos = arrayimporte[1];
				if (sCentimos.length() < ValoresDefecto.DECIMALES)
				{
					sCentimos = sCentimos +"0";
				}
				else if (sCentimos.length() > ValoresDefecto.DECIMALES)
				{
					sCentimos = sCentimos.substring(0, ValoresDefecto.DECIMALES);
				}
				//logger.debug("sEuros:|{}|",sEuros);
				//logger.debug("sCentimos:|{}|",sCentimos);
			
				sRecargoReal = sEuros + sCentimos +"0000";
			}
			else
			{
				sRecargoReal = sRecargo+"000000";
			}

		}
		
		if (sRecargo.isEmpty() 
				|| sRecargoReal.equals("0000000")
				|| sRecargoReal.equals("-0000000") 
				|| sRecargoReal.equals("-00000"))
		{
			sRecargoReal= "0";
		}
		
		//logger.debug("sRecargoReal:|{}|",sRecargoReal);
		
		return sRecargoReal;
	}
	
	public static long redondeaRecargo(long liRecargo)
	{
		long liRedondeo = 0;
		
		if (liRecargo != 0)
		{
			liRedondeo = liRecargo/10000;
			//BOE-A-1998-29216 - Art�culo 11. Redondeo.
			if ((liRecargo%10000) >4999)
			{
				liRedondeo = liRedondeo + 1;
			}
		}
		
		return liRedondeo;
	}
	
	public static String recuperaRecargo(boolean bNegativo, String sRecargo)
	{
		String sRecargoReal = "0";
		
		String sEuros = "0";
		String sCentimos = "00";
		
		logger.debug("sRecargo:|"+sRecargo+"|");
		
		logger.debug("sRecargo.length():|"+sRecargo.length()+"|");
		
		if (sRecargo.length()>6)
		{
			sEuros = sRecargo.substring(0, sRecargo.length()-6);
			sCentimos = sRecargo.substring(sRecargo.length()-6,sRecargo.length()-4);
		}
		else if (sRecargo.length()>5)
		{
			sCentimos = sRecargo.substring(0,2);
		}
		else if (sRecargo.length()>4)
		{
			sCentimos = sRecargo.substring(0,1);
		}
		
		//BOE-A-1998-29216 - Art�culo 11. Redondeo.
		if ((Long.parseLong(sRecargo)%10000) >4999)
		{
			int iCentimos = Integer.parseInt(sCentimos)+1;
			
			if (iCentimos > 99)
			{
				sEuros = Integer.toString(Integer.parseInt(sEuros) + 1);
				iCentimos = 0;
			}

			sCentimos = Integer.toString(iCentimos);
		}

		if (sCentimos.length() == 1)
		{
			sCentimos = "0" + sCentimos;
		}


		logger.debug("sEuros:|"+sEuros+"|");
		logger.debug("sCentimos:|"+sCentimos+"|");
		
		if (!sEuros.equals("0") || !sCentimos.equals("00"))
		{
			sRecargoReal = bNegativo ? "-" +sEuros + "," + sCentimos : sEuros + "," + sCentimos;
		}

		logger.debug("sRecargoReal:|"+sRecargoReal+"|");

		return sRecargoReal;
	}

	public static String compruebaImporte(String sImporte)
	{

		String sImporteReal = "#";
		
		String sSeparador = "";
		
		//logger.debug("sImporte:|{}|",sImporte);
		
		if (sImporte.matches("-?[\\d]+([\\.|,][\\d][\\d]*)?$"))
		{
			
			if (sImporte.contains("."))
			{
				sSeparador = "\\.";
			}
			else if (sImporte.contains(","))
			{
				sSeparador = ",";
			}
			
			if (!sSeparador.equals(""))
			{
				String[] arrayimporte = sImporte.split(sSeparador);
				String sEuros = arrayimporte[0];
				String sCentimos = arrayimporte[1];
				if (sCentimos.length() < ValoresDefecto.DECIMALES)
				{
					sCentimos = sCentimos +"0";
				}
				else if (sCentimos.length() > ValoresDefecto.DECIMALES)
				{
					sCentimos = sCentimos.substring(0, ValoresDefecto.DECIMALES);
				}
				//logger.debug("sEuros:|{}|",sEuros);
				//logger.debug("sCentimos:|{}|",sCentimos);
			
				sImporteReal = sEuros + sCentimos;
			}
			else
			{
				sImporteReal = sImporte+"00";
			}

		}
		
		if (sImporte.isEmpty() 
				|| sImporteReal.equals("000")
				|| sImporteReal.equals("-000") 
				|| sImporteReal.equals("-0"))
		{
			sImporteReal= "0";
		}
		
		//logger.debug("sImporteReal:|{}|",sImporteReal);
		
		return sImporteReal;
	}
	
	public static ImporteDevolucion separaImporteDevolucion(String sImporte)
	{
		
		//logger.debug("sImporte:|{}|",sImporte);
	
		return new ImporteDevolucion(sImporte.startsWith("-"),sImporte.replaceFirst("-", ""));
	}
	
	public static String compruebaImporteDevolucion(boolean bNegativo, String sImporte)
	{
		String sImporteReal = "#";
		
		if (sImporte.matches("-?[\\d]+([\\.|,][\\d]{2})?$"))
		{
		
			
			sImporteReal = sImporte.replaceFirst("-", "");
		
			if (sImporte.length()>3)
			{
				String sEuros = sImporteReal.substring(0, sImporte.length()-3);
				String sCentimos = sImporteReal.substring(sImporte.length()-2,sImporte.length());
		
				//logger.debug("sEuros:|{}|",sEuros);
				//logger.debug("sCentimos:|{}|",sCentimos);
			
				sImporteReal = bNegativo ? "-"+ sEuros + sCentimos : sEuros + sCentimos;
			}
			
			if (sImporte.isEmpty() 
				|| sImporteReal.equals("000")
				|| sImporteReal.equals("-000") 
				|| sImporteReal.equals("-0"))
			{
				sImporteReal= "0";
			}
		
			//logger.debug("sImporteReal:|{}|",sImporteReal);
		}

		
		return sImporteReal;
	}
	
	public static String invierteSigno(String sImporte)
	{
		String sNuevoImporte = "";
		
		if (Long.parseLong(sImporte) == 0)
		{
			sNuevoImporte = sImporte;
		}
		else if (Long.parseLong(sImporte) < 0)
		{
			sNuevoImporte = sImporte.substring(1);
		}
		else
		{
			sNuevoImporte = "-" + sImporte;
		}
		
		return sNuevoImporte;
	}
	
    public static boolean esAlfanumerico (String sCampo) 
    {
    	boolean bAlfanumerico = false;
    	
		try
		{
			Integer.parseInt(sCampo);
		}
		catch(NumberFormatException nfe)
		{
			 bAlfanumerico = true;
		}
    	
    	return bAlfanumerico;
    }

	public static String compruebaFecha(String sFecha)
	{

		//logger.debug("sFecha:|{}|",sFecha);
		
		String sFechaFormateada = "";
		
		if (sFecha.matches("[\\d]{2}[/][\\d]{2}[/][\\d]{4}$"))
		{
			
			String[] arrayfecha = sFecha.split("/");
			String sDia = arrayfecha[0];
			String sMes = arrayfecha[1];
			String sA�o = arrayfecha[2];

			//logger.debug("sDia:|{}|",sDia);
			//logger.debug("sMes:|{}|",sMes);
			//logger.debug("sA�o:|{}|",sA�o);
	
			
			sFechaFormateada = sA�o+sMes+sDia;
		
			//logger.debug("sFechaFormateada:|{}|",sFechaFormateada);
		
			try 
			{
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				formatter.setLenient(false);
				Date myDate = formatter.parse(sFechaFormateada);
				
				logger.debug("Fecha reibida:|"+myDate+"|");
			} 
			catch (ParseException e) 
			{
				sFechaFormateada = "#";
			} 
		}
		else
		{
			sFechaFormateada = "0";
		}
		
		return sFechaFormateada;
	}
	
    public static String calculaDCIBAN(String sPais, String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT) 
    {
        String sValorCompleto = sNUCCEN+sNUCCOF+sNUCCDI+sNUCCNT+(sPais.charAt(0)-55)+(sPais.charAt(1)-55)+"00";
        
        BigInteger biValorCompleto = new BigInteger(sValorCompleto);
       
        BigInteger biISO7604 = new BigInteger("97");

        int iDCIBAN = 98 - biValorCompleto.mod(biISO7604).intValue();
        
        logger.debug("iDCIBAN:"+iDCIBAN);
        
        String sPrefijo = (iDCIBAN > 9) ? "":"0";
        
        String sDCIBAN =  sPrefijo+iDCIBAN;
        
        logger.debug("sDCIBAN:"+sDCIBAN);
        
        
        logger.debug("SEPA:"+sPais+sDCIBAN+sNUCCEN+sNUCCOF+sNUCCDI+sNUCCNT);
    	
    	return sDCIBAN;
    }
	
    public static String calculaDC(String sNUCCEN, String sNUCCOF, String sNUCCNT) 
    {
    	String sDC = "";
        int iTotal = 0;
        int iProducto = 0;
        String sCifra1 = "";
        String sCifra2 = "";
        

        
        logger.debug("sNUCCEN:|"+sNUCCEN+"|");
        logger.debug("sNUCCOF:|"+sNUCCOF+"|");
        //logger.debug("sNUCCDI:|"+sNUCCDI+"|");
        logger.debug("sNUCCNT:|"+sNUCCNT+"|");

        
        if ((sNUCCEN.length() == 4)
        	&& (sNUCCOF.length() == 4)
        	&& (sNUCCNT.length() == 10))
        {
            /*Primer d�gito.*/
            for (int i = 0; i < 4; i++) 
            {
                if (i==0)
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*4;
                }
                else if (i==1)
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*8;
                }
                else if (i==2)
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*5;
                }
                else 
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*10;
                }
                iTotal = iTotal + iProducto;
            }
            for (int j = 0; j < 4; j++) 
            {
                if (j==0)
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*9;
                }
                else if (j==1)
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*7;
                }
                else if (j==2)
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*3;
                }
                else 
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*6;
                }
                iTotal = iTotal + iProducto;
            }
     
            iProducto = 11 - iTotal % 11;
            
            if (iProducto == 10) 
            {
                sCifra1 = "1";
            } 
            else if (iProducto == 11) 
            {
                sCifra1 = "0";
            } 
            else 
            {
                sCifra1 = String.valueOf(iProducto);
            }
            
            iTotal=0;
     
            /*Segundo d�gito.*/
            for (int k=0; k<10; k++)
            {
                if (k==0)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*1;
                }
                else if (k==1)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*2;
                }
                else if (k==2)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*4;
                }
                else if (k==3)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*8;
                }
                else if (k==4)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*5;
                }
                else if (k==5)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*10;
                }
                else if (k==6)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*9;
                }
                else if (k==7)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*7;
                }
                else if (k==8)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*3;
                }
                else 
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*6;
                }
                iTotal = iTotal + iProducto;
            }
            
            iProducto = 11 - iTotal % 11;
            
            if (iProducto == 10) 
            {
                sCifra2 = "1";
            } 
            else if (iProducto == 11) 
            {
                sCifra2 = "0";
            } 
            else 
            {
                sCifra2 = String.valueOf(iProducto);
            }

            sDC=sCifra1+sCifra2;
        }
        
        logger.debug("sNUCCDI:|"+sDC+"|");
        
        return sDC;
    }
	
    public static boolean compruebaCC(String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT) 
    {
    	boolean bOK = false;
    	
    	if (sNUCCDI.length() == 2)
    	{
    		bOK = calculaDC(sNUCCEN,sNUCCOF,sNUCCNT).equals(sNUCCDI);
    	}
    	
        return bOK;
    }
    
    public static boolean compruebaCIF(String sNUDCOM) 
    {

        boolean bResultado = false;

        try 
        {
            String sCIF = sNUDCOM.trim();

            int iSuma = 0;

            int iCodigoControl = 0;
 
            String sLetrasCIF = "ABCDEFGHJKLMNPQRSUVW";
            
            String sPrimeraLetra = sCIF.substring(0, 1);
            
            
            
            if (!(sCIF.length() == 10))
            {
            	bResultado =  false;
            }
            if (!sCIF.matches("[A-Z][0-9]{9}$"))
            {
            	bResultado =  false;
            }
            if (sLetrasCIF.indexOf(sPrimeraLetra) < 0)
            {
            	bResultado =  false;
            }
            else
            {
                iSuma = iSuma + Integer.parseInt(sCIF.substring(2, 3)) + Integer.parseInt(sCIF.substring(4, 5))
                        + Integer.parseInt(sCIF.substring(6, 7));

                for (int i = 1; i < 8; i = i + 2) 
                {

                    int iValor = (Integer.parseInt(sCIF.substring(i, i + 1)) * 2);

                    if (iValor < 10)
                    {
                        iSuma = iSuma + iValor;
                    }
                    else 
                    {
                        iSuma = iSuma + (Integer.parseInt(String.valueOf(iValor).substring(0, 1)))
                                + (Integer.parseInt(String.valueOf(iValor).substring(1, 2)));
                    }
                }

                iCodigoControl = ((10 - (iSuma % 10)) % 10);

                String sLetraSociedad = "KLMNPQRSW";
                
                String sUltimaLetra = sCIF.substring(8, 9);
                
                if (sLetraSociedad.indexOf(sPrimeraLetra) >= 0) 
                {
                    byte[] ascii = new byte[1];

                    if (iCodigoControl == 0)
                    {
                        iCodigoControl = 10;
                    }
                    iCodigoControl = iCodigoControl + 64;
                    ascii[0] = (Integer.valueOf(iCodigoControl)).byteValue();


                    
                    bResultado = (sUltimaLetra.equals(new String(ascii)));
                } 
                else 
                {
                	String sNumsCIF = "0123456789";
                    bResultado = (iCodigoControl == sNumsCIF.indexOf(sUltimaLetra));
                }
            }


        } 
        catch (Exception e) 
        {
            bResultado = false;
        }
        return bResultado;
    }
    
	public static boolean compruebaCorreo(String sCorreo)
	{
		return sCorreo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	
	public static String recuperaImporte(boolean bNegativo, String sImporte)
	{
		String sImporteReal = "0";
		String sEuros = "0";
		String sCentimos = "00";
		
		logger.debug("sImporte:|"+sImporte+"|");
		
		if (sImporte.length()>2)
		{
			sEuros = sImporte.substring(0, sImporte.length()-2);
			sCentimos = sImporte.substring(sImporte.length()-2,sImporte.length());

		}
		else if (sImporte.length() == 2)
		{
			sCentimos = sImporte;
		}
		else if (sImporte.length() == 1)
		{
			sCentimos = "0" + sImporte;
		}


		logger.debug("sEuros:|"+sEuros+"|");
		logger.debug("sCentimos:|"+sCentimos+"|");
		
		if (!sEuros.equals("0") || !sCentimos.equals("00"))
		{
			sImporteReal = bNegativo ? "-" +sEuros + "," + sCentimos : sEuros + "," + sCentimos;
		}

		logger.debug("sImporteReal:|"+sImporteReal+"|");

		return sImporteReal;
	}
	
	public static String recuperaIngreso(String sValor)
	{
		String sIngreso = "SI";
		
		logger.debug("sValor:|"+sValor+"|");
		
		if (sValor.equals(ValoresDefecto.CAMPO_NUME_SIN_INFORMAR))
		{
			sIngreso = "NO";
		}

		return sIngreso;
	}
	
	public static String recuperaFecha(String sFecha)
	{
		logger.debug("sFecha:|"+sFecha+"|");		
		
		String sFechaFormateada = "";
		
		if (!sFecha.equals("0")
			&&!sFecha.isEmpty())
		{
	        while (sFecha.length() < 8) 
	        {
	        	sFecha="0"+sFecha;
	        }
			
			String sA�o = sFecha.substring(0, 4);
			String sMes = sFecha.substring(4, 6);
			String sDia = sFecha.substring(6, 8);
			
		
			//logger.debug("sDia:|{}|",sDia);
			//logger.debug("sMes:|{}|",sMes);
			//logger.debug("sA�o:|{}|",sA�o);
			
			sFechaFormateada = sDia+"/"+sMes+"/"+sA�o;
		
			//logger.debug("sFechaFormateada:|{}|",sFechaFormateada);
		}
		
		return sFechaFormateada;
	}
	
	public static String recuperaCodigo(String sCodigo)
	{
		String sCodigoRevisado = sCodigo;
		
		if (sCodigoRevisado.equals("#"))
		{
			sCodigoRevisado = "";
		}
		
		//logger.debug("sCodigoRevisado:|{}|",sCodigoRevisado);
		
		return sCodigoRevisado;
	}
	
	public static String recuperaBit(String sCodigo)
	{
		String sDescripcion = "#";
		
		logger.debug("sCodigo:|"+sCodigo+"|");
		
		if (sCodigo.equals("false"))
		{
			sDescripcion = "No";
		}
		else if (sCodigo.equals("true"))
		{
			sDescripcion = "Si";
		}
		
		logger.debug("sDescripcion:|"+sDescripcion+"|");
		
		return sDescripcion;
	}
	
	public static Cuenta recuperaCuentaSEPA(String sCuentaSEPA)
	{
		String sPais = sCuentaSEPA.substring(0, 2);	
		String sDCIBAN = sCuentaSEPA.substring(2, 4);	
		String sNUCCEN = sCuentaSEPA.substring(4, 8);
		String sNUCCOF = sCuentaSEPA.substring(8, 12);
		String sNUCCDI = sCuentaSEPA.substring(12, 14);
		String sNUCCNT = sCuentaSEPA.substring(14);
		
		return new Cuenta(
				sPais,
				sDCIBAN,
				sNUCCEN,
				sNUCCOF,
				sNUCCDI,
				sNUCCNT,
				 "");
	}
	
	public static String compruebaISO20022(String sCampo)
	{
		
		String sCampoFormateado = "";
		
		sCampoFormateado = sCampo.replaceAll("[�]", "N");
		sCampoFormateado = sCampoFormateado.replaceAll("[�]", "n");
		sCampoFormateado = sCampoFormateado.replaceAll("[�]", "C");
		sCampoFormateado = sCampoFormateado.replaceAll("[�]", "c");
		sCampoFormateado = sCampoFormateado.replaceAll("[������]", "A");
		sCampoFormateado = sCampoFormateado.replaceAll("[������]", "a");
		sCampoFormateado = sCampoFormateado.replaceAll("[�]", "A");
		sCampoFormateado = sCampoFormateado.replaceAll("[�]", "a");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "E");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "e");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "I");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "i");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "O");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "o");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "U");
		sCampoFormateado = sCampoFormateado.replaceAll("[����]", "u");
		sCampoFormateado = sCampoFormateado.replaceAll("[^\\w/\\?\\-\\:()\\.\\,\\'\\+\\s]","?");
		
		//logger.debug("sCampo:|"+sCampo+"|");
		//logger.debug("sCampoFormateado:|"+sCampoFormateado+"|");
		
		return sCampoFormateado;
	}

}

//Autor: Francisco Valverde Manj�n