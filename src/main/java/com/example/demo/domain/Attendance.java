package com.example.demo.domain;

import com.example.demo.adapter.out.persistence.AttendanceJpaEntity;
import com.example.demo.domain.constant.DayType;
import com.example.demo.domain.constant.Department;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attendance {

    private AttendanceId attendanceId;
    private Long userId;
    private Department department;
    private String name;
    private LocalDate workDate;
    private DayType dayType;
    private LocalTime startTime;
    private LocalTime endTime;

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
                .id(attendanceId.getValue())
                .userId(userId)
                .department(department)
                .name(name)
                .workDate(workDate)
                .dayType(dayType)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
