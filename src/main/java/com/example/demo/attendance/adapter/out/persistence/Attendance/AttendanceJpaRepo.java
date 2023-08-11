package com.example.demo.attendance.adapter.out.persistence.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceJpaRepo extends JpaRepository<AttendanceJpaEntity, Long>, AttendanceJpaRepoCustom {

    Optional<AttendanceJpaEntity> findByUserIdAndWorkDate(Long userId, LocalDate workDate);

}
