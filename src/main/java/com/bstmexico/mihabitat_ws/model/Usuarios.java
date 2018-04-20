package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;



@Entity
@Table(name = "tusuarios")
public class Usuarios implements Serializable {

	private static final long serialVersionUID = -6582217662400663493L;

	@NotNull
	@Column(name = "BActivo", nullable = false)
	private Boolean activo;

	@Email
	@NotNull
	@Size(min = 1, max = 64)
	@Column(length = 64, name = "VEmail", nullable = false, unique = true)
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NIdUsuario", nullable = false, unique = true)
	private Long id;

	@NotNull
	@Size(min = 32, max = 32)
	@Column(name = "VPassword", nullable = false, length = 32)
	private String password;
	

	@NotNull
	@Size(min = 5, max = 64)
	@Column(name = "VUser", nullable = false, length = 64, unique = true)
	private String user;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = Persona.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "NIdPersona", nullable = false, unique = true)
	private Persona persona;
	
	@Size(min = 1)
	@NotNull
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tusuarioroles", joinColumns = { 
			@JoinColumn(name = "NIdUsuario" , nullable = false) }, 
			inverseJoinColumns = { 
					@JoinColumn(name = "NIdCatalogo", nullable = false) })
	private Collection<CatalogoRolUsuarios> roles;
	
	
	public Collection<CatalogoRolUsuarios> getRoles() {
		return roles;
	}

	public void setRoles(Collection<CatalogoRolUsuarios> roles) {
		this.roles = roles;
	}
	
	public void addRol(CatalogoRolUsuarios rol) {
		if (this.roles == null) {
			this.roles = new HashSet<CatalogoRolUsuarios>();
		}
		this.roles.add(rol);
	}

	public Usuarios() {
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}