package com.example.demo.attendance.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceJpaRepo extends JpaRepository<AttendanceJpaEntity, Long> {
}
