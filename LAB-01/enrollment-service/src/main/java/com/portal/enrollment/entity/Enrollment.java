package com.portal.enrollment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
    private String status;
    
    // Constructors
    public Enrollment() {
        this.enrollmentDate = LocalDateTime.now();
        this.status = "ACTIVE";
    }
    
    public Enrollment(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDateTime.now();
        this.status = "ACTIVE";
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

