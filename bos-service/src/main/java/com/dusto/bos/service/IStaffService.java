package com.dusto.bos.service;

import com.dusto.bos.domain.Staff;
import com.dusto.bos.utils.PageBean;

public interface IStaffService {

    public void save(Staff model);

    public void pageQuery(PageBean pageBean);

    public void deleteBatch(String ids);

}
