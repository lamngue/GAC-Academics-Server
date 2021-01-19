package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
public class Rating {
    @Id
    private String id;
    private final String rating;
    private final String description;
    private final String course;
    private final String gradeReceived;
    private final String currentGPA;
    private final String dateAdded;
    private ArrayList<String> likedBy;
    private ArrayList<String> dislikedBy;
    private Integer likes;
    private Integer dislikes;
    public Rating(String rating, String description, String course, String gradeReceived, String currentGPA, String dateAdded) {
        this.rating = rating;
        this.description = description;
        this.course = course;
        this.gradeReceived = gradeReceived;
        this.currentGPA = currentGPA;
        this.dateAdded = dateAdded;
        this.likedBy = new ArrayList<>();
        this.dislikedBy = new ArrayList<>();
        this.likes = 0;
        this.dislikes = 0;
        id = null;
    }
}