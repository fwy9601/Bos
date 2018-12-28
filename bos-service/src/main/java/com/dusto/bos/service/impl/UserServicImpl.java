package com.dusto.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IUserDao;
import com.dusto.bos.domain.User;
import com.dusto.bos.service.IUserService;
import com.dusto.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServicImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user) {
        //使用MD5加密密码
        String md5 = MD5Utils.md5(user.getPassword());
        return userDao.findUserByUsernameAndPassword(user.getUsername(),md5);
    }

}
