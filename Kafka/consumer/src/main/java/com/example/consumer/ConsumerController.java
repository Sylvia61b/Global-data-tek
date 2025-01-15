package com.example.consumer;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConsumerController {

    @GetMapping("/status")
    public String checkStatus() {
        return "Consumer is running and listening to Kafka topic.";
    }
}
