package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;


@Entity
@Table(name = "tcondominios")
public class Condominio implements Serializable {

	private static final long serialVersionUID = 1354049397051994635L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NIdCondominio", nullable = false, unique = true)
	private Long id;

	@Size(min = 1, max = 128)
	@Column(length = 128, name = "VNombre", nullable = false)
	private String nombre;

	public Condominio() {
	}

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
	/*
	@JoinColumn(name = "NIdLogo", referencedColumnName = "NIdArchivo")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private ArchivoImagenCondominio logoCondominio;


	@JoinColumn(name = "NIdConfiguracion", referencedColumnName = "NIdConfiguracionCondominio")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private ConfiguracionCondominio configuracionCondominio;
	
	@Valid
	@NotNull
	@JoinColumn(name = "NIdDireccion", nullable = false, referencedColumnName = "NIdDireccion")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private DireccionCondominio direccion;
	
	public DireccionCondominio getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionCondominio direccion) {
		this.direccion = direccion;
	}
	
	public ArchivoImagenCondominio getLogoCondominio() {
		return logoCondominio;
	}

	public void setLogoCondominio(ArchivoImagenCondominio logoCondominio) {
		this.logoCondominio = logoCondominio;
	}
*/

}