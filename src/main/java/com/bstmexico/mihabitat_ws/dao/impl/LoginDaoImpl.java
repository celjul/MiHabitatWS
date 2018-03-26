package com.bstmexico.mihabitat_ws.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bstmexico.mihabitat_ws.dao.LoginDAO;
import com.bstmexico.mihabitat_ws.model.Persona;


@Repository("loginDao")
public class LoginDaoImpl implements LoginDAO {
	 

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }
	   
	@Override
	public Persona checkLogin(String user, String password) {
		Persona persona = new Persona();
		Connection conn = null;

		try {
			String contrasena;
			contrasena = convertPassMd5(password);
			String sql = "select tusuarios.NIdUsuario , tusuarios.VEmail , tusuarios.BActivo , tusuarios.NIdPersona , "+
					"tpersonas.VNombre , tpersonas.VApellidoPaterno, tpersonas.VApellidoMaterno , "+
					"tcatalogos.NIdCatalogo from tusuarios,tpersonas,tusuarioroles,tcatalogos where "+
					"tusuarios.NIdPersona = tpersonas.NIdPersona and tusuarioroles.NIdUsuario = tusuarios.NIdUsuario "+
					"and tusuarioroles.NIdCatalogo = tcatalogos.NIdCatalogo "+
					"and VUser = '"+user+"' and VPassword = '"+contrasena+"';";
			System.out.println(sql);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
					persona.setIdUsuario(rs.getInt("NIdUsuario"));
					persona.setNombre(rs.getString("VNombre"));
					persona.setApPaterno(rs.getString("VApellidoPaterno"));
					persona.setApMaterno(rs.getString("VApellidoMaterno"));
					persona.setEmail(rs.getString("VEmail"));
					persona.setIdPersona(rs.getInt("NIdPersona"));
					persona.setbActivo(rs.getInt("BActivo"));	
					persona.setIdRol(rs.getInt("NIdCatalogo"));
			}if(rs.first()==false){
			persona.setIdUsuario(-1);}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} 	 finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return persona;
	}
	
	public static String convertPassMd5(String pass) {
		  String password = null;
		  MessageDigest mdEnc;
		  try {
		    mdEnc = MessageDigest.getInstance("MD5");
		    mdEnc.update(pass.getBytes(), 0, pass.length());
		    pass = new BigInteger(1, mdEnc.digest()).toString(16);
		    while (pass.length() < 32) {
		      pass = "0" + pass;
		    }
		    password = pass;
		  } catch (NoSuchAlgorithmException e1) {
		    e1.printStackTrace();
		  }
		  return password;
		}

}
