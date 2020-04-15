package com.epam.pdp.recognitionclient.repository;

import com.epam.pdp.recognitionclient.domain.entity.RecognitionRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RequestRepository extends CrudRepository<RecognitionRequest, Integer> {
    Set<RecognitionRequest> findAll();
}
