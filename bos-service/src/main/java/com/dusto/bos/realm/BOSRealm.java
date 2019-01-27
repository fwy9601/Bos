package com.dusto.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.dusto.bos.dao.IFunctionDao;
import com.dusto.bos.dao.IUserDao;
import com.dusto.bos.domain.Function;
import com.dusto.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userdao;
    
    @Autowired
    private IFunctionDao functionDao;

    /**
     * 认证方法
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 获得页面输入的用户名
        String username = usernamePasswordToken.getUsername();
        // 根据用户名查询密码
        User user = userdao.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 框架负责比对数据库中的密码和输入的密码是否一致
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }

    /**
     * 授权方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 为用户授权
        //info.addStringPermission("staff-list");
        //获取当前登录对象
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        //User user2 = (User)principals.getPrimaryPrincipal();
        // 根据当前用户查询数据库，获取实际对应的权限
        List<Function> list = null;
        if(user.getUsername().equals("admin")){
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
            //超级管理员内置用户，查询所有权限数据
            list = functionDao.findByCriteria(detachedCriteria );
        }else {
            list = (List<Function>) functionDao.findFunctionListByUserId(user.getId());
        }
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

}
