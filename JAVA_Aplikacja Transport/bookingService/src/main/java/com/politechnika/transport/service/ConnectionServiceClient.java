package com.politechnika.transport.service;

import com.politechnika.transport.dto.ConnectionDto;
import com.politechnika.transport.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "TRAIN-SERVICE", path = "/connections")
//@RequestMapping("/connections")
public interface ConnectionServiceClient {

    @GetMapping("/{connectionId}")
    ConnectionDto getConnectionById(@PathVariable String connectionId);

}