package com.dusto.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Staff;
import com.dusto.bos.service.IStaffService;
import com.dusto.bos.utils.PageBean;
import com.dusto.bos.web.action.base.BaseAction;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 取派员管理
 * 
 * @author dusto
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;

    public String add() {
        staffService.save(model);
        return LIST;
    }

    /**
     * 分页查询
     * 
     * @param page
     * @throws IOException
     */
    public String pageQuery() throws IOException {
        staffService.pageQuery(pageBean);
        this.java2Json(pageBean, new String[] { "currentPage", "pageSize", "detachedCriteria" });
        return NONE;
    }

    // 属性驱动
    private String ids;

    public String deleteBatch() {
        staffService.deleteBatch(ids);
        return LIST;
    }

    /**
     * 修改取派员信息
     * 
     * @return
     */
    public String edit() {
        Staff staff = staffService.findById(model.getId());
        // 使用页面提交的数据进行覆盖
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return LIST;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
