package com.dusto.bos.dao;

import com.dusto.bos.dao.base.IBaseDao;
import com.dusto.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

    public User findUserByUsernameAndPassword(String username,String password);

    public User findUserByUsername(String username);

}
