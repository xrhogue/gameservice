package com.bogie.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.bogie.common.dao.GenericDao;

@Transactional(readOnly=false)
public class GenericDaoImpl<I extends Serializable, T> implements GenericDao<I, T>
{
    @Autowired
    private HibernateTemplate   hibernateTemplate;
    
	public T get(Class<T> type, I id)
    {
        return (T)hibernateTemplate.get(type, id);
    }

    public void saveOrUpdate(T value)
    {
        hibernateTemplate.saveOrUpdate(value);
    }

    public void delete(T value)
    {
        hibernateTemplate.delete(value);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query)
    {
        return (List<T>)hibernateTemplate.find(query);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> find(String query, Object value)
    {
        return (List<T>)hibernateTemplate.find(query, value);
    }
}
