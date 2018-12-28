package com.dusto.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dusto.bos.dao.IUserDao;
import com.dusto.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    @Override
    public User findUserByUsernameAndPassword(String username,String password) {
        String hql = "FROM User u WHERE u.username = ? and u.password = ?";
        List<User> list = (List<User>)this.getHibernateTemplate().find(hql, username,password);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

}
