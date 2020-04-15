package com.epam.pdp.recognitionclient.controller;

import com.epam.pdp.recognitionclient.domain.dto.ImLink;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionResult;
import com.epam.pdp.recognitionclient.repository.RequestRepository;
import com.epam.pdp.recognitionclient.service.TextRecognitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@RestController
@RequestMapping("/recognition")
@Slf4j
public class TextRecognitionController {

    private RequestRepository requestRepository;
    private TextRecognitionService textRecognitionService;

    private String lastRequestId;

    public TextRecognitionController(RequestRepository requestRepository, TextRecognitionService textRecognitionService) {
        this.requestRepository = requestRepository;
        this.textRecognitionService = textRecognitionService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/text")
    public String textRecognitionByLink(@RequestBody ImLink imageLink) {
        log.info("Emit to the text recognition queues");
        lastRequestId = textRecognitionService.processTextRecognitionRequest(imageLink);
        return "Emit to the text recognition queues";
    }

    @RequestMapping("/text/last")
    public RecognitionResult getLastReport(){
        return textRecognitionService.obtainReport(lastRequestId);

    }

    @RequestMapping("/text/{requestId}")
    public RecognitionResult getReport(@PathVariable String requestId){
        return textRecognitionService.obtainReport(requestId);
    }

    @RequestMapping("/text/requests/list")
    public Set<RecognitionRequest> getRequestsList(){
        return requestRepository.findAll();

    }

}