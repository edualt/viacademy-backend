package com.example.viacademy.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthController {

    @RequestMapping
    public String health() {
        return "OK";
    }

}
