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
    //属性驱动
    private int page;
    private int rows;
    
    /**
     * 分页查询
     * @param page
     * @throws IOException 
     */
    public String pageQuery() throws IOException{
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        //创建离线提交查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);
        //将pageBean对象转为json，输出流写回页面
        //指定那些属性不需要装json
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //属性驱动
    private String ids;
    
    public String deleteBatch(){
        staffService.deleteBatch(ids);
        return LIST;
    }
    
    /**
     * 修改取派员信息
     * @return
     */
    public String edit(){
        Staff staff = staffService.findById(model.getId());
        //使用页面提交的数据进行覆盖
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return LIST;
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
