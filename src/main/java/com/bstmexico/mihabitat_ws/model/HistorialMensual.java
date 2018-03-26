package com.bstmexico.mihabitat_ws.model;

import java.util.Date;

public class HistorialMensual {
	
	private Date fecha;
	private int idTipoMovimiento;
	private String tipoMovimiento;
	private String detalle;
	private double monto;
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	

}
