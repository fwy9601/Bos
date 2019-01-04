package com.dusto.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.dusto.crm.domain.Customer;

@WebService
public interface ICustomerService {
    public List<Customer> findAll();
}
