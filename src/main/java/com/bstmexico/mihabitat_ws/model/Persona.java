package com.bstmexico.mihabitat_ws.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tpersonas")
public class Persona {

	private static final long serialVersionUID = -7395525800007747060L;
	
	@Size(max = 64)
	@Column(name = "VApellidoMaterno", nullable = true, length = 64)
	protected String apellidoMaterno;

	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "VApellidoPaterno", nullable = false, length = 64)
	protected String apellidoPaterno;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NIdPersona", nullable = false, unique = true)
	protected Long id;

	@NotNull
	@Size(min = 1, max = 64)
	@Column(name = "VNombre", nullable = false, length = 64)
	protected String nombre;

	public String getApellidoMaterno() {
		return apellidoMaterno == null ? "" : apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
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
	private String nombre;
	
	private String apPaterno;
	
	private String apMaterno;
	
	private int idUsuario;
	
	private int idPersona;
	
	private int bActivo;
	
	private String email;	
	
	private int idRol;
	

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getbActivo() {
		return bActivo;
	}

	public void setbActivo(int bActivo) {
		this.bActivo = bActivo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
*/
}
