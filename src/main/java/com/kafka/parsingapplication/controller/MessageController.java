package com.kafka.parsingapplication.controller;

import com.kafka.parsingapplication.service.ParseKafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class MessageController {
    private final ParseKafkaService kafkaService;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        kafkaService.upload(file);
        return "File upload successfully";
    }
}
