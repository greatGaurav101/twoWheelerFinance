package com.rupyy.lender.client;


import com.rupyy.lender.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name="USER-SERVICE",url = "http://localhost:8085/api/v1/customer")
public interface UserClient {

    @GetMapping("/getUserByid")
    Customer getDetails(@RequestParam UUID id);

}
