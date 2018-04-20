package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "tcondominios")
public class Condominio implements Serializable {

	private static final long serialVersionUID = 1354049397051994635L;

	@Fetch(FetchMode.SELECT)
	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tcondominioadministradores", joinColumns = { @JoinColumn(name = "NIdCondominio", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "NIdUsuario", nullable = false) })
	private Set<Usuarios> administradores;

/*	@Valid
	@NotNull
	@JoinColumn(name = "NIdDireccion", nullable = false, referencedColumnName = "NIdDireccion")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private DireccionCondominio direccion;*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NIdCondominio", nullable = false, unique = true)
	private Long id;

	@NotEmpty
	@Size(min = 1, max = 128)
	@Column(length = 128, name = "VNombre", nullable = false)
	private String nombre;

	/*@JoinColumn(name = "NIdLogo", referencedColumnName = "NIdArchivo")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private ArchivoImagenCondominio logoCondominio;


	@JoinColumn(name = "NIdConfiguracion", referencedColumnName = "NIdConfiguracionCondominio")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private ConfiguracionCondominio configuracionCondominio;
*/
	public Condominio() {
	}

	public Set<Usuarios> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(Set<Usuarios> administradores) {
		this.administradores = administradores;
	}

/*	public DireccionCondominio getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionCondominio direccion) {
		this.direccion = direccion;
	}*/

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
	

}
