package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Semester {
    private final String semester;
    private final ArrayList<String> course;
    public Semester(String semester, ArrayList<String> course) {
        this.semester = semester;
        this.course = course;
    }
}
