package com.example.demo.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BlueBreeze on 2019/3/24.
 */
@RestController
public class HiController {
    @Autowired
    ScheduleServiceHi scheduleServiceHi;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam(value = "name") String name) {
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}
