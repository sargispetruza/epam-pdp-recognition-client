package com.epam.pdp.recognitionclient.service;

import com.epam.pdp.recognitionclient.domain.dto.ImLink;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionResult;
import com.epam.pdp.recognitionclient.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TextRecognitionService {

    private AmqpTemplate template;
    private RequestRepository requestRepository;
    private TopicExchange topicExchange;
    private RestTemplate restTemplate;
    @Value("${recognition.service.reports.link}")
    private String reportsLink;

    public TextRecognitionService(AmqpTemplate template, RequestRepository requestRepository, TopicExchange topicExchange, RestTemplate restTemplate) {
        this.template = template;
        this.requestRepository = requestRepository;
        this.topicExchange = topicExchange;
        this.restTemplate = restTemplate;
    }


    public String processTextRecognitionRequest(ImLink imageLink){
        RecognitionRequest recognitionRequest = new RecognitionRequest(UUID.randomUUID().toString(), imageLink.getImageLink(), LocalDateTime.now());

        template.convertAndSend(topicExchange.getName(), "text.", recognitionRequest);
        requestRepository.save(recognitionRequest);
        return recognitionRequest.getRequestId();
    }

    public RecognitionResult obtainReport(String requestId){
        String link = reportsLink+requestId;
        return restTemplate.getForObject(
                link, RecognitionResult.class);
    }

}
