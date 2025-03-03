package com.example.relations.repository;

import com.example.relations.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance,Long> {
}
