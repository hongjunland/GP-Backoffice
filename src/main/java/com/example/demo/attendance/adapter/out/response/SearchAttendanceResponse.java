package com.example.demo.attendance.adapter.out.response;

import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class SearchAttendanceResponse {

    private Department department;
    private String name;
    private LocalDate workDate;
    private DayType dayType;
    private LocalTime startTime;
    private LocalTime endTime;

}
