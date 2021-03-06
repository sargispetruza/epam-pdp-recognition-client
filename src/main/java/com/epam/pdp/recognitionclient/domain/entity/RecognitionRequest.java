package com.epam.pdp.recognitionclient.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Data
public class RecognitionRequest implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String imageLink;
    private LocalDateTime dateTime;


    public RecognitionRequest() {
    }

    public RecognitionRequest(String imageLink, LocalDateTime dateTime) {
        this.imageLink = imageLink;
        this.dateTime = dateTime;
    }
}
