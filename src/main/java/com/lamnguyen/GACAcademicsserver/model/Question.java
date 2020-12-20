package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@Document("GAC-Questions")
public class Question {
    @Id
    private String id;
    @NotBlank
    private String dateAdded;
    @NotBlank
    private String topic;
    @NotBlank
    private String question;
    @NotBlank
    private String by;
    @NotBlank
    private String content;

    private ArrayList<Comment> comments;

    public Question(String dateAdded, String topic, String question, String by, String content, ArrayList<Comment> comments) {
        this.id = new ObjectId().toString();
        this.dateAdded = dateAdded;
        this.topic = topic;
        this.question = question;
        this.by = by;
        this.content = content;
        this.comments = comments;
    }
}
