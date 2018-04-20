package com.bstmexico.mihabitat_ws.dao;

import com.bstmexico.mihabitat_ws.model.Usuarios;

public interface LoginDAO  {

	public Usuarios checkLogin(String user,String Password);
	
}
