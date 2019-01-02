package com.dusto.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
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

}
