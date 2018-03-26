package com.bstmexico.mihabitat_ws.model;

import java.util.Date;

public class PendientesPago {

	private Date fecha;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
