package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity 
@Table(name = "tdepartamentocontactos")

public class ContactoDepartamento implements Serializable {

	private static final long serialVersionUID = 2467683185271659574L;

	@NotNull
	@Column(name = "BHabitante", nullable = false)
	private Boolean habitante;

	@Id
	@Column(name = "BPrincipal", nullable = false)
	private Boolean principal;

	@NotNull
	@Column(name = "BPropietario", nullable = false,unique=false)
	private Boolean propietario;
	
	@Transient
	@Column(name = "NIdDepartamento")
	private Long departamento;

	@Transient
	@Column(name = "NIdPersona")
	private Long persona;

	public ContactoDepartamento() {
	}

	public Long getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

	public Long getPersona() {
		return persona;
	}

	public void setPersona(Long persona) {
		this.persona = persona;
	}

	public Boolean getHabitante() {
		return habitante;
	}

	public void setHabitante(Boolean habitante) {
		this.habitante = habitante;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public Boolean getPropietario() {
		return propietario;
	}

	public void setPropietario(Boolean propietario) {
		this.propietario = propietario;
	}

}