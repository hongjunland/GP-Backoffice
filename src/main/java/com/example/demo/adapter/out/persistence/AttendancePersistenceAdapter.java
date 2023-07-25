package com.example.demo.adapter.out.persistence;

import com.example.demo.application.port.out.SaveAttendancePort;
import com.example.demo.domain.Attendance;
import com.example.demo.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AttendancePersistenceAdapter
        implements SaveAttendancePort {

    private final AttendanceJpaRepo repo;
    private final AttendancePersistenceMapper mapper;

    @Override
    public void saveAttendance(Attendance attendance) {
        repo.save(mapper.mapToJpaEntity(attendance));
    }

}
