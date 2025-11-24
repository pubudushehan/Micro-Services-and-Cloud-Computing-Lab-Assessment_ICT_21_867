package com.portal.result.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResultRequestDTO {
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotNull(message = "Course ID is required")
    private Long courseId;
    
    @NotBlank(message = "Grade is required")
    private String grade;
    
    private Double score;
    
    // Constructors
    public ResultRequestDTO() {}
    
    public ResultRequestDTO(Long studentId, Long courseId, String grade, Double score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.score = score;
    }
    
    // Getters and Setters
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
}

