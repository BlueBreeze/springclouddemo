package com.example.demo.serviceblue;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceBlueApplication {

    Logger logger = LoggerFactory.getLogger(ServiceBlueApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceBlueApplication.class, args);
    }


    @RequestMapping("/info")
    public String info() {
        return "service blue...";
    }

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hi-hi")
    public String hiHi(@RequestParam(value = "name", defaultValue = "hi") String name) {
        logger.info("calling service-hi hi.");
        return restTemplate.getForObject("http://localhost:8762/hi?name=" + name, String.class);
    }

    @RequestMapping("/hi")
    public String hiBlue() {
        return "I'm service blue";
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
