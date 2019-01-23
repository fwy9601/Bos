package com.dusto.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IRoleDao;
import com.dusto.bos.domain.Function;
import com.dusto.bos.domain.Role;
import com.dusto.bos.service.IRoleService;
import com.dusto.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleDao roleDao; 
    
    /**
     * 保存一个角色，并关联权限
     */
    public void add(Role role, String functionIds) {
        roleDao.save(role);
        if(StringUtils.isNoneBlank(functionIds)){
            String[] fIds = functionIds.split(",");
            for (String functionId : fIds) {
                Function function = new Function();
                function.setId(functionId);
                //角色关联权限
                role.getFunctions().add(function);
            }
        }
    }

    /**
     * 页面查询
     */
    public void pageQuery(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
    
}
