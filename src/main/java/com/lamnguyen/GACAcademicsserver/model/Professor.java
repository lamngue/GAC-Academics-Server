package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@Document(collection = "GAC")
public class Professor {
    @Id
    private String id;
    @NotBlank
    private String fullName;
    @NotBlank
    private String department;
    @NotBlank
    private String imageLink;

    private ArrayList<Rating> ratings;

    public Professor(String fullName, String department, String imageLink, ArrayList<Rating> ratings) {
        this.id = new ObjectId().toString();
        this.fullName = fullName;
        this.department = department;
        this.imageLink = imageLink;
        this.ratings = ratings;
    }
}
