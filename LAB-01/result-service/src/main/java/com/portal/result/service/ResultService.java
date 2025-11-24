package com.portal.result.service;

import com.portal.result.dto.*;
import com.portal.result.entity.Result;
import com.portal.result.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    
    @Autowired
    private ResultRepository resultRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${student.service.url}")
    private String studentServiceUrl;
    
    @Value("${course.service.url}")
    private String courseServiceUrl;
    
    public ResultResponseDTO createResult(ResultRequestDTO request) {
        // Validate student exists
        StudentDTO student = validateStudent(request.getStudentId());
        
        // Validate course exists
        CourseDTO course = validateCourse(request.getCourseId());
        
        // Create result
        Result result = new Result(
                request.getStudentId(),
                request.getCourseId(),
                request.getGrade(),
                request.getScore()
        );
        Result savedResult = resultRepository.save(result);
        
        // Create response
        return new ResultResponseDTO(
                savedResult.getId(),
                savedResult.getStudentId(),
                savedResult.getCourseId(),
                student.getName(),
                course.getCourseName(),
                savedResult.getGrade(),
                savedResult.getScore(),
                savedResult.getRecordedDate()
        );
    }
    
    public List<ResultResponseDTO> getResultsByStudentId(Long studentId) {
        // Validate student exists
        validateStudent(studentId);
        
        List<Result> results = resultRepository.findByStudentId(studentId);
        
        return results.stream()
                .map(result -> {
                    StudentDTO student = getStudent(result.getStudentId());
                    CourseDTO course = getCourse(result.getCourseId());
                    
                    return new ResultResponseDTO(
                            result.getId(),
                            result.getStudentId(),
                            result.getCourseId(),
                            student != null ? student.getName() : "Unknown",
                            course != null ? course.getCourseName() : "Unknown",
                            result.getGrade(),
                            result.getScore(),
                            result.getRecordedDate()
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
}

