package com.dusto.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dusto.bos.dao.IFunctionDao;
import com.dusto.bos.domain.Function;
import com.dusto.bos.service.IFunctionService;

@Service
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;
    
    @Override
    public List<Function> ajaxlist() {
        return functionDao.findAll();
    }

}
