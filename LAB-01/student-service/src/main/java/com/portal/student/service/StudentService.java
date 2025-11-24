package com.portal.student.service;

import com.portal.student.dto.StudentDTO;
import com.portal.student.entity.Student;
import com.portal.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }
    
    public Optional<StudentDTO> updateStudent(Long id, StudentDTO studentDTO) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(studentDTO.getName());
                    student.setEmail(studentDTO.getEmail());
                    student.setProgram(studentDTO.getProgram());
                    return convertToDTO(studentRepository.save(student));
                });
    }
    
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getProgram()
        );
    }
    
    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setProgram(dto.getProgram());
        return student;
    }
}

