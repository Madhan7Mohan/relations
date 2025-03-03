package com.example.relations.controller;

import com.example.relations.model.Performance;
import com.example.relations.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance")
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping("/add")
    public ResponseEntity<Performance> addPerformance(@RequestParam Long studentId,
                                                      @RequestParam Long courseId,
                                                      @RequestParam int marks) {
        return ResponseEntity.ok(performanceService.addPerformance(studentId, courseId, marks));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Performance>> getPerformance(@PathVariable Long studentId) {
        return ResponseEntity.ok(performanceService.getStudentPerformance(studentId));
    }
}
