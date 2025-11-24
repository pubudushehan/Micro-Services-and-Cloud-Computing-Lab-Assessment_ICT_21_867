package com.portal.enrollment.dto;

import java.time.LocalDateTime;

public class EnrollmentResponseDTO {
    
    private Long id;
    private Long studentId;
    private Long courseId;
    private String studentName;
    private String courseName;
    private LocalDateTime enrollmentDate;
    private String status;
    
    // Constructors
    public EnrollmentResponseDTO() {}
    
    public EnrollmentResponseDTO(Long id, Long studentId, Long courseId, 
                                 String studentName, String courseName,
                                 LocalDateTime enrollmentDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
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
    
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}

