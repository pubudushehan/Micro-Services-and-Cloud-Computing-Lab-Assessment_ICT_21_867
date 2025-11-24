package com.portal.notification.service;

import com.portal.notification.dto.EnrollmentNotificationDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    public void sendEnrollmentNotification(EnrollmentNotificationDTO notification) {
        System.out.println("===============================================");
        System.out.println("ENROLLMENT NOTIFICATION");
        System.out.println("Student " + notification.getStudentId() + " enrolled into Course " + notification.getCourseId());
        if (notification.getStudentName() != null && notification.getCourseName() != null) {
            System.out.println("Student Name: " + notification.getStudentName());
            System.out.println("Course Name: " + notification.getCourseName());
        }
        System.out.println("===============================================");
    }
}

