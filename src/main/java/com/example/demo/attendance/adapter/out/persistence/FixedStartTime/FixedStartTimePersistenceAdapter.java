package com.example.demo.attendance.adapter.out.persistence.FixedStartTime;

import com.example.demo.attendance.application.port.out.LoadFixedStartTimePort;
import com.example.demo.attendance.application.port.out.SaveFixedStartTimePort;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FixedStartTimePersistenceAdapter
        implements SaveFixedStartTimePort, LoadFixedStartTimePort {

    private final FixedStartTimeJpaRepo fixedStartTimeJpaRepo;
    private final FixedStartTimePersistenceMapper mapper;

    @Override
    public void saveFixedStartTime(FixedStartTime fixedStartTime) {
        fixedStartTimeJpaRepo.save(mapper.mapToJpaEntity(fixedStartTime));
    }

    @Override
    public FixedStartTime loadFixedStartTime(Long userId) {
        return mapper.mapToDomainEntity(fixedStartTimeJpaRepo.findByUserId(userId));
    }
}
