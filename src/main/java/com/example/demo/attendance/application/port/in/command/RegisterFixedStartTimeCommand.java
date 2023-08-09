package com.example.demo.attendance.application.port.in.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class RegisterFixedStartTimeCommand {

    private final LocalTime fixedStartTime;
    private final Long userId;

}
