package com.dusto.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.dusto.crm.domain.Customer;

@WebService
public interface ICustomerService {
    public List<Customer> findAll();

    // 查询未关联到定区的客户
    public List<Customer> findListNotAssociation();

    /**
     * 查询已关联到定区的客户
     * decidedzoneId定区Id
     */
    public List<Customer> findListHasAssociation(String decidedzoneId);
}
