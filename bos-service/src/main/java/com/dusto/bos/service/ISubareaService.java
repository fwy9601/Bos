package com.dusto.bos.service;

import com.dusto.bos.domain.Subarea;
import com.dusto.bos.utils.PageBean;

public interface ISubareaService {

    public void save(Subarea model);

    public void pageQuery(PageBean pageBean);

}
