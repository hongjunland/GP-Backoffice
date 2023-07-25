package com.example.demo.application.service;

import com.example.demo.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.application.port.in.usecase.RegisterAttendanceUseCase;
import com.example.demo.application.port.out.SaveAttendancePort;
import com.example.demo.domain.Attendance;
import com.example.demo.global.annotation.UseCase;
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
