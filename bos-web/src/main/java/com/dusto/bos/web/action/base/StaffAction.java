package com.dusto.bos.web.action.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Staff;
import com.dusto.bos.service.IStaffService;

/**
 * 取派员管理
 * @author dusto
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;
    
    public String add(){
        staffService.save(model);
        return LIST;
    }
}
