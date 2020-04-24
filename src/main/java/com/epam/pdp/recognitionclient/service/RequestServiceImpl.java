package com.epam.pdp.recognitionclient.service;

import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;
import com.epam.pdp.recognitionclient.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService{

    private RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Set<RecognitionRequest> getRequestsList(){
        return requestRepository.findAll();
    }
}
