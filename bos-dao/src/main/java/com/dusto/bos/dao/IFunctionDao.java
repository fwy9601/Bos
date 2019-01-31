package com.dusto.bos.dao;

import java.util.List;

import com.dusto.bos.dao.base.IBaseDao;
import com.dusto.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {

    List<Function> findFunctionListByUserId(String id);

    List<Function> findMenuByUserId(String userId);

    List<Function> findAllMenu();
    
}
