package com.bstmexico.mihabitat_ws.dao;

import java.util.List;

import com.bstmexico.mihabitat_ws.model.Departamentos;

public interface DepartamentoDaoHibernate {
	
	public void save(Departamentos p);
	
	public List<Departamentos> list();
	
	public List<Departamentos> getbyContacto(Long idUsuario);
	
}
