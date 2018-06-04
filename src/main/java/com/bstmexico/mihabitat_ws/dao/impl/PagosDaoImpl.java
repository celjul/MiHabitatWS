package com.bstmexico.mihabitat_ws.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bstmexico.mihabitat_ws.dao.PagosDao;
import com.bstmexico.mihabitat_ws.model.Departamento;


@Repository("pagosDao")
public class PagosDaoImpl implements PagosDao{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	@Override
	public void insertarPago(String NIdDepartamento,String NIdUsuario, String monto) {
		
		Connection conn = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String fecha = (dateFormat.format(date));
			String sql ="select tdepartamentos.NIdCondominio from tdepartamentos where NIdDepartamento = "+NIdDepartamento;
			int idCondominio = 0;		
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idCondominio =rs.getInt("NIdCondominio");
			}
			rs.close();
			ps.close();
			
			String sql2 ="select max(NFolio) from tpagos where NIdCondominio=nidcondominio = "+idCondominio;
			int folio = 0;
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				folio =(rs2.getInt("max(NFolio)")+1);
			}
			rs2.close();
			ps2.close();
			
			String sql3 ="select NIdPersona from tdepartamentocontactos where NIdDepartamento = "+NIdDepartamento;
			int idcontacto=0;			
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ResultSet rs3 = ps3.executeQuery();
			while (rs3.next()) {
				idcontacto = rs3.getInt("NIdPersona");
			}
			rs3.close();
			ps3.close();
			
			String sql4 ="insert into tpagos ( NIdCondominio, NIdContacto, NIdCuenta, DFecha, NFolio, NIdMetodoPago, DMonto) " + 
					"values ("+idCondominio+","+NIdUsuario+",9,'"+fecha+"',"+folio+",612,"+monto+")";
			System.out.println(sql4);
			PreparedStatement ps4 = conn.prepareStatement(sql4);
			ps4.executeUpdate();
			ps4.close();
			
			String sql5 ="SELECT nidpago FROM tpagos ORDER BY nidpago DESC LIMIT 1 ";
			int nidpago = 0;
			PreparedStatement ps5 = conn.prepareStatement(sql5);
			ResultSet rs5 = ps5.executeQuery();
			while (rs5.next()) {
				nidpago =rs5.getInt("nidpago");
			}
			rs5.close();
			ps5.close();
			
			String sql6 ="insert into testatuspago (NIdEstatus, NIdUsuario, NIdPago) values (42,"+NIdUsuario+","+nidpago+");";	
			PreparedStatement ps6 = conn.prepareStatement(sql6);
			ps6.executeUpdate();
			ps6.close();
			
			String sql7 ="insert into tpagosdepartamento (NIdCondominio, NIdDepartamento, DMonto, NIdPago) " + 
					"			values ("+idCondominio+","+NIdDepartamento+","+monto+","+nidpago+")";
			PreparedStatement ps7 = conn.prepareStatement(sql7);
			ps7.executeUpdate();
			ps7.close();	
		
			
			String sql8 ="insert into tmovimientos (DFecha, DHaber, NIdCuenta, NIdPagoMov, VTipo)" + 
					" values ('"+fecha+"',"+monto+",9,"+nidpago+",'pago')";
			PreparedStatement ps8 = conn.prepareStatement(sql8);
			ps8.executeUpdate();
			ps8.close();	
		
		
		}catch (SQLException e) {
			throw new RuntimeException(e);
			
			}finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}
	   
	   

}
