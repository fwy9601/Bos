package com.dusto.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dusto.bos.dao.IFunctionDao;
import com.dusto.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

    public List<Function> findAll() {
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>)this.getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 根据用户id查询对应的权限
     */
    public List<Function> findFunctionListByUserId(String userId) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.role r LEFT OUTER JOIN r.users u WHERE u.id=?";
        List<Function> list = (List<Function>)this.getHibernateTemplate().find(hql, userId);
        return list;
    }
    
}
