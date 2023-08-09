package com.example.demo.attendance.adapter.out.persistence;


import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.application.port.out.SearchAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.common.annotaion.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class AttendancePersistenceAdapter
        implements SaveAttendancePort, SearchAttendancePort {

    private final AttendanceJpaRepo attendanceJpaRepo;
    private final AttendancePersistenceMapper mapper;

    @Override
    public void saveAttendance(Attendance attendance) {
        attendanceJpaRepo.saveAttendance(mapper.mapToJpaEntity(attendance));
    }

    @Override
    public List<Attendance> searchAttendanceByCriteria(AttendanceSearchCriteria criteria) {
        return mapper.mapToDomainEntities(attendanceJpaRepo.searchAttendanceByCriteria(criteria));
    }
}
