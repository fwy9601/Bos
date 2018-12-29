package com.dusto.bos.web.action.base;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.User;
import com.dusto.bos.service.IUserService;
import com.dusto.bos.utils.BOSUtils;

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
            // 输入的验证码正确
            User user = userService.login(model);
            if (user != null) {
                // 登录成功，将user对象放入session，然后跳转到首页
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
                return HOME;
            } else {
                // 登录失败，设置提示信息，跳转到登录页面
                this.addActionError("用户名或者密码输入错误");
                return LOGIN;
            }
        } else {
            // 输入的验证码错误，设置提示信息，跳转到登录页面
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }

    /**
     * 用户注销
     * 
     * @return
     */
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

    /**
     * 修改当前用户的密码
     * 
     * @param checkcode
     * @throws IOException 
     */
    public String editPassword() throws IOException {
        String f = "1";
        // 获取当前用户
        User user = BOSUtils.getLoginUser();
        try {
            userService.editPassword(user.getId(), model.getPassword());
        } catch (Exception e) {
            f = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
