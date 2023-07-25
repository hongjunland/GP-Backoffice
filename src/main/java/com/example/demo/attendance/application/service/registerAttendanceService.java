package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class registerAttendanceService implements RegisterAttendanceUseCase {

    private final SaveAttendancePort saveAttendancePort;

    @Override
    public void registerAttendance(RegisterAttendanceCommand command) {
        Attendance attendance = command.toEntity();
        saveAttendancePort.saveAttendance(attendance);
    }

}
