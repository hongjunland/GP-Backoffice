package com.example.demo.attendance.application.port.in.query;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SearchAttendanceCriteria {

    private final LocalDate startDate;
    private final LocalDate endDate;

}
