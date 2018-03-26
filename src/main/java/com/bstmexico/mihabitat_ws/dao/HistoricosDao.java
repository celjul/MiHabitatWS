package com.bstmexico.mihabitat_ws.dao;

import java.util.List;

import com.bstmexico.mihabitat_ws.model.PendientesPago;

public interface HistoricosDao {

	public List<PendientesPago> getPendientesPago(int idDepartamento);
}
