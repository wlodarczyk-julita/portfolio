package com.politechnika.transport.service;

import com.politechnika.transport.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "USER-SERVICE", path = "/users")
//@RequestMapping("/users")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    UserDto getUserById(@PathVariable String userId);

}
