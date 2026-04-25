package com.rupyy.twf.service;

import com.rupyy.twf.dto.CustomerRequestDTO;
import com.rupyy.twf.dto.APIResponseDTO;
import com.rupyy.twf.entity.Customer;
import com.rupyy.twf.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public APIResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO){

        Customer customerEntity = new Customer();
        BeanUtils.copyProperties(customerRequestDTO,customerEntity);
        Customer savedCustomer = customerRepository.save(customerEntity);

        APIResponseDTO<Object> CustomerResponseDTO = new APIResponseDTO<>();
        BeanUtils.copyProperties(savedCustomer,CustomerResponseDTO);

        return CustomerResponseDTO;
    }

}
