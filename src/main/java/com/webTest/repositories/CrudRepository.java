package com.webTest.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CrudRepository<T> implements ICrudRepository<T>  {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> clazz;

    public void setClazz(final Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T findOne(int id) {
        return (T) getCurrentSession().get( clazz, id );
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + clazz.getName() + " ORDER BY id").list();
    }

    @Override
    public int save(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = getCurrentSession().beginTransaction();
        int id = (int)session.save(entity);
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public void update(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = getCurrentSession().beginTransaction();
        session.update(entity);
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        T entity = this.findOne(id);
        this.delete( entity );
    }

    @Override
    public void delete(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = getCurrentSession().beginTransaction();
        session.delete(entity);
        session.flush();
        session.clear();
        transaction.commit();
        session.close();
    }

    private final Session getCurrentSession(){
        return sessionFactory.openSession();
    }
}
