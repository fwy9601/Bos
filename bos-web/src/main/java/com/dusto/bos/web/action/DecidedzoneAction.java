package com.dusto.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Decidedzone;
import com.dusto.bos.service.DecidedzoneService;
import com.dusto.bos.web.action.base.BaseAction;
import com.dusto.crm.Customer;
import com.dusto.crm.ICustomerService;

/**
 * 定区管理
 * @author 13302
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{
    
    @Autowired
    private ICustomerService proxy;
    
    //属性驱动，接受多个分区id
    private String[] subareaId;
    
    @Autowired
    private DecidedzoneService decidedzoneService;
    
    public String add(){
        decidedzoneService.add(model,subareaId);
        return LIST;
    }
    
    /**
     * 分页查询
     * 
     * @param page
     * @throws IOException
     */
    public String pageQuery() throws IOException {
        decidedzoneService.pageQuery(pageBean);
        this.java2Json(pageBean, new String[] { "currentPage", "pageSize", "detachedCriteria","subareas","decidedzones" });
        return NONE;
    }
    
    /**
     * 远程调用crm，获取未关联的定区的客户
     * @return
     */
    public String findListNotAssociation(){
        List<Customer> list = proxy.findListNotAssociation();
        java2Json(list, new String[]{});
        return NONE;
    }

    /**
     * 远程调用crm，获取已关联的定区的客户
     * @return
     */
    public String findListHasAssociation(){
        List<Customer> list = proxy.findListHasAssociation(model.getId());
        java2Json(list, new String[]{});
        return NONE;
    }
    
    //属性驱动，接受页面提交的多个id
    private List<Integer> customerIds;
    
   
    /**
     * 远程调用crm，将客户关联到定区
     * @return
     */
    public String assigncustomerstodecidedzone(){
        proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
        return NONE;
    }
    
    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }
    
    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

}

