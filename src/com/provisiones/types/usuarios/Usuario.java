package com.provisiones.types.usuarios;

public class Usuario 
{
	private String sLogin = "";
	private String sPassword = "";
	private String sNombre = "";
	private String sApellido1 = "";
	private String sApellido2 = "";	
	private String sContacto = "";
	private String sFechaAlta = "";
	private String sFechaModificacion = "";
	private String sTipoUsuario = "";	
	private String sActivo = "";

	//Constructor de clase
	
	public Usuario(String sLogin, String sPassword, String sNombre,
			String sApellido1, String sApellido2, String sContacto,
			String sFechaAlta, String sFechaModificacion, String sTipoUsuario,
			String sActivo) {
		super();
		this.sLogin = sLogin;
		this.sPassword = sPassword;
		this.sNombre = sNombre;
		this.sApellido1 = sApellido1;
		this.sApellido2 = sApellido2;
		this.sContacto = sContacto;
		this.sFechaAlta = sFechaAlta;
		this.sFechaModificacion = sFechaModificacion;
		this.sTipoUsuario = sTipoUsuario;
		this.sActivo = sActivo;
	}

	//Métodos de acceso

	public String getsLogin() {
		return sLogin;
	}


	public void setsLogin(String sLogin) {
		this.sLogin = sLogin;
	}


	public String getsPassword() {
		return sPassword;
	}


	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}


	public String getsNombre() {
		return sNombre;
	}


	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}


	public String getsApellido1() {
		return sApellido1;
	}


	public void setsApellido1(String sApellido1) {
		this.sApellido1 = sApellido1;
	}


	public String getsApellido2() {
		return sApellido2;
	}


	public void setsApellido2(String sApellido2) {
		this.sApellido2 = sApellido2;
	}


	public String getsContacto() {
		return sContacto;
	}


	public void setsContacto(String sContacto) {
		this.sContacto = sContacto;
	}


	public String getsFechaAlta() {
		return sFechaAlta;
	}


	public void setsFechaAlta(String sFechaAlta) {
		this.sFechaAlta = sFechaAlta;
	}


	public String getsFechaModificacion() {
		return sFechaModificacion;
	}


	public void setsFechaModificacion(String sFechaModificacion) {
		this.sFechaModificacion = sFechaModificacion;
	}


	public String getsTipoUsuario() {
		return sTipoUsuario;
	}


	public void setsTipoUsuario(String sTipoUsuario) {
		this.sTipoUsuario = sTipoUsuario;
	}


	public String getsActivo() {
		return sActivo;
	}


	public void setsActivo(String sActivo) {
		this.sActivo = sActivo;
	}
	
	
	

}
