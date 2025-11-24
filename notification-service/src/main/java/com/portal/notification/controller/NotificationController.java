package com.portal.notification.controller;

import com.portal.notification.dto.EnrollmentNotificationDTO;
import com.portal.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notify")
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @PostMapping("/enrollment")
    public ResponseEntity<Map<String, String>> notifyEnrollment(@RequestBody EnrollmentNotificationDTO notification) {
        notificationService.sendEnrollmentNotification(notification);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Notification sent successfully");
        response.put("studentId", String.valueOf(notification.getStudentId()));
        response.put("courseId", String.valueOf(notification.getCourseId()));
        
        return ResponseEntity.ok(response);
    }
}

