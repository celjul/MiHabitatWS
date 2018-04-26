package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tdepartamentos")
public class Departamentos implements Serializable {

	private static final long serialVersionUID = -7577682341577773991L;

	@NotNull
	@Column(name = "BActivo", nullable = false)
	private Boolean activo;


	@JoinColumn(name = "NIdCondominio", nullable = false, unique = true)
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Condominio.class, cascade = CascadeType.ALL)
	private Condominio condominio;
/*
	@Valid
	@OneToMany(fetch = FetchType.LAZY, 
		mappedBy = "id.departamento", orphanRemoval = true)
	private Collection<ContactoDepartamento> contactos;
*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NIdDepartamento", nullable = false, unique = true)
	private Long id;

	@NotNull
	@Size(min = 1, max = 128)
	@Column(length = 128, name = "VNombre", nullable = false)
	private String nombre;

	@Size(max = 512)
	@Column(name = "VObservaciones", nullable = true)
	private String observaciones;

	@Size(max = 512)
	@Column(name = "VMensajeCobro", nullable = true)
	private String mensajeCobro;

	@Column(name = "NUnidadIndiviso", nullable = true)
	private BigDecimal unidadIndiviso;

	public Departamentos() {
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
/*
	public Collection<ContactoDepartamento> getContactos() {
		return contactos;
	}

	public void setContactos(Collection<ContactoDepartamento> contactos) {
		this.contactos = contactos;
	}
*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMensajeCobro() {
		return mensajeCobro;
	}

	public void setMensajeCobro(String mensajeCobro) {
		this.mensajeCobro = mensajeCobro;
	}


/*	public void addContacto(ContactoDepartamento contacto) {
		if (this.contactos == null) {
			this.contactos = new ArrayList<ContactoDepartamento>();
		}
		this.contactos.add(contacto);
	}*/

	public BigDecimal getUnidadIndiviso() { return unidadIndiviso; }

	public void setUnidadIndiviso(BigDecimal unidadIndiviso) { this.unidadIndiviso = unidadIndiviso; }


}
