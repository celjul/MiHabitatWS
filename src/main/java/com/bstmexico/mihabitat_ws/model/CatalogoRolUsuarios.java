package com.bstmexico.mihabitat_ws.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("rol")
public class CatalogoRolUsuarios extends Catalogo{

	private static final long serialVersionUID = -3494056695946735705L;

	public CatalogoRolUsuarios(){
	}
	
}
