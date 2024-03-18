package com.kafka.parsingapplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParseKafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void convertAndSend(MultipartFile file) throws IOException {
        XWPFDocument document = new XWPFDocument(file.getInputStream());
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        List<String> paragraphTexts = paragraphs.stream()
                .map(XWPFParagraph::getText)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(paragraphTexts);
        kafkaTemplate.send("parse", json);
    }

}
