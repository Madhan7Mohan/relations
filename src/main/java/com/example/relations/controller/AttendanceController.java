package com.example.relations.controller;

import com.example.relations.model.Attendance;
import com.example.relations.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<Attendance> markAttendance(@RequestParam Long studentId,
                                                     @RequestParam Long courseId,
                                                     @RequestParam String date,
                                                     @RequestParam boolean present) {
        LocalDate attendanceDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.markAttendance(studentId, courseId, attendanceDate, present));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getStudentAttendance(studentId));
    }
}
