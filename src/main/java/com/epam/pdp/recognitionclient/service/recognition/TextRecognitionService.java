package com.epam.pdp.recognitionclient.service.recognition;

import com.epam.pdp.recognitionclient.domain.dto.ImLinkDto;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionResult;
import com.epam.pdp.recognitionclient.repository.RequestRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TextRecognitionService implements RecognitionService{

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

    @Override
    public Integer processRecognitionRequest(ImLinkDto imageLink) {
        RecognitionRequest recognitionRequest = new RecognitionRequest(imageLink.getImageLink(), LocalDateTime.now());
        recognitionRequest = requestRepository.save(recognitionRequest);
        template.convertAndSend(topicExchange.getName(), "text.", recognitionRequest);

        return recognitionRequest.getId();
    }

    @Override
    public RecognitionResult obtainReport(Integer requestId){
        String link = reportsLink+requestId;
        return restTemplate.getForObject(
                link, RecognitionResult.class);
    }
}
