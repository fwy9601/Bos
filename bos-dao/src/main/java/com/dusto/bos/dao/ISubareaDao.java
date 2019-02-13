package com.dusto.bos.dao;

import java.util.List;

import com.dusto.bos.dao.base.IBaseDao;
import com.dusto.bos.domain.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {

    List<Object> findsubareasGroupByProvince();

}
