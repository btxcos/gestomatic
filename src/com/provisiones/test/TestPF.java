package com.provisiones.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Timestamp;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.QMDatosActivos;
import com.provisiones.misc.Parser;
import com.provisiones.misc.Utils;
import com.provisiones.types.DatosActivo;

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
import org.primefaces.event.FileUploadEvent;  
import org.primefaces.model.UploadedFile;  

public class TestPF implements Serializable{  
	  
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 5280073149818806999L;
	static String sClassName = TestPF.class.getName();
	static boolean bEnable = true;

	String sCampo = "un dato";
	
	
	  
    public String getsCampo() {
		return sCampo;
	}


	public void setsCampo(String sCampo) {
		this.sCampo = sCampo;
	}


	public void handleFileUpload(FileUploadEvent event)throws IOException 
    {
			guardarFichero(event);
			tablas(event.getFile().getFileName());
			//if (f1.renameTo(f2)) --renombrar el fichero leido y ya en base de datos
			setsCampo("El archivo " + event.getFile().getFileName() + " ha subido correctamente.");
	        FacesMessage msg = new FacesMessage("Correcto!", event.getFile().getFileName() + " ha subido correctamente.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}  


	public static void guardarFichero(FileUploadEvent event) throws IOException 
	{
        UploadedFile file = event.getFile();
        String fileName = file.getFileName();
        //long fileSize = file.getSize();
        InputStream is = file.getInputstream();
        OutputStream out = new FileOutputStream("C:\\"+fileName);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0)
            out.write(buf, 0, len);
        is.close();
        out.close();
	
	}
	
	
	public static void tablas(String sNombre) throws IOException 
	{
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();
		
		com.provisiones.dal.ConnectionManager.CloseDBConnection(conn);

		com.provisiones.misc.Utils.debugTrace(true, sClassName, "main", "Conexion Realizada");
		File archivo = new File ("C:\\"+sNombre);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = "";
		int i = 26;
		String aChar = new Character((char)i).toString();
		int contador=0;
		Utils.standardIO2File("");//Salida por fichero de texto
		java.util.Date date= new java.util.Date();
		
		System.out.println("Inicio: " + new Timestamp(date.getTime()));
		
		String sCOACES = "1";

		while((linea=br.readLine())!=null)
        {
			contador++;
    		if (linea.equals(aChar))
    			System.out.println("Lectura finalizada!");
    		else if (linea.length()< 881 )
    			System.out.println("Error en linea "+contador);
    		else
    		{
    			DatosActivo activo = Parser.leerActivo(linea);
    			QMDatosActivos.addDatosActivo(activo);
    		}
            
        }
		
		System.out.println("|"+QMDatosActivos.getDatosActivo(sCOACES).getNOVIAS()+"|");
		System.out.println("|"+Parser.escribirCierre("903845","20130721").length()+"|");
		System.out.println("Fin: " + new Timestamp(date.getTime()));
		
		br.close();				
	}
  
 
} 
