package com.bogie.common.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<I extends Serializable, T>
{
    T get(Class<T> type, I id);
    void saveOrUpdate(T value);
    void delete(T value);
    List<T> find(String query);
    List<T> find(String query, Object value);
}
