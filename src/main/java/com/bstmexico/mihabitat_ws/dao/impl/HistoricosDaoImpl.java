package com.bstmexico.mihabitat_ws.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bstmexico.mihabitat_ws.dao.HistoricosDao;
import com.bstmexico.mihabitat_ws.model.PendientesPago;

@Repository("historicosDao")
public class HistoricosDaoImpl implements HistoricosDao{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	   
	   
	   
	public List<PendientesPago> getPendientesPago(int idDepartamento) {
		List<PendientesPago> lista = new ArrayList();
		Connection conn = null;

		try {
			 
			String sql = "select tmovimientos.DFecha , tmovimientos.DDebe , tcargos.VConcepto from tmovimientos , tcargos , tcargosdepartamento "+
			"where tcargos.NIdCargo = tmovimientos.NIdCargo and tcargosdepartamento.NIdCargo = tcargos.NIdCargo "+
			"and tcargosdepartamento.NIdDepartamento = "+idDepartamento+" and tmovimientos.NIdTipo=28 order by DFecha DESC;";		
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PendientesPago pendientesPago = new PendientesPago();
				pendientesPago.setMonto(rs.getInt("DDebe"));
				pendientesPago.setFecha(rs.getDate("DFecha"));
				pendientesPago.setDetalle(rs.getString("VConcepto"));
				lista.add(pendientesPago);
			}
			rs.close();
			ps.close();
			}catch (SQLException e) {
			throw new RuntimeException(e);
			
			}finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return lista;
	}
	

}
