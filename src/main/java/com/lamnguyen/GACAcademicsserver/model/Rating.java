package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;

@Data
public class Rating {
    private final String rating;
    private final String description;
    private final String course;
    private final String gradeReceived;
    private final String currentGPA;
    private final String dateAdded;
    public Rating(String rating, String description, String course, String gradeReceived, String currentGPA, String dateAdded) {
        this.rating = rating;
        this.description = description;
        this.course = course;
        this.gradeReceived = gradeReceived;
        this.currentGPA = currentGPA;
        this.dateAdded = dateAdded;
    }
}