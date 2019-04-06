package com.example.demo.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ScheduleServiceHiHystric implements ScheduleServiceHi {
    @Override
    public String sayHiFromClientOne(@RequestParam(value = "name") String name) {
        return "sorry," + name;
    }
}