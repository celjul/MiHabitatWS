package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tcontactos")
public class Contacto implements Serializable {

	private static final long serialVersionUID = -2736360469212877697L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NIdContacto", nullable = false, unique = true)
	private Long id;

	@JoinColumn(name = "NIdCondominio", nullable = false, unique = true)
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Condominio.class, cascade = CascadeType.ALL)
	private Condominio condominio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NIdUsuario", nullable = true, referencedColumnName = "NIdUsuario")
	private Usuarios usuario;

	
	public Contacto() {
		super();
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	/*@Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.contacto", orphanRemoval = true)
	private Collection<ContactoDepartamento> departamentos;
	
	public Collection<ContactoDepartamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Collection<ContactoDepartamento> departamentos) {
		this.departamentos = departamentos;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}


}