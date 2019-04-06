package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by BlueBreeze on 2019/3/24.
 */
@FeignClient(value = "service-hi",fallback = ScheduleServiceHiHystric.class)
public interface ScheduleServiceHi {
    @GetMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
