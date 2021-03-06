package com.dusto.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Noticebill;
import com.dusto.bos.service.INoticebillService;
import com.dusto.bos.web.action.base.BaseAction;
import com.dusto.crm.Customer;
import com.dusto.crm.ICustomerService;

/**
 * 业务通知单管理
 * @author dusto
 *
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
    
    @Autowired
    private ICustomerService customerService;
    
    @Autowired
    private INoticebillService noticebillService;
    
    /**
     * 远程调用crm服务，根据手机号来查询客户信息
     * @return
     */
    public String findCustomerByTelephone(){
        Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
        java2Json(customer, new String[]{});
        return NONE;
    }
    
    /**
     * 保存一个业务通知单，并尝试自动分单
     * @return
     */
    public String add(){
        noticebillService.save(model);
        return "noticebill_add";
    }
    
}
