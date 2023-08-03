package com.example.demo.attendance.adapter.out.persistence;

import com.example.demo.attendance.domain.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendancePersistenceMapper {
    public Attendance mapToDomainEntity(AttendanceJpaEntity attendanceJpaEntity) {

        return Attendance.builder()
                .attendanceId(new Attendance.AttendanceId(attendanceJpaEntity.getId()))
                .userId(attendanceJpaEntity.getUserId())
                .department(attendanceJpaEntity.getDepartment())
                .name(attendanceJpaEntity.getName())
                .workDate(attendanceJpaEntity.getWorkDate())
                .dayType(attendanceJpaEntity.getDayType())
                .startTime(attendanceJpaEntity.getStartTime())
                .endTime(attendanceJpaEntity.getEndTime())
                .build();
    }

    public AttendanceJpaEntity mapToJpaEntity(Attendance attendance) {
        return attendance.toJpaEntity();
    }

}
