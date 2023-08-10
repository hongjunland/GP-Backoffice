package com.example.demo.attendance.adapter.out.persistence.Attendance;

import com.example.demo.attendance.domain.Attendance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .attendanceStatus(attendanceJpaEntity.getAttendanceStatus())
                .fixedStartTime(attendanceJpaEntity.getFixedStartTime())
                .build();
    }

    public List<Attendance> mapToDomainEntities(List<AttendanceJpaEntity> attendanceJpaEntities) {
        List<Attendance> attendances = new ArrayList<>();
        for (AttendanceJpaEntity attendanceJpaEntity : attendanceJpaEntities) {
            attendances.add(mapToDomainEntity(attendanceJpaEntity));
        }
        return attendances;
    }

    public AttendanceJpaEntity mapToJpaEntity(Attendance attendance) {
        return attendance.toJpaEntity();
    }

}
