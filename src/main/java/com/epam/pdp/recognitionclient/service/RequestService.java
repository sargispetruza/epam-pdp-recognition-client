package com.epam.pdp.recognitionclient.service;

import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;

import java.util.Set;

public interface RequestService {
    Set<RecognitionRequest> getRequestsList();
}
