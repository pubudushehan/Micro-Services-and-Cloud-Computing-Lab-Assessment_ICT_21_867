package com.portal.notification.dto;

public class EnrollmentNotificationDTO {
    
    private Long studentId;
    private Long courseId;
    private String studentName;
    private String courseName;
    
    // Constructors
    public EnrollmentNotificationDTO() {}
    
    public EnrollmentNotificationDTO(Long studentId, Long courseId, String studentName, String courseName) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
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
}

