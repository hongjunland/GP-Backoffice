package com.example.demo.attendance.application.port.in.command;

import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.constant.DayType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class RegisterAttendanceCommand {

    // command는 불변성을 지키기 위해 final을 사용했습니다.
    private final Long userId;
    private final Department department;
    private final String name;
    private final LocalDate workDate;
    private final DayType dayType;
    private final LocalTime startTime;
    private final LocalTime endTime;

    // command에서 직접적으로 Entity를 생성하거나 변경하는 것은 두 레이어 사이에 의존성이 생기게 됨.
    // 추후 service 레이어에서 변경 예정입니다.
    public Attendance toEntity() {
        return Attendance.builder()
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
