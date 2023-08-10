package com.example.demo.attendance.adapter.in.response;

import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@EqualsAndHashCode
public class SearchAttendanceResponse {

    private Department department;
    private String name;
    private LocalDate workDate;
    private DayType dayType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String attendanceStatus;

}
