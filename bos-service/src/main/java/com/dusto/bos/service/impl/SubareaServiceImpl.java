package com.dusto.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.ISubareaDao;
import com.dusto.bos.domain.Subarea;
import com.dusto.bos.service.ISubareaService;
import com.dusto.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    @Autowired
    private ISubareaDao subareaDao;
    
    /**
     * 添加分区
     */
    public void save(Subarea model) {
        subareaDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

}
