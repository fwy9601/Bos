package com.dusto.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dusto.bos.dao.base.IBaseDao;
/**
 * 持久层同意实现
 * @author 13302
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{

    //代表的是某个实体的类型
    private Class<T> entityClass;
    
    @Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
    public void setMySessionFactory(SessionFactory sessionFactory){
         super.setSessionFactory(sessionFactory);
    }
    
    //在父类中（BaseDaoImpl）的构造方法中动态获得entityClass
    @SuppressWarnings("unchecked")
    public BaseDaoImpl(){
        ParameterizedType superclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>)actualTypeArguments[0];
    }
    
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        String hql = "FORM "+entityClass.getSimpleName();
        return (List<T>)this.getHibernateTemplate().find(hql);
    }

}
