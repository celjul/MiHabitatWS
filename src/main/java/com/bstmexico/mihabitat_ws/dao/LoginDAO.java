package com.bstmexico.mihabitat_ws.dao;

import com.bstmexico.mihabitat_ws.model.Persona;

public interface LoginDAO  {

	public Persona checkLogin(String user,String Password);
}
