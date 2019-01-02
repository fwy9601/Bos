package com.dusto.bos.dao;

import java.util.List;

import com.dusto.bos.dao.base.IBaseDao;
import com.dusto.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region> {

    List<Region> findListByQ(String q);

}
