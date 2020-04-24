package com.epam.pdp.recognitionclient.controller;

import com.epam.pdp.recognitionclient.domain.dto.ImLinkDto;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionResult;
import com.epam.pdp.recognitionclient.service.RequestService;
import com.epam.pdp.recognitionclient.service.recognition.RecognitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/recognition")
@Slf4j
public class TextRecognitionController {

    private RecognitionService textRecognitionService;
    private RequestService requestService;
    private Integer lastRequestId;

    public TextRecognitionController(RecognitionService textRecognitionService, RequestService requestService) {
        this.textRecognitionService = textRecognitionService;
        this.requestService = requestService;
    }

    @GetMapping("/text")
    public String greetingForm(Model model) {
        model.addAttribute("imLinkDto", new ImLinkDto());
        return "text";
    }

    @PostMapping("/text")
    public String textRecognitionByLink(@ModelAttribute ImLinkDto imLinkDto) {
        lastRequestId = textRecognitionService.processRecognitionRequest(imLinkDto);
        return "text";
    }

    @GetMapping("/text/last")
    public @ResponseBody RecognitionResult getLastReport(){
        return textRecognitionService.obtainReport(lastRequestId);

    }

    @GetMapping("/text/{requestId}")
    public @ResponseBody RecognitionResult getReport(@PathVariable Integer requestId){
        return textRecognitionService.obtainReport(requestId);
    }

    @GetMapping("/text/requests/list")
    public @ResponseBody Set<RecognitionRequest> getRequestsList(){
        return requestService.getRequestsList();

    }

}