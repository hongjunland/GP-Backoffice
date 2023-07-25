package com.example.demo.application.port.in.usecase;

import com.example.demo.application.port.in.command.RegisterAttendanceCommand;

public interface RegisterAttendanceUseCase {

    void registerAttendance(RegisterAttendanceCommand command);

}
