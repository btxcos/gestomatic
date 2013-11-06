package com.provisiones.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.dal.qm.usuarios.QMUsuarios;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ResultadosTabla;
import com.provisiones.types.usuarios.Usuario;


public class TestActivos 
{
	//private static Logger logger = LoggerFactory.getLogger(TestActivos.class.getName());
	
	private ArrayList<ResultadosTabla> tabla = new ArrayList<ResultadosTabla>(); 
	
	public static void main(String[] args) throws IOException 
	{
		
	
		//ResultadosTabla datos = new ResultadosTabla("A","B");
		
		
		//Utils.debugTrace(true, "TEST", "TEST", "|"+getTabla().size()+"|");
		
		String sMsg = "1234";
		System.out.println(sMsg);
		System.out.println(sMsg.length());
		
		/*System.out.println("|"+Utils.timeStamp()+"|");
		sMsg = Utils.cifra(sMsg);
		
		System.out.println("|"+Utils.timeStamp()+"|");
		System.out.println("|"+sMsg+"|");
		System.out.println(sMsg.length());
		System.out.println("|"+Utils.timeStamp()+"|");
		System.out.println(Utils.descifra(sMsg));
		System.out.println("|"+Utils.timeStamp()+"|");*/
		
		
		/*Usuario unUsuario = new Usuario("admin", sMsg,"","","","","","","1","");
		
		String sUsuarioID = Long.toString(QMUsuarios.addUsuario(unUsuario));
		
		
		System.out.println("|"+QMUsuarios.getUsuario(sUsuarioID).getsPassword().length()+"|");*/
		
		
		Connection conn = null;
		
		conn = ConnectionManager.openDBConnection();
		
		
		
		String sConexion = conn.toString();

		
		System.out.println("|"+sConexion+"|");
		
		Connection conn2 = null;
		
		conn2 = ConnectionManager.openDBConnection();
		
		
		String sConexion2 = conn2.toString();

		
		System.out.println("|"+sConexion2+"|");
		
		/*try 
		{
			Connection conn2 = DriverManager.getConnection(sConexion);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

				
		//ConnectionManager.closeDBConnection(conn);
		//ConnectionManager.closeDBConnection(conn2);
		
		
		
		



	}

	public ArrayList<ResultadosTabla> getTabla() {
		return tabla;
	}

	public void setTabla(ArrayList<ResultadosTabla> tabla) {
		this.tabla = tabla;
	}
}
