package com.example.demo.servicehi;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServiceHiApplication {
    static Logger logger = LoggerFactory.getLogger(ServiceHiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam(value = "name", defaultValue = "blue") String name) {
        return String.format("hi,my name is %s,I'm from port %s.", name, port);
    }

    public String hiError(String name) {
        return "hi," + name + ", error!";
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hi-blue")
    public String hiBlue() {
        logger.info("calling trace service-hi");
        return restTemplate.getForObject("http://localhost:8766/hi", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        logger.info("calling trace service-hi");
        return "I'm service-hi";
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
