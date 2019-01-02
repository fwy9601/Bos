package com.dusto.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IRegionDao;
import com.dusto.bos.domain.Region;
import com.dusto.bos.service.IRegionService;
import com.dusto.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService{

    @Autowired
    private IRegionDao regionDao;
    
    /**
     * 区域数据批量保存
     */
    public void saveBatch(List<Region> regionList) {
        for (Region region : regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    /**
     * 区域分页查询
     */
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    public List<Region> findAll() {
        return regionDao.findAll();
    }

    /**
     * 根据页面输入进行模糊查询
     */
    public List<Region> findListByQ(String q) {
        return regionDao.findListByQ(q);
    }

}
