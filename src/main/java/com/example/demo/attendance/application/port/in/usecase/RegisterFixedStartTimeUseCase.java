package com.example.demo.attendance.application.port.in.usecase;

import com.example.demo.attendance.application.port.in.command.RegisterFixedStartTimeCommand;

public interface RegisterFixedStartTimeUseCase {

    void RegisterFixedStartTime(RegisterFixedStartTimeCommand command);

}
