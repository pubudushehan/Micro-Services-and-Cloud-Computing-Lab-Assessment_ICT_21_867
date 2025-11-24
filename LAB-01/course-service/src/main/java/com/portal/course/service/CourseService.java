package com.portal.course.service;

import com.portal.course.dto.CourseDTO;
import com.portal.course.entity.Course;
import com.portal.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<CourseDTO> getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }
    
    public Optional<CourseDTO> updateCourse(Long id, CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setCourseCode(courseDTO.getCourseCode());
                    course.setCourseName(courseDTO.getCourseName());
                    course.setCredits(courseDTO.getCredits());
                    course.setInstructor(courseDTO.getInstructor());
                    return convertToDTO(courseRepository.save(course));
                });
    }
    
    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private CourseDTO convertToDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getCredits(),
                course.getInstructor()
        );
    }
    
    private Course convertToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());
        course.setInstructor(dto.getInstructor());
        return course;
    }
}

