package com.jurik99.resource;

import lombok.extern.log4j.Log4j2;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class ServiceInstanceResource {

    private final DiscoveryClient discoveryClient;

    public ServiceInstanceResource(final DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/ping")
    public List<ServiceInstance> ping() {
        final List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
        log.info("INSTANCES: count={}", instances.size());

        instances.forEach(instance -> log.info("INSTANCE: id={}, port={}",
                                               instance.getServiceId(), instance.getPort()));
        return instances;
    }
}
