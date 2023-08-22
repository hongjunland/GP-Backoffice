package com.example.demo.attendance.adapter.in.request;

import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = false)
public class SearchAttendanceRequest extends SelfValidating<SearchAttendanceRequest> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate endDate;

    public SearchAttendanceRequest(
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.validateSelf();
    }

}
