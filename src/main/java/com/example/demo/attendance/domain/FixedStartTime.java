package com.example.demo.attendance.domain;

import com.example.demo.attendance.adapter.out.persistence.FixedStartTime.FixedStartTimeJpaEntity;
import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FixedStartTime {

    private final LocalTime fixedStartTime;
    private final Long userId;

    public FixedStartTimeJpaEntity toJpaEntity() {
        return FixedStartTimeJpaEntity.builder()
                .fixedStartTime(fixedStartTime)
                .userId(userId)
                .build();
    }

}
