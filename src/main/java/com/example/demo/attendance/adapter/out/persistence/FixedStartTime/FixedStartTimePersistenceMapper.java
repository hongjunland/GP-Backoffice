package com.example.demo.attendance.adapter.out.persistence.FixedStartTime;

import com.example.demo.attendance.domain.FixedStartTime;
import org.springframework.stereotype.Component;

@Component
public class FixedStartTimePersistenceMapper {

    public FixedStartTimeJpaEntity mapToJpaEntity(FixedStartTime fixedStartTime) {
        return fixedStartTime.toJpaEntity();
    }
}
