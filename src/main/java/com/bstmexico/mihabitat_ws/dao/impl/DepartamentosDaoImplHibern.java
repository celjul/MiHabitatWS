package com.bstmexico.mihabitat_ws.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.bstmexico.mihabitat_ws.dao.DepartamentoDaoHibernate;
import com.bstmexico.mihabitat_ws.model.Contacto;
import com.bstmexico.mihabitat_ws.model.ContactoDepartamento;
import com.bstmexico.mihabitat_ws.model.Departamentos;

public class DepartamentosDaoImplHibern implements DepartamentoDaoHibernate{

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Departamentos p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamentos> list() {
		Session session = this.sessionFactory.openSession();
		List<Departamentos> personList = session.createQuery("from Departamentos where NIdDepartamento = 1 ").list();
		session.close();
		return personList;
	}
	
	@SuppressWarnings("null")
	@Override
	public List<Departamentos> getbyContacto(Long idUsuario){
		List<Departamentos> list = new ArrayList<>();
		try {
			Session session = this.sessionFactory.openSession();
			List<Contacto> NidContactos = null;
			Query queryContacto = session.createQuery("from Contacto where NIdUsuario =:idUsuario");
			queryContacto.setParameter("idUsuario", idUsuario);
			NidContactos = queryContacto.list();
			int contadorContactos = 0 ;
			
			while(contadorContactos<NidContactos.size()) {
				List<ContactoDepartamento> idDepartamentos = new ArrayList<>();
				Session session2 = this.sessionFactory.openSession();
				SessionImpl sessionImpl = (SessionImpl) session2;
				Connection conn = sessionImpl.connection();
				try {
					PreparedStatement ps = conn.prepareStatement("select * from tdepartamentocontactos where NIdPersona ="+NidContactos.get(contadorContactos).getId());
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						ContactoDepartamento contactodepa = new ContactoDepartamento();
						contactodepa.setDepartamento(rs.getLong("NIdDepartamento"));
						idDepartamentos.add(contactodepa);
					}
					rs.close();
					ps.close();
					session2.close();
				} catch (SQLException e) {e.printStackTrace();}
				int cont =0 ;
				while(cont<idDepartamentos.size()) {
					List<Departamentos> personList = null;
					Query query = session.createQuery("from Departamentos where NIdDepartamento =:iddepartamento");
					query.setParameter("iddepartamento",idDepartamentos.get(cont).getDepartamento());
					personList = query.list();
					Departamentos depa = new Departamentos();	
					depa = personList.get(0);
					list.add(depa);
					cont++;
				} 
			contadorContactos++;
			}
		}catch (IllegalArgumentException ex) {throw ex;} catch (HibernateException ex) {throw ex;}
		return list;
	}
	
}
