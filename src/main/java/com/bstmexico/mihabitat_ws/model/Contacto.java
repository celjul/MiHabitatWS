package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "tcontactos")
@AttributeOverride(name = "id", column = @Column(name = "NIdContacto"))
public class Contacto implements Serializable {

	private static final long serialVersionUID = -2736360469212877697L;

	@NotNull
	@JoinColumn(name = "NIdCondominio", nullable = false, referencedColumnName = "NIdCondominio")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Condominio condominio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NIdUsuario", nullable = true, referencedColumnName = "NIdUsuario")
	private Usuarios usuarios;

	@Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.contacto", orphanRemoval = true)
	private Collection<ContactoDepartamento> departamentos;

	public Contacto() {
		super();
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public Collection<ContactoDepartamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Collection<ContactoDepartamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Usuarios getUsuario() {
		return usuarios;
	}

	public void setUsuario(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
