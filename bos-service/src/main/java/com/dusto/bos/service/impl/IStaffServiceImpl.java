package com.dusto.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.base.IStaffDao;
import com.dusto.bos.domain.Staff;
import com.dusto.bos.service.IStaffService;

@Service
@Transactional
public class IStaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffdao; 
    
    public void save(Staff model) {
        staffdao.save(model);
    }

}
