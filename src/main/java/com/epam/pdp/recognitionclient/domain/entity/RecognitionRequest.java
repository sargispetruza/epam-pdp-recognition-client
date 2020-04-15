package com.epam.pdp.recognitionclient.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "requests")
@Data
public class RecognitionRequest implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String requestId;
    private String imageLink;
    //TODO: додати дату створення


    public RecognitionRequest() {
    }

    public RecognitionRequest(String requestId, String imageLink) {
        this.requestId = requestId;
        this.imageLink = imageLink;
    }
}
