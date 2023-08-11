package com.example.demo.attendance.adapter.out.persistence.Attendance;


import com.example.demo.attendance.application.port.out.LoadAttendancePort;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.application.port.out.SearchAttendancePort;
import com.example.demo.attendance.application.port.out.UpdateAttendanceStatusPort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.common.annotaion.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class AttendancePersistenceAdapter
        implements SaveAttendancePort, LoadAttendancePort, SearchAttendancePort,
        UpdateAttendanceStatusPort {

    private final AttendanceJpaRepo attendanceJpaRepo;
    private final AttendancePersistenceMapper mapper;

    @Override
    public void saveAttendance(Attendance attendance) {
        attendanceJpaRepo.save(mapper.mapToJpaEntity(attendance));
    }

    @Override
    public Attendance loadAttendanceByAttendanceId(Attendance.AttendanceId attendanceId) {
        return mapper.mapToDomainEntity(attendanceJpaRepo.findById(attendanceId.getValue()).get());
        // todo : 없을 경우 에러 처리 해주기
    }

    @Override
    public Attendance loadAttendanceByUserIdAndWorkDate(Long userId, LocalDate workDate) {
        return attendanceJpaRepo.findByUserIdAndWorkDate(userId, workDate)
                .map(mapper::mapToDomainEntity)
                .orElse(null);
    }

    @Override
    public List<Attendance> searchAttendanceByCriteria(AttendanceSearchCriteria criteria) {
        return mapper.mapToDomainEntities(attendanceJpaRepo.searchAttendanceByCriteria(criteria));
    }

    @Override
    public void updateAttendanceStatus(FixedStartTime fixedStartTime) {
        attendanceJpaRepo.updateAttendanceStatus(fixedStartTime);
    }
}
