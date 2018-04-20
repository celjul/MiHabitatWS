package com.bstmexico.mihabitat_ws.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bstmexico.mihabitat_ws.dao.DepartamentoDaoHibernate;
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
}
