package com.dusto.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.dusto.bos.dao.IUserDao;
import com.dusto.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

}
