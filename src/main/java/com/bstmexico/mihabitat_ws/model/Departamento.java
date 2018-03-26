package com.bstmexico.mihabitat_ws.model;

import java.util.List;

public class Departamento {
	
	private int idDepartamento;
	
	private String nombre;
	
	private List etiquetas;
	
	private int saldoFavor;
	
	private int saldoDeuda;
	
	private int iDCondomino;
	
	private String nombreCondomino;

	
	public int getiDCondomino() {
		return iDCondomino;
	}


	public void setiDCondomino(int iDCondomino) {
		this.iDCondomino = iDCondomino;
	}


	public String getNombreCondomino() {
		return nombreCondomino;
	}


	public void setNombreCondomino(String nombreCondomino) {
		this.nombreCondomino = nombreCondomino;
	}


	public int getIdDepartamento() {
		return idDepartamento;
	}

	
	public int getSaldoFavor() {
		return saldoFavor;
	}


	public void setSaldoFavor(int saldoFavor) {
		this.saldoFavor = saldoFavor;
	}


	public int getSaldoDeuda() {
		return saldoDeuda;
	}


	public void setSaldoDeuda(int saldoDeuda) {
		this.saldoDeuda = saldoDeuda;
	}


	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List etiquetas) {
		this.etiquetas = etiquetas;
	}
	
	
	

}
