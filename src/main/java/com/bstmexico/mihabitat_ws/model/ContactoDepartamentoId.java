package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class ContactoDepartamentoId implements Serializable {

	private static final long serialVersionUID = -4436274484118349539L;

	@JsonIgnoreProperties(value = { "departamentos" })
	@ManyToOne(fetch = FetchType.LAZY)
	private Contacto contacto;

	@JsonIgnoreProperties(value = { "contactos" })
	@ManyToOne(fetch = FetchType.LAZY)
	private Departamento departamento;

	public ContactoDepartamentoId() {
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}