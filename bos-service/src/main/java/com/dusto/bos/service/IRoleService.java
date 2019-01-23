package com.dusto.bos.service;

import java.util.List;

import com.dusto.bos.domain.Role;
import com.dusto.bos.utils.PageBean;

public interface IRoleService {

    public void add(Role role, String functionIds);

    public void pageQuery(PageBean pageBean);

    public List<Role> findAll();

}
