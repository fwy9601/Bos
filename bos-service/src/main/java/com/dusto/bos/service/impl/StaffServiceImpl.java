package com.dusto.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.base.IStaffDao;
import com.dusto.bos.domain.Staff;
import com.dusto.bos.service.IStaffService;
import com.dusto.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffdao; 
    
    public void save(Staff model) {
        staffdao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        staffdao.pageQuery(pageBean);
    }

    /**
     * 取派员批量删除
     * 逻辑删除，将deltag改为1
     */
    public void deleteBatch(String ids) {
        if(StringUtils.isNotBlank(ids)){
            String[] split = ids.split(",");
            for (String id : split) {
                staffdao.executeUpdate("staff.delete", id);
            }
        }
    }

    /**
     * 根据id查询取派员
     */
    public Staff findById(String id) {
        return staffdao.findById(id);
    }

    /**
     * 根据id修改取派员
     */
    public void update(Staff staff) {
        staffdao.update(staff);
    }

    /**
     * 查询所有未删除的取派员，返回json
     * @return
     */
    public List<Staff> findListNotDelete() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        //添加过滤条件,deltag=0 未删除
        detachedCriteria.add(Restrictions.eq("deltag", "0"));
        return staffdao.findByCriteria(detachedCriteria);
    }

}
