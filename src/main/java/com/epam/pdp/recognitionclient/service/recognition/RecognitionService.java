package com.epam.pdp.recognitionclient.service.recognition;

import com.epam.pdp.recognitionclient.domain.dto.ImLinkDto;
import com.epam.pdp.recognitionclient.domain.entity.RecognitionResult;

public interface RecognitionService {

    Integer processRecognitionRequest(ImLinkDto imageLink);

    RecognitionResult obtainReport(Integer requestId);

}
