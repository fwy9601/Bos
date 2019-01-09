package com.dusto.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IDecidedzoneDao;
import com.dusto.bos.dao.ISubareaDao;
import com.dusto.bos.domain.Decidedzone;
import com.dusto.bos.domain.Subarea;
import com.dusto.bos.service.DecidedzoneService;
import com.dusto.bos.service.ISubareaService;
import com.dusto.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {

    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    
    @Autowired
    private ISubareaDao subareaDao;
    
    /**
     * 添加定区，关联分区
     */
    public void add(Decidedzone model, String[] subareaid) {
        decidedzoneDao.save(model);
        for (String id : subareaid) {
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(model);
        }
    }

    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }

}
