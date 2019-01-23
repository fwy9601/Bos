package com.dusto.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Role;
import com.dusto.bos.service.IRoleService;
import com.dusto.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    //属性驱动
    private String functionIds;
    
    @Autowired
    private IRoleService service;
    
    /**
     * 添加角色
     * @return
     */
    public String add(){
        service.add(model,functionIds);
        return NONE;
    }
    
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
    
    /**
     * 分页查询
     */
    public String pageQuery(){
        service.pageQuery(pageBean);
        this.java2Json(pageBean, new String[]{"functions","users"});
        return NONE;
    }
    
    /**
     * 查询所有的角色信息
     */
    public String listajax(){
        List<Role> list = service.findAll();
        this.java2Json(list, new String[]{"functions","users"});
        return NONE;
    }
}
