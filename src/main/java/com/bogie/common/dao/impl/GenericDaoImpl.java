package com.bogie.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bogie.common.dao.GenericDao;

@Transactional(readOnly=false)
public class GenericDaoImpl<I extends Serializable, T> implements GenericDao<I, T>
{
    @Autowired
    private EntityManager       entityManager;
    
	@SuppressWarnings("unchecked")
    public T get(Class<T> type, I id)
    {	    
        return (T)getSession().get(type, id);
    }

    public T saveOrUpdate(T value)
    {
        getSession().saveOrUpdate(value);
        
        return value;
    }

    public void delete(T value)
    {
        getSession().delete(value);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query)
    {
        return (List<T>)getSession().createQuery(query).list();
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query, Object value)
    {
        return (List<T>)getSession().createQuery(query).setProperties(value).list();
    }
    
    private Session getSession()
    {
        return (Session)entityManager.getDelegate();
    }
}
