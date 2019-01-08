package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Staff;
import com.dusto.bos.utils.PageBean;

public interface IStaffService {

    public void save(Staff model);

    public void pageQuery(PageBean pageBean);

    public void deleteBatch(String ids);

    public Staff findById(String id);

    public void update(Staff staff);

    public List<Staff> findListNotDelete();

}
