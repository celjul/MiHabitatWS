package com.bstmexico.mihabitat_ws.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bstmexico.mihabitat_ws.dao.LoginDAO;
import com.bstmexico.mihabitat_ws.model.CatalogoRolUsuarios;
import com.bstmexico.mihabitat_ws.model.Departamentos;
import com.bstmexico.mihabitat_ws.model.Persona;
import com.bstmexico.mihabitat_ws.model.Usuarios;


@Repository("loginDao")
public class LoginDaoImpl implements LoginDAO {
	 
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
   	   
	@Override
	public Usuarios checkLogin(String user, String password) {

		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Usuarios as u where u.user=:usuario and password=:pass");
		query.setParameter("usuario", user);
		query.setParameter("pass", convertPassMd5(password));
		List<Usuarios> listaUsuario = query.list();
		return listaUsuario.get(0);
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
