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
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.application.FacesMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.types.ImporteDevolucion;

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
			  logger.error("ERROR: ocurrió un error al cifrar la cadena.");
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
			logger.error("ERROR: ocurrió un error al descifrar la cadena.");
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
				logger.error("ERROR: la conexíon se cerró de forma inesperada.");
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
				logger.error("ERROR: la conexíon se cerró de forma inesperada.");
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
			//logger.debug("sEuros:|{}|",sEuros);
			//logger.debug("sCentimos:|{}|",sCentimos);
		
			sResultado = sEuros + sCentimos;
		}
		
		return sResultado;
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
		else if (sImporte.equals(""))
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
			else if (sImporte.equals(""))
			{
				sImporteReal= "0";
			}
		
			//logger.debug("sImporteReal:|{}|",sImporteReal);
		}

		
		return sImporteReal;
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
			String sAño = arrayfecha[2];

			//logger.debug("sDia:|{}|",sDia);
			//logger.debug("sMes:|{}|",sMes);
			//logger.debug("sAño:|{}|",sAño);
	
			
			sFechaFormateada = sAño+sMes+sDia;
		
			//logger.debug("sFechaFormateada:|{}|",sFechaFormateada);
		
			try 
			{
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				formatter.setLenient(false);
				Date myDate = formatter.parse(sFechaFormateada);
				
				logger.debug("Fecha reibida:|{}|",myDate);
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
            /*Primer dígito.*/
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
     
            /*Segundo dígito.*/
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
		
		if (sImporte.length()>2)
		{
			String sEuros = sImporte.substring(0, sImporte.length()-2);
			String sCentimos = sImporte.substring(sImporte.length()-2,sImporte.length());

			//logger.debug("sEuros:|{}|",sEuros);
			//logger.debug("sCentimos:|{}|",sCentimos);
	
			sImporteReal = bNegativo ? "-"+ sEuros + "," + sCentimos : sEuros + "," + sCentimos;
		}

		//logger.debug("sImporteReal:|{}|",sImporteReal);

		return sImporteReal;
	}
	
	public static String recuperaFecha(String sFecha)
	{
		//logger.debug("sFecha:|{}|",sFecha);		
		
		String sFechaFormateada = "";
		
		if (!sFecha.equals("0"))
		{
			String sAño = sFecha.substring(0, 4);
			String sMes = sFecha.substring(4, 6);
			String sDia = sFecha.substring(6, 8);
			
		
			//logger.debug("sDia:|{}|",sDia);
			//logger.debug("sMes:|{}|",sMes);
			//logger.debug("sAño:|{}|",sAño);
			
			sFechaFormateada = sDia+"/"+sMes+"/"+sAño;
		
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
}
