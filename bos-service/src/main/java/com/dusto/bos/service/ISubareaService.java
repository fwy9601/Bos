package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Subarea;
import com.dusto.bos.utils.PageBean;

public interface ISubareaService {

    public void save(Subarea model);

    public void pageQuery(PageBean pageBean);

    public List<Subarea> findAll();

    public List<Subarea> findListNotAssociation();
    
    /**
     * 根据定区id查询关联的分区
     */
    public List<Subarea> findListByDecidedzoneId(String decidedzoneId);

    public List<Object> findsubareasGroupByProvince();

}
