package com.dusto.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Decidedzone;
import com.dusto.bos.service.DecidedzoneService;
import com.dusto.bos.web.action.base.BaseAction;

/**
 * 定区管理
 * @author 13302
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{
    //属性驱动，接受多个分区id
    private String[] subareaid;
    
    @Autowired
    private DecidedzoneService decidedzoneService;
    
    public String add(){
        decidedzoneService.add(model,subareaid);
        return LIST;
    }
    
    
    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }
    
}

