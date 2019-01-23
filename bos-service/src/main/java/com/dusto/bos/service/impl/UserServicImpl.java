package com.dusto.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IUserDao;
import com.dusto.bos.domain.Role;
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
     * 
     * @param user
     * @return
     */
    public User login(User user) {
        // 使用MD5加密密码
        String md5 = MD5Utils.md5(user.getPassword());
        return userDao.findUserByUsernameAndPassword(user.getUsername(), md5);
    }

    /**
     * 根据用户id修改密码
     */
    public void editPassword(String id, String password) {
        password = MD5Utils.md5(password);
        userDao.executeUpdate("user.editpassword",  password,id);
    }

    /**
     * 添加用户，同时关联角色
     */
    public void save(User user, String[] roleIds) {
        userDao.save(user);
        if(roleIds!=null&&roleIds.length>0){
            for (String roleId : roleIds) {
                //手动关联托管对象
                Role role = new Role();
                role.setId(roleId);
                //用户对象关联角色对象
                user.getRoles().add(role);
            }
        }
    }

}
