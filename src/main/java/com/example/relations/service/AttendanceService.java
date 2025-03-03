package com.example.relations.service;

import com.example.relations.model.Attendance;
import com.example.relations.model.Course;
import com.example.relations.model.Student;
import com.example.relations.repository.AttendanceRepository;
import com.example.relations.repository.CourseRepository;
import com.example.relations.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Attendance markAttendance(Long studentId, Long courseId, LocalDate date, boolean present) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setDate(date);
        attendance.setPresent(present);

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getStudentAttendance(Long studentId) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }
}
