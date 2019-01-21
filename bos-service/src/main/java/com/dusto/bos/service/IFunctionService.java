package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Function;

public interface IFunctionService {

    public List<Function> ajaxlist();

    public void save(Function model);


}
