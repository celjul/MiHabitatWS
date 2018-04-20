package com.bstmexico.mihabitat_ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tdepartamentos")
public class Departamentos {

	@Id
	@Column(name="NIdDepartamento")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nIdDepartamento;
	
	@Column(name="BActivo")
	private boolean bActivo;  
	
	@Column(name="Vnombre")
	private String vnombre;
	
	@Column(name="VObservaciones")
	private String vObservaciones;
	
	@Column(name="NIdCondominio")
	private String nIdCondominio;
	
	@Column(name="NIdMantenimiento")
	private String nIdMantenimiento;
	
	@Column(name="VMensajeCobro")
	private String vMensajeCobro;
	
	@Column(name="NUnidadIndiviso")
	private String nUnidadIndiviso;

	public int getnIdDepartamento() {
		return nIdDepartamento;
	}

	public void setnIdDepartamento(int nIdDepartamento) {
		this.nIdDepartamento = nIdDepartamento;
	}
	
	public boolean isbActivo() {
		return bActivo;
	}

	public void setbActivo(boolean bActivo) {
		this.bActivo = bActivo;
	}

	public String getVnombre() {
		return vnombre;
	}

	public void setVnombre(String vnombre) {
		this.vnombre = vnombre;
	}

	public String getvObservaciones() {
		return vObservaciones;
	}

	public void setvObservaciones(String vObservaciones) {
		this.vObservaciones = vObservaciones;
	}

	public String getnIdCondominio() {
		return nIdCondominio;
	}

	public void setnIdCondominio(String nIdCondominio) {
		this.nIdCondominio = nIdCondominio;
	}

	public String getnIdMantenimiento() {
		return nIdMantenimiento;
	}

	public void setnIdMantenimiento(String nIdMantenimiento) {
		this.nIdMantenimiento = nIdMantenimiento;
	}

	public String getvMensajeCobro() {
		return vMensajeCobro;
	}

	public void setvMensajeCobro(String vMensajeCobro) {
		this.vMensajeCobro = vMensajeCobro;
	}

	public String getnUnidadIndiviso() {
		return nUnidadIndiviso;
	}

	public void setnUnidadIndiviso(String nUnidadIndiviso) {
		this.nUnidadIndiviso = nUnidadIndiviso;
	}
	
}
