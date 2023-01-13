package ru.itmo.hps.lab1.chat.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EurekaController {

    private final DiscoveryClient discoveryClient;

//    @GetMapping("/services/{applicationName}")
//    public List<ServiceInstance> serviceInstancesByApplicationName(
//            @PathVariable String applicationName) {
//        return this.discoveryClient.getInstances(applicationName);
//    }
//
//    @GetMapping("/services")
//    public List<String> servicesList() {
//        return this.discoveryClient.getServices();
//    }

}
