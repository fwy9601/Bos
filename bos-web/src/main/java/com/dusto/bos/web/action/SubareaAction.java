package com.dusto.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Subarea;
import com.dusto.bos.service.ISubareaService;
import com.dusto.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    /**
     * 添加分区
     * 
     * @return
     */
    public String add() {
        subareaService.save(model);
        return LIST;
    }

    /**
     * 分页查询
     * 
     * @return
     */
    public String pageQuery() {
        subareaService.pageQuery(pageBean);
        this.java2Json(pageBean,
                new String[] { "currentPage", "pageSize", "detachedCriteria", "decidedzone", "subareas" });
        return NONE;
    }
}
