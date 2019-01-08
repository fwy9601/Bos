package com.dusto.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dusto.bos.dao.base.IBaseDao;
import com.dusto.bos.utils.PageBean;
/**
 * 持久层统一实现
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
        String hql = "FROM "+entityClass.getSimpleName();
        return (List<T>)this.getHibernateTemplate().find(hql);
    }
    
    /**
     * 执行更新queryName是在hbm.xml中的语句名称
     */
    public void executeUpdate(String queryName, Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        //为HQL语句中的？赋值
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        query.executeUpdate();
    }

    /**
     * 页面查询
     */
    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        //查询total--总数量
        detachedCriteria.setProjection(Projections.rowCount());//制定hibernate发出sql的形式 count(*)
        List<Long> countList = (List<Long>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        pageBean.setTotal(countList.get(0).intValue());
        //查询rows--当前页需要的数据集合
        detachedCriteria.setProjection(null);//制定hibernate发出sql的形式 count(*)
        //指定hibernate封装结果集形式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firstResult = (currentPage-1)*pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, pageSize);
        pageBean.setRows(rows);
    }

    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<T>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

}
