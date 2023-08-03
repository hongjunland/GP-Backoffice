package com.example.demo.attendance.application.port.in.query;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SearchAttendanceCriteria {

    private final LocalDate startDate;
    private final LocalDate endDate;

}
