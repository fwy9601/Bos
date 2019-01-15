package com.dusto.bos.web.action.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Noticebill;
import com.dusto.crm.Customer;
import com.dusto.crm.ICustomerService;

/**
 * 业务通知单管理
 * @author dusto
 *
 */
@Controller
@Scope("prototype")
public class NoticebilAction extends BaseAction<Noticebill> {
    
    @Autowired
    private ICustomerService customerService;
    
    public String findCustomerByTelephone(){
        Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
        java2Json(customer, new String[]{});
        return NONE;
    }
    
}
