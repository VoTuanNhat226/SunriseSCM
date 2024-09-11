package com.vtn.services;

import com.vtn.dto.customer.CustomerDTO;
import com.vtn.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    Customer findById(Long id);

    void save(Customer customer);

    void update(Customer customer);

    void delete(Long id);

    Long count();

    List<Customer> findAllWithFilter(Map<String, String> params);

    CustomerDTO getCustomerResponse(Customer customer);

    Customer getProfileCustomer(String username);

    CustomerDTO updateProfileCustomer(String username, CustomerDTO customerDTO);
}
