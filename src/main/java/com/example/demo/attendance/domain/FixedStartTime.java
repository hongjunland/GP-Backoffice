package com.example.demo.attendance.domain;

import com.example.demo.attendance.adapter.out.persistence.FixedStartTime.FixedStartTimeJpaEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FixedStartTime {

    private final Long fixedStartTimeId;
    private LocalTime fixedStartTime;
    private LocalDate updateDate;
    private final Long userId;

    public FixedStartTimeJpaEntity toJpaEntity() {
        return FixedStartTimeJpaEntity.builder()
                .id(fixedStartTimeId)
                .fixedStartTime(fixedStartTime)
                .updateDate(updateDate)
                .userId(userId)
                .build();
    }

    public FixedStartTime updateFixedStartTime(LocalTime newFixedStartTime, LocalDate updateDate) {
        this.fixedStartTime = newFixedStartTime;
        this.updateDate = updateDate;
        return this;
    }

}
