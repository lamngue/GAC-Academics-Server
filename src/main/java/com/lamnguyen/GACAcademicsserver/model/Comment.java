package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
public class Comment {
    @Id
    private String id;
    @NotBlank
    private String by;
    @NotBlank
    private String content;
    @NotBlank
    private String dateAdded;

    public Comment(String by, String content, String dateAdded) {
        this.id = new ObjectId().toString();
        this.by = by;
        this.content = content;
        this.dateAdded = dateAdded;
    }
}
