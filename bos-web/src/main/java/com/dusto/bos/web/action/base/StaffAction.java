package com.dusto.bos.web.action.base;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Staff;
import com.dusto.bos.service.IStaffService;
import com.dusto.bos.utils.PageBean;

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
    //
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
        staffService.pageQuery(pageBean);
        //将pageBean对象转为json，输出流写回页面
        //
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
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
}
