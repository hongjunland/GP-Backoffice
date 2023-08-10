package com.example.demo.attendance.adapter.out.persistence.FixedStartTime;

import com.example.demo.attendance.domain.FixedStartTime;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FixedStartTimePersistenceMapper {

    public FixedStartTime mapToDomainEntity(FixedStartTimeJpaEntity fixedStartTimeJpaEntity) {
        return Optional.ofNullable(fixedStartTimeJpaEntity)
                .map(entity -> FixedStartTime.builder()
                        .fixedStartTime(entity.getFixedStartTime())
                        .userId(entity.getUserId())
                        .build())
                .orElse(null);
    }

    public FixedStartTimeJpaEntity mapToJpaEntity(FixedStartTime fixedStartTime) {
        return fixedStartTime.toJpaEntity();
    }
}
