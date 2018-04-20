package com.bstmexico.mihabitat_ws.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tdepartamentocontactos")
@AssociationOverrides({
		@AssociationOverride(name = "id.contacto", joinColumns = 
				@JoinColumn(name = "NIdPersona")),
		@AssociationOverride(name = "id.departamento", joinColumns = 
				@JoinColumn(name = "NIdDepartamento")) })
public class ContactoDepartamento implements Serializable {

	private static final long serialVersionUID = 2467683185271659574L;

	@EmbeddedId
	private ContactoDepartamentoId id = new ContactoDepartamentoId();

	@NotNull
	@Column(name = "BHabitante", nullable = false)
	private Boolean habitante;

	@NotNull
	@Column(name = "BPrincipal", nullable = false)
	private Boolean principal;

	@NotNull
	@Column(name = "BPropietario", nullable = false)
	private Boolean propietario;

	
	public ContactoDepartamento() {
	}

	public ContactoDepartamentoId getId() {
		return id;
	}

	public void setId(ContactoDepartamentoId id) {
		this.id = id;
	}
	
	@Transient
	public Contacto getContacto() {
		return getId().getContacto();
	}
	
	public void setContacto(Contacto contacto) {
		getId().setContacto(contacto);
	}

	@Transient
	public Departamento getDepartamento() {
		return getId().getDepartamento();
	}

	public void setDepartamento(Departamento departamento) {
		getId().setDepartamento(departamento);
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
