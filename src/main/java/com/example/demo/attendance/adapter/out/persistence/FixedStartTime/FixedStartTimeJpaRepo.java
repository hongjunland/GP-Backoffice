package com.example.demo.attendance.adapter.out.persistence.FixedStartTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedStartTimeJpaRepo extends JpaRepository<FixedStartTimeJpaEntity, Long> {

    FixedStartTimeJpaEntity findByUserId(Long userId);

}
