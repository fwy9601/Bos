package com.dusto.bos.web.interceptor;

import com.dusto.bos.domain.User;
import com.dusto.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器，实现用户未登录自动跳转到登录页面
 * @author dusto
 *
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor{

    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User user = BOSUtils.getLoginUser();
        if(user==null){
            return "login";
        }
        return invocation.invoke();
    }

}
