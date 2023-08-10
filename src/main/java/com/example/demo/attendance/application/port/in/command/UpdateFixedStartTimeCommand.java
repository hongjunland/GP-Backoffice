package com.example.demo.attendance.application.port.in.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class UpdateFixedStartTimeCommand {

    private final LocalTime fixedStartTime;
    private final LocalDate updateDate;
    private final Long userId;

}
