package com.example.demo.attendance.adapter.in.request;

import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterFixedStartTimeRequest extends SelfValidating<RegisterFixedStartTimeRequest> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private final LocalTime fixedStartTime;

    private final Long userId;

    public RegisterFixedStartTimeRequest(
            LocalTime fixedStartTime,
            Long userId
    ) {
        this.fixedStartTime = fixedStartTime;
        this.userId = userId;
        this.validateSelf();
    }
}
