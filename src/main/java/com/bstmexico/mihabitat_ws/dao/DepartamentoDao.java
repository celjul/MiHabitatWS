package com.bstmexico.mihabitat_ws.dao;

import java.util.List;

import com.bstmexico.mihabitat_ws.model.Departamento;

public interface DepartamentoDao {
	
	public List<Departamento> getDepartamentos(String idPersona);
	
	public Departamento getTorreEtiquetas(String idDepartamento);
	
	public int getSaldoFavor(int idDepartamento);

}
