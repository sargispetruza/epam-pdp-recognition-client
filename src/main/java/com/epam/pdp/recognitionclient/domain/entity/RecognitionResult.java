package com.epam.pdp.recognitionclient.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "results")
@Data
public class RecognitionResult {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String requestId;
    private String recogResut;

    public RecognitionResult() {
    }

    public RecognitionResult(String requestId, String recogResut) {
        this.requestId = requestId;
        this.recogResut = recogResut;
    }
}
