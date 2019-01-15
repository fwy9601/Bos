package com.dusto.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.dusto.crm.domain.Customer;

@WebService
public interface ICustomerService {
    public List<Customer> findAll();

    /**
     * 查询未关联到定区的客户
     * 
     * @return
     */
    public List<Customer> findListNotAssociation();

    /**
     * 查询关联到指定定区的客户
     * 
     * @param decidedzoneId
     * @return
     */
    public List<Customer> findListHasAssociation(String decidedzoneId);

    /**
     * 定去关联客户
     * 
     * @param decidedzoneId
     * @param customerIds
     */
    public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds);
    
    /**
     * 根据手机号查询客户信息
     */
    public Customer findCustomerByTelephone(String telephone);
    /**
     * 根据客户地址信息查询定去id
     */
    public String findDecidedzoneIdByAddress(String address);
}
