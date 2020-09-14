package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;

@Data
public class Rating {
    private final String rating;
    private final String description;
    private final String course;
    private final String dateAdded;
    public Rating(String rating, String description, String course, String dateAdded) {
        this.rating = rating;
        this.description = description;
        this.course = course;
        this.dateAdded = dateAdded;
    }
}