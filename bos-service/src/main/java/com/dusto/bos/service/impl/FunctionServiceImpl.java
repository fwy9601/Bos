package com.dusto.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IFunctionDao;
import com.dusto.bos.domain.Function;
import com.dusto.bos.domain.User;
import com.dusto.bos.service.IFunctionService;
import com.dusto.bos.utils.BOSUtils;
import com.dusto.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;

    public List<Function> findAll() {
        return functionDao.findAll();
    }

    // 添加权限
    public void save(Function model) {
        Function parentFunction = model.getParentFunction();
        if (parentFunction != null && StringUtils.isBlank(parentFunction.getId())) {
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    /**
     * 根据当前登陆人查询对应的菜单数据，返回json
     */
    public List<Function> findMenu() {
        List<Function> list = null;
        User user = BOSUtils.getLoginUser();
        if(user.getUsername().equals("admin")){
            //如果是超级管理员内置用户，就查询所有菜单
            list = functionDao.findAllMenu();
        }else{
            //其他用户，根据id查询菜单
            list = functionDao.findMenuByUserId(user.getId());
        }
        return list;
    }

}
