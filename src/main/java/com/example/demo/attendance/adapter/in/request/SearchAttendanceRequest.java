package com.example.demo.attendance.adapter.in.request;

import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = false)
public class SearchAttendanceRequest extends SelfValidating<SearchAttendanceRequest> {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

    private final Department department;

    private final String name;

    public SearchAttendanceRequest(
            LocalDate startDate,
            LocalDate endDate,
            Department department,
            String name
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.department = department;
        this.validateSelf();
    }

}
