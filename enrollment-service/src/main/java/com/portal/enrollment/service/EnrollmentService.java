package com.portal.enrollment.service;

import com.portal.enrollment.dto.*;
import com.portal.enrollment.entity.Enrollment;
import com.portal.enrollment.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${student.service.url}")
    private String studentServiceUrl;
    
    @Value("${course.service.url}")
    private String courseServiceUrl;
    
    @Value("${notification.service.url}")
    private String notificationServiceUrl;
    
    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO request) {
        // Validate student exists
        StudentDTO student = validateStudent(request.getStudentId());
        
        // Validate course exists
        CourseDTO course = validateCourse(request.getCourseId());
        
        // Create enrollment
        Enrollment enrollment = new Enrollment(request.getStudentId(), request.getCourseId());
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        
        // Send notification
        sendEnrollmentNotification(student, course);
        
        // Create response
        return new EnrollmentResponseDTO(
                savedEnrollment.getId(),
                savedEnrollment.getStudentId(),
                savedEnrollment.getCourseId(),
                student.getName(),
                course.getCourseName(),
                savedEnrollment.getEnrollmentDate(),
                savedEnrollment.getStatus()
        );
    }
    
    public List<EnrollmentResponseDTO> getEnrollmentsByStudentId(Long studentId) {
        // Validate student exists
        validateStudent(studentId);
        
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        
        return enrollments.stream()
                .map(enrollment -> {
                    StudentDTO student = getStudent(enrollment.getStudentId());
                    CourseDTO course = getCourse(enrollment.getCourseId());
                    
                    return new EnrollmentResponseDTO(
                            enrollment.getId(),
                            enrollment.getStudentId(),
                            enrollment.getCourseId(),
                            student != null ? student.getName() : "Unknown",
                            course != null ? course.getCourseName() : "Unknown",
                            enrollment.getEnrollmentDate(),
                            enrollment.getStatus()
                    );
                })
                .collect(Collectors.toList());
    }
    
    private StudentDTO validateStudent(Long studentId) {
        try {
            String url = studentServiceUrl + "/students/" + studentId;
            StudentDTO student = restTemplate.getForObject(url, StudentDTO.class);
            
            if (student == null) {
                throw new RuntimeException("Student not found with ID: " + studentId);
            }
            return student;
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate student: " + e.getMessage());
        }
    }
    
    private CourseDTO validateCourse(Long courseId) {
        try {
            String url = courseServiceUrl + "/courses/" + courseId;
            CourseDTO course = restTemplate.getForObject(url, CourseDTO.class);
            
            if (course == null) {
                throw new RuntimeException("Course not found with ID: " + courseId);
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate course: " + e.getMessage());
        }
    }
    
    private StudentDTO getStudent(Long studentId) {
        try {
            String url = studentServiceUrl + "/students/" + studentId;
            return restTemplate.getForObject(url, StudentDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
    
    private CourseDTO getCourse(Long courseId) {
        try {
            String url = courseServiceUrl + "/courses/" + courseId;
            return restTemplate.getForObject(url, CourseDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
    
    private void sendEnrollmentNotification(StudentDTO student, CourseDTO course) {
        try {
            String url = notificationServiceUrl + "/notify/enrollment";
            NotificationDTO notification = new NotificationDTO(
                    student.getId(),
                    course.getId(),
                    student.getName(),
                    course.getCourseName()
            );
            restTemplate.postForObject(url, notification, String.class);
        } catch (Exception e) {
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
}

