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

import com.bstmexico.mihabitat_ws.dao.DepartamentoDao;
import com.bstmexico.mihabitat_ws.model.Departamento;

@Repository("departamentoDao")
public class DepartamentoDaoImpl implements DepartamentoDao{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	   
	   
	
	public List<Departamento> getDepartamentos(String idPersona) {
		List<Departamento> lista = new ArrayList();
		Connection conn = null;

		try {
			String sql ="select tdepartamentos.VNombre , tdepartamentos.NIdDepartamento , tcondominios.NIdCondominio, "+ 
			"tcondominios.VNombre  AS nombreCondo from tcondominios, tdepartamentos left join tdepartamentocontactos on "+
			"tdepartamentos.NIdDepartamento = tdepartamentocontactos.NIdDepartamento	Left join tcontactos "+
			"on tdepartamentocontactos.NIdPersona = tcontactos.NIdContacto where tcondominios.NIdCondominio "+
			"= tdepartamentos.NIdCondominio and tcontactos.NIdUsuario = "+idPersona+";";
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Departamento depa = new Departamento();
				depa.setiDCondomino(rs.getInt("NIdCondominio"));
				depa.setIdDepartamento(rs.getInt("NIdDepartamento"));
				depa.setNombre(rs.getString("VNombre"));
				depa.setNombreCondomino(rs.getString("nombreCondo"));
				lista.add(depa);
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
	
	public Departamento getTorreEtiquetas(String idDepartamento) {
		Connection conn = null;
		Departamento depa = new Departamento();
		List<String> list = new ArrayList();
		depa.setIdDepartamento(Integer.valueOf(idDepartamento));
		try {

			String sql = "select tdepartamentos.VNombre from tdepartamentos where NIdDepartamento="+idDepartamento+";";
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				depa.setNombre(rs.getString("VNombre"));
			}
			rs.close();
			ps.close();
			String sql2="select * from tgruposcondominio where NIdGrupo in (select NIdGrupo from tdepartamentogrupos where NIdDepartamento = "+idDepartamento+");";
			Connection conn2 = dataSource.getConnection();
			PreparedStatement ps2 = conn2.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				list.add(rs2.getString("VDescripcion"));
			}rs2.close();
			ps2.close();
			depa.setEtiquetas(list);
		}catch (SQLException e) {
		throw new RuntimeException(e);
			
			}finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return depa;
		
	}



	
	public int getSaldoFavor(int idDepartamento) {
		Connection conn = null;
		int dHaber = 0;
		int dDebe=0;
		int saldo=0;
		try {
			String sql = "select sum(DHaber) from tmovimientos where NIdDepartamento ="+idDepartamento+";";
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dHaber=rs.getInt("sum(DHaber)");
			}
			rs.close();
			ps.close();
			String sql2="select sum(DDebe) from tmovimientos where NIdDepartamento ="+idDepartamento+";";
			System.out.println(sql2);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				dDebe=rs2.getInt("sum(DDebe)");
			}rs2.close();
			ps2.close();
			saldo=dHaber-dDebe;
		}catch (SQLException e) {
		throw new RuntimeException(e);
			
			}finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return saldo;
	}

	
}
