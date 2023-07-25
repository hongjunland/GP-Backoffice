package com.example.demo.attendance.application.port.in.usecase;

import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;

public interface RegisterAttendanceUseCase {

    void registerAttendance(RegisterAttendanceCommand command);

}
