package com.example.interview.controller;

import com.example.interview.service.SelfDividingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SelfDividingController {

    @Autowired
    private SelfDividingService service;

    @GetMapping("/self-dividing/{number}")
    public Map<String, Object> checkSelfDividing(@PathVariable int number) {
        boolean result = service.isSelfDividing(number);
        Map<String, Object> response = new HashMap<>();
        response.put("number", number);
        response.put("isSelfDividing", result);
        return response;
    }
}