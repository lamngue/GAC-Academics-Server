package com.lamnguyen.GACAcademicsserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@Document(collection = "GAC-Student")
public class Student {
    @Id
    private String id;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;

    private ArrayList<Semester> classesPlan;

    public Student(String id, String startDate, String endDate, ArrayList<Semester> classesPlan) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classesPlan = classesPlan;
    }
}
