package com.dusto.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Function;
import com.dusto.bos.service.IFunctionService;
import com.dusto.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    
    @Autowired
    private IFunctionService functionService;
    
    /**
     * 查询所有权限，放回json数据
     */
    public String ajaxlist(){
        List<Function> list = functionService.ajaxlist();
        java2Json(list, new String[]{"parentFunction","roles","children"});
        return NONE;
    }
    
    /**
     * 添加权限
     */
    public String add(){
        functionService.save(model);
        return LIST;
    }
}
