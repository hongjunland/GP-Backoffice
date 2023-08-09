package com.example.demo.attendance.application.port.in.command;

import com.example.demo.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class CheckLatenessCommand {

    private final LocalTime fixedStartTime;
    private final Long userId;
    private final LocalDate effectiveFromDate;

}
