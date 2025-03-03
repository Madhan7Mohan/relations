package com.example.relations.service;

import com.example.relations.model.Course;
import com.example.relations.model.Performance;
import com.example.relations.model.Student;
import com.example.relations.repository.CourseRepository;
import com.example.relations.repository.PerformanceRepository;
import com.example.relations.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Performance addPerformance(Long studentId, Long courseId, int marks) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Performance performance = new Performance();
        performance.setStudent(student);
        performance.setCourse(course);
        performance.setMarks(marks);

        return performanceRepository.save(performance);
    }

    public List<Performance> getStudentPerformance(Long studentId) {
        return performanceRepository.findAll().stream()
                .filter(p -> p.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }
}
