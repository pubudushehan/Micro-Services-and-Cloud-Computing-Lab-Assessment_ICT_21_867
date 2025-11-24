package com.portal.result.dto;

import java.time.LocalDateTime;

public class ResultResponseDTO {
    
    private Long id;
    private Long studentId;
    private Long courseId;
    private String studentName;
    private String courseName;
    private String grade;
    private Double score;
    private LocalDateTime recordedDate;
    
    // Constructors
    public ResultResponseDTO() {}
    
    public ResultResponseDTO(Long id, Long studentId, Long courseId, 
                            String studentName, String courseName,
                            String grade, Double score, LocalDateTime recordedDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.grade = grade;
        this.score = score;
        this.recordedDate = recordedDate;
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
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

