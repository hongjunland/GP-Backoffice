package com.example.demo.attendance.adapter.out.persistence;


import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.common.annotaion.PersistenceAdapter;
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
