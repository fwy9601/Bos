package com.dusto.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IWorkordermanageDao;
import com.dusto.bos.domain.Workordermanage;
import com.dusto.bos.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

    @Autowired
    private IWorkordermanageDao workordermanageDao; 
    
    public void save(Workordermanage model) {
        workordermanageDao.save(model);
    }

}
