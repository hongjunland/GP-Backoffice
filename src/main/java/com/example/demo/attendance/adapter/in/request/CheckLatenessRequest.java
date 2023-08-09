package com.example.demo.attendance.adapter.in.request;

import com.example.demo.common.SelfValidating;
import com.example.demo.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class CheckLatenessRequest extends SelfValidating<CheckLatenessRequest> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private final LocalTime fixedStartTime;

    private final Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate effectiveFromDate;

    public CheckLatenessRequest(
            LocalTime fixedStartTime,
            Long userId,
            LocalDate effectiveFromDate
    ) {
        this.fixedStartTime = fixedStartTime;
        this.userId = userId;
        this.effectiveFromDate = effectiveFromDate;
        this.validateSelf();
    }
}
