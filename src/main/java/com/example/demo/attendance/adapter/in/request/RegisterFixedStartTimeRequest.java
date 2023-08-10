package com.example.demo.attendance.adapter.in.request;

import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterFixedStartTimeRequest extends SelfValidating<RegisterFixedStartTimeRequest> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime fixedStartTime;

    private Long userId;

    public RegisterFixedStartTimeRequest(
            LocalTime fixedStartTime,
            Long userId
    ) {
        this.fixedStartTime = fixedStartTime;
        this.userId = userId;
        this.validateSelf();
    }
}
