package com.example.demo.attendance.domain;

import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaEntity;
import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.attendance.domain.constant.DayType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attendance {

    private final AttendanceId attendanceId;
    private final Long userId;
    private Department department;
    private String name;
    private LocalDate workDate;
    private DayType dayType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String attendanceStatus;

    /*
    식별자 사용해서 해당 Domain Entity를 고유하게 식별해주기 위함입니다.
    식별성을 유지하면서도 비즈니스 로직을 더욱 명확하게 표현할 수 있습니다.
    */
    @Value
    public static class AttendanceId {
        private Long value;
    }

    public AttendanceJpaEntity toJpaEntity() {
        return AttendanceJpaEntity.builder()
                .userId(userId)
                .department(department)
                .name(name)
                .workDate(workDate)
                .dayType(dayType)
                .startTime(startTime)
                .endTime(endTime)
                .attendanceStatus(attendanceStatus)
                .build();
    }

}
