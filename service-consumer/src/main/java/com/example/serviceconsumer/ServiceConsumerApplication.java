package com.example.serviceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
    public class TestController {

        private final RestTemplate template;

        @Autowired
        public TestController(RestTemplate restTemplate) {
            this.template = restTemplate;
        }

        @GetMapping("/echo/{str}")
        public String echo(@PathVariable String str) {
//            ServiceInstance choose = loadBalancerClient.choose("service-provider");
//            return template.getForObject(choose.getUri() + "/remote/echo/" + str, String.class);
            return template.getForObject( "http://service-provider/remote/echo/" + str, String.class);
        }

    }

}
