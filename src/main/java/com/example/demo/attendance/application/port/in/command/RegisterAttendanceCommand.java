package com.example.demo.attendance.application.port.in.command;

import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.attendance.domain.constant.DayType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
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

}
