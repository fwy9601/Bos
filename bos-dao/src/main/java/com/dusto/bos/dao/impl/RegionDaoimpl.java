package com.dusto.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dusto.bos.dao.IRegionDao;
import com.dusto.bos.domain.Region;

@Repository
public class RegionDaoimpl extends BaseDaoImpl<Region> implements IRegionDao {

    /**
     * 根据q参数进行模糊查询
     */
    public List<Region> findListByQ(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ? OR r.citycode LIKE ? OR r.province LIKE ? OR r.city LIKE ? OR r.district LIKE ? ";

        List<Region> list = (List<Region>)this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%",
                "%" + q + "%");
        return list;
    }

}
