package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Region;

public interface IRegionService {

    public void saveBatch(List<Region> regionList);

}
