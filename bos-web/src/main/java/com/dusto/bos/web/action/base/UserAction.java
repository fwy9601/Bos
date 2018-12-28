package com.dusto.bos.web.action.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.User;
import com.dusto.bos.service.IUserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    // 属性驱动，接受页面输入的验证码
    private String checkcode;

    @Autowired
    private IUserService userService;
    /**
     * 用户登录
     */
    public String login() {
        // 从session中获得正确的验证码
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        // 校验验证码是否正确
        if (StringUtils.isNoneBlank(checkcode) && checkcode.equals(key)) {
            //输入的验证码正确
            User user =  userService.login(model);
            if(user != null){
                //登录成功，将user对象放入session，然后跳转到首页
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
                return HOME;
            }else{
                //登录失败，设置提示信息，跳转到登录页面
                this.addActionError("用户名或者密码输入错误");
                return LOGIN;
            }
        }else{
            //输入的验证码错误，设置提示信息，跳转到登录页面
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
