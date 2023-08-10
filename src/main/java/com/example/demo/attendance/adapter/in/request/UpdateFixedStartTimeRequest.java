package com.example.demo.attendance.adapter.in.request;

import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class UpdateFixedStartTimeRequest extends SelfValidating<UpdateFixedStartTimeRequest> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime fixedStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate updateDate;

    private Long userId;

    public UpdateFixedStartTimeRequest(
            LocalTime fixedStartTime,
            LocalDate updateDate,
            Long userId
    ) {
        this.fixedStartTime = fixedStartTime;
        this.updateDate = updateDate;
        this.userId = userId;
    }
}
