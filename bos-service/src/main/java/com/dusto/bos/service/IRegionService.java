package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Region;
import com.dusto.bos.utils.PageBean;

public interface IRegionService {

    public void saveBatch(List<Region> regionList);

    public void pageQuery(PageBean pageBean);

}
