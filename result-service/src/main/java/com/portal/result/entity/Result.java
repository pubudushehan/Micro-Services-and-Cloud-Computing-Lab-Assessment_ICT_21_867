package com.portal.result.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long studentId;
    private Long courseId;
    private String grade;
    private Double score;
    private LocalDateTime recordedDate;
    
    // Constructors
    public Result() {
        this.recordedDate = LocalDateTime.now();
    }
    
    public Result(Long studentId, Long courseId, String grade, Double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.score = score;
        this.recordedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public Double getScore() {
        return score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }
    
    public LocalDateTime getRecordedDate() {
        return recordedDate;
    }
    
    public void setRecordedDate(LocalDateTime recordedDate) {
        this.recordedDate = recordedDate;
    }
}

