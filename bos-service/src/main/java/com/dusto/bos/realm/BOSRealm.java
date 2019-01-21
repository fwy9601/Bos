package com.dusto.bos.realm;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.dusto.bos.dao.IUserDao;
import com.dusto.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userdao;

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
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 为用户授权
        info.addStringPermission("staff-list");
        // TODO 后期需要修改为根据当前用户查询数据库，获取实际对应的权限
        return info;
    }

}