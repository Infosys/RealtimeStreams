/*
* Copyright 2019 Infosys Ltd.
*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.streamconnect.dss.metadata.connection.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Basic CRUD Abstraction for DAO Layer in the FDP.
 *
 * @version 1.0
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
@Repository
public class BaseDataAccess<T, ID extends Serializable> {

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    /** The persistent class. */
    private Class<T> persistentClass;

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param <T>
     *            the generic type
     * @param criterion
     *            the criterion
     * @return the list
     */
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findByCriteria(final Criterion... criterion) {
        Criteria crit;
        try {

            crit = this.sessionFactory.getCurrentSession()
                    .createCriteria(getPersistentClass()).setCacheable(true);
            for (Criterion c : criterion) {
                crit.add(c);
            }
        } catch (HibernateException e) {

            throw (e);
        }

        return crit.list();
    }

    /**
     * Gets the persistent class.
     *
     * @return persistentClass
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * This method will give the complete details of a T object available.
     *
     * @param enitity
     *            the enitity
     * @param id
     *            the id
     * @return RestroCategory
     */
    @SuppressWarnings("unchecked")
    public T findById(final T enitity, final ID id) {
        T object;
        try {
            object = (T) this.sessionFactory.getCurrentSession()
                    .get(enitity.getClass(), id);

        } catch (HibernateException e) {

            throw (e);
        }
        return object;
    }

    /**
     * This method will give the complete details of a T object available.
     *
     * @param hql
     *            the hql
     * @param parameterList
     *            the parameter list
     * @return RestroCategory
     */
    @SuppressWarnings("unchecked")
    public T findById(final String hql,
                      final Map<String, Object> parameterList) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        T uniqueResult;
        try {
            Query createQuery = currentSession.createQuery(hql);
            createQuery.setCacheable(true);
            if (parameterList != null) {
                Set<Entry<String, Object>> entrySet = parameterList.entrySet();
                if (entrySet != null) {
                    for (Entry<String, Object> entry : entrySet) {
                        createQuery.setParameter(entry.getKey(),
                                entry.getValue());
                    }
                }
            }
            uniqueResult = (T) createQuery.uniqueResult();
        } catch (HibernateException e) {

            throw (e);
        }

        return uniqueResult;
    }

    /**
     * This will be used when directly all values of an entities is needed.
     *
     * @param entity
     *            the entity
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll(final T entity) {
        List<T> list;
        try {

            list = (List<T>) this.sessionFactory.getCurrentSession()
                    .createCriteria(entity.getClass()).setCacheable(true)
                    .list();
        } catch (HibernateException e) {

            throw (e);
        }

        return list;
    }

    /**
     * List all.
     *
     * @param entity
     *            the entity
     * @param hql
     *            the hql
     * @return the list
     */
    public List<T> listAll(final T entity, final String hql) {
        List<T> list;
        try {
            Session currentSession = this.sessionFactory.getCurrentSession();
            currentSession.createQuery(hql);
            list = (List<T>) currentSession.createCriteria(entity.getClass())
                    .setCacheable(true).list();
        } catch (HibernateException e) {

            throw (e);
        }

        return list;
    }

    /**
     * This will be used when directly all values of an entities is needed.
     *
     * @param entity
     *            the entity
     * @param maxResults
     *            the max results
     * @param firstResult
     *            the first result
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll(final T entity, final int maxResults,
                           final int firstResult) {
        List<T> list;
        try {

            list = (List<T>) this.sessionFactory.getCurrentSession()
                    .createCriteria(entity.getClass()).setMaxResults(maxResults)
                    .setFirstResult(firstResult).setCacheable(true).list();
        } catch (HibernateException e) {

            throw (e);
        }

        return list;
    }

    /**
     * This is method can be used ,if a query is having parameter binding.
     *
     * @param hql
     *            the hql
     * @param parameterList
     *            the parameter list
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll(final String hql,
                           final Map<String, Object> parameterList) {
        Query createQuery;
        try {

            Session currentSession = this.sessionFactory.getCurrentSession();
            createQuery = currentSession.createQuery(hql);
            createQuery.setCacheable(true);
            if (parameterList != null) {
                Set<Entry<String, Object>> entrySet = parameterList.entrySet();
                if (entrySet != null) {
                    for (Entry<String, Object> entry : entrySet) {
                        createQuery.setParameter(entry.getKey(),
                                entry.getValue());
                    }
                }
            }
        } catch (HibernateException e) {

            throw (e);
        }

        return createQuery.list();
    }

    /**
     * This is method can be used ,if a query is complex query statement with
     * out parameter binding.
     *
     * @param hql
     *            the hql
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll(final String hql) {
        List<T> list;
        try {

            list = (List<T>) this.sessionFactory.getCurrentSession()
                    .createQuery(hql).setCacheable(true).list();
        } catch (HibernateException e) {

            throw (e);
        }

        return list;
    }

    /**
     * This is method can be used ,if a query is complex query statement with
     * out parameter binding and have pagination.
     *
     * @param hql
     *            the hql
     * @param maxResults
     *            the max results
     * @param firstResult
     *            the first result
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll(final String hql, final int maxResults,
                           final int firstResult) {

        List<T> list;
        try {
            list = (List<T>) this.sessionFactory.getCurrentSession()
                    .createQuery(hql).setMaxResults(maxResults)
                    .setFirstResult(firstResult).setCacheable(true).list();
        } catch (HibernateException e) {

            throw (e);
        }

        return list;
    }

    /**
     * This is method can be used ,if a query is having parameter binding and
     * have pagination.
     *
     * @param hql
     *            the hql
     * @param parameterList
     *            the parameter list
     * @param maxResults
     *            the max results
     * @param firstResult
     *            the first result
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> listAll(final String hql,
                           final Map<String, Object> parameterList, final int maxResults,
                           final int firstResult) {
        Query createQuery;

        try {
            Session currentSession = this.sessionFactory.getCurrentSession();
            createQuery = currentSession.createQuery(hql);
            createQuery.setCacheable(true);
            if (parameterList != null) {
                Set<Entry<String, Object>> entrySet = parameterList.entrySet();
                if (entrySet != null) {
                    for (Entry<String, Object> entry : entrySet) {
                        createQuery.setParameter(entry.getKey(),
                                entry.getValue());
                    }
                }
            }
            createQuery.setMaxResults(maxResults).setFirstResult(firstResult);
        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }

        return createQuery.list();
    }

    /**
     * This will save a entities.
     *
     * @param entity
     *            the entity
     * @return Boolean
     */
    public Boolean save(final T entity) {
        try {
            this.sessionFactory.getCurrentSession().save(entity);

        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }
        return true;
    }

    /**
     * This will update a T object.
     *
     * @param entity
     *            the entity
     * @return boolean
     */
    public boolean saveOrUpdate(final T entity) {
        try {

            this.sessionFactory.getCurrentSession().saveOrUpdate(entity);

        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }
        return true;
    }

    /**
     * This can be used to merge a T object.
     *
     * @param entity
     *            the entity
     * @return T
     */
    @SuppressWarnings("unchecked")
    public T merge(final T entity) {
        T merge;
        try {
            merge = (T) this.sessionFactory.getCurrentSession().merge(entity);

        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }
        return merge;
    }

    /**
     * This can be used to merge a T object.
     *
     * @param entity
     *            the entity
     * @return T
     */
    public Boolean persist(final T entity) {
        try {
            this.sessionFactory.getCurrentSession().persist(entity);

        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }
        return true;
    }

    /**
     * This can be used to get the total count a T object ,let say String hql =
     * "SELECT count(distinct E.firstName) FROM Employee E";.
     *
     * @param hql
     *            the hql
     * @return T
     */
    public Long count(final String hql) {
        @SuppressWarnings("unchecked")
        List<T> list = this.sessionFactory.getCurrentSession().createQuery(hql)
                .list();
        Long object;

        try {
            object = (Long) list.get(0);
        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }

        return object;
    }

    /**
     * This can be used to get the total count a T object ,let say String hql =
     * "SELECT count(distinct E.firstName) FROM Employee E";.
     *
     * @param hql
     *            the hql
     * @param parameterList
     *            the parameter list
     * @return T
     */
    public Integer count(final String hql,
                         final Map<String, Object> parameterList) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        Query createQuery = currentSession.createQuery(hql);

        if (parameterList != null) {
            Set<Entry<String, Object>> entrySet = parameterList.entrySet();
            if (entrySet != null) {
                for (Entry<String, Object> entry : entrySet) {
                    createQuery.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }
        Integer object;
        @SuppressWarnings("unchecked")
        List<T> list = createQuery.list();
        try {
            object = new Integer(list.size());
        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }

        return object;
    }

    /**
     * This is method can be used ,if a query is having parameter binding , and
     * to run a simple SQL(CRUD).
     *
     * @param hql
     *            the hql
     * @param parameterList
     *            the parameter list
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> runQuery(final String hql,
                            final Map<String, Object> parameterList) {
        Query createQuery;
        try {
            // LOGGER.info("In runQuery ---> Start");
            Session currentSession = this.sessionFactory.getCurrentSession();
            createQuery = currentSession.createQuery(hql);
            createQuery.setCacheable(true);
            if (parameterList != null) {
                Set<Entry<String, Object>> entrySet = parameterList.entrySet();
                if (entrySet != null) {
                    for (Entry<String, Object> entry : entrySet) {
                        createQuery.setParameter(entry.getKey(),
                                entry.getValue());
                    }
                }
            }
        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }
        return createQuery.list();
    }

    /**
     * This is method can be used ,if a query is having parameter binding , and
     * to run a simple SQL(CRUD).
     *
     * @param hql
     *            the hql
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> runNativeQuery(final String hql) {
        Query createQuery;
        try {
            Session currentSession = this.sessionFactory.getCurrentSession();
            createQuery = currentSession.createSQLQuery(hql);
            createQuery.setCacheable(true);

        } catch (HibernateException e) {

            /*
             * throw new DataAccessLayerException(
             * ErrorMessageEnum.SQL_LAYER_EXCEPTION.getMessage(), e);
             */
            throw (e);
        }
        return createQuery.list();
    }

    /**
     * This will update a T object.
     *
     * @param entity
     *            the entity
     * @return boolean
     */
    public boolean update(final T entity) {
        try {
            this.sessionFactory.getCurrentSession().update(entity);

        } catch (HibernateException e) {

            throw (e);
        }
        return true;
    }

}
