package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Function;
import com.dusto.bos.utils.PageBean;

public interface IFunctionService {

    public List<Function> findAll();

    public void save(Function model);

    public void pageQuery(PageBean pageBean);

}
